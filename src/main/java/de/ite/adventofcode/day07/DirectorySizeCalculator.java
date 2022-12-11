package de.ite.adventofcode.day07;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class DirectorySizeCalculator extends Calculator<Integer, Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day07_filesystem.txt");
        DirectorySizeCalculator directorySizeCalculator = new DirectorySizeCalculator(inputLines);
        log.info("Find all of the directories with a total size of at most 100000. What is the sum of the total sizes of those directories?");
        log.info("Task 01: {}", directorySizeCalculator.solveFirstTask());
        log.info("Find the smallest directory that, if deleted, would free up enough space on the filesystem to run the update. What is the total size of that directory?");
        log.info("Task 02: {}", directorySizeCalculator.solveSecondTask());
    }

    private static final String CD = "$ cd ";
    private static final String LS = "$ ls ";
    private static final String DIR = "dir ";
    private static final String PARENT_DIR = "..";

    public DirectorySizeCalculator(List<String> inputLines) {
        super(inputLines);
    }


    @Override
    public Integer solveFirstTask() {
        final int MAX_DIR_SIZE = 100_000;

        Directory rootDirectory = initFileTree();
        List<Directory> directories = collectSubDirectoriesRecursive(rootDirectory);
        return directories.stream()
                .filter(directory -> directory.getSize() <= MAX_DIR_SIZE)
                .mapToInt(Directory::getSize)
                .sum();
    }

    @Override
    public Integer solveSecondTask() {
        final int TOTAL_DISK_SPACE = 70_000_000;
        final int NEEDED_UNUSED_SPACE = 30_000_000;
        Directory rootDirectory = initFileTree();

        int usedSpace = rootDirectory.getSize();
        int unusedSpace = TOTAL_DISK_SPACE - usedSpace;
        int sizeToBeDeleted = NEEDED_UNUSED_SPACE - unusedSpace;

        List<Directory> directories = collectSubDirectoriesRecursive(rootDirectory);
        return directories.stream()
                .filter(directory -> directory.getSize() >= sizeToBeDeleted)
                .min(Comparator.comparing(Directory::getSize))
                .map(Directory::getSize)
                .orElse(-1);
    }

    private List<Directory> collectSubDirectoriesRecursive(Directory directory) {
        List<Directory> list = new ArrayList<>();
        list.add(directory);
        for (Directory subDirectory : directory.getSubDirectories()) {
            list.addAll(collectSubDirectoriesRecursive(subDirectory));
        }
        return list;
    }

    public Directory initFileTree() {
        final Directory rootDirectory = new Directory(Directory.ROOT);
        Directory currentDirectory = rootDirectory;
        for(int i = 1; i < inputLines.size(); i++) {
            String line = inputLines.get(i);
            if(isOutput(line)) {
                continue;
            }

            if(isChangeDirectory(line)) {
                currentDirectory = changeDir(currentDirectory, line);
            } else if(isList(line)) {
                addSubDirectoriesAndFiles(currentDirectory, i);
            }

        }
        return rootDirectory;
    }

    private void addSubDirectoriesAndFiles(Directory currentDirectory, int i) {
        for(int l = i + 1; l < inputLines.size() && isOutput(inputLines.get(l)); l++) {
            String outputLine = inputLines.get(l);
            if(outputLine.startsWith(DIR)) {
                addSubDirectory(currentDirectory, outputLine);
            } else {
                addFile(currentDirectory, outputLine);
            }
        }
    }

    private void addSubDirectory(Directory currentDirectory, String outputLine) {
        String subDirectoryName = outputLine.replace(DIR, "");
        Directory subDirectory = new Directory(subDirectoryName, currentDirectory);
        currentDirectory.addSubDirectory(subDirectory);
    }

    private void addFile(Directory currentDirectory, String outputLine) {
        String[] split = outputLine.split(" ");
        int fileSize = Integer.parseInt(split[0]);
        String fileName = split[1];
        currentDirectory.addFile(new File(fileName, fileSize));
    }

    private Directory changeDir(Directory currentDirectory, String command) {
        String dirName = command.replace(CD, "");
        if(PARENT_DIR.equals(dirName)) {
            return currentDirectory.getParent();
        }
        return currentDirectory.findSubDirectory(dirName);
    }

    private boolean isList(String line) {
        return line.startsWith(LS.trim());
    }

    private boolean isChangeDirectory(String line) {
        return line.startsWith(CD);
    }

    private boolean isOutput(String line) {
        return !line.startsWith("$");
    }

}
