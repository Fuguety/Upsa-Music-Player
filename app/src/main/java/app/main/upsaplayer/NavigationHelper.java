package app.main.upsaplayer;

import android.app.Activity;
import android.content.Intent;


// Manages page changing from the bottom nav
public class NavigationHelper
{
    public static void navigate(Activity from, int selectedId)
    {
        Intent intent = null;

        if (selectedId == R.id.nav_home)
        {
            intent = new Intent(from, HomePage.class);
        }
        else if (selectedId == R.id.nav_playlists)
        {
            intent = new Intent(from, PlaylistsPage.class);
        }
        else if (selectedId == R.id.nav_settings)
        {
           // intent = new Intent(from, SettingsPage.class);

            // TODO

            return;
        }

        // Prevents calling itself
        if (intent != null &&
                !from.getClass().getName().equals(intent.getComponent().getClassName()))

            {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                from.startActivity(intent);
            }
    }
}
