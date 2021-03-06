package fr.utt.lo02.tdvp.model.layout;

import java.util.HashMap;
import java.util.Map;

import fr.utt.lo02.tdvp.model.Card;

/**
 * The LayoutVistor class, used for counting points
 **/

public class LayoutVisitor {

	/**
	 * Points set by parameters (shape, color and filled)
	 * There are stored in a map where the key is the parameter and the value is the points for this parameter
	 **/
	Map<String,Integer> pointsByParameter = new HashMap<String,Integer>();
	
	/**
	 * The current number of adjacent card of the same parameter, by parameter
	 **/
	Map<String,Integer> currentParamaterChain = new HashMap<String,Integer>();

	/**
	 * Class constructor
	 **/
	public LayoutVisitor() {

	}

	/**
	 * Make the points count, for any type of Layout
	 * @param the layout 
	 **/
	public void countPointsVisit(Layout layout)
	{

		int tmp;

		pointsByParameter.put("red",0);
		pointsByParameter.put("green",0);
		pointsByParameter.put("blue",0);
		pointsByParameter.put("square",0);
		pointsByParameter.put("triangle",0);
		pointsByParameter.put("circle",0);
		pointsByParameter.put("hollow",0);
		pointsByParameter.put("filled",0);

		currentParamaterChain.put("red",0);
		currentParamaterChain.put("green",0);
		currentParamaterChain.put("blue",0);
		currentParamaterChain.put("square",0);
		currentParamaterChain.put("triangle",0);
		currentParamaterChain.put("circle",0);
		currentParamaterChain.put("hollow",0);
		currentParamaterChain.put("filled",0);

		//FOR EACH LINE
		for(int y = 0; y < layout.getY(); y++)
		{
			//LOCATION BY LOCATION
			for(int x = 0; x < layout.getX(); x++)
			{
				Location currentLocation = new Location(x,y);
				//CHECK IF THE LOCATION EXISTS
				if(layout.getLocations().containsKey(currentLocation))
				{
					//CHECK IF A CARD IS ON THIS LOCATION
					if(layout.getLocations().get(currentLocation) != null)
					{
						//FOR EACH PARAMATER
						//FOR EACH COLOR
						for (Card.Color currentColor: Card.Color.values()) {
							String color = currentColor.toString().toLowerCase();
							if(layout.getLocations().get(currentLocation).getColor() == currentColor)
							{
								//JUST MAKE THE CHAIN LONGER
								tmp = currentParamaterChain.get(color)+1;
								currentParamaterChain.replace(color, tmp);
							}
							else
							{
								//BREAK THE CHAIN AND ADD THE POINTS OF THE CHAIN
								tmp = pointsByParameter.get(color) + getPointsFor("color",currentParamaterChain.get(color));

								pointsByParameter.replace(color, tmp);
								currentParamaterChain.replace(color, 0);
							}
						}

						//FOR EACH SHAPE
						for (Card.Shape currentShape: Card.Shape.values()) {
							String shape = currentShape.toString().toLowerCase();
							if(layout.getLocations().get(currentLocation).getShape() == currentShape)
							{
								//JUST MAKE THE CHAIN LONGER
								tmp = currentParamaterChain.get(shape)+1;
								currentParamaterChain.replace(shape, tmp);
							}
							else
							{
								//BREAK THE CHAIN AND ADD THE POINTS OF THE CHAIN
								tmp = pointsByParameter.get(shape) + getPointsFor("shape",currentParamaterChain.get(shape));
								pointsByParameter.replace(shape, tmp);
								currentParamaterChain.replace(shape, 0);
							}
						}

                        //FOR EACH FILLED/HOLLOW
                        for (Card.Filled currentFilled: Card.Filled.values()) {
							String filled = currentFilled.toString().toLowerCase();
							if(layout.getLocations().get(currentLocation).getFilled() == currentFilled)
							{
								//JUST MAKE THE CHAIN LONGER
								tmp = currentParamaterChain.get(filled)+1;
								currentParamaterChain.replace(filled, tmp);
							}
							else
							{
								//BREAK THE CHAIN AND ADD THE POINTS OF THE CHAIN
								tmp = pointsByParameter.get(filled) + getPointsFor("filled",currentParamaterChain.get(filled));
								pointsByParameter.replace(filled, tmp);
								currentParamaterChain.replace(filled, 0);
							}
                        }
					}
				}
			}

			//RESET CHAIN -> CHAINS MUST NOT BE SHARED BETWEEN LINES OR COLUMNS
			updatePoints();
			resetChains();
		}



		//FOR EACH COLUMN
		for(int x = 0; x < layout.getX(); x++)
		{
			//LOCATION BY LOCATION
			for(int y = 0; y < layout.getY(); y++)
			{
				Location currentLocation = new Location(x,y);
				//CHECK IF THE LOCATION EXISTS
				if(layout.getLocations().containsKey(currentLocation))
				{
					//CHECK IF A CARD IS ON THIS LOCATION
					if(layout.getLocations().get(currentLocation) != null)
					{
						//FOR EACH PARAMATER
						//FOR EACH COLOR
						for (Card.Color currentColor: Card.Color.values()) {
							String color = currentColor.toString().toLowerCase();
							if(layout.getLocations().get(currentLocation).getColor() == currentColor)
							{
								//JUST MAKE THE CHAIN LONGER
								tmp = currentParamaterChain.get(color)+1;
								currentParamaterChain.replace(color, tmp);
							}
							else
							{
								//BREAK THE CHAIN AND ADD THE POINTS OF THE CHAIN
								tmp = pointsByParameter.get(color) + getPointsFor("color",currentParamaterChain.get(color));
								pointsByParameter.replace(color, tmp);
								currentParamaterChain.replace(color, 0);
							}
						}

						//FOR EACH SHAPE
						for (Card.Shape currentShape: Card.Shape.values()) {
							String shape = currentShape.toString().toLowerCase();
							if(layout.getLocations().get(currentLocation).getShape() == currentShape)
							{
								//JUST MAKE THE CHAIN LONGER
								tmp = currentParamaterChain.get(shape)+1;
								currentParamaterChain.replace(shape, tmp);
							}
							else
							{
								//BREAK THE CHAIN AND ADD THE POINTS OF THE CHAIN
								tmp = pointsByParameter.get(shape) + getPointsFor("shape",currentParamaterChain.get(shape));
								pointsByParameter.replace(shape, tmp);
								currentParamaterChain.replace(shape, 0);
							}
						}

						//FOR EACH FILLED/HOLLOW
                        for (Card.Filled currentFilled: Card.Filled.values()) {
							String filled = currentFilled.toString().toLowerCase();
							if(layout.getLocations().get(currentLocation).getFilled() == currentFilled)
							{
								//JUST MAKE THE CHAIN LONGER
								tmp = currentParamaterChain.get(filled)+1;
								currentParamaterChain.replace(filled, tmp);
							}
							else
							{
								//BREAK THE CHAIN AND ADD THE POINTS OF THE CHAIN
								tmp = pointsByParameter.get(filled) + getPointsFor("filled",currentParamaterChain.get(filled));
								pointsByParameter.replace(filled, tmp);
								currentParamaterChain.replace(filled, 0);
							}
                        }
					}
				}
			}

			//RESET CHAIN -> CHAIN MUST NOT BE SHARED BETWEEN LINES OR COLUMNS
			updatePoints();
			resetChains();
		}


	}
	
	
	/**
	 * Return points for a given parameter for a series of cards
	 * @param the given parameter
	 * @param the number of adjacent cards for the given parameter
	 * @return the number of points
	 **/
	private int getPointsFor(String parameter, int chainSize)
	{
		switch(parameter)
		{
			case "color":
				if(chainSize > 2)
				{
					return chainSize + 1;
				}
				else
				{
					return 0;
				}
			case "shape":
				if(chainSize > 1)
				{
					return chainSize - 1;
				}
				else
				{
					return 0;
				}
			case "filled":
				if(chainSize > 2)
				{
					return chainSize;
				}
				else
				{
					return 0;
				}
			default:
				return 0;
		}
	}

