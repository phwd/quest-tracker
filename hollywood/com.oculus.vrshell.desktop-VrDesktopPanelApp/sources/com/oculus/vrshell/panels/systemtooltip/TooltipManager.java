package com.oculus.vrshell.panels.systemtooltip;

import com.oculus.vrshell.panels.FrameCommandChannel;
import com.oculus.vrshell.sdk.BuildConfig;

public class TooltipManager {
    private String mSystemTooltipConfiguration;
    private TooltipState mSystemTooltipState = TooltipState.NoAction;

    private enum TooltipState {
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
        this.mSystemTooltipConfiguration = BuildConfig.FLAVOR;
    }

    public void frame(FrameCommandChannel commandChannel) {
        if (this.mSystemTooltipState == TooltipState.Show) {
            commandChannel.sendCommand(String.format("tooltip show %s", this.mSystemTooltipConfiguration));
        } else if (this.mSystemTooltipState == TooltipState.Hide) {
            commandChannel.sendCommand("tooltip hide");
        }
        this.mSystemTooltipState = TooltipState.NoAction;
        this.mSystemTooltipConfiguration = BuildConfig.FLAVOR;
    }
}
