package sun.nio.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

class LinuxUserDefinedFileAttributeView extends AbstractUserDefinedFileAttributeView {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String USER_NAMESPACE = "user.";
    private static final int XATTR_NAME_MAX = 255;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private final UnixPath file;
    private final boolean followLinks;

    private byte[] nameAsBytes(UnixPath file2, String name) throws IOException {
        if (name != null) {
            String name2 = USER_NAMESPACE + name;
            byte[] bytes = Util.toBytes(name2);
            if (bytes.length <= 255) {
                return bytes;
            }
            throw new FileSystemException(file2.getPathForExceptionMessage(), null, "'" + name2 + "' is too big");
        }
        throw new NullPointerException("'name' is null");
    }

    private List<String> asList(long address, int size) {
        List<String> list = new ArrayList<>();
        int start = 0;
        for (int pos = 0; pos < size; pos++) {
            if (unsafe.getByte(((long) pos) + address) == 0) {
                int len = pos - start;
                byte[] value = new byte[len];
                for (int i = 0; i < len; i++) {
                    value[i] = unsafe.getByte(((long) start) + address + ((long) i));
                }
                String s = Util.toString(value);
                if (s.startsWith(USER_NAMESPACE)) {
                    list.add(s.substring(USER_NAMESPACE.length()));
                }
                start = pos + 1;
            }
        }
        return list;
    }

    LinuxUserDefinedFileAttributeView(UnixPath file2, boolean followLinks2) {
        this.file = file2;
        this.followLinks = followLinks2;
    }

    @Override // java.nio.file.attribute.UserDefinedFileAttributeView
    public List<String> list() throws IOException {
        if (System.getSecurityManager() != null) {
            checkAccess(this.file.getPathForPermissionCheck(), true, false);
        }
        int fd = this.file.openForAttributeAccess(this.followLinks);
        NativeBuffer buffer = null;
        int size = 1024;
        try {
            NativeBuffer buffer2 = NativeBuffers.getNativeBuffer(1024);
            while (true) {
                try {
                    List<String> unmodifiableList = Collections.unmodifiableList(asList(buffer2.address(), LinuxNativeDispatcher.flistxattr(fd, buffer2.address(), size)));
                    buffer2.release();
                    LinuxNativeDispatcher.close(fd);
                    return unmodifiableList;
                } catch (UnixException x) {
                    if (x.errno() != UnixConstants.ERANGE || size >= 32768) {
                        String pathForExceptionMessage = this.file.getPathForExceptionMessage();
                        throw new FileSystemException(pathForExceptionMessage, null, "Unable to get list of extended attributes: " + x.getMessage());
                    }
                    buffer2.release();
                    size *= 2;
                    buffer2 = NativeBuffers.getNativeBuffer(size);
                }
            }
            String pathForExceptionMessage2 = this.file.getPathForExceptionMessage();
            throw new FileSystemException(pathForExceptionMessage2, null, "Unable to get list of extended attributes: " + x.getMessage());
        } catch (Throwable th) {
            if (0 != 0) {
                buffer.release();
            }
            LinuxNativeDispatcher.close(fd);
            throw th;
        }
    }

    @Override // java.nio.file.attribute.UserDefinedFileAttributeView
    public int size(String name) throws IOException {
        if (System.getSecurityManager() != null) {
            checkAccess(this.file.getPathForPermissionCheck(), true, false);
        }
        int fd = this.file.openForAttributeAccess(this.followLinks);
        try {
            int fgetxattr = LinuxNativeDispatcher.fgetxattr(fd, nameAsBytes(this.file, name), 0, 0);
            LinuxNativeDispatcher.close(fd);
            return fgetxattr;
        } catch (UnixException x) {
            String pathForExceptionMessage = this.file.getPathForExceptionMessage();
            throw new FileSystemException(pathForExceptionMessage, null, "Unable to get size of extended attribute '" + name + "': " + x.getMessage());
        } catch (Throwable th) {
            LinuxNativeDispatcher.close(fd);
            throw th;
        }
    }

