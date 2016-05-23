package com.example.johir.nutellamusic;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Johir on 4/11/2016.
 */
public class SongsManager {
    // SDCard Path
    final String MEDIA_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Music/";
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    // Constructor
    public SongsManager() {

    }

    public ArrayList<HashMap<String, String>> getPlayList(
            ContentResolver resolver) {

        String projection[] = { MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA };
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, projection, null,
                null, sortOrder);
        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            String path = cursor.getString(1);
            HashMap<String, String> song = new HashMap<String, String>();
            song.put("songTitle", title);
            song.put("songPath", path);
            songsList.add(song);
        }

        //HashMap<String, String> song = new HashMap<String, String>();
        //song.put("songTitle", "super ringtone");
        //song.put("songPath", "android.resource://com.example.johir/raw/super_ringtone");

        cursor.close();
        return songsList;
    }

    /**
     * Class to filter files which are having .mp3 extension
     * */
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}
