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

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Yannic Siebenhaar on 18.07.2015.
 */
public class Input
{
    private static InputController inputController;
    private static Window window;

    public static void init(Window window)
    {
        Input.window = window;
        inputController = new InputController(window);
    }

    public static void close()
    {

    }

    public static void update()
    {
        glfwPollEvents();

        //TODO: Remove Hack and implement Profile for Debugging

        System.out.println(window.getWindowHandler());

        if (glfwGetKey(Input.window.getWindowHandler(), GLFW_KEY_ESCAPE) == 1 || Input.window.shouldClose())
        {
            Engine.getInstance().quit();
        }

    }
}
