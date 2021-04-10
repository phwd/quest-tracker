package com.oculus.vrshell.panels;

import com.facebook.systrace.Systrace;

public enum PanelButton {
    A(1),
    B(2),
    X(4),
    Y(8),
    BACK(32),
    RIGHT_TRIGGER(256),
    TOUCH(Systrace.TRACE_TAG_COMPONENTS),
    REMOTE_TOUCH(Systrace.TRACE_TAG_APP_CORE_INFRA),
    REMOTE_CLICK(8589934592L),
    REMOTE_TRIGGER(17179869184L),
    HOME(Systrace.TRACE_TAG_CLASS_LOADS),
    JOYSTICK_MOVE(Systrace.TRACE_TAG_FRESCO);
    
    private final long flagValue;

    private PanelButton(long j) {
        this.flagValue = j;
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
