package com.amisec.service.item.service;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.*;

public class ItemNormalizationServiceTest {

    ItemNormalizationService service = new ItemNormalizationService();

    @Test
    public void testNormalizeItemSpecifics()
    {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("itemPropColor","red");
        modelMap.addAttribute("itemPropDesc","Item is a Perfume bottle");

        ModelMap itemSpecifics = service.normalizeItemSpecifics(modelMap);

        assertEquals("Red", itemSpecifics.getAttribute("itemPropColor"));
        assertEquals("Item is a perfume bottle", itemSpecifics.getAttribute("itemPropDesc"));
    }
}