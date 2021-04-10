package defpackage;

/* renamed from: wF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5396wF0 implements AbstractC4451qk {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Runnable f11532a;

    public C5396wF0(Runnable runnable) {
        this.f11532a = runnable;
    }

    @Override // defpackage.AbstractC4451qk
    public void a() {
    }

    @Override // defpackage.AbstractC4451qk
    public void onSuccess() {
        this.f11532a.run();
    }
}
