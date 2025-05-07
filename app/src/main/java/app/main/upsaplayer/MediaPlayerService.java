package app.main.upsaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MediaPlayerService
{
    private static MediaPlayer mediaPlayer;
    private static List<Music> queue;
    private static int currentIndex = 0;
    private static Context contextRef;
    private static LinearLayout musicBar;
    private static Button btnPause;
    private static Button btnNext;
    private static TextView songTitleText;


    public static void startPlayback(Context context, List<Music> musicList, int startIndex)
    {
        queue = musicList;
        currentIndex = startIndex;
        playNext(context);
        contextRef = context.getApplicationContext();


        if (musicBar != null)
        {
            musicBar.setVisibility(View.VISIBLE);
            btnPause.setText("Pause");

            if (songTitleText != null && queue != null && currentIndex < queue.size())
            {
                songTitleText.setText(queue.get(currentIndex).getTitle());
            }

        }

    }

    private static void playNext(Context context)
    {
        if (currentIndex >= queue.size())
        {
            currentIndex = 0; // goes back to start of playlist
            return;
        }

        // Dummy sound (replace with real file later)
        int dummyResId = R.raw.audio;
        // dummy sound



        if (mediaPlayer != null)
        {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(context, dummyResId);

        if (mediaPlayer == null)
        {
            android.util.Log.e("MediaPlayerService", "MediaPlayer creation failed (file missing or invalid)");
            return;
        }

        mediaPlayer.setOnCompletionListener(mp ->
        {
            currentIndex++;
            playNext(context);
        });

        mediaPlayer.start(); // plays song

        btnPause.setText("Pause");

    }

    public static void stop()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    public static void pause()
    {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

    public static void resume()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.start();
        }
    }

    public static boolean isPlaying()
    {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public static void skip()
    {
        currentIndex++;
        playNext(contextRef);

        if (songTitleText != null && queue != null && currentIndex < queue.size())
        {
            songTitleText.setText(queue.get(currentIndex).getTitle());
        }


    }


    public static void bindBar(View bar)
    {
        musicBar = (LinearLayout) bar;
        songTitleText = musicBar.findViewById(R.id.song_title);
        btnPause = musicBar.findViewById(R.id.btn_pause);
        btnNext = musicBar.findViewById(R.id.btn_next);

        btnPause.setOnClickListener(v ->
        {
            if (isPlaying())
            {
                pause();
                btnPause.setText("Play");
            }
            else
            {
                resume();
                btnPause.setText("Pause");
            }
        });

        btnNext.setOnClickListener(v -> skip());
    }


}
