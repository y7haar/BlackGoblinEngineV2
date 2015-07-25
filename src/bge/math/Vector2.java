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

package bge.math;

/**
 * Class for representing a Vector with 2 components.
 * Commonly used for 2D transformations that affect the screen.
 *
 * Created by Yannic Siebenhaar on 20.07.2015.
 */
public class Vector2
{
    public static final Vector2 UP = new Vector2(0.0f, 1.0f);
    public static final Vector2 DOWN = new Vector2(0.0f, -1.0f);

    public static final Vector2 LEFT = new Vector2(-1.0f, 0.0f);
    public static final Vector2 RIGHT = new Vector2(1.0f, 0.0f);

    /**
     * The x component.
     */
    public float x;

    /**
     * The y component
     */
    public float y;

    /**
     * Constructs a new Vector with given values.
     *
     * @param x The x component of a Vector.
     * @param y The y component of a Vector.
     */
    public Vector2(final float x, final float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new Vector. All components are set to 0.0f.
     */
    public Vector2()
    {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    /**
     * Setter method to set all values of the Vector. If you want to set a single value, use the public property.
     *
     * @param x The x component of a Vector.
     * @param y The y component of a Vector.
     */
    public void set(final float x, final float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Performs an addition with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector2 add(final Vector2 rhs)
    {
        return new Vector2(x + rhs.x, y + rhs.y);
    }

    /**
     * Performs a subtraction with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector2 sub(final Vector2 rhs)
    {
        return new Vector2(x - rhs.x, y - rhs.y);
    }

    /**
     * Performs a multiplication with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector2 mul(final Vector2 rhs)
    {
        return new Vector2(x * rhs.x, y * rhs.y);
    }

    /**
     * Performs a multiplication with a float value. All components are multiplicated with given value.
     * This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs The number.
     * @return A new Vector that holds the result.
     */
    public Vector2 mul(final float rhs)
    {
        return new Vector2(x * rhs, y * rhs);
    }

    /**
     * Performs a division with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector2 div(final Vector2 rhs)
    {
        return new Vector2(x / rhs.x, y / rhs.y);
    }

    /**
     * Calculates the length of the Vector and returns the result as floating point number.
     *
     * @return The length of the Vector.
     */
    public float length()
    {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Calculates the dot product of two Vectors and returns the result as floating point number.
     *
     * @param rhs Right hand side value to calculate dot product.
     * @return The dot product of the Vector.
     */
    public float dot(final Vector2 rhs)
    {
        return x * rhs.x + y * rhs.y;
    }

    /**
     * Normalizes the Vector. Normalizing means dividing all components (x, y) by the length of a Vector.
     * This Operation affects the Vector and changes data.
     * @return The updated instance.
     */
    public Vector2 normalize()
    {
        final float length = (float) Math.sqrt(x * x + y * y);

        x /= length;
        y /= length;

        return this;
    }

    /**
     * Returns a new instance of the normalized Vector.
     * Normalizing means dividing all components (x, y) by the length of a Vector.
     * This Operation does not affects the Vector.
     * @return A new instance of the normalized Vector.
     */
    public Vector2 getNormalized()
    {
        final float length = (float) Math.sqrt(x * x + y * y);

        float newX = x / length;
        float newY = y / length;

        return new Vector2(newX, newY);
    }

    /**
     * Calculates the distance of two Vectors.
     *
     * @param rhs Right hand side value to calculate the distance.
     * @return The distance as floating point number.
     */
    public float distance(final Vector2 rhs)
    {
        final float newX = rhs.x - x;
        final float newY = rhs.y - y;

        return (float) Math.sqrt(newX * newX + newY * newY);
    }

    /**
     * Converts a Vector4 into a Vector2. The z and w components are ignored.
     *
     * @param rhs Vector which will be converted.
     * @return A new Vector that contains data of the given Vector.
     */
    public static Vector2 parseVector(final Vector4 rhs)
    {
        return new Vector2(rhs.x, rhs.y);
    }

    /**
     * Converts a Vector3 into a Vector2. The z component is ignored.
     *
     * @param rhs Vector which will be converted.
     * @return A new Vector that contains data of the given Vector.
     */
    public static Vector2 parseVector(final Vector3 rhs)
    {
        return new Vector2(rhs.x, rhs.y);
    }


    /**
     * Converts Vector2 into a String. Useful for debugging.
     * @return The converted String.
     */
    public String toString()
    {
        return "Vector2: (" + this.x + "), (" + this.y + ")";
    }
}
