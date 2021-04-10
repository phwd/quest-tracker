package sun.net;

import java.net.URL;
import java.util.ArrayList;

public class ProgressMonitor {
    private static ProgressMeteringPolicy meteringPolicy = new DefaultProgressMeteringPolicy();
    private static ProgressMonitor pm = new ProgressMonitor();
    private ArrayList progressListenerList = new ArrayList();
    private ArrayList progressSourceList = new ArrayList();

    public static synchronized ProgressMonitor getDefault() {
        ProgressMonitor progressMonitor;
        synchronized (ProgressMonitor.class) {
            progressMonitor = pm;
        }
        return progressMonitor;
    }

    public synchronized int getProgressUpdateThreshold() {
        return meteringPolicy.getProgressUpdateThreshold();
    }

    public boolean shouldMeterInput(URL url, String str) {
        return meteringPolicy.shouldMeterInput(url, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r12.progressListenerList.size() <= 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        r0 = new java.util.ArrayList();
        r1 = r12.progressListenerList;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r12 = r12.progressListenerList.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        if (r12.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r0.add((sun.net.ProgressListener) r12.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        r12 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r12.hasNext() == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        ((sun.net.ProgressListener) r12.next()).progressStart(new sun.net.ProgressEvent(r13, r13.getURL(), r13.getMethod(), r13.getContentType(), r13.getState(), r13.getProgress(), r13.getExpected()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerSource(sun.net.ProgressSource r13) {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.ProgressMonitor.registerSource(sun.net.ProgressSource):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r12.progressListenerList.size() <= 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r0 = new java.util.ArrayList();
        r1 = r12.progressListenerList;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r12 = r12.progressListenerList.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        if (r12.hasNext() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r0.add((sun.net.ProgressListener) r12.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        r12 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
        if (r12.hasNext() == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        ((sun.net.ProgressListener) r12.next()).progressFinish(new sun.net.ProgressEvent(r13, r13.getURL(), r13.getMethod(), r13.getContentType(), r13.getState(), r13.getProgress(), r13.getExpected()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unregisterSource(sun.net.ProgressSource r13) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.ProgressMonitor.unregisterSource(sun.net.ProgressSource):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        r0 = new java.util.ArrayList();
        r1 = r12.progressListenerList;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r12 = r12.progressListenerList.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        if (r12.hasNext() == false) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        r0.add((sun.net.ProgressListener) r12.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        r12 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r12.hasNext() == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        ((sun.net.ProgressListener) r12.next()).progressUpdate(new sun.net.ProgressEvent(r13, r13.getURL(), r13.getMethod(), r13.getContentType(), r13.getState(), r13.getProgress(), r13.getExpected()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r12.progressListenerList.size() <= 0) goto L_?;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateProgress(sun.net.ProgressSource r13) {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.ProgressMonitor.updateProgress(sun.net.ProgressSource):void");
    }
}
