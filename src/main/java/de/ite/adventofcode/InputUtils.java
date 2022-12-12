package de.ite.adventofcode;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.util.List;

public class InputUtils {

    private InputUtils() {}

    @SneakyThrows
    public static List<String> readInput(String fileName) {
        return Files.readAllLines(ResourceUtils.getFile(String.format("classpath:%s", fileName)).toPath());
    }

    @SneakyThrows
    public static String readInputAsString(String fileName) {
        return Files.readString(ResourceUtils.getFile(String.format("classpath:%s", fileName)).toPath());
    }

}
