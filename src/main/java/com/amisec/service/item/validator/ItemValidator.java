package com.amisec.service.item.validator;

import com.amisec.service.item.dto.ItemRequestDTO;
import com.amisec.service.item.service.ItemNormalizationService;
import com.amisec.service.item.util.ItemError;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ItemValidator implements Validator {

    private final ItemNormalizationService itemNormalizationService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ItemRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ItemRequestDTO itemRequestDTO = (ItemRequestDTO) target;

       if(itemRequestDTO.getSiteId() == null || itemRequestDTO.getSiteId() < 0 || itemRequestDTO.getSiteId() > 100)
       {
           errors.reject(ItemError.INVALID_SITE_ID.getErrorCode(), ItemError.INVALID_SITE_ID.getErrorDescription());
           return ;
       }


        if(StringUtils.isEmpty(itemRequestDTO.getTitle()) || itemRequestDTO.getTitle().length() > 85 ) {
            errors.reject(ItemError.INVALID_TITLE_LENGTH.getErrorCode(), ItemError.INVALID_TITLE_LENGTH.getErrorDescription());
            return;
        }

        //updating the item specifics , using normalization
        itemRequestDTO.setItemSpecifics(itemNormalizationService.normalizeItemSpecifics
                (itemRequestDTO.getItemSpecifics()));

        if(itemRequestDTO.getItemSpecifics() == null
                || CollectionUtils.isEmpty(itemRequestDTO.getItemSpecifics().entrySet())
                || itemRequestDTO.getItemSpecifics().entrySet().size() < 2
                || itemRequestDTO.getItemSpecifics().entrySet().size() > 10) {
            errors.reject(ItemError.INVALID_ITEM_SPECIFIC_SIZE.getErrorCode(), ItemError.INVALID_ITEM_SPECIFIC_SIZE.getErrorDescription());
            return;
        }
    }
}
