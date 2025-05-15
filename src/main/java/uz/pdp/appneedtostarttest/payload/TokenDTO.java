package uz.pdp.appneedtostarttest.payload;

import lombok.*;
import uz.pdp.appneedtostarttest.utils.AppConstant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

    private final String tokenType = AppConstant.TOKEN_TYPE;

    private String accessToken;

    private String refreshToken;
}
