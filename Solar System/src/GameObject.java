import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject
{
    protected double x, y, velX, velY;
    protected ID id;
    
    public GameObject(double x, double y, double velX, double velY, ID id)
    {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.id = id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setId(ID id)
    {
        this.id = id;
    }
    
    public ID getId()
    {
        return id;
    }
    
    public void setVelX(double velX)
    {
        this.velX = velX;
    }
    
    public void setVelY(double velY)
    {
        this.velY = velY;
    }
    
    public double getVelX()
    {
        return velX;
    }
    public double getVelY()
    {
        return velY;
    }
}