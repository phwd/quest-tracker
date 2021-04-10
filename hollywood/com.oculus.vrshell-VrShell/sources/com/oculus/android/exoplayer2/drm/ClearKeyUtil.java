package com.oculus.android.exoplayer2.drm;

import android.util.Log;
import com.oculus.android.exoplayer2.util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ClearKeyUtil {
    private static final Pattern REQUEST_KIDS_PATTERN = Pattern.compile("\"kids\":\\[\"(.*?)\"]");
    private static final String TAG = "ClearKeyUtil";

    private ClearKeyUtil() {
    }

    public static byte[] adjustRequestData(byte[] bArr) {
        if (Util.SDK_INT >= 27) {
            return bArr;
        }
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr);
        Matcher matcher = REQUEST_KIDS_PATTERN.matcher(fromUtf8Bytes);
        if (!matcher.find()) {
            Log.e(TAG, "Failed to adjust request data: " + fromUtf8Bytes);
            return bArr;
        }
        int start = matcher.start(1);
        int end = matcher.end(1);
        StringBuilder sb = new StringBuilder(fromUtf8Bytes);
        base64ToBase64Url(sb, start, end);
        return Util.getUtf8Bytes(sb.toString());
    }

    public static byte[] adjustResponseData(byte[] bArr) {
        if (Util.SDK_INT >= 27) {
            return bArr;
        }
        try {
            JSONObject jSONObject = new JSONObject(Util.fromUtf8Bytes(bArr));
            JSONArray jSONArray = jSONObject.getJSONArray("keys");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                jSONObject2.put("k", base64UrlToBase64(jSONObject2.getString("k")));
                jSONObject2.put("kid", base64UrlToBase64(jSONObject2.getString("kid")));
            }
            return Util.getUtf8Bytes(jSONObject.toString());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to adjust response data: " + Util.fromUtf8Bytes(bArr), e);
            return bArr;
        }
    }

    private static void base64ToBase64Url(StringBuilder sb, int i, int i2) {
        while (i < i2) {
            char charAt = sb.charAt(i);
            if (charAt == '+') {
                sb.setCharAt(i, '-');
            } else if (charAt == '/') {
                sb.setCharAt(i, '_');
            }
            i++;
        }
    }

    private static String base64UrlToBase64(String str) {
        return str.replace('-', '+').replace('_', '/');
    }
}
