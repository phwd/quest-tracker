package com.oculus.localmedia;

import android.util.Log;
import com.oculus.libraryapi.OVRLibrary;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaResponse {
    private String error;
    private MediaErrorCode errorCode = MediaErrorCode.NONE;
    private ArrayList<FolderItem> folders = new ArrayList<>();
    private ArrayList<MediaItem> items = new ArrayList<>();
    private String jsonResponse;
    private MediaPagination paginationData;
    private String queryId;

    public MediaResponse(String queryId2) {
        this.queryId = queryId2;
    }

    public static MediaResponse fromJson(String jsonResponse2) throws Exception {
        new JSONObject(jsonResponse2);
        MediaResponse mediaResponse = new MediaResponse(null);
        mediaResponse.jsonResponse = jsonResponse2;
        return mediaResponse;
    }

    public MediaResponse setError(String error2) {
        return setError(error2, MediaErrorCode.INTERNAL_ERROR);
    }

    public MediaResponse setError(String error2, MediaErrorCode errorCode2) {
        this.error = error2;
        this.errorCode = errorCode2;
        return this;
    }

    public void addMediaItem(MediaItem item) {
        this.items.add(item);
    }

    public void addFolderItem(FolderItem folder) {
        this.folders.add(folder);
    }

    public void setPaginationData(MediaPagination paginationData2) {
        this.paginationData = paginationData2;
    }

    public ArrayList<MediaItem> getItems() {
        return this.items;
    }

    public ArrayList<FolderItem> getFolders() {
        return this.folders;
    }

    public int getTotalCount() {
        if (this.paginationData != null) {
            return this.paginationData.getTotalCount();
        }
        return 0;
    }

    public String getStartCursor() {
        if (this.paginationData != null) {
            return this.paginationData.getStartCursor();
        }
        return null;
    }

    public String getLastCursor() {
        if (this.paginationData != null) {
            return this.paginationData.getLastCursor();
        }
        return null;
    }

    public int getSize() {
        int i;
        int i2 = 0;
        if (this.items != null) {
            i = this.items.size();
        } else {
            i = 0;
        }
        int size = 0 + i;
        if (this.folders != null) {
            i2 = this.folders.size();
        }
        return size + i2;
    }

    private JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            if (this.queryId != null && !this.queryId.isEmpty()) {
                json.put("queryId", this.queryId);
            }
            if (this.errorCode != MediaErrorCode.NONE) {
                json.put(OVRLibrary.EXTRA_ERROR_CODE, this.errorCode);
                json.put("error", this.error);
            } else {
                JSONArray itemsArray = new JSONArray();
                Iterator<MediaItem> it = this.items.iterator();
                while (it.hasNext()) {
                    JSONObject itemJSON = it.next().toJSON();
                    if (itemJSON != null) {
                        itemsArray.put(itemJSON);
                    }
                }
                json.put("items", itemsArray);
                JSONArray foldersArray = new JSONArray();
                Iterator<FolderItem> it2 = this.folders.iterator();
                while (it2.hasNext()) {
                    JSONObject folderJSON = it2.next().toJSON();
                    if (folderJSON != null) {
                        foldersArray.put(folderJSON);
                    }
                }
                json.put("folders", foldersArray);
                if (this.paginationData != null) {
                    json.put("page_info", this.paginationData.toJSON());
                }
            }
        } catch (JSONException e) {
            Log.e(LocalMediaManager.TAG, "Could not serialize query response : " + e);
        }
        return json;
    }

    public String toString() {
        return this.jsonResponse != null ? this.jsonResponse : toJSON().toString();
    }
}
