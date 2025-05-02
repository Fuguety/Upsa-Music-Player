package app.main.upsaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.SearchView;




import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<Music> musicList;

    MusicAdapter adapter;

    // Main funtion
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);          // Setup
        setContentView(R.layout.activity_home_page); // Selected Page xml

        recyclerView = findViewById(R.id.recyclerView);
        musicList = new ArrayList<>();

        // Dummy data
        musicList.add(new Music("Song A"));
        musicList.add(new Music("Song B"));
        musicList.add(new Music("Song C"));

        adapter = new MusicAdapter(musicList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        addMusic(findViewById(R.id.fab_add));
    }


    public void addMusic(View view)
    {
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // get current list
                musicList = adapter.getMusicList();

                // find the next number
                int nextNumber = 1;
                for (Music music : musicList)
                {
                    String title = music.getTitle();
                    if (title.startsWith("Song ") && title.length() > 5)
                    {
                        try
                        {
                            int number = Integer.parseInt(title.substring(5));
                            if (number >= nextNumber)
                            {
                                nextNumber = number + 1;
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            // ignore
                        }
                    }
                }

                // add new music
                musicList.add(new Music("Song " + nextNumber));

                // update adapter
                adapter = new MusicAdapter(musicList);
                recyclerView.setAdapter(adapter);
            }
        });
    }


    // Search Bar
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
