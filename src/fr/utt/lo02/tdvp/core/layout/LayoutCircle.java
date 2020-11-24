package fr.utt.lo02.tdvp.core.layout;

import fr.utt.lo02.tdvp.core.Card;

public class LayoutCircle extends Layout {
	
    public LayoutCircle() {   	
    	
    	//Les positions -> CHANGER EN FOR
    	//1st row
    	locations.put(new Location(0,2),null);
    	//last row
    	locations.put(new Location(4,2),null);
    	
    	//2nd row and 4th row
    	
    	for(int i = 1;  i <= 3; i+=2)
    	{
    		for(int j = 1 ; j <=3; j++) 
    		{
    			locations.put(new Location(i,i),null);
    		}
    	}
    	
    	//3rd row    	
    	for(int i = 0; i < 5;i++)
    	{
    		locations.put(new Location(2,i),null);
    	}
    	
    }

    public void countPointsAccept(LayoutVisitor visitor, Card victoryCard) {
    }

}
