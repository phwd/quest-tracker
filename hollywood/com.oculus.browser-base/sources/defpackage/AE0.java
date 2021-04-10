package defpackage;

/* renamed from: AE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AE0 extends C5903zE0 {
    public final Object c = new Object();

    public AE0(int i) {
        super(i);
    }

    @Override // defpackage.C5903zE0
    public Object a() {
        Object a2;
        synchronized (this.c) {
            a2 = super.a();
        }
        return a2;
    }

    @Override // defpackage.C5903zE0
    public boolean b(Object obj) {
        boolean b;
        synchronized (this.c) {
            b = super.b(obj);
        }
        return b;
    }
}
