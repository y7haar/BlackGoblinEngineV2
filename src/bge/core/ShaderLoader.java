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

import java.io.*;

/**
 * Created by Yannic Siebenhaar on 23.07.2015.
 */
public class ShaderLoader
{
    private static final String VERTEX_EXTENSION = "vs";
    private static final String FRAGMENT_EXTENSION = "fs";


    public Shader loadShaderDirectory(String directory)
    {
        Shader shader = new Shader();

        File dir = new File(directory);

        System.out.println(dir.getAbsolutePath());

        File files[] = dir.listFiles();

        for (File f : files)
        {
            if (f.getName().endsWith(VERTEX_EXTENSION))
            {
                VertexShader vertexShader = loadVertexShader(f.getPath());
                shader.setVertexShader(vertexShader);
            } else if (f.getName().endsWith(FRAGMENT_EXTENSION))
            {
                FragmentShader fragmentShader = loadFragmentShader(f.getPath());
                shader.setFragmentShader(fragmentShader);
            }
        }

        return shader;
    }

    public VertexShader loadVertexShader(String directory)
    {
        return new VertexShader(readText(directory));
    }

    public FragmentShader loadFragmentShader(String directory)
    {
        return new FragmentShader(readText(directory));
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
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return text;
    }
}
