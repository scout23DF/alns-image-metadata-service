package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Feature {

    private String type;
    private List<Double> bbox;
    private Geometry geometry;
    private Properties properties;

}
