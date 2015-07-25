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

import bge.math.Matrix4x4;
import bge.math.Vector3;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

/**
 * Created by Yannic Siebenhaar on 24.07.2015.
 */
public class Material
{
    // If Shaders have LOD stages, this class will hold multiple Shaders
    private Shader shader;
    private String name;

    private HashMap<String, Integer> uniformLocations;
    private HashMap<Integer, Object> uniformValues;

    /**
     * Constructor for a new Material
     *
     * @param name The name of the new material, each material must have a unique name
     */
    public Material(String name)
    {
        this.name = name;

        this.uniformLocations = new HashMap<>();
        this.uniformValues = new HashMap<>();
    }

    public void setShader(Shader shader)
    {
        this.shader = shader;
    }

    public Shader getShader()
    {
        return shader;
    }

    public String getName()
    {
        return name;
    }

    /**
     * This method sets the material to the current material
     */
    public void use()
    {
        shader.bind();
    }

    public void update()
    {
        // This method will bind the correct Shader (LOD) if not already set and give all setted data to the Shader
    }

    public void addValue(String name)
    {
        int location = glGetUniformLocation(shader.getProgramHandle(), name);

        if (location < 0)
        {
            System.err.println("No uniform in Shader with name " + name);
            //TODO: Exception
        }

        uniformLocations.put(name, location);
    }

    // Setters for Uniforms

    public void setInt(String name, int value)
    {
        Integer location = uniformLocations.get(name);
        uniformValues.put(location, value);

        //TODO: Needs to be removed later
        glUniform1i(uniformLocations.get(name), value);
    }

    public void setFloat(String name, float value)
    {
        Integer location = uniformLocations.get(name);
        uniformValues.put(location, value);

        //TODO: Needs to be removed later
        glUniform1f(uniformLocations.get(name), value);
    }

    public void setVector3(String name, Vector3 value)
    {
        Integer location = uniformLocations.get(name);
        uniformValues.put(location, value);

        //TODO: Needs to be removed later
        glUniform3f(uniformLocations.get(name), value.x, value.y, value.z);
    }

    public void setMatrix4x4(String name, Matrix4x4 value)
    {
        Integer location = uniformLocations.get(name);
        uniformValues.put(location, value);

        //TODO: Needs to be removed later
        glUniformMatrix4fv(uniformLocations.get(name), true, value.toFloatBuffer());
    }
}
