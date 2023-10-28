package toy.five.triprecord.domain.jouney.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.jouney.dto.response.JourneyDetailResponse;
import toy.five.triprecord.domain.jouney.dto.request.*;
import toy.five.triprecord.domain.jouney.dto.response.JourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.response.LodgmentJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.dto.response.MoveJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.dto.response.VisitJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.service.JourneyService;

import java.time.LocalDateTime;
import java.util.List;

import static toy.five.triprecord.domain.jouney.entity.JourneyType.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/journeys")
@RestController
public class JourneyController {

    private JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping
    public List<JourneyDetailResponse> getAllJourneysFromTrip(@RequestParam Long tripId) {

        return journeyService.getAllJourneysByTripId(tripId);

    }

    @PostMapping("/{tripId}")
    public JourneyCreateResponse createJourney(
            @PathVariable Long tripId,
            @RequestBody @Valid JourneyCreateRequest request
    ) {
        return journeyService.saveJourneys(tripId, request);
    }

    @PutMapping("/move/{journeyId}")
    public MoveJourneyUpdateResponse updateMoveJourney(
            @PathVariable Long journeyId,
            @RequestBody @Valid MoveJourneyUpdateRequest updateRequest
    ) {
        return journeyService.modifyMoveJourney(journeyId, updateRequest);
    }

    @PutMapping("/lodgment/{journeyId}")
    public LodgmentJourneyUpdateResponse updateLodgmentJourney(
            @PathVariable Long journeyId,
            @RequestBody @Valid LodgmentJourneyUpdateRequest updateRequest
    ) {
        return journeyService.modifyLodgmentJourney(journeyId, updateRequest);
    }

    @PutMapping("/visit/{journeyId}")
    public VisitJourneyUpdateResponse updateVisitJourney(
            @PathVariable Long journeyId,
            @RequestBody @Valid VisitJourneyUpdateRequest updateRequest
    ) {

        return journeyService.modifyVisitJourney(journeyId, updateRequest);
    }

}
