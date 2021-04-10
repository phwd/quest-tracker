package org.chromium.components.payments;

import J.N;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JniPaymentApp extends PaymentApp {
    public final Handler l = new Handler();
    public final int m;
    public long n;
    public AbstractC1216Ty0 o;
    public AbstractC1277Uy0 p;

    public JniPaymentApp(String str, String str2, String str3, Bitmap bitmap, int i, long j) {
        super(str, str2, str3, new BitmapDrawable(bitmap));
        this.m = i;
        this.n = j;
    }

    public static PayerData createPayerData(String str, String str2, String str3, Address address, String str4) {
        return new PayerData(str, str2, str3, address, str4);
    }

    public static Address createShippingAddress(String str, String[] strArr, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        return new Address(str, strArr, str2, str3, str4, str5, str6, str7, str8, str9);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean A() {
        return N.MU6Mtkqz(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean B(String str, C1401Wz0 wz0) {
        return N.MRag5HOD(this.n, str, wz0 != null ? wz0.b() : null);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean C() {
        return N.MY9Q_PcC(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void D() {
        N.M1KlGngz(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void E(PaymentHandlerHost paymentHandlerHost) {
        N.M_McFosm(this.n, paymentHandlerHost);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void F(C4530rA0 ra0) {
        N.MKIICwOk(this.n, ra0.b());
    }

    public void finalize() {
        long j = this.n;
        if (j != 0) {
            N.MvY3Yqx_(j);
            this.n = 0;
        }
        super.finalize();
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void g(AbstractC1216Ty0 ty0) {
        this.o = ty0;
        N.Mix09tOZ(this.n, this);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public String h() {
        return N.MBu_znl4(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean i() {
        return N.MSRfo5q7(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean j() {
        return N.MOoH91qV(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void k() {
        N.M89HArmx(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void l() {
        long j = this.n;
        if (j != 0) {
            N.MvY3Yqx_(j);
            this.n = 0;
        }
    }

    @Override // org.chromium.components.payments.PaymentApp
    public String m() {
        return N.MJ23g7SX(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public Set n() {
        return new HashSet(Arrays.asList(N.MMxfB3ye(this.n)));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public String o() {
        return N.MVauQfUP(this.n);
    }

    public void onAbortResult(boolean z) {
        this.l.post(new E40(this, z));
    }

    public void onInvokeError(String str) {
        this.l.post(new G40(this, str));
    }

    public void onInvokeResult(String str, String str2, PayerData payerData) {
        this.l.post(new F40(this, str, str2, payerData));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public Set p() {
        return new HashSet(Arrays.asList(N.McL$JgEC(this.n)));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public int q() {
        return this.m;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public long r() {
        return N.MNEirz5D(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean s() {
        return N.MxH2M7Qu(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean t() {
        return N.MFs5Lo5_(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean u() {
        return N.Mz9bB0kb(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean v() {
        return N.McrEaHZb(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void w(String str, String str2, String str3, String str4, byte[][] bArr, Map map, C1035Qz0 qz0, List list, Map map2, C1523Yz0 yz0, List list2, AbstractC1277Uy0 uy0) {
        this.p = uy0;
        N.MdDxV11A(this.n, this);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean y() {
        return N.MfxbAxL$(this.n);
    }
}
