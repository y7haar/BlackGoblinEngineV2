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
 * Created by Yannic Siebenhaar on 20.07.2015.
 */
public class Quaternion
{
    public float x;
    public float y;
    public float z;
    public float w;

    public Quaternion(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion()
    {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public void set(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion mul(Quaternion rhs)
    {
        float newW = w * rhs.w - x * rhs.x - y * rhs.y - z * rhs.z;
        float newX = x * rhs.w + w * rhs.x + y * rhs.z - z * rhs.y;
        float newY = y * rhs.w + w * rhs.y + z * rhs.x - x * rhs.z;
        float newZ = z * rhs.w + w * rhs.z + x * rhs.y - y * rhs.x;

        return new Quaternion(newX, newY, newZ, newW);
    }

    public Quaternion mul(Vector3 rhs)
    {
        float newW = -x * rhs.x - y * rhs.y - z * rhs.z;
        float newX = w * rhs.x + y * rhs.z - z * rhs.y;
        float newY = w * rhs.y + z * rhs.x - x * rhs.z;
        float newZ = w * rhs.z + x * rhs.y - y * rhs.x;

        return new Quaternion(newX, newY, newZ, newW);
    }

    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion normalize()
    {
        float length = (float) Math.sqrt(x * x + y * y + z * z + w * w);

        x /= length;
        y /= length;
        z /= length;
        w /= length;

        return this;
    }

    public Quaternion fromAngleAxis(float angle, Vector3 axis)
    {
        Vector3 axisNorm = axis.getNormalized();
        angle = (float) Math.toRadians(angle) / 2.0f;

        float sinus = (float) Math.sin(angle);

        x = axisNorm.x * sinus;
        y = axisNorm.y * sinus;
        z = axisNorm.z * sinus;
        w = (float) Math.cos(angle);

        return this;
    }

    public Quaternion fromEulerAngles(Vector3 euler)
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

        /*
        w = (cos1 * cos2 * cos3 - sin1 * sin2 * sin3);
        x = (sin1 * sin2 * cos3 + cos1 * cos2 * sin3);
        y = (sin1 * cos2 * cos3 + cos1 * sin2 * sin3);
        z = (cos1 * sin2 * cos3 - sin1 * cos2 * sin3);
        */


        w = (cos1 * cos2 * cos3 - sin1 * sin2 * sin3);
        x = (sin1 * sin2 * cos3 + cos1 * cos2 * sin3);
        z = (sin1 * cos2 * cos3 + cos1 * sin2 * sin3);
        y = (cos1 * sin2 * cos3 - sin1 * cos2 * sin3);

        return this;
    }


    public String toString()
    {
        return "Quaternion: (" + this.x + "), (" + this.y + "), (" + this.z + "), (" + this.w + ")";
    }

}
