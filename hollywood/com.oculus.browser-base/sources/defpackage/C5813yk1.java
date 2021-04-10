package defpackage;

import android.text.TextUtils;

/* renamed from: yk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5813yk1 implements Q31 {
    @Override // defpackage.Q31
    public Object get() {
        String b = QX.b();
        return TextUtils.isEmpty(b) ? "chrome-native://newtab/" : b;
    }
}
