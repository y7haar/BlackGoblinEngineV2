package bge.core;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yannic on 18.07.2015.
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
