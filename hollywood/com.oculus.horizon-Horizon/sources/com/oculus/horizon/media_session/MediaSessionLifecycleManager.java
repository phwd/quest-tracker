package com.oculus.horizon.media_session;

import X.AbstractC06600ny;
import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.os.Handler;
import android.os.PowerManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.vr_lifecycle.VRLifecycleManager;
import com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter;
import com.oculus.util.WeakRef;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_android_os_Handler_ULSEP_BINDING_ID"})
@ApplicationScoped
public class MediaSessionLifecycleManager {
    public static volatile MediaSessionLifecycleManager _UL__ULSEP_com_oculus_horizon_media_ULUNDERSCORE_session_MediaSessionLifecycleManager_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final Set<MediaSessionCallback> mCallbacks = new CopyOnWriteArraySet();
    @Nullable
    public Runnable mDelayedCallback;
    public final VRLifecycleManager.VRLifecycle mVRLifecycle = new VRLifecycleManager.VRLifecycle() {
        /* class com.oculus.horizon.media_session.MediaSessionLifecycleManager.AnonymousClass1 */

        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
        public final void A6C() {
            MediaSessionLifecycleManager mediaSessionLifecycleManager = MediaSessionLifecycleManager.this;
            synchronized (mediaSessionLifecycleManager) {
                for (MediaSessionCallback mediaSessionCallback : mediaSessionLifecycleManager.mCallbacks) {
                    mediaSessionCallback.A6C();
                }
            }
        }

        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
        public final void A6D() {
            MediaSessionLifecycleManager mediaSessionLifecycleManager = MediaSessionLifecycleManager.this;
            synchronized (mediaSessionLifecycleManager) {
                for (MediaSessionCallback mediaSessionCallback : mediaSessionLifecycleManager.mCallbacks) {
                    mediaSessionCallback.A6D();
                }
            }
        }

        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
        public final void A7A() {
            MediaSessionLifecycleManager mediaSessionLifecycleManager = MediaSessionLifecycleManager.this;
            synchronized (mediaSessionLifecycleManager) {
                Iterator<MediaSessionCallback> it = mediaSessionLifecycleManager.mCallbacks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next().A8z()) {
                        long A3k = ((AbstractC06600ny) AnonymousClass0J2.A03(2, 399, mediaSessionLifecycleManager._UL_mInjectionContext)).A3k(36591777037156367L);
                        if (A3k > 0) {
                            Runnable runnable = mediaSessionLifecycleManager.mDelayedCallback;
                            if (runnable != null) {
                                ((Handler) AnonymousClass0J2.A03(3, 334, mediaSessionLifecycleManager._UL_mInjectionContext)).removeCallbacks(runnable);
                                MediaSessionLifecycleManager.A00(mediaSessionLifecycleManager);
                            }
                            mediaSessionLifecycleManager.mDelayedCallback = new Runnable() {
                                /* class com.oculus.horizon.media_session.MediaSessionLifecycleManager.AnonymousClass2 */

                                public final void run() {
                                    MediaSessionLifecycleManager mediaSessionLifecycleManager = MediaSessionLifecycleManager.this;
                                    synchronized (mediaSessionLifecycleManager) {
                                        MediaSessionLifecycleManager.A00(mediaSessionLifecycleManager);
                                        for (MediaSessionCallback mediaSessionCallback : mediaSessionLifecycleManager.mCallbacks) {
                                            mediaSessionCallback.A6G();
                                        }
                                    }
                                }
                            };
                            PowerManager.WakeLock newWakeLock = ((PowerManager) AnonymousClass0J2.A03(1, 458, mediaSessionLifecycleManager._UL_mInjectionContext)).newWakeLock(1, "MediaSessionLifecycleManager::WakeLock");
                            mediaSessionLifecycleManager.mWakeLock = newWakeLock;
                            newWakeLock.acquire();
                            ((Handler) AnonymousClass0J2.A03(3, 334, mediaSessionLifecycleManager._UL_mInjectionContext)).postDelayed(mediaSessionLifecycleManager.mDelayedCallback, TimeUnit.SECONDS.toMillis(A3k));
                        }
                    }
                }
                for (MediaSessionCallback mediaSessionCallback : mediaSessionLifecycleManager.mCallbacks) {
                    mediaSessionCallback.A6G();
                }
            }
        }

        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
        public final void A7B() {
            MediaSessionLifecycleManager mediaSessionLifecycleManager = MediaSessionLifecycleManager.this;
            synchronized (mediaSessionLifecycleManager) {
                Runnable runnable = mediaSessionLifecycleManager.mDelayedCallback;
                if (runnable != null) {
                    ((Handler) AnonymousClass0J2.A03(3, 334, mediaSessionLifecycleManager._UL_mInjectionContext)).removeCallbacks(runnable);
                    MediaSessionLifecycleManager.A00(mediaSessionLifecycleManager);
                } else {
                    for (MediaSessionCallback mediaSessionCallback : mediaSessionLifecycleManager.mCallbacks) {
                        mediaSessionCallback.A6H();
                    }
                }
            }
        }
    };
    @Nullable
    public PowerManager.WakeLock mWakeLock;

    public static synchronized void A00(MediaSessionLifecycleManager mediaSessionLifecycleManager) {
        synchronized (mediaSessionLifecycleManager) {
            mediaSessionLifecycleManager.mDelayedCallback = null;
            PowerManager.WakeLock wakeLock = mediaSessionLifecycleManager.mWakeLock;
            if (wakeLock != null) {
                wakeLock.release();
                mediaSessionLifecycleManager.mWakeLock = null;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void A01(com.oculus.horizon.media_session.MediaSessionCallback r12) {
        /*
        // Method dump skipped, instructions count: 249
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.media_session.MediaSessionLifecycleManager.A01(com.oculus.horizon.media_session.MediaSessionCallback):void");
    }

    public final synchronized void A02(MediaSessionCallback mediaSessionCallback) {
        this.mCallbacks.remove(mediaSessionCallback);
        if (this.mCallbacks.isEmpty()) {
            VRLifecycleManager vRLifecycleManager = (VRLifecycleManager) AnonymousClass0J2.A03(0, 489, this._UL_mInjectionContext);
            VRLifecycleManager.VRLifecycle vRLifecycle = this.mVRLifecycle;
            synchronized (vRLifecycleManager.mClientsLock) {
                if (SelfReapingRefCounter.A01(vRLifecycleManager, vRLifecycle)) {
                    vRLifecycleManager.mClients.remove(new WeakRef(vRLifecycle));
                    vRLifecycleManager.mClients.size();
                }
                if (vRLifecycleManager.mClients.isEmpty()) {
                    SelfReapingRefCounter.A00(vRLifecycleManager);
                }
            }
        }
    }

    @Inject
    public MediaSessionLifecycleManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
    }
}
