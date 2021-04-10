package org.chromium.components.permissions.nfc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NfcSystemLevelSetting {
    public static Intent a() {
        Intent intent = new Intent("android.settings.NFC_SETTINGS");
        if (intent.resolveActivity(ContextUtils.getApplicationContext().getPackageManager()) == null) {
            return null;
        }
        return intent;
    }

    public static boolean isNfcAccessPossible() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext.checkPermission("android.permission.NFC", Process.myPid(), Process.myUid()) == 0 && NfcAdapter.getDefaultAdapter(applicationContext) != null) {
            return true;
        }
        return false;
    }

    public static boolean isNfcSystemLevelSettingEnabled() {
        if (!isNfcAccessPossible()) {
            return false;
        }
        return NfcAdapter.getDefaultAdapter(ContextUtils.getApplicationContext()).isEnabled();
    }

    public static void promptToEnableNfcSystemLevelSetting(WebContents webContents, long j) {
        WindowAndroid I = webContents.I();
        if (I == null) {
            PostTask.b(Zo1.f9374a, new RunnableC0891Oo0(j), 0);
            return;
        }
        C0830No0 no0 = new C0830No0();
        RunnableC0952Po0 po0 = new RunnableC0952Po0(j);
        C2746gl0 v0 = I.v0();
        if (v0 == null) {
            PostTask.b(Zo1.f9374a, new RunnableC0709Lo0(po0), 0);
            return;
        }
        Activity activity = (Activity) I.s0().get();
        View inflate = LayoutInflater.from(activity).inflate(R.layout.f40570_resource_name_obfuscated_RES_2131624366, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.text);
        textView.setText(R.string.f55920_resource_name_obfuscated_RES_2131952909);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.f34900_resource_name_obfuscated_RES_2131231530, 0, 0, 0);
        Resources resources = activity.getResources();
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, no0);
        hh0.e(AbstractC3258jl0.f, inflate);
        hh0.d(AbstractC3258jl0.g, resources, R.string.f55940_resource_name_obfuscated_RES_2131952911);
        hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
        hh0.d(AbstractC3258jl0.b, resources, R.string.f55920_resource_name_obfuscated_RES_2131952909);
        hh0.b(AbstractC3258jl0.n, true);
        UH0 a2 = hh0.a();
        no0.G = I;
        no0.H = po0;
        no0.F = v0;
        v0.i(a2, 1, false);
    }
}
