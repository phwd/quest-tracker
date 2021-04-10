package java.nio.file.attribute;

import java.io.IOException;
import java.util.Set;

public interface PosixFileAttributeView extends BasicFileAttributeView, FileOwnerAttributeView {
    @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView, java.nio.file.attribute.FileOwnerAttributeView
    String name();

    @Override // java.nio.file.attribute.BasicFileAttributeView
    PosixFileAttributes readAttributes() throws IOException;

    void setGroup(GroupPrincipal groupPrincipal) throws IOException;

    void setPermissions(Set<PosixFilePermission> set) throws IOException;
}
