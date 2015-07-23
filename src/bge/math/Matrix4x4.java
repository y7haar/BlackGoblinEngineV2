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

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by Yannic Siebenhaar on 20.07.2015.
 */
public class Matrix4x4
{
    private float[][] mat;

    public Matrix4x4(float[][] elements)
    {
        this.mat = elements;
    }

    public Matrix4x4()
    {
        this.mat = new float[4][4];
        identity();
    }

    public float get(int x, int y)
    {
        return mat[x][y];
    }

    public void set(int x, int y, float value)
    {
        mat[x][y] = value;
    }

    public float[][] getElements()
    {
        return mat;
    }

    public void setElements(float[][] elements)
    {
        this.mat = elements;
    }

    public Matrix4x4 identity()
    {
        mat[0][0] = 1.0f;
        mat[0][1] = 0.0f;
        mat[0][2] = 0.0f;
        mat[0][3] = 0.0f;
        mat[1][0] = 0.0f;
        mat[1][1] = 1.0f;
        mat[1][2] = 0.0f;
        mat[1][3] = 0.0f;
        mat[2][0] = 0.0f;
        mat[2][1] = 0.0f;
        mat[2][2] = 1.0f;
        mat[2][3] = 0.0f;
        mat[3][0] = 0.0f;
        mat[3][1] = 0.0f;
        mat[3][2] = 0.0f;
        mat[3][3] = 1.0f;

        return this;
    }

    public Matrix4x4 add(Matrix4x4 rhs)
    {
        float[][] newMat = new float[4][4];

        newMat[0][0] = mat[0][0] + rhs.mat[0][0];
        newMat[0][1] = mat[0][1] + rhs.mat[0][1];
        newMat[0][2] = mat[0][2] + rhs.mat[0][2];
        newMat[0][3] = mat[0][3] + rhs.mat[0][3];

        newMat[1][0] = mat[1][0] + rhs.mat[1][0];
        newMat[1][1] = mat[1][1] + rhs.mat[1][1];
        newMat[1][2] = mat[1][2] + rhs.mat[1][2];
        newMat[1][3] = mat[1][3] + rhs.mat[1][3];

        newMat[2][0] = mat[2][0] + rhs.mat[2][0];
        newMat[2][1] = mat[2][1] + rhs.mat[2][1];
        newMat[2][2] = mat[2][2] + rhs.mat[2][2];
        newMat[2][3] = mat[2][3] + rhs.mat[2][3];

        newMat[3][0] = mat[3][0] + rhs.mat[3][0];
        newMat[3][1] = mat[3][1] + rhs.mat[3][1];
        newMat[3][2] = mat[3][2] + rhs.mat[3][2];
        newMat[3][3] = mat[3][3] + rhs.mat[3][3];

        return new Matrix4x4(newMat);
    }

    public Matrix4x4 mul(Matrix4x4 rhs)
    {
        float[][] newMat = new float[4][4];

        for(int i = 0;i < 4;++i)
        {
            newMat[i][0] = (mat[i][0] * rhs.mat[0][0]) +
                    (mat[i][1] * rhs.mat[1][0]) +
                    (mat[i][2] * rhs.mat[2][0]) +
                    (mat[i][3] * rhs.mat[3][0]);

            newMat[i][1] = (mat[i][0] * rhs.mat[0][1]) +
                    (mat[i][1] * rhs.mat[1][1]) +
                    (mat[i][2] * rhs.mat[2][1]) +
                    (mat[i][3] * rhs.mat[3][1]);

            newMat[i][2] = (mat[i][0] * rhs.mat[0][2]) +
                    (mat[i][1] * rhs.mat[1][2]) +
                    (mat[i][2] * rhs.mat[2][2]) +
                    (mat[i][3] * rhs.mat[3][2]);

            newMat[i][3] = (mat[i][0] * rhs.mat[0][3]) +
                    (mat[i][1] * rhs.mat[1][3]) +
                    (mat[i][2] * rhs.mat[2][3]) +
                    (mat[i][3] * rhs.mat[3][3]);
        }

        return new Matrix4x4(newMat);
    }

