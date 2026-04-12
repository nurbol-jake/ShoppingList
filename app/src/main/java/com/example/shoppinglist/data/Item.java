package com.example.shoppinglist.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}