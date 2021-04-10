package defpackage;

import java.util.List;

/* renamed from: hL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2854hL0 implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f10066a;
    public final C0110Bu b;

    public C2854hL0(Object obj) {
        this.f10066a = obj;
        this.b = C0232Du.f7922a.b(obj.getClass());
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        C0110Bu bu = this.b;
        Object obj = this.f10066a;
        C0110Bu.a((List) bu.f7768a.get(j80), r80, j80, obj);
        C0110Bu.a((List) bu.f7768a.get(EnumC3157j80.ON_ANY), r80, j80, obj);
    }
}
