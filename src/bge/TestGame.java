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

import bge.components.Camera;
import bge.components.GameObject;
import bge.components.Mesh;
import bge.core.*;
import bge.math.*;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class TestGame
{
    Mesh mesh;
    ResourceLoader sl;
    Material material;
    GameObject g1, g2;
    Camera c;

    public TestGame()
    {
        g1 = new GameObject();
        g2 = new GameObject();

        c = new Camera();

        mesh = new Mesh();
        sl = ResourceLoader.getInstance();
        material = sl.loadMaterialPackage("game/shader/basic");

        Vertex[] vertices = new Vertex[]{
                new Vertex(new Vector3(0, 1.7f, 0)),
                new Vertex(new Vector3(1, 0, -1)),
                new Vertex(new Vector3(1, 0, 1)),
                new Vertex(new Vector3(-1, 0, 1)),
                new Vertex(new Vector3(-1, 0, -1))
        };

        int indices[] = new int[]{0, 2, 1,
                0, 3, 2,
                0, 4, 3,
                0, 1, 4,
                1, 2, 3,
                1, 3, 4};


        mesh.addVertices(vertices, indices);

        g1.getRenderer().setRenderContent(mesh);
        g1.getRenderer().setMaterial(material);

        g2.getRenderer().setRenderContent(mesh);
        g2.getRenderer().setMaterial(material);

        g1.getTransform().setPosition(new Vector3(-0.5f, 0, 0));
        g2.getTransform().setPosition(new Vector3(0.5f, 0, 0));

        RenderController.getInstance().addGameObject(g1);
        RenderController.getInstance().addGameObject(g2);
    }

    public void update()
    {
        g1.getTransform().rotate(new Vector3(0, 10 * Time.getDelta(), 0));
        //g2.getTransform().rotate(new Vector3(0, 400 * Time.getDelta(), 0));
        //g2.getTransform().translate(Vector3.FORWARD.mul(Time.getDelta()));
    }

}
