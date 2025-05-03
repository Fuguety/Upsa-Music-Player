package app.main.upsaplayer;

import java.util.List;

public class Playlist
{
    private String name;
    private List<Music> musicList;


    public Playlist(String name, List<Music> musicList)
    {
        this.name = name;
        this.musicList = musicList;
    }

    public String getName() { return name; }
    public List<Music> getMusicList() { return musicList; }

}
