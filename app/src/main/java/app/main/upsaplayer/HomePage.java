package app.main.upsaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<Music> musicList;

    MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page); // updated layout

        recyclerView = findViewById(R.id.recyclerView);
        musicList = new ArrayList<>();

        // Dummy data
        musicList.add(new Music("Song A"));
        musicList.add(new Music("Song B"));

        adapter = new MusicAdapter(musicList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