    @Override // java.nio.file.attribute.UserDefinedFileAttributeView
    public int read(String name, ByteBuffer dst) throws IOException {
        NativeBuffer nb;
        long address;
        if (System.getSecurityManager() != null) {
            checkAccess(this.file.getPathForPermissionCheck(), true, false);
        }
        if (!dst.isReadOnly()) {
            int pos = dst.position();
            int lim = dst.limit();
            int rem = pos <= lim ? lim - pos : 0;
            if (dst instanceof DirectBuffer) {
                address = ((DirectBuffer) dst).address() + ((long) pos);
                nb = null;
            } else {
                NativeBuffer nb2 = NativeBuffers.getNativeBuffer(rem);
                address = nb2.address();
                nb = nb2;
            }
            int fd = this.file.openForAttributeAccess(this.followLinks);
            try {
                int n = LinuxNativeDispatcher.fgetxattr(fd, nameAsBytes(this.file, name), address, rem);
                if (rem != 0) {
                    if (nb != null) {
                        for (int i = 0; i < n; i++) {
                            dst.put(unsafe.getByte(((long) i) + address));
                        }
                    }
                    dst.position(pos + n);
                    LinuxNativeDispatcher.close(fd);
                    if (nb != null) {
                        nb.release();
                    }
                    return n;
                } else if (n <= 0) {
                    try {
                        LinuxNativeDispatcher.close(fd);
                        return 0;
                    } finally {
                        if (nb != null) {
                            nb.release();
                        }
                    }
                } else {
                    throw new UnixException(UnixConstants.ERANGE);
                }
            } catch (UnixException x) {
                String msg = x.errno() == UnixConstants.ERANGE ? "Insufficient space in buffer" : x.getMessage();
                throw new FileSystemException(this.file.getPathForExceptionMessage(), null, "Error reading extended attribute '" + name + "': " + msg);
            } catch (Throwable th) {
                LinuxNativeDispatcher.close(fd);
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Read-only buffer");
        }
    }

    @Override // java.nio.file.attribute.UserDefinedFileAttributeView
    public int write(String name, ByteBuffer src) throws IOException {
        long address;
        NativeBuffer nb;
        int rem = 0;
        if (System.getSecurityManager() != null) {
            checkAccess(this.file.getPathForPermissionCheck(), false, true);
        }
        int pos = src.position();
        int lim = src.limit();
        if (pos <= lim) {
            rem = lim - pos;
        }
        if (src instanceof DirectBuffer) {
            nb = null;
            address = ((DirectBuffer) src).address() + ((long) pos);
        } else {
            nb = NativeBuffers.getNativeBuffer(rem);
            address = nb.address();
            if (src.hasArray()) {
                for (int i = 0; i < rem; i++) {
                    unsafe.putByte(((long) i) + address, src.get());
                }
            } else {
                byte[] tmp = new byte[rem];
                src.get(tmp);
                src.position(pos);
                for (int i2 = 0; i2 < rem; i2++) {
                    unsafe.putByte(((long) i2) + address, tmp[i2]);
                }
            }
        }
        int fd = this.file.openForAttributeAccess(this.followLinks);
        try {
            LinuxNativeDispatcher.fsetxattr(fd, nameAsBytes(this.file, name), address, rem);
            src.position(pos + rem);
            try {
                LinuxNativeDispatcher.close(fd);
                return rem;
            } finally {
                if (nb != null) {
                    nb.release();
                }
            }
        } catch (UnixException x) {
            String pathForExceptionMessage = this.file.getPathForExceptionMessage();
            throw new FileSystemException(pathForExceptionMessage, null, "Error writing extended attribute '" + name + "': " + x.getMessage());
        } catch (Throwable th) {
            LinuxNativeDispatcher.close(fd);
            throw th;
        }
    }

    @Override // java.nio.file.attribute.UserDefinedFileAttributeView
    public void delete(String name) throws IOException {
        if (System.getSecurityManager() != null) {
            checkAccess(this.file.getPathForPermissionCheck(), false, true);
        }
        int fd = this.file.openForAttributeAccess(this.followLinks);
        try {
            LinuxNativeDispatcher.fremovexattr(fd, nameAsBytes(this.file, name));
            LinuxNativeDispatcher.close(fd);
        } catch (UnixException x) {
            String pathForExceptionMessage = this.file.getPathForExceptionMessage();
            throw new FileSystemException(pathForExceptionMessage, null, "Unable to delete extended attribute '" + name + "': " + x.getMessage());
        } catch (Throwable th) {
            LinuxNativeDispatcher.close(fd);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void copyExtendedAttributes(int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 112
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.fs.LinuxUserDefinedFileAttributeView.copyExtendedAttributes(int, int):void");
    }

    private static void copyExtendedAttribute(int ofd, byte[] name, int nfd) throws UnixException {
        int size = LinuxNativeDispatcher.fgetxattr(ofd, name, 0, 0);
        NativeBuffer buffer = NativeBuffers.getNativeBuffer(size);
        try {
            long address = buffer.address();
            LinuxNativeDispatcher.fsetxattr(nfd, name, address, LinuxNativeDispatcher.fgetxattr(ofd, name, address, size));
        } finally {
            buffer.release();
        }
    }
}
