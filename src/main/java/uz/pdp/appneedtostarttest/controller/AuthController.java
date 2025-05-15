package uz.pdp.appneedtostarttest.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.RefreshTokenDTO;
import uz.pdp.appneedtostarttest.payload.SignInRequestDTO;
import uz.pdp.appneedtostarttest.payload.SignUpRequestDTO;
import uz.pdp.appneedtostarttest.payload.TokenDTO;
import uz.pdp.appneedtostarttest.utils.AppConstant;

@RequestMapping(AuthController.BASE_PATH_AUTH)
public interface AuthController {

    String BASE_PATH_AUTH = AppConstant.BASE_PATH_V1+"/auth";

    @PostMapping("/login")
    ApiResult<TokenDTO> login(@RequestBody SignInRequestDTO signInRequestDTO);

    @PostMapping("/refresh-token")
    ApiResult<TokenDTO> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO);

    @PostMapping("/sign-up")
    ApiResult<TokenDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO);
}
