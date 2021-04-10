package defpackage;

import android.text.TextUtils;
import java.util.Objects;

/* renamed from: EV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class EV implements OU0 {

    /* renamed from: a  reason: collision with root package name */
    public final FV f7963a;

    public EV(FV fv) {
        this.f7963a = fv;
    }

    @Override // defpackage.OU0
    public void a(String str) {
        FV fv = this.f7963a;
        Objects.requireNonNull(fv);
        if (TextUtils.equals(str, "ui_theme_setting")) {
            fv.f();
        }
    }
}
