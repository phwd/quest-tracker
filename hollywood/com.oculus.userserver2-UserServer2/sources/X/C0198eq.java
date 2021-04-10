package X;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import com.oculus.userserver.api.ExceptionUtils;
import com.oculus.userserver.managerservice.OculusUserStore;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.eq  reason: case insensitive filesystem */
public final class C0198eq {
    @Nullable
    public static ep A00(@Nullable Intent intent) throws JSONException {
        Object obj;
        Uri uri;
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        jSONObject.put(OculusAuthorizationInterceptor.EXTRA_ACTION, intent.getAction());
        jSONObject.put("package", intent.getPackage());
        jSONObject.put(ExceptionUtils.KEY_TYPE, intent.getType());
        Uri data = intent.getData();
        if (data != null) {
            arrayList.add(data);
            jSONObject.put(OculusUserStore.KEY_DATA, fA.A00(data).A00());
        }
        ClipData A00 = C0197eo.A00(intent);
        if (A00 != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < A00.getItemCount(); i++) {
                ClipData.Item itemAt = A00.getItemAt(i);
                if (!(itemAt == null || (uri = itemAt.getUri()) == null)) {
                    arrayList.add(uri);
                    jSONArray.put(fA.A00(uri).A00());
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
            jSONObject.put("component_name", component.toString());
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
        return new ep(jSONObject, arrayList);
    }
}
