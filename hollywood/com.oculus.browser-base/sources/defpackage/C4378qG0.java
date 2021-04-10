package defpackage;

import android.content.Intent;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.photo_picker.DecoderService;

/* renamed from: qG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4378qG0 implements Q31 {
    @Override // defpackage.Q31
    public Object get() {
        return new Intent(ContextUtils.getApplicationContext(), DecoderService.class);
    }
}
