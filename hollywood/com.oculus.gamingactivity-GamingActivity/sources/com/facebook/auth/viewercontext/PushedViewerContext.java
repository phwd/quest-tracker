package com.facebook.auth.viewercontext;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface PushedViewerContext extends Closeable {
    public static final PushedViewerContext NOT_PUSHED = new PushedViewerContext() {
        /* class com.facebook.auth.viewercontext.PushedViewerContext.AnonymousClass1 */

        @Override // java.io.Closeable, com.facebook.auth.viewercontext.PushedViewerContext, java.lang.AutoCloseable
        public void close() {
        }
    };

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
