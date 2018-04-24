package programmcode;
public class Vector2
{
    public float x;
    public float y;
    public Vector2()
    {
        this(0, 0);
    }
    public Vector2(float x, float y)
    {
        this.x = (int)x;
        this.y = (int)y;
    }
    public float distance(Vector2 other)
    {
       float dx = other.x - x;
       float dy = other.y - y;
       
        return (float)Math.sqrt(dx * dx + dy * dy);
    }
    public void set(Vector2 other)
    {
        this.x = other.x;
        this.y = other.y;
    }
}
