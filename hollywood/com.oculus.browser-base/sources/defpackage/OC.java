package defpackage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;

/* renamed from: OC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OC extends DH0 {
    public static final /* synthetic */ int I = 0;

    /* renamed from: J  reason: collision with root package name */
    public int f8608J = 4;

    public OC(Activity activity) {
        super(activity);
    }

    @Override // defpackage.DH0
    public CH0 a() {
        CH0 ch0 = new CH0();
        ch0.f7799a = R.drawable.f29020_resource_name_obfuscated_RES_2131230942;
        ch0.b = R.string.f50590_resource_name_obfuscated_RES_2131952376;
        ch0.e = R.string.f50580_resource_name_obfuscated_RES_2131952375;
        ch0.g = R.string.f50470_resource_name_obfuscated_RES_2131952364;
        ch0.h = R.string.f55970_resource_name_obfuscated_RES_2131952914;
        Activity ownerActivity = getOwnerActivity();
        Resources resources = ownerActivity.getResources();
        C4467qp0 qp0 = new C4467qp0(resources, new NC(ownerActivity, resources));
        ch0.c = FY0.a(resources.getString(R.string.f50580_resource_name_obfuscated_RES_2131952375), new EY0("<link>", "</link>", qp0));
        ch0.d = true;
        return ch0;
    }

    @Override // defpackage.K4
    public void dismiss() {
        int i = this.f8608J;
        if (i < 36) {
            UC.a(i);
            this.f8608J = 36;
        }
        super.dismiss();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_secondary) {
            dismiss();
        } else if (id == R.id.button_primary) {
            this.f8608J = 0;
            DataReductionProxySettings d = DataReductionProxySettings.d();
            getContext();
            d.g(true);
            dismiss();
            C1184Ti1.b(getContext(), getContext().getString(R.string.f50490_resource_name_obfuscated_RES_2131952366), 1).b.show();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        PC.b();
    }
}
