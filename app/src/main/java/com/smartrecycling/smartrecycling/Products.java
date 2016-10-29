package com.smartrecycling.smartrecycling;

/**
 * Created by OP on 10/29/2016.
 */

public class Products {
    public String id;
    public String material;
    public String name;
    public String content;

    public Products()
    {

    }


    public Products(String id, String material, String name, String content)
    {
        this.material = material;
        this.name = name;
        this.content = content;
        this.id = id;
    }
}
