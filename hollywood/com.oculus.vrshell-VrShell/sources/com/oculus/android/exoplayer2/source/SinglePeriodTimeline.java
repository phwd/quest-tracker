package com.oculus.android.exoplayer2.source;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.util.Assertions;

public final class SinglePeriodTimeline extends Timeline {
    private static final Object ID = new Object();
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    @Override // com.oculus.android.exoplayer2.Timeline
    public int getPeriodCount() {
        return 1;
    }

    @Override // com.oculus.android.exoplayer2.Timeline
    public int getWindowCount() {
        return 1;
    }

    public SinglePeriodTimeline(long j, boolean z, boolean z2) {
        this(j, j, 0, 0, z, z2);
    }

    public SinglePeriodTimeline(long j, long j2, long j3, long j4, boolean z, boolean z2) {
        this(C.TIME_UNSET, C.TIME_UNSET, j, j2, j3, j4, z, z2);
    }

    public SinglePeriodTimeline(long j, long j2, long j3, long j4, long j5, long j6, boolean z, boolean z2) {
        this.presentationStartTimeMs = j;
        this.windowStartTimeMs = j2;
        this.periodDurationUs = j3;
        this.windowDurationUs = j4;
        this.windowPositionInPeriodUs = j5;
        this.windowDefaultStartPositionUs = j6;
        this.isSeekable = z;
        this.isDynamic = z2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r1 > r7) goto L_0x0027;
     */
    @Override // com.oculus.android.exoplayer2.Timeline
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.android.exoplayer2.Timeline.Window getWindow(int r19, com.oculus.android.exoplayer2.Timeline.Window r20, boolean r21, long r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = 0
            r2 = 1
            r3 = r19
            com.oculus.android.exoplayer2.util.Assertions.checkIndex(r3, r1, r2)
            if (r21 == 0) goto L_0x000e
            java.lang.Object r1 = com.oculus.android.exoplayer2.source.SinglePeriodTimeline.ID
            goto L_0x000f
        L_0x000e:
            r1 = 0
        L_0x000f:
            r3 = r1
            long r1 = r0.windowDefaultStartPositionUs
            boolean r4 = r0.isDynamic
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 == 0) goto L_0x0030
            r7 = 0
            int r4 = (r22 > r7 ? 1 : (r22 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0030
            long r7 = r0.windowDurationUs
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x0029
        L_0x0027:
            r10 = r5
            goto L_0x0031
        L_0x0029:
            long r1 = r1 + r22
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x0030
            goto L_0x0027
        L_0x0030:
            r10 = r1
        L_0x0031:
            long r4 = r0.presentationStartTimeMs
            long r6 = r0.windowStartTimeMs
            boolean r8 = r0.isSeekable
            boolean r9 = r0.isDynamic
            long r12 = r0.windowDurationUs
            r14 = 0
            r15 = 0
            long r1 = r0.windowPositionInPeriodUs
            r16 = r1
            r2 = r20
            com.oculus.android.exoplayer2.Timeline$Window r1 = r2.set(r3, r4, r6, r8, r9, r10, r12, r14, r15, r16)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.android.exoplayer2.source.SinglePeriodTimeline.getWindow(int, com.oculus.android.exoplayer2.Timeline$Window, boolean, long):com.oculus.android.exoplayer2.Timeline$Window");
    }

    @Override // com.oculus.android.exoplayer2.Timeline
    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        Assertions.checkIndex(i, 0, 1);
        Object obj = z ? ID : null;
        return period.set(obj, obj, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    @Override // com.oculus.android.exoplayer2.Timeline
    public int getIndexOfPeriod(Object obj) {
        return ID.equals(obj) ? 0 : -1;
    }
}
