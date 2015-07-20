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

package bge;

import bge.core.Engine;
import bge.core.Color;
import bge.math.Matrix4x4;
import bge.math.*;

/**
 * Created by Yannic Siebenhaar on 18.07.2015.
 */
public class Main
{
    public static void main(String[] args)
    {
        Matrix4x4 mat = new Matrix4x4();
        mat.set(0, 0, 1.0f);
        mat.set(2, 0, 4.0f);

        System.out.println(mat);

        Vector3 v = new Vector3(-10.0f, 5.0f, 0.0f);
        System.out.println(v.normalize());

        Quaternion q = new Quaternion().fromEulerAngles(new Vector3(12.0f, 24.0f, 80.0f));
        System.out.println(q);

        Engine engine = Engine.getInstance();
        engine.run();
    }

}