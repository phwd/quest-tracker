package com.oculus.platform;

import X.AnonymousClass006;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.oculus.platform.provider.OculusContent;

public class OVRProfile {
    public static final String TAG = "OVRProfile";
    public String id = null;
    public String imageUrl = null;
    public String locale = null;
    public String name = null;

    public void set(Context context, String str) {
        ContentResolver contentResolver = context.getContentResolver();
        String obj = OculusContent.Profile.CONTENT_URI.toString();
        if (str != null) {
            obj = AnonymousClass006.A07(obj, "?app_id=", str);
        }
        Cursor query = contentResolver.query(Uri.parse(obj), null, null, null, null);
        if (query == null || !query.moveToFirst()) {
            this.id = null;
            this.name = null;
            this.locale = null;
            this.imageUrl = null;
            return;
        }
        this.id = query.getString(query.getColumnIndex("id"));
        this.name = query.getString(query.getColumnIndex("username"));
        this.locale = query.getString(query.getColumnIndex("locale"));
        int columnIndex = query.getColumnIndex("image_url");
        if (columnIndex != -1) {
            this.imageUrl = query.getString(columnIndex);
        } else {
            this.imageUrl = null;
        }
        query.close();
    }
}
