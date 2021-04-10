package java.net;

import java.io.InputStream;
import java.security.Permission;
import java.util.Date;
import java.util.Hashtable;
import sun.security.util.SecurityConstants;

public abstract class URLConnection {
    private static boolean defaultAllowUserInteraction = false;
    private static boolean defaultUseCaches = true;
    private static FileNameMap fileNameMap;
    private static Hashtable handlers = new Hashtable();
    protected boolean allowUserInteraction = defaultAllowUserInteraction;
    protected boolean connected = false;
    protected boolean doInput = true;
    protected boolean doOutput = false;
    protected long ifModifiedSince = 0;
    protected URL url;
    protected boolean useCaches = defaultUseCaches;

    public String getHeaderField(String str) {
        return null;
    }

    public static synchronized FileNameMap getFileNameMap() {
        FileNameMap fileNameMap2;
        synchronized (URLConnection.class) {
            if (fileNameMap == null) {
                fileNameMap = new DefaultFileNameMap();
            }
            fileNameMap2 = fileNameMap;
        }
        return fileNameMap2;
    }

    protected URLConnection(URL url2) {
        this.url = url2;
    }

    public URL getURL() {
        return this.url;
    }

    public long getLastModified() {
        return getHeaderFieldDate("last-modified", 0);
    }

    public long getHeaderFieldDate(String str, long j) {
        try {
            return Date.parse(getHeaderField(str));
        } catch (Exception unused) {
            return j;
        }
    }

    public Permission getPermission() {
        return SecurityConstants.ALL_PERMISSION;
    }

    public InputStream getInputStream() {
        throw new UnknownServiceException("protocol doesn't support input");
    }

    public String toString() {
        return getClass().getName() + ":" + this.url;
    }

    public void setUseCaches(boolean z) {
        if (!this.connected) {
            this.useCaches = z;
            return;
        }
        throw new IllegalStateException("Already connected");
    }

    public boolean getUseCaches() {
        return this.useCaches;
    }

    public static String guessContentTypeFromName(String str) {
        return getFileNameMap().getContentTypeFor(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String guessContentTypeFromStream(java.io.InputStream r20) {
        /*
        // Method dump skipped, instructions count: 640
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.URLConnection.guessContentTypeFromStream(java.io.InputStream):java.lang.String");
    }

    private static boolean checkfpx(InputStream inputStream) {
        int i;
        int i2;
        int i3;
        int i4;
        inputStream.mark(256);
        long skipForward = skipForward(inputStream, 28);
        if (skipForward < 28) {
            inputStream.reset();
            return false;
        }
        int[] iArr = new int[16];
        if (readBytes(iArr, 2, inputStream) < 0) {
            inputStream.reset();
            return false;
        }
        int i5 = iArr[0];
        long j = skipForward + 2;
        if (readBytes(iArr, 2, inputStream) < 0) {
            inputStream.reset();
            return false;
        }
        if (i5 == 254) {
            i2 = iArr[0];
            i = iArr[1] << 8;
        } else {
            i2 = iArr[0] << 8;
            i = iArr[1];
        }
        int i6 = i2 + i;
        long j2 = 48 - (j + 2);
        if (skipForward(inputStream, j2) < j2) {
            inputStream.reset();
            return false;
        } else if (readBytes(iArr, 4, inputStream) < 0) {
            inputStream.reset();
            return false;
        } else {
            if (i5 == 254) {
                i4 = iArr[0] + (iArr[1] << 8) + (iArr[2] << 16);
                i3 = iArr[3] << 24;
            } else {
                i4 = (iArr[0] << 24) + (iArr[1] << 16) + (iArr[2] << 8);
                i3 = iArr[3];
            }
            inputStream.reset();
            long j3 = (((long) (1 << i6)) * ((long) (i4 + i3))) + 512 + 80;
            if (j3 < 0) {
                return false;
            }
            inputStream.mark(((int) j3) + 48);
            if (skipForward(inputStream, j3) < j3) {
                inputStream.reset();
                return false;
            } else if (readBytes(iArr, 16, inputStream) < 0) {
                inputStream.reset();
                return false;
            } else if (i5 == 254 && iArr[0] == 0 && iArr[2] == 97 && iArr[3] == 86 && iArr[4] == 84 && iArr[5] == 193 && iArr[6] == 206 && iArr[7] == 17 && iArr[8] == 133 && iArr[9] == 83 && iArr[10] == 0 && iArr[11] == 170 && iArr[12] == 0 && iArr[13] == 161 && iArr[14] == 249 && iArr[15] == 91) {
                inputStream.reset();
                return true;
            } else if (iArr[3] == 0 && iArr[1] == 97 && iArr[0] == 86 && iArr[5] == 84 && iArr[4] == 193 && iArr[7] == 206 && iArr[6] == 17 && iArr[8] == 133 && iArr[9] == 83 && iArr[10] == 0 && iArr[11] == 170 && iArr[12] == 0 && iArr[13] == 161 && iArr[14] == 249 && iArr[15] == 91) {
                inputStream.reset();
                return true;
            } else {
                inputStream.reset();
                return false;
            }
        }
    }

    private static int readBytes(int[] iArr, int i, InputStream inputStream) {
        byte[] bArr = new byte[i];
        if (inputStream.read(bArr, 0, i) < i) {
            return -1;
        }
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        return 0;
    }

    private static long skipForward(InputStream inputStream, long j) {
        long j2 = 0;
        while (j2 != j) {
            long skip = inputStream.skip(j - j2);
            if (skip <= 0) {
                if (inputStream.read() == -1) {
                    return j2;
                }
                j2++;
            }
            j2 += skip;
        }
        return j2;
    }
}
