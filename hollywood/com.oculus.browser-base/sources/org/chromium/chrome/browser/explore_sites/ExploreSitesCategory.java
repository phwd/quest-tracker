package org.chromium.chrome.browser.explore_sites;

import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExploreSitesCategory {

    /* renamed from: a  reason: collision with root package name */
    public int f10667a;
    public List b = new ArrayList();
    public int c;

    public ExploreSitesCategory(int i, int i2, String str, int i3, int i4) {
        this.f10667a = i2;
    }

    public static ExploreSitesCategory createAndAppendToList(int i, int i2, String str, int i3, int i4, List list) {
        ExploreSitesCategory exploreSitesCategory = new ExploreSitesCategory(i, i2, str, i3, i4);
        list.add(exploreSitesCategory);
        return exploreSitesCategory;
    }
}
