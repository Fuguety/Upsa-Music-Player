package app.main.upsaplayer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlaylistDetailPage extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_detail);

        int index = getIntent().getIntExtra("playlist_index", -1);

        if (index >= 0)
        {
            Playlist playlist = PlaylistManager.getPlaylists().get(index);

            RecyclerView recyclerView = findViewById(R.id.playlist_music_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicAdapter(playlist.getMusicList()));
        }
    }
}
