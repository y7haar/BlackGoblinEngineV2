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

/**
 * Created by Yannic Siebenhaar on 18.07.2015.
 */
public class Time
{
    private static final long SECOND = 1000000000L;
    private static final long ENGINE_START_TIME = System.nanoTime();

    private static long startTime = 0;
    private static long endTime = 0;

    private static double delta = 0;
    private static long frameCount = 0;
    private static double fps = 0;


    public static long getTime()
    {
        return System.nanoTime() - ENGINE_START_TIME;
    }

    public static double getDelta()
    {
        return Time.delta;
    }

    public static double getFramesPerSecond()
    {
        return fps;
    }

    public static long getFrameCount()
    {
        return frameCount;
    }


    public static void updateEarly()
    {
        startTime = Time.getTime();
    }

    public static void updateLate()
    {
        endTime = Time.getTime();

        Time.delta = endTime - startTime;
        Time.fps = Time.SECOND / Time.delta;

        Time.frameCount++;
    }

}
