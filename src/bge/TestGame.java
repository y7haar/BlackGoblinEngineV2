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
import bge.components.RenderContent;
import bge.core.*;
import bge.math.*;

import static org.lwjgl.glfw.GLFW.glfwGetKey;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class TestGame
{
    Mesh mesh;
    ResourceLoader sl;
    Material material;
    GameObject g1, g2;
    Camera camera;

    public TestGame()
    {
        camera = new Camera();
        camera.setBackgroundColor(Color.WHITE);
        RenderController.getInstance().addCamera(camera);

        g1 = new GameObject();
        g2 = new GameObject();


        //mesh = ResourceLoader.getInstance().loadMesh("game/models/m.obj");

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

        g1.getTransform().setPosition(new Vector3(0, 0, 5f));
        //g2.getTransform().setPosition(new Vector3(0.5f, 0, 0));

        //RenderController.getInstance().addGameObject(g1);
        //RenderController.getInstance().addGameObject(g2);

        for (int i = 0; i < 20; ++i)
        {
            for (int j = 0; j < 20; ++j)
            {
                GameObject go = new GameObject();
                go.getRenderer().setRenderContent(mesh);
                go.getRenderer().setMaterial(material);

                go.getTransform().setPosition(new Vector3(5 * i, 0, 5 * j));

                RenderController.getInstance().addGameObject(go);
            }
        }

    }

    public void update()
    {
        if (Input.getKeyUp("c"))
            camera.setBackgroundColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

        if (Input.getKey("w"))
            camera.getTransform().translate(Vector3.FORWARD.mul(10).mul(Time.getDelta()));

        if (Input.getKey("a"))
            camera.getTransform().translate(Vector3.LEFT.mul(10).mul(Time.getDelta()));

        if (Input.getKey("s"))
            camera.getTransform().translate(Vector3.BACK.mul(10).mul(Time.getDelta()));

        if (Input.getKey("d"))
            camera.getTransform().translate(Vector3.RIGHT.mul(10).mul(Time.getDelta()));

        if (Input.getKey("left shift"))
            camera.getTransform().translate(Vector3.DOWN.mul(10).mul(Time.getDelta()));

        if (Input.getKey("space"))
            camera.getTransform().translate(Vector3.UP.mul(10).mul(Time.getDelta()));


        if (Input.getKey("left"))
            camera.getTransform().rotate(Vector3.UP.mul(100).mul(Time.getDelta()));

        if (Input.getKey("right"))
            camera.getTransform().rotate(Vector3.DOWN.mul(100).mul(Time.getDelta()));

        if (Input.getKey("up"))
            camera.getTransform().rotate(Vector3.LEFT.mul(100).mul(Time.getDelta()));

        if (Input.getKey("down"))
            camera.getTransform().rotate(Vector3.RIGHT.mul(100).mul(Time.getDelta()));

        //g1.getTransform().rotate(Vector3.LEFT.mul(Time.getDelta() * 100.0f));
        //g2.getTransform().rotate(new Vector3(0, 400 * Time.getDelta(), 0));
        //g2.getTransform().translate(Vector3.LEFT.mul(Time.getDelta()));
    }

}
