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

import bge.components.Transform;
import bge.core.*;
import bge.math.*;
import bge.rendering.Mesh;
import bge.rendering.Shader;
import bge.rendering.Vertex;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class TestGame
{
    Mesh mesh;
    ShaderLoader sl;
    Shader shader;
    Transform transform;
    Transform t2;
    float newX = 0.0f;
    float newY = 0.0f;

    public TestGame()
    {
        mesh = new Mesh();
        sl = new ShaderLoader();
        shader = sl.loadShaderDirectory("game/shader/basic");

        Vertex[] vertices = new Vertex[3];

        vertices[0] = new Vertex(new Vector3(-1, -1, 0));
        vertices[1] = new Vertex(new Vector3(0, 1, 0));
        vertices[2] = new Vertex(new Vector3(1, -1, 0));

        transform = new Transform();
        t2 = new Transform();

        mesh.addVertices(vertices);

        shader.prepare();
        shader.addUniform("transform");

    }

    public void update()
    {
        newX += 0.5f * Time.getDelta();
        newY += 0.5f * Time.getDelta();

        System.out.println("DELTA  " + Time.getDelta());

        //transform.scale(new Vector3(-0.01f, -0.01f, -0.01f));
        transform.setPosition(new Vector3((float) Math.sin(newX), (float) Math.cos(newY), 0));
        //transform.translate(new Vector3(-0.1f, 0.0f, 0));
        //transform.setRotation(new Quaternion().fromEulerAngles(new Vector3(newY, newY, newX)));
        transform.rotate(new Vector3(0, 0, 10f * Time.getDelta()));

        shader.bind();
        mesh.render();
        shader.setUniform("transform", transform.getTransformMatrix());
    }

}
