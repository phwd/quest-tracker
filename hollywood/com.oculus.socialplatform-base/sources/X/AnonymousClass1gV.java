package X;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* renamed from: X.1gV  reason: invalid class name */
public class AnonymousClass1gV extends AnonymousClass1gU<Bitmap> {
    public Bitmap A00;
    public final int A01;
    public final long A02;
    public final Handler A03;

    @Override // X.AbstractC08781fH
    public final void onLoadCleared(@Nullable Drawable drawable) {
        this.A00 = null;
    }

    @Override // X.AbstractC08781fH
    public final void onResourceReady(@NonNull Object obj, @Nullable AbstractC08911fj r6) {
        this.A00 = (Bitmap) obj;
        Handler handler = this.A03;
        handler.sendMessageAtTime(handler.obtainMessage(1, this), this.A02);
    }

    public AnonymousClass1gV(Handler handler, int i, long j) {
        this.A03 = handler;
        this.A01 = i;
        this.A02 = j;
    }
}
