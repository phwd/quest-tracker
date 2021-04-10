package defpackage;

import android.view.View;
import android.widget.AdapterView;
import org.chromium.content.browser.picker.DateTimeSuggestion;

/* renamed from: F10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F10 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ C3684mD F;
    public final /* synthetic */ int G;
    public final /* synthetic */ double H;
    public final /* synthetic */ double I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ double f7991J;
    public final /* synthetic */ double K;
    public final /* synthetic */ P10 L;

    public F10(P10 p10, C3684mD mDVar, int i, double d, double d2, double d3, double d4) {
        this.L = p10;
        this.F = mDVar;
        this.G = i;
        this.H = d;
        this.I = d2;
        this.f7991J = d3;
        this.K = d4;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (i == this.F.getCount() - 1) {
            this.L.a();
            this.L.c(this.G, this.H, this.I, this.f7991J, this.K);
            return;
        }
        this.L.d.a(((DateTimeSuggestion) this.F.getItem(i)).f10930a);
        this.L.a();
        this.L.b = true;
    }
}
