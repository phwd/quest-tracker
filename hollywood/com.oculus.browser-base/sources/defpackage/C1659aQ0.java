package defpackage;

import org.chromium.chrome.browser.searchwidget.SearchActivity;

/* renamed from: aQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1659aQ0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final SearchActivity f9430a;

    public C1659aQ0(SearchActivity searchActivity) {
        this.f9430a = searchActivity;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        SearchActivity searchActivity = this.f9430a;
        Boolean bool = (Boolean) obj;
        if (!searchActivity.v()) {
            if (bool == null || !bool.booleanValue()) {
                AbstractC1220Ua0.a("searchwidget", "User failed to select a default search engine.", new Object[0]);
                searchActivity.finish();
                return;
            }
            searchActivity.W.post(new RunnableC1839bQ0(searchActivity));
        }
    }
}
