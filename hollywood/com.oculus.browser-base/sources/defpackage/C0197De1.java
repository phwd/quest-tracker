package defpackage;

import java.util.HashSet;
import java.util.Set;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchContext;
import org.chromium.chrome.browser.contextualsearch.CtrSuppression;

/* renamed from: De1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0197De1 {

    /* renamed from: a  reason: collision with root package name */
    public Set f7902a = new HashSet();
    public AJ0 b;
    public CtrSuppression c;

    public C0197De1(C4700sA sAVar, C5210vA vAVar, int i, int i2, ContextualSearchContext contextualSearchContext, int i3, boolean z, int i4, int i5) {
        CtrSuppression ctrSuppression = new CtrSuppression();
        this.c = ctrSuppression;
        this.f7902a.add(ctrSuppression);
        this.f7902a.add(new C2340eL());
        this.f7902a.add(new C3023iK0(sAVar));
        this.f7902a.add(new C0136Ce1(sAVar, vAVar, i, i2, z));
        this.f7902a.add(new C0075Be1(i3));
        this.f7902a.add(new C0319Fe1(contextualSearchContext));
        this.f7902a.add(new C0258Ee1(contextualSearchContext));
        this.f7902a.add(new C5516wz(contextualSearchContext));
        this.f7902a.add(new C3948nn0(sAVar, i2));
        this.f7902a.add(new C5456wf(sAVar, i2));
        this.f7902a.add(new C2532fV0(contextualSearchContext, i5));
        this.f7902a.add(new XX0(i4));
        this.f7902a.add(new C3549lR0(sAVar, vAVar, i, i2));
        AJ0 aj0 = new AJ0();
        this.b = aj0;
        this.f7902a.add(aj0);
    }
}
