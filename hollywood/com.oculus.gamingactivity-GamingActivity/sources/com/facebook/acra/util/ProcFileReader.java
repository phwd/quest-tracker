package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class ProcFileReader {
    public static final int CANNOT_DETERMINE_OPEN_FDS = -1;
    public static final int SECURITY_EXCEPTION = -2;

    public abstract int getOpenFDCount();

    @Nullable
    public abstract OpenFDLimits getOpenFDLimits();

    public abstract String getOpenFileDescriptors();

    public static ProcFileReader getProcFileReader() {
        if (NativeProcFileReader.isReady()) {
            return NativeProcFileReader.getInstance();
        }
        return JavaProcFileReader.getInstance();
    }

    public static class OpenFDLimits {
        public final int hardLimit;
        public final int softLimit;

        public OpenFDLimits(int softLimit2, int hardLimit2) {
            this.softLimit = softLimit2;
            this.hardLimit = hardLimit2;
        }
    }
}
