package com.oculus.localmedia;

import android.util.Log;
import com.oculus.localmedia.MediaQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FolderItem {
    private static Comparator<FolderItem> DATE_ADDED_COMPARATOR = new Comparator<FolderItem>() {
        /* class com.oculus.localmedia.FolderItem.AnonymousClass1 */

        public int compare(FolderItem folderItem1, FolderItem folderItem2) {
            return (int) (folderItem2.mDateAdded - folderItem1.mDateAdded);
        }
    };
    private static Comparator<FolderItem> NAME_COMPARATOR = new Comparator<FolderItem>() {
        /* class com.oculus.localmedia.FolderItem.AnonymousClass2 */

        public int compare(FolderItem mediaItem1, FolderItem folderItem2) {
            return mediaItem1.getName().compareToIgnoreCase(folderItem2.getName());
        }
    };
    private ArrayList<FolderItem> folders;
    private ArrayList<MediaItem> items;
    private String mActorName;
    private String mActorPicture;
    private HashMap<MediaType, FolderMediaCount> mCounts;
    private long mDateAdded;
    private boolean mIsPrepared;
    private String mName;
    private String mPath;

    private FolderItem(String name, String path, long dateAdded, ArrayList<MediaItem> items2, HashMap<MediaType, FolderMediaCount> counts, String actorName, String actorPicture) {
        this.mName = name;
        this.mPath = path;
        this.mDateAdded = dateAdded;
        this.items = items2;
        this.folders = new ArrayList<>();
        this.mCounts = counts;
        this.mActorName = actorName;
        this.mActorPicture = actorPicture;
    }

    public String getName() {
        return this.mName;
    }

    public String getPath() {
        return this.mPath;
    }

    public static Builder builder() {
        return new Builder();
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("name", this.mName);
            json.put("path", this.mPath);
            json.put("type", MediaType.FOLDER.toString());
            json.put("actorName", this.mActorName);
            json.put("actorPicture", this.mActorPicture);
            json.put("timeStamp", this.mDateAdded);
            if (this.items != null && this.items.size() > 0) {
                JSONArray itemsArray = new JSONArray();
                Iterator<MediaItem> it = this.items.iterator();
                while (it.hasNext()) {
                    JSONObject itemJSON = it.next().toJSON();
                    if (itemJSON != null) {
                        itemsArray.put(itemJSON);
                    }
                }
                json.put("items", itemsArray);
            }
            if (this.folders != null && this.folders.size() > 0) {
                JSONArray foldersArray = new JSONArray();
                Iterator<FolderItem> it2 = this.folders.iterator();
                while (it2.hasNext()) {
                    JSONObject folderJSON = it2.next().toJSON();
                    if (folderJSON != null) {
                        foldersArray.put(folderJSON);
                    }
                }
                json.put("folders", foldersArray);
            }
            if (this.mCounts == null || this.mCounts.size() <= 0) {
                return json;
            }
            JSONObject countsObject = new JSONObject();
            for (MediaType t : this.mCounts.keySet()) {
                countsObject.put(t.toString(), this.mCounts.get(t).toJSON());
            }
            json.put("counts", countsObject);
            return json;
        } catch (JSONException e) {
            Log.e(LocalMediaManager.TAG, "Could not serialize to JSON media with path : " + this.mPath);
            return null;
        }
    }

    public static class Builder {
        private String mActorName;
        private String mActorPicture;
        private HashMap<MediaType, FolderMediaCount> mCounts = new HashMap<>();
        private long mDateAdded;
        private ArrayList<MediaItem> mItems = new ArrayList<>();
        private String mName;
        private String mPath;

        public Builder setName(String name) {
            this.mName = name;
            return this;
        }

        public Builder setPath(String path) {
            this.mPath = path;
            return this;
        }

        public Builder setDateAdded(long dateAdded) {
            this.mDateAdded = dateAdded;
            return this;
        }

        public FolderItem build() {
            if (this.mPath != null) {
                return new FolderItem(this.mName, this.mPath, this.mDateAdded, this.mItems, this.mCounts, this.mActorName, this.mActorPicture);
            }
            Log.e(LocalMediaManager.TAG, "FilePath is mandatory to build a folder item.");
            return null;
        }
    }

    public void prepare(LocalMediaManager localMediaManager, MediaQuery query) {
        if (!this.mIsPrepared) {
            this.mIsPrepared = true;
            this.mCounts = FolderMediaCountRegistry.getSingleton().get(this.mPath);
            MediaQuery.builder();
            MediaResponse subResponse = localMediaManager.queryMedia(MediaQuery.Builder.fromQuery(query).recurse(this.mPath).build());
            if (subResponse != null) {
                Iterator<MediaItem> it = subResponse.getItems().iterator();
                while (it.hasNext()) {
                    this.items.add(it.next());
                }
                Iterator<FolderItem> it2 = subResponse.getFolders().iterator();
                while (it2.hasNext()) {
                    FolderItem subFolder = it2.next();
                    if (!LocalMediaManager.PARENT_FOLDER_NAME.equalsIgnoreCase(subFolder.getName())) {
                        this.folders.add(subFolder);
                    }
                }
            }
        }
    }

    public static void sort(List<FolderItem> items2, MediaSort sortType, boolean ascendingOrder) {
        Comparator<FolderItem> comparator;
        switch (sortType) {
            case DATE:
                comparator = DATE_ADDED_COMPARATOR;
                break;
            default:
                comparator = NAME_COMPARATOR;
                break;
        }
        if (ascendingOrder) {
            comparator = Collections.reverseOrder(comparator);
        }
        Collections.sort(items2, comparator);
    }
}
