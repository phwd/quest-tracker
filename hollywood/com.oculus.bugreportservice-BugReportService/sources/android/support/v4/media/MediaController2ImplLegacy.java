package android.support.v4.media;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.ResultReceiver;
import android.support.v4.media.MediaController2;
import android.support.v4.media.session.MediaControllerCompat$Callback;
import android.util.Log;
import java.util.concurrent.Executor;

@TargetApi(16)
class MediaController2ImplLegacy implements MediaController2.SupportLibraryImpl {
    private static final boolean DEBUG = Log.isLoggable("MC2ImplLegacy", 3);
    static final Bundle sDefaultRootExtras = new Bundle();
    private final MediaController2.ControllerCallback mCallback;
    private final Executor mCallbackExecutor;
    private final HandlerThread mHandlerThread;
    private MediaController2 mInstance;

    @Override // java.lang.AutoCloseable
    public void close() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public void onConnectedNotLocked(Bundle bundle) {
        throw null;
    }

    static {
        sDefaultRootExtras.putBoolean("android.support.v4.media.root_default_root", true);
    }

    /* renamed from: android.support.v4.media.MediaController2ImplLegacy$3  reason: invalid class name */
    class AnonymousClass3 extends ResultReceiver {
        final /* synthetic */ MediaController2ImplLegacy this$0;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            if (this.this$0.mHandlerThread.isAlive()) {
                if (i == -1) {
                    this.this$0.mCallbackExecutor.execute(new Runnable() {
                        /* class android.support.v4.media.MediaController2ImplLegacy.AnonymousClass3.AnonymousClass1 */

                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass3.this.this$0.mCallback.onDisconnected(AnonymousClass3.this.this$0.mInstance);
                        }
                    });
                    this.this$0.close();
                    throw null;
                } else if (i == 0) {
                    this.this$0.onConnectedNotLocked(bundle);
                    throw null;
                }
            }
        }
    }

    private final class ControllerCompatCallback extends MediaControllerCompat$Callback {
        final /* synthetic */ MediaController2ImplLegacy this$0;

        /* renamed from: android.support.v4.media.MediaController2ImplLegacy$ControllerCompatCallback$1  reason: invalid class name */
        class AnonymousClass1 extends ResultReceiver {
            final /* synthetic */ ControllerCompatCallback this$1;

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                if (this.this$1.this$0.mHandlerThread.isAlive()) {
                    if (i == -1) {
                        this.this$1.this$0.mCallbackExecutor.execute(new Runnable() {
                            /* class android.support.v4.media.MediaController2ImplLegacy.ControllerCompatCallback.AnonymousClass1.AnonymousClass1 */

                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass1.this.this$1.this$0.mCallback.onDisconnected(AnonymousClass1.this.this$1.this$0.mInstance);
                            }
                        });
                        this.this$1.this$0.close();
                        throw null;
                    } else if (i == 0) {
                        this.this$1.this$0.onConnectedNotLocked(bundle);
                        throw null;
                    }
                }
            }
        }
    }
}
