package com.example.shoppinglist.viewmodel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.shoppinglist.data.Item;
import com.example.shoppinglist.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository repo;

    public ItemViewModel(@NonNull Application app) {
        super(app);
        repo = new ItemRepository(app);
    }

    public void addItem(Item item) {
        repo.insert(item);
    }

    public void deleteItem(Item item) {
        repo.delete(item);
    }

    public List<Item> getItems() {
        return repo.getAll();
    }
}