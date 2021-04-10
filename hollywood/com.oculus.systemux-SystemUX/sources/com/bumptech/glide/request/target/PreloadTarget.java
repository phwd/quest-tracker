package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.transition.Transition;

public final class PreloadTarget<Z> extends CustomTarget<Z> {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        /* class com.bumptech.glide.request.target.PreloadTarget.AnonymousClass1 */

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((PreloadTarget) message.obj).clear();
            return true;
        }
    });
    private static final int MESSAGE_CLEAR = 1;
    private final RequestManager requestManager;

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(@Nullable Drawable drawable) {
    }

    public static <Z> PreloadTarget<Z> obtain(RequestManager requestManager2, int i, int i2) {
        return new PreloadTarget<>(requestManager2, i, i2);
    }

    private PreloadTarget(RequestManager requestManager2, int i, int i2) {
        super(i, i2);
        this.requestManager = requestManager2;
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onResourceReady(@NonNull Z z, @Nullable Transition<? super Z> transition) {
        HANDLER.obtainMessage(1, this).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.requestManager.clear(this);
    }
}
