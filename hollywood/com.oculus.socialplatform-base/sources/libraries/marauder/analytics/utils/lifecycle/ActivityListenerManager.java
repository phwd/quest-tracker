package libraries.marauder.analytics.utils.lifecycle;

import android.app.Activity;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ActivityListenerManager {
    public List<ActivityListener> listeners;

    public static class ActivityListenerManagerHolder {
        public static final ActivityListenerManager INSTANCE = new ActivityListenerManager();
    }

    public static ActivityListenerManager getInstance() {
        return ActivityListenerManagerHolder.INSTANCE;
    }

    public void activityCreated(Activity activity) {
        for (ActivityListener activityListener : this.listeners) {
            activityListener.onActivityCreate(activity);
        }
    }

    public void activityDestroyed(Activity activity) {
        for (ActivityListener activityListener : this.listeners) {
            activityListener.onActivityDestroy(activity);
        }
    }

    public void activityPaused(Activity activity) {
        for (ActivityListener activityListener : this.listeners) {
            activityListener.onActivityPause(activity);
        }
    }

    public void activityResumed(Activity activity) {
        for (ActivityListener activityListener : this.listeners) {
            activityListener.onActivityResume(activity);
        }
    }

    public void addListener(ActivityListener activityListener) {
        if (!this.listeners.contains(activityListener)) {
            this.listeners.add(activityListener);
        }
    }

    public void removeListener(ActivityListener activityListener) {
        if (this.listeners.contains(activityListener)) {
            this.listeners.remove(activityListener);
        }
    }

    public ActivityListenerManager() {
        this.listeners = new CopyOnWriteArrayList();
    }
}
