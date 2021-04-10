package com.facebook.acra.util.minidump;

import X.AnonymousClass0MD;
import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;
import com.oculus.localmedia.MediaProviderUtils;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.util.HashSet;
import javax.annotation.Nullable;

@SuppressLint({"HexColorValueUsage"})
public class MinidumpReader {
    public static final String ALT_STACK = "WriteThreadUnwindStream failed";
    public static final String CUSTOM_STREAM_GLOBAL = "global";
    public static final String LOG_TAG = "MinidumpReader";
    public static final int MD_FB_APP_CUSTOM_DATA = -87110918;
    public static final int MD_FB_APP_STATE_LOG = -87110452;
    public static final int MD_FB_APP_VERSION_CODE = -87110917;
    public static final int MD_FB_APP_VERSION_NAME = -87110916;
    public static final int MD_FB_DUMP_ERROR_LOG = -87110912;
    public static final int MD_FB_JAVA_STACK = -87110915;
    public static final int MD_FB_STREAM_MARKERS = -87162880;
    public static final long MD_FB_UNWIND_SYMBOLS_OFFSET = 17592186044416L;
    public static final int MD_HEADER_SIGNATURE = 1347241037;
    public static final int MD_LINUX_CMD_LINE = 1197932550;
    public static final int MD_MODULE_LIST_STREAM = 4;
    public static final int MD_MODULE_LIST_STREAM_OFFSET = 16;
    public static final int MD_THREAD_LIST_STREAM_OFFSET = 8;
    public static final int MODULE_FULL_SIZE = 108;
    public static final String MODULE_LIST = "WriteMappings failed";
    public static final int MODULE_LIST_OFFSET = 24;
    public static final String THREAD_LIST = "WriteThreadListStream failed";
    public RandomAccessFile mHandle;
    public int mStreamCount;
    public int mStreamsPos;

    public static boolean checkMinidumpMarkerStream(long j) {
        boolean z = false;
        if ((16 & j) != 0) {
            z = true;
        }
        boolean z2 = false;
        if ((8 & j) != 0) {
            z2 = true;
        }
        boolean z3 = false;
        if ((j & MD_FB_UNWIND_SYMBOLS_OFFSET) != 0) {
            z3 = true;
        }
        return z || (z2 && z3);
    }

    @Nullable
    public static JsonReader retrieveJsonNode(@Nullable JsonReader jsonReader, String str) throws IOException {
        if (jsonReader != null) {
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
        }
        return null;
    }

