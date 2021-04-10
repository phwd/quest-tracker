package com.oculus.authapi.inject;

import X.BZ;
import X.C0204ez;
import X.C0225ft;
import X.Om;
import X.SZ;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Base64;
import com.facebook.acra.LogCatCollector;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.authapi.OVRAuth;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class CallerInfoProviderImpl implements OVRAuth.CallerInfoProvider {
    public Om _UL_mInjectionContext;

    @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
    public final Intent A0t(Intent intent) {
        String str;
        try {
            Context context = (Context) BZ.A02(0, 1, this._UL_mInjectionContext);
            try {
                intent.setExtrasClassLoader(context.getClassLoader());
                Bundle extras = intent.getExtras();
                if (extras == null) {
                    extras = new Bundle();
                }
                extras.setClassLoader(context.getClassLoader());
                String str2 = null;
                try {
                    str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
                    str = null;
                }
                long currentTimeMillis = System.currentTimeMillis();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("t", Long.toString(currentTimeMillis));
                    jSONObject.put("r", Long.toString(elapsedRealtime));
                    if (str != null) {
                        jSONObject.put("v", str);
                    }
                    str2 = Base64.encodeToString(jSONObject.toString().getBytes(LogCatCollector.UTF_8_ENCODING), 11);
                } catch (JSONException unused2) {
                }
                C0204ez ezVar = new C0204ez();
                ezVar.A01 = str2;
                ezVar.A00 = new ComponentName(context, "com.facebook.invalid_class.f4c3b00c");
                Intent intent2 = new Intent();
                intent2.setComponent(ezVar.A00);
                intent2.setFlags(0);
                intent2.setAction(ezVar.A01);
                intent2.setDataAndType(null, null);
                intent2.setSourceBounds(null);
                intent2.setSelector(null);
                intent2.setClipData(null);
                for (String str3 : ezVar.A02) {
                    intent2.addCategory(str3);
                }
                if (intent2.getComponent() != null) {
                    intent2.setPackage(intent2.getComponent().getPackageName());
                    extras.putParcelable("_ci_", PendingIntent.getActivity(context, 0, intent2, 1140850688));
                    intent.putExtras(extras);
                    return intent;
                }
                throw new SecurityException("Must generate PendingIntent based on an explicit intent.");
            } catch (Exception e) {
                throw new C0225ft(e);
            }
        } catch (C0225ft e2) {
            throw new RuntimeException(e2);
        }
    }

    @Inject
    public CallerInfoProviderImpl(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
    }
}
