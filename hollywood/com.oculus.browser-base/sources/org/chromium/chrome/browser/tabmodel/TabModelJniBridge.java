package org.chromium.chrome.browser.tabmodel;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.Origin;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class TabModelJniBridge implements TabModel {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10777a;
    public long b;
    public boolean c;

    public TabModelJniBridge(Profile profile, boolean z) {
        this.f10777a = profile.g();
        this.c = z;
    }

    @Override // defpackage.N81
    public boolean a() {
        return this.f10777a;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public Profile b() {
        return (Profile) N.McKCR2Q3(this.b, this);
    }

    public abstract boolean closeTabAt(int i);

    public Tab createNewTabForDevTools(String str) {
        return y(false).b(new LoadUrlParams(str, 0), 2, null);
    }

    public abstract boolean createTabWithWebContents(Tab tab, Profile profile, WebContents webContents);

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void destroy() {
        if (z()) {
            N.Mg3Aho0E(this.b, this);
            this.b = 0;
        }
    }

    @Override // defpackage.N81
    public abstract int getCount();

    @Override // defpackage.N81
    public abstract Tab getTabAt(int i);

    @Override // defpackage.N81
    public abstract int index();

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public abstract boolean isActiveModel();

    public abstract boolean isSessionRestoreInProgress();

    public abstract void openNewTab(Tab tab, String str, Origin origin, String str2, ResourceRequestBody resourceRequestBody, int i, boolean z, boolean z2);

    public final void setIndex(int i) {
        ((C4423qa1) this).x(i, 3);
    }

    public abstract A61 y(boolean z);

    public boolean z() {
        return this.b != 0;
    }
}
