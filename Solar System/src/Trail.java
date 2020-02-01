import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Trail extends GameObject{

	private float alpha = 1;
	private Handler handler;
	private Color color;
	
	private int width, height;
	private float life;
	
	public Trail(double x, double y, double velX, double velY, ID id, Color color, int width, int height, float life, Handler handler) 
	{
		super(x, y, velX, velY, id);
		this.color = color;
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	public void tick() 
	{
		if(alpha > life) alpha -= life - .0001f;
		else handler.removeObject(this);
	}
	
	public void render(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillOval((int) (x-width/2), (int) (y-height/2), width, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float Alpha) 
	{
		return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	}
}
