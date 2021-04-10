package defpackage;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

/* renamed from: ai0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1703ai0 {

    /* renamed from: a  reason: collision with root package name */
    public final Uri f9444a;
    public final ContentValues b;

    public C1703ai0(Uri uri, String str, String str2) {
        uri.getClass();
        this.f9444a = uri;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        ContentValues contentValues = new ContentValues();
        this.b = contentValues;
        str.getClass();
        contentValues.put("_display_name", str);
        str2.getClass();
        contentValues.put("mime_type", str2);
        contentValues.put("date_added", Long.valueOf(currentTimeMillis));
        contentValues.put("date_modified", Long.valueOf(currentTimeMillis));
        try {
            contentValues.put("is_pending", (Integer) 1);
        } catch (Exception e) {
            Log.e("MediaStoreUtils", "Unable to set pending content values.", e);
        }
    }
}
