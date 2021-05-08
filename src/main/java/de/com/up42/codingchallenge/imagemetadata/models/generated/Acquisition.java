package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Acquisition {

    private Date beginViewingDate;
    private Date endViewingDate;
    private String mission;
    private String missionId;
    private String missionCode;
    private String missionName;
    private String polarization;
    private String sensorMode;
    private String sensorId;

}
