package com.oculus.vrshell.panels.systemtooltip;

import com.oculus.vrshell.panels.FrameCommandChannel;

public class TooltipManager {
    private String mSystemTooltipConfiguration;
    private TooltipState mSystemTooltipState = TooltipState.NoAction;

    /* access modifiers changed from: private */
    public enum TooltipState {
        NoAction,
        Show,
        Hide
    }

    public void showTooltip(TooltipDefinition tooltipDefinition, TooltipUVCoordinates tooltipUVCoordinates) {
        this.mSystemTooltipState = TooltipState.Show;
        this.mSystemTooltipConfiguration = tooltipDefinition.getTooltipConfigurationJson(tooltipUVCoordinates);
    }

    public void hideTooltip() {
        this.mSystemTooltipState = TooltipState.Hide;
        this.mSystemTooltipConfiguration = "";
    }

    public void frame(FrameCommandChannel frameCommandChannel) {
        if (this.mSystemTooltipState == TooltipState.Show) {
            frameCommandChannel.sendCommand(String.format("tooltip show %s", this.mSystemTooltipConfiguration));
        } else if (this.mSystemTooltipState == TooltipState.Hide) {
            frameCommandChannel.sendCommand("tooltip hide");
        }
        this.mSystemTooltipState = TooltipState.NoAction;
        this.mSystemTooltipConfiguration = "";
    }
}
