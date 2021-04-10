package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.components.media_router.BrowserMediaRouter;

/* renamed from: vl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5304vl implements Runnable {
    public final AbstractC5474wl F;
    public final String G;
    public final List H;

    public RunnableC5304vl(AbstractC5474wl wlVar, String str, List list) {
        this.F = wlVar;
        this.G = str;
        this.H = list;
    }

    public void run() {
        AbstractC5474wl wlVar = this.F;
        String str = this.G;
        List list = this.H;
        Objects.requireNonNull(wlVar);
        list.size();
        BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) wlVar.c;
        if (!browserMediaRouter.e.containsKey(str)) {
            browserMediaRouter.e.put(str, new HashMap());
        }
        Map map = (Map) browserMediaRouter.e.get(str);
        map.put(wlVar, list);
        ArrayList arrayList = new ArrayList();
        for (List list2 : map.values()) {
            arrayList.addAll(list2);
        }
        browserMediaRouter.f.put(str, arrayList);
        long j = browserMediaRouter.b;
        if (j != 0) {
            N.M2xU9exV(j, browserMediaRouter, str, arrayList.size());
        }
    }
}
