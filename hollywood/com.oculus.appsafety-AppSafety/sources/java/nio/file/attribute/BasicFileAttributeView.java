package java.nio.file.attribute;

import java.io.IOException;

public interface BasicFileAttributeView extends FileAttributeView {
    @Override // java.nio.file.attribute.AttributeView
    String name();

    BasicFileAttributes readAttributes() throws IOException;

    void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException;
}
