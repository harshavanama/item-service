package com.amisec.service.item.validator;

import com.amisec.service.item.dto.ItemRequestDTO;
import com.amisec.service.item.service.ItemNormalizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness =  Strictness.LENIENT)
public class ItemValidatorTest {

    @InjectMocks
    private ItemValidator itemValidator;

    @Mock
    private ItemNormalizationService itemNormalizationService;

    @Test
    public void testValidateSiteId_whenNull()
    {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setSiteId(null);
        WebDataBinder webDataBinder = new WebDataBinder(itemRequestDTO);

        itemValidator.validate(itemRequestDTO,webDataBinder.getBindingResult());

        assertTrue(webDataBinder.getBindingResult().hasErrors());
        assertEquals("ERR::101", webDataBinder.getBindingResult().getAllErrors().get(0).getCode());

    }

    @Test
    public void testValidateSiteId_whenSiteGreater()
    {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setSiteId(999);
        itemRequestDTO.setTitle(null);
        WebDataBinder webDataBinder = new WebDataBinder(itemRequestDTO);

        itemValidator.validate(itemRequestDTO,webDataBinder.getBindingResult());

        assertTrue(webDataBinder.getBindingResult().hasErrors());
        assertEquals(1, webDataBinder.getBindingResult().getAllErrors().size());

        assertEquals("ERR::101", webDataBinder.getBindingResult().getAllErrors().get(0).getCode());

    }
    @Test
    public void testValidateTitle_whenNull()
    {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setSiteId(100);
        itemRequestDTO.setTitle(null);
        WebDataBinder webDataBinder = new WebDataBinder(itemRequestDTO);

        itemValidator.validate(itemRequestDTO,webDataBinder.getBindingResult());

        assertTrue(webDataBinder.getBindingResult().hasErrors());
        assertEquals("ERR::102", webDataBinder.getBindingResult().getAllErrors().get(0).getCode());

    }

    @Test
    public void testValidateTitle_whenLengthGretareThan85()
    {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setSiteId(100);
        itemRequestDTO.setTitle("owqdisadjoasjdosadjosadjaosdjdsaojdojdsojsadoajsdoajsdosajdosdjosajoasdjsoadjosdaj" +
                "asiohasdjhoasdhsadohsadohsadohsadohsadohsdohsdosadhosadhosdhosdahosadhosadhasdohsadohsadohdohsdoahadohsdohsaaosdhossadoaosddsohhdsao" +
                "iashisauhidhsadiuhadihadihsdiasdhisdhasdihihdsaisadhiadhiasdhoadhodhsodshodshdsaohsadohdosdhosahodhdsohsaohaohdohsosadhsaohsadoh");
        WebDataBinder webDataBinder = new WebDataBinder(itemRequestDTO);

        itemValidator.validate(itemRequestDTO,webDataBinder.getBindingResult());

        assertTrue(webDataBinder.getBindingResult().hasErrors());
        assertEquals("ERR::102", webDataBinder.getBindingResult().getAllErrors().get(0).getCode());

    }


    @Test
    public void testValidateItemSpecifics_whenNull()
    {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setSiteId(100);
        itemRequestDTO.setTitle("Titile");
        WebDataBinder webDataBinder = new WebDataBinder(itemRequestDTO);
        doReturn(itemRequestDTO.getItemSpecifics()).when(itemNormalizationService).normalizeItemSpecifics(any(ModelMap.class));
        itemValidator.validate(itemRequestDTO,webDataBinder.getBindingResult());

        assertTrue(webDataBinder.getBindingResult().hasErrors());
        assertEquals("ERR::103", webDataBinder.getBindingResult().getAllErrors().get(0).getCode());

    }


    @Test
    public void testValidateItemSpecifics_whenEmptyModelMap()
    {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setSiteId(100);
        itemRequestDTO.setTitle("Titile");
        itemRequestDTO.setItemSpecifics(new ModelMap());
        WebDataBinder webDataBinder = new WebDataBinder(itemRequestDTO);
        doReturn(itemRequestDTO.getItemSpecifics()).when(itemNormalizationService).normalizeItemSpecifics(any(ModelMap.class));
        itemValidator.validate(itemRequestDTO,webDataBinder.getBindingResult());

        assertTrue(webDataBinder.getBindingResult().hasErrors());
        assertEquals("ERR::103", webDataBinder.getBindingResult().getAllErrors().get(0).getCode());

    }

}