	/**
	 * Ask and update of the points for each parameter
	 **/
	private void updatePoints() {
		int tmp;
		//COLOR
		for (Card.Color currentColor: Card.Color.values()) {
			String color = currentColor.toString().toLowerCase();
			tmp = pointsByParameter.get(color) + getPointsFor("color",currentParamaterChain.get(color));
			pointsByParameter.replace(color, tmp);
		}


		//SHAPE
		for (Card.Shape currentShape: Card.Shape.values()) {
			String shape = currentShape.toString().toLowerCase();
			tmp = pointsByParameter.get(shape) + getPointsFor("shape",currentParamaterChain.get(shape));
			pointsByParameter.replace(shape, tmp);
		}


        //FILLED
		for (Card.Filled currentFilled: Card.Filled.values()) {
			String filled = currentFilled.toString().toLowerCase();
			tmp = pointsByParameter.get(filled) + getPointsFor("filled",currentParamaterChain.get(filled));
			pointsByParameter.replace(filled, tmp);
		}
	}

	/**
	 *If a card adjacent to another don't share the current parameter, we break the chain of adjacent cards sharing this parameter
	 **/
	private void resetChains()
	{
		currentParamaterChain.replace("red",0);
		currentParamaterChain.replace("green",0);
		currentParamaterChain.replace("blue",0);
		currentParamaterChain.replace("square",0);
		currentParamaterChain.replace("triangle",0);
		currentParamaterChain.replace("circle",0);
		currentParamaterChain.replace("hollow",0);
		currentParamaterChain.replace("filled",0);
	}

	/**
	 * @return points by category
	 **/
	public Map<String,Integer> getPoints() {
			return this.pointsByParameter;
	}

}
