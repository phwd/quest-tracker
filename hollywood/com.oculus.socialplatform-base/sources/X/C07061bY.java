package X;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;

/* renamed from: X.1bY  reason: invalid class name and case insensitive filesystem */
public final class C07061bY<DataT> implements AbstractC07051bX<DataT> {
    public static final String[] A0A = {"_data"};
    public final int A00;
    public final int A01;
    public final Context A02;
    public final Uri A03;
    public final AnonymousClass1cO A04;
    public final AbstractC07011bT<File, DataT> A05;
    public final AbstractC07011bT<Uri, DataT> A06;
    public final Class<DataT> A07;
    @Nullable
    public volatile AbstractC07051bX<DataT> A08;
    public volatile boolean A09;

    @Override // X.AbstractC07051bX
    public final void cancel() {
        this.A09 = true;
        AbstractC07051bX<DataT> r0 = this.A08;
        if (r0 != null) {
            r0.cancel();
        }
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        AbstractC07051bX<DataT> r0 = this.A08;
        if (r0 != null) {
            r0.A26();
        }
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<DataT> A3h() {
        return this.A07;
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    public C07061bY(Context context, AbstractC07011bT<File, DataT> r3, AbstractC07011bT<Uri, DataT> r4, Uri uri, int i, int i2, AnonymousClass1cO r8, Class<DataT> cls) {
        this.A02 = context.getApplicationContext();
        this.A05 = r3;
        this.A06 = r4;
        this.A03 = uri;
        this.A01 = i;
        this.A00 = i2;
        this.A04 = r8;
        this.A07 = cls;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r12, @NonNull AnonymousClass1Ry<? super DataT> r13) {
        Uri uri;
        C07091bb<DataT> r0;
        AbstractC07051bX r1;
        Uri uri2;
        try {
            if (Environment.isExternalStorageLegacy()) {
                AbstractC07011bT<File, DataT> r4 = this.A05;
                uri = this.A03;
                Cursor cursor = null;
                try {
                    Cursor query = this.A02.getContentResolver().query(uri, A0A, null, null, null);
                    if (query == null || !query.moveToFirst()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to media store entry for: ");
                        sb.append(uri);
                        throw new FileNotFoundException(sb.toString());
                    }
                    String string = query.getString(query.getColumnIndexOrThrow("_data"));
                    if (!TextUtils.isEmpty(string)) {
                        File file = new File(string);
                        query.close();
                        r0 = r4.A1r(file, this.A01, this.A00, this.A04);
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("File path was empty in media store for: ");
                        sb2.append(uri);
                        throw new FileNotFoundException(sb2.toString());
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        cursor.close();
                    }
                    throw th;
                }
            } else {
                if (this.A02.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0) {
                    uri = this.A03;
                    uri2 = MediaStore.setRequireOriginal(uri);
                } else {
                    uri2 = this.A03;
                    uri = uri2;
                }
                r0 = this.A06.A1r(uri2, this.A01, this.A00, this.A04);
            }
            if (r0 == null || (r1 = (AbstractC07051bX<Data>) r0.A01) == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to build fetcher for: ");
                sb3.append(uri);
                r13.A7F(new IllegalArgumentException(sb3.toString()));
                return;
            }
            this.A08 = r1;
            if (this.A09) {
                cancel();
            } else {
                r1.A6H(r12, r13);
            }
        } catch (FileNotFoundException e) {
            r13.A7F(e);
        }
    }
}
