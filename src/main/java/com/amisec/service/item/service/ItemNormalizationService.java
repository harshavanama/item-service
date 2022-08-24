package com.amisec.service.item.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@RequiredArgsConstructor
public class ItemNormalizationService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(ItemNormalizationService.class);


    @Cacheable("itemSpecifics")
    public ModelMap normalizeItemSpecifics(ModelMap itemSpecifics)
    {
        // capitalize logic;
        ModelMap updatedModel = new ModelMap();

        itemSpecifics.forEach((itemSpecKey, itemSpecValue)->
        {
            String itemSpecific = (String) itemSpecValue;
            String updatedItemSpec = new StringBuilder().append(itemSpecific.substring(0, 1).toUpperCase())
                    .append(itemSpecific.substring(1).toLowerCase()).toString();
            updatedModel.put(itemSpecKey, updatedItemSpec);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException thrown while normalization ", e);

            }
        });
        return updatedModel;
    }
}
