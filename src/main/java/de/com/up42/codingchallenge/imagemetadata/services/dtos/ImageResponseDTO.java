package de.com.up42.codingchallenge.imagemetadata.services.dtos;

import de.com.up42.codingchallenge.imagemetadata.utils.ImageUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ImageResponseDTO implements IGenericBaseDTO {

    private final String featureId;
    private final String quickLookImageAsBase64;


    public byte[] getQuickLookImageAsByteArray() {
        return ImageUtils.convertBase64DataToByteArray(this.quickLookImageAsBase64);
    }

}
