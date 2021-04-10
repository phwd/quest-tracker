package com.facebook.common.procread;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@TargetApi(26)
@Nullsafe(Nullsafe.Mode.LOCAL)
final class NewProcReader implements IProcReader {
    private static final int ERROR_LEN = -1;
    private static final int FILE_NOT_EXIST_ERROR_LEN = -2147483647;
    private static final int READ_PROC_FILE_BUFFER_SIZE = 256;
    private static final int READ_PROC_LINE_BUFFER_SIZE = 2048;
    private static final String TAG = "NewProcReader";
    private final Set<String> mFilesCannotAccess = new HashSet();

    static NewProcReader create() {
        return new NewProcReader();
    }

    NewProcReader() {
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean readProcFile(String fileName, int[] format, @Nullable String[] outStrings, @Nullable long[] outLongs, @Nullable float[] outFloats) {
        byte[] buffer = new byte[256];
        int len = readFile(fileName, buffer);
        if (len >= 0) {
            return parseProcLine(buffer, 0, len, format, outStrings, outLongs, outFloats);
        }
        Log.i(TAG, "Unable to open and read process file: " + fileName);
        return false;
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean parseProcLine(byte[] buffer, int startIndex, int endIndex, int[] format, @Nullable String[] outStrings, @Nullable long[] outLongs, @Nullable float[] outFloats) {
        byte c;
        int NB = buffer.length;
        int NS = outStrings != null ? outStrings.length : 0;
        int NL = outLongs != null ? outLongs.length : 0;
        int NR = outFloats != null ? outFloats.length : 0;
        int i = startIndex;
        int di = 0;
        for (int mode : format) {
            if ((mode & 512) != 0) {
                i++;
            } else if ((mode & 1024) != 0) {
                if (buffer[i] == 34) {
                    i++;
                } else {
                    mode &= -1025;
                }
            }
            char term = (char) (mode & 255);
            if (i >= endIndex) {
                Log.i(TAG, "Ran off end of data @" + i);
                return false;
            }
            int end = -1;
            if ((mode & 512) != 0) {
                while (i < endIndex && buffer[i] != 41) {
                    i++;
                }
                end = i;
                i++;
            } else if ((mode & 1024) != 0) {
                while (buffer[i] != 34 && i < endIndex) {
                    i++;
                }
                end = i;
                i++;
            }
            while (i < endIndex && buffer[i] != term) {
                i++;
            }
            if (end < 0) {
                end = i;
            }
            if (i < endIndex) {
                i++;
                if ((mode & 256) != 0) {
                    while (i < endIndex && buffer[i] == term) {
                        i++;
                    }
                }
            }
            if ((mode & 28672) != 0) {
                boolean setEnd = end < NB;
                if (setEnd) {
                    c = buffer[end];
                    buffer[end] = 0;
                } else {
                    c = 0;
                }
                if (!((mode & 16384) == 0 || di >= NR || outFloats == null)) {
                    outFloats[di] = ByteParse.strtof(buffer, i, null);
                }
                if (!((mode & 8192) == 0 || di >= NL || outLongs == null)) {
                    if ((mode & 2048) != 0) {
                        outLongs[di] = (long) buffer[i];
                    } else {
                        outLongs[di] = ByteParse.strtoll(buffer, i, 10, null);
                    }
                }
                if (!((mode & 4096) == 0 || di >= NS || outStrings == null)) {
                    outStrings[di] = new String(buffer, i, findChar(buffer, i, 0, buffer.length) - i);
                }
                if (setEnd) {
                    buffer[end] = c;
                }
                di++;
            }
        }
        return true;
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean readProcLines(String file, String[] reqFields, long[] outFields) {
        if (file == null || reqFields == null || outFields == null) {
            throw new NullPointerException("Cannot pass null values");
        }
        int count = reqFields.length;
        if (count > outFields.length) {
            throw new IllegalArgumentException("Array lengths differ");
        }
        for (int i = 0; i < count; i++) {
            outFields[i] = 0;
        }
        boolean res = true;
        byte[] buffer = new byte[2048];
        int len = readFile(file, buffer);
        if (len < 0) {
            Log.i(TAG, "Unable to read " + file);
            len = 0;
            res = false;
        }
        int bufMaxLen = buffer.length;
        if (len < bufMaxLen) {
            buffer[len] = 0;
        }
        int foundCount = 0;
        int bIdx = 0;
        while (bIdx < len && buffer[bIdx] != 0 && foundCount < count) {
            boolean skipToEol = true;
            int i2 = 0;
            while (true) {
                if (i2 >= count) {
                    break;
                }
                String field = reqFields[i2];
                if (bufferEquals(buffer, bIdx, field)) {
                    bIdx += field.length();
                    while (bIdx < bufMaxLen && (buffer[bIdx] == 0 || buffer[bIdx] == 32 || buffer[bIdx] == 9)) {
                        bIdx++;
                    }
                    while (bIdx < bufMaxLen && buffer[bIdx] >= 48 && buffer[bIdx] <= 57) {
                        bIdx++;
                    }
                    skipToEol = bIdx >= bufMaxLen || buffer[bIdx] != 10;
                    if (bIdx < bufMaxLen && buffer[bIdx] != 0) {
                        buffer[bIdx] = 0;
                        bIdx++;
                    }
                    outFields[i2] = ByteParse.strtoll(buffer, bIdx, 10, null);
                    foundCount++;
                } else {
                    i2++;
                }
            }
            if (skipToEol) {
                while (bIdx < len && buffer[bIdx] != 0 && buffer[bIdx] != 10) {
                    bIdx++;
                }
                if (bIdx < bufMaxLen && buffer[bIdx] == 10) {
                    bIdx++;
                }
            }
        }
        return res;
    }

    private int readFile(String file, byte[] outBuffer) {
        if (this.mFilesCannotAccess.contains(file)) {
            return -1;
        }
        Object threadPolicy = null;
        if (Build.VERSION.SDK_INT >= 9) {
            threadPolicy = StrictMode.allowThreadDiskReads();
        }
        try {
            int len = readFileSystemOs(file, outBuffer);
            if (len == FILE_NOT_EXIST_ERROR_LEN) {
                this.mFilesCannotAccess.add(file);
                return -1;
            } else if (threadPolicy == null || Build.VERSION.SDK_INT < 9) {
                return len;
            } else {
                StrictMode.setThreadPolicy((StrictMode.ThreadPolicy) threadPolicy);
                return len;
            }
        } finally {
            if (threadPolicy != null && Build.VERSION.SDK_INT >= 9) {
                StrictMode.setThreadPolicy((StrictMode.ThreadPolicy) threadPolicy);
            }
        }
    }

    private static int readFileSystemOs(String fileName, byte[] outBuffer) {
        int bufferSize = outBuffer.length;
        try {
            FileDescriptor fd = Os.open(fileName, 0, 0);
            try {
                int len = Os.read(fd, outBuffer, 0, bufferSize - 1);
                try {
                    Os.close(fd);
                } catch (ErrnoException e) {
                }
                return len;
            } catch (ErrnoException | InterruptedIOException e2) {
                Log.i(TAG, "Unable to read process file: " + fileName, e2);
                try {
                    Os.close(fd);
                    return FILE_NOT_EXIST_ERROR_LEN;
                } catch (ErrnoException e3) {
                    return FILE_NOT_EXIST_ERROR_LEN;
                }
            } catch (Throwable th) {
                try {
                    Os.close(fd);
                } catch (ErrnoException e4) {
                }
                throw th;
            }
        } catch (ErrnoException e5) {
            Log.i(TAG, "Unable to raw open process file: " + fileName, e5);
            return FILE_NOT_EXIST_ERROR_LEN;
        }
    }

    private static int findChar(byte[] chars, int start, char toFind, int defaultIdx) {
        for (int i = start; i < chars.length; i++) {
            if (chars[i] == toFind) {
                return i;
            }
        }
        return defaultIdx;
    }

    private static boolean bufferEquals(byte[] buffer, int bufferOffset, String field) {
        int fLen = field.length();
        int bIdx = bufferOffset;
        int fIdx = 0;
        while (bIdx < buffer.length && fIdx < fLen) {
            if (buffer[bIdx] != field.charAt(fIdx)) {
                return false;
            }
            bIdx++;
            fIdx++;
        }
        if (fIdx == fLen) {
            return true;
        }
        return false;
    }
}
