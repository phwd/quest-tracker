package com.facebook.acra.util.minidump;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.debug.log.BLog;
import com.oculus.common.build.BuildConfig;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.util.HashSet;

@SuppressLint({"HexColorValueUsage"})
public class MinidumpReader {
    public static final String LOG_TAG = "MinidumpReader";
    public RandomAccessFile mHandle;
    private int mStreamCount;
    private int mStreamsPos;

    public static boolean checkMinidumpMarkerStream(long j) {
        return (((16 & j) > 0 ? 1 : ((16 & j) == 0 ? 0 : -1)) != 0) || ((((8 & j) > 0 ? 1 : ((8 & j) == 0 ? 0 : -1)) != 0) && (((j & 17592186044416L) > 0 ? 1 : ((j & 17592186044416L) == 0 ? 0 : -1)) != 0));
    }

    public static class MDLocationDescription {
        public int pos;
        public int size;

        public MDLocationDescription(int i, int i2) {
            this.pos = i;
            this.size = i2;
        }
    }

    public static class MinidumpMarkers {
        public long endMarker;
        public long startMarker;

        public MinidumpMarkers(long j, long j2) {
            this.startMarker = j;
            this.endMarker = j2;
        }
    }

    public MinidumpReader(RandomAccessFile randomAccessFile) throws IOException {
        this.mHandle = randomAccessFile;
        this.mHandle.seek(0);
        if (readIntLE() == 1347241037) {
            this.mHandle.skipBytes(4);
            this.mStreamCount = readIntLE();
            this.mStreamsPos = readIntLE();
            return;
        }
        throw new RuntimeException("Invalid minidump signature");
    }

    public MDLocationDescription findStream(int i) throws IOException {
        this.mHandle.seek((long) this.mStreamsPos);
        for (int i2 = 0; i2 < this.mStreamCount; i2++) {
            int readIntLE = readIntLE();
            int readIntLE2 = readIntLE();
            int readIntLE3 = readIntLE();
            if (readIntLE == i) {
                return new MDLocationDescription(readIntLE3, readIntLE2);
            }
        }
        return null;
    }

    public final String getString(int i) throws IOException {
        String string = getString(findStream(i));
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    private String getString(MDLocationDescription mDLocationDescription) throws IOException {
        if (mDLocationDescription == null) {
            return null;
        }
        this.mHandle.seek((long) mDLocationDescription.pos);
        byte[] bArr = new byte[mDLocationDescription.size];
        this.mHandle.read(bArr);
        return new String(bArr);
    }

    private String getModuleString(MDLocationDescription mDLocationDescription) throws IOException {
        this.mHandle.seek((long) mDLocationDescription.pos);
        byte[] bArr = new byte[mDLocationDescription.size];
        byte[] bArr2 = new byte[(mDLocationDescription.size / 2)];
        this.mHandle.read(bArr);
        for (int i = 0; i < mDLocationDescription.size / 2; i++) {
            bArr2[i] = bArr[i * 2];
        }
        return new String(bArr2);
    }

    public final HashSet<String> getModuleList() {
        try {
            MDLocationDescription findStream = findStream(4);
            if (findStream == null) {
                BLog.e(LOG_TAG, "Stream is Null");
                return null;
            }
            this.mHandle.seek((long) findStream.pos);
            int readIntLE = readIntLE();
            HashSet<String> hashSet = new HashSet<>();
            int i = findStream.pos;
            for (int i2 = 0; i2 < readIntLE; i2++) {
                this.mHandle.seek((long) (i + 24));
                this.mHandle.seek((long) readIntLE());
                String moduleString = getModuleString(new MDLocationDescription((int) this.mHandle.getFilePointer(), readIntLE()));
                if (moduleString != null && (moduleString.startsWith("/system") || moduleString.startsWith("/apex") || moduleString.startsWith("/vendor") || moduleString.startsWith("/odm")) && (moduleString.endsWith(".so") || moduleString.contains("app_process") || moduleString.endsWith("linker"))) {
                    hashSet.add(moduleString);
                }
                i += 108;
            }
            return hashSet;
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getModuleList failed to read");
            return null;
        }
    }

    public final String getErrorLogData() {
        try {
            return getString(-87110912);
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getErrorLogData error: %s", BuildConfig.PROVIDER_SUFFIX);
            return null;
        }
    }

    public final MinidumpMarkers getMinidumpMarkers() {
        try {
            MDLocationDescription findStream = findStream(-87162880);
            if (findStream == null) {
                return null;
            }
            this.mHandle.seek((long) findStream.pos);
            return new MinidumpMarkers(readLongIntLE(), readLongIntLE());
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getMinidumpMarkers failed to read");
            return null;
        }
    }

    public static boolean checkMinidumpErrLogStream(String str) {
        if (str == null) {
            return false;
        }
        if (!str.contains("WriteMappings failed")) {
            return str.contains("WriteThreadListStream failed") && str.contains("WriteThreadUnwindStream failed");
        }
        return true;
    }

    public final String getJavaStack() {
        try {
            return getString(-87110915);
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getJavaStack error");
            return null;
        }
    }

    public final String getCustomData(String str) {
        Exception e;
        String str2;
        try {
            str2 = getString(-87110918);
            if (str2 == null) {
                return null;
            }
            try {
                return getCustomDataFromJson(str2, str);
            } catch (Exception e2) {
                e = e2;
                BLog.e(LOG_TAG, e, "getCustomData error: %s", str2);
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            str2 = BuildConfig.PROVIDER_SUFFIX;
            BLog.e(LOG_TAG, e, "getCustomData error: %s", str2);
            return null;
        }
    }

    private static String getCustomDataFromJson(String str, String str2) throws IOException {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            JsonReader retrieveJsonNode = retrieveJsonNode(retrieveJsonNode(jsonReader, "global"), str2);
            String nextString = retrieveJsonNode != null ? retrieveJsonNode.nextString() : null;
            jsonReader.close();
            return nextString;
        } catch (Throwable unused) {
        }
        throw th;
    }

    private static JsonReader retrieveJsonNode(JsonReader jsonReader, String str) throws IOException {
        if (jsonReader == null) {
            return null;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (jsonReader.peek() != JsonToken.NULL) {
                if (nextName.equals(str)) {
                    return jsonReader;
                }
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return null;
    }

    public int readIntLE() throws IOException {
        int readInt = this.mHandle.readInt();
        return ((readInt >> 24) & 255) | ((readInt & 255) << 24) | ((65280 & readInt) << 8) | ((16711680 & readInt) >> 8);
    }

    private long readLongIntLE() throws IOException {
        long readLong = this.mHandle.readLong();
        return (((readLong >> 56) & 255) << 0) | (((readLong >> 0) & 255) << 56) | (((readLong >> 8) & 255) << 48) | (((readLong >> 16) & 255) << 40) | (((readLong >> 24) & 255) << 32) | (((readLong >> 32) & 255) << 24) | (((readLong >> 40) & 255) << 16) | (((readLong >> 48) & 255) << 8);
    }
}
