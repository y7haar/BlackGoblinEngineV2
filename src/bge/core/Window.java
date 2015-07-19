package bge.core;

import org.lwjgl.glfw.*;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yannic on 18.07.2015.
 */
public class Window
{
    private long window;

    private void init(int width, int height, String title)
    {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        window = glfwCreateWindow(width, height, title, 0, 0);

        ByteBuffer videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(this.window, (GLFWvidmode.width(videoMode) - width) / 2, (GLFWvidmode.height(videoMode) - height) / 2);

        glfwMakeContextCurrent(this.window);

        //Enable VSync = 1
        glfwSwapInterval(0);

        glfwShowWindow(this.window);
    }

    public Window(int width, int height, String title)
    {
        init(width, height, title);
    }

    public Window()
    {
        init(800, 600, "Window");
    }

    public void update()
    {
        glfwSwapBuffers(this.window);
    }

    public boolean shouldClose()
    {
        return glfwWindowShouldClose(this.window) != GL_FALSE;
    }

    public void close()
    {
        glfwDestroyWindow(this.window);
    }

    public long getWindowHandler()
    {
        return window;
    }
}
