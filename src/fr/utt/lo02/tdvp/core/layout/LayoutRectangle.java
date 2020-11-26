package fr.utt.lo02.tdvp.core.layout;

import fr.utt.lo02.tdvp.core.Card;

public class LayoutRectangle extends Layout {
    public LayoutRectangle() {
    	
    	this.x = 5;
    	this.y = 3;
    	
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 3; j++) {
                locations.put(new Location(i, j), null);
            }
        }
    }
    
    public void reset()
    {
    	for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 3; j++) {
                locations.replace(new Location(i, j), null);
            }
        }
    }

}
