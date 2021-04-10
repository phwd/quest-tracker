package X;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/* renamed from: X.1aa  reason: invalid class name and case insensitive filesystem */
public class C06581aa implements AbstractC06551aX {
    public static final String[] A01 = {"_data"};
    public final ContentResolver A00;

    public C06581aa(ContentResolver contentResolver) {
        this.A00 = contentResolver;
    }

    @Override // X.AbstractC06551aX
    public final Cursor A8o(Uri uri) {
        return this.A00.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, A01, "kind = 1 AND video_id = ?", new String[]{uri.getLastPathSegment()}, null);
    }
}
