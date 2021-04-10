package org.chromium.shape_detection;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.mojo.system.impl.CoreImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InterfaceRegistrar {
    public static AbstractC1552Zj0 a(int i) {
        CoreImpl coreImpl = (CoreImpl) VA.f9067a;
        Objects.requireNonNull(coreImpl);
        return new C1709ak0(new Rp1(coreImpl, i));
    }

    public static void bindBarcodeDetectionProvider(int i) {
        AbstractC1552Zj0 a2 = a(i);
        Context applicationContext = ContextUtils.getApplicationContext();
        C0015Af af = null;
        if (!AbstractC4310pu.a(applicationContext)) {
            AbstractC1220Ua0.f("BarcodeProviderImpl", "Google Play Services not available", new Object[0]);
        } else {
            try {
                PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
                if (packageInfo.versionCode < 19742000) {
                    AbstractC1220Ua0.f("BarcodeProviderImpl", "Detection disabled (%s < 19.7.42)", packageInfo.versionName);
                } else {
                    af = new C0015Af();
                }
            } catch (PackageManager.NameNotFoundException unused) {
                AbstractC1220Ua0.f("BarcodeProviderImpl", "Google Play Services not available", new Object[0]);
            }
        }
        if (af == null) {
            ((QW) a2).close();
            return;
        }
        int i2 = AbstractC5966zf.h;
        PN0 pn0 = new PN0(a2);
        SA l = a2.l();
        pn0.Y(af);
        pn0.f0(new C0503If(l, af));
        pn0.g0();
    }

    public static void bindFaceDetectionProvider(int i) {
        int i2 = AbstractC4905tN.p;
        C5075uN uNVar = new C5075uN();
        AbstractC1552Zj0 a2 = a(i);
        PN0 pn0 = new PN0(a2);
        SA l = a2.l();
        pn0.Y(uNVar);
        pn0.f0(new C5755yN(l, uNVar));
        pn0.g0();
    }

    public static void bindTextDetection(int i) {
        C1297Vf1 vf1;
        AbstractC1552Zj0 a2 = a(i);
        if (!AbstractC4310pu.a(ContextUtils.getApplicationContext())) {
            AbstractC1220Ua0.a("TextDetectionImpl", "Google Play Services not available", new Object[0]);
            vf1 = null;
        } else {
            vf1 = new C1297Vf1();
        }
        if (vf1 == null) {
            ((QW) a2).close();
            return;
        }
        int i2 = AbstractC1236Uf1.A;
        PN0 pn0 = new PN0(a2);
        SA l = a2.l();
        pn0.Y(vf1);
        pn0.f0(new C1541Zf1(l, vf1));
        pn0.g0();
    }
}
