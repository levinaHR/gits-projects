package com.example.tugas7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[] subjects = {
            "Dragons Dogma: Dark Arisen", "Final Fantasy IX & XV", "Kingdom Come: Deliverance", "Monster Hunter World",
            "Monster Hunter 4 Ultimate", "Red Dead Redemption 2", "TEKKEN 7", "Assassin's Creed Series", "The Sims Series",
            "Lego Series", "The Legend of Zelda: Breath of the Wild", "The Elder Scrolls V Skyrim", "Infamous Second Son",
            "Tomb Raider 2013", "Trine Series", "Rune Factory 3 & 4", "Harvest Moon/Story of Seasons Series",
            "I don't remember what else :p"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerView);
        recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerViewAdapter = new AdapterRecyclerView(context, subjects);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}