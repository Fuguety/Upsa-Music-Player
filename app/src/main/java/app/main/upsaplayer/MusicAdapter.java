package app.main.upsaplayer;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>
{
    private List<Music> musicList;
    private List<Music> fullMusicList;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView musicTitle;
        public ImageView menuButton;

        public ViewHolder(View view)
        {
            super(view);
            musicTitle = view.findViewById(R.id.musicTitle);
            menuButton = view.findViewById(R.id.menu_button);
        }
    }

    public List<Music> getMusicList() { return musicList; }
    public List<Music> getFullMusicList() { return fullMusicList; }

    public MusicAdapter(List<Music> musicList)
    {
        this.musicList = new ArrayList<>(musicList);
        this.fullMusicList = new ArrayList<>(musicList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Music music = musicList.get(position);
        holder.musicTitle.setText(music.getTitle());

        holder.menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), holder.menuButton);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.music_item_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        int id = item.getItemId();

                        if (id == R.id.rename)
                        {
                            // TODO: Handle rename
                            return true;
                        }
                        else if (id == R.id.add_to_playlist)
                        {
                            AddMusicPlaylist addMusic = new AddMusicPlaylist(
                                    v.getContext(),
                                    holder.menuButton,
                                    PlaylistManager.getPlaylists()
                            );
                            addMusic.addMusic(music);
                            return true;
                        }
                        else if (id == R.id.delete)
                        {
                            // TODO: Handle delete
                            return true;
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return musicList.size();
    }



}
