/*
 * Copyright (c) 2015 Yannic Siebenhaar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package bge.core;

/**
 * Created by Yannic Siebenhaar on 19.07.2015.
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