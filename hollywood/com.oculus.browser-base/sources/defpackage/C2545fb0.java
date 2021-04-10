package defpackage;

import android.util.Property;
import org.chromium.chrome.browser.ntp.LogoView;

/* renamed from: fb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2545fb0 extends Property {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LogoView f9930a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2545fb0(LogoView logoView, Class cls, String str) {
        super(cls, str);
        this.f9930a = logoView;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((LogoView) obj).N);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        LogoView logoView = (LogoView) obj;
        Float f = (Float) obj2;
        if (logoView.N != f.floatValue()) {
            logoView.N = f.floatValue();
            this.f9930a.invalidate();
        }
    }
}
