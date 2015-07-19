package bge;

import bge.core.Engine;
import bge.core.Color;

public class Main
{
    public static void main(String[] args)
    {
        Color c = Color.CLEAR;

        System.out.println(c);

        Engine engine = Engine.getInstance();
        engine.run();
    }

}