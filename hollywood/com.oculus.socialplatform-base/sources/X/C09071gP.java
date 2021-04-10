package X;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1gP  reason: invalid class name and case insensitive filesystem */
public final class C09071gP<Z> extends AnonymousClass1gU<Z> {
    public static final Handler A01 = new Handler(Looper.getMainLooper(), new C09171gp());
    public final AnonymousClass1g0 A00;

    @Override // X.AbstractC08781fH
    public final void onLoadCleared(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    public final void onResourceReady(@NonNull Z z, @Nullable AbstractC08911fj<? super Z> r4) {
        A01.obtainMessage(1, this).sendToTarget();
    }

    public C09071gP(AnonymousClass1g0 r1, int i, int i2) {
        super(i, i2);
        this.A00 = r1;
    }
}
