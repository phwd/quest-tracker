package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: gu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2773gu0 implements AbstractC3114iu0 {
    @Override // defpackage.AbstractC3114iu0
    public boolean a(String str, PackageManager packageManager, C2229dj1 dj1) {
        dj1.b();
        String str2 = dj1.b;
        if (str2 == null) {
            throw new IllegalStateException();
        } else if (!str2.equals(str)) {
            return false;
        } else {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 134217728);
            ArrayList arrayList = new ArrayList();
            SigningInfo signingInfo = packageInfo.signingInfo;
            if (signingInfo.hasMultipleSigners()) {
                for (Signature signature : signingInfo.getApkContentsSigners()) {
                    arrayList.add(AbstractC3285ju0.a(signature));
                }
            } else {
                arrayList.add(AbstractC3285ju0.a(signingInfo.getSigningCertificateHistory()[0]));
            }
            if (arrayList.size() != 1) {
                return dj1.equals(C2229dj1.a(str, arrayList));
            }
            dj1.b();
            List list = dj1.c;
            if (list != null) {
                return packageManager.hasSigningCertificate(str, Arrays.copyOf((byte[]) list.get(0), ((byte[]) dj1.c.get(0)).length), 1);
            }
            throw new IllegalStateException();
        }
    }
}
