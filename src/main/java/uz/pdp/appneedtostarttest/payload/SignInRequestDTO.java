package uz.pdp.appneedtostarttest.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.appneedtostarttest.utils.AppConstant;
import uz.pdp.appneedtostarttest.utils.MessageConstants;


@Getter
@Setter
public class SignInRequestDTO {

    @NotBlank(message = MessageConstants.EMAIL_CAN_NOT_BE_EMPTY)
    @Pattern(regexp = AppConstant.EMAIL_REGEX,message = "EMAIL_DID_NOT_MATCH")
    private String email;

    @NotBlank(message = MessageConstants.PASSWORD_CAN_NOT_BE_EMPTY)
    @Pattern(regexp = AppConstant.PASSWORD_REGEX,message = "PASSWORD_DID_NOT_MATCH")
    private String password;
}
