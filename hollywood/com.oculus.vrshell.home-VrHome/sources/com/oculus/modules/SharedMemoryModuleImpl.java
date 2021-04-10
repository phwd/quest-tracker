package com.oculus.modules;

import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SharedMemoryModule;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedMemoryModuleImpl extends SharedMemoryModule {
    private static final String TAG = SharedMemoryModuleImpl.class.getSimpleName();
    private static int mHeight = 0;
    private static String mPath = "";
    private static int mWidth = 0;

    public static void setStaticFileInfo(String path, int width, int height) {
        mPath = path;
        mWidth = width;
        mHeight = height;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SharedMemoryModule
    public void getMostRecentFileInfoImpl(Resolver<SharedMemoryModule.FileInfo> resolver) {
        try {
            resolver.resolve(SharedMemoryModule.FileInfo.makeFromJSONObject(new JSONObject().put("path", mPath).put("width", mWidth).put("height", mHeight)));
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.SharedMemoryModule
    public void shutdownModule() {
    }
}
