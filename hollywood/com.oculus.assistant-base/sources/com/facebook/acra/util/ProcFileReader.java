package com.facebook.acra.util;

public abstract class ProcFileReader {
    public static final int CANNOT_DETERMINE_OPEN_FDS = -1;
    public static final int SECURITY_EXCEPTION = -2;

    public abstract int getOpenFDCount();

    public abstract OpenFDLimits getOpenFDLimits();

    public abstract String getOpenFileDescriptors();

    public class OpenFDLimits {
        public final int hardLimit;
        public final int softLimit;

        public OpenFDLimits(int i, int i2) {
            this.softLimit = i;
            this.hardLimit = i2;
        }
    }

    public static ProcFileReader getProcFileReader() {
        if (NativeProcFileReader.sReadyToUse.get()) {
            return NativeProcFileReader.getInstance();
        }
        return JavaProcFileReader.getInstance();
    }
}
