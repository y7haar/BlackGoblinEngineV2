package bge.core;

/**
 * Created by Yannic on 18.07.2015.
 */
public class Input
{
    private static InputController inputController;

    public static void init(Window window)
    {
        inputController = new InputController(window);
        inputController.start();
    }

    public static void close()
    {
        inputController.close();
    }
}
