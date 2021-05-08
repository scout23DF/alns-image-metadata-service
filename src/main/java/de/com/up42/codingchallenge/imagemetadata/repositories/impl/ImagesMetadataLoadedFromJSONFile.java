package de.com.up42.codingchallenge.imagemetadata.repositories.impl;

import de.com.up42.codingchallenge.imagemetadata.models.generated.Feature;
import de.com.up42.codingchallenge.imagemetadata.models.generated.ImageMetadata;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Hides the constructor to force useage of the Builder.
public class ImagesMetadataLoadedFromJSONFile {

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static List<Feature> allFeaturesFlattenedList;

    private List<ImageMetadata> imagesMetadataLoadedList;

    public List<Feature> getAllFeaturesFlattenedList() {

        if (allFeaturesFlattenedList == null || CollectionUtils.isEmpty(allFeaturesFlattenedList)) {

            allFeaturesFlattenedList = new ArrayList<>();

            if (!CollectionUtils.isEmpty(imagesMetadataLoadedList)) {

                imagesMetadataLoadedList.forEach(oneImgMtd -> {
                    allFeaturesFlattenedList.addAll(oneImgMtd.getFeatures());
                });

            }

        }

        return allFeaturesFlattenedList;
    }

}
