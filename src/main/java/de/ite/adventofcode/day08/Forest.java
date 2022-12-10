package de.ite.adventofcode.day08;

import java.util.ArrayList;
import java.util.List;

public class Forest {

    final int width;
    final int height;
    private final Tree[][] trees;
    private record TreeVisibility(int viewingDistance, boolean isVisibleFromOutside) {}

    public Forest(List<String> inputLines) {
        width = inputLines.get(0).length();
        height = inputLines.size();
        trees = new Tree[width][height];
        initForest(inputLines);
    }

    private void initForest(List<String> inputLines) {
        for(int y = 0; y < height; y++) {
            String line = inputLines.get(y);
            for(int x = 0; x < width; x++) {
                Position position = new Position(x, y);
                int treeHeight = Integer.parseInt(line.substring(x, x + 1));
                trees[x][y] = new Tree(position, treeHeight);
            }
        }
    }

    public List<Tree> getVisibleTrees() {
        List<Tree> visibleTrees = new ArrayList<>();
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Tree tree = trees[x][y];
                if(isTreeVisible(tree)) {
                    visibleTrees.add(tree);
                }
            }
        }
        return visibleTrees;
    }

    public int getHighestScenicScore() {
        int highestScore = 0;
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                highestScore = Math.max(highestScore, getScenicScore(trees[x][y]));
            }
        }
        return highestScore;
    }

    private int getScenicScore(Tree tree) {
        return lookNorth(tree).viewingDistance * lookEast(tree).viewingDistance * lookSouth(tree).viewingDistance * lookWest(tree).viewingDistance;
    }

    private boolean isTreeVisible(Tree tree) {
        return lookNorth(tree).isVisibleFromOutside() || lookEast(tree).isVisibleFromOutside() || lookSouth(tree).isVisibleFromOutside() || lookWest(tree).isVisibleFromOutside();
    }


    private TreeVisibility lookSouth(Tree tree) {
        int visibleTrees = 0;
        for(int j = tree.position().y() + 1; j < height; j++) {
            visibleTrees++;
            Tree treeInLine = trees[tree.position().x()][j];
            boolean isLarger = treeInLine.height() >= tree.height();
            if(isLarger) {
                return new TreeVisibility(visibleTrees, false);
            }
        }
        return new TreeVisibility(visibleTrees, true);
    }

    private TreeVisibility lookNorth(Tree tree) {
        int visibleTrees = 0;
        for(int j = tree.position().y() - 1; j >= 0; j--) {
            visibleTrees++;
            Tree treeInLine = trees[tree.position().x()][j];
            boolean isLarger = treeInLine.height() >= tree.height();
            if(isLarger) {
                return new TreeVisibility(visibleTrees, false);
            }
        }
        return new TreeVisibility(visibleTrees, true);
    }

    private TreeVisibility lookWest(Tree tree) {
        int visibleTrees = 0;
        for(int i = tree.position().x() - 1; i >= 0; i--) {
            visibleTrees++;
            Tree treeInLine = trees[i][tree.position().y()];
            boolean isLarger = treeInLine.height() >= tree.height();
            if(isLarger) {
                return new TreeVisibility(visibleTrees, false);
            }
        }
        return new TreeVisibility(visibleTrees, true);
    }

    private TreeVisibility lookEast(Tree tree) {
        int visibleTrees = 0;
        for(int i = tree.position().x() + 1; i < width; i++) {
            visibleTrees++;
            Tree treeInLine = trees[i][tree.position().y()];
            boolean isLarger = treeInLine.height() >= tree.height();
            if(isLarger) {
                return new TreeVisibility(visibleTrees, false);
            }
        }
        return new TreeVisibility(visibleTrees, true);
    }

}
