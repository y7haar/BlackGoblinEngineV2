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
import bge.math.Vector3;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Yannic Siebenhaar on 26.07.2015.
 */
public class ObjFormatParser
{
    private String text;
    private String current;
    private int index;

    private Mesh mesh;
    private ArrayList<Vertex> vertices;
    private ArrayList<Integer> indices;

    public ObjFormatParser(String text)
    {
        this.index = 0;
        this.text = text;


        this.mesh = new Mesh();
        this.vertices = new ArrayList<>();
        this.indices = new ArrayList<>();
    }

    private void parseVertex(String s)
    {
        String[] splits = s.split("\\s+");

        float x = Float.parseFloat(splits[1]);
        float y = Float.parseFloat(splits[2]);
        float z = Float.parseFloat(splits[3]);

        Vertex v = new Vertex(new Vector3(x, y, z));
        vertices.add(v);
    }

    private void parseVertexNormal(String s)
    {
        //TODO: Parse Normals
    }

    private void parseVertexTexture(String s)
    {
        // TODO: Parse Textures
    }

    private void parseFace(String face)
    {
        String[] splits = face.split("\\s+");

        int vertexValues[] = new int[3];


        for (int i = 1; i < 4; ++i)
        {
            String[] slashes = splits[i].split("/");

            if (!slashes[0].isEmpty())
                vertexValues[i - 1] = Integer.parseInt(slashes[0]);
        }

        indices.add(vertexValues[0]);
        indices.add(vertexValues[1]);
        indices.add(vertexValues[2]);
    }

    public Mesh parse()
    {
        String lines[] = text.split("\\r?\\n");

        for (String s : lines)
        {
            s = s.trim();

            if (s.startsWith("vn"))
            {
                parseVertexNormal(s);
            }

            else if (s.startsWith("vt"))
            {
                parseVertexTexture(s);
            }

            else if (s.startsWith("v"))
            {
                parseVertex(s);
            }

            else if (s.startsWith("f"))
            {
                parseFace(s);
            }
        }

        Vertex[] vertexArr = new Vertex[vertices.size()];
        vertexArr = vertices.toArray(vertexArr);

        int[] indexArr = indices.stream().mapToInt(i -> i).toArray();
        mesh.addVertices(vertexArr, indexArr);

        for (Vertex v : vertexArr)
        {
            System.out.println(v);
        }

        for (int i = 0; i < indexArr.length; i += 3)
        {
            System.out.println("" + indexArr[i] + " " + indexArr[i + 1] + " " + indexArr[i + 2]);
        }

        System.out.println(indexArr.length);
        System.out.println(vertexArr.length);

        return mesh;
    }
}
