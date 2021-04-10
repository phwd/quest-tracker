package X;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1gU  reason: invalid class name */
public abstract class AnonymousClass1gU<T> implements AbstractC08781fH<T> {
    public final int height;
    @Nullable
    public AnonymousClass1h5 request;
    public final int width;

    @Override // X.AbstractC08541eo
    public void onDestroy() {
    }

    @Override // X.AbstractC08781fH
    public void onLoadFailed(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    public void onLoadStarted(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08541eo
    public void onStart() {
    }

    @Override // X.AbstractC08541eo
    public void onStop() {
    }

    @Override // X.AbstractC08781fH
    public final void removeCallback(@NonNull AbstractC09041fz r1) {
    }

    @Override // X.AbstractC08781fH
    @Nullable
    public final AnonymousClass1h5 getRequest() {
        return this.request;
    }

    @Override // X.AbstractC08781fH
    public final void getSize(@NonNull AbstractC09041fz r3) {
        r3.A84(this.width, this.height);
    }

    @Override // X.AbstractC08781fH
    public final void setRequest(@Nullable AnonymousClass1h5 r1) {
        this.request = r1;
    }

    public AnonymousClass1gU() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public AnonymousClass1gU(int i, int i2) {
        if (C08381eW.A06(i, i2)) {
            this.width = i;
            this.height = i2;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A05("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ", i, " and height: ", i2));
    }
}
