package org.chromium.chrome.browser.download.settings;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadLocationPreferenceDialog extends AbstractDialogInterface$OnClickListenerC1632aF0 {
    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void m1(View view) {
        ((ListView) view.findViewById(R.id.location_preference_list_view)).setAdapter((ListAdapter) ((DownloadLocationPreference) l1()).z0);
        super.m1(view);
    }

    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void o1(boolean z) {
    }
}
