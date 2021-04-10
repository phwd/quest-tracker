package com.facebook.secure.fileprovider;

import android.content.Context;
import java.io.File;

class ReadableRoot {
    private final String mName;
    private final String mPath;
    private final StoragePath mStoragePath;

    ReadableRoot(String str, StoragePath storagePath, String str2) {
        this.mName = str;
        this.mStoragePath = storagePath;
        this.mPath = str2;
    }

    /* access modifiers changed from: package-private */
    public File resolveFile(Context context) {
        return FileUtil.buildPath(this.mStoragePath.getDirectoryForContext(context), this.mPath);
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.mName;
    }
}
