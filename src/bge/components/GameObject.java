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

package bge.components;

import java.util.HashMap;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class GameObject
{
    private HashMap<String, Component> components;
    private Transform transform;
    private Renderer renderer;

    public GameObject()
    {
        this.components = new HashMap<>();
        this.transform = new Transform(this);
        this.renderer = new MeshRenderer(this);

        addComponent(transform);
        addComponent(renderer);
    }

    public Transform getTransform()
    {
        return this.transform;
    }

    public void addComponent(Component component)
    {
        component.setGameObject(this);
        components.put(component.getName(), component);
    }

    public Component getComponent(String component)
    {
        return components.get(component);
    }

    public Renderer getRenderer()
    {
        return renderer;
    }

    public void setRenderer(Renderer renderer)
    {
        this.renderer = renderer;
    }

    public void update()
    {
        for (Component c : components.values())
        {
            // Update all components

            if (c.isEnabled())
                c.update();
        }
    }
}
