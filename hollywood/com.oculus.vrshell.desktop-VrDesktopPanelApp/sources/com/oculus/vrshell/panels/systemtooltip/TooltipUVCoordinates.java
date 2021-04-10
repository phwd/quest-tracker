package com.oculus.vrshell.panels.systemtooltip;

public final class TooltipUVCoordinates {
    public final float U;
    public final float V;

    public TooltipUVCoordinates(float U2, float V2) {
        this.U = U2;
        this.V = V2;
    }

    public static TooltipUVCoordinates getTooltipUVCoordinates(int hostViewXCoordinate, int hostViewYCoordinate, int hostViewWidth, int hostViewHeight, int rootViewWidth, int rootViewHeight, TooltipPosition tooltipPosition) {
        float tooltipTargetXCoordinate = (float) hostViewXCoordinate;
        float tooltipTargetYCoordinate = (float) hostViewYCoordinate;
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition[tooltipPosition.ordinal()];
        if (i == 1) {
            tooltipTargetXCoordinate += ((float) hostViewWidth) / 2.0f;
        } else if (i == 2) {
            tooltipTargetXCoordinate += ((float) hostViewWidth) / 2.0f;
            tooltipTargetYCoordinate += (float) hostViewHeight;
        } else if (i == 3) {
            tooltipTargetYCoordinate += ((float) hostViewHeight) / 2.0f;
        } else if (i == 4) {
            tooltipTargetXCoordinate += (float) hostViewWidth;
            tooltipTargetYCoordinate += ((float) hostViewHeight) / 2.0f;
        }
        return new TooltipUVCoordinates(tooltipTargetXCoordinate / ((float) rootViewWidth), 1.0f - (tooltipTargetYCoordinate / ((float) rootViewHeight)));
    }

    /* renamed from: com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition = new int[TooltipPosition.values().length];

        static {
            try {
                $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition[TooltipPosition.Top.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition[TooltipPosition.Bottom.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition[TooltipPosition.Left.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipPosition[TooltipPosition.Right.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
}
