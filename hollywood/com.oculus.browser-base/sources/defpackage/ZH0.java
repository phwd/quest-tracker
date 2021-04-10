package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: ZH0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZH0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9336a;
    public final AbstractC1821bI0 b;
    public final YH0 c;
    public final AbstractC1641aI0 d = new XH0(this);

    public ZH0(AbstractC1821bI0 bi0, Object obj, YH0 yh0, boolean z) {
        this.b = bi0;
        this.f9336a = obj;
        this.c = yh0;
        if (z) {
            Iterator it = ((ArrayList) bi0.a()).iterator();
            while (it.hasNext()) {
                this.c.a(this.b, this.f9336a, it.next());
            }
        }
        bi0.f9530a.b(this.d);
    }

    public static ZH0 a(AbstractC1821bI0 bi0, Object obj, YH0 yh0) {
        return new ZH0(bi0, obj, yh0, true);
    }

    public void b() {
        AbstractC1821bI0 bi0 = this.b;
        bi0.f9530a.c(this.d);
    }
}
