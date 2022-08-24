package com.amisec.service.item.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemError {

    INVALID_SITE_ID("ERR::101","SiteId should be in between 0 and 100"),
    INVALID_TITLE_LENGTH("ERR::102", "Title length is under 85 characters"),
    INVALID_ITEM_SPECIFIC_SIZE("ERR::103", "Number of Item Specifics between 2 - 10");


    private final String errorCode;
    private final String errorDescription;

}
