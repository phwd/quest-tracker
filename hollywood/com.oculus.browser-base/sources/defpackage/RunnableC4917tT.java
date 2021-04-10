package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: tT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4917tT implements Runnable {
    public final /* synthetic */ ArrayList F;
    public final /* synthetic */ Map G;

    public RunnableC4917tT(AbstractC5257vT vTVar, ArrayList arrayList, Map map) {
        this.F = arrayList;
        this.G = map;
    }

    public void run() {
        String str;
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.F.get(i);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                Iterator it = this.G.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (transitionName.equals(entry.getValue())) {
                        str = (String) entry.getKey();
                        break;
                    }
                }
                view.setTransitionName(str);
            }
        }
    }
}
