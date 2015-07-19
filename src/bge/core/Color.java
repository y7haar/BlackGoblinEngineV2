package bge.core;

/**
 * Created by Yannic on 19.07.2015.
 */
public class Color
{
    public static Color BLACK   = new Color(0.0f, 0.0f, 0.0f);
    public static Color WHITE   = new Color(1.0f, 1.0f, 1.0f);
    public static Color GREY    = new Color(0.5f, 0.5f, 0.5f);
    public static Color BLUE    = new Color(0.0f, 0.0f, 1.0f);
    public static Color RED     = new Color(1.0f, 0.0f, 0.0f);
    public static Color GREEN   = new Color(0.0f, 1.0f, 0.0f);
    public static Color YELLOW  = new Color(1.0f, 1.0f, 0.0f);
    public static Color CYAN    = new Color(0.0f, 1.0f, 1.0f);
    public static Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
    public static Color CLEAR   = new Color(0.0f, 0.0f, 0.0f, 0.0f);


    private float red;
    private float green;
    private float blue;
    private float alpha;

    public Color(float red, float green, float blue, float alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color(float red, float green, float blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 1.0f;
    }

    public Color()
    {
        this.red = 0.0f;
        this.green = 0.0f;
        this.blue = 0.0f;
        this.alpha = 1.0f;
    }

    public void set(float r, float g, float b, float a)
    {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    public void set(float r, float g, float b)
    {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public float getRed()
    {
        return red;
    }

    public float getGreen()
    {
        return green;
    }

    public float getBlue()
    {
        return blue;
    }

    public float getAlpha()
    {
        return alpha;
    }

    public void setRed(float red)
    {
        this.red = red;
    }

    public void setGreen(float green)
    {
        this.green = green;
    }

    public void setBlue(float blue)
    {
        this.blue = blue;
    }

    public void setAlpha(float alpha)
    {
        this.alpha = alpha;
    }

    public String toString()
    {
        return "Color: (" + red + "), (" + green + "), (" + blue + "), (" + alpha + ")";
    }

}