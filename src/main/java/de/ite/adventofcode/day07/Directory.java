package de.ite.adventofcode.day07;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Directory implements FileSystemObject {
    public static final String ROOT = "/";
    private final String name;
    private final Directory parent;
    private final Map<String, Directory> subDirectories = new HashMap<>();
    private final Map<String, File> files = new HashMap<>();

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public Directory(String name) {
        this.name = name;
        this.parent = null;
    }

    public void addSubDirectory(Directory subDirectory) {
        subDirectories.put(subDirectory.getName(), subDirectory);
    }

    public void addFile(File file) {
        files.put(file.getName(), file);
    }

    public List<Directory> getSubDirectories() {
        return subDirectories.values().stream().sorted(Comparator.comparing(Directory::getName)).toList();
    }

    public Directory findSubDirectory(String name) {
        return subDirectories.computeIfAbsent(name, n -> new Directory(n, this));
    }

    public Directory getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int fileSizeSum = files.values().stream().mapToInt(File::getSize).sum();
        int subDirectorySizeSUm = subDirectories.values().stream().mapToInt(Directory::getSize).sum();
        return fileSizeSum + subDirectorySizeSUm;
    }
}
