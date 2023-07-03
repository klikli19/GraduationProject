package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constant.Role;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapperImpl;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.security.MyUserDetails;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    @Mock
    private Authentication authentication;
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;

    @Spy
    private AdsMapperImpl adsMapper;

    @Mock
    private AdRepository adRepository;

    @Mock
    private MyUserDetails userDetails;

    private Ad ad1;
    private User user1;
    private Image image1;
    private CreateAdsDTO createAdsDTO;

    @BeforeEach
    public void setUp(){
        image1 = new Image(1L,"image/jpeg",new byte[0]);

        user1 = new User(1L,"User1","Last name user1","+71111111111",
                Role.USER,"password","user@mail.ru",
                image1);

        ad1 = new Ad();
        ad1.setId(1L);
        ad1.setTitle("new ad 1");
        ad1.setDescription("description for ad 1");
        ad1.setPrice(100);
        ad1.setAuthor(user1);
        ad1.setImage(new Image(2L,"image/jpeg",new byte[0]));

        createAdsDTO = new CreateAdsDTO();
        createAdsDTO.setTitle("Title");
        createAdsDTO.setDescription("Description");
        createAdsDTO.setPrice(100);

        Collection<Ad> collection = new ArrayList<>();
        collection.add(ad1);
    }

    @Test
    public void shouldGetAllAdsWithTitle(){

    }

    @Test
    public void shouldThrowExceptionsWhenNegativePrice(){
        createAdsDTO.setPrice(-100);
        MultipartFile image = new MockMultipartFile("image", new byte[0]);
        assertThrows(IllegalArgumentException.class, () -> adService.createAd(createAdsDTO,image,authentication));
    }

    /*@Test
    public void shouldCreateAd() throws IOException {
        User user = new User(1L,"Ivan","Ivanov","+79999999999",
                Role.USER,"password","user@mail.ru",image1);
        MultipartFile image = new MockMultipartFile("image", new byte[0]);

        Ad ad = new Ad();

        when(userService.getAuthorizedUser(authentication)).thenReturn(user);
        when(imageService.downloadImage(image)).thenReturn(image1);
        when(adRepository.save(ad)).thenReturn(ad);

        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setPk(1);
        adsDTO.setAuthor(Math.toIntExact(user.getId()));
        adsDTO.setTitle("Title");
        adsDTO.setPrice(100);
        adsDTO.setImage("/ads/1/image");

        assertEquals(adsDTO,adService.createAd(createAdsDTO,image,authentication));
    }*/

    @Test
    public void getFullAd(){
        when(adRepository.findById(1L)).thenReturn(Optional.of(ad1));

        FullAdsDto expected = new FullAdsDto();
        expected.setPk(1);
        expected.setAuthorFirstName("User1");
        expected.setAuthorLastName("Last name user1");
        expected.setDescription("description for ad 1");
        expected.setEmail("user@mail.ru");
        expected.setImage("/ads/1/image");
        expected.setPhone("+71111111111");
        expected.setPrice(100);
        expected.setTitle("new ad 1");

        assertEquals(expected, adService.getFullAd(1L));
    }

    @Test
    public void updateAdThrowExceptionWhenNegativePrice(){
        createAdsDTO.setPrice(-100);
        assertThrows(IllegalArgumentException.class, () -> adService.updateAd(createAdsDTO,1L));
    }

    @Test
    public void updateAd(){
        when(adRepository.findById(1L)).thenReturn(Optional.of(ad1));
        when(adRepository.save(ad1)).thenReturn(ad1);

        AdsDTO expected = new AdsDTO();
        expected.setPk(1);
        expected.setAuthor(1);
        expected.setTitle("Title");
        expected.setPrice(100);
        expected.setImage("/ads/1/image");

        assertEquals(expected, adService.updateAd(createAdsDTO,1L));
    }

    @Test
    public void getAllUserAds(){

    }

    @Test
    public void updateImage() throws IOException{
        when(adRepository.findById(1L)).thenReturn(Optional.of(ad1));
        MultipartFile image = new MockMultipartFile("image", new byte[0]);
        when(imageService.downloadImage(image)).thenReturn(image1);

        String expected = "/ads/1/image";
        assertEquals(expected,adService.updateImage(1L,image));
    }
}
