package org.chromium.chrome.browser.explore_sites;

import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExploreSitesSite {

    /* renamed from: a  reason: collision with root package name */
    public static final NH0 f10668a = new NH0();
    public static final SH0 b = new SH0();
    public static final OH0 c = new OH0();
    public static final OH0 d = new OH0();
    public static final TH0 e = new TH0(false);
    public static final QH0 f = new QH0();
    public UH0 g;

    public ExploreSitesSite(int i, String str, String str2, boolean z) {
        NH0 nh0 = f10668a;
        SH0 sh0 = b;
        OH0 oh0 = c;
        OH0 oh02 = d;
        QH0 qh0 = f;
        Map c2 = UH0.c(new KH0[]{nh0, sh0, oh0, oh02, e, qh0});
        JH0 jh0 = new JH0(null);
        jh0.f8282a = i;
        c2.put(nh0, jh0);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = str;
        c2.put(oh0, lh0);
        LH0 lh02 = new LH0(null);
        lh02.f8415a = str2;
        c2.put(oh02, lh02);
        GH0 gh0 = new GH0(null);
        gh0.f8081a = z;
        c2.put(qh0, gh0);
        JH0 jh02 = new JH0(null);
        jh02.f8282a = -1;
        c2.put(sh0, jh02);
        this.g = new UH0(c2, null);
    }

    public static void createSiteInCategory(int i, String str, String str2, boolean z, ExploreSitesCategory exploreSitesCategory) {
        ExploreSitesSite exploreSitesSite = new ExploreSitesSite(i, str, str2, z);
        exploreSitesCategory.b.add(exploreSitesSite);
        if (exploreSitesSite.g.h(f)) {
            exploreSitesCategory.c++;
        }
    }
}
