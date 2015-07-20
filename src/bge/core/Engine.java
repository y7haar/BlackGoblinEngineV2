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

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yannic Siebenhaar on 18.07.2015.
 */
public class Engine
{
    private static Engine instance = new Engine();

    private GLFWErrorCallback errorCallback;
    private Window mainWindow;
    private boolean running = false;

    private long frameStartTime = 0;

    public static Engine getInstance()
    {
        return instance;
    }

    private Engine()
    {
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        if(glfwInit() != GL_TRUE)
        {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        this.mainWindow = new Window();
        Input.init(mainWindow);
    }

    public void run()
    {
        this.running = true;

        GLContext.createFromCurrent();

        while(running)
        {
            this.update();

            try
            {
                Thread.sleep(1);
            }

            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        this.close();
    }

    private void update()
    {
        running = ! this.mainWindow.shouldClose();


        this.updateTimeEarly();

        this.mainWindow.update();
        glfwPollEvents();

        this.updateTimeLate();

        System.out.println(Time.getFps());
    }

    private void updateTimeLate()
    {
        Time.incrementFrameCount();
        Time.calculateDelta(frameStartTime);
        Time.calculateFps();
    }

    private void updateTimeEarly()
    {
        frameStartTime = Time.getTime();
    }

    public void close()
    {
        Input.close();

        this.mainWindow.close();

        glfwTerminate();
        this.errorCallback.release();
    }
}
