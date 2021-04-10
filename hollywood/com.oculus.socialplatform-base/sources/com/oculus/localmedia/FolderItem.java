package com.oculus.localmedia;

import X.AnonymousClass006;
import android.util.Log;
import com.oculus.localmedia.MediaQuery;
import com.oculus.systemdialog.DialogList;
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
    public static Comparator<FolderItem> DATE_ADDED_COMPARATOR = new Comparator<FolderItem>() {
        /* class com.oculus.localmedia.FolderItem.AnonymousClass1 */

        public int compare(FolderItem folderItem, FolderItem folderItem2) {
            return (int) (folderItem2.mDateAdded - folderItem.mDateAdded);
        }
    };
    public static Comparator<FolderItem> NAME_COMPARATOR = new Comparator<FolderItem>() {
        /* class com.oculus.localmedia.FolderItem.AnonymousClass2 */

        public int compare(FolderItem folderItem, FolderItem folderItem2) {
            return folderItem.mName.compareToIgnoreCase(folderItem2.mName);
        }
    };
    public ArrayList<FolderItem> folders;
    public ArrayList<MediaItem> items;
    public String mActorName;
    public String mActorPicture;
    public HashMap<MediaType, FolderMediaCount> mCounts;
    public long mDateAdded;
    public boolean mIsPrepared;
    public String mName;
    public String mPath;

    public static class Builder {
        public String mActorName;
        public String mActorPicture;
        public HashMap<MediaType, FolderMediaCount> mCounts = new HashMap<>();
        public long mDateAdded;
        public ArrayList<MediaItem> mItems = new ArrayList<>();
        public String mName;
        public String mPath;

        public Builder addMediaItems(ArrayList<MediaItem> arrayList) {
            this.mItems.addAll(arrayList);
            return this;
        }

        public FolderItem build() {
            String str = this.mPath;
            if (str != null) {
                return new FolderItem(this.mName, str, this.mDateAdded, this.mItems, this.mCounts, this.mActorName, this.mActorPicture);
            }
            Log.e(LocalMediaManager.TAG, "FilePath is mandatory to build a folder item.");
            return null;
        }

        public Builder setCounts(MediaType mediaType, int i) {
            this.mCounts.put(mediaType, new FolderMediaCount(i, i));
            return this;
        }

        public Builder setActorName(String str) {
            this.mActorName = str;
            return this;
        }

        public Builder setActorPicture(String str) {
            this.mActorPicture = str;
            return this;
        }

        public Builder setDateAdded(long j) {
            this.mDateAdded = j;
            return this;
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setPath(String str) {
            this.mPath = str;
            return this;
        }
    }

    /* renamed from: com.oculus.localmedia.FolderItem$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$localmedia$MediaSort;

        static {
            int[] iArr = new int[MediaSort.values().length];
            $SwitchMap$com$oculus$localmedia$MediaSort = iArr;
            try {
                iArr[MediaSort.DATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public void prepare(LocalMediaManager localMediaManager, MediaQuery mediaQuery) {
        if (!this.mIsPrepared) {
            this.mIsPrepared = true;
            this.mCounts = FolderMediaCountRegistry.getSingleton().get(this.mPath);
            MediaQuery.Builder fromQuery = MediaQuery.Builder.fromQuery(mediaQuery);
            fromQuery.recurse(this.mPath);
            MediaResponse queryMedia = localMediaManager.queryMedia(fromQuery.build());
            if (queryMedia != null) {
                Iterator<MediaItem> it = queryMedia.items.iterator();
                while (it.hasNext()) {
                    this.items.add(it.next());
                }
                Iterator<FolderItem> it2 = queryMedia.folders.iterator();
                while (it2.hasNext()) {
                    FolderItem next = it2.next();
                    if (!LocalMediaManager.PARENT_FOLDER_NAME.equalsIgnoreCase(next.mName)) {
                        this.folders.add(next);
                    }
                }
            }
        }
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.mName);
            jSONObject.put("path", this.mPath);
            jSONObject.put("type", MediaType.FOLDER.toString());
            jSONObject.put("actorName", this.mActorName);
            jSONObject.put("actorPicture", this.mActorPicture);
            jSONObject.put("timeStamp", this.mDateAdded);
            ArrayList<MediaItem> arrayList = this.items;
            if (arrayList != null && arrayList.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<MediaItem> it = this.items.iterator();
                while (it.hasNext()) {
                    JSONObject json = it.next().toJSON();
                    if (json != null) {
                        jSONArray.put(json);
                    }
                }
                jSONObject.put(DialogList.DIALOG_LIST_ITEMS_KEY, jSONArray);
            }
            ArrayList<FolderItem> arrayList2 = this.folders;
            if (arrayList2 != null && arrayList2.size() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator<FolderItem> it2 = this.folders.iterator();
                while (it2.hasNext()) {
                    JSONObject json2 = it2.next().toJSON();
                    if (json2 != null) {
                        jSONArray2.put(json2);
                    }
                }
                jSONObject.put("folders", jSONArray2);
            }
            HashMap<MediaType, FolderMediaCount> hashMap = this.mCounts;
            if (hashMap != null && hashMap.size() > 0) {
                JSONObject jSONObject2 = new JSONObject();
                for (MediaType mediaType : this.mCounts.keySet()) {
                    jSONObject2.put(mediaType.toString(), this.mCounts.get(mediaType).toJSON());
                }
                jSONObject.put("counts", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(LocalMediaManager.TAG, AnonymousClass006.A07("Could not serialize to JSON media with path : ", this.mPath));
            return null;
        }
    }

    public static void sort(List<FolderItem> list, MediaSort mediaSort, boolean z) {
        Comparator<FolderItem> comparator;
        if (mediaSort.ordinal() != 1) {
            comparator = NAME_COMPARATOR;
        } else {
            comparator = DATE_ADDED_COMPARATOR;
        }
        if (z) {
            comparator = Collections.reverseOrder(comparator);
        }
        Collections.sort(list, comparator);
    }

    public String getName() {
        return this.mName;
    }

    public String getPath() {
        return this.mPath;
    }

    public FolderItem(String str, String str2, long j, ArrayList<MediaItem> arrayList, HashMap<MediaType, FolderMediaCount> hashMap, String str3, String str4) {
        this.mName = str;
        this.mPath = str2;
        this.mDateAdded = j;
        this.items = arrayList;
        this.folders = new ArrayList<>();
        this.mCounts = hashMap;
        this.mActorName = str3;
        this.mActorPicture = str4;
    }
}
