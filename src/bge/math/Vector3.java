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
 * Class for representing a Vector with 3 components.
 * Commonly used for 3D transformations.
 *
 * Created by Yannic Siebenhaar on 20.07.2015.
 */
public class Vector3
{
    public static final Vector3 UP = new Vector3(0.0f, 1.0f, 0.0f);
    public static final Vector3 DOWN = new Vector3(0.0f, -1.0f, 0.0f);

    public static final Vector3 LEFT = new Vector3(-1.0f, 0.0f, 0.0f);
    public static final Vector3 RIGHT = new Vector3(1.0f, 0.0f, 0.0f);

    public static final Vector3 FORWARD = new Vector3(0.0f, 0.0f, 1.0f);
    public static final Vector3 BACK = new Vector3(0.0f, 0.0f, -1.0f);


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
     * Constructs a new Vector with given values.
     *
     * @param x The x component of a Vector.
     * @param y The y component of a Vector.
     * @param z The z component of a Vector.
     */
    public Vector3(final float x, final float y, final float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a new Vector. All components are set to 0.0f.
     */
    public Vector3()
    {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    /**
     * Copy Constructor for getting a new Vector with same values.
     * @param vector The Vector to copy from.
     */
    public Vector3(Vector3 vector)
    {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    /**
     * Setter method to set all values of the Vector. If you want to set a single value, use the public property.
     *
     * @param x The x component of a Vector.
     * @param y The y component of a Vector.
     * @param z The z component of a Vector.
     */
    public void set(final float x, final float y, final float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Performs an addition with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector3 add(final Vector3 rhs)
    {
        return new Vector3(x + rhs.x, y + rhs.y, z + rhs.z);
    }

    /**
     * Performs a subtraction with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector3 sub(final Vector3 rhs)
    {
        return new Vector3(x - rhs.x, y - rhs.y, z - rhs.z);
    }

    /**
     * Performs a multiplication with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector3 mul(final Vector3 rhs)
    {
        return new Vector3(x * rhs.x, y * rhs.y, z * rhs.z);
    }

    /**
     * Performs a multiplication with a float value. All components are multiplicated with given value.
     * This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs The number.
     * @return A new Vector that holds the result.
     */
    public Vector3 mul(final float rhs)
    {
        return new Vector3(x * rhs, y * rhs, z * rhs);
    }

    /**
     * Performs a division with another Vector. This operation returns a new Vector which contains the calculated data.
     *
     * @param rhs A Vector as right hand side argument.
     * @return A new Vector that holds the result.
     */
    public Vector3 div(final Vector3 rhs)
    {
        return new Vector3(x / rhs.x, y / rhs.y, z / rhs.z);
    }

    /**
     * Calculates the length of the Vector and returns the result as floating point number.
     *
     * @return The length of the Vector.
     */
    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Calculates the dot product of two Vectors and returns the result as floating point number.
     *
     * @param rhs Right hand side value to calculate dot product.
     * @return The dot product of the Vector.
     */
    public float dot(final Vector3 rhs)
    {
        return x * rhs.x + y * rhs.y + z * rhs.z;
    }

    /**
     * Normalizes the Vector. Normalizing means dividing all components (x, y, z) by the length of a Vector.
     * This Operation affects the Vector and changes data.
     * @return The updated instance.
     */
    public Vector3 normalize()
    {
        final float length = (float) Math.sqrt(x * x + y * y + z * z);

        x /= length;
        y /= length;
        z /= length;

        return this;
    }

    /**
     * Negates the Vector. All values are multiplied with -1.
     * This Operation affects the Vector and changes data.
     *
     * @return The updated instance.
     */
    public Vector3 negate()
    {
        x = -x;
        y = -y;
        z = -z;

        return this;
    }

    /**
     * Returns a new instance of the negated Vector.
     * All components are multiplied with -1.
     * This Operation does not affects the Vector.
     *
     * @return A new instance of the negated Vector.
     */
    public Vector3 getNegated()
    {
        return new Vector3(-x, -y, -z);
    }

    /**
     * Returns a new instance of the normalized Vector.
     * Normalizing means dividing all components (x, y, z) by the length of a Vector.
     * This Operation does not affects the Vector.
     * @return A new instance of the normalized Vector.
     */
    public Vector3 getNormalized()
    {
        final float length = (float) Math.sqrt(x * x + y * y + z * z);

        final float newX = x / length;
        final float newY = y / length;
        final float newZ = z / length;

        return new Vector3(newX, newY, newZ);
    }

    /**
     * Calculates the cross product of two Vectors and returns the result as new Vector.
     *
     * @param rhs Right hand side parameter to calculate cross product.
     * @return A new Vector that contains the result. This Vector is orthogonal to both Vectors.
     */
    public Vector3 cross(final Vector3 rhs)
    {
        final float newX, newY, newZ;

        newX = (y * rhs.z) - (z * rhs.y);
        newY = (z * rhs.x) - (x * rhs.z);
        newZ = (x * rhs.y) - (y * rhs.x);

        return new Vector3(newX, newY, newZ);
    }

    /**
     * Calculates the distance of two Vectors.
     *
     * @param rhs Right hand side value to calculate the distance.
     * @return The distance as floating point number.
     */
    public float distance(final Vector3 rhs)
    {
        final float newX = rhs.x - x;
        final float newY = rhs.y - y;
        final float newZ = rhs.z - z;

        return (float) Math.sqrt(newX * newX + newY * newY + newZ * newZ);
    }

    /**
     * Converts a Vector4 into a Vector3. The w component is ignored.
     *
     * @param rhs Vector which will be converted.
     * @return A new Vector that contains data of the given Vector.
     */
    public static Vector3 parseVector(final Vector4 rhs)
    {
        return new Vector3(rhs.x, rhs.y, rhs.z);
    }

    /**
     * Converts a Vector2 into a Vector3. The z component is set to 0.0f.
     *
     * @param rhs Vector which will be converted.
     * @return A new Vector that contains data of the given Vector.
     */
    public static Vector3 parseVector(final Vector2 rhs)
    {
        return new Vector3(rhs.x, rhs.y, 0.0f);
    }

    public boolean equals(Vector3 rhs)
    {
        return (x == rhs.x && y == rhs.y && z == rhs.z);
    }

    /**
     * Converts Vector3 into a String. Useful for debugging.
     * @return The converted String.
     */
    public String toString()
    {
        return "Vector3: (" + this.x + "), (" + this.y + "), (" + this.z + ")";
    }
}
