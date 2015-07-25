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
    private static final int VERTEX_SHADER = 0;
    private static final int TESSELLATION_CONTROL_SHADER = 1;
    private static final int TESSELLATION_EVALUATION_SHADER = 2;
    private static final int GEOMETRY_SHADER = 3;
    private static final int FRAGMENT_SHADER = 4;
    private static final int COMPUTE_SHADER = 5;

    private static final int SHADER_PART_LENGTH = 6;


    private int program;
    private ShaderPart[] shaderParts;

    public Shader()
    {
        this.program = glCreateProgram();

        if (program == 0)
        {
            throw new IllegalStateException("ShaderProgram could not be created");
        }

        this.shaderParts = new ShaderPart[SHADER_PART_LENGTH];
    }

    public void setVertexShader(ShaderPart shader)
    {
        this.shaderParts[VERTEX_SHADER] = shader;
    }

    public void setTessellationControlShader(ShaderPart shader)
    {
        this.shaderParts[TESSELLATION_CONTROL_SHADER] = shader;
    }

    public void setTessellationEvaluationShader(ShaderPart shader)
    {
        this.shaderParts[TESSELLATION_EVALUATION_SHADER] = shader;
    }

    public void setGeometryShader(ShaderPart shader)
    {
        this.shaderParts[GEOMETRY_SHADER] = shader;
    }

    public void setFragmentShader(ShaderPart shader)
    {
        this.shaderParts[FRAGMENT_SHADER] = shader;
    }

    public void setComputeShader(ShaderPart shader)
    {
        this.shaderParts[COMPUTE_SHADER] = shader;
    }

    private void compile()
    {
        for (ShaderPart sp : shaderParts)
        {
            if (sp != null)
            {
                sp.compile();
                glAttachShader(program, sp.getShaderHandle());
            }
        }
    }

    private void link() throws Exception
    {
        glLinkProgram(program);

        if (glGetProgrami(program, GL_LINK_STATUS) == 0)
        {
            System.err.println("Linking failed for Shader with id " + program);
            System.err.println(glGetShaderInfoLog(program));

            throw new Exception("Linking failed for Shader with id " + program);
        }

        glValidateProgram(program);

        if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0)
        {
            System.err.println("Shader invalid with id " + program);
            System.err.println(glGetShaderInfoLog(program));

            throw new Exception("Validation failed for Shader with id " + program);
        }
    }

    private void cleanUp()
    {
        for (ShaderPart sp : shaderParts)
        {
            if (sp != null)
            {
                glDetachShader(program, sp.getShaderHandle());
                glDeleteShader(sp.getShaderHandle());
            }
        }
    }

    public void prepare() throws Exception
    {
        compile();
        link();
        cleanUp();
    }

    public void bind()
    {
        glUseProgram(program);
    }

    public int getProgramHandle()
    {
        return program;
    }
}
