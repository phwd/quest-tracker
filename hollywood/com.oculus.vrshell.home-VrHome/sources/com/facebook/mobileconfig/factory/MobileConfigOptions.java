package com.facebook.mobileconfig.factory;

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
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("");
        }
    }

    public static MobileConfigOptions create() {
        return new MobileConfigOptions();
    }

    public MobileConfigOptions withoutLogging() {
        MobileConfigOptions option = getCopy();
        option.mWithoutLogging = true;
        return option;
    }

    public MobileConfigOptions getLatest() {
        MobileConfigOptions option = getCopy();
        option.mGetLatestValue = true;
        return option;
    }

    public MobileConfigOptions requestForValueSource() {
        MobileConfigOptions option = getCopy();
        option.mRequestForValueSource = true;
        return option;
    }

    public boolean isValueSourceRequested() {
        return this.mRequestForValueSource;
    }

    public void setValueSource(MobileConfigValueSource valueSource) {
        this.mValueSource = valueSource;
    }

    public MobileConfigValueSource getValueSource() {
        return this.mValueSource;
    }
}
