package bge;

import bge.core.Engine;
import bge.core.Color;
import bge.math.Matrix4x4;
import bge.math.Vector2;

public class Main
{
    public static void main(String[] args)
    {
        Matrix4x4 mat = new Matrix4x4();
        mat.set(0, 0, 1.0f);
        mat.set(2, 0, 4.0f);

        System.out.println(mat);

        Engine engine = Engine.getInstance();
        engine.run();
    }

}