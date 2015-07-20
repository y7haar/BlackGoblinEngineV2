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
public class Vector4
{
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4()
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

    public Vector4 add(Vector4 rhs)
    {
        return new Vector4(x + rhs.x, y + rhs.y, z + rhs.z, w + rhs.w);
    }

    public Vector4 sub(Vector4 rhs)
    {
        return new Vector4(x - rhs.x, y - rhs.y, z - rhs.z, w - rhs.w);
    }

    public Vector4 mul(Vector4 rhs)
    {
        return new Vector4(x * rhs.x, y * rhs.y, z * rhs.z, w * rhs.w);
    }

    public Vector4 div(Vector4 rhs)
    {
        return new Vector4(x / rhs.x, y / rhs.y, z / rhs.z, w / rhs.w);
    }


    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public float dot(Vector4 rhs)
    {
        return x * rhs.x + y * rhs.y + z * rhs.z + w * rhs.w;
    }

    public Vector4 normalize()
    {
        float length = (float) Math.sqrt(x * x + y * y + z * z + w * w);

        x /= length;
        y /= length;
        z /= length;
        w /= length;

        return this;
    }

    public float distance(Vector4 rhs)
    {
        float newX = rhs.x - x;
        float newY = rhs.y - y;
        float newZ = rhs.z - z;
        float newW = rhs.w - w;

        return (float) Math.sqrt(newX * newX + newY * newY + newZ * newZ + newW * newW);
    }




    public static Vector4 parseVector(Vector3 rhs)
    {
        return new Vector4(rhs.x, rhs.y, rhs.z, 0.0f);
    }

    public static Vector4 parseVector(Vector2 rhs)
    {
        return new Vector4(rhs.x, rhs.y, 0.0f, 0.0f);
    }

    public String toString()
    {
        return "Vector4: (" + this.x + "), (" + this.y + "), (" + this.z + "), (" + this.w + ")";
    }
}
