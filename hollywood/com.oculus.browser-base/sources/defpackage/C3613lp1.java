package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: lp1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3613lp1 extends AbstractC1099Sa1 {
    public final /* synthetic */ UmaSessionStats I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3613lp1(UmaSessionStats umaSessionStats, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = umaSessionStats;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        UmaSessionStats umaSessionStats = this.I;
        Objects.requireNonNull(umaSessionStats);
        WebContents l = tab.l();
        int i = 0;
        N.MZTfl9lI(l != null && l.f().p());
        if (umaSessionStats.f) {
            N.MeATiwBk();
        }
        String s = tab.s();
        if (!TextUtils.isEmpty(s) && AbstractC5154ur1.d(s)) {
            PostTask.b(C3070if1.b, new RunnableC3271jp1(s), 0);
        }
        AbstractC0124Ca1 ca1 = umaSessionStats.b;
        if (ca1 != null) {
            TabModel l2 = ((AbstractC0246Ea1) ca1).l(false);
            if (l2 != null) {
                i = l2.getCount();
            }
            N.MP6JTEGK(i);
        }
    }
}
