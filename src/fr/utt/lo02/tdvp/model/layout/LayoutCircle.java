package fr.utt.lo02.tdvp.model.layout;

public class LayoutCircle extends Layout {
    public LayoutCircle() {
    	this.x = 5;
    	this.y = 5;
        this.reset();
    }

    public void reset() {
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
    
    public boolean moveHorizontally(int offset){
    	
    	if(offset == 1)
    	{
    		boolean isRowEmpty = true;
    		
    		if(locations.get(new Location(2, 0)) != null
    			|| locations.get(new Location(3, 1)) != null
				|| locations.get(new Location(4, 2)) != null
				|| locations.get(new Location(3, 3)) != null
				|| locations.get(new Location(2, 4)) != null){isRowEmpty=false;}
    		
    		if(isRowEmpty)
    		{
    			//DECALE
    			for(int i = 4; i > -1; i--)
    			{
    				for(int j = 0; j < 5; j++)
    				{
    					//x column = x-1 column
    					if(locations.containsKey(new Location(i, j))) 
    						locations.replace(new Location(i+1,j),locations.get(new Location(i, j)));
    				}
    			}
    			
    			//REMOVE FISRT "COLUMN"
    			locations.replace(new Location(0,2),null);
    			locations.replace(new Location(1,1),null);
    			locations.replace(new Location(1,3),null);
    			
    			return true;
    			
    		}
    		else return false;
    	}
    	else return false;
    }
    
    public boolean moveVertically(int offset){
    	return false;
    }
}
