package com.challenge.endpoints;

import com.challenge.dto.CompanyDTO;
import com.challenge.entity.Company;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.CompanyMapper;
import com.challenge.service.impl.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyEndpoint {

    private final CompanyService service;
    private final CompanyMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(mapper.map(service.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException())));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findByUserIdAndOrAccelerationId(
            @RequestParam(value = "userId", required = false, defaultValue = "0") Long userId,
            @RequestParam(value = "accelerationId", required = false, defaultValue = "0") Long accelerationId) {

        List<Company> companies = new ArrayList<>();

        if (userId != 0) companies.addAll(service.findByUserId(userId));
        if (accelerationId != 0) companies.addAll(service.findByAccelerationId(accelerationId));

        if (companies.isEmpty()) {
            new ResourceNotFoundException();
        }

        return ResponseEntity.ok().body(mapper.map(companies));
    }
}
