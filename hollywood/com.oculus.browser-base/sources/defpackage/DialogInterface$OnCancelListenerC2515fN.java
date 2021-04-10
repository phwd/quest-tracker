package defpackage;

import android.content.DialogInterface;
import android.content.Intent;

/* renamed from: fN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnCancelListenerC2515fN implements DialogInterface.OnCancelListener {
    public final /* synthetic */ String F;
    public final /* synthetic */ String G;
    public final /* synthetic */ Intent H;
    public final /* synthetic */ boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C3198jN f9917J;

    public DialogInterface$OnCancelListenerC2515fN(C3198jN jNVar, String str, String str2, Intent intent, boolean z) {
        this.f9917J = jNVar;
        this.F = str;
        this.G = str2;
        this.H = intent;
        this.I = z;
    }

    public void onCancel(DialogInterface dialogInterface) {
        C3198jN.h(this.F, this.G, this.H.getDataString(), this.f9917J.f10201a, this.I, true);
    }
}
