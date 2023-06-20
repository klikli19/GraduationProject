package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.entity.Comment;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

/**
 * Interface CommentMapper
 * The interface is used to work with the database
 * @author Bogomolov Ilya
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", expression = "java(image(comment))")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    CommentDTO toCommentDTO(Comment comment);

    default Long createdAt(LocalDateTime value) {
        if (value == null) {
            return 0L;
        }
        return value.toInstant(ZoneOffset.ofHours(3)).toEpochMilli();
    }

    default String image(Comment comment) {
        int id = comment.getAuthor().getId().intValue();
        return "/users/" + id + "/image";
    }

    Comment toComment(CreateCommentDTO createComment);

    List<CommentDTO> toCommentsListDto(Collection<Comment> commentCollection);

}

