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

/**
 * Created by Yannic Siebenhaar on 26.07.2015.
 */
public class CameraTransform extends Transform
{

    private Matrix4x4 viewMatrix;
    private Matrix4x4 projectionMatrix;
    private Matrix4x4 translationMatrix;
    private Matrix4x4 rotationMatrix;


    public CameraTransform()
    {
        super();

        this.projectionMatrix = new Matrix4x4().getPerspectiveProjection(60.0f, 4.0f / 3.0f, 1.0f, 1000.0f);
        this.viewMatrix = new Matrix4x4();
        this.translationMatrix = new Matrix4x4();
        this.rotationMatrix = new Matrix4x4();
    }

    public Matrix4x4 getViewMatrix()
    {
        this.viewMatrix.identity();

        translationMatrix.getTranslation(getPosition().getNegated());
        rotationMatrix.getRotation(getRotation().getConjugated());

        //this.viewMatrix = viewMatrix.mul(translationMatrix.getTranslation(getPosition().getNegated()));
        //this.viewMatrix = viewMatrix.mul(rotationMatrix);
        //this.viewMatrix = viewMatrix.mul(translationMatrix.getTranslation(getPosition()));

        translationMatrix.getTranslation(getPosition().getNegated());

        Matrix4x4 negTranslation = new Matrix4x4().getTranslation(getPosition());

        viewMatrix = viewMatrix.mul(rotationMatrix).mul(translationMatrix);

        //this.viewMatrix = viewMatrix.mul(rotationMatrix).mul(translationMatrix);
        //this.viewMatrix = viewMatrix.mul(translationMatrix).mul(rotationMatrix);
        //this.viewMatrix = viewMatrix.mul(rotationMatrix);

        return this.viewMatrix;
    }

    public Matrix4x4 getProjectionMatrix()
    {
        return projectionMatrix;
    }

    public void setProjectionMatrix(Matrix4x4 projection)
    {
        this.projectionMatrix = projection;
    }
}
