package retrofit.android;

import android.util.Log;
import retrofit.RestAdapter;

public class AndroidLog implements RestAdapter.Log {
    private static final int LOG_CHUNK_SIZE = 4000;
    private final String tag;

    public AndroidLog(String str) {
        this.tag = str;
    }

    @Override // retrofit.RestAdapter.Log
    public final void log(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2 = i + LOG_CHUNK_SIZE;
            logChunk(str.substring(i, Math.min(length, i2)));
            i = i2;
        }
    }

    public void logChunk(String str) {
        Log.d(getTag(), str);
    }

    public String getTag() {
        return this.tag;
    }
}
