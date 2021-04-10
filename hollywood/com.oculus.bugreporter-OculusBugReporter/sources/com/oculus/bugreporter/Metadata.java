package com.oculus.bugreporter;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import oculus.internal.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Metadata {
    private static final String FIELD_BUG_ID = "bug_id";
    private static final String FIELD_EXTRAMEDIA = "extra_media";
    private static final String FIELD_IS_LOGS_FILE_ADDED = "is_logs_file_added";
    private static final String FIELD_IS_PAST_AUDIO_DATA_ADDED = "is_past_audio_data_added";
    private static final String FIELD_IS_SCREENSHOT_ADDED = "is_screenshot_added";
    private static final String FIELD_IS_UPLOADED = "is_uploaded";
    private static final String FIELD_IS_USER_DETAILS_ADDED = "is_user_details_added";
    private static final String FIELD_TIMESTAMP = "modified_timestamp";
    private static final String TAG = "BugReportMetadata";
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

    /* JADX INFO: finally extract failed */
    public static boolean writeToFile(File file, Metadata metadata) {
        FileOutputStream os = null;
        try {
            JSONObject json = new JSONObject();
            json.put("bug_id", metadata.bugId);
            json.put(FIELD_IS_SCREENSHOT_ADDED, metadata.isScreenshotAdded);
            json.put(FIELD_IS_PAST_AUDIO_DATA_ADDED, metadata.isPastAudioDataAdded);
            json.put(FIELD_IS_LOGS_FILE_ADDED, metadata.isLogsFileAdded);
            json.put(FIELD_IS_USER_DETAILS_ADDED, metadata.isUserDetailsAdded);
            json.put(FIELD_TIMESTAMP, System.currentTimeMillis());
            json.put(FIELD_IS_UPLOADED, metadata.isUploaded);
            json.put(FIELD_EXTRAMEDIA, metadata.extraMedia);
            os = new FileOutputStream(file);
            os.write(json.toString().getBytes());
            os.close();
            FileUtils.closeQuietly(os);
            return true;
        } catch (IOException | JSONException e) {
            Log.w(TAG, "Couldn't write to bug metadata", e);
            FileUtils.closeQuietly(os);
            return false;
        } catch (Throwable th) {
            FileUtils.closeQuietly(os);
            throw th;
        }
    }

    public static Metadata readFromFile(File file) {
        try {
            String jsonStr = FileUtils.readAllText(file);
            if (jsonStr != null) {
                JSONObject json = new JSONObject(jsonStr);
                Metadata metadata = new Metadata();
                metadata.bugId = json.getString("bug_id");
                metadata.isScreenshotAdded = json.getBoolean(FIELD_IS_SCREENSHOT_ADDED);
                metadata.isPastAudioDataAdded = json.getBoolean(FIELD_IS_PAST_AUDIO_DATA_ADDED);
                metadata.isLogsFileAdded = json.getBoolean(FIELD_IS_LOGS_FILE_ADDED);
                metadata.isUserDetailsAdded = json.getBoolean(FIELD_IS_USER_DETAILS_ADDED);
                metadata.isUploaded = json.getBoolean(FIELD_IS_UPLOADED);
                metadata.timestamp = json.getLong(FIELD_TIMESTAMP);
                metadata.extraMedia = json.optString(FIELD_EXTRAMEDIA);
                return metadata;
            }
            Log.w(TAG, "Could not read bug metadata");
            return null;
        } catch (JSONException ex) {
            Log.w(TAG, "Could not read bug metadata", ex);
            return null;
        }
    }
}
