package com.challenge.endpoints;

import com.challenge.dto.UserDTO;
import com.challenge.entity.User;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.UserMapper;
import com.challenge.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(mapper.map(service
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException())));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findByCompanyIdOrAccelerationName(
            @RequestParam(value = "companyId", required = false, defaultValue = "0") Long companyId,
            @RequestParam(value = "accelerationName", required = false, defaultValue = "") String accelerationName) {

        List<User> users = new ArrayList<>();
        if (companyId != 0) users.addAll(service.findByCompanyId(companyId));
        if (!accelerationName.isEmpty()) users.addAll(service.findByAccelerationName(accelerationName));

        if (users.isEmpty()) {
            new ResourceNotFoundException();
        }

        return ResponseEntity.ok(mapper.map(users));
    }
}
