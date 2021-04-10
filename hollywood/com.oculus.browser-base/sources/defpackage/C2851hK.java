package defpackage;

import android.view.MenuItem;
import com.oculus.browser.R;

/* renamed from: hK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2851hK implements AbstractC4790sj1 {
    public final /* synthetic */ View$OnClickListenerC3876nK F;

    public C2851hK(View$OnClickListenerC3876nK nKVar) {
        this.F = nKVar;
    }

    @Override // defpackage.AbstractC4790sj1
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.delete_menu_id) {
            this.F.Z.run();
            this.F.b();
            return true;
        } else if (menuItem.getItemId() != R.id.help_menu_id) {
            return true;
        } else {
            View$OnClickListenerC3876nK nKVar = this.F;
            AbstractC0073Be.c(nKVar.G, nKVar.b0);
            return true;
        }
    }
}
