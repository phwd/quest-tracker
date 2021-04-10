package oculus.internal;

import android.content.Context;
import android.content.Intent;

public class ContextCompat implements ContextInterface {
    public void startForegroundService(Context context, Intent intent) {
        context.startForegroundService(intent);
    }
}
