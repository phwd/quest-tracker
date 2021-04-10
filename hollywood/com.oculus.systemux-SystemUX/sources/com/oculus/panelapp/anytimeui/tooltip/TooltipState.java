package com.oculus.panelapp.anytimeui.tooltip;

import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;

public final class TooltipState {
    private boolean mIsTooltipEnabled;
    private final TooltipDefinition mTooltipDefinition;

    public TooltipState(TooltipDefinition tooltipDefinition, boolean z) {
        this.mTooltipDefinition = tooltipDefinition;
        this.mIsTooltipEnabled = z;
    }

    public TooltipDefinition getTooltipDefinition() {
        return this.mTooltipDefinition;
    }

    public boolean isTooltipEnabled() {
        return this.mIsTooltipEnabled;
    }

    public void setTooltipEnabled(boolean z) {
        this.mIsTooltipEnabled = z;
    }
}
