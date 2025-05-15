package uz.pdp.appneedtostarttest.service;

import uz.pdp.appneedtostarttest.entity.User;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.RefreshTokenDTO;
import uz.pdp.appneedtostarttest.payload.SignInRequestDTO;
import uz.pdp.appneedtostarttest.payload.SignUpRequestDTO;
import uz.pdp.appneedtostarttest.payload.TokenDTO;

import java.util.UUID;


public interface AuthService {

    ApiResult<TokenDTO> login(SignInRequestDTO signInRequestDTO);

    ApiResult<TokenDTO> refreshToken(RefreshTokenDTO refreshTokenDTO);

    ApiResult<TokenDTO> signUp(SignUpRequestDTO signUpRequestDTO);
}
