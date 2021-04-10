package com.oculus.vrshell.panels;

import com.oculus.vrshell.desktop.Constants;

public enum PanelButton {
    A(1),
    B(2),
    X(4),
    Y(8),
    BACK(32),
    RIGHT_TRIGGER(256),
    TOUCH(4194304),
    REMOTE_TOUCH(Constants.PBUTTON_REMOTE_TOUCH),
    REMOTE_CLICK(8589934592L),
    REMOTE_TRIGGER(17179869184L),
    HOME(34359738368L),
    JOYSTICK_MOVE(Constants.PBUTTON_JOYSTICK_MOVE);
    
    private final long flagValue;

    private PanelButton(long flagValue2) {
        this.flagValue = flagValue2;
    }

    public long getFlagValue() {
        return this.flagValue;
    }

    public static long getAnyActionFlagValue() {
        return TOUCH.getFlagValue() | getClickOrTriggerFlagValue();
    }

    public static long getAnyBackFlagValue() {
        return BACK.getFlagValue() | B.getFlagValue() | Y.getFlagValue();
    }

    public static long getClickOrTriggerFlagValue() {
        return REMOTE_TRIGGER.getFlagValue() | RIGHT_TRIGGER.getFlagValue() | REMOTE_CLICK.getFlagValue() | A.getFlagValue() | X.getFlagValue();
    }
}
