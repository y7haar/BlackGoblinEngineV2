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

import bge.TestGame;
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

    private RenderController renderController;

    private GLFWErrorCallback errorCallback;
    private Window mainWindow;
    private boolean running = false;

    // TODO: Remove Game out of Engine
    private TestGame game;

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
        GLContext.createFromCurrent();
        Input.init(mainWindow);

        this.renderController = new RenderController();
        this.renderController.init();


        //TODO: Remove Game class out of Engine
        game = new TestGame();
    }

    public void run()
    {
        this.running = true;

        GLContext.createFromCurrent();

        while(running)
        {
            this.updateEarly();
            this.update();
            this.updateLate();

            try
            {
                Thread.sleep(1);
            }

            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        close();
    }

    private void update()
    {
        //TODO: Remove Game
        System.out.println(Time.getFramesPerSecond());
        game.update();
    }

    private void updateEarly()
    {
        Time.updateEarly();

        Input.update();

        renderController.update();
    }

    private void updateLate()
    {
        this.mainWindow.update();
        Time.updateLate();
    }

    public void close()
    {
        renderController.close();
        Input.close();

        mainWindow.close();

        glfwTerminate();
        errorCallback.release();
    }

    public void quit()
    {
        this.running = false;
    }
}
