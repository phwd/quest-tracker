package defpackage;

import android.content.Intent;

/* renamed from: dN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2174dN implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f9776a;
    public final /* synthetic */ Intent b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ C3198jN d;

    public C2174dN(C3198jN jNVar, String str, Intent intent, boolean z) {
        this.d = jNVar;
        this.f9776a = str;
        this.b = intent;
        this.c = z;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        if (iArr.length > 0 && iArr[0] == 0 && ((C2003cN) this.d.f10201a).m()) {
            String str = this.f9776a;
            String dataString = this.b.getDataString();
            AbstractC1652aN aNVar = this.d.f10201a;
            C3198jN.h(str, dataString, null, aNVar, this.c, ((C2003cN) aNVar).b.a());
        } else if (this.c) {
            ((C2003cN) this.d.f10201a).g();
        }
    }
}
