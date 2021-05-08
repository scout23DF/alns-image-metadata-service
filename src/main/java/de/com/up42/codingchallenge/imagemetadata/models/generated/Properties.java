package de.com.up42.codingchallenge.imagemetadata.models.generated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Properties {

    private String id;
    private Centroid centroid;
    private Visibility visibility;
    private Illumination illumination;
    private Production production;
    private Archive archive;
    private SpatialCoverage spatialCoverage;
    private String uid;
    private Enrichment enrichment;
    private Identification identification;
    private Transmission transmission;
    private ContentDescription contentDescription;
    private Acquisition acquisition;
    private Orbit orbit;
    private State state;
    private Attitude attitude;
    private String quicklook;
    private Quality quality;
    private Target target;
    private Provider provider;

    private Date timestamp;
    private Date timeStamp;

}
