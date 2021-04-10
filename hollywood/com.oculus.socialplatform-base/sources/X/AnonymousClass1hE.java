package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1hE  reason: invalid class name */
public final class AnonymousClass1hE implements AbstractC07941di {
    public static final Bitmap.Config A09 = Bitmap.Config.ARGB_8888;
    public long A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public long A05;
    public final C09241hM A06 = new C09241hM();
    public final AnonymousClass1hF A07;
    public final Set<Bitmap.Config> A08;

    @Nullable
    private synchronized Bitmap A00(int i, int i2, @Nullable Bitmap.Config config) {
        Bitmap.Config config2;
        Bitmap A3J;
        A03(config);
        AnonymousClass1hF r5 = this.A07;
        if (config != null) {
            config2 = config;
        } else {
            config2 = A09;
        }
        A3J = r5.A3J(i, i2, config2);
        if (A3J == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                r5.A6K(i, i2, config);
            }
            this.A03++;
        } else {
            this.A02++;
            this.A05 -= (long) r5.A4v(A3J);
            A3J.setHasAlpha(true);
            A3J.setPremultiplied(true);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            r5.A6K(i, i2, config);
        }
        return A3J;
    }

    private synchronized void A02(long j) {
        while (true) {
            if (this.A05 <= j) {
                break;
            }
            AnonymousClass1hF r5 = this.A07;
            Bitmap A99 = r5.A99();
            if (A99 == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                }
                this.A05 = 0;
            } else {
                this.A05 -= (long) r5.A4v(A99);
                this.A01++;
                if (Log.isLoggable("LruBitmapPool", 3)) {
                    r5.A6L(A99);
                }
                A99.recycle();
            }
        }
    }

    @Override // X.AbstractC07941di
    public final synchronized void A8l(Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (!bitmap.isRecycled()) {
            if (bitmap.isMutable()) {
                AnonymousClass1hF r4 = this.A07;
                if (((long) r4.A4v(bitmap)) <= this.A00 && this.A08.contains(bitmap.getConfig())) {
                    int A4v = r4.A4v(bitmap);
                    r4.A8l(bitmap);
                    this.A04++;
                    this.A05 += (long) A4v;
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        r4.A6L(bitmap);
                    }
                    A02(this.A00);
                }
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                this.A07.A6L(bitmap);
                bitmap.isMutable();
                this.A08.contains(bitmap.getConfig());
            }
            bitmap.recycle();
        } else {
            throw new IllegalStateException("Cannot pool recycled bitmap");
        }
    }

    public AnonymousClass1hE(long j) {
        AnonymousClass1hY r1 = new AnonymousClass1hY();
        Set<Bitmap.Config> A012 = A01();
        this.A00 = j;
        this.A07 = r1;
        this.A08 = A012;
    }

    @TargetApi(26)
    public static void A03(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            StringBuilder sb = new StringBuilder("Cannot create a mutable Bitmap with config: ");
            sb.append(config);
            sb.append(". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override // X.AbstractC07941di
    public final void A27() {
        A02(0);
    }

    @Override // X.AbstractC07941di
    @SuppressLint({"InlinedApi"})
    public final void AAm(int i) {
        if (i >= 40 || i >= 20) {
            A27();
        } else if (i >= 20 || i == 15) {
            A02(this.A00 / 2);
        }
    }

    @TargetApi(26)
    public static Set<Bitmap.Config> A01() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        hashSet.add(null);
        if (Build.VERSION.SDK_INT >= 26) {
            hashSet.remove(Bitmap.Config.HARDWARE);
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // X.AbstractC07941di
    @NonNull
    public final Bitmap A3J(int i, int i2, Bitmap.Config config) {
        Bitmap A002 = A00(i, i2, config);
        if (A002 != null) {
            A002.eraseColor(0);
            return A002;
        }
        if (config == null) {
            config = A09;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // X.AbstractC07941di
    @NonNull
    public final Bitmap A3n(int i, int i2, Bitmap.Config config) {
        Bitmap A002 = A00(i, i2, config);
        if (A002 != null) {
            return A002;
        }
        if (config == null) {
            config = A09;
        }
        return Bitmap.createBitmap(i, i2, config);
    }
}
