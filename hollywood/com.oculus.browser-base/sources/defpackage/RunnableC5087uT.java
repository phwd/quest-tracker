package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: uT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5087uT implements Runnable {
    public final /* synthetic */ ArrayList F;
    public final /* synthetic */ Map G;

    public RunnableC5087uT(AbstractC5257vT vTVar, ArrayList arrayList, Map map) {
        this.F = arrayList;
        this.G = map;
    }

    public void run() {
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.F.get(i);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.setTransitionName((String) this.G.get(view.getTransitionName()));
        }
    }
}
