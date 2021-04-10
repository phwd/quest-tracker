package com.oculus.mediaupload.io;

import X.AnonymousClass0NO;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Dependencies;
import com.oculus.mediaupload.model.MediaMetadata;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({})
public class MediaUploaderDB {
    public static final String FB_DB_PATH = "media_uploader_db_fb.json";
    public static final String FB_GAMING_PROFILE_ACCESS_TOKEN_KEY = "fb_gaming_profile_access_token_key";
    public static final String FB_GAMING_PROFILE_PREFERENCES = "fb_gaming_profile_preferences";
    public static final String OC_DB_PATH = "media_uploader_db_oc.json";
    public static final String TAG = "MediaUploaderDB";

    public enum Type {
        OCULUS,
        FACEBOOK
    }

    /* renamed from: com.oculus.mediaupload.io.MediaUploaderDB$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$io$MediaUploaderDB$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.mediaupload.io.MediaUploaderDB$Type[] r0 = com.oculus.mediaupload.io.MediaUploaderDB.Type.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.mediaupload.io.MediaUploaderDB.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$io$MediaUploaderDB$Type = r2
                com.oculus.mediaupload.io.MediaUploaderDB$Type r0 = com.oculus.mediaupload.io.MediaUploaderDB.Type.OCULUS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.mediaupload.io.MediaUploaderDB$Type r0 = com.oculus.mediaupload.io.MediaUploaderDB.Type.FACEBOOK     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.io.MediaUploaderDB.AnonymousClass1.<clinit>():void");
        }
    }

    public static MediaMetadata A00(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(A02(new FileInputStream(new File(new File(context.getFilesDir(), FileUtils.MEDIA_METADATA_PATH), StringFormatUtil.formatStrLocaleSafe("%s.%s", str.replaceAll("\\([0-9]+\\)\\.", "."), FileUtils.MEDIA_METADATA_EXT)))));
            return new MediaMetadata(jSONObject.optString(MediaMetadata.APP_ID_KEY), jSONObject.optString(MediaMetadata.RICH_PRESENCE_JSON_KEY), jSONObject.optBoolean(MediaMetadata.IS_INSTANT_REPLAY_KEY, false), jSONObject.optBoolean(MediaMetadata.CAPTURED_WITH_SHORTCUT_KEY, false));
        } catch (FileNotFoundException unused) {
            return new MediaMetadata();
        } catch (SecurityException | JSONException e) {
            AnonymousClass0NO.A0B(TAG, "Failed to read media metadata", e);
            return new MediaMetadata();
        }
    }

    public static String A02(InputStream inputStream) {
        if (inputStream == null) {
            return "";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    inputStream.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "Can not read file", e);
            return "{}";
        }
    }

    @VisibleForTesting
    public static HashSet<String> A03(String str) {
        HashSet<String> hashSet = new HashSet<>();
        try {
            Iterator<String> keys = new JSONObject(str).keys();
            while (keys.hasNext()) {
                hashSet.add(keys.next());
            }
        } catch (JSONException e) {
            AnonymousClass0NO.A0B(TAG, "Could not get already uploaded files: %s ", e);
        }
        return hashSet;
    }

    public static void A04(FileOutputStream fileOutputStream, String str) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "File write failed", e);
        }
    }

    public static String A01(Context context, Type type) {
        try {
            int ordinal = type.ordinal();
            String str = OC_DB_PATH;
            if (1 - ordinal == 0) {
                str = FB_DB_PATH;
            }
            return A02(context.openFileInput(str));
        } catch (FileNotFoundException unused) {
            return "{}";
        }
    }
}
