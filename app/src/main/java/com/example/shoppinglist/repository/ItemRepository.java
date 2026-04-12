package com.example.shoppinglist.repository;
import android.content.Context;
import androidx.room.Room;

import com.example.shoppinglist.data.AppDatabase;
import com.example.shoppinglist.data.Item;
import com.example.shoppinglist.data.ItemDao;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;

    public ItemRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(
                context,
                AppDatabase.class,
                "shopping_db"
        ).allowMainThreadQueries().build();

        itemDao = db.itemDao();
    }

    public void insert(Item item) {
        itemDao.insert(item);
    }

    public void delete(Item item) {
        itemDao.delete(item);
    }

    public List<Item> getAll() {
        return itemDao.getAll();
    }
}