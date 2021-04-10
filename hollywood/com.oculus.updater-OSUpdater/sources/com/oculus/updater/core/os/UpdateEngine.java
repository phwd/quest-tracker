package com.oculus.updater.core.os;

import android.os.Handler;
import android.os.IBinder;
import android.os.IUpdateEngine;
import android.os.IUpdateEngineCallback;
import android.os.RemoteException;
import androidx.annotation.VisibleForTesting;
import com.facebook.debug.log.BLog;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;

@Dependencies
@VisibleForTesting
public class UpdateEngine {
    private static final String TAG = "UpdateEngine";
    @Nullable
    private IUpdateEngine mUpdateEngine;

    @AutoGeneratedFactoryMethod
    public static final UpdateEngine _UL__ULSEP_com_oculus_updater_core_os_UpdateEngine_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new UpdateEngine();
    }

    @Inject
    public UpdateEngine() {
        BLog.d(TAG, "UpdateEngine constructor");
        initialize();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initialize() {
        int i = 0;
        while (this.mUpdateEngine == null) {
            try {
                Thread.sleep((long) Math.min(i * 1000, 30000));
                this.mUpdateEngine = IUpdateEngine.Stub.asInterface((IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "android.os.UpdateEngineService"));
            } catch (Exception e) {
                BLog.e(TAG, "UpdateEngine binder initialization failed", e);
            }
            i++;
        }
    }

    public boolean supportsABUpdates() {
        return this.mUpdateEngine != null;
    }

    public boolean bind(final IUpdateEngineCallback iUpdateEngineCallback, final Handler handler) {
        if (supportsABUpdates()) {
            AnonymousClass1 r0 = new IUpdateEngineCallback.Stub() {
                /* class com.oculus.updater.core.os.UpdateEngine.AnonymousClass1 */

                @Override // android.os.IUpdateEngineCallback
                public void onStatusUpdate(final int i, final float f) {
                    try {
                        if (handler != null) {
                            handler.post(new Runnable() {
                                /* class com.oculus.updater.core.os.UpdateEngine.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    try {
                                        iUpdateEngineCallback.onStatusUpdate(i, f);
                                    } catch (RemoteException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        } else {
                            iUpdateEngineCallback.onStatusUpdate(i, f);
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override // android.os.IUpdateEngineCallback
                public void onPayloadApplicationComplete(final int i) {
                    try {
                        if (handler != null) {
                            handler.post(new Runnable() {
                                /* class com.oculus.updater.core.os.UpdateEngine.AnonymousClass1.AnonymousClass2 */

                                public void run() {
                                    try {
                                        iUpdateEngineCallback.onPayloadApplicationComplete(i);
                                    } catch (RemoteException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        } else {
                            iUpdateEngineCallback.onPayloadApplicationComplete(i);
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            try {
                this.mUpdateEngine.asBinder().linkToDeath(new UpdateEngineDeathRecipient(iUpdateEngineCallback, handler), 0);
                return this.mUpdateEngine.bind(r0);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("AB updates are not supported on this device");
        }
    }

    public void applyPayload(String str, long j, long j2, String[] strArr) {
        if (supportsABUpdates()) {
            try {
                this.mUpdateEngine.applyPayload(str, j, j2, strArr);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("AB updates are not supported on this device");
        }
    }

    public void cancel() {
        if (supportsABUpdates()) {
            try {
                this.mUpdateEngine.cancel();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("AB updates are not supported on this device");
        }
    }

    public void suspend() {
        if (supportsABUpdates()) {
            try {
                this.mUpdateEngine.suspend();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("AB updates are not supported on this device");
        }
    }

    public void resume() {
        if (supportsABUpdates()) {
            try {
                this.mUpdateEngine.resume();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("AB updates are not supported on this device");
        }
    }

    public void resetStatus() {
        if (supportsABUpdates()) {
            try {
                this.mUpdateEngine.resetStatus();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("AB updates are not supported on this device");
        }
    }

    /* access modifiers changed from: private */
    public final class UpdateEngineDeathRecipient implements IBinder.DeathRecipient {
        final IUpdateEngineCallback mCallback;
        final Handler mHandler;

        public UpdateEngineDeathRecipient(IUpdateEngineCallback iUpdateEngineCallback, Handler handler) {
            this.mCallback = iUpdateEngineCallback;
            this.mHandler = handler;
        }

        public void binderDied() {
            UpdateEngine.this.mUpdateEngine = null;
            UpdateEngine.this.initialize();
            UpdateEngine.this.bind(this.mCallback, this.mHandler);
            try {
                this.mCallback.onPayloadApplicationComplete(1001);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
