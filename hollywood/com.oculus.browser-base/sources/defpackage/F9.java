package defpackage;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.banners.AppBannerManager;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: F9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F9 implements B9 {

    /* renamed from: a  reason: collision with root package name */
    public static final Q21 f7994a = new Q21("TabbedAppOverflowMenuRegroup", "action_bar", "");
    public static final Q21 b = new Q21("TabbedAppOverflowMenuThreeButtonActionbar", "three_button_action_bar", "");
    public static final Map c;
    public MenuItem d;
    public final Context e;
    public final boolean f;
    public final C1595a3 g;
    public final C3261jm0 h;
    public final AbstractC0124Ca1 i;
    public final Uk1 j;
    public final View k;
    public C1128Sl l = new C1128Sl();
    public final AbstractC0956Pq0 m;
    public Callback n;
    public boolean o;
    public DU0 p;
    public int q;
    public final C2746gl0 r;
    public AbstractC2260du0 s;
    public BookmarkBridge t;
    public Runnable u;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Integer valueOf = Integer.valueOf((int) R.id.all_bookmarks_menu_id);
        Integer valueOf2 = Integer.valueOf((int) R.id.bookmark_this_page_id);
        Integer valueOf3 = Integer.valueOf((int) R.id.bookmark_this_page_chip_id);
        Integer valueOf4 = Integer.valueOf((int) R.id.add_to_bookmarks_menu_id);
        linkedHashMap.put(valueOf, new Pair(new HashSet(Arrays.asList(valueOf2, valueOf3, valueOf4)), 0));
        linkedHashMap.put(valueOf2, new Pair(new HashSet(Arrays.asList(valueOf)), 1));
        linkedHashMap.put(valueOf3, new Pair(new HashSet(Arrays.asList(valueOf)), 1));
        linkedHashMap.put(valueOf4, new Pair(new HashSet(Arrays.asList(valueOf)), 1));
        Integer valueOf5 = Integer.valueOf((int) R.id.downloads_menu_id);
        Integer valueOf6 = Integer.valueOf((int) R.id.offline_page_id);
        Integer valueOf7 = Integer.valueOf((int) R.id.offline_page_chip_id);
        Integer valueOf8 = Integer.valueOf((int) R.id.add_to_downloads_menu_id);
        linkedHashMap.put(valueOf5, new Pair(new HashSet(Arrays.asList(valueOf6, valueOf7, valueOf8)), 2));
        linkedHashMap.put(valueOf6, new Pair(new HashSet(Arrays.asList(valueOf5)), 3));
        linkedHashMap.put(valueOf7, new Pair(new HashSet(Arrays.asList(valueOf5)), 3));
        linkedHashMap.put(valueOf8, new Pair(new HashSet(Arrays.asList(valueOf5)), 3));
        c = linkedHashMap;
    }

    public F9(Context context, C1595a3 a3Var, C3261jm0 jm0, AbstractC0124Ca1 ca1, Uk1 uk1, View view, AbstractC1509Ys0 ys0, AbstractC0956Pq0 pq0, C2746gl0 gl0) {
        this.e = context;
        this.f = DeviceFormFactor.a(context);
        this.g = a3Var;
        this.h = jm0;
        this.i = ca1;
        this.j = uk1;
        this.k = view;
        this.r = gl0;
        if (ys0 != null) {
            ys0.g(this.l.b(new C9(this)));
        }
        this.m = pq0;
        D9 d9 = new D9(this);
        this.n = d9;
        ((C1078Rq0) pq0).l(d9);
        this.p = new DU0();
    }

    public static int h() {
        int i2 = i();
        if (i2 == 1 || i2 == 2) {
            return R.id.offline_page_chip_id;
        }
        return i2 == 3 ? R.id.add_to_downloads_menu_id : R.id.offline_page_id;
    }

    public static int i() {
        if (!m()) {
            return 0;
        }
        Q21 q21 = b;
        if (q21.c().equals("action_chip_view")) {
            return 1;
        }
        if (q21.c().equals("destination_chip_view")) {
            return 2;
        }
        return q21.c().equals("add_to_option") ? 3 : 0;
    }

    public static boolean m() {
        return CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuThreeButtonActionbar");
    }

    @Override // defpackage.B9
    public int a() {
        return 0;
    }

    @Override // defpackage.B9
    public boolean b() {
        return false;
    }

    @Override // defpackage.B9
    public void c(AbstractC5717y9 y9Var, View view) {
    }

    @Override // defpackage.B9
    public int d() {
        return 0;
    }

    @Override // defpackage.B9
    public void e(AbstractC5717y9 y9Var, View view) {
    }

    @Override // defpackage.B9
    public boolean f(int i2) {
        return true;
    }

    public final int g() {
        if (!CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuRegroup")) {
            return 0;
        }
        Q21 q21 = f7994a;
        if (q21.c().equals("backward_button")) {
            return 1;
        }
        return q21.c().equals("share_button") ? 2 : 0;
    }

    public void j(boolean z) {
        int i2;
        if (this.d != null) {
            Resources resources = this.e.getResources();
            Drawable icon = this.d.getIcon();
            if (z) {
                i2 = resources.getInteger(R.integer.f36110_resource_name_obfuscated_RES_2131492908);
            } else {
                i2 = resources.getInteger(R.integer.f36100_resource_name_obfuscated_RES_2131492907);
            }
            icon.setLevel(i2);
            this.d.setTitle(z ? R.string.f45280_resource_name_obfuscated_RES_2131951845 : R.string.f45270_resource_name_obfuscated_RES_2131951844);
            this.d.setTitleCondensed(resources.getString(z ? R.string.f54870_resource_name_obfuscated_RES_2131952804 : R.string.f54800_resource_name_obfuscated_RES_2131952797));
        }
    }

    public void k(MenuItem menuItem, MenuItem menuItem2, MenuItem menuItem3, Tab tab, boolean z) {
        int i2;
        this.q = 0;
        if (!z) {
            menuItem.setVisible(false);
            menuItem3.setVisible(false);
            if (menuItem2 != null) {
                menuItem2.setVisible(false);
                return;
            }
            return;
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ResolveInfo a2 = AbstractC2612fx1.a(applicationContext, AbstractC2612fx1.d(applicationContext, tab.s()));
        AbstractC3364kK0.k("Android.PrepareMenu.OpenWebApkVisibilityCheck", SystemClock.elapsedRealtime() - elapsedRealtime);
        if ((a2 == null || a2.activityInfo.packageName == null) ? false : true) {
            menuItem3.setTitle(applicationContext.getString(R.string.f54740_resource_name_obfuscated_RES_2131952791, a2.loadLabel(applicationContext.getPackageManager()).toString()));
            menuItem.setVisible(false);
            menuItem3.setVisible(true);
            if (menuItem2 != null) {
                menuItem2.setVisible(false);
                return;
            }
            return;
        }
        C5711y7 a3 = AppBannerManager.a(tab.l());
        if (menuItem2 == null || (i2 = a3.f11664a) != R.string.f54460_resource_name_obfuscated_RES_2131952763) {
            menuItem.setTitle(a3.f11664a);
            menuItem.setVisible(true);
            if (menuItem2 != null) {
                menuItem2.setVisible(false);
            }
        } else {
            menuItem2.setTitle(i2);
            menuItem2.setVisible(true);
            menuItem.setVisible(false);
        }
        menuItem3.setVisible(false);
        int i3 = a3.f11664a;
        if (i3 == R.string.f54450_resource_name_obfuscated_RES_2131952762) {
            this.q = 1;
        } else if (i3 == R.string.f54460_resource_name_obfuscated_RES_2131952763) {
            this.q = 2;
        }
    }

    public boolean l(Tab tab) {
        return false;
    }

    public void n(MenuItem menuItem, Tab tab) {
        AbstractC0956Pq0 pq0;
        if (this.t == null && (pq0 = this.m) != null) {
            this.t = (BookmarkBridge) ((C1078Rq0) pq0).H;
        }
        BookmarkBridge bookmarkBridge = this.t;
        if (bookmarkBridge == null) {
            menuItem.setEnabled(false);
        } else {
            menuItem.setEnabled(bookmarkBridge.f());
        }
        BookmarkBridge bookmarkBridge2 = this.t;
        if (bookmarkBridge2 != null && bookmarkBridge2.e(tab)) {
            menuItem.setIcon(R.drawable.f28580_resource_name_obfuscated_RES_2131230898);
            menuItem.setChecked(true);
            menuItem.setTitleCondensed(this.e.getString(R.string.f51850_resource_name_obfuscated_RES_2131952502));
            return;
        }
        menuItem.setIcon(R.drawable.f28570_resource_name_obfuscated_RES_2131230897);
        menuItem.setChecked(false);
        menuItem.setTitleCondensed(this.e.getString(R.string.f54470_resource_name_obfuscated_RES_2131952764));
    }
}
