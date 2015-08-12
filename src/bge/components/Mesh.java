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

package bge.rendering;

import java.nio.FloatBuffer;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class Mesh
{
    private int vertexBufferObject;
    private int vao;
    private FloatBuffer vertexBuffer;
    private int size;

    public Mesh()
    {
        this.vao = glGenVertexArrays();
        this.vertexBufferObject = glGenBuffers();
        this.vertexBuffer = null;
        this.size = 0;
    }

    public void addVertices(Vertex[] vertices)
    {
        glBindVertexArray(vao);

        this.vertexBuffer = createFloatBuffer(vertices.length * Vertex.SIZE);
        size = vertices.length;

        for (Vertex v : vertices)
        {
            this.vertexBuffer.put(v.getPosition().x);
            this.vertexBuffer.put(v.getPosition().y);
            this.vertexBuffer.put(v.getPosition().z);
        }
        this.vertexBuffer.flip();

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        glBufferData(GL_ARRAY_BUFFER, this.vertexBuffer, GL_STATIC_DRAW);
    }

    public void render()
    {
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * Float.BYTES, 0);

        glDrawArrays(GL_TRIANGLES, 0, size);

        glDisableVertexAttribArray(0);
    }
}
