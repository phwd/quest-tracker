package java.nio.channels;

import java.nio.file.attribute.FileAttribute;

public abstract class AsynchronousFileChannel implements AsynchronousChannel {
    private static final FileAttribute[] NO_ATTRIBUTES = new FileAttribute[0];
}
