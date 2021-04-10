package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.oculus.browser.R;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListItem;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListView;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: L  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L extends BaseAdapter {
    public final Context F;
    public N81 G;
    public TabModel H;
    public C2090cu0 I;

    /* renamed from: J  reason: collision with root package name */
    public final AccessibilityTabModelListView f8399J;
    public final K K = new K(this);

    public L(Context context, AccessibilityTabModelListView accessibilityTabModelListView) {
        this.F = context;
        this.f8399J = accessibilityTabModelListView;
    }

    public int getCount() {
        N81 n81 = this.G;
        if (n81 != null) {
            return n81.getCount();
        }
        return 0;
    }

    public Object getItem(int i) {
        return new Object();
    }

    public long getItemId(int i) {
        N81 n81 = this.G;
        if (n81 == null || n81.getTabAt(i) == null) {
            return -1;
        }
        return (long) this.G.getTabAt(i).getId();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        AccessibilityTabModelListItem accessibilityTabModelListItem;
        int itemId = (int) getItemId(i);
        if (view instanceof AccessibilityTabModelListItem) {
            accessibilityTabModelListItem = (AccessibilityTabModelListItem) view;
        } else {
            accessibilityTabModelListItem = (AccessibilityTabModelListItem) LayoutInflater.from(this.F).inflate(R.layout.f36580_resource_name_obfuscated_RES_2131623967, (ViewGroup) null, false);
        }
        Tab d = AbstractC1160Ta1.d(this.G, itemId);
        boolean o = this.H.o();
        boolean z = AbstractC1160Ta1.c(this.G).getId() == itemId;
        Tab tab = accessibilityTabModelListItem.d0;
        if (tab != null) {
            tab.I(accessibilityTabModelListItem.p0);
        }
        accessibilityTabModelListItem.d0 = d;
        d.A(accessibilityTabModelListItem.p0);
        accessibilityTabModelListItem.e0 = o;
        accessibilityTabModelListItem.f0 = z;
        accessibilityTabModelListItem.h();
        accessibilityTabModelListItem.g();
        K k = this.K;
        AccessibilityTabModelListView accessibilityTabModelListView = this.f8399J;
        accessibilityTabModelListItem.g0 = k;
        accessibilityTabModelListItem.j0 = accessibilityTabModelListView;
        accessibilityTabModelListItem.setTranslationX(0.0f);
        accessibilityTabModelListItem.setAlpha(1.0f);
        accessibilityTabModelListItem.setScaleX(1.0f);
        accessibilityTabModelListItem.setScaleY(1.0f);
        accessibilityTabModelListItem.setHeight(accessibilityTabModelListItem.i0);
        accessibilityTabModelListItem.b();
        accessibilityTabModelListItem.m0.removeCallbacks(accessibilityTabModelListItem.l0);
        K k2 = accessibilityTabModelListItem.g0;
        if (k2 != null) {
            boolean a2 = k2.a(accessibilityTabModelListItem.d0.getId());
            accessibilityTabModelListItem.f(a2);
            if (a2) {
                accessibilityTabModelListItem.m0.postDelayed(accessibilityTabModelListItem.l0, (long) accessibilityTabModelListItem.I);
            }
        } else {
            accessibilityTabModelListItem.f(false);
        }
        return accessibilityTabModelListItem;
    }
}
