package java.nio.file.attribute;

import java.io.IOException;

public interface FileOwnerAttributeView extends FileAttributeView {
    UserPrincipal getOwner() throws IOException;

    @Override // java.nio.file.attribute.AttributeView
    String name();

    void setOwner(UserPrincipal userPrincipal) throws IOException;
}
