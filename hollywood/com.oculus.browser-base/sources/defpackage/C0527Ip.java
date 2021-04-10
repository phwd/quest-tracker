package defpackage;

import java.util.Objects;

/* renamed from: Ip  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0527Ip extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ActionMode$CallbackC0588Jp f8250a;
    public final String b;

    public C0527Ip(ActionMode$CallbackC0588Jp jp, String str) {
        this.f8250a = jp;
        this.b = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ActionMode$CallbackC0588Jp jp = this.f8250a;
        String str = this.b;
        Boolean bool = (Boolean) obj;
        Objects.requireNonNull(jp);
        if (bool != null && bool.booleanValue()) {
            AbstractC3535lK0.a("MobileActionMode.WebSearch");
            jp.d.onResult(str);
        }
    }
}
