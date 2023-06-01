package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "username")
    @Mapping(target = "role", defaultValue = "USER")
    User toEntity(RegisterReq dto);

    @Mapping(target = "image", source = "image", qualifiedByName = "imageMapping")
    UserDTO toDTO(User entity);

    @Named("imageMapping")
    default String image(Image image) {
        if (image == null) {
            return "";
        }
        return "users/me/image" + image.getId();
    }

    User toEntity(UserDTO dto);
}
