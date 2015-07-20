package bge.math;

/**
 * Created by Yannic on 20.07.2015.
 */
public class Vector2
{
    public float x;
    public float y;

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2()
    {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 rhs)
    {
        return new Vector2(x + rhs.x, y + rhs.y);
    }

    public Vector2 sub(Vector2 rhs)
    {
        return new Vector2(x - rhs.x, y - rhs.y);
    }

    public Vector2 mul(Vector2 rhs)
    {
        return new Vector2(x * rhs.x, y * rhs.y);
    }

    public Vector2 div(Vector2 rhs)
    {
        return new Vector2(x / rhs.x, y / rhs.y);
    }


    public float length()
    {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2 rhs)
    {
        return x * rhs.x + y * rhs.y;
    }

    public Vector2 normalize()
    {
        float length = (float) Math.sqrt(x * x + y * y);

        x /= length;
        y /= length;

        return this;
    }

    public float distance(Vector2 rhs)
    {
        float newX = rhs.x - x;
        float newY = rhs.y - y;

        return (float) Math.sqrt(newX * newX + newY * newY);
    }



    public static Vector2 parseVector(Vector4 rhs)
    {
        return new Vector2(rhs.x, rhs.y);
    }

    public static Vector2 parseVector(Vector3 rhs)
    {
        return new Vector2(rhs.x, rhs.y);
    }



    public String toString()
    {
        return "Vector2: (" + this.x + "), (" + this.y + ")";
    }
}
