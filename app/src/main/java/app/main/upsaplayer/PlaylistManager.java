package app.main.upsaplayer;

import java.util.ArrayList;
import java.util.List;

public class PlaylistManager
{
    private static List<Playlist> playlists = new ArrayList<>();

    public static List<Playlist> getPlaylists()
    {
        return playlists;
    }

    public static void addPlaylist(Playlist playlist)
    {
        playlists.add(playlist);
    }

    public static void addMusicToPlaylist(int index, Music music)
    {
        if (index >= 0 && index < playlists.size())
        {
            playlists.get(index).getMusicList().add(music);
        }
    }


}
