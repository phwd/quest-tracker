package defpackage;

import android.view.View;

/* renamed from: Js0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0595Js0 implements YH0 {

    /* renamed from: a  reason: collision with root package name */
    public final OH0 f8319a;

    public C0595Js0(OH0 oh0) {
        this.f8319a = oh0;
    }

    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        KH0 kh0 = (KH0) obj3;
        ((View) obj2).setOnClickListener((View.OnClickListener) ((UH0) obj).g(this.f8319a));
    }
}
