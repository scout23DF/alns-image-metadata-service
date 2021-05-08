package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Geometry {

    private String type;
    private List<List<List<Double>>> coordinates;
    private GeographicBoundingPolygon geographicBoundingPolygon;
    private Boolean global;
    private CenterPoint centerPoint;

}
