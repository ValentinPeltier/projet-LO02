package fr.utt.lo02.tdvp.core.layout;

import fr.utt.lo02.tdvp.core.Card;

public class LayoutRectangle extends Layout {
    public LayoutRectangle() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                locations.put(new Location(i, j), null);
            }
        }
    }

    public void countPointsAccept(LayoutVisitor visitor, Card victoryCard) {
        // TODO
    }
}
