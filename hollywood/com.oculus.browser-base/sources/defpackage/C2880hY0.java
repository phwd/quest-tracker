package defpackage;

import J.N;
import android.content.Intent;
import java.util.Objects;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: hY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2880hY0 implements Ky1 {

    /* renamed from: a  reason: collision with root package name */
    public final C3221jY0 f10077a;

    public C2880hY0(C3221jY0 jy0) {
        this.f10077a = jy0;
    }

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        C3221jY0 jy0 = this.f10077a;
        Objects.requireNonNull(jy0);
        if (i == -1) {
            N.MDAxNisW(jy0.f10212a.f10933a, intent.getStringExtra("com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE"), 1);
        } else if (i == 0) {
            N.MqHdTL15(jy0.f10212a.f10933a);
        }
    }
}
