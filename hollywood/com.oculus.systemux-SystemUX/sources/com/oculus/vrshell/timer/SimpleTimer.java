package com.oculus.vrshell.timer;

import android.util.Log;

public class SimpleTimer {
    private static final long INIT_VALUE = -1;
    private long mDuration;
    private long mEndTime;
    private String mName;
    private long mStartTime;
    private TimeService mTimeService;

    public SimpleTimer() {
        this("");
    }

    public SimpleTimer(String str) {
        this(str, TimeServiceElapsedRealtime.getInstance());
    }

    public SimpleTimer(String str, TimeService timeService) {
        this.mStartTime = -1;
        this.mEndTime = -1;
        this.mDuration = -1;
        setName(str);
        this.mTimeService = timeService;
    }

    public SimpleTimer setName(String str) {
        this.mName = str;
        return this;
    }

    public boolean isStarted() {
        return this.mStartTime != -1;
    }

    public boolean isFinished() {
        return this.mEndTime != -1;
    }

    public boolean isRunning() {
        return isStarted() && !isFinished();
    }

    public long peekDuration() {
        throwIfNotStarted();
        throwIfFinished();
        return this.mTimeService.getCurrentMs() - this.mStartTime;
    }

    public long getDuration() {
        throwIfNotStarted();
        throwIfNotFinished();
        return this.mDuration;
    }

    public SimpleTimer start() {
        throwIfStarted();
        this.mStartTime = this.mTimeService.getCurrentMs();
        return this;
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

    public SimpleTimer finish() {
        throwIfNotStarted();
        throwIfFinished();
        this.mEndTime = this.mTimeService.getCurrentMs();
        this.mDuration = this.mEndTime - this.mStartTime;
        return this;
    }

    public long finishWithLogV(String str) {
        long duration = finish().getDuration();
        Log.v(str, getLogString());
        return duration;
    }

    public long finishWithLogI(String str) {
        long duration = finish().getDuration();
        Log.i(str, getLogString());
        return duration;
    }

    private String getLogString() {
        throwIfNotFinished();
        return String.format("SimpleTimer[%s] took %d ms", this.mName, Long.valueOf(getDuration()));
    }

    private void throwIfStarted() {
        if (isStarted()) {
            throw new RuntimeException("SimpleTimer was already started!");
        }
    }

    private void throwIfNotStarted() {
        if (!isStarted()) {
            throw new RuntimeException("SimpleTimer is not running!");
        }
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
}
