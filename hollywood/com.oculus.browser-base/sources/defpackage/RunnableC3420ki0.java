package defpackage;

/* renamed from: ki0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3420ki0 implements Runnable {
    public final C3762mi0 F;

    public RunnableC3420ki0(C3762mi0 mi0) {
        this.F = mi0;
    }

    public void run() {
        Integer num;
        C3762mi0 mi0 = this.F;
        mi0.d = false;
        Integer num2 = mi0.c;
        if (num2 != null && mi0.b != num2.intValue()) {
            int intValue = mi0.c.intValue();
            mi0.c = null;
            mi0.d(intValue);
        } else if (mi0.e && mi0.b == 2 && (num = (Integer) mi0.f.get()) != null) {
            mi0.d(num.intValue());
        }
    }
}
