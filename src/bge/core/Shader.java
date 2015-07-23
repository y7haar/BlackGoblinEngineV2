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

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class Shader
{
    private int program;
    private VertexShader vertexShader;
    private FragmentShader fragmentShader;

    // TODO: Support other Shader types

    public Shader()
    {
        this.vertexShader = null;
        this.fragmentShader = null;

        this.program = glCreateProgram();

        if (program == 0)
        {
            System.err.println("Shader could not be created");
        }
    }

    public void setVertexShader(VertexShader shader)
    {
        this.vertexShader = shader;
    }

    public void setFragmentShader(FragmentShader shader)
    {
        this.fragmentShader = shader;
    }

    // TODO: Add methods for setting other shader types

    private void compile()
    {
        if (vertexShader != null)
        {
            vertexShader.compile();
            glAttachShader(program, vertexShader.getShaderHandle());
        }

        if (fragmentShader != null)
        {
            System.out.println("ASKDLKASJDK");
            fragmentShader.compile();
            glAttachShader(program, fragmentShader.getShaderHandle());
        }

        //TODO: Other Types
    }

    private void link()
    {
        glLinkProgram(program);

        if (glGetProgrami(program, GL_LINK_STATUS) == 0)
        {
            System.err.println("Linking failed");
            System.err.println(glGetShaderInfoLog(program));
            //TODO: Exception
        }

        glValidateProgram(program);

        if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0)
        {
            System.err.println("Shader not valid");
            System.err.println(glGetShaderInfoLog(program));
        }
    }

    public void prepare()
    {
        compile();
        link();
    }

    public void bind()
    {
        glUseProgram(program);
    }
}
