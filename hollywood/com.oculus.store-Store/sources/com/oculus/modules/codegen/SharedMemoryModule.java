package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SharedMemoryModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SharedMemoryModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getMostRecentFileInfoImpl(Resolver<FileInfo> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getMostRecentFileInfo", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getMostRecentFileInfo(int resolveID, int rejectID) {
        getMostRecentFileInfoImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class FileInfo extends NativeModuleParcel {
        public double height;
        public String path;
        public double width;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("height", this.height);
                parcel.put("path", this.path);
                parcel.put("width", this.width);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.height = json.optDouble("height", 0.0d);
            this.path = json.optString("path");
            this.width = json.optDouble("width", 0.0d);
        }

        public static final FileInfo makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            FileInfo result = new FileInfo();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
