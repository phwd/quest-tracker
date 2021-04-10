package defpackage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* renamed from: ZE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZE0 implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
    public final Preference F;

    public ZE0(Preference preference) {
        this.F = preference;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        CharSequence p = this.F.p();
        if (this.F.i0 && !TextUtils.isEmpty(p)) {
            contextMenu.setHeaderTitle(p);
            contextMenu.add(0, 0, 0, R.string.f50200_resource_name_obfuscated_RES_2131952337).setOnMenuItemClickListener(this);
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        CharSequence p = this.F.p();
        ((ClipboardManager) this.F.F.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Preference", p));
        Context context = this.F.F;
        Toast.makeText(context, context.getString(R.string.f59030_resource_name_obfuscated_RES_2131953220, p), 0).show();
        return true;
    }
}
