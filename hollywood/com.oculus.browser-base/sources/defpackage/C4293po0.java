package defpackage;

import android.content.Context;
import android.content.Intent;
import com.oculus.browser.R;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.components.permissions.nfc.NfcSystemLevelSetting;

/* renamed from: po0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4293po0 extends QX0 {
    public C4293po0(BrowserContextHandle browserContextHandle) {
        super(browserContextHandle, 13, "");
    }

    @Override // defpackage.QX0
    public boolean h() {
        return NfcSystemLevelSetting.isNfcSystemLevelSettingEnabled();
    }

    @Override // defpackage.QX0
    public Intent k(Context context) {
        return NfcSystemLevelSetting.a();
    }

    @Override // defpackage.QX0
    public String l(Context context) {
        return context.getResources().getString(R.string.f46850_resource_name_obfuscated_RES_2131952002);
    }

    @Override // defpackage.QX0
    public String m(Context context) {
        return context.getResources().getString(R.string.f46860_resource_name_obfuscated_RES_2131952003);
    }

    @Override // defpackage.QX0
    public boolean s() {
        return NfcSystemLevelSetting.isNfcAccessPossible();
    }
}
