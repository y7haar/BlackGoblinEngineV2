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

import bge.components.EngineComponent;
import org.lwjgl.glfw.*;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yannic Siebenhaar on 18.07.2015.
 */
public class Window extends EngineComponent
{
    private long window;
    private ByteBuffer videoMode;

    public Window(int width, int height, String title)
    {
        window = glfwCreateWindow(width, height, title, 0, 0);

        videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(this.window, (GLFWvidmode.width(videoMode) - width) / 2, (GLFWvidmode.height(videoMode) - height) / 2);

        glfwMakeContextCurrent(this.window);

        //Enable VSync = 1
        glfwSwapInterval(1);

        glfwShowWindow(this.window);
    }

    public Window()
    {
        this(1024, 768, "Black Goblin Engine");
    }

    public int getWidth()
    {
        return GLFWvidmode.width(videoMode);
    }

    public int getHeight()
    {
        return GLFWvidmode.height(videoMode);
    }

    public double getAspectRatio()
    {
        return GLFWvidmode.width(videoMode) / GLFWvidmode.height(videoMode);
    }

    @Override
    public void init()
    {

    }


    @Override
    public void update()
    {
        glfwSwapBuffers(this.window);
    }

    public boolean shouldClose()
    {
        return glfwWindowShouldClose(this.window) != GL_FALSE;
    }

    @Override
    public void close()
    {
        glfwDestroyWindow(this.window);
    }

    public long getWindowHandle()
    {
        return window;
    }

    public void setTitle(String title)
    {
        glfwSetWindowTitle(window, title);
    }
}
