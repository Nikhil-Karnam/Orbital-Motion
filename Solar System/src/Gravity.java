
public class Gravity {
	
	private static final double gConstant = .1;
	
	//returns new trajectory from gravitational force of an list of objects
	
	public static double xForce(Particle center, Particle[] particles) 
	{	
		double value = center.getVelX();
		
		for(int i = 0; i < particles.length; i++) 
		{
			double distance = Math.hypot(particles[i].getX()-center.getX(), particles[i].getY()-center.getY());
			double gForce = gConstant * center.getMass() * particles[i].getMass() / Math.pow(distance, 2);
			value += (gForce * (particles[i].getX()-center.getX()) / distance);
		}
		
		return value;
	}
	
	public static double yForce(Particle center, Particle[] particles) 
	{	
		double value = center.getVelY();
		
		for(int i = 0; i < particles.length; i++) 
		{
			double distance = Math.hypot(particles[i].getX()-center.getX(), particles[i].getY()-center.getY());
			double gForce = gConstant * center.getMass() * particles[i].getMass() / Math.pow(distance, 2);
			value += gForce * (particles[i].getY()-center.getY()) / distance;
		}

		return value;
	}
	
}
