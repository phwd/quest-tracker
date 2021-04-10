package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: pR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4232pR extends ActionMode.Callback2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4403qR f11065a;

    public C4232pR(C4403qR qRVar, AbstractC4061oR oRVar) {
        this.f11065a = qRVar;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.select_action_menu_paste) {
            C4064oS0 os0 = this.f11065a.b;
            os0.f10550a.K.x0();
            os0.f10550a.p();
            actionMode.finish();
            return true;
        } else if (itemId == R.id.select_action_menu_paste_as_plain_text) {
            C4064oS0 os02 = this.f11065a.b;
            WebContentsImpl webContentsImpl = os02.f10550a.K;
            webContentsImpl.r0();
            N.MdSkKRWg(webContentsImpl.H, webContentsImpl);
            os02.f10550a.p();
            actionMode.finish();
            return true;
        } else if (itemId == R.id.select_action_menu_select_all) {
            this.f11065a.b.f10550a.E();
            actionMode.finish();
            return true;
        } else {
            Objects.requireNonNull(this.f11065a);
            return true;
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.setTitle(DeviceFormFactor.a(this.f11065a.c) ? this.f11065a.c.getString(R.string.f46510_resource_name_obfuscated_RES_2131951968) : null);
        actionMode.setSubtitle((CharSequence) null);
        Context context = this.f11065a.c;
        try {
            actionMode.getMenuInflater().inflate(R.menu.f42500_resource_name_obfuscated_RES_2131689484, menu);
        } catch (Resources.NotFoundException unused) {
            new MenuInflater(context).inflate(R.menu.f42500_resource_name_obfuscated_RES_2131689484, menu);
        }
        if (!this.f11065a.b.f10550a.k()) {
            menu.removeItem(R.id.select_action_menu_paste);
        }
        if (!this.f11065a.b.f10550a.W) {
            menu.removeItem(R.id.select_action_menu_select_all);
        }
        if (!this.f11065a.b.f10550a.l()) {
            menu.removeItem(R.id.select_action_menu_paste_as_plain_text);
        }
        MenuItem findItem = menu.findItem(R.id.select_action_menu_paste_as_plain_text);
        if (findItem != null) {
            findItem.setTitle(17039385);
        }
        menu.removeItem(R.id.select_action_menu_cut);
        menu.removeItem(R.id.select_action_menu_copy);
        menu.removeItem(R.id.select_action_menu_share);
        menu.removeItem(R.id.select_action_menu_web_search);
        Objects.requireNonNull(this.f11065a);
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        Objects.requireNonNull(this.f11065a);
        this.f11065a.d = null;
    }

    public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        rect.set(this.f11065a.e);
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Objects.requireNonNull(this.f11065a);
        return false;
    }
}
