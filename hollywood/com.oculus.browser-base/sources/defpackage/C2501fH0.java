package defpackage;

import org.chromium.components.signin.identitymanager.ProfileOAuth2TokenServiceDelegate;

/* renamed from: fH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2501fH0 implements AbstractC2672gH0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f9913a;
    public final /* synthetic */ ProfileOAuth2TokenServiceDelegate b;

    public C2501fH0(ProfileOAuth2TokenServiceDelegate profileOAuth2TokenServiceDelegate, String str) {
        this.b = profileOAuth2TokenServiceDelegate;
        this.f9913a = str;
    }

    @Override // defpackage.AbstractC2672gH0
    public void a(Object obj) {
        Boolean bool = (Boolean) obj;
    }

    @Override // defpackage.AbstractC2672gH0
    public void b(boolean z) {
        StringBuilder i = AbstractC2531fV.i("Failed to invalidate auth token: ");
        i.append(this.f9913a);
        AbstractC1220Ua0.a("OAuth2TokenService", i.toString(), new Object[0]);
    }

    @Override // defpackage.AbstractC2672gH0
    public Object run() {
        this.b.H.c(this.f9913a);
        return Boolean.TRUE;
    }
}
