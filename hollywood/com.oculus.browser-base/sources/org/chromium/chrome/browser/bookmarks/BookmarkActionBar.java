package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import com.oculus.browser.R;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BookmarkActionBar extends AbstractView$OnClickListenerC2014cS0 implements AbstractC4790sj1, QI {
    public BookmarkActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        h();
        this.I.setOnClickListener(this);
        t(R.menu.f42380_resource_name_obfuscated_RES_2131689472);
        this.o0 = this;
        ((C4616ri0) p()).findItem(R.id.selection_mode_edit_menu_id).setTitle(R.string.f51850_resource_name_obfuscated_RES_2131952502);
        ((C4616ri0) p()).findItem(R.id.selection_mode_move_menu_id).setTitle(R.string.f48000_resource_name_obfuscated_RES_2131952117);
        ((C4616ri0) p()).findItem(R.id.selection_mode_delete_menu_id).setTitle(R.string.f47980_resource_name_obfuscated_RES_2131952115);
        ((C4616ri0) p()).findItem(R.id.selection_open_in_incognito_tab_id).setTitle(R.string.f49740_resource_name_obfuscated_RES_2131952291);
        ((C4616ri0) p()).setGroupEnabled(R.id.selection_mode_menu_group, false);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void R() {
        if (this.x0) {
            super.R();
            return;
        }
        throw null;
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void U() {
        super.U();
        ((C4616ri0) p()).findItem(R.id.search_menu_id).setVisible(false);
        ((C4616ri0) p()).findItem(R.id.edit_menu_id).setVisible(false);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0, defpackage.AbstractC3039iS0
    public void b(List list) {
        super.b(list);
    }

    @Override // defpackage.AbstractC4790sj1
    public boolean onMenuItemClick(MenuItem menuItem) {
        s();
        if (menuItem.getItemId() == R.id.edit_menu_id) {
            getContext();
            throw null;
        } else if (menuItem.getItemId() == R.id.close_menu_id) {
            getContext();
            return true;
        } else if (menuItem.getItemId() == R.id.search_menu_id) {
            throw null;
        } else {
            throw null;
        }
    }
}
