import java.awt.Polygon;
import java.awt.Rectangle;

public class fire extends Polygon{
	
	private static final long serialVersionUID = 1L;
	
	int pWidth = playground.pWidth;
	int pHeight = playground.pHeight;
	private double locx = 0;
	private double locy = 0;
	private double movAspect=0;
	public static int[] vpolygonX = {3,-3,-3,3,3};
	public static int[] vpolygonY = {3,-3,3,-3,3};
	private int fWidth = 5;
	private int fHeight = 5;
	private double xSpeed = 5;
	private double ySpeed = 5;		
	public boolean exist = false;
	
	public fire (double locx, double locy, double movAspect) {
		
		super(vpolygonX, vpolygonY,5);
		this.exist = true;
		this.locx = locx;
		this.locy = locy;
		this.movAspect = movAspect;
		this.setxSpeed(this.fxmovAspect(movAspect)*10);
		this.setySpeed(this.fymovAspect(movAspect)*10);
	}
	
	public double fxmovAspect(double xmovAspect) {
		return (double) (Math.cos(xmovAspect * Math.PI/180));
	}
	public double fymovAspect(double ymovAspect) {
		return (double) (Math.sin(ymovAspect * Math.PI/180));
	}
	
	public double getlocx() {
		return locx;
	}
	
	public double getlocy() {
		return locy;
	}
		
	public void setlocx(double xloc) {
		this.locx = xloc;
	}
	public void setlocy(double yloc) {
		this.locy = yloc;
	}
	
	public double getmovAspect() {
		return movAspect;
	}
	
	public void setmovAspect(double aspectmov) {
		this.movAspect = aspectmov;
	}
	
	public void xPoz(double inc) {
		this.locx += inc;
	}
	
	public void yPoz(double inc) {
		this.locy += inc;
	}
	
	public double getxSpeed() {
		return xSpeed;
	}
	
	public double getySpeed() {
		return ySpeed;
	}
	
	public void setxSpeed(double speedx) {
		this.xSpeed = speedx;
	}
	
	public void setySpeed(double speedy) {
		this.ySpeed = speedy;
	}
	
	public int getfWidth() {
		return fWidth;
	}
	public int getfHeight() {
		return fHeight;
	}
	
	public Rectangle getClash() {
		return new Rectangle ((int) getlocx()-6, (int) getlocy()-6,getfWidth(),getfHeight());
	}
	
	public void move() {
		
		if (this.exist) {
			this.xPoz(this.getxSpeed());
			if (this.getlocx()<0 || this.getlocx()>pWidth) {
				this.exist = false;
			}
			this.yPoz(this.getySpeed());
			if (this.getlocy()<0 || this.getlocy()>pHeight) {
				this.exist = false;
			}
		}
	}
	
}