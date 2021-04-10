package com.oculus.vrshell.timer;

public class SimpleTimer {
    public static final long INIT_VALUE = -1;
    public long mDuration;
    public long mEndTime;
    public String mName;
    public long mStartTime;
    public TimeService mTimeService;

    public boolean isFinished() {
        if (this.mEndTime != -1) {
            return true;
        }
        return false;
    }

    public boolean isStarted() {
        if (this.mStartTime != -1) {
            return true;
        }
        return false;
    }

    private String getLogString() {
        throwIfNotFinished();
        return String.format("SimpleTimer[%s] took %d ms", this.mName, Long.valueOf(getDuration()));
    }

    private void throwIfFinished() {
        if (isFinished()) {
            throw new RuntimeException("SimpleTimer was already finished!");
        }
    }

    private void throwIfNotFinished() {
        if (!isFinished()) {
            throw new RuntimeException("SimpleTimer is still running!");
        }
    }

    private void throwIfNotStarted() {
        if (!isStarted()) {
            throw new RuntimeException("SimpleTimer is not running!");
        }
    }

    private void throwIfStarted() {
        if (isStarted()) {
            throw new RuntimeException("SimpleTimer was already started!");
        }
    }

    public SimpleTimer finish() {
        throwIfNotStarted();
        throwIfFinished();
        long currentMs = this.mTimeService.getCurrentMs();
        this.mEndTime = currentMs;
        this.mDuration = currentMs - this.mStartTime;
        return this;
    }

    public long finishWithLogI(String str) {
        finish();
        long duration = getDuration();
        getLogString();
        return duration;
    }

    public long finishWithLogV(String str) {
        finish();
        long duration = getDuration();
        getLogString();
        return duration;
    }

    public long getDuration() {
        throwIfNotStarted();
        throwIfNotFinished();
        return this.mDuration;
    }

    public boolean isRunning() {
        if (!isStarted() || isFinished()) {
            return false;
        }
        return true;
    }

    public long peekDuration() {
        throwIfNotStarted();
        throwIfFinished();
        return this.mTimeService.getCurrentMs() - this.mStartTime;
    }

    public SimpleTimer reset() {
        if (isStarted()) {
            throwIfNotFinished();
        }
        this.mStartTime = -1;
        this.mEndTime = -1;
        this.mDuration = -1;
        return this;
    }

    public SimpleTimer start() {
        throwIfStarted();
        this.mStartTime = this.mTimeService.getCurrentMs();
        return this;
    }

    public SimpleTimer setName(String str) {
        this.mName = str;
        return this;
    }

    public SimpleTimer() {
        this("");
    }

    public SimpleTimer(String str) {
        this(str, TimeServiceElapsedRealtime.sInstance);
    }

    public SimpleTimer(String str, TimeService timeService) {
        this.mStartTime = -1;
        this.mEndTime = -1;
        this.mDuration = -1;
        this.mName = str;
        this.mTimeService = timeService;
    }
}
