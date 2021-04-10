package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.08Y  reason: invalid class name */
public class AnonymousClass08Y {
    public ConcurrentHashMap<Long, C03900dT> A00 = new ConcurrentHashMap<>();

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0024 A[SYNTHETIC, Splitter:B:17:0x0024] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002b A[SYNTHETIC, Splitter:B:25:0x002b] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface A05(android.content.Context r4, @androidx.annotation.Nullable android.os.CancellationSignal r5, @androidx.annotation.NonNull X.AnonymousClass09U[] r6, int r7) {
        /*
            r3 = this;
            int r1 = r6.length
            r2 = 0
            r0 = 1
            if (r1 >= r0) goto L_0x0006
            return r2
        L_0x0006:
            X.09U r0 = r3.A04(r6, r7)
            android.content.ContentResolver r1 = r4.getContentResolver()     // Catch:{ IOException -> 0x0028, all -> 0x001e }
            android.net.Uri r0 = r0.A03     // Catch:{ IOException -> 0x0028, all -> 0x001e }
            java.io.InputStream r1 = r1.openInputStream(r0)     // Catch:{ IOException -> 0x0028, all -> 0x001e }
            android.graphics.Typeface r0 = r3.A03(r4, r1)     // Catch:{ IOException -> 0x0029, all -> 0x0020 }
            if (r1 == 0) goto L_0x001d
            r1.close()     // Catch:{ IOException -> 0x001d }
        L_0x001d:
            return r0
        L_0x001e:
            r0 = move-exception
            goto L_0x0022
        L_0x0020:
            r0 = move-exception
            r2 = r1
        L_0x0022:
            if (r2 == 0) goto L_0x0027
            r2.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            throw r0
        L_0x0028:
            r1 = r2
        L_0x0029:
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x002e }
        L_0x002e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass08Y.A05(android.content.Context, android.os.CancellationSignal, X.09U[], int):android.graphics.Typeface");
    }

    public static <T> T A00(T[] tArr, int i, AnonymousClass08X<T> r12) {
        int i2 = 700;
        if ((i & 1) == 0) {
            i2 = 400;
        }
        boolean z = false;
        if ((i & 2) != 0) {
            z = true;
        }
        T t = null;
        int i3 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int abs = Math.abs(r12.A4u(t2) - i2) << 1;
            int i4 = 1;
            if (r12.A5U(t2) == z) {
                i4 = 0;
            }
            int i5 = abs + i4;
            if (t == null || i3 > i5) {
                t = t2;
                i3 = i5;
            }
        }
        return t;
    }

    public AnonymousClass09U A04(AnonymousClass09U[] r2, int i) {
        return (AnonymousClass09U) A00(r2, i, new C03850dO(this));
    }

    @Nullable
    public Typeface A06(Context context, C03900dT r10, Resources resources, int i) {
        long j;
        AnonymousClass084 r0 = (AnonymousClass084) A00(r10.A00, i, new C03840dN(this));
        if (r0 == null) {
            return null;
        }
        int i2 = r0.A00;
        Typeface A02 = AnonymousClass08W.A01.A02(context, resources, i2, r0.A05, i);
        if (A02 != null) {
            AnonymousClass08W.A00.A01(AnonymousClass08W.A00(resources, i2, i), A02);
        }
        if (A02 == null) {
            j = 0;
        } else {
            try {
                Field declaredField = Typeface.class.getDeclaredField("native_instance");
                declaredField.setAccessible(true);
                j = ((Number) declaredField.get(A02)).longValue();
            } catch (IllegalAccessException | NoSuchFieldException e) {
                Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e);
                j = 0;
            }
        }
        if (j == 0) {
            return A02;
        }
        this.A00.put(Long.valueOf(j), r10);
        return A02;
    }

    @Nullable
    public Typeface A02(Context context, Resources resources, int i, String str, int i2) {
        File A002 = AnonymousClass08Z.A00(context);
        if (A002 == null) {
            return null;
        }
        try {
            if (!AnonymousClass08Z.A02(A002, resources, i)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(A002.getPath());
            A002.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            A002.delete();
        }
    }

    public Typeface A03(Context context, InputStream inputStream) {
        File A002 = AnonymousClass08Z.A00(context);
        if (A002 == null) {
            return null;
        }
        try {
            if (!AnonymousClass08Z.A03(A002, inputStream)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(A002.getPath());
            A002.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            A002.delete();
        }
    }
}
