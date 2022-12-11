package de.ite.adventofcode.day09;

import java.util.LinkedList;
import java.util.List;

public class Rope {

    private final List<Knot> knotList = new LinkedList<>();

    public Rope(int knots) {
        Knot tail = new Knot(null);
        knotList.add(tail);

        Knot successor = tail;
        for(int i = 1; i < knots; i++) {
            Knot knot = new Knot(successor);
            knotList.add(0, knot);
            successor = knot;
        }
    }

    public void move(Motion motion) {
        Knot head = knotList.get(0);
        head.move(List.of(motion));

        Knot currentKnot = head;
        while(currentKnot.hasSuccessor()) {
            Knot successor = currentKnot.getSuccessor();
            if(!areKnotsTouching(currentKnot, successor)) {
                List<Motion> motionsToMove = getMotionsNeededForSuccessor(currentKnot, successor);
                successor.move(motionsToMove);
            }
            currentKnot = successor;
        }
    }

    public Knot getTail() {
        return knotList.get(knotList.size() - 1);
    }

    private List<Motion> getMotionsNeededForSuccessor(Knot currentKnot, Knot successor) {
        if(knotsHaveSameColumn(currentKnot, successor)) {
            return List.of(currentKnot.getY() > successor.getY() ? Motion.UP : Motion.DOWN);
        }

        if(knotsHaveSameRow(currentKnot, successor)) {
            return List.of(currentKnot.getX() > successor.getX() ? Motion.RIGHT : Motion.LEFT);
        }

        Motion verticalMotion = currentKnot.getY() > successor.getY() ? Motion.UP : Motion.DOWN;
        Motion horizontalMotion = currentKnot.getX() < successor.getX() ? Motion.LEFT : Motion.RIGHT;
        return List.of(verticalMotion, horizontalMotion);
    }

    private boolean knotsHaveSameColumn(Knot first, Knot second) {
        return first.getX() == second.getX();
    }

    private boolean knotsHaveSameRow(Knot first, Knot second) {
        return first.getY() == second.getY();
    }

    private boolean areKnotsTouching(Knot first, Knot second) {
        return (Math.abs(first.getY() - second.getY()) < 2) && (Math.abs(first.getX() - second.getX()) < 2);
    }

}
