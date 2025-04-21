package app.main.upsaplayer;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import android.widget.TextView;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>
{
    private List<Music> musicList;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView musicTitle;

        public ViewHolder(View view)
        {
            super(view);
            musicTitle = view.findViewById(R.id.musicTitle);
        }
    }

    public MusicAdapter(List<Music> musicList)
    {
        this.musicList = musicList;
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
    }

    @Override
    public int getItemCount()
    {
        return musicList.size();
    }
}
