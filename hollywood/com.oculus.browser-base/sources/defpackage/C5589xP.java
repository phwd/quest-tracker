package defpackage;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.ByteArrayInputStream;

/* renamed from: xP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5589xP extends AbstractC2032cb {
    public String i;
    public ContentResolver j;
    public AbstractC5419wP k;

    public C5589xP(String str, ContentResolver contentResolver, AbstractC5419wP wPVar) {
        this.i = str;
        this.j = contentResolver;
        this.k = wPVar;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        if (!h()) {
            AbstractC5419wP wPVar = this.k;
            String str = this.i;
            C5173uy uyVar = (C5173uy) wPVar;
            if (uyVar.Z.R.f8000a.a(str) == null) {
                FC0 fc0 = uyVar.Z.R;
                if (bitmap == null) {
                    fc0.b.add(str);
                } else {
                    fc0.f8000a.d(str, bitmap);
                    fc0.b.remove(str);
                }
            }
            if (bitmap != null && str.equals(uyVar.c0.F)) {
                uyVar.b0.n(bitmap);
            }
        }
    }

    /* renamed from: m */
    public Bitmap c() {
        Cursor query;
        byte[] blob;
        if (h() || (query = this.j.query(Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(this.i)), "photo"), new String[]{"data15"}, null, null, null)) == null) {
            return null;
        }
        try {
            if (query.moveToFirst() && (blob = query.getBlob(0)) != null) {
                return BitmapFactory.decodeStream(new ByteArrayInputStream(blob));
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }
}
