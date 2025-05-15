package uz.pdp.appneedtostarttest.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicReqDTO {

    @NotBlank(message = "TOPIC_NAME_MUST_NOT_BE_EMPTY")
    @NotNull(message = "TOPIC_NAME_MUST_NOT_BE_NULL")
    private String name;


    private String description;

    @NotNull(message = "SUBJECT_ID_NAME_MUST_NOT_BE_NULL")
    private UUID subjectId;

}
