package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* access modifiers changed from: package-private */
public class ResourceRecycler {
    private final Handler handler = new Handler(Looper.getMainLooper(), new ResourceRecyclerCallback());
    private boolean isRecycling;

    ResourceRecycler() {
    }

    /* access modifiers changed from: package-private */
    public synchronized void recycle(Resource<?> resource, boolean z) {
        if (!this.isRecycling) {
            if (!z) {
                this.isRecycling = true;
                resource.recycle();
                this.isRecycling = false;
            }
        }
        this.handler.obtainMessage(1, resource).sendToTarget();
    }

    private static final class ResourceRecyclerCallback implements Handler.Callback {
        static final int RECYCLE_RESOURCE = 1;

        ResourceRecyclerCallback() {
        }

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((Resource) message.obj).recycle();
            return true;
        }
    }
}
