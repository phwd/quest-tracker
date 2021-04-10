package defpackage;

import android.widget.TextView;

/* renamed from: Td  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1167Td implements AbstractC0012Ae {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1228Ud f8969a;

    public C1167Td(C1228Ud ud) {
        this.f8969a = ud;
    }

    @Override // defpackage.AbstractC0012Ae
    public int a(TextView textView) {
        return 0;
    }

    @Override // defpackage.AbstractC0012Ae
    public int b(TextView textView) {
        return Math.max(0, this.f8969a.f9035J.getLeft() - textView.getMeasuredWidth());
    }
}
