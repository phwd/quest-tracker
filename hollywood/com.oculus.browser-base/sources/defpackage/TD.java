package defpackage;

/* renamed from: TD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TD {

    /* renamed from: a  reason: collision with root package name */
    public XK0 f8946a;
    public XK0 b;
    public int c;
    public int d;
    public int e;
    public int f;

    public TD(XK0 xk0, XK0 xk02, int i, int i2, int i3, int i4) {
        this.f8946a = xk0;
        this.b = xk02;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("ChangeInfo{oldHolder=");
        i.append(this.f8946a);
        i.append(", newHolder=");
        i.append(this.b);
        i.append(", fromX=");
        i.append(this.c);
        i.append(", fromY=");
        i.append(this.d);
        i.append(", toX=");
        i.append(this.e);
        i.append(", toY=");
        i.append(this.f);
        i.append('}');
        return i.toString();
    }
}
