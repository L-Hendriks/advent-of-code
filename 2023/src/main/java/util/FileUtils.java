package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FileUtils {

    public static List<String> FileToList(String fileName){
        try {
            return Files.readAllLines(Path.of(Objects.requireNonNull(FileUtils.class.getResource("../" + fileName)).toURI()));
        }catch (URISyntaxException | IOException use){
            throw new RuntimeException(use);
        }
    }
}
