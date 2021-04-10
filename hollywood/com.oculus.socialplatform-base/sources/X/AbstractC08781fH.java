package X;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1fH  reason: invalid class name and case insensitive filesystem */
public interface AbstractC08781fH<R> extends AbstractC08541eo {
    @Nullable
    AnonymousClass1h5 getRequest();

    void getSize(@NonNull AbstractC09041fz v);

    void onLoadCleared(@Nullable Drawable drawable);

    void onLoadFailed(@Nullable Drawable drawable);

    void onLoadStarted(@Nullable Drawable drawable);

    void onResourceReady(@NonNull R r, @Nullable AbstractC08911fj<? super R> v);

    void removeCallback(@NonNull AbstractC09041fz v);

    void setRequest(@Nullable AnonymousClass1h5 v);
}
