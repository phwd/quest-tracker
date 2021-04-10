package defpackage;

import org.chromium.base.ContextUtils;

/* renamed from: W4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W4 implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public static X4 f9127a;

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        if (f9127a == null) {
            f9127a = new X4(ContextUtils.getApplicationContext(), null);
        }
        return f9127a;
    }
}
