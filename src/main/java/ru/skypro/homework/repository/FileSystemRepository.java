package ru.skypro.homework.repository;

import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Objects;


@Repository
public
class FileSystemRepository {

    String RESOURCES_DIR = Objects.requireNonNull(FileSystemRepository.class.getResource("/")).getPath();

    public String save(byte[] data, long date) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + new Date(date).getTime());
        Files.createDirectories(newFile.getParent());

        Files.write(newFile, data);

        return newFile.toAbsolutePath().toString();
    }
}
