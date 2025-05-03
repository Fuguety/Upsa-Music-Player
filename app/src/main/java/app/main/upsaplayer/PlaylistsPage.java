package app.main.upsaplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsPage extends AppCompatActivity
{
    ListView playlistListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists_page);

        playlistListView = findViewById(R.id.playlist_list);

        List<String> playlistNames = new ArrayList<>();
        for (Playlist playlist : PlaylistManager.getPlaylists())
        {
            playlistNames.add(playlist.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlistNames);
        playlistListView.setAdapter(adapter);

        playlistListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(PlaylistsPage.this, PlaylistDetailPage.class);
                intent.putExtra("playlist_index", position);
                startActivity(intent);
            }
        });
    }
}
