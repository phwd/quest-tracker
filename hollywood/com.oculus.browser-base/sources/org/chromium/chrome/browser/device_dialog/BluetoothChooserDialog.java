package org.chromium.chrome.browser.device_dialog;

import J.N;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BluetoothChooserDialog implements AbstractC3145j40, HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final WindowAndroid f10651a;
    public final Activity b;
    public C3316k40 c;
    public String d;
    public int e;
    public Drawable f;
    public String g;
    public Drawable[] h;
    public long i;
    public boolean j;
    public final BluetoothAdapter k;
    public final SpannableString l;
    public final BroadcastReceiver m = new C4955ti(this);

    public BluetoothChooserDialog(WindowAndroid windowAndroid, String str, int i2, long j2) {
        this.f10651a = windowAndroid;
        Activity activity = (Activity) windowAndroid.s0().get();
        this.b = activity;
        this.d = str;
        this.e = i2;
        this.i = j2;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.k = defaultAdapter;
        this.f = f(R.drawable.f29620_resource_name_obfuscated_RES_2131231002);
        this.g = activity.getString(R.string.f47820_resource_name_obfuscated_RES_2131952099);
        this.h = new Drawable[]{f(R.drawable.f32640_resource_name_obfuscated_RES_2131231304), f(R.drawable.f32650_resource_name_obfuscated_RES_2131231305), f(R.drawable.f32660_resource_name_obfuscated_RES_2131231306), f(R.drawable.f32670_resource_name_obfuscated_RES_2131231307), f(R.drawable.f32680_resource_name_obfuscated_RES_2131231308)};
        if (defaultAdapter == null) {
            AbstractC1220Ua0.d("Bluetooth", "BluetoothChooserDialog: Default Bluetooth adapter not found.", new Object[0]);
        }
        this.l = FY0.a(activity.getString(R.string.f47800_resource_name_obfuscated_RES_2131952097), new EY0("<link>", "</link>", d(2)));
    }

    public static BluetoothChooserDialog create(WindowAndroid windowAndroid, String str, int i2, long j2) {
        if (!windowAndroid.hasPermission("android.permission.ACCESS_FINE_LOCATION") && !windowAndroid.canRequestPermission("android.permission.ACCESS_FINE_LOCATION")) {
            return null;
        }
        BluetoothChooserDialog bluetoothChooserDialog = new BluetoothChooserDialog(windowAndroid, str, i2, j2);
        Profile b2 = Profile.b();
        SpannableString spannableString = new SpannableString(bluetoothChooserDialog.d);
        boolean z = !AbstractC1270Uv.e(bluetoothChooserDialog.b);
        C3956nq nqVar = new C3956nq(b2);
        AbstractC0229Ds0.a(spannableString, bluetoothChooserDialog.b.getResources(), nqVar, bluetoothChooserDialog.e, false, z, true);
        nqVar.a();
        SpannableString spannableString2 = new SpannableString(bluetoothChooserDialog.b.getString(R.string.f47830_resource_name_obfuscated_RES_2131952100, new Object[]{bluetoothChooserDialog.d}));
        TextUtils.copySpansFrom(spannableString, 0, spannableString.length(), Object.class, spannableString2, spannableString2.toString().indexOf(bluetoothChooserDialog.d));
        String string = bluetoothChooserDialog.b.getString(R.string.f47880_resource_name_obfuscated_RES_2131952105);
        SpannableString a2 = FY0.a(bluetoothChooserDialog.b.getString(R.string.f47950_resource_name_obfuscated_RES_2131952112), new EY0("<link>", "</link>", bluetoothChooserDialog.d(0)));
        String string2 = bluetoothChooserDialog.b.getString(R.string.f47810_resource_name_obfuscated_RES_2131952098);
        SpannableString a3 = FY0.a(bluetoothChooserDialog.b.getString(R.string.f47890_resource_name_obfuscated_RES_2131952106), new EY0("<link1>", "</link1>", bluetoothChooserDialog.d(0)), new EY0("<link2>", "</link2>", bluetoothChooserDialog.d(6)));
        bluetoothChooserDialog.c = new C3316k40(bluetoothChooserDialog.b, bluetoothChooserDialog, new C2975i40(spannableString2, a2, string, a2, a3, a3, string2));
        bluetoothChooserDialog.b.registerReceiver(bluetoothChooserDialog.m, new IntentFilter("android.location.MODE_CHANGED"));
        bluetoothChooserDialog.j = true;
        return bluetoothChooserDialog;
    }

    @Override // defpackage.AbstractC3145j40
    public void a(String str) {
        if (str.isEmpty()) {
            e(1, "");
        } else {
            e(2, str);
        }
    }

    public void addOrUpdateDevice(String str, String str2, boolean z, int i2) {
        String str3;
        Drawable drawable = null;
        if (z) {
            drawable = this.f.getConstantState().newDrawable();
            str3 = this.g;
        } else if (i2 != -1) {
            drawable = this.h[i2].getConstantState().newDrawable();
            str3 = this.b.getResources().getQuantityString(R.plurals.f42970_resource_name_obfuscated_RES_2131820589, i2, Integer.valueOf(i2));
        } else {
            str3 = null;
        }
        C3316k40 k40 = this.c;
        k40.f.setVisibility(8);
        k40.k.a(str, str2, drawable, str3);
        k40.c(2);
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        if (this.i != 0) {
            for (String str : strArr) {
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    if (c()) {
                        this.c.a();
                        N.MvKl$XvB(this.i);
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public final boolean c() {
        SpannableString spannableString;
        boolean hasPermission = this.f10651a.hasPermission("android.permission.ACCESS_FINE_LOCATION");
        boolean e2 = C1159Ta0.a().e();
        if (hasPermission || this.f10651a.canRequestPermission("android.permission.ACCESS_FINE_LOCATION")) {
            EY0 ey0 = new EY0("<permission_link>", "</permission_link>", d(3));
            EY0 ey02 = new EY0("<services_link>", "</services_link>", d(4));
            if (hasPermission) {
                if (e2) {
                    return true;
                }
                spannableString = FY0.a(this.b.getString(R.string.f47870_resource_name_obfuscated_RES_2131952104), ey02);
            } else if (e2) {
                spannableString = FY0.a(this.b.getString(R.string.f47840_resource_name_obfuscated_RES_2131952101), ey0);
            } else {
                spannableString = FY0.a(this.b.getString(R.string.f47850_resource_name_obfuscated_RES_2131952102), ey0, ey02);
            }
            this.c.b(spannableString, FY0.a(this.b.getString(R.string.f47860_resource_name_obfuscated_RES_2131952103), new EY0("<link>", "</link>", d(5))));
            return false;
        }
        e(0, "");
        return false;
    }

    public void closeDialog() {
        this.i = 0;
        this.c.b.dismiss();
    }

    public final C4467qp0 d(int i2) {
        return new C4467qp0(this.b.getResources(), new C4785si(this, i2));
    }

    public final void e(int i2, String str) {
        if (this.j) {
            this.b.unregisterReceiver(this.m);
            this.j = false;
        }
        long j2 = this.i;
        if (j2 != 0) {
            N.MztfiUrN(j2, i2, str);
        }
    }

    public final Drawable f(int i2) {
        Fs1 a2 = Fs1.a(this.b.getResources(), i2, this.b.getTheme());
        Activity activity = this.b;
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        a2.setTintList(activity.getColorStateList(R.color.f12880_resource_name_obfuscated_RES_2131099978));
        return a2;
    }

    public void notifyAdapterTurnedOff() {
        this.c.b(FY0.a(this.b.getString(R.string.f47790_resource_name_obfuscated_RES_2131952096), new EY0("<link>", "</link>", d(1))), this.l);
    }

    public final void notifyAdapterTurnedOn() {
        this.c.a();
    }

    public void notifyDiscoveryState(int i2) {
        if (i2 == 0) {
            c();
        } else if (i2 == 2) {
            C3316k40 k40 = this.c;
            k40.f.setVisibility(8);
            k40.c(3);
        }
    }
}
