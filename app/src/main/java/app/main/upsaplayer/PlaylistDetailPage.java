package app.main.upsaplayer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

            recyclerView.setAdapter(new MusicAdapter(playlist.getMusicList(), new OnMusicClickListener()
            {
                @Override
                public void onMusicClick(int position)
                {
                    MediaPlayerService.startPlayback(PlaylistDetailPage.this, playlist.getMusicList(), position);
                }
            }));
        }



        // Bottom Nav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_playlists); // Highlight page

        bottomNav.setOnItemSelectedListener(item -> {
            NavigationHelper.navigate(this, item.getItemId());
            return true;
        });





    }
}
