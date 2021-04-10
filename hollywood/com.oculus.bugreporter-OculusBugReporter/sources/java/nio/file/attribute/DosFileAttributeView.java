package java.nio.file.attribute;

import java.io.IOException;

public interface DosFileAttributeView extends BasicFileAttributeView {
    @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView
    String name();

    @Override // java.nio.file.attribute.BasicFileAttributeView
    DosFileAttributes readAttributes() throws IOException;

    void setArchive(boolean z) throws IOException;

    void setHidden(boolean z) throws IOException;

    void setReadOnly(boolean z) throws IOException;

    void setSystem(boolean z) throws IOException;
}
