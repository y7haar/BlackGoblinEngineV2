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
public class Matrix4x4
{
    public float[][] elements;

    public Matrix4x4(float[][] elements)
    {
        this.elements = elements;
    }

    public Matrix4x4()
    {
        this.elements = new float[4][4];
    }

    public float[][] getElements()
    {
        return elements;
    }

    public void setElements(float[][] elements)
    {
        this.elements = elements;
    }

    public float get(int x, int y)
    {
        return elements[x][y];
    }

    public void set(int x, int y, float value)
    {
        elements[x][y] = value;
    }

    public Matrix4x4 identity()
    {
        elements[0][0] = 1.0f;
        elements[0][1] = 0.0f;
        elements[0][2] = 0.0f;
        elements[0][3] = 0.0f;

        elements[1][0] = 0.0f;
        elements[1][1] = 1.0f;
        elements[1][2] = 0.0f;
        elements[1][3] = 0.0f;

        elements[2][0] = 0.0f;
        elements[2][1] = 0.0f;
        elements[2][2] = 1.0f;
        elements[2][3] = 0.0f;

        elements[3][0] = 0.0f;
        elements[3][1] = 0.0f;
        elements[3][2] = 0.0f;
        elements[3][3] = 1.0f;

        return this;
    }

    public Matrix4x4 add(Matrix4x4 rhs)
    {
        float[][] newMat = new float[4][4];

        newMat[0][0] = elements[0][0] + rhs.elements[0][0];
        newMat[0][1] = elements[0][1] + rhs.elements[0][1];
        newMat[0][2] = elements[0][2] + rhs.elements[0][2];
        newMat[0][3] = elements[0][3] + rhs.elements[0][3];

        newMat[1][0] = elements[1][0] + rhs.elements[1][0];
        newMat[1][1] = elements[1][1] + rhs.elements[1][1];
        newMat[1][2] = elements[1][2] + rhs.elements[1][2];
        newMat[1][3] = elements[1][3] + rhs.elements[1][3];

        newMat[2][0] = elements[2][0] + rhs.elements[2][0];
        newMat[2][1] = elements[2][1] + rhs.elements[2][1];
        newMat[2][2] = elements[2][2] + rhs.elements[2][2];
        newMat[2][3] = elements[2][3] + rhs.elements[2][3];

        newMat[3][0] = elements[3][0] + rhs.elements[3][0];
        newMat[3][1] = elements[3][1] + rhs.elements[3][1];
        newMat[3][2] = elements[3][2] + rhs.elements[3][2];
        newMat[3][3] = elements[3][3] + rhs.elements[3][3];

        return new Matrix4x4(newMat);
    }

    public Matrix4x4 mul(Matrix4x4 rhs)
    {
        float[][] newMat = new float[4][4];

        for(int i = 0;i < 4;++i)
        {
            newMat[i][0] =  (elements[i][0] * rhs.elements[0][i]) +
                            (elements[i][1] * rhs.elements[1][i]) +
                            (elements[i][2] * rhs.elements[2][i]) +
                            (elements[i][3] * rhs.elements[3][i]);

            newMat[i][1] =  (elements[i][0] * rhs.elements[0][i]) +
                            (elements[i][1] * rhs.elements[1][i]) +
                            (elements[i][2] * rhs.elements[2][i]) +
                            (elements[i][3] * rhs.elements[3][i]);

            newMat[i][2] =  (elements[i][0] * rhs.elements[0][i]) +
                            (elements[i][1] * rhs.elements[1][i]) +
                            (elements[i][2] * rhs.elements[2][i]) +
                            (elements[i][3] * rhs.elements[3][i]);

            newMat[i][3] =  (elements[i][0] * rhs.elements[0][i]) +
                            (elements[i][1] * rhs.elements[1][i]) +
                            (elements[i][2] * rhs.elements[2][i]) +
                            (elements[i][3] * rhs.elements[3][i]);
        }

        return new Matrix4x4(newMat);
    }


    public String toString()
    {
        String x = "Matrix4x4:\t(" + elements[0][0] + ", " + elements[0][1] + ", " + elements[0][2] + ", " + elements[0][3] + ")\n";
        String y =       "\t\t\t(" + elements[1][0] + ", " + elements[1][1] + ", " + elements[1][2] + ", " + elements[1][3] + ")\n";
        String z =       "\t\t\t(" + elements[2][0] + ", " + elements[2][1] + ", " + elements[2][2] + ", " + elements[2][3] + ")\n";
        String w =       "\t\t\t(" + elements[3][0] + ", " + elements[3][1] + ", " + elements[3][2] + ", " + elements[3][3] + ")";

        return x + y + z + w;
    }
}