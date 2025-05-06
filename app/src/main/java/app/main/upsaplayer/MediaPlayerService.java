package app.main.upsaplayer;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.List;

public class MediaPlayerService
{
    private static MediaPlayer mediaPlayer;
    private static List<Music> queue;
    private static int currentIndex = 0;

    public static void startPlayback(Context context, List<Music> musicList, int startIndex)
    {
        queue = musicList;
        currentIndex = startIndex;
        playNext(context);
    }

    private static void playNext(Context context)
    {
        if (currentIndex >= queue.size())
        {
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

        mediaPlayer.start();
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
}
