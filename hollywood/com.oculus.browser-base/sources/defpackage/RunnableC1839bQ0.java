package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.searchwidget.SearchActivity;

/* renamed from: bQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1839bQ0 implements Runnable {
    public final SearchActivity F;

    public RunnableC1839bQ0(SearchActivity searchActivity) {
        this.F = searchActivity;
    }

    public void run() {
        SearchActivity searchActivity = this.F;
        searchActivity.r0 = true;
        String str = searchActivity.s0;
        if (str != null) {
            searchActivity.C0(str, searchActivity.t0, searchActivity.u0, searchActivity.v0);
        }
        CustomTabsConnection.f().r();
        searchActivity.w0.A(U20.d(searchActivity.getIntent(), "org.chromium.chrome.browser.searchwidget.START_VOICE_SEARCH", false));
        AbstractC3535lK0.a("SearchWidget.WidgetSelected");
        Objects.requireNonNull(SearchActivity.A0());
    }
}
