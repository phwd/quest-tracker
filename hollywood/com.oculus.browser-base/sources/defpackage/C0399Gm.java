package defpackage;

import android.widget.TextView;

/* renamed from: Gm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0399Gm implements AbstractC0012Ae {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View$OnClickListenerC0521Im f8108a;

    public C0399Gm(View$OnClickListenerC0521Im im) {
        this.f8108a = im;
    }

    @Override // defpackage.AbstractC0012Ae
    public int a(TextView textView) {
        return 0;
    }

    @Override // defpackage.AbstractC0012Ae
    public int b(TextView textView) {
        return Math.max(0, this.f8108a.U.getLeft() - textView.getMeasuredWidth());
    }
}
