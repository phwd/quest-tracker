package X;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import com.facebook.acra.AppComponentStats;
import com.facebook.assistant.oacr.OacrConstants;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class J7 {
    public static J6 A00(Intent intent) {
        J6 A00;
        Object obj;
        Uri uri;
        if (intent == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        jSONObject.put("action", intent.getAction());
        jSONObject.put("package", intent.getPackage());
        jSONObject.put("type", intent.getType());
        Uri data = intent.getData();
        if (data != null) {
            arrayList.add(data);
            jSONObject.put("data", JN.A00(data).A01());
        }
        ClipData A002 = J5.A00(intent);
        if (A002 != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < A002.getItemCount(); i++) {
                ClipData.Item itemAt = A002.getItemAt(i);
                if (!(itemAt == null || (uri = itemAt.getUri()) == null)) {
                    arrayList.add(uri);
                    jSONArray.put(JN.A00(uri).A01());
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
                String str3 = OacrConstants.AUTO_SPEECH_DOMAIN;
                if (obj2 != null) {
                    str3 = obj2.getClass().getCanonicalName();
                }
                jSONObject2.put(AppComponentStats.ATTRIBUTE_NAME, str2);
                jSONObject2.put("value_type", str3);
                if (obj2 != null) {
                    URLUtil.isValidUrl(obj2.toString());
                }
                jSONArray3.put(jSONObject2);
            }
            jSONObject.put("extra_names", jSONArray3);
        }
        Intent selector = intent.getSelector();
        if (!(selector == null || (A00 = A00(selector)) == null || (obj = A00.A01) == null)) {
            jSONObject.put("selector", obj);
        }
        if (intent.getFlags() > 0) {
            jSONObject.put("flags", intent.getFlags());
        }
        return new J6(jSONObject, arrayList);
    }
}
