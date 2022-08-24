package com.amisec.service.item.controller;

import com.amisec.service.item.dto.ErrorDto;
import com.amisec.service.item.dto.ItemRequestDTO;
import com.amisec.service.item.dto.ResponseDTO;
import com.amisec.service.item.validator.ItemValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private static  final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private final ItemValidator itemValidator;

    @PostMapping
    public ResponseEntity<ResponseDTO> addItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO, BindingResult bindingResult)
    {
        LOGGER.debug("Add Item itemRequestDTO {}", itemRequestDTO);
        itemValidator.validate(itemRequestDTO, bindingResult);

        if(bindingResult.hasErrors())
        {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                ErrorDto errorDto = new ErrorDto(fieldError.getCode(), fieldError.getDefaultMessage());
                errorDtoList.add(errorDto);
            });

            return ResponseEntity.badRequest().body(new ResponseDTO(false, errorDtoList));
        }
        return ResponseEntity.ok().body(new ResponseDTO(true, null));
    }
}

