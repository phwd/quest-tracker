package sun.nio.fs;

import java.io.IOException;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashMap;
import java.util.Map;

final class FileOwnerAttributeViewImpl implements FileOwnerAttributeView, DynamicFileAttributeView {
    private static final String OWNER_NAME = "owner";
    private final boolean isPosixView = false;
    private final FileAttributeView view;

    FileOwnerAttributeViewImpl(PosixFileAttributeView view2) {
        this.view = view2;
    }

    FileOwnerAttributeViewImpl(AclFileAttributeView view2) {
        this.view = view2;
    }

    @Override // java.nio.file.attribute.AttributeView, java.nio.file.attribute.FileOwnerAttributeView
    public String name() {
        return OWNER_NAME;
    }

    @Override // sun.nio.fs.DynamicFileAttributeView
    public void setAttribute(String attribute, Object value) throws IOException {
        if (attribute.equals(OWNER_NAME)) {
            setOwner((UserPrincipal) value);
            return;
        }
        throw new IllegalArgumentException("'" + name() + ":" + attribute + "' not recognized");
    }

    @Override // sun.nio.fs.DynamicFileAttributeView
    public Map<String, Object> readAttributes(String[] attributes) throws IOException {
        Map<String, Object> result = new HashMap<>();
        for (String attribute : attributes) {
            if (attribute.equals("*") || attribute.equals(OWNER_NAME)) {
                result.put(OWNER_NAME, getOwner());
            } else {
                throw new IllegalArgumentException("'" + name() + ":" + attribute + "' not recognized");
            }
        }
        return result;
    }

    @Override // java.nio.file.attribute.FileOwnerAttributeView
    public UserPrincipal getOwner() throws IOException {
        if (this.isPosixView) {
            return ((PosixFileAttributeView) this.view).readAttributes().owner();
        }
        return ((AclFileAttributeView) this.view).getOwner();
    }

    @Override // java.nio.file.attribute.FileOwnerAttributeView
    public void setOwner(UserPrincipal owner) throws IOException {
        if (this.isPosixView) {
            ((PosixFileAttributeView) this.view).setOwner(owner);
        } else {
            ((AclFileAttributeView) this.view).setOwner(owner);
        }
    }
}
