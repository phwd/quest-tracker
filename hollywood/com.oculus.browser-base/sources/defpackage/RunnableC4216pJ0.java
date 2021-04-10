package defpackage;

import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.chrome.browser.share.qrcode.QRCodeGenerationRequest;

/* renamed from: pJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4216pJ0 implements Runnable {
    public final C4898tJ0 F;

    public RunnableC4216pJ0(C4898tJ0 tj0) {
        this.F = tj0;
    }

    public void run() {
        C4898tJ0 tj0 = this.F;
        String str = tj0.f;
        if (TextUtils.isEmpty(str)) {
            tj0.b.m(AbstractC5578xJ0.b, tj0.f11337a.getResources().getString(R.string.f59690_resource_name_obfuscated_RES_2131953286));
        } else {
            new QRCodeGenerationRequest(str, new C4557rJ0(tj0, str));
        }
    }
}
