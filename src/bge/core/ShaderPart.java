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

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.opengl.GL43.*;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class ShaderPart
{
    public static final int VERTEX_TYPE = GL_VERTEX_SHADER;
    public static final int GEOMETRY_TYPE = GL_GEOMETRY_SHADER;
    public static final int TESSELLATION_CONTROL_TYPE = GL_TESS_CONTROL_SHADER;
    public static final int TESSELLATION_EVALUATION_TYPE = GL_TESS_EVALUATION_SHADER;
    public static final int FRAGMENT_TYPE = GL_FRAGMENT_SHADER;
    public static final int COMPUTE_TYPE = GL_COMPUTE_SHADER;

    private int shaderType = -1;

    private int handle;
    private String source;

    public ShaderPart(String source, int type)
    {
        this.source = source;
        this.shaderType = type;
    }

    public void compile()
    {
        handle = glCreateShader(shaderType);

        if (handle == 0)
        {
            throw new IllegalStateException("ShaderPart could not be created");
        }

        glShaderSource(handle, source);
        glCompileShader(handle);

        if (glGetShaderi(handle, GL_COMPILE_STATUS) == 0)
        {
            System.err.println("Shader could not be compiled with id " + handle);
            System.err.println(glGetShaderInfoLog(handle));

            throw new IllegalStateException("ShaderPart could not be compiled");
        }
    }

    public int getShaderHandle()
    {
        return this.handle;
    }
}
