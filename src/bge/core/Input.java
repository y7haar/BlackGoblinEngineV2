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

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Yannic Siebenhaar on 18.07.2015.
 */
public class Input
{
    private static Window window;

    private static HashMap<String, InputKey> keys;
    private static InputKey invalidKey;

    public static void init(Window window)
    {
        Input.window = window;
        keys = new HashMap<>();
        invalidKey = new InputKey(GLFW_KEY_UNKNOWN);

        // Alphabet
        keys.put("a", new InputKey(GLFW_KEY_A));
        keys.put("b", new InputKey(GLFW_KEY_B));
        keys.put("c", new InputKey(GLFW_KEY_C));
        keys.put("d", new InputKey(GLFW_KEY_D));
        keys.put("e", new InputKey(GLFW_KEY_E));
        keys.put("f", new InputKey(GLFW_KEY_F));
        keys.put("g", new InputKey(GLFW_KEY_G));
        keys.put("h", new InputKey(GLFW_KEY_H));
        keys.put("i", new InputKey(GLFW_KEY_I));
        keys.put("j", new InputKey(GLFW_KEY_J));
        keys.put("k", new InputKey(GLFW_KEY_K));
        keys.put("l", new InputKey(GLFW_KEY_L));
        keys.put("m", new InputKey(GLFW_KEY_M));
        keys.put("n", new InputKey(GLFW_KEY_N));
        keys.put("o", new InputKey(GLFW_KEY_O));
        keys.put("p", new InputKey(GLFW_KEY_P));
        keys.put("q", new InputKey(GLFW_KEY_Q));
        keys.put("r", new InputKey(GLFW_KEY_R));
        keys.put("s", new InputKey(GLFW_KEY_S));
        keys.put("t", new InputKey(GLFW_KEY_T));
        keys.put("u", new InputKey(GLFW_KEY_U));
        keys.put("v", new InputKey(GLFW_KEY_V));
        keys.put("w", new InputKey(GLFW_KEY_W));
        keys.put("x", new InputKey(GLFW_KEY_X));
        keys.put("y", new InputKey(GLFW_KEY_Y));
        keys.put("z", new InputKey(GLFW_KEY_Z));

        // Numbers
        keys.put("1", new InputKey(GLFW_KEY_1));
        keys.put("2", new InputKey(GLFW_KEY_2));
        keys.put("3", new InputKey(GLFW_KEY_3));
        keys.put("4", new InputKey(GLFW_KEY_4));
        keys.put("5", new InputKey(GLFW_KEY_5));
        keys.put("6", new InputKey(GLFW_KEY_6));
        keys.put("7", new InputKey(GLFW_KEY_7));
        keys.put("8", new InputKey(GLFW_KEY_8));
        keys.put("9", new InputKey(GLFW_KEY_9));
        keys.put("0", new InputKey(GLFW_KEY_0));

        // Arrow keys
        keys.put("up", new InputKey(GLFW_KEY_UP));
        keys.put("down", new InputKey(GLFW_KEY_DOWN));
        keys.put("left", new InputKey(GLFW_KEY_LEFT));
        keys.put("right", new InputKey(GLFW_KEY_RIGHT));

        // Modifiers
        keys.put("left shift", new InputKey(GLFW_KEY_LEFT_SHIFT));
        keys.put("right shift", new InputKey(GLFW_KEY_RIGHT_SHIFT));
        keys.put("left ctrl", new InputKey(GLFW_KEY_LEFT_CONTROL));
        keys.put("right ctrl", new InputKey(GLFW_KEY_RIGHT_CONTROL));
        keys.put("left alt", new InputKey(GLFW_KEY_LEFT_ALT));
        keys.put("right alt", new InputKey(GLFW_KEY_RIGHT_ALT));

        // Special Keys
        keys.put("backspace", new InputKey(GLFW_KEY_BACKSPACE));
        keys.put("tab", new InputKey(GLFW_KEY_TAB));
        keys.put("return", new InputKey(GLFW_KEY_KP_ENTER)); // Return on Numpad
        keys.put("escape", new InputKey(GLFW_KEY_ESCAPE));
        keys.put("space", new InputKey(GLFW_KEY_SPACE));
        keys.put("delete", new InputKey(GLFW_KEY_DELETE));
        keys.put("insert", new InputKey(GLFW_KEY_INSERT));
        keys.put("home", new InputKey(GLFW_KEY_HOME));
        keys.put("end", new InputKey(GLFW_KEY_END));
        keys.put("page up", new InputKey(GLFW_KEY_PAGE_UP));
        keys.put("page down", new InputKey(GLFW_KEY_PAGE_DOWN));
        keys.put("enter", new InputKey(GLFW_KEY_ENTER));


        //TODO: Add missing keys for Keypad and FN Keys and some special keys
    }

    public static void close()
    {

    }

    public static boolean getKey(String key)
    {
        return keys.getOrDefault(key, invalidKey).isPressed();
    }

    public static boolean getKeyDown(String key)
    {
        return keys.getOrDefault(key, invalidKey).isDown();
    }

    public static boolean getKeyUp(String key)
    {
        return keys.getOrDefault(key, invalidKey).isUp();
    }

    public static void update()
    {
        glfwPollEvents();

        //TODO: Remove Hack and implement Profile for Debugging
        if (Input.window.shouldClose() || getKeyDown("escape"))
        {
            Engine.getInstance().quit();
        }

        for (InputKey current : keys.values())
        {
            if (glfwGetKey(window.getWindowHandle(), current.getKeyCode()) == GLFW_PRESS)
            {
                // Was not pressed last Frame
                if (!current.isPressed())
                    current.setDown(true);

                else
                    current.setDown(false);

                current.setPressed(true);
                current.setUp(false);
            } else
            {
                // Was pressed last Frame
                if (current.isPressed())
                    current.setUp(true);

                else
                    current.setUp(false);

                current.setPressed(false);
                current.setDown(false);
            }
        }

    }
}
