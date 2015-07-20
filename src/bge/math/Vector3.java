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
public class Vector3
{
    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3()
    {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public void set(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 rhs)
    {
        return new Vector3(x + rhs.x, y + rhs.y, z + rhs.z);
    }

    public Vector3 sub(Vector3 rhs)
    {
        return new Vector3(x - rhs.x, y - rhs.y, z - rhs.z);
    }

    public Vector3 mul(Vector3 rhs)
    {
        return new Vector3(x * rhs.x, y * rhs.y, z * rhs.z);
    }

    public Vector3 div(Vector3 rhs)
    {
        return new Vector3(x / rhs.x, y / rhs.y, z / rhs.z);
    }


    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3 rhs)
    {
        return x * rhs.x + y * rhs.y + z * rhs.z;
    }

    public Vector3 normalize()
    {
        float length = (float) Math.sqrt(x * x + y * y + z * z);

        x /= length;
        y /= length;
        z /= length;

        return this;
    }

    public Vector3 cross(Vector3 rhs)
    {
        float newX, newY, newZ;

        newX = (y * rhs.z) - (z * rhs.y);
        newY = (z * rhs.x) - (x * rhs.z);
        newZ = (x * rhs.y) - (y * rhs.x);

        return new Vector3(newX, newY, newZ);
    }

    public float distance(Vector3 rhs)
    {
        float newX = rhs.x - x;
        float newY = rhs.y - y;
        float newZ = rhs.z - z;

        return (float) Math.sqrt(newX * newX + newY * newY + newZ * newZ);
    }


    public static Vector3 parseVector(Vector4 rhs)
    {
        return new Vector3(rhs.x, rhs.y, 0.0f);
    }

    public static Vector3 parseVector(Vector2 rhs)
    {
        return new Vector3(rhs.x, rhs.y, 0.0f);
    }

    public String toString()
    {
        return "Vector3: (" + this.x + "), (" + this.y + "), (" + this.z + ")";
    }
}
