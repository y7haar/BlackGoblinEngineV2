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

package bge.core;

import bge.components.Camera;
import bge.components.EngineComponent;
import bge.components.GameObject;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by Yannic Siebenhaar on 22.07.2015.
 */
public class RenderController extends EngineComponent
{
    private static RenderController instance;

    private ArrayList<GameObject> gameObjects;
    private ArrayList<Camera> cameras;

    private Camera currentCamera;
    private Material currentMaterial;

    public static RenderController getInstance()
    {
        if (instance == null)
            instance = new RenderController();

        return instance;
    }

    private RenderController()
    {
    }

    @Override
    public void init()
    {
        //glFrontFace(GL_CW);
        //glCullFace(GL_BACK);
        //glEnable(GL_CULL_FACE);

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_FRAMEBUFFER_SRGB);

        gameObjects = new ArrayList<>();
        cameras = new ArrayList<>(1);

        currentCamera = null;
        currentMaterial = null;
    }

    public void addCamera(Camera camera)
    {
        cameras.add(camera);
    }

    @Override
    public void update()
    {
        for (Camera c : cameras)
        {
            currentCamera = c;
            c.update();

            for (GameObject o : gameObjects)
            {
                o.update();
            }
        }

    }

    @Override
    public void close()
    {

    }

    public void addGameObject(GameObject o)
    {
        gameObjects.add(o);
    }

    public Camera getCurrentCamera()
    {
        return currentCamera;
    }

    public Material getCurrentMaterial()
    {
        return currentMaterial;
    }

    public void setCurrentMaterial(Material currentMaterial)
    {
        this.currentMaterial = currentMaterial;
    }
}
