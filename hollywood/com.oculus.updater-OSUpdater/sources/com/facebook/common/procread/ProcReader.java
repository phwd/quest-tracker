package com.facebook.common.procread;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcReader {
    private static final int[] PROC_READ_STRING_ALL_FORMAT = {4096};
    private static IProcReader sProcReader;

    static {
        IProcReader create = OldProcReader.create();
        if (create == null) {
            create = NewProcReader.create();
        }
        sProcReader = create;
    }

    private ProcReader() {
    }

    public static String readProcFileEntirely(String str) {
        String[] strArr = {null};
        readProcFile(str, PROC_READ_STRING_ALL_FORMAT, strArr, null, null);
        return strArr[0];
    }

    public static boolean readProcFile(String str, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        IProcReader iProcReader = sProcReader;
        if (iProcReader == null) {
            return false;
        }
        return iProcReader.readProcFile(str, iArr, strArr, jArr, fArr);
    }

    public static boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        IProcReader iProcReader = sProcReader;
        if (iProcReader == null) {
            return false;
        }
        return iProcReader.parseProcLine(bArr, i, i2, iArr, strArr, jArr, fArr);
    }
}
