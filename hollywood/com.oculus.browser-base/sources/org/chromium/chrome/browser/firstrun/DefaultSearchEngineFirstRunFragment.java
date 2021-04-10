package org.chromium.chrome.browser.firstrun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.components.browser_ui.widget.RadioButtonLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DefaultSearchEngineFirstRunFragment extends AbstractComponentCallbacksC3550lS implements UQ {
    public Button A0;
    public int y0;
    public RadioButtonLayout z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f37810_resource_name_obfuscated_RES_2131624090, viewGroup, false);
        this.z0 = (RadioButtonLayout) inflate.findViewById(R.id.default_search_engine_dialog_options);
        Button button = (Button) inflate.findViewById(R.id.button_primary);
        this.A0 = button;
        button.setEnabled(false);
        Objects.requireNonNull(LocaleManager.getInstance());
        this.y0 = -1;
        return inflate;
    }
}
