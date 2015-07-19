package bge.core;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Yannic on 18.07.2015.
 */
public class InputController extends Thread
{
    private Window mainWindow;

    public InputController(Window window)
    {
        this.mainWindow = window;
    }

    @Override
    public void run()
    {
        while(! Thread.currentThread().isInterrupted())
        {
            if(glfwGetKey(mainWindow.getWindowHandler(), GLFW_KEY_E) == GLFW_PRESS)
                System.out.println("E pressed");

            try
            {
                Thread.sleep(1);
            }

            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void close()
    {
        this.interrupt();
    }
}
