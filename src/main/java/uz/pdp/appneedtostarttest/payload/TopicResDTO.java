package uz.pdp.appneedtostarttest.payload;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicResDTO {

    private UUID id;

    private String name;

    private String description;

    private SubjectResDTO subjectResDTO;
}
