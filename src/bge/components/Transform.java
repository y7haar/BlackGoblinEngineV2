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

package bge.components;

import bge.math.Matrix4x4;
import bge.math.Quaternion;
import bge.math.Vector3;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class Transform extends Component
{
    private Vector3 position;
    private Quaternion rotation;
    private Vector3 scale;

    private Matrix4x4 translationMatrix;
    private Matrix4x4 rotationMatrix;
    private Matrix4x4 scalingMatrix;
    private Matrix4x4 transformMatrix;

    public Transform()
    {
        this(null);
    }

    @Override
    public void update()
    {
        // Stays probably empty
    }

    public Transform(GameObject gameObject)
    {
        super(gameObject, "transform");

        this.position = new Vector3();
        this.rotation = new Quaternion(0, 0, 0, 1);
        this.scale = new Vector3(1, 1, 1);

        this.translationMatrix = new Matrix4x4();
        this.rotationMatrix = new Matrix4x4();
        this.scalingMatrix = new Matrix4x4();
        this.transformMatrix = new Matrix4x4();
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public void setPosition(Vector3 position)
    {
        this.position = position;
    }

    public Quaternion getRotation()
    {
        return rotation;
    }

    public void setRotation(Quaternion rotation)
    {
        this.rotation = rotation;
    }

    public Vector3 getScale()
    {
        return scale;
    }

    public void setScale(Vector3 scale)
    {
        this.scale = scale;
    }


    public void translate(Vector3 translation)
    {
        this.position = this.position.add(translation);
    }

    public void rotate(Vector3 euler)
    {
        Quaternion newRotation = new Quaternion().fromEulerAngles(euler);
        this.rotation = rotation.mul(newRotation);
    }

    public void rotate(Vector3 euler, boolean world)
    {
        if (!world)
        {
            Quaternion newRotation = new Quaternion().fromEulerAngles(euler);
            this.rotation = rotation.mul(newRotation);
        } else
        {
            //TODO: Implement local rotation
        }

    }

    public void scale(Vector3 scale)
    {
        this.scale = this.scale.add(scale);
    }


    public Matrix4x4 getTransformMatrix()
    {
        this.transformMatrix.identity();

        translationMatrix.getTranslation(position);
        rotationMatrix.getRotation(rotation);
        scalingMatrix.getScaling(scale);

        this.transformMatrix = transformMatrix.mul(translationMatrix).mul(rotationMatrix).mul(scalingMatrix);

        return this.transformMatrix;
    }
}