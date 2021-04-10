package defpackage;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.PackageManagerUtils;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Jp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionMode$CallbackC0588Jp implements ActionMode.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f8314a;
    public final B2 b;
    public final AbstractC3467ky c;
    public final Callback d;
    public final Q31 e;

    public ActionMode$CallbackC0588Jp(Tab tab, WebContents webContents, AbstractC3467ky kyVar, Callback callback, Q31 q31) {
        this.f8314a = tab;
        SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(webContents);
        Objects.requireNonNull(r);
        this.b = r;
        this.c = kyVar;
        this.d = callback;
        this.e = q31;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (!this.b.e()) {
            return true;
        }
        if (menuItem.getItemId() == R.id.select_action_menu_web_search) {
            LocaleManager.getInstance().f(AbstractC5112ud1.b(this.f8314a), new C0527Ip(this, ((SelectionPopupControllerImpl) this.b).Z));
            this.b.c();
        } else if (!((GT0) this.e.get()).a() || menuItem.getItemId() != R.id.select_action_menu_share) {
            return this.b.f(actionMode, menuItem);
        } else {
            AbstractC3535lK0.a("MobileActionMode.Share");
            GT0 gt0 = (GT0) this.e.get();
            WindowAndroid i = this.f8314a.i();
            String str = "";
            String str2 = ((SelectionPopupControllerImpl) this.b).Z;
            if (!TextUtils.isEmpty(str2) && str2.length() >= 100000) {
                str2 = str2.substring(0, 100000) + "…";
            }
            if (!TextUtils.isEmpty(str)) {
                str = HG.a(str);
            }
            gt0.b(new C2189dU0(i, "", str2, str, null, null, null, null, null, null), new C1915bt(true, false, false, null, true, false, null), 4);
        }
        return true;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Objects.requireNonNull(this.b);
        int i = YQ.a() ? 7 : 5;
        B2 b2 = this.b;
        ((SelectionPopupControllerImpl) b2).S = i;
        b2.g(actionMode, menu);
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = (SelectionPopupControllerImpl) this.b;
        selectionPopupControllerImpl.R = null;
        if (selectionPopupControllerImpl.Y) {
            selectionPopupControllerImpl.m();
        }
        Objects.requireNonNull(this.b);
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Objects.requireNonNull(this.b);
        AbstractC3535lK0.a("MobileActionBarShown.Floating");
        Objects.requireNonNull(this.b);
        this.b.j(actionMode, menu);
        List<ResolveInfo> b2 = PackageManagerUtils.b();
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : b2) {
            hashSet.add(resolveInfo.activityInfo.packageName);
        }
        List<ResolveInfo> c2 = PackageManagerUtils.c(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME"), 131072);
        HashSet hashSet2 = new HashSet();
        for (ResolveInfo resolveInfo2 : c2) {
            hashSet2.add(resolveInfo2.activityInfo.packageName);
        }
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (!(item.getGroupId() != R.id.select_action_menu_text_processing_menus || item.getIntent() == null || item.getIntent().getComponent() == null)) {
                String packageName = item.getIntent().getComponent().getPackageName();
                if (hashSet.contains(packageName) || hashSet2.contains(packageName)) {
                    item.setVisible(false);
                }
            }
        }
        return true;
    }
}
