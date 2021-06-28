import java.awt.Polygon;
import java.awt.Rectangle;

public class vehicle extends Polygon{

	private static final long serialVersionUID = 1L;
	
	private double xspeed = 0, yspeed = 0;
	
	int pWidth = playground.pWidth;
	int pHeight = playground.pHeight;
	
	private double locx = pWidth/2;
	private double locy = pHeight/2;
	public static int[] vpolygonX = {-13,14,-13,-5,-13};
	public static int[] vpolygonY = {-15,0,15,0,-15};
	private int vWidth = 22;
	private int vHeight = 33;
	private double xPoz = getxloc() + vehicle.vpolygonX[0];
	private double yPoz = getyloc() + vehicle.vpolygonY[0];
	private double dirAspect = 0, movAspect = 0;
	
	public double getxloc() {
		return locx;
	}
	
	public double getyloc() {
		return locy;
	}
	
	public void setxloc(double xloc) {
		this.locx = xloc;
	}
	
	public void setyloc(double yloc) {
		this.locy = yloc;
	}
	
	public void increaseX(double incloc) {
		this.locx += incloc;
	}
	
	public void increaseY(double incloc) {
		this.locy += incloc;
	}
	
	public double getxPoz() {
		return xPoz;			
	}
	
	public double getyPoz() {
		return yPoz;			
	}
	
	public void setxPoz(double pozX) {
		this.xPoz = pozX;	
	}
	
	public void setyPoz(double pozY) {
		this.yPoz = pozY;	
	}
	
	public int getvWidth() {
		return vWidth;
	}
	
	public int getvHeight() {
		return vHeight;
	}
	
	public double getxspeed() {
		return xspeed;
	}
	
	public double getyspeed() {
		return yspeed;
	}
	
	public void setxspeed(double speedx) {
		this.xspeed = speedx;
	}
	
	public void setyspeed(double speedy) {
		this.yspeed = speedy;
	}
	
	public void increaseSpeedx(double xSpeedIncrease) {
		this.xspeed+=xSpeedIncrease;
	}
	
	public void increaseSpeedy(double ySpeedIncrease) {
		this.yspeed+=ySpeedIncrease;
	}
	
	public void decreaseSpeedx(double xSpeedDecrease) {
		this.xspeed-=xSpeedDecrease;
	}
	
	public void decreaseSpeedy(double ySpeedDecrease) {
		this.yspeed-=ySpeedDecrease;
	}
	
	public void setmovAspect(double movaspect) {
		this.movAspect = movaspect;
	}
	
	public double getmovAspect() {
		return movAspect;
	}
	
	public void increaseMovAspect(double movAspect) {
		this.movAspect += movAspect;
	}
	
	public double xmovAspect(double xmovaspect) {
		return (double) (Math.cos(xmovaspect * Math.PI/180));
	}
	
	public double ymovAspect(double ymovaspect) {
		return (double) (Math.sin(ymovaspect * Math.PI/180));
	}
	
	public double getdirAspect() {
		return dirAspect;
	}
	
	public void increaseDirAspect() {
		if (getdirAspect() >= 355) {
			dirAspect = 0;
		}
		else
		{
			dirAspect += 5;
		}
	}
	
	public void decreaseDirAspect() {
		if (getdirAspect() < 0) {
			dirAspect = 355;
		}
		else
		{
			dirAspect -= 5;
		}
	}
	
	public Rectangle getClash() {
		return new Rectangle((int) getxloc()-14, (int)getyloc()-14, getvWidth(), getvHeight());		
	}
	
	public double getvfrontx() {
		return this.getxloc() + Math.cos(dirAspect) * 14 ;
	}
	
	public double getvfronty() {
		return this.getyloc() + Math.sin(dirAspect) * 14 ;
	}
	
	public void move() {
		this.increaseX(this.getxspeed());
		
		if(this.getxloc()<0) {
			this.setxloc(pWidth);
		}else if (this.getxloc() > pWidth) {
			this.setxloc(0);
		}
		
		this.increaseY(this.getyspeed());
		
		if(this.getyloc()<0) {
			this.setyloc(pHeight);
		}else if (this.getyloc() > pHeight) {
			this.setyloc(0);
		}
		
	}
	
	
	public vehicle() {
		super(vpolygonX, vpolygonY, 5);
	}
	
}