    @Nullable
    public HashSet<String> getModuleList() {
        try {
            MDLocationDescription findStream = findStream(4);
            if (findStream == null) {
                AnonymousClass0MD.A04(LOG_TAG, "Stream is Null");
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
                if (isSystemModule(moduleString)) {
                    hashSet.add(moduleString);
                }
                i += 108;
            }
            return hashSet;
        } catch (IOException e) {
            AnonymousClass0MD.A0C(LOG_TAG, e, "getModuleList failed to read");
            return null;
        }
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

    public static boolean checkMinidumpErrLogStream(@Nullable String str) {
        if (str == null) {
            return false;
        }
        if (str.contains(MODULE_LIST)) {
            return true;
        }
        if (!str.contains(THREAD_LIST) || !str.contains(ALT_STACK)) {
            return false;
        }
        return true;
    }

    @Nullable
    private MDLocationDescription findStream(int i) throws IOException {
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

    @Nullable
    private String getModuleString(MDLocationDescription mDLocationDescription) throws IOException {
        this.mHandle.seek((long) mDLocationDescription.pos);
        int i = mDLocationDescription.size;
        byte[] bArr = new byte[i];
        byte[] bArr2 = new byte[(i >> 1)];
        this.mHandle.read(bArr);
        for (int i2 = 0; i2 < (mDLocationDescription.size >> 1); i2++) {
            bArr2[i2] = bArr[i2 << 1];
        }
        return new String(bArr2);
    }

    public static boolean isSystemModule(String str) {
        if (str == null) {
            return false;
        }
        if (!str.startsWith("/system") && !str.startsWith("/apex") && !str.startsWith("/vendor") && !str.startsWith("/odm")) {
            return false;
        }
        if (str.endsWith(".so") || str.contains("app_process") || str.endsWith("linker")) {
            return true;
        }
        return false;
    }

    private int readIntLE() throws IOException {
        int readInt = this.mHandle.readInt();
        return ((readInt >> 24) & MediaProviderUtils.JPEG_HEADER) | ((readInt & MediaProviderUtils.JPEG_HEADER) << 24) | ((65280 & readInt) << 8) | ((16711680 & readInt) >> 8);
    }

    private long readLongIntLE() throws IOException {
        long readLong = this.mHandle.readLong();
        return (((readLong >> 56) & 255) << 0) | (((readLong >> 0) & 255) << 56) | (((readLong >> 8) & 255) << 48) | (((readLong >> 16) & 255) << 40) | (((readLong >> 24) & 255) << 32) | (((readLong >> 32) & 255) << 24) | (((readLong >> 40) & 255) << 16) | (((readLong >> 48) & 255) << 8);
    }

    @Nullable
    public String getCustomDataFromJson(String str, String str2) throws IOException {
        String str3;
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            JsonReader retrieveJsonNode = retrieveJsonNode(retrieveJsonNode(jsonReader, CUSTOM_STREAM_GLOBAL), str2);
            if (retrieveJsonNode != null) {
                str3 = retrieveJsonNode.nextString();
            } else {
                str3 = null;
            }
            jsonReader.close();
            return str3;
        } catch (Throwable unused) {
        }
        throw th;
    }

    @Nullable
    public MinidumpMarkers getMinidumpMarkers() {
        try {
            MDLocationDescription findStream = findStream(MD_FB_STREAM_MARKERS);
            if (findStream == null) {
                return null;
            }
            this.mHandle.seek((long) findStream.pos);
            return new MinidumpMarkers(readLongIntLE(), readLongIntLE());
        } catch (IOException e) {
            AnonymousClass0MD.A0C(LOG_TAG, e, "getMinidumpMarkers failed to read");
            return null;
        }
    }

    public MinidumpReader(RandomAccessFile randomAccessFile) throws IOException {
        this.mHandle = randomAccessFile;
        randomAccessFile.seek(0);
        if (readIntLE() == 1347241037) {
            this.mHandle.skipBytes(4);
            this.mStreamCount = readIntLE();
            this.mStreamsPos = readIntLE();
            return;
        }
        throw new RuntimeException("Invalid minidump signature");
    }

    public boolean checkIfMinidumpCorrupted() {
        boolean z;
        MinidumpMarkers minidumpMarkers = getMinidumpMarkers();
        if (minidumpMarkers != null) {
            z = checkMinidumpMarkerStream(minidumpMarkers.startMarker ^ minidumpMarkers.endMarker);
        } else {
            z = false;
        }
        boolean checkMinidumpErrLogStream = checkMinidumpErrLogStream(getErrorLogData());
        if (z || checkMinidumpErrLogStream) {
            return true;
        }
        return false;
    }

    @Nullable
    public String getCustomData(String str) {
        Exception e;
        String str2;
        try {
            str2 = getString(MD_FB_APP_CUSTOM_DATA);
            if (str2 != null) {
                try {
                    return getCustomDataFromJson(str2, str);
                } catch (Exception e2) {
                    e = e2;
                    AnonymousClass0MD.A0E(LOG_TAG, e, "getCustomData error: %s", str2);
                    return null;
                }
            }
        } catch (Exception e3) {
            e = e3;
            str2 = "";
            AnonymousClass0MD.A0E(LOG_TAG, e, "getCustomData error: %s", str2);
            return null;
        }
        return null;
    }

    @Nullable
    public String getErrorLogData() {
        try {
            return getString(MD_FB_DUMP_ERROR_LOG);
        } catch (IOException e) {
            AnonymousClass0MD.A0E(LOG_TAG, e, "getErrorLogData error: %s", "");
            return null;
        }
    }

    @Nullable
    public String getJavaStack() {
        try {
            return getString(MD_FB_JAVA_STACK);
        } catch (IOException e) {
            AnonymousClass0MD.A0C(LOG_TAG, e, "getJavaStack error");
            return null;
        }
    }

    @Nullable
    private Integer getInt(MDLocationDescription mDLocationDescription) throws IOException {
        if (mDLocationDescription == null || mDLocationDescription.size != 4) {
            return null;
        }
        this.mHandle.seek((long) mDLocationDescription.pos);
        return Integer.valueOf(readIntLE());
    }

    @Nullable
    private String getString(MDLocationDescription mDLocationDescription) throws IOException {
        if (mDLocationDescription == null) {
            return null;
        }
        this.mHandle.seek((long) mDLocationDescription.pos);
        byte[] bArr = new byte[mDLocationDescription.size];
        this.mHandle.read(bArr);
        return new String(bArr);
    }

    @Nullable
    public Integer getInt(int i) throws IOException {
        return getInt(findStream(i));
    }

    @Nullable
    public String getString(int i) throws IOException {
        String string = getString(findStream(i));
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }
}
