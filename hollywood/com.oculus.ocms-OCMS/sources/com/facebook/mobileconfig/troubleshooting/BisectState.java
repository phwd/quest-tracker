package com.facebook.mobileconfig.troubleshooting;

public interface BisectState {
    boolean canContinue();

    String getCulprit();

    int getLeft();

    int getMiddle();

    int getNumberOfStepsMade();

    int getNumberOfStepsRemaining();

    int getRight();

    int getSize();

    String getTaskNumber();

    String getUniqueId();

    boolean isRunning();
}
