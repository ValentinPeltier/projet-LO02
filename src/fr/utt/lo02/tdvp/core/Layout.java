package fr.utt.lo02.tdvp.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Layout {
    private List<Location> locations = new ArrayList<Location> ();

    public boolean placeCard(int x, int y, Card card, int offsetX, int offsetY) {
    }

    public boolean moveCard(int x1, int y1, int x2, int y2) {
    }

    private Layout() {
    }

    public static Layout getInstance() {
    }

}
