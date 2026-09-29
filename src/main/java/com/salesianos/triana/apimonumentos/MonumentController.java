package com.salesianos.triana.apimonumentos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monument")
public class MonumentController {

    private final MonumentRepository monumentRepository;

    @GetMapping
    public ResponseEntity<List<Monument>> getAllMonuments() {
        List<Monument> result = monumentRepository.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monument> getMonumentById(@PathVariable Long id) {
        return ResponseEntity.of(monumentRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Monument> addMonument(@RequestBody Monument monument) {
        if (StringUtils.hasText(monument.getName())) {
            return ResponseEntity.status(201)
                    .body(monumentRepository.save(monument));
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Monument> updateMonument(
            @PathVariable Long id,
            @RequestBody Monument monument) {

        if (monument == null) {
            return ResponseEntity.badRequest().build();
        }

        

        return monumentRepository.findById(id)
                .map(m -> {
                    m.setCountryCode(monument.getCountryCode().trim().toUpperCase());
                    m.setCountryName(monument.getCountryName().trim());
                    m.setCity(monument.getCity().trim());
                    m.setLatitude(monument.getLatitude());
                    m.setLongitude(monument.getLongitude());
                    m.setName(monument.getName().trim());
                    m.setDescription(monument.getDescription());
                    m.setPhotoUrl(monument.getPhotoUrl());
                    return ResponseEntity.ok(monumentRepository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonument(@PathVariable Long id) {
        monumentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
