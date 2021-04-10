package org.chromium.chrome.browser.device_dialog;

import J.N;
import android.app.Activity;
import android.text.SpannableString;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UsbChooserDialog implements AbstractC3145j40 {

    /* renamed from: a  reason: collision with root package name */
    public C3316k40 f10653a;
    public long b;

    public UsbChooserDialog(long j) {
        this.b = j;
    }

    public static UsbChooserDialog create(WindowAndroid windowAndroid, String str, int i, long j) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            return null;
        }
        UsbChooserDialog usbChooserDialog = new UsbChooserDialog(j);
        Profile b2 = Profile.b();
        SpannableString spannableString = new SpannableString(str);
        C3956nq nqVar = new C3956nq(b2);
        AbstractC0229Ds0.a(spannableString, activity.getResources(), nqVar, i, false, !AbstractC1270Uv.e(activity), true);
        nqVar.a();
        SpannableString spannableString2 = new SpannableString(activity.getString(R.string.f64250_resource_name_obfuscated_RES_2131953742, new Object[]{str}));
        TextUtils.copySpansFrom(spannableString, 0, spannableString.length(), Object.class, spannableString2, spannableString2.toString().indexOf(str));
        String string = activity.getString(R.string.f64240_resource_name_obfuscated_RES_2131953741);
        SpannableString a2 = FY0.a(activity.getString(R.string.f64230_resource_name_obfuscated_RES_2131953740), new EY0("<link>", "</link>", new C4467qp0(activity.getResources(), new Kr1(usbChooserDialog))));
        usbChooserDialog.f10653a = new C3316k40(activity, usbChooserDialog, new C2975i40(spannableString2, "", string, a2, a2, a2, activity.getString(R.string.f64220_resource_name_obfuscated_RES_2131953739)));
        return usbChooserDialog;
    }

    @Override // defpackage.AbstractC3145j40
    public void a(String str) {
        if (this.b == 0) {
            return;
        }
        if (str.isEmpty()) {
            N.MyQOumx4(this.b);
        } else {
            N.M8m3iwzV(this.b, str);
        }
    }

    public void addDevice(String str, String str2) {
        C3316k40 k40 = this.f10653a;
        k40.f.setVisibility(8);
        k40.k.a(str, str2, null, null);
        k40.c(2);
    }

    public final void closeDialog() {
        this.b = 0;
        this.f10653a.b.dismiss();
    }

    public final void removeDevice(String str) {
        C3316k40 k40 = this.f10653a;
        AE ae = k40.k;
        BE be = (BE) ae.L.remove(str);
        if (be != null) {
            int position = ae.getPosition(be);
            int i = ae.f7662J;
            if (position == i) {
                ae.d(-1);
            } else if (position < i) {
                ae.f7662J = i - 1;
            }
            ae.c(be.b);
            ae.remove(be);
        }
        k40.c(3);
    }

    public final void setIdleState() {
        C3316k40 k40 = this.f10653a;
        k40.f.setVisibility(8);
        k40.c(3);
    }
}
