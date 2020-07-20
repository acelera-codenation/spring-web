package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Submission;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/submission")
public class SubmissionEndpoint {

    private final SubmissionService service;
    private final SubmissionMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findByChallengeIdAndAccelerationId(
            @RequestParam(value = "challengeId", required = true) Long challengeId,
            @RequestParam(value = "accelerationId", required = true) Long accelerationId) {

        List<Submission> submissions = service.findByChallengeIdAndAccelerationId(challengeId, accelerationId);

        if (submissions.isEmpty()) {
            new ResourceNotFoundException();
        }

        return ResponseEntity.ok().body(mapper.map(submissions));
    }
}
