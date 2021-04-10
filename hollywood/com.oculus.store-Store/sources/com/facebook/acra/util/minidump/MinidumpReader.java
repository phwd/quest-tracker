package com.facebook.acra.util.minidump;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.debug.log.BLog;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.util.HashSet;
import javax.annotation.Nullable;

@SuppressLint({"HexColorValueUsage"})
public class MinidumpReader {
    private static final String ALT_STACK = "WriteThreadUnwindStream failed";
    private static final String CUSTOM_STREAM_GLOBAL = "global";
    public static final String LOG_TAG = MinidumpReader.class.getSimpleName();
    public static final int MD_FB_APP_CUSTOM_DATA = -87110918;
    public static final int MD_FB_APP_STATE_LOG = -87110452;
    public static final int MD_FB_APP_VERSION_CODE = -87110917;
    public static final int MD_FB_APP_VERSION_NAME = -87110916;
    public static final int MD_FB_DUMP_ERROR_LOG = -87110912;
    public static final int MD_FB_JAVA_STACK = -87110915;
    public static final int MD_FB_STREAM_MARKERS = -87162880;
    private static final long MD_FB_UNWIND_SYMBOLS_OFFSET = 17592186044416L;
    private static final int MD_HEADER_SIGNATURE = 1347241037;
    public static final int MD_LINUX_CMD_LINE = 1197932550;
    public static final int MD_MODULE_LIST_STREAM = 4;
    private static final int MD_MODULE_LIST_STREAM_OFFSET = 16;
    private static final int MD_THREAD_LIST_STREAM_OFFSET = 8;
    private static final int MODULE_FULL_SIZE = 108;
    private static final String MODULE_LIST = "WriteMappings failed";
    private static final int MODULE_LIST_OFFSET = 24;
    private static final String THREAD_LIST = "WriteThreadListStream failed";
    private RandomAccessFile mHandle;
    private int mStreamCount;
    private int mStreamsPos;

    /* access modifiers changed from: package-private */
    public static class MDLocationDescription {
        public int pos;
        public int size;

        public MDLocationDescription(int pos2, int size2) {
            this.pos = pos2;
            this.size = size2;
        }
    }

    public static class MinidumpMarkers {
        public long endMarker;
        public long startMarker;

        public MinidumpMarkers(long startMarker2, long endMarker2) {
            this.startMarker = startMarker2;
            this.endMarker = endMarker2;
        }
    }

    public MinidumpReader(RandomAccessFile minidump) throws IOException {
        this.mHandle = minidump;
        this.mHandle.seek(0);
        if (readIntLE() != MD_HEADER_SIGNATURE) {
            throw new RuntimeException("Invalid minidump signature");
        }
        this.mHandle.skipBytes(4);
        this.mStreamCount = readIntLE();
        this.mStreamsPos = readIntLE();
    }

    @Nullable
    private MDLocationDescription findStream(int streamType) throws IOException {
        this.mHandle.seek((long) this.mStreamsPos);
        for (int i = 0; i < this.mStreamCount; i++) {
            int type = readIntLE();
            int size = readIntLE();
            int pos = readIntLE();
            if (type == streamType) {
                return new MDLocationDescription(pos, size);
            }
        }
        return null;
    }

    @Nullable
    public String getString(int streamType) throws IOException {
        String values = getString(findStream(streamType));
        if (TextUtils.isEmpty(values)) {
            return null;
        }
        return values;
    }

    @Nullable
    private String getString(MDLocationDescription stream) throws IOException {
        if (stream == null) {
            return null;
        }
        this.mHandle.seek((long) stream.pos);
        byte[] buf = new byte[stream.size];
        this.mHandle.read(buf);
        return new String(buf);
    }

    @Nullable
    private String getModuleString(MDLocationDescription stream) throws IOException {
        this.mHandle.seek((long) stream.pos);
        byte[] buf = new byte[stream.size];
        byte[] buf1 = new byte[(stream.size / 2)];
        this.mHandle.read(buf);
        for (int i = 0; i < stream.size / 2; i++) {
            buf1[i] = buf[i * 2];
        }
        return new String(buf1);
    }

    private static boolean isSystemModule(String module) {
        if (module == null || ((!module.startsWith("/system") && !module.startsWith("/apex") && !module.startsWith("/vendor") && !module.startsWith("/odm")) || (!module.endsWith(".so") && !module.contains("app_process") && !module.endsWith("linker")))) {
            return false;
        }
        return true;
    }

