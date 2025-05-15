package uz.pdp.appneedtostarttest.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.net.ErrorData;
import uz.pdp.appneedtostarttest.utils.AppConstant;
import uz.pdp.appneedtostarttest.utils.MessageConstants;


import java.io.IOException;
import java.util.List;
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException {
        log.error("Responding with unauthorized error. URL -  {}, Message - {}", req.getRequestURI(), e.getMessage());
        ApiResult<ErrorData> apiResult = ApiResult.errorResponse(MessageConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED.value());
        resp.getWriter().write(AppConstant.objectMapper.writeValueAsString(apiResult));
        resp.setStatus(HttpStatus.UNAUTHORIZED.value());
        resp.setContentType("application/json");
    }

}
