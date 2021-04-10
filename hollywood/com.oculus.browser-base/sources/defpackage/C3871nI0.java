package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: nI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3871nI0 implements AbstractC4451qk {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4042oI0 f10482a;

    public C3871nI0(C4042oI0 oi0) {
        this.f10482a = oi0;
    }

    @Override // defpackage.AbstractC4451qk
    public void a() {
    }

    @Override // defpackage.AbstractC4451qk
    public void onSuccess() {
        C4042oI0 oi0 = this.f10482a;
        long j = oi0.f;
        if (j != 0) {
            N.MlPuxSGY(j, oi0, Profile.b());
        }
    }
}
