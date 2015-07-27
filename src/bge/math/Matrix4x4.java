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
 * Class for representing a 4x4 Matrix.
 * Commonly used for doing calculations for the transform of an object.
 *
 * Created by Yannic Siebenhaar on 20.07.2015.
 */
public class Matrix4x4
{
    private float[][] mat;

    /**
     * Constructs a new Matrix with given elements.
     *
     * @param elements 2D Array of elements, must have the size 4 * 4.
     */
    public Matrix4x4(final float[][] elements)
    {
        this.mat = elements;
    }

    /**
     * Constructs a new Matrix with all elements set to 0.0f.
     */
    public Matrix4x4()
    {
        this.mat = new float[4][4];
    }

    /**
     * Retrieves an element of the Matrix stored at the given index x and y.
     *
     * @param x The x location of the value.
     * @param y The y location of the value.
     * @return Element that is stored at specified location.
     */
    public float get(final int x, final int y)
    {
        return mat[x][y];
    }

    /**
     * Sets an element of the Matrix stored at the given index x and y to a given value.
     *
     * @param x     The x location of the value.
     * @param y     The y location of the value.
     * @param value The value that is assigned to the location.
     */
    public void set(final int x, final int y, final float value)
    {
        mat[x][y] = value;
    }

    /**
     * Returns a 2D Array of all 4 * 4 Elements of the Matrix.
     *
     * @return 2D Array of elements.
     */
    public float[][] getElements()
    {
        return mat;
    }

    /**
     * Sets all elements to new values.
     *
     * @param elements A 2D Array of size 4 * 4 that contains updated values.
     */
    public void setElements(final float[][] elements)
    {
        this.mat = elements;
    }

    /**
     * Sets the Matrix to an identity Matrix. This operation affects the Matrix and updates its elements.
     * @return An instance of the identity Matrix.
     */
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

    /**
     * Invertes the Matrix. This operation affects the Matrix and updates its elements.
     * @return An instance of the inverted Matrix.
     */
    public Matrix4x4 inverse()
    {
        //TODO: Implement
        return this;
    }

