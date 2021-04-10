package com.oculus.bugreportservice;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import oculus.internal.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Metadata {
    public String bugId;
    public String extraMedia;
    public boolean isLogsFileAdded;
    public boolean isPastAudioDataAdded;
    public boolean isScreenshotAdded;
    public boolean isUploaded;
    public boolean isUserDetailsAdded;
    public long timestamp;

    public boolean isReadyToUpload() {
        return this.isScreenshotAdded && this.isPastAudioDataAdded && this.isLogsFileAdded && this.isUserDetailsAdded;
    }

    public static boolean writeToFile(File file, Metadata metadata) {
        Throwable th;
        Throwable e;
        FileOutputStream fileOutputStream = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("bug_id", metadata.bugId);
            jSONObject.put("is_screenshot_added", metadata.isScreenshotAdded);
            jSONObject.put("is_past_audio_data_added", metadata.isPastAudioDataAdded);
            jSONObject.put("is_logs_file_added", metadata.isLogsFileAdded);
            jSONObject.put("is_user_details_added", metadata.isUserDetailsAdded);
            jSONObject.put("modified_timestamp", System.currentTimeMillis());
            jSONObject.put("is_uploaded", metadata.isUploaded);
            jSONObject.put("extra_media", metadata.extraMedia);
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
                    Log.w("BugReportMetadata", "Couldn't write to bug metadata", e);
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
            Log.w("BugReportMetadata", "Couldn't write to bug metadata", e);
            FileUtils.closeQuietly(fileOutputStream);
            return false;
        }
    }

    public static Metadata readFromFile(File file) {
        try {
            String readAllText = FileUtils.readAllText(file);
            if (readAllText != null) {
                JSONObject jSONObject = new JSONObject(readAllText);
                Metadata metadata = new Metadata();
                metadata.bugId = jSONObject.getString("bug_id");
                metadata.isScreenshotAdded = jSONObject.getBoolean("is_screenshot_added");
                metadata.isPastAudioDataAdded = jSONObject.getBoolean("is_past_audio_data_added");
                metadata.isLogsFileAdded = jSONObject.getBoolean("is_logs_file_added");
                metadata.isUserDetailsAdded = jSONObject.getBoolean("is_user_details_added");
                metadata.isUploaded = jSONObject.getBoolean("is_uploaded");
                metadata.timestamp = jSONObject.getLong("modified_timestamp");
                metadata.extraMedia = jSONObject.optString("extra_media");
                return metadata;
            }
            Log.w("BugReportMetadata", "Could not read bug metadata");
            return null;
        } catch (JSONException e) {
            Log.w("BugReportMetadata", "Could not read bug metadata", e);
            return null;
        }
    }
}
