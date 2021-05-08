package de.com.up42.codingchallenge.imagemetadata.models.enums;

import java.util.Arrays;
import java.util.Optional;

public enum AppServiceOperationTypeEnum {

    SEARCH_ALL_FEATURES(10, "Search All Features with no criteria.")
   ,SEARCH_FEATURE_BY_ID(20, "Search one Feature by Id.")
   ,GET_IMAGE_AS_PNG(30, "Get one Image's Feature in PNG Format.")
   ;

    private Integer codStatus;
    private String descriptionStatus;

    AppServiceOperationTypeEnum(Integer pCodStatus, String pDescriptionStatus) {
        this.codStatus = pCodStatus;
        this.descriptionStatus = pDescriptionStatus;
    }

    public Integer getCodStatus() {
        return codStatus;
    }

    public String getDescriptionStatus() {
        return descriptionStatus;
    }

    public static Optional<AppServiceOperationTypeEnum> fromText(String pText) {
        return Arrays.stream(values())
                .filter(bl -> bl.descriptionStatus.equalsIgnoreCase(pText))
                .findFirst();
    }

    public static Optional<AppServiceOperationTypeEnum> fromValue(Integer pTargetCode) {
        return Arrays.stream(values())
                .filter(bl -> bl.codStatus.equals(pTargetCode))
                .findFirst();
    }

}
