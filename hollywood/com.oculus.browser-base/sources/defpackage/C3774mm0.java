package defpackage;

import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: mm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3774mm0 {

    /* renamed from: a  reason: collision with root package name */
    public Object f10446a;
    public final C1322Vq0 b = new C1322Vq0();

    public C3774mm0(Object obj) {
        this.f10446a = obj;
    }

    public void a(Object obj) {
        Object obj2 = ThreadUtils.f10596a;
        if (!Objects.equals(this.f10446a, obj)) {
            this.f10446a = obj;
            Iterator it = this.b.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC1139Sq0) uq0.next()).a();
                } else {
                    return;
                }
            }
        }
    }
}
