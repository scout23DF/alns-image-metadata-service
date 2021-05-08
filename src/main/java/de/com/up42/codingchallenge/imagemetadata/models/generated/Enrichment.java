package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Enrichment {

    private Naturallanguage naturallanguage;
    private List<Geoname> geonames;

}
