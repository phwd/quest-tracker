package com.oculus.vrshell.panels.systemtooltip;

public final class TooltipUVCoordinates {
    public final float U;
    public final float V;

    public static TooltipUVCoordinates getTooltipUVCoordinates(int i, int i2, int i3, int i4, int i5, int i6, TooltipPosition tooltipPosition) {
        float f;
        float f2 = (float) i;
        float f3 = (float) i2;
        switch (tooltipPosition.ordinal()) {
            case 0:
                f2 += ((float) i3) / 2.0f;
                break;
            case 1:
                f2 += ((float) i3) / 2.0f;
                f = (float) i4;
                f3 += f;
                break;
            case 3:
                f2 += (float) i3;
            case 2:
                f = ((float) i4) / 2.0f;
                f3 += f;
                break;
        }
        return new TooltipUVCoordinates(f2 / ((float) i5), 1.0f - (f3 / ((float) i6)));
    }

    /* renamed from: com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition[] r0 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition = r2
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r0 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Top     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r0 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Bottom     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r0 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Left     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r0 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Right     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.<clinit>():void");
        }
    }

    public TooltipUVCoordinates(float f, float f2) {
        this.U = f;
        this.V = f2;
    }
}
