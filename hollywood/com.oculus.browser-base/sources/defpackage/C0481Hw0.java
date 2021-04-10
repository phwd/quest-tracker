package defpackage;

import android.database.Cursor;
import android.net.Uri;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: Hw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0481Hw0 implements Iterator {
    public static final Uri F;
    public static final Uri G;
    public static final String[] H = {"_id", "url", "title", "type", "parent", "favicon", "touchicon"};
    public final Cursor I;

    static {
        Uri build = new Uri.Builder().scheme("content").authority("com.android.partnerbookmarks").build();
        F = build;
        G = build.buildUpon().appendPath("bookmarks").build();
    }

    public C0481Hw0(Cursor cursor) {
        this.I = cursor;
    }

    public boolean hasNext() {
        Cursor cursor = this.I;
        if (cursor != null) {
            return cursor.getCount() > 0 && !this.I.isLast() && !this.I.isAfterLast();
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public Object next() {
        Cursor cursor = this.I;
        if (cursor == null) {
            throw new IllegalStateException();
        } else if (cursor.moveToNext()) {
            C0359Fw0 fw0 = new C0359Fw0();
            try {
                Cursor cursor2 = this.I;
                long j = cursor2.getLong(cursor2.getColumnIndexOrThrow("_id"));
                fw0.f8052a = j;
                if (j == 0) {
                    AbstractC1220Ua0.d("PartnerBookmarks", "Dropping the bookmark: reserved _id was used", new Object[0]);
                } else {
                    Cursor cursor3 = this.I;
                    long j2 = cursor3.getLong(cursor3.getColumnIndexOrThrow("parent"));
                    fw0.b = j2;
                    if (j2 == 0) {
                        fw0.b = 0;
                    }
                    Cursor cursor4 = this.I;
                    fw0.c = cursor4.getInt(cursor4.getColumnIndexOrThrow("type")) == 2;
                    Cursor cursor5 = this.I;
                    fw0.d = cursor5.getString(cursor5.getColumnIndexOrThrow("url"));
                    Cursor cursor6 = this.I;
                    fw0.e = cursor6.getString(cursor6.getColumnIndexOrThrow("title"));
                    Cursor cursor7 = this.I;
                    fw0.f = cursor7.getBlob(cursor7.getColumnIndexOrThrow("favicon"));
                    Cursor cursor8 = this.I;
                    fw0.g = cursor8.getBlob(cursor8.getColumnIndexOrThrow("touchicon"));
                    if ((fw0.c || fw0.d != null) && fw0.e != null) {
                        return fw0;
                    }
                    AbstractC1220Ua0.d("PartnerBookmarks", "Dropping the bookmark: no title, or no url on a non-foler", new Object[0]);
                }
            } catch (IllegalArgumentException e) {
                StringBuilder i = AbstractC2531fV.i("Dropping the bookmark: ");
                i.append(e.getMessage());
                AbstractC1220Ua0.d("PartnerBookmarks", i.toString(), new Object[0]);
            }
            return null;
        } else {
            throw new NoSuchElementException();
        }
    }
}
