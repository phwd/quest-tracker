package defpackage;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;
import org.chromium.chrome.browser.autofill.settings.AndroidPaymentAppsFragment;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;
import org.chromium.components.browser_ui.settings.TextMessagePreference;

/* renamed from: g6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2638g6 implements ServiceWorkerPaymentAppBridge.GetServiceWorkerPaymentAppsInfoCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AndroidPaymentAppsFragment f9976a;

    public C2638g6(AndroidPaymentAppsFragment androidPaymentAppsFragment) {
        this.f9976a = androidPaymentAppsFragment;
    }

    @Override // org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge.GetServiceWorkerPaymentAppsInfoCallback
    public void a(Map map) {
        Drawable drawable;
        AndroidPaymentAppsFragment androidPaymentAppsFragment = this.f9976a;
        HashMap hashMap = new HashMap();
        List<ResolveInfo> c = PackageManagerUtils.c(new Intent("org.chromium.intent.action.PAY"), 0);
        if (!c.isEmpty()) {
            for (ResolveInfo resolveInfo : c) {
                CharSequence loadLabel = resolveInfo.loadLabel(ContextUtils.getApplicationContext().getPackageManager());
                if (!TextUtils.isEmpty(loadLabel)) {
                    hashMap.put(resolveInfo.activityInfo.packageName, new Pair(loadLabel.toString(), resolveInfo.loadIcon(ContextUtils.getApplicationContext().getPackageManager())));
                }
            }
        }
        int i = AndroidPaymentAppsFragment.G0;
        Objects.requireNonNull(androidPaymentAppsFragment);
        if (!hashMap.isEmpty() || !map.isEmpty()) {
            for (Map.Entry entry : hashMap.entrySet()) {
                C2467f6 f6Var = new C2467f6(androidPaymentAppsFragment.z0.f11127a);
                f6Var.V((CharSequence) ((Pair) entry.getValue()).first);
                f6Var.N((Drawable) ((Pair) entry.getValue()).second);
                androidPaymentAppsFragment.z0.g.a0(f6Var);
            }
            for (Map.Entry entry2 : map.entrySet()) {
                C2467f6 f6Var2 = new C2467f6(androidPaymentAppsFragment.z0.f11127a);
                f6Var2.V((CharSequence) ((Pair) entry2.getValue()).first);
                f6Var2.T((CharSequence) entry2.getKey());
                if (((Pair) entry2.getValue()).second == null) {
                    drawable = new ColorDrawable(0);
                } else {
                    drawable = new BitmapDrawable(androidPaymentAppsFragment.I(), (Bitmap) ((Pair) entry2.getValue()).second);
                }
                if (f6Var2.P != drawable) {
                    f6Var2.P = drawable;
                    f6Var2.O = 0;
                    f6Var2.s();
                }
                androidPaymentAppsFragment.z0.g.a0(f6Var2);
            }
            TextMessagePreference textMessagePreference = new TextMessagePreference(androidPaymentAppsFragment.z0.f11127a, null);
            textMessagePreference.U(R.string.f58200_resource_name_obfuscated_RES_2131953137);
            textMessagePreference.w0 = Boolean.FALSE;
            androidPaymentAppsFragment.z0.g.a0(textMessagePreference);
        }
    }
}
