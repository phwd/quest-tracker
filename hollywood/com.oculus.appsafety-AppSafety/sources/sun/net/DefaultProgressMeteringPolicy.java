package sun.net;

import java.net.URL;

/* compiled from: ProgressMonitor */
class DefaultProgressMeteringPolicy implements ProgressMeteringPolicy {
    DefaultProgressMeteringPolicy() {
    }

    @Override // sun.net.ProgressMeteringPolicy
    public boolean shouldMeterInput(URL url, String method) {
        return false;
    }

    @Override // sun.net.ProgressMeteringPolicy
    public int getProgressUpdateThreshold() {
        return 8192;
    }
}