    public Matrix4x4 getTranslation(Vector3 translation)
    {
        mat[0][0] = 1.0f;
        mat[0][1] = 0.0f;
        mat[0][2] = 0.0f;
        mat[0][3] = translation.x;
        mat[1][0] = 0.0f;
        mat[1][1] = 1.0f;
        mat[1][2] = 0.0f;
        mat[1][3] = translation.y;
        mat[2][0] = 0.0f;
        mat[2][1] = 0.0f;
        mat[2][2] = 1.0f;
        mat[2][3] = translation.z;
        mat[3][0] = 0.0f;
        mat[3][1] = 0.0f;
        mat[3][2] = 0.0f;
        mat[3][3] = 1.0f;

        return this;
    }

    public Matrix4x4 getRotation(Quaternion rotation)
    {
        float xx2 = 2 * (rotation.x * rotation.x);
        float yy2 = 2 * (rotation.y * rotation.y);
        float zz2 = 2 * (rotation.z * rotation.z);

        float xy2 = 2 * rotation.x * rotation.y;
        float xz2 = 2 * rotation.x * rotation.z;
        ;

        float yz2 = 2 * rotation.y * rotation.z;
        ;

        float wx2 = 2 * rotation.w * rotation.x;
        ;
        float wy2 = 2 * rotation.w * rotation.y;
        ;
        float wz2 = 2 * rotation.w * rotation.z;
        ;


        mat[0][0] = 1 - yy2 - zz2;
        mat[0][1] = xy2 + wz2;
        mat[0][2] = xz2 - wy2;
        mat[0][3] = mat[0][3];
        mat[1][0] = xy2 - wz2;
        mat[1][1] = 1 - xx2 - zz2;
        mat[1][2] = yz2 + wx2;
        mat[1][3] = mat[1][3];
        mat[2][0] = xz2 + wy2;
        mat[2][1] = yz2 - wx2;
        mat[2][2] = 1 - xx2 - yy2;
        mat[2][3] = mat[2][3];
        mat[3][0] = 0;
        mat[3][1] = 0;
        mat[3][2] = 0;
        mat[3][3] = 1;

        //TODO: Remove last column if possible

        return this;
    }

    public Matrix4x4 getScaling(Vector3 scaling)
    {
        mat[0][0] = scaling.x;
        mat[0][1] = 0.0f;
        mat[0][2] = 0.0f;
        mat[0][3] = 0.0f;
        mat[1][0] = 0.0f;
        mat[1][1] = scaling.y;
        mat[1][2] = 0.0f;
        mat[1][3] = 0.0f;
        mat[2][0] = 0.0f;
        mat[2][1] = 0.0f;
        mat[2][2] = scaling.z;
        mat[2][3] = 0.0f;
        mat[3][0] = 0.0f;
        mat[3][1] = 0.0f;
        mat[3][2] = 0.0f;
        mat[3][3] = 1.0f;

        return this;
    }

    public FloatBuffer toFloatBuffer()
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);

        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                buffer.put(mat[i][j]);
            }
        }

        buffer.flip();
        return buffer;
    }

    public String toString()
    {
        String x = "Matrix4x4:\t(" + mat[0][0] + ", " + mat[0][1] + ", " + mat[0][2] + ", " + mat[0][3] + ")\n";
        String y = "\t\t\t(" + mat[1][0] + ", " + mat[1][1] + ", " + mat[1][2] + ", " + mat[1][3] + ")\n";
        String z = "\t\t\t(" + mat[2][0] + ", " + mat[2][1] + ", " + mat[2][2] + ", " + mat[2][3] + ")\n";
        String w = "\t\t\t(" + mat[3][0] + ", " + mat[3][1] + ", " + mat[3][2] + ", " + mat[3][3] + ")";

        return x + y + z + w;
    }
}
