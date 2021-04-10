package sun.net;

import java.net.URL;

/* compiled from: ProgressMonitor */
class DefaultProgressMeteringPolicy implements ProgressMeteringPolicy {
    @Override // sun.net.ProgressMeteringPolicy
    public int getProgressUpdateThreshold() {
        return 8192;
    }

    @Override // sun.net.ProgressMeteringPolicy
    public boolean shouldMeterInput(URL url, String str) {
        return false;
    }

    DefaultProgressMeteringPolicy() {
    }
}
