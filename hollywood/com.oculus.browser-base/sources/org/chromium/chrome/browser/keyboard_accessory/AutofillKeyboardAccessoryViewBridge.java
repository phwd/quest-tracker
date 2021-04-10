package org.chromium.chrome.browser.keyboard_accessory;

import J.N;
import android.content.Context;
import android.content.DialogInterface;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.components.autofill.AutofillSuggestion;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillKeyboardAccessoryViewBridge implements AbstractC0070Bd, DialogInterface.OnClickListener {
    public long F;
    public C3402kc0 G;
    public Context H;
    public C1992cI0 I = new C1992cI0(2);

    public static void addToAutofillSuggestionArray(AutofillSuggestion[] autofillSuggestionArr, int i, String str, String str2, String str3, int i2, int i3, boolean z) {
        autofillSuggestionArr[i] = new AutofillSuggestion(str, str2, str3, i2 == 0 ? 0 : i2, false, i3, z, false, false);
    }

    public static AutofillKeyboardAccessoryViewBridge create() {
        return new AutofillKeyboardAccessoryViewBridge();
    }

    public static AutofillSuggestion[] createAutofillSuggestionArray(int i) {
        return new AutofillSuggestion[i];
    }

    @Override // defpackage.AbstractC0070Bd
    public void a(int i) {
        long j = this.F;
        if (j != 0) {
            N.Mg8PCuPV(j, this, i);
        }
    }

    @Override // defpackage.AbstractC0070Bd
    public void b(int i) {
        this.G.a();
        long j = this.F;
        if (j != 0) {
            N.MjgUnUA$(j, this, i);
        }
    }

    @Override // defpackage.AbstractC0070Bd
    public void c() {
    }

    public final void confirmDeletion(String str, String str2) {
        throw new Exception("Not implemented yet!");
    }

    @Override // defpackage.AbstractC0070Bd
    public void d() {
        long j = this.F;
        if (j != 0) {
            N.M$YFyQSp(j, this);
        }
    }

    public final void dismiss() {
        this.I.b(new AutofillSuggestion[0]);
        this.H = null;
    }

    public final void init(long j, WindowAndroid windowAndroid) {
        C2294e50 e50;
        Context context = (Context) windowAndroid.s0().get();
        this.H = context;
        if (context instanceof ChromeActivity) {
            C3402kc0 kc0 = ((ChromeActivity) context).S0;
            this.G = kc0;
            C1992cI0 ci0 = this.I;
            View$OnLayoutChangeListenerC4598rc0 rc0 = kc0.f10290a;
            if (rc0.c0() && (e50 = rc0.L) != null) {
                C4686s50 s50 = e50.f9829a;
                Objects.requireNonNull(s50);
                ci0.F.add(new C4345q50(s50, this));
            }
        }
        this.F = j;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        long j = this.F;
        if (j != 0) {
            N.MRSuTxnn(j, this);
        }
    }

    public final void resetNativeViewPointer() {
        this.F = 0;
    }

    public final void show(AutofillSuggestion[] autofillSuggestionArr, boolean z) {
        this.I.b(autofillSuggestionArr);
    }
}
