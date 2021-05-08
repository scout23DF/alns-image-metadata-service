package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Identification {

    private String profile;
    private String externalId;
    private String collection;
    private String type;
    private Dataset dataset;

}
