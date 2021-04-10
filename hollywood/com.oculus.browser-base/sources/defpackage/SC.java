package defpackage;

import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import com.oculus.browser.R;
import org.chromium.chrome.browser.firstrun.DataReductionProxyFirstRunFragment;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;

/* renamed from: SC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SC implements View.OnClickListener {
    public final /* synthetic */ SwitchCompat F;

    public SC(DataReductionProxyFirstRunFragment dataReductionProxyFirstRunFragment, SwitchCompat switchCompat) {
        this.F = switchCompat;
    }

    public void onClick(View view) {
        DataReductionProxySettings d = DataReductionProxySettings.d();
        view.getContext();
        d.g(this.F.isChecked());
        if (this.F.isChecked()) {
            this.F.setText(R.string.f50480_resource_name_obfuscated_RES_2131952365);
        } else {
            this.F.setText(R.string.f50460_resource_name_obfuscated_RES_2131952363);
        }
    }
}
