package libraries.marauder.analytics;

import android.content.Context;
import java.io.File;

public class AnalyticsUtil {
    public static final String ANALYTICS_DIRECTORY_NAME = "analytics";

    public static String formatServerTime(long j) {
        return String.format(null, "%.3f", Double.valueOf(((double) j) / 1000.0d));
    }

    public static File getAnalyticsDirectory(Context context) {
        return new File(context.getFilesDir(), ANALYTICS_DIRECTORY_NAME);
    }

    public static String getBatchFilename(AnalyticsSession analyticsSession) {
        return String.format(null, "%s_%d.batch", analyticsSession.getSessionId().toString(), Integer.valueOf(analyticsSession.mSequence));
    }
}
