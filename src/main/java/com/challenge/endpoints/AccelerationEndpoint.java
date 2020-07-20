package com.challenge.endpoints;

import com.challenge.dto.AccelerationDTO;
import com.challenge.entity.Acceleration;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.AccelerationMapper;
import com.challenge.service.impl.AccelerationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/acceleration")
public class AccelerationEndpoint {

    private final AccelerationService service;
    private final AccelerationMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<AccelerationDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok()
                .body(mapper.map(service.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException())));
    }

    @GetMapping
    public ResponseEntity<List<AccelerationDTO>> findByCompanyId(
            @RequestParam(name = "companyId", required = false, defaultValue = "0") Long companyId) {

        List<Acceleration> accelerations = service.findByCompanyId(companyId);

        if (accelerations.isEmpty()) {
            new ResourceNotFoundException();
        }

        return ResponseEntity.ok().body(mapper.map(service.findByCompanyId(companyId)));
    }
}
