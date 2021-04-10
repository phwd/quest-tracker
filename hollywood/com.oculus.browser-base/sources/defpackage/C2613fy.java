package defpackage;

import java.util.Objects;
import org.chromium.mojo.system.ResultAnd;

/* renamed from: fy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2613fy implements Cw1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2784gy f9969a;

    public C2613fy(C2784gy gyVar, AbstractC2442ey eyVar) {
        this.f9969a = gyVar;
    }

    @Override // defpackage.Cw1
    public void a(int i) {
        ResultAnd h0;
        C2784gy gyVar = this.f9969a;
        Objects.requireNonNull(gyVar);
        if (i == 0) {
            do {
                try {
                    h0 = C2784gy.h0(gyVar.G, gyVar.I);
                } catch (C5475wl0 e) {
                    gyVar.f0(e);
                    return;
                }
            } while (((Boolean) h0.b).booleanValue());
            if (h0.f10995a != 17) {
                gyVar.f0(new C5475wl0(h0.f10995a));
                return;
            }
            return;
        }
        gyVar.f0(new C5475wl0(i));
    }
}
