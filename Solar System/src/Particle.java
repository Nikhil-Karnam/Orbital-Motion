import java.awt.Color;
import java.awt.Graphics;

public class Particle extends GameObject{

	private Handler handler;
	private int timer = 0;

	private double mass;
	private int size;
	
	public Particle(double x, double y, int size, double mass, double velX, double velY, ID id, Handler handler) 
	{
		super(x, y, velX, velY, id);
		
		this.handler = handler;
		this.mass = mass;
		this.size = size;
	}
	
	public boolean equals(Object o) 
	{
		if(o instanceof Particle)
		{
			Particle p = (Particle) o;
			return p.mass==mass && p.size==size;
		}
		
		return false;
	}
	
	public void tick() 
	{
		timer++;
		collision();
		
		//sets x and y velocities
		
		x += velX;
		y += velY;
		
		velX = Gravity.xForce(this, handler.list(this));
		velY = Gravity.yForce(this, handler.list(this));
		
		
		//clamps objects to field of view
		
		if(x + size >= Main.WIDTH-5) {x-=4; velX *= -.6;}
		if(x <= 5) {x+=4; velX *= -.6;}
		
		if(y + size >= Main.HEIGHT-50) {y-=4; velY *= -.6;}
		if(y <= 5) {y+=4; velY *= -.6;}
		
		//adds trail
		
		if(timer%2 == 0)
			handler.addObject(new Trail(getX(), getY(), velX, velY, ID.Trail, Color.orange, 16, 16, .01f, handler));
	}
	
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.drawOval((int) x, (int) y, size, size);
	}
	
	
	//returns true if two particles intersect each other
	
	public boolean intersects(Particle a, Particle b) 
	{
		int r = a.getRadius() + b.getRadius();
		double w = a.getX() - b.getX();
		double h = a.getY() - b.getY();
		
		if(Math.hypot(w, h) <= r)
			return true;
		
		return false;
	}
	
	public void collision() 
	{
		for(int i = 0; i < handler.list(this).length; i++) 
		{
			if(intersects(this, handler.list(this)[i])) 
			{
				
			}
		}
	}
	
	
	//getters and setters
	
	public double getMass() {
		return mass;
	}
	
	public int getRadius() {
		return size/2;
	}
	
	public double getX() {
		return x + size/2;
	}
	
	public double getY() 
	{
		return y + size/2;
	}
}