package libraries.marauder.analytics.utils.concurrent;

import android.os.Looper;
import com.oculus.util.thread.ThreadUtils;

public class ThreadUtil {

    public static class ThreadUtilHolder {
        public static final ThreadUtil INSTANCE = new ThreadUtil();
    }

    private void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static ThreadUtil getInstance() {
        return ThreadUtilHolder.INSTANCE;
    }

    public boolean isUiThread() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return true;
        }
        return false;
    }

    public ThreadUtil() {
    }

    public /* synthetic */ ThreadUtil(AnonymousClass1 r1) {
    }

    public void assertOnNonUiThread() {
        assertOnNonUiThread("This operation can't be run on UI thread.");
    }

    public void assertOnNonUiThread(String str) {
        checkState(!isUiThread(), str);
    }

    public void assertOnUiThread() {
        assertOnUiThread(ThreadUtils.MUST_BE_UI_THREAD);
    }

    public void assertOnUiThread(String str) {
        checkState(isUiThread(), str);
    }
}
