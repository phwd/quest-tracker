package java.nio.channels;

import java.nio.MappedByteBuffer;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.nio.file.attribute.FileAttribute;

public abstract class FileChannel extends AbstractInterruptibleChannel implements SeekableByteChannel, GatheringByteChannel, ScatteringByteChannel {
    private static final FileAttribute[] NO_ATTRIBUTES = new FileAttribute[0];

    public abstract MappedByteBuffer map(MapMode mapMode, long j, long j2);

    public abstract long size();

    protected FileChannel() {
    }

    public static class MapMode {
        public static final MapMode PRIVATE = new MapMode("PRIVATE");
        public static final MapMode READ_ONLY = new MapMode("READ_ONLY");
        public static final MapMode READ_WRITE = new MapMode("READ_WRITE");
        private final String name;

        private MapMode(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }
}
