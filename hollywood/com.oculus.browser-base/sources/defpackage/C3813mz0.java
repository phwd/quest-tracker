package defpackage;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.RemoteException;
import java.util.Arrays;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;

/* renamed from: mz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3813mz0 {

    /* renamed from: a  reason: collision with root package name */
    public static C3813mz0 f10465a;
    public AbstractC2710gZ b;
    public PaymentRequestUpdateEventListener c;
    public PackageInfo d;
    public C3456ku0 e;

    public static C3813mz0 a() {
        Object obj = ThreadUtils.f10596a;
        if (f10465a == null) {
            f10465a = new C3813mz0();
        }
        return f10465a;
    }

    public boolean b(int i) {
        PackageInfo packageInfo;
        Object obj = ThreadUtils.f10596a;
        C3456ku0 ku0 = this.e;
        if (ku0 == null) {
            AbstractC1220Ua0.a("PaymentDetailsUpdate", "Caller's signature or package name does not match invoked app's.", new Object[0]);
            return false;
        }
        Objects.requireNonNull(ku0);
        String nameForUid = ContextUtils.getApplicationContext().getPackageManager().getNameForUid(i);
        if (nameForUid == null) {
            packageInfo = null;
        } else {
            packageInfo = ku0.a(nameForUid);
        }
        PackageInfo packageInfo2 = this.d;
        if (packageInfo2 == null || packageInfo == null || !packageInfo2.packageName.equals(packageInfo.packageName)) {
            AbstractC1220Ua0.a("PaymentDetailsUpdate", "Caller's signature or package name does not match invoked app's.", new Object[0]);
            return false;
        }
        boolean equals = Arrays.equals(packageInfo.signatures, this.d.signatures);
        if (!equals) {
            AbstractC1220Ua0.a("PaymentDetailsUpdate", "Caller's signature or package name does not match invoked app's.", new Object[0]);
        }
        return equals;
    }

    public boolean c() {
        Object obj = ThreadUtils.f10596a;
        return this.b != null;
    }

    public void d() {
        Object obj = ThreadUtils.f10596a;
        f10465a = null;
    }

    public final void e(String str, AbstractC2710gZ gZVar) {
        Object obj = ThreadUtils.f10596a;
        if (gZVar != null) {
            Bundle bundle = new Bundle();
            bundle.putString("error", str);
            try {
                ((C2368eZ) gZVar).d(bundle);
            } catch (RemoteException e2) {
                AbstractC1220Ua0.a("PaymentDetailsUpdate", "Error calling updateWith", e2);
            }
        }
    }
}
