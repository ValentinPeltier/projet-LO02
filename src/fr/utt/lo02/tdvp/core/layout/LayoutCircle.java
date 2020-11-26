package fr.utt.lo02.tdvp.core.layout;

import fr.utt.lo02.tdvp.core.Card;

public class LayoutCircle extends Layout {
	
	
    public LayoutCircle() {
    	
    	this.x = 5;
    	this.y = 5;
    	
        // First row
        locations.put(new Location(0, 2), null);

        // Last row
        locations.put(new Location(4, 2), null);

        // 2nd and 4th rows
        for (int i = 1; i < 4; i++) {
            locations.put(new Location(1, i), null);
            locations.put(new Location(3, i), null);
        }

        // 3rd row
        for(int i = 0; i < 5; i++) {
            locations.put(new Location(2, i), null);
        }
    }

    
    public void reset()
    {
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
