package com.facebook.mobileconfig.factory;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.common.build.BuildConfig;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MobileConfigOptions implements Cloneable {
    public static final MobileConfigOptions GET_LATEST;
    public static final MobileConfigOptions NONE = new MobileConfigOptions();
    public static final MobileConfigOptions WITHOUT_LOGGING;
    private boolean mGetLatestValue = false;
    public boolean mRequestForValueSource = false;
    public MobileConfigValueSource mValueSource = MobileConfigValueSource.UNKNOWN;
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
            throw new RuntimeException(BuildConfig.PROVIDER_SUFFIX);
        }
    }

    public static MobileConfigOptions create() {
        return new MobileConfigOptions();
    }

    public final MobileConfigOptions requestForValueSource() {
        MobileConfigOptions copy = getCopy();
        copy.mRequestForValueSource = true;
        return copy;
    }

    static {
        MobileConfigOptions copy = new MobileConfigOptions().getCopy();
        copy.mGetLatestValue = true;
        GET_LATEST = copy;
        MobileConfigOptions copy2 = new MobileConfigOptions().getCopy();
        copy2.mWithoutLogging = true;
        WITHOUT_LOGGING = copy2;
    }
}
