package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class ProcFileReader {
    public abstract int getOpenFDCount();

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

        public OpenFDLimits(int i, int i2) {
            this.softLimit = i;
            this.hardLimit = i2;
        }
    }
}
