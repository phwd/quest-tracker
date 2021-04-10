package defpackage;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: hN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC2857hN implements DialogInterface.OnClickListener {
    public final /* synthetic */ Intent F;
    public final /* synthetic */ boolean G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ String I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ String f10067J;
    public final /* synthetic */ C3198jN K;

    public DialogInterface$OnClickListenerC2857hN(C3198jN jNVar, Intent intent, boolean z, boolean z2, String str, String str2) {
        this.K = jNVar;
        this.F = intent;
        this.G = z;
        this.H = z2;
        this.I = str;
        this.f10067J = str2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            C3198jN.p(this.F, this.G, this.K.f10201a);
            C2003cN cNVar = (C2003cN) this.K.f10201a;
            Tab tab = cNVar.b;
            if ((tab != null && !tab.x() && cNVar.b.isInitialized()) && this.H) {
                ((C2003cN) this.K.f10201a).g();
            }
        } catch (ActivityNotFoundException unused) {
            C3198jN.h(this.I, this.f10067J, this.F.getDataString(), this.K.f10201a, this.H, true);
        }
    }
}
