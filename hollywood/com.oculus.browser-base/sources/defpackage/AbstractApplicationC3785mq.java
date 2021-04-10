package defpackage;

/* renamed from: mq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractApplicationC3785mq extends TY0 {
    public static final Object H = new Object();
    public static volatile BC I;

    public AbstractApplicationC3785mq() {
        this.F = new C3272jq(new C3614lq());
    }

    public static BC g() {
        if (I == null) {
            synchronized (H) {
                if (I == null) {
                    I = new BC(new C2931hq(), new X8(), null);
                }
            }
        }
        return I;
    }

    public static boolean h(int i) {
        return (i >= 10 && i < 20) || i >= 60;
    }
}
