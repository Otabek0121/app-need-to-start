package uz.pdp.appneedtostarttest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.RefreshTokenDTO;
import uz.pdp.appneedtostarttest.payload.SignInRequestDTO;
import uz.pdp.appneedtostarttest.payload.SignUpRequestDTO;
import uz.pdp.appneedtostarttest.payload.TokenDTO;
import uz.pdp.appneedtostarttest.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ApiResult<TokenDTO> login(SignInRequestDTO signInRequestDTO){
       return authService.login(signInRequestDTO);
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(RefreshTokenDTO refreshTokenDTO){
        return authService.refreshToken(refreshTokenDTO);
    }

    @Override
    public ApiResult<TokenDTO> signUp(SignUpRequestDTO signUpRequestDTO) {
        return authService.signUp(signUpRequestDTO);
    }
}
