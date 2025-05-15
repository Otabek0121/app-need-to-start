package uz.pdp.appneedtostarttest.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectReqDTO {

    @NotNull(message = "SUBJECT_NAME_MUST_NOT_BE_NULL")
    @NotBlank(message = "SUBJECT_NAME_MUST_NOT_BE_EMPTY")
    private String name;

    private String description;

}
