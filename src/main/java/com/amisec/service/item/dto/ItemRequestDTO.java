package com.amisec.service.item.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ui.ModelMap;

import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDTO {


    Integer siteId;
    Integer categoryId;
    @Max(value = 85)
    String title;
    String condition;
    BigDecimal price;
    Integer quantity;
    List<String> imageUrl;
    ModelMap itemSpecifics;
    String Description;
}
