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

import bge.components.Mesh;

import java.io.*;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class ResourceLoader
{
    // Singleon
    private static ResourceLoader instance;

    private static final String VERTEX_EXTENSION = ".vs";
    private static final String FRAGMENT_EXTENSION = ".fs";

    private ResourceLoader()
    {
    }

    public static ResourceLoader getInstance()
    {
        if (instance == null)
            instance = new ResourceLoader();

        return instance;
    }

    public Material loadMaterialPackage(String directory)
    {
        Shader shader = loadShaderPackage(directory);

        try
        {
            shader.prepare();
        }

        catch (Exception e)
        {
            return MaterialManager.getInstance().getDefaultMaterial();
        }

        String splits[] = directory.split("/");
        String name = splits[splits.length - 1];

        Material material = new Material(name);

        material.setShader(shader);
        MaterialManager.getInstance().addMaterial(material);

        return material;
    }

    private Shader loadShaderPackage(String directory)
    {
        Shader shader = new Shader();

        // Shader must have same fileName as parent folder
        String splits[] = directory.split("/");
        String name = splits[splits.length - 1];

        File dir = new File(directory);


        File files[] = dir.listFiles();

        for (File f : files)
        {
            if (f.getName().endsWith(name + VERTEX_EXTENSION))
            {
                ShaderPart vertexShader = loadShaderPart(f.getPath(), ShaderPart.VERTEX_TYPE);
                shader.setVertexShader(vertexShader);
            } else if (f.getName().endsWith(name + FRAGMENT_EXTENSION))
            {
                ShaderPart fragmentShader = loadShaderPart(f.getPath(), ShaderPart.FRAGMENT_TYPE);
                shader.setFragmentShader(fragmentShader);
            }
        }

        return shader;
    }

    private ShaderPart loadShaderPart(String directory, int type)
    {
        return new ShaderPart(readText(directory), type);
    }

    public Mesh loadMesh()
    {
        //TODO: Implement parser for Obj Files
        return null;
    }

    // TODO: Other Types

    private String readText(String directory)
    {
        String text = "";


        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(directory)));

            String line = reader.readLine();

            while (line != null)
            {
                text += "\n" + line;
                line = reader.readLine();
            }

            reader.close();
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        return text;
    }
}
