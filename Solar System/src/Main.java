import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1200, HEIGHT = 1200;

	private boolean isRunning = false;
	private Thread thread;
		
	Handler handler;
	
	public Main() 
	{
		handler = new Handler();
		
		handler.addObject(new Particle(200, 200, 400, 200, 10, 4, ID.Particle, handler));
		handler.addObject(new Particle(500, 500, 150, 100, 0, 0, ID.Particle, handler));
		handler.addObject(new Particle(100, 800, 50, 80, 5, -2, ID.Particle, handler));
						
		new Window(WIDTH, HEIGHT, "Orbital Motion", this);
		start();
	}
	
	private void start() 
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() 
	{
		isRunning = false;
		
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void run() 
	{
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(isRunning)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
	}
	
	public void tick() 
	{
		handler.tick();
	}
	
	public void render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) 
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		//////////////////////////////
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);

		//////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) 
	{
		new Main();
	}
}
