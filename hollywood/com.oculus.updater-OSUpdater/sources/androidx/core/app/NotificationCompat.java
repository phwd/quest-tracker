package androidx.core.app;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NotificationCompat {

    public static class Action {

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface BadgeIconType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface GroupAlertBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface NotificationVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface StreamType {
    }
}
