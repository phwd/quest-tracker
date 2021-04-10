package libcore.timezone;

import android.system.ErrnoException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import libcore.io.BufferIterator;
import libcore.io.MemoryMappedFile;
import libcore.util.BasicLruCache;
import libcore.util.ZoneInfo;

public final class ZoneInfoDB {
    private static final TzData DATA = TzData.loadTzDataWithFallback(TimeZoneDataFiles.getTimeZoneFilePaths("tzdata"));

    public static class TzData implements AutoCloseable {
        private int[] byteOffsets;
        private final BasicLruCache cache = new BasicLruCache(1) {
            /* class libcore.timezone.ZoneInfoDB.TzData.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public ZoneInfo create(String str) {
                try {
                    return TzData.this.makeTimeZoneUncached(str);
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to load timezone for ID=" + str, e);
                }
            }
        };
        private boolean closed;
        private String[] ids;
        private MemoryMappedFile mappedFile;
        private int[] rawUtcOffsetsCache;
        private String version;
        private String zoneTab;

        public static TzData loadTzDataWithFallback(String... strArr) {
            for (String str : strArr) {
                TzData tzData = new TzData();
                if (tzData.loadData(str)) {
                    return tzData;
                }
            }
            System.logE("Couldn't find any tzdata file!");
            return createFallback();
        }

        private static TzData createFallback() {
            TzData tzData = new TzData();
            tzData.populateFallback();
            return tzData;
        }

        private TzData() {
        }

        public BufferIterator getBufferIterator(String str) {
            checkNotClosed();
            int binarySearch = Arrays.binarySearch(this.ids, str);
            if (binarySearch < 0) {
                return null;
            }
            int i = this.byteOffsets[binarySearch];
            BufferIterator bigEndianIterator = this.mappedFile.bigEndianIterator();
            bigEndianIterator.skip(i);
            return bigEndianIterator;
        }

        private void populateFallback() {
            this.version = "missing";
            this.zoneTab = "# Emergency fallback data.\n";
            this.ids = new String[]{"GMT"};
            int[] iArr = new int[1];
            this.rawUtcOffsetsCache = iArr;
            this.byteOffsets = iArr;
        }

        private boolean loadData(String str) {
            try {
                this.mappedFile = MemoryMappedFile.mmapRO(str);
                try {
                    readHeader();
                    throw null;
                } catch (Exception e) {
                    close();
                    System.logE("tzdata file \"" + str + "\" was present but invalid!", e);
                    return false;
                }
            } catch (ErrnoException unused) {
                return false;
            }
        }

        private void readHeader() {
            try {
                byte[] bArr = new byte[12];
                this.mappedFile.bigEndianIterator().readByteArray(bArr, 0, bArr.length);
                new String(bArr, 0, 6, StandardCharsets.US_ASCII);
                throw null;
            } catch (IndexOutOfBoundsException e) {
                throw new IOException("Invalid read from data file", e);
            }
        }

        /* access modifiers changed from: package-private */
        public ZoneInfo makeTimeZoneUncached(String str) {
            BufferIterator bufferIterator = getBufferIterator(str);
            if (bufferIterator == null) {
                return null;
            }
            return ZoneInfo.readTimeZone(str, bufferIterator, System.currentTimeMillis());
        }

        public String[] getAvailableIDs() {
            checkNotClosed();
            return (String[]) this.ids.clone();
        }

        public ZoneInfo makeTimeZone(String str) {
            checkNotClosed();
            ZoneInfo zoneInfo = (ZoneInfo) this.cache.get(str);
            if (zoneInfo == null) {
                return null;
            }
            return (ZoneInfo) zoneInfo.clone();
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (!this.closed) {
                this.closed = true;
                this.ids = null;
                this.byteOffsets = null;
                this.rawUtcOffsetsCache = null;
                this.cache.evictAll();
                MemoryMappedFile memoryMappedFile = this.mappedFile;
                if (memoryMappedFile != null) {
                    try {
                        memoryMappedFile.close();
                    } catch (ErrnoException unused) {
                    }
                    this.mappedFile = null;
                }
            }
        }

        private void checkNotClosed() {
            if (this.closed) {
                throw new IllegalStateException("TzData is closed");
            }
        }
    }

    public static TzData getInstance() {
        return DATA;
    }
}
