package com.example.shoppinglist.ui;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.*;

import com.example.shoppinglist.R;
import com.example.shoppinglist.data.Item;
import com.example.shoppinglist.viewmodel.ItemViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etPrice;
    private RecyclerView recyclerView;
    private TextView tvTotal;
    private FloatingActionButton fabAdd;

    private ItemViewModel viewModel;
    private ItemAdapter adapter;
    private List<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        tvTotal = findViewById(R.id.tvTotal);
        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        list = viewModel.getItems();

        adapter = new ItemAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updateTotal();

        fabAdd.setOnClickListener(v -> addItem());

        // Swipe для удаления
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; // не нужно перемещение
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Item item = list.get(position);
                viewModel.deleteItem(item);
                adapter.removeItem(position);
                updateTotal();
                Toast.makeText(MainActivity.this, "Товар удалён", Toast.LENGTH_SHORT).show();
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    private void addItem() {
        String name = etName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        try { price = Double.parseDouble(priceStr); } catch (NumberFormatException e) {
            Toast.makeText(this, "Неверная цена", Toast.LENGTH_SHORT).show();
            return;
        }

        Item item = new Item(name, price);
        viewModel.addItem(item);

        list.clear();
        list.addAll(viewModel.getItems());
        adapter.notifyDataSetChanged();

        updateTotal();

        etName.setText("");
        etPrice.setText("");
    }

    private void updateTotal() {
        double sum = 0;
        for (Item item : list) sum += item.price;
        tvTotal.setText("Итого: " + sum);
    }
}