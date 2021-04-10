package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MediaStoreFileLoaderModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = MediaStoreFileLoaderModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getAllFilesFromMediaStoreImpl(boolean z, String str, Resolver<List<FileManagerFile>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDownloadFilesFromMediaStoreImpl(boolean z, String str, Resolver<List<FileManagerFile>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getFilesInFolderImpl(String str, String str2, Resolver<List<FileManagerFile>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getMediaFilesFromMediaStoreImpl(boolean z, String str, Resolver<List<FileManagerFile>> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getAllFilesFromMediaStore", "+bsii"));
        list.add(new Pair<>("getDownloadFilesFromMediaStore", "+bsii"));
        list.add(new Pair<>("getFilesInFolder", "+ssii"));
        list.add(new Pair<>("getMediaFilesFromMediaStore", "+bsii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getAllFilesFromMediaStore(boolean reset, String ordering, int resolveID, int rejectID) {
        getAllFilesFromMediaStoreImpl(reset, ordering, ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDownloadFilesFromMediaStore(boolean reset, String ordering, int resolveID, int rejectID) {
        getDownloadFilesFromMediaStoreImpl(reset, ordering, ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getFilesInFolder(String folderPath, String ordering, int resolveID, int rejectID) {
        getFilesInFolderImpl(folderPath, ordering, ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getMediaFilesFromMediaStore(boolean reset, String ordering, int resolveID, int rejectID) {
        getMediaFilesFromMediaStoreImpl(reset, ordering, ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class FileManagerFile extends NativeModuleParcel {
        public double dateAdded;
        public FileManagerFileMetadata fileMetadata;
        public FileManagerFolderMetadata folderMetadata;
        public String kind;
        public String path;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("dateAdded", this.dateAdded);
                parcel.put("fileMetadata", this.fileMetadata == null ? JSONObject.NULL : this.fileMetadata.convertToJSONObject());
                parcel.put("folderMetadata", this.folderMetadata == null ? JSONObject.NULL : this.folderMetadata.convertToJSONObject());
                parcel.put("kind", this.kind);
                parcel.put("path", this.path);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            FileManagerFolderMetadata fileManagerFolderMetadata = null;
            this.dateAdded = json.optDouble("dateAdded", 0.0d);
            this.fileMetadata = json.isNull("fileMetadata") ? null : FileManagerFileMetadata.makeFromJSONObject(json.optJSONObject("fileMetadata"));
            if (!json.isNull("folderMetadata")) {
                fileManagerFolderMetadata = FileManagerFolderMetadata.makeFromJSONObject(json.optJSONObject("folderMetadata"));
            }
            this.folderMetadata = fileManagerFolderMetadata;
            this.kind = json.optString("kind");
            this.path = json.optString("path");
        }
    }

    public static class FileManagerFileMetadata extends NativeModuleParcel {
        public Double durationInMs;
        public double height;
        public String mimeType;
        public double size;
        public String thumbnail;
        public String type;
        public double width;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("durationInMs", this.durationInMs);
                parcel.put("height", this.height);
                parcel.put("mimeType", this.mimeType);
                parcel.put("size", this.size);
                parcel.put("thumbnail", this.thumbnail);
                parcel.put("type", this.type);
                parcel.put("width", this.width);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.durationInMs = json.isNull("durationInMs") ? null : Double.valueOf(json.optDouble("durationInMs", 0.0d));
            this.height = json.optDouble("height", 0.0d);
            this.mimeType = json.isNull("mimeType") ? null : json.optString("mimeType");
            this.size = json.optDouble("size", 0.0d);
            if (!json.isNull("thumbnail")) {
                str = json.optString("thumbnail");
            }
            this.thumbnail = str;
            this.type = json.optString("type");
            this.width = json.optDouble("width", 0.0d);
        }

        public static final FileManagerFileMetadata makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            FileManagerFileMetadata result = new FileManagerFileMetadata();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class FileManagerFolderMetadata extends NativeModuleParcel {
        public double itemCount;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("itemCount", this.itemCount);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.itemCount = json.optDouble("itemCount", 0.0d);
        }

        public static final FileManagerFolderMetadata makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            FileManagerFolderMetadata result = new FileManagerFolderMetadata();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
