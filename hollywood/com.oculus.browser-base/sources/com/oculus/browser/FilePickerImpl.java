package com.oculus.browser;

import J.N;
import android.content.Context;
import android.content.ContextWrapper;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FilePickerImpl {

    /* renamed from: a  reason: collision with root package name */
    public long f9700a;

    public FilePickerImpl(long j) {
        this.f9700a = j;
    }

    public static FilePickerImpl create(long j) {
        return new FilePickerImpl(j);
    }

    public void ensureFilePickerPermissions() {
        Oy1 oy1;
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext instanceof Oy1) {
            oy1 = (Oy1) applicationContext;
        } else {
            oy1 = applicationContext instanceof ContextWrapper ? (Oy1) ((ContextWrapper) applicationContext).getBaseContext() : null;
        }
        if (oy1 != null) {
            WindowAndroid i = oy1.i();
            if (i.canRequestPermission("android.permission.READ_EXTERNAL_STORAGE")) {
                i.i(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, new UP(this));
            }
        }
    }

    public void handleDialogResult(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.getString("dialogId").equals("common_system_dialog_file_picker")) {
                return;
            }
            if (jSONObject.getString("action").equals("filesSelected")) {
                JSONArray jSONArray = jSONObject.getJSONArray("listSelection");
                String[] strArr = new String[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    strArr[i] = jSONArray.getString(i);
                }
                N.MGR71x5O(this.f9700a, strArr);
                return;
            }
            N.MfSJ03V$(this.f9700a);
        } catch (JSONException e) {
            StringBuilder i2 = AbstractC2531fV.i("Failed to parse dialog result: ");
            i2.append(e.toString());
            AbstractC1220Ua0.a("FilePickerImpl", i2.toString(), new Object[0]);
            N.MfSJ03V$(this.f9700a);
        }
    }
}
