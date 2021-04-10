package com.oculus.browser;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebUIFetcher extends RN {

    /* renamed from: a  reason: collision with root package name */
    public QN f9716a;
    public Tx1 b;

    public WebUIFetcher(QN qn, String str, String str2, Tx1 tx1) {
        this.b = tx1;
        this.f9716a = qn;
        qn.b = this;
        qn.d(str, str2, 2, 2);
    }

    public static void fetch(String str, String str2, int i) {
        if (str2 != null && str2.length() == 0) {
            str2 = null;
        }
        new WebUIFetcher(new QN(), str, str2, new Tx1(i));
    }

    public static native void nativeResponse(String str, String str2, int i);

    @Override // defpackage.RN
    public void b(String str) {
        nativeResponse(str, null, this.b.f8998a);
    }

    @Override // defpackage.RN
    public void d(Exception exc) {
        Tx1 tx1 = this.b;
        StringBuilder i = AbstractC2531fV.i("Facebook service call failed: ");
        i.append(exc.toString());
        nativeResponse(null, i.toString(), tx1.f8998a);
    }
}
