package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;

/* renamed from: Yg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1481Yg {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1542Zg f9287a;

    public C1481Yg(C1542Zg zg) {
        this.f9287a = zg;
    }

    public void a(C3245jh jhVar) {
        Object obj = ThreadUtils.f10596a;
        C1542Zg zg = this.f9287a;
        zg.c = true;
        zg.d = jhVar;
        Iterator it = new ArrayList(this.f9287a.b).iterator();
        while (it.hasNext()) {
            ((C1699ah) it.next()).f0(jhVar);
        }
    }
}
