package X;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.FileNotFoundException;

/* renamed from: X.1YB  reason: invalid class name */
public class AnonymousClass1YB implements AbstractC07051bX<File> {
    public static final String[] A02 = {"_data"};
    public final Context A00;
    public final Uri A01;

    @Override // X.AbstractC07051bX
    public final void A26() {
    }

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<File> A3h() {
        return File.class;
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r9, @NonNull AnonymousClass1Ry<? super File> r10) {
        ContentResolver contentResolver = this.A00.getContentResolver();
        Uri uri = this.A01;
        Cursor query = contentResolver.query(uri, A02, null, null, null);
        String str = null;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    str = query.getString(query.getColumnIndexOrThrow("_data"));
                }
            } finally {
                query.close();
            }
        }
        if (TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder("Failed to find file path for: ");
            sb.append(uri);
            r10.A7F(new FileNotFoundException(sb.toString()));
            return;
        }
        r10.A6x(new File(str));
    }

    public AnonymousClass1YB(Context context, Uri uri) {
        this.A00 = context;
        this.A01 = uri;
    }
}
