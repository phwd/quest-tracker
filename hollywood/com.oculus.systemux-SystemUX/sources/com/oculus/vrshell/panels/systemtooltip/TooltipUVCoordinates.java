package com.oculus.vrshell.panels.systemtooltip;

public final class TooltipUVCoordinates {
    public final float U;
    public final float V;

    public TooltipUVCoordinates(float f, float f2) {
        this.U = f;
        this.V = f2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition = new int[TooltipPosition.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition[] r0 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition = r0
                int[] r0 = com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r1 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Top     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r1 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Bottom     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r1 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Left     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.vrshell.panels.systemtooltip.TooltipPosition r1 = com.oculus.vrshell.panels.systemtooltip.TooltipPosition.Right     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates.AnonymousClass1.<clinit>():void");
        }
    }

    public static TooltipUVCoordinates getTooltipUVCoordinates(int i, int i2, int i3, int i4, int i5, int i6, TooltipPosition tooltipPosition) {
        float f;
        float f2 = (float) i;
        float f3 = (float) i2;
        int i7 = AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition[tooltipPosition.ordinal()];
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 != 3) {
                    if (i7 == 4) {
                        f2 += (float) i3;
                    }
                }
                f = ((float) i4) / 2.0f;
            } else {
                f2 += ((float) i3) / 2.0f;
                f = (float) i4;
            }
            f3 += f;
        } else {
            f2 += ((float) i3) / 2.0f;
        }
        return new TooltipUVCoordinates(f2 / ((float) i5), 1.0f - (f3 / ((float) i6)));
    }
}
