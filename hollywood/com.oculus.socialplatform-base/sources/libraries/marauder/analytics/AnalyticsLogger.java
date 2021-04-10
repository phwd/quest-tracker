package libraries.marauder.analytics;

import android.view.MotionEvent;
import android.widget.TextView;

public interface AnalyticsLogger {
    void flush();

    void queueUpload();

    void registerTextView(TextView textView);

    void reportEvent(IAnalyticsEvent iAnalyticsEvent);

    void reportLogin(String str, String str2);

    void reportLogout();

    void reportStarredEvent(IAnalyticsEvent iAnalyticsEvent);

    void reportTouchEvent(MotionEvent motionEvent);

    void unregisterTextView(TextView textView);
}
