package com.oculus.unifiedtelemetry;

import X.AbstractC0096Hu;
import X.C00103y;
import X.C0484rn;
import X.KO;
import X.Mu;
import X.Q4;
import android.app.Application;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.inject.rootmodule.defaultmodule.___DEFAULT___ProcessRootModule;
import com.facebook.xplat.fbglog.FbGlog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class UnifiedTelemetryApplication extends Application implements Q4 {
    public static final String TAG = "UnifiedTelemetryApplication";
    public AbstractC0096Hu mInjector;

    @Override // X.Q4
    public final synchronized AbstractC0096Hu A2U() {
        AbstractC0096Hu hu;
        while (true) {
            try {
                hu = this.mInjector;
                if (hu == null) {
                    wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return hu;
    }

    public final void onCreate() {
        super.onCreate();
        synchronized (Mu.class) {
            Mu.A01.A52(4);
            Iterator<BLogLevelCallback> it = Mu.A00.iterator();
            while (it.hasNext()) {
                it.next();
                FbGlog.setLogLevel(4);
            }
        }
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            if (KO.A00().A00 != null) {
                arrayList.add(new ___DEFAULT___ProcessRootModule());
            }
            AbstractC0096Hu.A00 = getApplicationContext();
            this.mInjector = new C00103y(this, arrayList);
            notifyAll();
        }
        try {
            C0484rn.A01(getApplicationContext());
            C0484rn.A03 = new Object() {
                /* class com.oculus.unifiedtelemetry.UnifiedTelemetryApplication.AnonymousClass1 */
            };
        } catch (IOException e) {
            throw new RuntimeException("SoLoader init failed", e);
        }
    }
}
