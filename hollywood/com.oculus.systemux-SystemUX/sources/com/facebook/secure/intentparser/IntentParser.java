package com.facebook.secure.intentparser;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.secure.sanitizer.SanitizerConfig;
import com.facebook.secure.sanitizer.SensitiveDataSanitizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntentParser {

    public static class ParsedIntent {
        @Nullable
        public JSONObject mIntentContent;
        @Nullable
        public List<Uri> mUris;

        public ParsedIntent(@Nullable JSONObject jSONObject, @Nullable List<Uri> list) {
            this.mIntentContent = jSONObject;
            this.mUris = list;
        }
    }

    @Nullable
    public static ParsedIntent parseIntent(@Nullable Intent intent, @Nullable SanitizerConfig sanitizerConfig, @Nullable SanitizerConfig sanitizerConfig2) throws JSONException {
        ParsedIntent parseIntent;
        JSONObject jSONObject;
        ClipData clipData;
        Uri uri;
        if (intent == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        ArrayList arrayList = new ArrayList();
        jSONObject2.put("action", intent.getAction());
        jSONObject2.put("package", intent.getPackage());
        jSONObject2.put("type", intent.getType());
        Uri data = intent.getData();
        if (data != null) {
            arrayList.add(data);
            JSONObject uRIJson = SensitiveDataSanitizer.sanitizeURI(data, sanitizerConfig).getURIJson();
            if (uRIJson != null) {
                jSONObject2.put("data", uRIJson);
            }
        }
        if (Build.VERSION.SDK_INT >= 16 && (clipData = Api16IUtils.getClipData(intent)) != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item itemAt = clipData.getItemAt(i);
                if (!(itemAt == null || (uri = itemAt.getUri()) == null)) {
                    arrayList.add(uri);
                    JSONObject uRIJson2 = SensitiveDataSanitizer.sanitizeURI(uri, sanitizerConfig).getURIJson();
                    if (uRIJson2 != null) {
                        jSONArray.put(uRIJson2);
                    }
                }
            }
            jSONObject2.put("clip_data", jSONArray);
        }
        Set<String> categories = intent.getCategories();
        if (categories != null && !categories.isEmpty()) {
            JSONArray jSONArray2 = new JSONArray();
            for (String str : categories) {
                jSONArray2.put(str);
            }
            jSONObject2.put("categories", jSONArray2);
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            jSONObject2.put("component_name", component.toString());
        }
        Rect sourceBounds = intent.getSourceBounds();
        if (sourceBounds != null) {
            jSONObject2.put("source_bounds", sourceBounds.toString());
        }
        Bundle extras = intent.getExtras();
        if (extras != null && extras.size() > 0) {
            jSONObject2.put("extra_names", SensitiveDataSanitizer.sanitizeIntentExtras(extras, sanitizerConfig2, sanitizerConfig));
        }
        Intent selector = intent.getSelector();
        if (!(selector == null || (parseIntent = parseIntent(selector, sanitizerConfig, sanitizerConfig2)) == null || (jSONObject = parseIntent.mIntentContent) == null)) {
            jSONObject2.put("selector", jSONObject);
        }
        if (intent.getFlags() > 0) {
            jSONObject2.put("flags", intent.getFlags());
        }
        return new ParsedIntent(jSONObject2, arrayList);
    }

    @Nullable
    public static ParsedIntent parseIntent(@Nullable Intent intent) throws JSONException {
        return parseIntent(intent, null, null);
    }

    /* access modifiers changed from: package-private */
    @TargetApi(16)
    @DoNotOptimize
    public static class Api16IUtils {
        Api16IUtils() {
        }

        static ClipData getClipData(Intent intent) {
            return intent.getClipData();
        }

        static void setClipData(Intent intent, ClipData clipData) {
            intent.setClipData(clipData);
        }
    }
}
