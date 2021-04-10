package org.chromium.chrome.browser.autofill;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.components.autofill.AutofillSuggestion;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillPopupBridge implements AbstractC0070Bd, DialogInterface.OnClickListener {
    public final long F;
    public final C3066ie G;
    public DialogC2461f4 H;
    public final Context I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC3808mx1 f10609J;

    public AutofillPopupBridge(View view, long j, WindowAndroid windowAndroid) {
        this.F = j;
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity != null) {
            Configuration configuration = activity.getResources().getConfiguration();
            if (!(N.M09VlOh_("AutofillRefreshStyleAndroid") && configuration.orientation == 2 && !configuration.isLayoutSizeAtLeast(4))) {
                C3066ie ieVar = new C3066ie(activity, view, this);
                this.G = ieVar;
                this.I = activity;
                ChromeActivity chromeActivity = (ChromeActivity) activity;
                chromeActivity.S0.f10290a.P = ieVar;
                this.f10609J = AbstractC3637lx1.a(chromeActivity.R0());
                return;
            }
        }
        this.G = null;
        this.I = null;
    }

    public static void addToAutofillSuggestionArray(AutofillSuggestion[] autofillSuggestionArr, int i, String str, String str2, String str3, int i2, boolean z, int i3, boolean z2, boolean z3, boolean z4) {
        autofillSuggestionArr[i] = new AutofillSuggestion(str, str2, str3, i2 == 0 ? 0 : i2, z, i3, z2, z3, z4);
    }

    public static AutofillPopupBridge create(View view, long j, WindowAndroid windowAndroid) {
        return new AutofillPopupBridge(view, j, windowAndroid);
    }

    public static AutofillSuggestion[] createAutofillSuggestionArray(int i) {
        return new AutofillSuggestion[i];
    }

    @Override // defpackage.AbstractC0070Bd
    public void a(int i) {
        N.Mfhlibrm(this.F, this, i);
    }

    @Override // defpackage.AbstractC0070Bd
    public void b(int i) {
        N.MD76PU5t(this.F, this, i);
    }

    @Override // defpackage.AbstractC0070Bd
    public void c() {
        int Mk31b3DX;
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) this.f10609J;
        if (webContentsAccessibilityImpl.g() && (Mk31b3DX = N.Mk31b3DX(webContentsAccessibilityImpl.K, webContentsAccessibilityImpl)) != 0) {
            webContentsAccessibilityImpl.m(Mk31b3DX);
            N.MB302_MP(webContentsAccessibilityImpl.K, webContentsAccessibilityImpl, webContentsAccessibilityImpl.U);
        }
    }

    public final void confirmDeletion(String str, String str2) {
        C2246dp1 dp1 = new C2246dp1(this.I, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        C1598a4 a4Var = dp1.f9828a;
        a4Var.d = str;
        a4Var.f = str2;
        dp1.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, null);
        dp1.e(R.string.f56550_resource_name_obfuscated_RES_2131952972, this);
        DialogC2461f4 a2 = dp1.a();
        this.H = a2;
        a2.show();
    }

    @Override // defpackage.AbstractC0070Bd
    public void d() {
        N.MOHZpjVa(this.F, this);
    }

    public final void dismiss() {
        C3066ie ieVar = this.G;
        if (ieVar != null) {
            ieVar.a();
        }
        DialogC2461f4 f4Var = this.H;
        if (f4Var != null) {
            f4Var.dismiss();
        }
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) this.f10609J;
        if (webContentsAccessibilityImpl.g()) {
            N.MdET073e(webContentsAccessibilityImpl.K, webContentsAccessibilityImpl);
            webContentsAccessibilityImpl.W = null;
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        N.MlIbag6_(this.F, this);
    }

    public final void show(AutofillSuggestion[] autofillSuggestionArr, boolean z) {
        C3066ie ieVar = this.G;
        if (ieVar != null) {
            boolean M09VlOh_ = N.M09VlOh_("AutofillRefreshStyleAndroid");
            ieVar.I = new ArrayList(Arrays.asList(autofillSuggestionArr));
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            HashSet hashSet = new HashSet();
            for (int i = 0; i < autofillSuggestionArr.length; i++) {
                int i2 = autofillSuggestionArr[i].f;
                if (i2 == -3) {
                    hashSet.add(Integer.valueOf(arrayList.size()));
                } else if (M09VlOh_ && (i2 == -13 || i2 == -9 || i2 == -7 || i2 == -5 || i2 == -4)) {
                    arrayList2.add(autofillSuggestionArr[i]);
                } else {
                    arrayList.add(autofillSuggestionArr[i]);
                }
            }
            if (!arrayList2.isEmpty() && !((EJ) ieVar.F).L.c()) {
                C0314Fd fd = new C0314Fd(ieVar.G, arrayList2, ieVar);
                EJ ej = (EJ) ieVar.F;
                ej.N.findViewById(R.id.dropdown_body_footer_divider).setVisibility(0);
                ej.P.removeAllViews();
                ej.P.addView(fd);
            }
            C0131Cd cd = new C0131Cd(ieVar.G, arrayList, hashSet, M09VlOh_);
            EJ ej2 = (EJ) ieVar.F;
            ej2.M = cd;
            ej2.O.setAdapter((ListAdapter) cd);
            ej2.L.e();
            EJ ej3 = (EJ) ieVar.F;
            ej3.H = z;
            ej3.b();
            ieVar.b().setOnItemLongClickListener(ieVar);
            ieVar.b().setAccessibilityDelegate(new C2895he(ieVar));
            AbstractC3808mx1 mx1 = this.f10609J;
            ListView b = this.G.b();
            WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) mx1;
            if (webContentsAccessibilityImpl.g()) {
                webContentsAccessibilityImpl.W = b;
                N.MMiqVowe(webContentsAccessibilityImpl.K, webContentsAccessibilityImpl);
            }
        }
    }

    public final boolean wasSuppressed() {
        return this.G == null;
    }
}
