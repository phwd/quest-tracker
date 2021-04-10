package com.oculus.bugreporter;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import oculus.internal.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Extradata {
    private static final String FIELD_APPID = "app_id";
    private static final String FIELD_CATEGORYID = "category_Id";
    private static final String FIELD_EXTRAMEDIA = "extra_media";
    private static final String FIELD_SHOULD_UPLOAD_PAST_AUDIO_DATA = "upload_past_audio_data";
    private static final String FIELD_SHOULD_UPLOAD_SCREENSHOT = "upload_screenshot";
    private static final String FIELD_SOURCE = "source";
    private static final String TAG = "Extradata";
    public String appId;
    public String categoryId;
    public String extraMedia;
    public boolean shouldUploadPastAudioData;
    public boolean shouldUploadScreenshot;
    public String source;

    /* JADX INFO: finally extract failed */
    public static boolean writeToFile(File file, Extradata extradata) {
        FileOutputStream os = null;
        try {
            JSONObject json = new JSONObject();
            json.put("source", extradata.source);
            json.put(FIELD_SHOULD_UPLOAD_SCREENSHOT, extradata.shouldUploadScreenshot);
            json.put(FIELD_SHOULD_UPLOAD_PAST_AUDIO_DATA, extradata.shouldUploadPastAudioData);
            json.put(FIELD_EXTRAMEDIA, extradata.extraMedia);
            json.put(FIELD_APPID, extradata.appId);
            json.put(FIELD_CATEGORYID, extradata.categoryId);
            os = new FileOutputStream(file);
            os.write(json.toString().getBytes());
            os.close();
            FileUtils.closeQuietly(os);
            return true;
        } catch (IOException | JSONException e) {
            Log.w(TAG, "Couldn't write to bug extradata", e);
            FileUtils.closeQuietly(os);
            return false;
        } catch (Throwable th) {
            FileUtils.closeQuietly(os);
            throw th;
        }
    }

    public static Extradata readFromFile(File file) {
        try {
            String jsonStr = FileUtils.readAllText(file);
            if (jsonStr != null) {
                JSONObject json = new JSONObject(jsonStr);
                Extradata extradata = new Extradata();
                extradata.source = json.getString("source");
                extradata.shouldUploadScreenshot = json.getBoolean(FIELD_SHOULD_UPLOAD_SCREENSHOT);
                extradata.shouldUploadPastAudioData = json.getBoolean(FIELD_SHOULD_UPLOAD_PAST_AUDIO_DATA);
                extradata.extraMedia = json.getString(FIELD_EXTRAMEDIA);
                extradata.appId = json.getString(FIELD_APPID);
                extradata.categoryId = json.getString(FIELD_CATEGORYID);
                return extradata;
            }
            Log.w(TAG, "Couldn't read bug extradata");
            return null;
        } catch (JSONException e) {
            Log.w(TAG, "Couldn't read bug extradata", e);
            return null;
        }
    }
}
