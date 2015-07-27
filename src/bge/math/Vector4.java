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
 * Class for representing a Vector with 4 components.
 * Commonly used for calculations with matrices.
 *
 * Created by Yannic Siebenhaar on 20.07.2015.
 */
public class Vector4
{
    /**
     * The x component.
     */
    public float x;

    /**
     * The y component.
     */
    public float y;

    /**
     * The z component.
     */
    public float z;

    /**
     * The w component.
     */
    public float w;

    /**
     * Constructs a new Vector with given values.
     *
     * @param x The x component of a Vector.
     * @param y The y component of a Vector.
     * @param z The z component of a Vector.
     * @param w The w component of a Vector.
     */
    public Vector4(final float x, final float y, final float z, final float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Constructs a new Vector. All components are set to 0.0f.
     */
    public Vector4()
    {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    /**
     * Copy Constructor for getting a new Vector with same values.
     * @param vector The Vector to copy from.
     */
    public Vector4(Vector4 vector)
    {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
        this.w = vector.w;
    }

    /**
     * Setter method to set all values of the Vector. If you want to set a single value, use the public property.
     *
     * @param x The x component of a Vector.
     * @param y The y component of a Vector.
     * @param z The z component of a Vector.
     * @param w The w component of a Vector.
     */
    public void set(final float x, final float y, final float z, final float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Performs an addition with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector4 add(final Vector4 rhs)
    {
        return new Vector4(x + rhs.x, y + rhs.y, z + rhs.z, w + rhs.w);
    }

    /**
     * Performs a subtraction with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector4 sub(final Vector4 rhs)
    {
        return new Vector4(x - rhs.x, y - rhs.y, z - rhs.z, w - rhs.w);
    }

    /**
     * Performs a multiplication with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector4 mul(final Vector4 rhs)
    {
        return new Vector4(x * rhs.x, y * rhs.y, z * rhs.z, w * rhs.w);
    }

    /**
     * Performs a multiplication with a float value. All components are multiplicated with given value.
     * This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs The number.
     * @return A new Vector that holds the result.
     */
    public Vector4 mul(final float rhs)
    {
        return new Vector4(x * rhs, y * rhs, z * rhs, w * rhs);
    }

    /**
     * Performs a division with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector4 div(final Vector4 rhs)
    {
        return new Vector4(x / rhs.x, y / rhs.y, z / rhs.z, w / rhs.w);
    }

    /**
     * Calculates the length of the Vector and returns the result as floating point number.
     *
     * @return The length of the Vector.
     */
    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * Calculates the dot product of two Vectors and returns the result as floating point number.
     *
     * @param rhs Right hand side value to calculate dot product.
     * @return The dot product of the Vector.
     */
    public float dot(final Vector4 rhs)
    {
        return x * rhs.x + y * rhs.y + z * rhs.z + w * rhs.w;
    }

    /**
     * Normalizes the Vector. Normalizing means dividing all components (x, y, z, w) by the length of a Vector.
     * This Operation affects the Vector and changes data.
     * @return The updated instance.
     */
    public Vector4 normalize()
    {
        final float length = (float) Math.sqrt(x * x + y * y + z * z + w * w);

        x /= length;
        y /= length;
        z /= length;
        w /= length;

        return this;
    }

    /**
     * Negates the Vector. All values are multiplied with -1.
     * This Operation affects the Vector and changes data.
     *
     * @return The updated instance.
     */
    public Vector4 negate()
    {
        x = -x;
        y = -y;
        z = -z;
        w = -w;

        return this;
    }

    /**
     * Returns a new instance of the negated Vector.
     * All components are multiplied with -1.
     * This Operation does not affects the Vector.
     *
     * @return A new instance of the negated Vector.
     */
    public Vector4 getNegated()
    {
        return new Vector4(-x, -y, -z, -w);
    }

    /**
     * Returns a new instance of the normalized Vector.
     * Normalizing means dividing all components (x, y, z, w) by the length of a Vector.
     * This Operation does not affects the Vector.
     * @return A new instance of the normalized Vector.
     */
    public Vector4 getNormalized()
    {
        final float length = (float) Math.sqrt(x * x + y * y + z * z + w * w);

        final float newX = x / length;
        final float newY = y / length;
        final float newZ = z / length;
        final float newW = w / length;

        return new Vector4(newX, newY, newZ, newW);
    }

    /**
     * Calculates the distance of two Vectors.
     *
     * @param rhs Right hand side value to calculate the distance.
     * @return The distance as floating point number.
     */
    public float distance(final Vector4 rhs)
    {
        final float newX = rhs.x - x;
        final float newY = rhs.y - y;
        final float newZ = rhs.z - z;
        final float newW = rhs.w - w;

        return (float) Math.sqrt(newX * newX + newY * newY + newZ * newZ + newW * newW);
    }


    /**
     * Converts a Vector3 into a Vector4. The w component is set to 0.0f.
     *
     * @param rhs Vector which will be converted.
     * @return A new Vector that contains data of the given Vector.
     */
    public static Vector4 parseVector(final Vector3 rhs)
    {
        return new Vector4(rhs.x, rhs.y, rhs.z, 0.0f);
    }

    /**
     * Converts a Vector2 into a Vector4. The z and w components are set to 0.0f.
     *
     * @param rhs Vector which will be converted.
     * @return A new Vector that contains data of the given Vector.
     */
    public static Vector4 parseVector(final Vector2 rhs)
    {
        return new Vector4(rhs.x, rhs.y, 0.0f, 0.0f);
    }

    /**
     * Converts Vector4 into a String. Useful for debugging.
     * @return The converted String.
     */
    public String toString()
    {
        return "Vector4: (" + this.x + "), (" + this.y + "), (" + this.z + "), (" + this.w + ")";
    }
}
