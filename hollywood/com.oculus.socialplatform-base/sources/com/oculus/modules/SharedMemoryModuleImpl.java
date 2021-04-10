package com.oculus.modules;

import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SharedMemoryModule;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedMemoryModuleImpl extends SharedMemoryModule {
    public static final String TAG = "SharedMemoryModuleImpl";
    public static int mHeight = 0;
    public static String mPath = "";
    public static int mWidth;

    @Override // com.oculus.modules.codegen.SharedMemoryModule
    public void shutdownModule() {
    }

    public static void setStaticFileInfo(String str, int i, int i2) {
        mPath = str;
        mWidth = i;
        mHeight = i2;
    }

    @Override // com.oculus.modules.codegen.SharedMemoryModule
    public void getMostRecentFileInfoImpl(Resolver<SharedMemoryModule.FileInfo> resolver) {
        try {
            resolver.resolve(SharedMemoryModule.FileInfo.makeFromJSONObject(new JSONObject().put("path", mPath).put("width", mWidth).put("height", mHeight)));
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }
}
