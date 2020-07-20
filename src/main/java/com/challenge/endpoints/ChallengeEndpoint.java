package com.challenge.endpoints;

import com.challenge.dto.ChallengeDTO;
import com.challenge.entity.Challenge;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.ChallengeMapper;
import com.challenge.service.impl.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/challenge")
public class ChallengeEndpoint {

    private final ChallengeService service;
    private final ChallengeMapper mapper;

    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> findByAccelerationIdAndUserId(
            @RequestParam(value = "accelerationId", required = false) Long accelerationId,
            @RequestParam(value = "userId", required = false) Long userId) {

        List<Challenge> challenges = service.findByAccelerationIdAndUserId(accelerationId, userId);

        if (challenges.isEmpty()) {
            new ResourceNotFoundException();
        }

        return ResponseEntity.ok().body(mapper.map(challenges));
    }
}
