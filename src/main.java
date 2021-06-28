import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

class main extends Polygon{
	private static final long serialVersionUID = 1L;
	int xPoz;
	int yPoz;
	int x = 1;
	int y = 1;
	int bsWidth = 22;
	int bsHeight = 33;
	int[] xArray, yArray;
	int bsscore = 0;
	int width = playground.pWidth;
	int height = playground.pHeight;
	public boolean exist = true;
	
	public static ArrayList<main> bs = new ArrayList<main>();
	
	public static int[] bpolygonX = {7,11,22,0,14,22,34,22,9,3,74,29,55};
	public static int[] bpolygonY = {38,11,14,13,46,44,2,0,4,2,13,20,10};
	
	public main(int[] xArray, int[] yArray, int polygonDraw, int rndStartingPointX, int rndStartingPointY) {
		
		super(xArray, yArray, polygonDraw);
		this.x = (int) (Math.random()*5);
		this.y = (int) (Math.random()*5);	
		this.xPoz = rndStartingPointX; 
		this.yPoz = rndStartingPointY; 
	}
	
	public Rectangle getClash() {
		
		return new Rectangle(super.xpoints[0], super.ypoints[0], bsWidth, bsHeight);
		
	}
	
	public void move(vehicle spaceVehicle, ArrayList<fire> f) {
		
		int xPoz = super.xpoints[0];
		int yPoz = super.ypoints[0];
		
		Rectangle bsControl = this.getClash();
		
		for (main bstone : bs) {
			
			if(bstone.exist) {
			Rectangle other = bstone.getClash();
			
			if (bstone != this && other.intersects(bsControl)) {
				int rndX = this.x;
				int rndY = this.y;
				this.x = bstone.x;
				this.y = bstone.y;
				bstone.x = rndX;
				bstone.y = rndY;
				}
			Rectangle vBox = spaceVehicle.getClash();
			if (other.intersects(vBox)) {
				spaceVehicle.setxloc(spaceVehicle.pWidth/2);
				spaceVehicle.setyloc(spaceVehicle.pHeight/2);
				spaceVehicle.setxspeed(0);
				spaceVehicle.setyspeed(0);
				bsscore++;
			}
			for (fire fr: f) {
				if (fr.exist) {
					if(other.contains(fr.getlocx(),fr.getlocy())) {
						bstone.exist = false;
						fr.exist = false;
					}
				}
			}
			}
		}
		
		if (xPoz < 0 || (xPoz + 33) > width) {
			x *= -1;
		}
		if (yPoz < 0 || (yPoz + 66) > height) {
			y *= -1;
		}
		
		for (int i = 0; i<super.xpoints.length; i++) {
			
			super.xpoints[i] += x;
			super.ypoints[i] += y;
			
		}
		
	}
	
	public static int[] getxArray(int rndStartingPointX) {
		
		int[] rndxArray = (int[]) bpolygonX.clone();
		
		for(int i = 0; i<rndxArray.length; i++)
		{
			rndxArray[i] += rndStartingPointX;
		}
		return rndxArray;
	}
	
	public static int[] getyArray(int rndStartingPointY) {
		
		int[] rndyArray = (int[]) bpolygonY.clone();
		
		for(int i = 0; i<rndyArray.length; i++)
		{
			rndyArray[i] += rndStartingPointY;
		}
		return rndyArray;
	}
}


