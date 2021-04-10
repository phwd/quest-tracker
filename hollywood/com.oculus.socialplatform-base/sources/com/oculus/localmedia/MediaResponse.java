package com.oculus.localmedia;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.systemdialog.DialogList;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaResponse {
    public String error;
    public MediaErrorCode errorCode = MediaErrorCode.NONE;
    public ArrayList<FolderItem> folders = new ArrayList<>();
    public ArrayList<MediaItem> items = new ArrayList<>();
    public String jsonResponse;
    @Nullable
    public MediaPagination paginationData;
    public String queryId;

    public static MediaResponse fromJson(String str) throws Exception {
        new JSONObject(str);
        MediaResponse mediaResponse = new MediaResponse(null);
        mediaResponse.jsonResponse = str;
        return mediaResponse;
    }

    private JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.queryId;
            if (str != null && !str.isEmpty()) {
                jSONObject.put("queryId", str);
            }
            MediaErrorCode mediaErrorCode = this.errorCode;
            if (mediaErrorCode != MediaErrorCode.NONE) {
                jSONObject.put(OVRLibrary.EXTRA_ERROR_CODE, mediaErrorCode);
                jSONObject.put("error", this.error);
                return jSONObject;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<MediaItem> it = this.items.iterator();
            while (it.hasNext()) {
                JSONObject json = it.next().toJSON();
                if (json != null) {
                    jSONArray.put(json);
                }
            }
            jSONObject.put(DialogList.DIALOG_LIST_ITEMS_KEY, jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            Iterator<FolderItem> it2 = this.folders.iterator();
            while (it2.hasNext()) {
                JSONObject json2 = it2.next().toJSON();
                if (json2 != null) {
                    jSONArray2.put(json2);
                }
            }
            jSONObject.put("folders", jSONArray2);
            MediaPagination mediaPagination = this.paginationData;
            if (mediaPagination != null) {
                jSONObject.put("page_info", mediaPagination.toJSON());
                return jSONObject;
            }
            return jSONObject;
        } catch (JSONException e) {
            String str2 = LocalMediaManager.TAG;
            StringBuilder sb = new StringBuilder("Could not serialize query response : ");
            sb.append(e);
            Log.e(str2, sb.toString());
        }
    }

    public void addFolderItem(FolderItem folderItem) {
        this.folders.add(folderItem);
    }

    public void addMediaItem(MediaItem mediaItem) {
        this.items.add(mediaItem);
    }

    @Nullable
    public String getLastCursor() {
        MediaPagination mediaPagination = this.paginationData;
        if (mediaPagination != null) {
            return mediaPagination.mLastCursor;
        }
        return null;
    }

    public int getSize() {
        int i;
        ArrayList<MediaItem> arrayList = this.items;
        int i2 = 0;
        if (arrayList != null) {
            i = arrayList.size();
        } else {
            i = 0;
        }
        int i3 = i + 0;
        ArrayList<FolderItem> arrayList2 = this.folders;
        if (arrayList2 != null) {
            i2 = arrayList2.size();
        }
        return i3 + i2;
    }

    @Nullable
    public String getStartCursor() {
        MediaPagination mediaPagination = this.paginationData;
        if (mediaPagination != null) {
            return mediaPagination.mStartCursor;
        }
        return null;
    }

    public int getTotalCount() {
        MediaPagination mediaPagination = this.paginationData;
        if (mediaPagination != null) {
            return mediaPagination.mTotalCount;
        }
        return 0;
    }

    public String toString() {
        String str = this.jsonResponse;
        if (str == null) {
            return toJSON().toString();
        }
        return str;
    }

    public MediaResponse(String str) {
        this.queryId = str;
    }

    public ArrayList<FolderItem> getFolders() {
        return this.folders;
    }

    public ArrayList<MediaItem> getItems() {
        return this.items;
    }

    public void setPaginationData(MediaPagination mediaPagination) {
        this.paginationData = mediaPagination;
    }

    public MediaResponse setError(String str) {
        MediaErrorCode mediaErrorCode = MediaErrorCode.INTERNAL_ERROR;
        this.error = str;
        this.errorCode = mediaErrorCode;
        return this;
    }

    public MediaResponse setError(String str, MediaErrorCode mediaErrorCode) {
        this.error = str;
        this.errorCode = mediaErrorCode;
        return this;
    }
}
