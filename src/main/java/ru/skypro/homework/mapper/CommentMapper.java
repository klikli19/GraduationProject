package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "text", target = "text")
    CommentDTO commentToCommentDTO(Comment comment);

    default Long createdAt(LocalDateTime value) {
        if (value == null) {
            return 0L;
        }
        return value.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    default String image(Image image) {
        if (image == null) {
            return "";
        }
        return "users/me/" + image.getId();
    }


    @Mapping(source = "text", target = "text")
    Comment createCommentDtoToComment(CreateCommentDTO createComment);

    List<CommentDTO> commentsToCommentsListDto(Collection<Comment> commentCollection);

}
