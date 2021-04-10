package org.chromium.chrome.browser.device_dialog;

import J.N;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BluetoothScanningPermissionDialog {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f10652a;
    public Dialog b;
    public ListView c;
    public AE d;
    public boolean e = false;
    public long f;

    public BluetoothScanningPermissionDialog(WindowAndroid windowAndroid, String str, int i, long j) {
        Activity activity = (Activity) windowAndroid.s0().get();
        this.f10652a = activity;
        this.f = j;
        Profile b2 = Profile.b();
        SpannableString spannableString = new SpannableString(str);
        C3956nq nqVar = new C3956nq(b2);
        AbstractC0229Ds0.a(spannableString, activity.getResources(), nqVar, i, false, !AbstractC1270Uv.e(activity), true);
        nqVar.a();
        SpannableString spannableString2 = new SpannableString(activity.getString(R.string.f47940_resource_name_obfuscated_RES_2131952111, new Object[]{str}));
        TextUtils.copySpansFrom(spannableString, 0, spannableString.length(), Object.class, spannableString2, spannableString2.toString().indexOf(str));
        String string = activity.getString(R.string.f47930_resource_name_obfuscated_RES_2131952110);
        String string2 = activity.getString(R.string.f47920_resource_name_obfuscated_RES_2131952109);
        String string3 = activity.getString(R.string.f47910_resource_name_obfuscated_RES_2131952108);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.f37020_resource_name_obfuscated_RES_2131624011, (ViewGroup) null);
        TextViewWithClickableSpans textViewWithClickableSpans = (TextViewWithClickableSpans) linearLayout.findViewById(R.id.dialog_title);
        textViewWithClickableSpans.setText(spannableString2);
        textViewWithClickableSpans.setMovementMethod(LinkMovementMethod.getInstance());
        TextViewWithClickableSpans textViewWithClickableSpans2 = (TextViewWithClickableSpans) linearLayout.findViewById(R.id.not_found_message);
        textViewWithClickableSpans2.setText(string);
        textViewWithClickableSpans2.setMovementMethod(LinkMovementMethod.getInstance());
        textViewWithClickableSpans2.setVisibility(0);
        this.c = (ListView) linearLayout.findViewById(R.id.items);
        AE ae = new AE(activity, false, R.layout.f37030_resource_name_obfuscated_RES_2131624012);
        this.d = ae;
        ae.setNotifyOnChange(true);
        this.c.setAdapter((ListAdapter) this.d);
        this.c.setEmptyView(textViewWithClickableSpans2);
        this.c.setDivider(null);
        ((ProgressBar) linearLayout.findViewById(R.id.progress)).setVisibility(8);
        Button button = (Button) linearLayout.findViewById(R.id.block);
        button.setText(string2);
        button.setEnabled(true);
        button.setOnClickListener(new View$OnClickListenerC5125ui(this));
        Button button2 = (Button) linearLayout.findViewById(R.id.allow);
        button2.setText(string3);
        button2.setEnabled(true);
        button2.setOnClickListener(new View$OnClickListenerC5295vi(this));
        DialogC5805yi yiVar = new DialogC5805yi(this, activity);
        this.b = yiVar;
        yiVar.requestWindowFeature(1);
        this.b.setCanceledOnTouchOutside(true);
        this.b.addContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        this.b.setOnCancelListener(new DialogInterface$OnCancelListenerC5635xi(this));
        Window window = this.b.getWindow();
        if (!DeviceFormFactor.a(activity)) {
            window.setBackgroundDrawable(new ColorDrawable(-1));
            window.setGravity(48);
            window.setLayout(-1, -2);
        }
        this.b.show();
        linearLayout.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC5465wi(this, linearLayout));
    }

    public static BluetoothScanningPermissionDialog create(WindowAndroid windowAndroid, String str, int i, long j) {
        return new BluetoothScanningPermissionDialog(windowAndroid, str, i, j);
    }

    public final void a(int i) {
        long j = this.f;
        if (j != 0) {
            N.MTfcgGhg(j, i);
        }
    }

    public void addOrUpdateDevice(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = this.f10652a.getString(R.string.f47900_resource_name_obfuscated_RES_2131952107, new Object[]{str});
        }
        this.d.a(str, str2, null, null);
        this.c.setVisibility(0);
    }

    public final /* synthetic */ void b() {
        a(1);
        this.b.setOnDismissListener(null);
        this.b.dismiss();
    }

    public final /* synthetic */ void c() {
        a(0);
        this.b.setOnDismissListener(null);
        this.b.dismiss();
    }

    public final void closeDialog() {
        this.f = 0;
        this.b.dismiss();
    }

    public final void d(LinearLayout linearLayout, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
            View findViewById = linearLayout.findViewById(R.id.container);
            int height = this.f10652a.getWindow().getDecorView().getHeight();
            float f2 = this.f10652a.getResources().getDisplayMetrics().density;
            findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(AbstractC4089od0.b((((float) Math.round((((((float) height) / f2) * 0.3f) / 48.0f) - 0.5f)) + 0.5f) * 48.0f, 72.0f, 408.0f) * f2)));
        }
    }

    public final /* synthetic */ void e() {
        a(2);
    }
}
