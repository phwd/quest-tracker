package oculus.internal;

import android.os.Build;

public class BuildCompat implements OSBuildInterface {
    @Override // oculus.internal.OSBuildInterface
    public String getSerial() {
        return Build.getSerial();
    }
}
