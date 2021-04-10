package X;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import com.oculus.platform.OVRServiceManager;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1ab  reason: invalid class name and case insensitive filesystem */
public final class C09231ab {
    @Nullable
    public static C09281ag A00(@Nullable Intent intent) throws JSONException {
        Object obj;
        Uri uri;
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        jSONObject.put("action", intent.getAction());
        jSONObject.put("package", intent.getPackage());
        jSONObject.put("type", intent.getType());
        Uri data = intent.getData();
        if (data != null) {
            arrayList.add(data);
            jSONObject.put("data", C09221aa.A00(data).A00());
        }
        ClipData A00 = C09301aj.A00(intent);
        if (A00 != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < A00.getItemCount(); i++) {
                ClipData.Item itemAt = A00.getItemAt(i);
                if (!(itemAt == null || (uri = itemAt.getUri()) == null)) {
                    arrayList.add(uri);
                    jSONArray.put(C09221aa.A00(uri).A00());
                }
            }
            jSONObject.put("clip_data", jSONArray);
        }
        Set<String> categories = intent.getCategories();
        if (categories != null && !categories.isEmpty()) {
            JSONArray jSONArray2 = new JSONArray();
            for (String str : categories) {
                jSONArray2.put(str);
            }
            jSONObject.put("categories", jSONArray2);
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            jSONObject.put(OVRServiceManager.IAP_COMPONENT_NAME_KEY, component.toString());
        }
        Rect sourceBounds = intent.getSourceBounds();
        if (sourceBounds != null) {
            jSONObject.put("source_bounds", sourceBounds.toString());
        }
        Bundle extras = intent.getExtras();
        if (extras != null && extras.size() > 0) {
            JSONArray jSONArray3 = new JSONArray();
            for (String str2 : extras.keySet()) {
                JSONObject jSONObject2 = new JSONObject();
                Object obj2 = extras.get(str2);
                String str3 = "";
                if (obj2 != null) {
                    str3 = obj2.getClass().getCanonicalName();
                }
                jSONObject2.put("name", str2);
                jSONObject2.put("value_type", str3);
                if (obj2 != null) {
                    URLUtil.isValidUrl(obj2.toString());
                }
                jSONArray3.put(jSONObject2);
            }
            jSONObject.put("extra_names", jSONArray3);
        }
        Intent selector = intent.getSelector();
        if (!(selector == null || (obj = A00(selector).A01) == null)) {
            jSONObject.put("selector", obj);
        }
        if (intent.getFlags() > 0) {
            jSONObject.put("flags", intent.getFlags());
        }
        return new C09281ag(jSONObject, arrayList);
    }
}
