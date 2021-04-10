package oculus.internal.ui;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;

public class ClearActivityUtils {
    private static final String INTENT_ACTION_FINISH = "com.oculus.os.clearactivity.FINISH";
    private static final String INTENT_EXTRA_SOURCE_PACKAGE = "sourcePackage";
    private static final String PACKAGE = "com.oculus.os.clearactivity";
    private static final String TAG = "ClearActivityUtils";

    /* access modifiers changed from: private */
    public static Intent getLaunchIntent(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE);
        if (intent != null) {
            intent.putExtra(INTENT_EXTRA_SOURCE_PACKAGE, context.getPackageName());
        }
        return intent;
    }

    /* access modifiers changed from: private */
    public static Intent getFinishIntent(Context context) {
        Intent intent = getLaunchIntent(context);
        if (intent != null) {
            intent.setAction(INTENT_ACTION_FINISH);
        }
        return intent;
    }

    public static class ForcePauseUtil {
        private boolean mHasClearActivity = false;
        private boolean mShouldSetClearActivity = false;

        public void setForcePauseBackgroundActivity(boolean shouldPauseActivity) {
            this.mShouldSetClearActivity = shouldPauseActivity;
        }

        public void onStart(Context context) {
            if (this.mShouldSetClearActivity) {
                if (this.mHasClearActivity) {
                    Log.v(ClearActivityUtils.TAG, "Not starting clear activity since one already started");
                    return;
                }
                Intent clearActivityIntent = ClearActivityUtils.getLaunchIntent(context);
                if (clearActivityIntent == null) {
                    Log.e(ClearActivityUtils.TAG, "could not get Clear Activity launch intent");
                    return;
                }
                context.startActivityAsUser(clearActivityIntent, UserHandle.CURRENT);
                this.mHasClearActivity = true;
            }
        }

        public void onStop(Context context) {
            if (this.mHasClearActivity) {
                Intent clearActivityFinishIntent = ClearActivityUtils.getFinishIntent(context);
                if (clearActivityFinishIntent != null) {
                    context.startActivityAsUser(clearActivityFinishIntent, UserHandle.CURRENT);
                }
                this.mHasClearActivity = false;
            }
        }
    }
}
