package libraries.marauder.analytics.utils.lifecycle;

import android.app.Activity;

public interface ActivityListener {
    void onActivityCreate(Activity activity);

    void onActivityDestroy(Activity activity);

    void onActivityPause(Activity activity);

    void onActivityResume(Activity activity);
}
