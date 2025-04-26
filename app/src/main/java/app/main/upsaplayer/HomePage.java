package app.main.upsaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;




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


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Search songs...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                // When user presses search button
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                List<Music> filteredList = new ArrayList<>();

                for (Music music : musicList)
                {
                    if (music.getTitle().toLowerCase().contains(newText.toLowerCase()))
                    {
                        filteredList.add(music);
                    }
                }

                adapter = new MusicAdapter(filteredList);
                recyclerView.setAdapter(adapter);

                return true;
            }

        });

        return true;
    }






}
