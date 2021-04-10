package org.chromium.chrome.browser.history;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HistoryManagerToolbar extends AbstractView$OnClickListenerC2014cS0 {
    public HistoryManagerToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        t(R.menu.f42410_resource_name_obfuscated_RES_2131689475);
        ((C4616ri0) p()).findItem(R.id.selection_mode_open_in_incognito).setTitle(R.string.f49740_resource_name_obfuscated_RES_2131952291);
        if (!N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "history.deleting_enabled")) {
            ((C4616ri0) p()).removeItem(R.id.selection_mode_delete_menu_id);
        }
        if (!N.M$3vpOHw()) {
            ((C4616ri0) p()).removeItem(R.id.selection_mode_open_in_incognito);
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void T(boolean z) {
        if (this.y0) {
            this.D0 = z;
            Y();
        }
        throw null;
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void U() {
        super.U();
        throw null;
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0, defpackage.AbstractC3039iS0
    public void b(List list) {
        MenuItem menuItem;
        boolean z = this.v0;
        super.b(list);
        if (this.v0) {
            int size = this.w0.c.size();
            View findViewById = findViewById(R.id.selection_mode_delete_menu_id);
            boolean z2 = false;
            if (findViewById != null) {
                findViewById.setContentDescription(getResources().getQuantityString(R.plurals.f42590_resource_name_obfuscated_RES_2131820551, size, Integer.valueOf(size)));
            }
            Menu p = p();
            int i = 0;
            while (true) {
                C4616ri0 ri0 = (C4616ri0) p;
                if (i >= ri0.size()) {
                    menuItem = null;
                    break;
                }
                menuItem = ri0.getItem(i);
                if (menuItem.getItemId() == R.id.selection_mode_copy_link) {
                    break;
                }
                i++;
            }
            if (size == 1) {
                z2 = true;
            }
            menuItem.setVisible(z2);
            if (!z) {
                throw null;
            }
        }
    }
}
