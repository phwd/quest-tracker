package com.facebook.stetho.inspector.network;

import android.content.Context;
import android.util.Base64OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ResponseBodyFileManager {
    private final Context mContext;
    private final Map<String, AsyncPrettyPrinter> mRequestIdMap;

    public OutputStream openResponseBodyFile(String str, boolean z) throws IOException {
        FileOutputStream openFileOutput = this.mContext.openFileOutput(getFilename(str), 0);
        openFileOutput.write(z ? 1 : 0);
        return z ? new Base64OutputStream(openFileOutput, 0) : openFileOutput;
    }

    private static String getFilename(String str) {
        return "network-response-body-" + str;
    }

    public void associateAsyncPrettyPrinterWithId(String str, AsyncPrettyPrinter asyncPrettyPrinter) {
        if (this.mRequestIdMap.put(str, asyncPrettyPrinter) != null) {
            throw new IllegalArgumentException("cannot associate different pretty printers with the same request id: " + str);
        }
    }
}