    /**
     * Performs an addition with another Matrix. This operation returns a new Matrix which contains the calculated data.
     *
     * @param rhs Right hand side argument for addition.
     * @return A new Matrix which contains the result.
     */
    public Matrix4x4 add(final Matrix4x4 rhs)
    {
        final float[][] newMat = new float[4][4];

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

    /**
     * Performs a multiplication with another Matrix. This operation returns a new Matrix which contains the calculated data.
     *
     * @param rhs Right hand side argument for multiplication.
     * @return A new Matrix which contains the result.
     */
    public Matrix4x4 mul(final Matrix4x4 rhs)
    {
        final float[][] newMat = new float[4][4];

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

    /**
     * Performs a multiplication with another Vector4. This operation returns a new Vector4 which contains the calculated data.
     *
     * @param rhs Right hand side argument for multiplication.
     * @return A new Vector4 which contains the result.
     */
    public Vector4 mul(final Vector4 rhs)
    {
        final Vector4 newVec = new Vector4();

        newVec.x = mat[0][0] * rhs.x + mat[0][1] * rhs.y + mat[0][2] * rhs.z + mat[0][3] * rhs.w;
        newVec.y = mat[1][0] * rhs.x + mat[1][1] * rhs.y + mat[1][2] * rhs.z + mat[1][3] * rhs.w;
        newVec.z = mat[2][0] * rhs.x + mat[2][1] * rhs.y + mat[2][2] * rhs.z + mat[2][3] * rhs.w;
        newVec.w = mat[3][0] * rhs.x + mat[3][1] * rhs.y + mat[3][2] * rhs.z + mat[3][3] * rhs.w;

        return newVec;
    }

    /**
     * Calculates a Matrix translation with a given Vector. This operation affects the Matrix and updates its data.
     *
     * @param translation A Vector that stores the translation.
     * @return An instance of the Matrix that contains the result.
     */
    public Matrix4x4 getTranslation(final Vector3 translation)
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

    /**
     * Calculates a Matrix rotation with a given Quaternion. This operation affects the Matrix and updates its data.
     *
     * @param rotation A Quaternion that stores the rotation.
     * @return An instance of the Matrix that contains the result.
     */
    public Matrix4x4 getRotation(final Quaternion rotation)
    {
        final float xx2 = 2 * (rotation.x * rotation.x);
        final float yy2 = 2 * (rotation.y * rotation.y);
        final float zz2 = 2 * (rotation.z * rotation.z);

        final float xy2 = 2 * rotation.x * rotation.y;
        final float xz2 = 2 * rotation.x * rotation.z;

        final float yz2 = 2 * rotation.y * rotation.z;

        final float wx2 = 2 * rotation.w * rotation.x;
        final float wy2 = 2 * rotation.w * rotation.y;
        final float wz2 = 2 * rotation.w * rotation.z;


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

        return this;
    }

    /**
     * Calculates a Matrix scaling with a given Vector. This operation affects the Matrix and updates its data.
     *
     * @param scaling A Vector that stores the scaling.
     * @return An instance of a Matrix that contains the result.
     */
    public Matrix4x4 getScaling(final Vector3 scaling)
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

    /**
     * Calculates a Matrix to a given perspective (3D) projection.
     * The opposite of a perspective projection would be an orthographic projection.
     * This operation affects the Matrix and updates its data.
     *
     * @param fov         The Field of View of the projection in degrees.
     * @param aspectRatio The aspect ratio of the projection (e.g. 4 / 3 or 16 / 9).
     * @param zNear       Sets distance when objects are clipped near of the projection.
     * @param zFar        Sets distance when objects are clipped far of the projection.
     * @return An instance of a Matrix that contains a perspective projection.
     */
    public Matrix4x4 getPerspectiveProjection(final float fov, final float aspectRatio, final float zNear, final float zFar)
    {
        final float zRange = zNear - zFar;
        final float tanOfFov2 = (float) Math.tan(Math.toRadians(fov / 2.0));

        mat[0][0] = 1.0f / (tanOfFov2 * aspectRatio);
        mat[0][1] = 0.0f;
        mat[0][2] = 0.0f;
        mat[0][3] = 0.0f;

        mat[1][0] = 0.0f;
        mat[1][1] = 1.0f / tanOfFov2;
        mat[1][2] = 0.0f;
        mat[1][3] = 0.0f;

        mat[2][0] = 0.0f;
        mat[2][1] = 0.0f;
        mat[2][2] = (-zNear - zFar) / zRange;
        mat[2][3] = 2.0f * zFar * zNear / zRange;

        mat[3][0] = 0.0f;
        mat[3][1] = 0.0f;
        mat[3][2] = 1.0f;
        mat[3][3] = 0.0f;

        return this;
    }

    /**
     * Calculates LookAt Matrix for specified target. Up represents the orientation to up.
     *
     * @param target The target to look at.
     * @param up     Up.
     * @return Calculated LookAt Matrix.
     */
    public Matrix4x4 getLookAt(Vector3 target, Vector3 up)
    {
        Vector3 n = new Vector3(target);
        n.normalize();

        Vector3 u = new Vector3(up);
        u.normalize();
        u = u.cross(target);

        Vector3 lookAt = n.cross(u);

        mat[0][0] = u.x;
        mat[0][1] = lookAt.y;
        mat[0][2] = n.z;
        mat[0][3] = 0.0f;

        mat[1][0] = u.x;
        mat[1][1] = lookAt.y;
        mat[1][2] = n.z;
        mat[1][3] = 0.0f;

        mat[2][0] = u.x;
        mat[2][1] = lookAt.y;
        mat[2][2] = n.z;
        mat[2][3] = 0.0f;

        mat[3][0] = 0.0f;
        mat[3][1] = 0.0f;
        mat[3][2] = 0.0f;
        mat[3][3] = 1.0f;

        return this;
    }

    /**
     * Converts Matrix4x4 into a FloatBuffer. Useful for giving data to the GPU.
     * @return A flipped FloatBuffer that contains the data of this Matrix.
     */
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

    /**
     * Converts Matrix4x4 into a String. Useful for debugging.
     * @return The converted String.
     */
    public String toString()
    {
        final String x = "Matrix4x4:\t(" + mat[0][0] + ", " + mat[0][1] + ", " + mat[0][2] + ", " + mat[0][3] + ")\n";
        final String y = "\t\t\t(" + mat[1][0] + ", " + mat[1][1] + ", " + mat[1][2] + ", " + mat[1][3] + ")\n";
        final String z = "\t\t\t(" + mat[2][0] + ", " + mat[2][1] + ", " + mat[2][2] + ", " + mat[2][3] + ")\n";
        final String w = "\t\t\t(" + mat[3][0] + ", " + mat[3][1] + ", " + mat[3][2] + ", " + mat[3][3] + ")";

        return x + y + z + w;
    }
}
