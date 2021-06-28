import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList; 
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

public class playground extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static int pWidth = 1920;
	public static int pHeight = 1080;
	public static boolean key = false;
	public static int keyCode;
	public static ArrayList<fire> f = new ArrayList<fire>();
	public static void main(String[] args) {
		new playground();
	}
	
	public playground() {
		
		this.setSize(pWidth, pHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawPanel panel = new drawPanel();
		this.add(panel, BorderLayout.CENTER);
		ScheduledThreadPoolExecutor start = new ScheduledThreadPoolExecutor(5);
		start.scheduleAtFixedRate(new clear(this), 0L, 25L, TimeUnit.MILLISECONDS);
		this.setVisible(true);
		
		addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent e) {
			
				if (e.getKeyCode() == 87) {

					keyCode = e.getKeyCode();
					key = true;
				}else if (e.getKeyCode() == 68) {
					keyCode = e.getKeyCode();
					key = true;
				}else if (e.getKeyCode() == 65) {
					keyCode = e.getKeyCode();
					key = true;
				}else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					f.add(new fire(drawPanel.spaceVehicle.getvfrontx(), drawPanel.spaceVehicle.getvfronty(),drawPanel.spaceVehicle.getdirAspect()));
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {		
			}

			@Override
			public void keyReleased(KeyEvent e) {
				key = false;
			}
		});
		
	}
	class clear implements Runnable {
		playground playGround;
		
		clear (playground playGround){
			this.playGround = playGround;
		}
		
		@Override
		public void run() {
			playGround.repaint();
		}	
	}
	
	@SuppressWarnings("serial")
	static class drawPanel extends JComponent{
		
		public static ArrayList<main> bs = new ArrayList<main>();
		
		int[] xArray = main.bpolygonX;
		int[] yArray = main.bpolygonY;

		static vehicle spaceVehicle = new vehicle();
		
		public drawPanel() {
			
			for(int i = 0; i < 22; i++) {
				int rndStartingPointX = (int) (Math.random() * (playground.pWidth - 50)+1);
				int rndStartingPointY = (int) (Math.random() * (playground.pHeight - 50)+1);
				
				bs.add(new main(main.getxArray(rndStartingPointX), main.getyArray(rndStartingPointY),13, rndStartingPointX, rndStartingPointY));
				
				main.bs = bs;
			}
		}
		public void paint (Graphics g) {
			
			Graphics2D graSettings = (Graphics2D) g;
			
			AffineTransform id = new AffineTransform();
			
			graSettings.setColor(Color.BLACK);
			graSettings.fillRect(0, 0, getWidth(), getHeight());
			graSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graSettings.setPaint(Color.BLUE);
			
			for(main bstone: bs) {
				if(bstone.exist) {
					bstone.move(spaceVehicle,playground.f);
					graSettings.draw(bstone);
				}
				
			}
			
			if(playground.key == true && keyCode == 68) {
				spaceVehicle.increaseDirAspect();
			}else if(playground.key == true && keyCode == 65) {
				spaceVehicle.decreaseDirAspect();
			}else if(key == true && keyCode == 87) {
				spaceVehicle.setmovAspect(spaceVehicle.getdirAspect());
				spaceVehicle.increaseSpeedx(spaceVehicle.xmovAspect(spaceVehicle.getmovAspect())/15);
				spaceVehicle.increaseSpeedy(spaceVehicle.ymovAspect(spaceVehicle.getmovAspect())/15);
				
				
			}
			
			spaceVehicle.move();
			
			graSettings.setTransform(id);
			graSettings.setPaint(Color.GRAY);
			graSettings.translate(spaceVehicle.getxloc(), spaceVehicle.getyloc());
			graSettings.rotate(Math.toRadians(spaceVehicle.getdirAspect()));
			graSettings.draw(spaceVehicle);
			
			for (fire fr : playground.f) {
				fr.move();
				if (fr.exist) {
					graSettings.setTransform(id);
					graSettings.translate(fr.getlocx(), fr.getlocy());
					graSettings.draw(fr);
				}
			}
		}
	}
}
