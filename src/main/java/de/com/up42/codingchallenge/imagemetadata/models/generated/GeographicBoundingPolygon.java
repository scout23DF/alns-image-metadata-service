package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GeographicBoundingPolygon {

    private List<List<List<Double>>> coordinates;
    private String type;

}
