package defpackage;

import android.content.Context;
import org.chromium.base.ThreadUtils;

/* renamed from: rm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4628rm0 implements Runnable {
    public final /* synthetic */ Context F;
    public final /* synthetic */ C2046cf1 G;
    public final /* synthetic */ AbstractC0804Ne H;
    public final /* synthetic */ AbstractC4798sm0 I;

    public RunnableC4628rm0(AbstractC4798sm0 sm0, Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        this.I = sm0;
        this.F = context;
        this.G = cf1;
        this.H = ne;
    }

    public void run() {
        Object obj = ThreadUtils.f10596a;
        AbstractC4798sm0 sm0 = this.I;
        if (!sm0.b) {
            sm0.f(this.F, this.G, this.H);
        }
    }
}
