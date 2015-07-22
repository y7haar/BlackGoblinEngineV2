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

package bge;

import bge.core.Mesh;
import bge.core.Vertex;
import bge.math.Vector3;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class TestGame
{
    Mesh mesh;

    public TestGame()
    {
        mesh = new Mesh();

        Vertex[] vertices = new Vertex[3];

        vertices[0] = new Vertex(new Vector3(-1, -1, 0));
        vertices[1] = new Vertex(new Vector3(0, 1, 0));
        vertices[2] = new Vertex(new Vector3(1, -1, 0));

        mesh.addVertices(vertices);
    }

    public void update()
    {
        mesh.render();
    }

}
