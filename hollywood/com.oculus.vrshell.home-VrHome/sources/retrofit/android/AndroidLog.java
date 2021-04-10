package retrofit.android;

import android.util.Log;
import retrofit.RestAdapter;

public class AndroidLog implements RestAdapter.Log {
    private static final int LOG_CHUNK_SIZE = 4000;
    private final String tag;

    public AndroidLog(String tag2) {
        this.tag = tag2;
    }

    @Override // retrofit.RestAdapter.Log
    public final void log(String message) {
        int len = message.length();
        for (int i = 0; i < len; i += LOG_CHUNK_SIZE) {
            logChunk(message.substring(i, Math.min(len, i + LOG_CHUNK_SIZE)));
        }
    }

    public void logChunk(String chunk) {
        Log.d(getTag(), chunk);
    }

    public String getTag() {
        return this.tag;
    }
}
