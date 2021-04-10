package defpackage;

import java.util.HashMap;
import java.util.Map;

/* renamed from: k00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3304k00 {

    /* renamed from: a  reason: collision with root package name */
    public final UH0 f10253a;
    public final C2792h00 b;
    public final Runnable c;

    public C3304k00(C2792h00 h00, Runnable runnable) {
        RunnableC2963i00 i00 = new RunnableC2963i00(this);
        RunnableC3133j00 j00 = new RunnableC3133j00(this);
        Map c2 = UH0.c(AbstractC3475l00.c);
        OH0 oh0 = AbstractC3475l00.f10321a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = i00;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(oh0, lh0);
        OH0 oh02 = AbstractC3475l00.b;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = j00;
        this.f10253a = AbstractC2531fV.o(hashMap, oh02, lh02, c2, null);
        this.b = h00;
        this.c = runnable;
    }
}
