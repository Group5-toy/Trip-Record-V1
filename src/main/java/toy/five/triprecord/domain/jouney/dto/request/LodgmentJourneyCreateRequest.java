package toy.five.triprecord.domain.jouney.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.entity.JourneyType;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LodgmentJourneyCreateRequest {

    @NotNull
    private String name;
    @NotNull
    private String dormitoryName;
    @NotNull
    private JourneyType type;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;


}
