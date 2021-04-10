package retrofit.android;

import retrofit.RestAdapter;

public class AndroidLog implements RestAdapter.Log {
    public static final int LOG_CHUNK_SIZE = 4000;
    public final String tag;

    public AndroidLog(String str) {
        this.tag = str;
    }

    @Override // retrofit.RestAdapter.Log
    public final void log(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 4000;
            str.substring(i, Math.min(length, i2));
            i = i2;
        }
    }

    public String getTag() {
        return this.tag;
    }

    public void logChunk(String str) {
    }
}
