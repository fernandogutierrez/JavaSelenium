package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

public class StringGenerator {
     
	
	public static String randomName()
	{
		return Long.toHexString(Double.doubleToLongBits(Math.random()));
	}
	public static String randomRoomName(List<WebElement> roomsAvaibles)
	{
		int roomSize = roomsAvaibles.size();
	    ArrayList<String> nameRooms = new ArrayList<String>();
	    
		for(WebElement row : roomsAvaibles){
	    	String textRoom = row.getText();
	    	nameRooms.add(textRoom);
	    }
	    int numRandom = getRandom(roomSize);
	    String split[]= StringUtils.split(nameRooms.get(numRandom));
	    
		return split[0];
	}
	private static int getRandom(int max)
	{
		Random rand = new Random();
	    return rand.nextInt((max - 0) + 1) + 0;
	}
    
	public static String randomRoom(List<WebElement> roomsAvaibles)
	{
	   List<String> nameRooms = null;
	   
		for(WebElement row : roomsAvaibles){
	    	String textRoom = row.getText();
	    	textRoom = textRoom.replaceAll("\\s+","");
	    	nameRooms.add(row.getText());
	    }
		 return "nn";
	}
}
