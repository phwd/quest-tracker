package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SharedMemoryModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "SharedMemoryModule";

    public static class FileInfo extends NativeModuleParcel {
        public double height;
        public String path;
        public double width;

        public static final FileInfo makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFromJSONObject(jSONObject);
            return fileInfo;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("height", this.height);
                jSONObject.put("path", this.path);
                jSONObject.put("width", this.width);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.height = jSONObject.optDouble("height", 0.0d);
            this.path = jSONObject.optString("path");
            this.width = jSONObject.optDouble("width", 0.0d);
        }
    }

    public abstract void getMostRecentFileInfoImpl(Resolver<FileInfo> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getMostRecentFileInfo", "+ii"));
        return arrayList;
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void getMostRecentFileInfo(int i, int i2) {
        getMostRecentFileInfoImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }
}
