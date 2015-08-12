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
 * Class for representing a Quaternion. Quaternions are commonly used for handling rotations.
 *
 * Created by Yannic Siebenhaar on 20.07.2015.
 */
public class Quaternion
{
    /**
     * The (imaginary) x component of a Quaternion.
     */
    public float x;

    /**
     * The (imaginary) y component of a Quaternion.
     */
    public float y;

    /**
     * The (imaginary) z component of a Quaternion.
     */

    public float z;
    /**
     * The (real) w component of a Quaternion.
     */
    public float w;

    /**
     * Constructs a new Quaternion with given values.
     *
     * @param x The (imaginary) x component of a Quaternion.
     * @param y The (imaginary) y component of a Quaternion.
     * @param z The (imaginary) z component of a Quaternion.
     * @param w The (real) w component of a Quaternion.
     */
    public Quaternion(final float x, final float y, final float z, final float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Constructs a new Quaternion, all Values are set to 0.0f.
     */
    public Quaternion()
    {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    /**
     * Setter method to set all values of the Quaternion. If you want to set a single value, use the public property.
     *
     * @param x The (imaginary) x component of a Quaternion.
     * @param y The (imaginary) y component of a Quaternion.
     * @param z The (imaginary) z component of a Quaternion.
     * @param w The (real) w component of a Quaternion.
     */
    public void set(final float x, final float y, final float z, final float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Performs a multiplication with another Quaternion. This operation returns a new Quaternion which contains the calculated data.
     *
     * @param rhs A Quaternion as right hand side argument.
     * @return A new Quaternion that holds the result.
     */
    public Quaternion mul(final Quaternion rhs)
    {
        final float newW = w * rhs.w - x * rhs.x - y * rhs.y - z * rhs.z;
        final float newX = x * rhs.w + w * rhs.x + y * rhs.z - z * rhs.y;
        final float newY = y * rhs.w + w * rhs.y + z * rhs.x - x * rhs.z;
        final float newZ = z * rhs.w + w * rhs.z + x * rhs.y - y * rhs.x;

        return new Quaternion(newX, newY, newZ, newW);
    }

    /**
     * Performs a multiplication with a Vector3. This operation returns a new Quaternion which contains the calculated data.
     *
     * @param rhs A Vector3 as right hand side argument.
     * @return A new Quaternion that holds the result.
     */
    public Quaternion mul(final Vector3 rhs)
    {
        final float newW = -x * rhs.x - y * rhs.y - z * rhs.z;
        final float newX = w * rhs.x + y * rhs.z - z * rhs.y;
        final float newY = w * rhs.y + z * rhs.x - x * rhs.z;
        final float newZ = w * rhs.z + x * rhs.y - y * rhs.x;

        return new Quaternion(newX, newY, newZ, newW);
    }

    /**
     * Calculates the length of the Quaternion and returns the result as floating point number.
     *
     * @return The length of the Quaternion.
     */
    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * Normalizes the Quaternion. Normalizing means dividing all components (x, y, z, w) by the length of a Quaternion.
     * This Operation affects the Quaternion and changes data.
     * @return The updated instance.
     */
    public Quaternion normalize()
    {
        final float length = (float) Math.sqrt(x * x + y * y + z * z + w * w);

        x /= length;
        y /= length;
        z /= length;
        w /= length;

        return this;
    }

    /**
     * Conjugates the Quaternion.
     * This Operation affects the Quaternion and changes data.
     *
     * @return The updated instance.
     */
    public Quaternion conjugate()
    {
        x = -x;
        y = -y;
        z = -z;

        return this;
    }

    /**
     * Returns a new instance of a conjugated Quaternion.
     *
     * @return A new conjugated instance.
     */
    public Quaternion getConjugated()
    {
        return new Quaternion(-x, -y, -z, w);
    }

    /**
     * Converts a rotation around angle axis into a Quaternion. This Operation affects the Quaternion and changes data.
     *
     * @param angle The rotation in degrees around the axis.
     * @param axis  The axis as Vector3 to rotate around.
     * @return The updated instance.
     */
    public Quaternion fromAngleAxis(float angle, final Vector3 axis)
    {
        final Vector3 axisNorm = axis.getNormalized();
        angle = (float) Math.toRadians(angle) / 2.0f;

        final float sinus = (float) Math.sin(angle);

        x = axisNorm.x * sinus;
        y = axisNorm.y * sinus;
        z = axisNorm.z * sinus;
        w = (float) Math.cos(angle);

        return this;
    }

    /**
     * Converts a rotation from euler angles into a Quaternion. This Operation affects the Quaternion and changes data.
     *
     * @param euler A Vector3 which represents an euler rotation
     * @return The updated instance.
     */
    public Quaternion fromEulerAngles(final Vector3 euler)
    {
        // Degree to Radiant

        final float xRad2 = (float) Math.toRadians(euler.x) / 2.0f;
        final float yRad2 = (float) Math.toRadians(euler.y) / 2.0f;
        final float zRad2 = (float) Math.toRadians(euler.z) / 2.0f;

        final float cos1 = (float) Math.cos(xRad2);
        final float cos2 = (float) Math.cos(yRad2);
        final float cos3 = (float) Math.cos(zRad2);

        final float sin1 = (float) Math.sin(xRad2);
        final float sin2 = (float) Math.sin(yRad2);
        final float sin3 = (float) Math.sin(zRad2);

        w = (cos1 * cos2 * cos3 - sin1 * sin2 * sin3);

        y = (cos1 * sin2 * cos3 - sin1 * cos2 * sin3);
        x = (sin1 * cos2 * cos3 + cos1 * sin2 * sin3);
        z = (sin1 * sin2 * cos3 + cos1 * cos2 * sin3);

        return this;
    }

    public boolean equals(Quaternion rhs)
    {
        return (x == rhs.x && y == rhs.y && z == rhs.z && w == rhs.w);
    }

    /**
     * Converts Quaternion into a String. Useful for debugging.
     * @return The converted String.
     */
    public String toString()
    {
        return "Quaternion: (" + this.x + "), (" + this.y + "), (" + this.z + "), (" + this.w + ")";
    }

}
