package defpackage;

import J.N;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;

/* renamed from: Vp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1319Vp implements Runnable {
    public final ChromeActivity F;
    public final Tab G;
    public final C0695Li H;
    public final BookmarkBridge I;

    public RunnableC1319Vp(ChromeActivity chromeActivity, Tab tab, C0695Li li, BookmarkBridge bookmarkBridge) {
        this.F = chromeActivity;
        this.G = tab;
        this.H = li;
        this.I = bookmarkBridge;
    }

    public void run() {
        BookmarkBridge.BookmarkItem bookmarkItem;
        JW0 jw0;
        JW0 jw02;
        RecyclerView recyclerView;
        View view;
        Iterator it;
        Object obj;
        String str;
        long j;
        ChromeActivity chromeActivity = this.F;
        Tab tab = this.G;
        C0695Li li = this.H;
        BookmarkBridge bookmarkBridge = this.I;
        Objects.requireNonNull(chromeActivity);
        if (tab.x() || !tab.isInitialized()) {
            li.a();
            return;
        }
        if (N.M09VlOh_("ReadLater")) {
            String n = tab.n();
            Objects.requireNonNull(li);
            Object obj2 = ThreadUtils.f10596a;
            bookmarkItem = (BookmarkBridge.BookmarkItem) N.MqM$dEO_(li.b, li, n);
        } else {
            bookmarkItem = null;
        }
        if (bookmarkItem == null) {
            Objects.requireNonNull(bookmarkBridge);
            Object obj3 = ThreadUtils.f10596a;
            if (tab.G()) {
                j = -1;
            } else {
                j = N.MUjtS5c8(bookmarkBridge.b, bookmarkBridge, tab.l(), true);
            }
            if (j != -1) {
                bookmarkItem = li.c(new BookmarkId(j, 0));
            }
        }
        View$OnClickListenerC5098uY0 U = chromeActivity.U();
        C5638xj xjVar = chromeActivity.b1.a0;
        boolean d1 = chromeActivity.d1();
        C1380Wp wp = new C1380Wp(bookmarkItem, tab, li);
        if (bookmarkItem != null) {
            AbstractC1243Ui.g(chromeActivity, bookmarkItem.c);
            wp.onResult(bookmarkItem.c);
            return;
        }
        boolean z = CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuThreeButtonActionbar") && F9.b.c().equals("add_to_option");
        if (!CachedFeatureFlags.isEnabled("ReadLater") || z) {
            wp.onResult(AbstractC1243Ui.a(li, tab, U, chromeActivity, d1));
            return;
        }
        C0329Fi fi = new C0329Fi(chromeActivity, xjVar, li);
        AbstractC3535lK0.a("Android.Bookmarks.BottomSheet.Open");
        fi.d = new C0999Qi(wp, tab, U, li, chromeActivity, d1);
        View inflate = LayoutInflater.from(chromeActivity).inflate(R.layout.f37060_resource_name_obfuscated_RES_2131624015, (ViewGroup) null);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R.id.sheet_item_list);
        ArrayList arrayList = new ArrayList();
        Objects.requireNonNull(li);
        Object obj4 = ThreadUtils.f10596a;
        BookmarkId bookmarkId = (BookmarkId) N.MmusspW0(li.b, li);
        BookmarkId d = li.d();
        BookmarkId bookmarkId2 = (BookmarkId) N.MG_d8ZCM(li.b, li);
        ArrayList arrayList2 = new ArrayList();
        N.MOEaKJZM(li.b, li, true, false, arrayList2);
        BookmarkId bookmarkId3 = (BookmarkId) N.MTVYsNWF(li.b, li);
        ArrayList arrayList3 = new ArrayList();
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            BookmarkId bookmarkId4 = (BookmarkId) it2.next();
            if (bookmarkId4.getType() == 2) {
                arrayList.add(bookmarkId4);
                Um1.a(Profile.b()).notifyEvent("read_later_bottom_sheet_folder_seen");
            } else if (li.c(bookmarkId4).e.equals(bookmarkId3)) {
                arrayList3.add(bookmarkId4);
            }
        }
        if (li.g(d)) {
            arrayList.add(d);
        }
        if (li.g(bookmarkId)) {
            arrayList.add(bookmarkId);
        }
        if (li.g(bookmarkId2)) {
            arrayList.add(bookmarkId2);
        }
        arrayList.addAll(arrayList3);
        C4935tb0 tb0 = new C4935tb0();
        Iterator it3 = arrayList.iterator();
        int i = 2;
        int i2 = 0;
        int i3 = 1;
        while (it3.hasNext()) {
            BookmarkBridge.BookmarkItem c = fi.c.c((BookmarkId) it3.next());
            int type = c.c.getType();
            Map c2 = UH0.c(AbstractC0390Gi.e);
            OH0 oh0 = AbstractC0390Gi.f8102a;
            if (c.c.getType() == i) {
                Tm1 a2 = Um1.a(Profile.b());
                if (!a2.isInitialized() || !a2.shouldTriggerHelpUI("IPH_ReadLaterBottomSheet")) {
                    i3 = i2;
                }
                it = it3;
                SpannableString spannableString = new SpannableString(fi.b.getResources().getString(R.string.f60000_resource_name_obfuscated_RES_2131953317));
                if (i3 != 0) {
                    a2.dismissed("IPH_ReadLaterBottomSheet");
                    view = inflate;
                    recyclerView = recyclerView2;
                    jw02 = jw0;
                    obj = FY0.a(spannableString.toString(), new EY0("<new>", "</new>", new RelativeSizeSpan(0.75f), new SuperscriptSpan(), new ForegroundColorSpan(fi.b.getResources().getColor(R.color.f11460_resource_name_obfuscated_RES_2131099836))));
                } else {
                    view = inflate;
                    recyclerView = recyclerView2;
                    jw02 = jw0;
                    obj = new SpannableString(FY0.b(spannableString.toString(), new EY0("<new>", "</new>", new Object[0])));
                }
            } else {
                view = inflate;
                recyclerView = recyclerView2;
                jw02 = jw0;
                it = it3;
                obj = c.f10620a;
            }
            LH0 lh0 = new LH0(null);
            lh0.f8415a = obj;
            HashMap hashMap = (HashMap) c2;
            hashMap.put(oh0, lh0);
            OH0 oh02 = AbstractC0390Gi.b;
            int type2 = c.c.getType();
            if (type2 != 0) {
                i = 2;
                if (type2 != 2) {
                    str = null;
                } else {
                    C0695Li li2 = fi.c;
                    BookmarkId bookmarkId5 = c.c;
                    Objects.requireNonNull(li2);
                    Object obj5 = ThreadUtils.f10596a;
                    ArrayList arrayList4 = new ArrayList();
                    N.MjHaBU2n(li2.b, li2, bookmarkId5.getId(), bookmarkId5.getType(), arrayList4);
                    Iterator it4 = arrayList4.iterator();
                    int i4 = 0;
                    while (it4.hasNext()) {
                        if (!li2.c((BookmarkId) it4.next()).h) {
                            i4++;
                        }
                    }
                    if (i4 > 0) {
                        str = fi.b.getResources().getQuantityString(R.plurals.f42910_resource_name_obfuscated_RES_2131820583, i4, Integer.valueOf(i4));
                    } else {
                        str = fi.b.getResources().getString(R.string.f59900_resource_name_obfuscated_RES_2131953307);
                    }
                }
                i2 = 0;
            } else {
                C0695Li li3 = fi.c;
                BookmarkId bookmarkId6 = c.c;
                Objects.requireNonNull(li3);
                Object obj6 = ThreadUtils.f10596a;
                int M9Wq4IA6 = N.M9Wq4IA6(li3.b, li3, bookmarkId6.getId(), bookmarkId6.getType());
                if (M9Wq4IA6 > 0) {
                    String quantityString = fi.b.getResources().getQuantityString(R.plurals.f42650_resource_name_obfuscated_RES_2131820557, M9Wq4IA6, Integer.valueOf(M9Wq4IA6));
                    i2 = 0;
                    i = 2;
                    str = quantityString;
                } else {
                    i2 = 0;
                    i = 2;
                    str = fi.b.getResources().getString(R.string.f55950_resource_name_obfuscated_RES_2131952912);
                }
            }
            LH0 lh02 = new LH0(null);
            lh02.f8415a = str;
            hashMap.put(oh02, lh02);
            OH0 oh03 = AbstractC0390Gi.c;
            C1754aw0 aw0 = new C1754aw0(AbstractC1243Ui.c(fi.b, type), Integer.valueOf(AbstractC1243Ui.d(type)));
            LH0 lh03 = new LH0(null);
            lh03.f8415a = aw0;
            hashMap.put(oh03, lh03);
            OH0 oh04 = AbstractC0390Gi.d;
            RunnableC0207Di di = new RunnableC0207Di(fi, c);
            LH0 lh04 = new LH0(null);
            lh04.f8415a = di;
            i3 = 1;
            tb0.q(new C4765sb0(1, AbstractC2531fV.o(hashMap, oh04, lh04, c2, null)));
            it3 = it;
            inflate = view;
            recyclerView2 = recyclerView;
            jw0 = jw02;
        }
        jw0 = new JW0(tb0);
        jw0.v(i3, new L70(R.layout.f37070_resource_name_obfuscated_RES_2131624016), new C0085Bi());
        recyclerView2.q0(jw0);
        recyclerView2.t0(new LinearLayoutManager(fi.b));
        fi.e = new C0024Ai(inflate, new C0146Ci(recyclerView2));
        ((C5638xj) fi.f8034a).j(new C0268Ei(fi));
        ((C5638xj) fi.f8034a).u(fi.e, true);
    }
}
