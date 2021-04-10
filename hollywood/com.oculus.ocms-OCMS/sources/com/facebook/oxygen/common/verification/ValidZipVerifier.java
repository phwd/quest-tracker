package com.facebook.oxygen.common.verification;

import android.annotation.SuppressLint;
import android.util.Pair;
import androidfb.util.apk.ZipUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.io.Closeables;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ValidZipVerifier {
    public static final int EOCD_LENGTH_OFFSET = 12;
    public static final int EOCD_OFFSET_OFFSET = 16;
    public static final int EOCD_TOTAL_OFFSET = 10;
    private static final short ZIP64_MAGICCOUNT = -1;
    private static final int ZIP64_MAGICVAL = -1;
    private final Config mConfig;

    public interface Config {
        Counters getCounters();

        boolean isZipCompatKillSwitchEnabled();
    }

    public interface Counters {
        void bumpKillSwitchEnabled();

        void bumpKillSwitchFetchFailed();

        void bumpMaybeZip64();

        void bumpNoEOCD();

        void bumpNotZip64();

        void bumpZip64EOCDFound();
    }

    public ValidZipVerifier(Config config) {
        this.mConfig = config;
    }

    public void enforceZipFileFormat(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            enforceZipFileFormat(randomAccessFile);
            enforceZipCompatibility(randomAccessFile);
        } finally {
            Closeables.close(randomAccessFile, true);
        }
    }

    private void enforceZipFileFormat(RandomAccessFile randomAccessFile) throws IOException {
        byte[] bArr = {80, 75, 3, 4};
        byte[] bArr2 = new byte[4];
        randomAccessFile.seek(0);
        if (randomAccessFile.read(bArr2) != bArr2.length) {
            throw new IOException("Zip file is too small.");
        } else if (!Arrays.equals(bArr, bArr2)) {
            throw new SecurityException(String.format(null, "Malicious ZIP file. First 4 bytes: 0x%02X%02X%02X%02X", Byte.valueOf(bArr2[0]), Byte.valueOf(bArr2[1]), Byte.valueOf(bArr2[2]), Byte.valueOf(bArr2[3])));
        }
    }

    @SuppressLint({"CatchGeneralException"})
    private void enforceZipCompatibility(RandomAccessFile randomAccessFile) throws IOException, SecurityException {
        Counters counters = this.mConfig.getCounters();
        try {
            if (this.mConfig.isZipCompatKillSwitchEnabled()) {
                counters.bumpKillSwitchEnabled();
                return;
            }
            Pair<ByteBuffer, Long> findZipEndOfCentralDirectoryRecord = ZipUtils.findZipEndOfCentralDirectoryRecord(randomAccessFile);
            if (findZipEndOfCentralDirectoryRecord == null) {
                counters.bumpNoEOCD();
                throw new SecurityException("Unable to find end of central directory");
            } else if (!ZipUtils.isZip64EndOfCentralDirectoryLocatorPresent(randomAccessFile, ((Long) findZipEndOfCentralDirectoryRecord.second).longValue())) {
                ByteBuffer order = ((ByteBuffer) findZipEndOfCentralDirectoryRecord.first).order(ByteOrder.LITTLE_ENDIAN);
                int i = order.getInt(12);
                int i2 = order.getInt(16);
                short s = order.getShort(10);
                if (i == -1 || i2 == -1 || s == -1) {
                    counters.bumpMaybeZip64();
                    throw new SecurityException("APK may be ZIP64");
                } else {
                    counters.bumpNotZip64();
                }
            } else {
                counters.bumpZip64EOCDFound();
                throw new SecurityException("ZIP64 end of central directory found");
            }
        } catch (Exception unused) {
            counters.bumpKillSwitchFetchFailed();
        }
    }
}
