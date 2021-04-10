package org.chromium.components.browser_ui.site_settings;

import android.content.res.Configuration;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearWebsiteStorageDialog extends AbstractDialogInterface$OnClickListenerC1632aF0 {
    public static Callback U0;
    public View V0;

    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void m1(View view) {
        this.V0 = view;
        int i = ClearWebsiteStorage.z0;
        ((TextView) view.findViewById(R.id.signed_out_text)).setText(R.string.f65730_resource_name_obfuscated_RES_2131953890);
        ((TextView) view.findViewById(R.id.offline_text)).setText(R.string.f65700_resource_name_obfuscated_RES_2131953887);
        super.m1(view);
    }

    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void o1(boolean z) {
        U0.onResult(Boolean.valueOf(z));
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void onConfigurationChanged(Configuration configuration) {
        this.i0 = true;
        View view = this.V0;
        if (view != null) {
            Handler handler = view.getHandler();
            View view2 = this.V0;
            view2.getClass();
            handler.post(new RunnableC2262dv(view2));
        }
    }
}
