package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/candidate")
public class CandidateEndpoint {

    private final CandidateService service;
    private final CandidateMapper mapper;

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findById(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "companyId") Long companyId,
            @PathVariable(value = "accelerationId") Long accelerationId) {

        return ResponseEntity.ok()
                .body(mapper.map(service.findById(userId, companyId, accelerationId)
                        .orElse(new Candidate())));
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findByCompanyIdAndOrAccelerationId(
            @RequestParam(value = "companyId", required = false, defaultValue = "0") Long companyId,
            @RequestParam(value = "accelerationId", required = false, defaultValue = "0") Long accelerationId) {

        List<Candidate> candidates = new ArrayList<>();
        if (companyId != 0) candidates.addAll(service.findByCompanyId(companyId));
        if (accelerationId != 0) candidates.addAll(service.findByAccelerationId(accelerationId));

        if (candidates.isEmpty()) {
            new ResourceNotFoundException();
        }

        return ResponseEntity.ok().body(mapper.map(candidates));
    }
}
