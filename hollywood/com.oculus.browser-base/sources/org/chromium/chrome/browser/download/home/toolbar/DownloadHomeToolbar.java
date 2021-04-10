package org.chromium.chrome.browser.download.home.toolbar;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadHomeToolbar extends AbstractView$OnClickListenerC2014cS0 {
    public Yo1 b1;

    public DownloadHomeToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        t(R.menu.f42400_resource_name_obfuscated_RES_2131689474);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0, defpackage.AbstractC3039iS0
    public void b(List list) {
        boolean z = this.v0;
        super.b(list);
        if (this.v0) {
            int size = this.w0.c.size();
            View findViewById = findViewById(R.id.selection_mode_share_menu_id);
            if (findViewById != null) {
                findViewById.setContentDescription(getResources().getQuantityString(R.plurals.f42600_resource_name_obfuscated_RES_2131820552, size, Integer.valueOf(size)));
            }
            View findViewById2 = findViewById(R.id.selection_mode_delete_menu_id);
            if (findViewById2 != null) {
                findViewById2.setContentDescription(getResources().getQuantityString(R.plurals.f42590_resource_name_obfuscated_RES_2131820551, size, Integer.valueOf(size)));
            }
            if (!z) {
                AbstractC3535lK0.a("Android.DownloadManager.SelectionEstablished");
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Yo1 yo1 = this.b1;
        if (yo1 != null) {
            yo1.b();
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void onFinishInflate() {
        super.onFinishInflate();
        post(new BH(this));
    }
}
