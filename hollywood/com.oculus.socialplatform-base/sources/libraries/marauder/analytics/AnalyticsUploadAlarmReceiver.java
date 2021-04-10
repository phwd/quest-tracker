package libraries.marauder.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AnalyticsUploadAlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Analytics.sLogger.queueUpload();
    }
}
