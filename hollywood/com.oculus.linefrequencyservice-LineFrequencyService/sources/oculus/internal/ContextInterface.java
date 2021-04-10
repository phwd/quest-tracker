package oculus.internal;

import android.content.Context;
import android.content.Intent;

public interface ContextInterface {
    void startForegroundService(Context context, Intent intent);
}
