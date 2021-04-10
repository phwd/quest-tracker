package X;

import android.content.ComponentName;
import android.content.Intent;

public final class YU {
    public static Intent A00() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.assistant", "com.oculus.assistant.service.AssistantService"));
        return intent;
    }
}
