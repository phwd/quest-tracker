package defpackage;

import J.N;
import org.chromium.components.signin.ChildAccountInfoFetcher;

/* renamed from: Pn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0949Pn implements T0 {

    /* renamed from: a  reason: collision with root package name */
    public final ChildAccountInfoFetcher f8713a;

    public C0949Pn(ChildAccountInfoFetcher childAccountInfoFetcher) {
        this.f8713a = childAccountInfoFetcher;
    }

    @Override // defpackage.T0
    public void a(int i) {
        ChildAccountInfoFetcher childAccountInfoFetcher = this.f8713a;
        boolean a2 = AbstractC1254Un.a(i);
        String str = childAccountInfoFetcher.c.name;
        Boolean.toString(a2);
        N.MBZpZMbr(childAccountInfoFetcher.f10890a, childAccountInfoFetcher.b, a2);
    }
}
