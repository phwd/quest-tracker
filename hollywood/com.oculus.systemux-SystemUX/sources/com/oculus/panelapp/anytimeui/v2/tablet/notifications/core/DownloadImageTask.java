package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.Nullable;
import java.net.URL;
import java.util.HashMap;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "DownloadImageTask";
    private static HashMap<String, Bitmap> alreadyRetrieved = new HashMap<>();

    /* access modifiers changed from: protected */
    public Bitmap doInBackground(String... strArr) {
        String str = strArr[0];
        if (alreadyRetrieved.containsKey(str)) {
            return alreadyRetrieved.get(str);
        }
        Bitmap loadBitmapFromUrl = loadBitmapFromUrl(str);
        alreadyRetrieved.put(str, loadBitmapFromUrl);
        return loadBitmapFromUrl;
    }

    @Nullable
    public static Bitmap loadBitmapFromUrl(String str) {
        try {
            return BitmapFactory.decodeStream(new URL(str).openStream());
        } catch (Exception e) {
            Log.e(TAG, String.format("Error retrieving icon %s", str), e);
            return null;
        }
    }
}
