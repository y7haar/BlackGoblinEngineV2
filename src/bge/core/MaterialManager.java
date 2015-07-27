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

import java.util.HashMap;

/**
 * Created by Yannic Siebenhaar on 24.07.2015.
 */
public class MaterialManager
{
    // Singleton
    private static final String DEFAULT_MATERIAL_DIRECTORY = "game/shader/default";
    private static MaterialManager instance;


    private boolean defaultMaterialLoaded;
    private HashMap<String, Material> materials;
    private Material defaultMaterial;


    private MaterialManager()
    {
        // load default
        defaultMaterialLoaded = false;
        materials = new HashMap<>();
    }

    public static MaterialManager getInstance()
    {
        if (instance == null)
            instance = new MaterialManager();

        return instance;
    }

    public void addMaterial(Material material)
    {
        if (materials.containsKey(material.getName()))
        {
            System.err.println("Material with name already exists");
        } else
        {
            materials.put(material.getName(), material);

            //TODO: Find better place to set values
            material.addValue("model");
            material.addValue("view");
            material.addValue("projection");
        }
    }

    public Material getMaterial(String name)
    {
        if (!materials.containsKey(name))
        {
            System.err.println("Material with name does not exist");
        } else
        {
            return materials.get(name);
        }

        return getDefaultMaterial();
    }

    public Material getDefaultMaterial()
    {
        if (defaultMaterial == null && !defaultMaterialLoaded)
        {
            defaultMaterialLoaded = true;
            defaultMaterial = ResourceLoader.getInstance().loadMaterialPackage(DEFAULT_MATERIAL_DIRECTORY);
        } else if (defaultMaterialLoaded)
        {
            System.err.println("Unable to load default Shader, Program will close");
            System.exit(1);
        }

        return defaultMaterial;
    }
}
