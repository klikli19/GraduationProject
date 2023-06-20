package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.LoginReq;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

/**
 * Interface UserMapper
 * The interface is used to work with the database
 * @author Kilikova Anna
 * @author Marina Gubina
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(target = "image", source = "image")
    User toEntity(UserDTO dto);

    //@Mapping(target = "image", source = "image", qualifiedByName = "imageMapping")
    @Mapping(target = "image",expression = "java(imageMapper(entity))")
    UserDTO toDTO(User entity);

    default String imageMapper(User user){
        return "/users/"+ user.getId() + "/image";
    }

    Image map(String value);

    /*
    @Named("imageMapping")
    default String image(Image image) {
        if (image == null) {
            return "";
        }
        return "/users/me/image";
                //+ image.getId();
    }*/

    User toEntity(LoginReq dto);

    @Mapping(target = "email", source = "username")
    User toEntity(RegisterReq req);
}
