package defpackage;

import android.content.DialogInterface;
import android.content.Intent;

/* renamed from: gN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC2686gN implements DialogInterface.OnClickListener {
    public final /* synthetic */ String F;
    public final /* synthetic */ String G;
    public final /* synthetic */ Intent H;
    public final /* synthetic */ boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C3198jN f9991J;

    public DialogInterface$OnClickListenerC2686gN(C3198jN jNVar, String str, String str2, Intent intent, boolean z) {
        this.f9991J = jNVar;
        this.F = str;
        this.G = str2;
        this.H = intent;
        this.I = z;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C3198jN.h(this.F, this.G, this.H.getDataString(), this.f9991J.f10201a, this.I, true);
    }
}
