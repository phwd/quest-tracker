package com.oculus.auth.components;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C003108z;
import X.C01020Iw;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import com.oculus.os.ICompanionServer;
import com.oculus.util.thread.ThreadUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Provider;

@VisibleForTesting
@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_components_AuthComponentRunner_ULSEP_BINDING_ID"})
@ThreadSafe
public class DeviceClaimHelper {
    public static final String COMPANION_SERVER = "com.oculus.companion.server.CompanionServer";
    @Inject
    @Eager
    public final AuthComponentRunner mComponentRunner;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    @Eager
    public final ThreadUtils mThreadUtils;

    public static final class CompanionServerConnection implements ServiceConnection {
        public final CountDownLatch latch;
        @Nullable
        public ICompanionServer server;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public ICompanionServer awaitServer() throws InterruptedException {
            if (this.latch.await(5, TimeUnit.SECONDS)) {
                ICompanionServer iCompanionServer = this.server;
                Object[] objArr = new Object[0];
                if (iCompanionServer != null) {
                    return iCompanionServer;
                }
                throw new VerifyException(Strings.lenientFormat("expected a non-null reference", objArr));
            }
            throw new IllegalStateException("Timed out waiting to bind to CompanionServer");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.server = ICompanionServer.Stub.asInterface(iBinder);
            this.latch.countDown();
        }

        public CompanionServerConnection() {
            this.latch = new CountDownLatch(1);
        }
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_components_DeviceClaimHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(386, r2);
    }

    @AutoGeneratedAccessMethod
    public static final DeviceClaimHelper _UL__ULSEP_com_oculus_auth_components_DeviceClaimHelper_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (DeviceClaimHelper) AnonymousClass117.A00(386, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final DeviceClaimHelper _UL__ULSEP_com_oculus_auth_components_DeviceClaimHelper_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new DeviceClaimHelper(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_components_DeviceClaimHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(386, r2);
    }

    private CompanionServerConnection bind() {
        CompanionServerConnection companionServerConnection = new CompanionServerConnection();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.companion.server", "com.oculus.companion.server.CompanionServer"));
        if (this.mContext.bindService(intent, companionServerConnection, 1)) {
            return companionServerConnection;
        }
        throw new IllegalStateException("Unable to bind to CompanionServer");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void claimDevice(String str) throws Exception {
        this.mThreadUtils.A05();
        CompanionServerConnection bind = bind();
        try {
            try {
                bind.awaitServer().claimDevice(str);
            } catch (IllegalStateException e) {
                throw new DeviceAlreadyClaimedError(e);
            }
        } finally {
            this.mContext.unbindService(bind);
        }
    }

    public AnonymousClass0DC<Void> claimDeviceAsync(final String str, AuthAction authAction) {
        return this.mComponentRunner.runAsync(AnonymousClass0DC.A07(new Callable<Void>() {
            /* class com.oculus.auth.components.DeviceClaimHelper.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                DeviceClaimHelper.this.claimDevice(str);
                return null;
            }
        }, AnonymousClass0DC.A0C, null), AuthLogger.COMPONENT_CLAIM_DEVICE, authAction);
    }

    @Inject
    public DeviceClaimHelper(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
        this.mThreadUtils = ThreadUtils.A01(r2);
        this.mComponentRunner = AuthComponentRunner._UL__ULSEP_com_oculus_auth_components_AuthComponentRunner_ULSEP_ACCESS_METHOD(r2);
    }
}