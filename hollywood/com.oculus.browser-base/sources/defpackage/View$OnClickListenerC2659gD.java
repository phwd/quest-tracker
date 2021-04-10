package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.datareduction.settings.DataReductionStatsPreference;

/* renamed from: gD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC2659gD implements View.OnClickListener {
    public final /* synthetic */ DataReductionStatsPreference F;

    public View$OnClickListenerC2659gD(DataReductionStatsPreference dataReductionStatsPreference) {
        this.F = dataReductionStatsPreference;
    }

    public void onClick(View view) {
        DialogInterface$OnClickListenerC2488fD fDVar = new DialogInterface$OnClickListenerC2488fD(this);
        C2290e4 e4Var = new C2290e4(this.F.F, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(R.string.f50680_resource_name_obfuscated_RES_2131952385);
        e4Var.c(R.string.f50670_resource_name_obfuscated_RES_2131952384);
        e4Var.e(R.string.f50660_resource_name_obfuscated_RES_2131952383, fDVar);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, fDVar);
        e4Var.i();
    }
}
