package com.oculus.bugreportservice;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import oculus.internal.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Extradata {
    public String appId;
    public String categoryId;
    public String extraMedia;
    public boolean shouldUploadLogs;
    public boolean shouldUploadPastAudioData;
    public boolean shouldUploadScreenshot;
    public String source;

    public static boolean writeToFile(File file, Extradata extradata) {
        Throwable th;
        Throwable e;
        FileOutputStream fileOutputStream = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("source", extradata.source);
            jSONObject.put("upload_logs", extradata.shouldUploadLogs);
            jSONObject.put("upload_screenshot", extradata.shouldUploadScreenshot);
            jSONObject.put("upload_past_audio_data", extradata.shouldUploadPastAudioData);
            jSONObject.put("extra_media", extradata.extraMedia);
            jSONObject.put("app_id", extradata.appId);
            jSONObject.put("category_Id", extradata.categoryId);
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                fileOutputStream2.write(jSONObject.toString().getBytes());
                fileOutputStream2.close();
                FileUtils.closeQuietly(fileOutputStream2);
                return true;
            } catch (IOException | JSONException e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    Log.w("Extradata", "Couldn't write to bug extradata", e);
                    FileUtils.closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                FileUtils.closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (IOException | JSONException e3) {
            e = e3;
            Log.w("Extradata", "Couldn't write to bug extradata", e);
            FileUtils.closeQuietly(fileOutputStream);
            return false;
        }
    }

    public static Extradata readFromFile(File file) {
        try {
            String readAllText = FileUtils.readAllText(file);
            if (readAllText != null) {
                JSONObject jSONObject = new JSONObject(readAllText);
                Extradata extradata = new Extradata();
                extradata.source = jSONObject.getString("source");
                extradata.shouldUploadLogs = jSONObject.getBoolean("upload_logs");
                extradata.shouldUploadScreenshot = jSONObject.getBoolean("upload_screenshot");
                extradata.shouldUploadPastAudioData = jSONObject.getBoolean("upload_past_audio_data");
                extradata.extraMedia = jSONObject.getString("extra_media");
                extradata.appId = jSONObject.getString("app_id");
                extradata.categoryId = jSONObject.getString("category_Id");
                return extradata;
            }
            Log.w("Extradata", "Couldn't read bug extradata");
            return null;
        } catch (JSONException e) {
            Log.w("Extradata", "Couldn't read bug extradata", e);
            return null;
        }
    }
}
