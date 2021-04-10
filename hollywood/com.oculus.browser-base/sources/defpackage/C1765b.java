package defpackage;

import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;

/* renamed from: b  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1765b implements AbstractC2094cv1 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9505a = false;
    public int b;
    public final /* synthetic */ ActionBarContextView c;

    public C1765b(ActionBarContextView actionBarContextView) {
        this.c = actionBarContextView;
    }

    @Override // defpackage.AbstractC2094cv1
    public void a(View view) {
        this.f9505a = true;
    }

    @Override // defpackage.AbstractC2094cv1
    public void b(View view) {
        if (!this.f9505a) {
            ActionBarContextView actionBarContextView = this.c;
            actionBarContextView.K = null;
            C1765b.super.setVisibility(this.b);
        }
    }

    @Override // defpackage.AbstractC2094cv1
    public void c(View view) {
        C1765b.super.setVisibility(0);
        this.f9505a = false;
    }
}
