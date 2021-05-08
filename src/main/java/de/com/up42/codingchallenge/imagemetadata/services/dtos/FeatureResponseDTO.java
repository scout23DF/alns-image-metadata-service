package de.com.up42.codingchallenge.imagemetadata.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FeatureResponseDTO implements IGenericBaseDTO {

    private String id;
    private Date timestamp;
    private Date beginViewingDate;
    private Date endViewingDate;
    private String missionName;

}
