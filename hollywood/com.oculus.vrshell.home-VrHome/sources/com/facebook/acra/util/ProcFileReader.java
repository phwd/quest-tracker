package com.facebook.acra.util;

import javax.annotation.Nullable;

public abstract class ProcFileReader {
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
