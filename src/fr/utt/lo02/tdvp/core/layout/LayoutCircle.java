package fr.utt.lo02.tdvp.core.layout;

public class LayoutCircle extends Layout {
    public LayoutCircle() {
    	this.x = 5;
    	this.y = 5;
        this.reset();
    }

    public void reset() {
        // First row
        locations.replace(new Location(0, 2), null);

        // Last row
        locations.replace(new Location(4, 2), null);

        // 2nd and 4th rows
        for (int i = 1; i < 4; i++) {
            locations.replace(new Location(1, i), null);
            locations.replace(new Location(3, i), null);
        }

        // 3rd row
        for(int i = 0; i < 5; i++) {
            locations.replace(new Location(2, i), null);
        }
    }
}
