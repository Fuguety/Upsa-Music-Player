package app.main.upsaplayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class AddMusicPlaylist
{
    private List<Playlist> playlists = new ArrayList<>();
    private Context context;
    private View anchorView;

    public AddMusicPlaylist(Context context, View anchorView, List<Playlist> existingPlaylists)
    {
        this.context = context;
        this.anchorView = anchorView;
        this.playlists = existingPlaylists;
    }



    public void addMusic(Music music)
    {
        showMenu(music);
    }

    private void showMenu(Music music)
    {
        PopupMenu popupMenu = new PopupMenu(context, anchorView);

        Menu menu = popupMenu.getMenu();

        // Add existing playlists
        for (int i = 0; i < playlists.size(); i++)
        {
            menu.add(0, i, i, playlists.get(i).getName());
        }

        // Add 'New Playlist' option
        int newPlaylistId = Menu.FIRST + playlists.size(); // Must be greater than all IDs above
        menu.add(0, newPlaylistId, newPlaylistId, "New Playlist +");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                int id = item.getItemId();

                // Add to selected playlist
                if (id != newPlaylistId)
                {
                    Playlist playlist = playlists.get(id);
                    playlist.getMusicList().add(music);
                    return true;
                }

                // Show dialog to create new playlist
                showCreatePlaylistDialog(music);
                return true;
            }
        });

        popupMenu.show();
    }


    private void showCreatePlaylistDialog(Music music)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("New Playlist");

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String playlistName = input.getText().toString();
                if (!playlistName.isEmpty())
                {
                    Playlist newPlaylist = new Playlist(playlistName, new ArrayList<>());
                    newPlaylist.getMusicList().add(music);
                    PlaylistManager.addPlaylist(newPlaylist);
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }



}
