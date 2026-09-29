package com.salesianos.triana.apimonumentos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monument {

    @Id @GeneratedValue
    private Long id;

    private String countryCode;
    private String countryName;
    private String city;

    private Double latitude;
    private Double longitude;

    private String name;

    @Column(length = 2000)
    private String description;

    private String photoUrl;

}
