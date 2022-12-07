package de.ite.adventofcode;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.util.List;

public class InputUtils {

    @SneakyThrows
    public static List<String> readInput(String fileName) {
        return Files.readAllLines(ResourceUtils.getFile(String.format("classpath:%s", fileName)).toPath());
    }

}
