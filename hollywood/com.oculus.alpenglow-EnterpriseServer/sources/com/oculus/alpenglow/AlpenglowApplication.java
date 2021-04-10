package com.oculus.alpenglow;

import X.AbstractC04970iB;
import X.AnonymousClass0L5;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0Qz;
import X.AnonymousClass0R7;
import X.C007508o;
import X.C01550Ij;
import X.C02750ak;
import X.C02850av;
import X.C04910hv;
import X.C05400jG;
import android.app.Application;
import com.facebook.inject.rootmodule.defaultmodule.___DEFAULT___ProcessRootModule;
import com.oculus.alpenglow.init.AlpenglowAppInitializer;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.INeedInit;
import com.oculus.managed.ManagedMode;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.processhelper.ProcessHelper;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.Nullable;

public class AlpenglowApplication extends Application implements AnonymousClass0Qz {
    public static final String TAG = "EnterpriseServer.AlpenglowApplication";
    public AnonymousClass0R7 _UL_mInjectionContext;
    @Nullable
    public AnonymousClass0Lh mInjector;

    @Override // X.AnonymousClass0Qz
    @Nullable
    public final synchronized AnonymousClass0Lh A3k() {
        AnonymousClass0Lh r0;
        if (!ProcessHelper.A00(this)) {
            r0 = null;
        } else {
            while (true) {
                try {
                    r0 = this.mInjector;
                    if (r0 != null) {
                        break;
                    }
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return r0;
    }

    public final void onCreate() {
        Throwable th;
        boolean z;
        super.onCreate();
        if (ProcessHelper.A00(this)) {
            AnonymousClass0NK.A00();
            synchronized (this) {
                ArrayList arrayList = new ArrayList();
                AnonymousClass0L5.A00();
                arrayList.add(new ___DEFAULT___ProcessRootModule());
                AnonymousClass0Lh.A00 = getApplicationContext();
                this.mInjector = new C007508o(this, arrayList);
                notifyAll();
            }
            this._UL_mInjectionContext = new AnonymousClass0R7(1, AnonymousClass0Lh.get(this));
            C02750ak r1 = new C02750ak();
            synchronized (C05400jG.class) {
                if (C05400jG.A00 == null) {
                    C05400jG.A00 = r1;
                } else {
                    throw new IllegalStateException("Cannot re-initialize NativeLoader.");
                }
            }
            if (C01550Ij.A00 == null) {
                C01550Ij.A00 = this;
                UnifiedTelemetryLogger.getInstance().init(getApplicationContext());
                AnonymousClass1 r0 = new AbstractC04970iB() {
                    /* class com.oculus.alpenglow.AlpenglowApplication.AnonymousClass1 */

                    @Override // X.AbstractC04970iB
                    public final void A7P(String str) {
                        AnonymousClass0NK.A06(AlpenglowApplication.TAG, "SecureContextHelper report message = %s", str);
                    }

                    @Override // X.AbstractC04970iB
                    public final void A7Q(String str, String str2, @Nullable Throwable th) {
                        AnonymousClass0NK.A06(AlpenglowApplication.TAG, "SecureContextHelper report category = %s, message = %s", str, str2, th);
                    }
                };
                C02850av r12 = C04910hv.A0C;
                synchronized (r12) {
                    r12.A00 = r0;
                }
                AlpenglowAppInitializer alpenglowAppInitializer = (AlpenglowAppInitializer) AnonymousClass0Lh.A03(0, 95, this._UL_mInjectionContext);
                synchronized (alpenglowAppInitializer) {
                    AnonymousClass0R7 r13 = alpenglowAppInitializer._UL_mInjectionContext;
                    boolean z2 = ((ManagedMode) AnonymousClass0Lh.A03(4, 86, r13)).isEnterpriseModeEnabled;
                    if (z2) {
                        AppInitLock appInitLock = (AppInitLock) AnonymousClass0Lh.A03(3, 128, r13);
                        AlpenglowAppInitializer.AnonymousClass1 r14 = new AppInitLock.Listener() {
                            /* class com.oculus.alpenglow.init.AlpenglowAppInitializer.AnonymousClass1 */
                        };
                        synchronized (appInitLock) {
                            try {
                                if (!appInitLock.listeners.contains(r14)) {
                                    appInitLock.listeners.add(r14);
                                }
                                z = appInitLock.initialized;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                        if (z) {
                            AppInitLock.A00(appInitLock);
                        }
                        ((Set) AnonymousClass0Lh.A03(0, 10, alpenglowAppInitializer._UL_mInjectionContext)).size();
                        for (INeedInit iNeedInit : (Set) AnonymousClass0Lh.A03(0, 10, alpenglowAppInitializer._UL_mInjectionContext)) {
                            try {
                                iNeedInit.A5C();
                            } catch (ExceptionInInitializerError unused) {
                            }
                        }
                        ((Set) AnonymousClass0Lh.A03(1, 41, alpenglowAppInitializer._UL_mInjectionContext)).size();
                        for (INeedInit iNeedInit2 : (Set) AnonymousClass0Lh.A03(1, 41, alpenglowAppInitializer._UL_mInjectionContext)) {
                            iNeedInit2.A5C();
                        }
                    }
                    ((Set) AnonymousClass0Lh.A03(2, 25, alpenglowAppInitializer._UL_mInjectionContext)).size();
                    for (INeedInit iNeedInit3 : (Set) AnonymousClass0Lh.A03(2, 25, alpenglowAppInitializer._UL_mInjectionContext)) {
                        String simpleName = iNeedInit3.getClass().getSimpleName();
                        if (z2 || AlpenglowAppInitializer.STETHO_INIT.equals(simpleName)) {
                            iNeedInit3.A5C();
                        }
                    }
                    AppInitLock appInitLock2 = (AppInitLock) AnonymousClass0Lh.A03(3, 128, alpenglowAppInitializer._UL_mInjectionContext);
                    synchronized (appInitLock2) {
                        try {
                            appInitLock2.initialized = true;
                            appInitLock2.notifyAll();
                        } catch (Throwable th3) {
                            th = th3;
                            throw th;
                        }
                    }
                    AppInitLock.A00(appInitLock2);
                }
                return;
            }
            throw new IllegalStateException("ApplicationHolder#set previously called");
        }
    }
}
