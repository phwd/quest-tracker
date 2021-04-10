package defpackage;

import J.N;
import android.content.DialogInterface;
import android.widget.CheckBox;
import android.widget.EditText;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: l3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC3483l3 implements DialogInterface.OnClickListener {
    public final /* synthetic */ CheckBox F;
    public final /* synthetic */ EditText G;
    public final /* synthetic */ C4167p3 H;

    public DialogInterface$OnClickListenerC3483l3(C4167p3 p3Var, CheckBox checkBox, EditText editText) {
        this.H = p3Var;
        this.F = checkBox;
        this.G = editText;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            boolean isChecked = this.F.isChecked();
            String trim = this.G.getText().toString().trim();
            String str = isChecked ? "*" : trim;
            if (!isChecked) {
                trim = "*";
            }
            SingleCategorySettings singleCategorySettings = (SingleCategorySettings) this.H.t0;
            BrowserContextHandle browserContextHandle = singleCategorySettings.G0.b;
            int i2 = (!singleCategorySettings.J0.r(8) || !singleCategorySettings.S0 ? !N.MJSt3Ocq(browserContextHandle, singleCategorySettings.J0.i()) : !singleCategorySettings.l1()) ? 1 : 2;
            int i3 = singleCategorySettings.J0.i();
            if (i3 == 0 && !trim.equals("*")) {
                trim.isEmpty();
            }
            N.Mxr_KJ0u(browserContextHandle, i3, str, trim, i2);
            if (str.equals("*")) {
                str = trim;
            }
            C1184Ti1.b(singleCategorySettings.x(), String.format(singleCategorySettings.x().getString(R.string.f64850_resource_name_obfuscated_RES_2131953802), str), 0).b.show();
            singleCategorySettings.n1();
            if (!singleCategorySettings.J0.r(18)) {
                return;
            }
            if (i2 == 2) {
                AbstractC3535lK0.a("SoundContentSetting.MuteBy.PatternException");
            } else {
                AbstractC3535lK0.a("SoundContentSetting.UnmuteBy.PatternException");
            }
        } else {
            dialogInterface.dismiss();
        }
    }
}
