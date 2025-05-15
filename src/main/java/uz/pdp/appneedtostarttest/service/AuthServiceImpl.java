package uz.pdp.appneedtostarttest.service;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appneedtostarttest.entity.User;
import uz.pdp.appneedtostarttest.exception.RestException;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.RefreshTokenDTO;
import uz.pdp.appneedtostarttest.payload.SignInRequestDTO;
import uz.pdp.appneedtostarttest.payload.SignUpRequestDTO;
import uz.pdp.appneedtostarttest.payload.TokenDTO;
import uz.pdp.appneedtostarttest.repository.UserRepository;
import uz.pdp.appneedtostarttest.security.JwtTokenProvider;
import uz.pdp.appneedtostarttest.utils.MessageConstants;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static uz.pdp.appneedtostarttest.utils.MessageConstants.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ApiResult<TokenDTO> login(SignInRequestDTO signInRequestDTO) {

        User user = userRepository.findByEmail(signInRequestDTO.getEmail())
                .orElseThrow(() -> RestException.restThrow(MessageConstants.USER_NOT_FOUND));


        if (!passwordEncoder.matches(signInRequestDTO.getPassword(), user.getPassword()))
            throw RestException.restThrow(MessageConstants.LOGIN_OR_PASSWORD_ERROR);
        Timestamp issuedAt = Timestamp.valueOf(LocalDateTime.now());
        String accessToken = jwtTokenProvider.generateAccessToken(user, issuedAt);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        user.setTokenIssuedAt(issuedAt);
        userRepository.save(user);

        return ApiResult.success(
                TokenDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build());
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(RefreshTokenDTO refreshTokenDTO) {


        String accessToken = refreshTokenDTO.getAccessToken().trim();
        accessToken = getTokenWithOutBearer(accessToken);
        try {
            jwtTokenProvider.getClaimsJws(accessToken, Boolean.TRUE);
        } catch (ExpiredJwtException ex) {
            try {
                String refreshToken = refreshTokenDTO.getRefreshToken();
                refreshToken = getTokenWithOutBearer(refreshToken);

                String userId = jwtTokenProvider.getUserIdFromToken(refreshToken, Boolean.FALSE);
                User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> RestException.restThrow(TOKEN_NOT_VALID));

                if (!user.isEnabled()
                        || !user.isAccountNonExpired()
                        || !user.isAccountNonLocked()
                        || !user.isCredentialsNonExpired())
                    throw RestException.restThrow(USER_PERMISSION_RESTRICTION);


                Timestamp tokenIssuedAt = new Timestamp(System.currentTimeMillis() / 1000 * 1000);
                String newAccessToken = jwtTokenProvider.generateAccessToken(user, tokenIssuedAt);
                String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);

                user.setTokenIssuedAt(tokenIssuedAt);
                userRepository.save(user);

                TokenDTO tokenDTO = TokenDTO.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(newRefreshToken)
                        .build();
                return ApiResult.success(tokenDTO);
            } catch (Exception e) {
                throw RestException.restThrow(REFRESH_TOKEN_EXPIRED);
            }
        } catch (Exception ex) {
            throw RestException.restThrow(WRONG_ACCESS_TOKEN);
        }
        throw RestException.restThrow(ACCESS_TOKEN_NOT_EXPIRED);

    }

    @Override
    public ApiResult<TokenDTO> signUp(SignUpRequestDTO signUpRequestDTO) {

        if(userRepository.existsByEmail(signUpRequestDTO.getEmail())){
             throw RestException.alreadyExists("USER_ALREADY_EXISTS");
        }

        if(!Objects.equals(signUpRequestDTO.getPassword(), signUpRequestDTO.getPrePassword())){
            throw RestException.restThrow("PASSWORD_NOT_MATCH");
        }

        User user = new User();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        userRepository.save(user);

        Timestamp issuedAt = Timestamp.valueOf(LocalDateTime.now());
        String accessToken = jwtTokenProvider.generateAccessToken(user, issuedAt);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        user.setTokenIssuedAt(issuedAt);
        userRepository.save(user);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(accessToken);
        tokenDTO.setRefreshToken(refreshToken);

        return ApiResult.success(tokenDTO);
    }

    private static String getTokenWithOutBearer(String token) {
        return token.trim();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //USERNI Email ORQALI DB DAN OLYAPDI TOPILMASA THROW TASHLAYDI
        return userRepository
                .findFirstByEmailAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(email)
                        .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
