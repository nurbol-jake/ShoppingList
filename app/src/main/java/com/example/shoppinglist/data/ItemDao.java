package com.example.shoppinglist.data;
import androidx.room.*;
import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Item item);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM Item")
    List<Item> getAll();
}