package fr.utt.lo02.tdvp.model.layout;

public class LayoutRectangle extends Layout {
    public LayoutRectangle() {
    	this.x = 5;
    	this.y = 3;
        this.reset();
    }

    public void reset() {
    	for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 3; j++) {
                locations.put(new Location(i, j), null);
            }
        }
    }
    
    //FONCTIONNEL
    public boolean moveHorizontally(int offset){
    	
    	//DROITE
    	if(offset == 1)
    	{
    		//IF LAST COLUMN IS EMPTY
    		if(locations.get(new Location(4, 0)) == null && locations.get(new Location(4, 1)) == null && locations.get(new Location(4, 2)) == null)
			{
    			//DECALE
    			for(int i = 4; i > -1; i--)
    			{
    				for(int j = 0; j < 3; j++)
    				{
    					//x column = x-1 column 
    					locations.replace(new Location(i+1,j),locations.get(new Location(i, j)));
    				}
    			}
    			
    			//REMOVE FIRST COLUMN
    			for(int j = 0; j < 3; j++)
				{
					locations.replace(new Location(0,j),null);
				}
    			
    			return true;
			}
    		else return false;
    	}
    	
    	//GAUCHE
    	else if(offset == -1)
    	{
    		if(locations.get(new Location(0, 0)) == null && locations.get(new Location(0, 1)) == null && locations.get(new Location(0, 2)) == null)
			{
    			//DECALE
    			for(int i = 0; i < 5; i++)
    			{
    				for(int j = 0; j < 3; j++)
    				{
    					//x column = x+1 column 
    					locations.replace(new Location(i,j),locations.get(new Location(i+1, j)));
    				}
    			}
    			
    			//REMOVE LAST COLUMN
    			for(int j = 0; j < 3; j++)
				{
					locations.replace(new Location(5,j),null);
				}
    			return true;
			}
    		else return false;
    	}
    	
    	else return false;
    }
    
    
    //EN DEBUG
    public boolean moveVertically(int offset){
    	
    	//BAS
    	if(offset == 1)
    	{
    		boolean isRowEmpty = true;
    		//IF LAST ROW IS EMPTY
    		for(int i = 0; i < 5; i++) {
    			if(locations.get(new Location(i, 2)) != null)
    				isRowEmpty = false;
    		}
    		
    		if(isRowEmpty)
			{
    			//DECALE
    			for(int j = 1; j > -1; j--)
    			{
    				for(int i = 0; i < 5; i++)
    				{
    					//x row = x-1 row 
    					locations.replace(new Location(i,j+1),locations.get(new Location(i, j)));
    				}
    			}
    			
    			//REMOVE FIRST ROW
    			for(int i = 0; i < 5; i++)
				{
					locations.replace(new Location(i,0),null);
				}
    			
    			return true;
			}
    		else return false;
    	}
    	
    	//HAUT
    	else if(offset == -1)
    	{
    		boolean isRowEmpty = true;
    		
    		//IF FIRST ROW IS EMPTY
    		for(int i = 0; i < 5; i++) {
    			if(locations.get(new Location(i, 0)) != null)
    				isRowEmpty = false;
    		}
    		
    		if(isRowEmpty)
			{
    			//DECALE
    			for(int j = 0; j < 2; j++)
    			{
    				for(int i = 0; i < 5; i++)
    				{
    					//x row = x+1 row 
    					locations.replace(new Location(i,j),locations.get(new Location(i, j+1)));
    				}
    			}
    			
    			//REMOVE LAST ROW
    			for(int i = 0; i < 5; i++)
				{
					locations.replace(new Location(i,2),null);
				}
    			
    			return true;
			}
    		else return false;
    	}
    	else return false;
    }
}
