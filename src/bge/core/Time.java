package bge.core;

/**
 * Created by Yannic on 18.07.2015.
 */
public class Time
{
    private static final long SECOND = 1000000000L;
    private static final long START_TIME = System.nanoTime();

    private static double delta = 0;
    private static long frameCount = 0;
    private static double fps = 0;


    public static long getTime()
    {
        return System.nanoTime() - START_TIME;
    }

    public static double getDelta()
    {
        return Time.delta;
    }

    public static void calculateDelta(long startTime)
    {
        Time.delta = Time.getTime() - startTime;
    }

    public static void calculateFps()
    {
        Time.fps = Time.SECOND / Time.delta;
    }

    public static double getFps()
    {
        return fps;
    }

    public static long getFrameCount()
    {
        return frameCount;
    }

    public static void incrementFrameCount()
    {
        Time.frameCount++;
    }
}
