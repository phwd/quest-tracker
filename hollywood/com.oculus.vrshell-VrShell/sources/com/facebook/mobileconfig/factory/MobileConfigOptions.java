package com.facebook.mobileconfig.factory;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MobileConfigOptions implements Cloneable {
    public static final MobileConfigOptions GET_LATEST = create().getLatest();
    public static final MobileConfigOptions NONE = create();
    public static final MobileConfigOptions WITHOUT_LOGGING = create().withoutLogging();
    private boolean mGetLatestValue = false;
    private boolean mRequestForValueSource = false;
    private MobileConfigValueSource mValueSource = MobileConfigValueSource.UNKNOWN;
    private boolean mWithoutLogging = false;

    private MobileConfigOptions() {
    }

    private MobileConfigOptions getCopy() {
        if (this != NONE && this != GET_LATEST && this != WITHOUT_LOGGING) {
            return this;
        }
        try {
            return (MobileConfigOptions) clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException("");
        }
    }

    public static MobileConfigOptions create() {
        return new MobileConfigOptions();
    }

    public MobileConfigOptions withoutLogging() {
        MobileConfigOptions copy = getCopy();
        copy.mWithoutLogging = true;
        return copy;
    }

    public MobileConfigOptions getLatest() {
        MobileConfigOptions copy = getCopy();
        copy.mGetLatestValue = true;
        return copy;
    }

    public MobileConfigOptions requestForValueSource() {
        MobileConfigOptions copy = getCopy();
        copy.mRequestForValueSource = true;
        return copy;
    }

    public boolean isWithoutLoggingRequested() {
        return this.mWithoutLogging;
    }

    public boolean isLatestValueRequested() {
        return this.mGetLatestValue;
    }

    public boolean isValueSourceRequested() {
        return this.mRequestForValueSource;
    }

    public void setValueSource(MobileConfigValueSource mobileConfigValueSource) {
        this.mValueSource = mobileConfigValueSource;
    }

    public MobileConfigValueSource getValueSource() {
        return this.mValueSource;
    }
}