    @Nullable
    public HashSet<String> getModuleList() {
        try {
            MDLocationDescription stream = findStream(4);
            if (stream == null) {
                BLog.e(LOG_TAG, "Stream is Null");
                return null;
            }
            this.mHandle.seek((long) stream.pos);
            int numModules = readIntLE();
            HashSet<String> moduleList = new HashSet<>();
            int stream_pos = stream.pos;
            for (int i = 0; i < numModules; i++) {
                this.mHandle.seek((long) (stream_pos + 24));
                this.mHandle.seek((long) readIntLE());
                String module = getModuleString(new MDLocationDescription((int) this.mHandle.getFilePointer(), readIntLE()));
                if (isSystemModule(module)) {
                    moduleList.add(module);
                }
                stream_pos += MODULE_FULL_SIZE;
            }
            return moduleList;
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getModuleList failed to read");
            return null;
        }
    }

    @Nullable
    public String getErrorLogData() {
        try {
            return getString(MD_FB_DUMP_ERROR_LOG);
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getErrorLogData error: %s", "");
            return null;
        }
    }

    @Nullable
    public MinidumpMarkers getMinidumpMarkers() {
        try {
            MDLocationDescription stream = findStream(MD_FB_STREAM_MARKERS);
            if (stream == null) {
                return null;
            }
            this.mHandle.seek((long) stream.pos);
            return new MinidumpMarkers(readLongIntLE(), readLongIntLE());
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getMinidumpMarkers failed to read");
            return null;
        }
    }

    public static boolean checkMinidumpMarkerStream(long marker) {
        boolean modulesNotExist;
        boolean threadsNotExist;
        boolean altStackNotExist;
        if ((16 & marker) != 0) {
            modulesNotExist = true;
        } else {
            modulesNotExist = false;
        }
        if ((8 & marker) != 0) {
            threadsNotExist = true;
        } else {
            threadsNotExist = false;
        }
        if ((17592186044416L & marker) != 0) {
            altStackNotExist = true;
        } else {
            altStackNotExist = false;
        }
        return modulesNotExist || (threadsNotExist && altStackNotExist);
    }

    public static boolean checkMinidumpErrLogStream(@Nullable String errLog) {
        if (errLog == null || (!errLog.contains(MODULE_LIST) && (!errLog.contains(THREAD_LIST) || !errLog.contains(ALT_STACK)))) {
            return false;
        }
        return true;
    }

    public boolean checkIfMinidumpCorrupted() {
        MinidumpMarkers markers = getMinidumpMarkers();
        boolean markerCheckFail = false;
        if (markers != null) {
            markerCheckFail = checkMinidumpMarkerStream(markers.startMarker ^ markers.endMarker);
        }
        boolean errLogFail = checkMinidumpErrLogStream(getErrorLogData());
        if (markerCheckFail || errLogFail) {
            return true;
        }
        return false;
    }

    @Nullable
    public String getJavaStack() {
        try {
            return getString(MD_FB_JAVA_STACK);
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "getJavaStack error");
            return null;
        }
    }

    @Nullable
    public String getCustomData(String key) {
        try {
            String values = getString(MD_FB_APP_CUSTOM_DATA);
            if (values == null) {
                return null;
            }
            return getCustomDataFromJson(values, key);
        } catch (Exception e) {
            BLog.e(LOG_TAG, e, "getCustomData error: %s", "");
            return null;
        }
    }

    @Nullable
    public String getCustomDataFromJson(String customStream, String key) throws IOException {
        JsonReader jsonReader = new JsonReader(new StringReader(customStream));
        try {
            JsonReader result = retrieveJsonNode(retrieveJsonNode(jsonReader, CUSTOM_STREAM_GLOBAL), key);
            String nextString = result != null ? result.nextString() : null;
            jsonReader.close();
            return nextString;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    @Nullable
    private static JsonReader retrieveJsonNode(@Nullable JsonReader jsonReader, String key) throws IOException {
        if (jsonReader == null) {
            return null;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (jsonReader.peek() != JsonToken.NULL) {
                if (name.equals(key)) {
                    return jsonReader;
                }
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return null;
    }

    @Nullable
    public Integer getInt(int streamType) throws IOException {
        return getInt(findStream(streamType));
    }

    @Nullable
    private Integer getInt(MDLocationDescription stream) throws IOException {
        if (stream == null || stream.size != 4) {
            return null;
        }
        this.mHandle.seek((long) stream.pos);
        return Integer.valueOf(readIntLE());
    }

    private int readIntLE() throws IOException {
        int number = this.mHandle.readInt();
        return ((number & 255) << 24) | ((65280 & number) << 8) | ((16711680 & number) >> 8) | ((number >> 24) & 255);
    }

    private long readLongIntLE() throws IOException {
        long number = this.mHandle.readLong();
        return (((number >> 0) & 255) << 56) | (((number >> 8) & 255) << 48) | (((number >> 16) & 255) << 40) | (((number >> 24) & 255) << 32) | (((number >> 32) & 255) << 24) | (((number >> 40) & 255) << 16) | (((number >> 48) & 255) << 8) | (((number >> 56) & 255) << 0);
    }
}
