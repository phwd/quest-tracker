package com.oculus.vrshell.panels.systemtooltip;

import com.oculus.vrshell.panels.FrameCommandChannel;

public class TooltipManager {
    public String mSystemTooltipConfiguration;
    public TooltipState mSystemTooltipState = TooltipState.NoAction;

    public enum TooltipState {
        NoAction,
        Show,
        Hide
    }

    public void frame(FrameCommandChannel frameCommandChannel) {
        String str;
        TooltipState tooltipState = this.mSystemTooltipState;
        if (tooltipState == TooltipState.Show) {
            str = String.format("tooltip show %s", this.mSystemTooltipConfiguration);
        } else {
            if (tooltipState == TooltipState.Hide) {
                str = "tooltip hide";
            }
            this.mSystemTooltipState = TooltipState.NoAction;
            this.mSystemTooltipConfiguration = "";
        }
        frameCommandChannel.sendCommand(str);
        this.mSystemTooltipState = TooltipState.NoAction;
        this.mSystemTooltipConfiguration = "";
    }

    public void hideTooltip() {
        this.mSystemTooltipState = TooltipState.Hide;
        this.mSystemTooltipConfiguration = "";
    }

    public void showTooltip(TooltipDefinition tooltipDefinition, TooltipUVCoordinates tooltipUVCoordinates) {
        this.mSystemTooltipState = TooltipState.Show;
        this.mSystemTooltipConfiguration = tooltipDefinition.getTooltipConfigurationJson(tooltipUVCoordinates);
    }
}
