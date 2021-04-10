package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import java.util.List;
import org.chromium.chrome.browser.homepage.settings.RadioButtonGroupHomepagePreference;
import org.chromium.components.browser_ui.widget.RadioButtonWithEditText;

/* renamed from: QJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QJ0 implements TextWatcher {
    public final /* synthetic */ RadioButtonWithEditText F;

    public QJ0(RadioButtonWithEditText radioButtonWithEditText) {
        this.F = radioButtonWithEditText;
    }

    public void afterTextChanged(Editable editable) {
        List<RJ0> list = this.F.L;
        if (list != null) {
            for (RJ0 rj0 : list) {
                RadioButtonGroupHomepagePreference radioButtonGroupHomepagePreference = (RadioButtonGroupHomepagePreference) rj0;
                if (!radioButtonGroupHomepagePreference.y0.b.equals(editable.toString())) {
                    IJ0 ij0 = radioButtonGroupHomepagePreference.y0;
                    ij0.f8217a = 1;
                    ij0.b = editable.toString();
                }
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
