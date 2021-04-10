package defpackage;

import android.content.pm.ProviderInfo;
import org.chromium.base.ContextUtils;

/* renamed from: Rw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1090Rw0 {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f8862a;

    public final boolean a() {
        if (f8862a == null) {
            boolean z = false;
            ProviderInfo resolveContentProvider = ContextUtils.getApplicationContext().getPackageManager().resolveContentProvider("com.android.partnerbrowsercustomizations", 0);
            if (resolveContentProvider != null) {
                if ((resolveContentProvider.applicationInfo.flags & 1) != 0) {
                    z = true;
                } else {
                    AbstractC1220Ua0.f("PartnerCustomize", AbstractC2531fV.h(AbstractC2531fV.i("Browser Customizations content provider package, "), resolveContentProvider.packageName, ", is not a system package. This could be a malicious attempt from a third party app, so skip reading the browser content provider."), new Object[0]);
                }
            }
            f8862a = Boolean.valueOf(z);
        }
        return f8862a.booleanValue();
    }
}
