import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<>();
		
	public void tick() 
	{
		for(int i = 0; i < object.size(); i++) 
		{
			GameObject o = object.get(i);
			o.tick();
		} 
	}
	
	public void render(Graphics g) 
	{
		for(int i = 0; i < object.size(); i++) 
		{
			GameObject o = object.get(i);
			o.render(g);
		} 
	}
	
	public void addObject(GameObject o) 
	{
		object.add(o);
	}
	
	public void removeObject(GameObject o) 
	{
		object.remove(o);
	}
	
	public Particle[] list(Particle o) 
	{
		ArrayList<Particle> particles = new ArrayList<>();
		
		for(int i = 0; i < 3; i++) 
		{
			if(!object.get(i).equals(o)) 
				particles.add((Particle) object.get(i));
		}
		
		Particle[] list = new Particle[particles.size()];
		particles.toArray(list);
		return list;
	}
}
