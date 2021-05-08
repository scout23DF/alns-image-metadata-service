package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Geoname {

    private String name;
    private List<State> states;

}
