package libraries.marauder.analytics;

import android.content.Context;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import libraries.debug.log.BLog;

public class AnalyticsStorage {
    public static final String TAG = "AnalyticsStorage";
    public final File mDirectory;

    private void ensureDirectory() {
        if (!this.mDirectory.exists() && !this.mDirectory.mkdir()) {
            BLog.e(TAG, "Unable to open analytics storage.");
        }
    }

    public AnalyticsStorage(Context context) {
        this.mDirectory = AnalyticsUtil.getAnalyticsDirectory(context);
    }

    public File store(AnalyticsSession analyticsSession) throws IOException {
        ensureDirectory();
        File file = new File(this.mDirectory, AnalyticsUtil.getBatchFilename(analyticsSession));
        if (file.exists() && !file.delete()) {
            BLog.w(TAG, "File %s was not deleted", file);
        }
        analyticsSession.mTime = System.currentTimeMillis();
        String obj = AnalyticsSessionSerializer.serialize(analyticsSession).toString();
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        printWriter.write(obj);
        printWriter.close();
        return file;
    }
}
