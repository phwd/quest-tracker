package libcore.io;

import android.system.ErrnoException;
import android.system.GaiException;
import android.system.Int64Ref;
import android.system.OsConstants;
import android.system.StructAddrinfo;
import android.system.StructLinger;
import android.system.StructPollfd;
import android.system.StructStat;
import android.system.StructStatVfs;
import dalvik.system.BlockGuard;
import dalvik.system.SocketTagger;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class BlockGuardOs extends ForwardingOs {
    public BlockGuardOs(Os os) {
        super(os);
    }

    private FileDescriptor tagSocket(FileDescriptor fd) throws ErrnoException {
        try {
            SocketTagger.get().tag(fd);
            return fd;
        } catch (SocketException e) {
            throw new ErrnoException("socket", OsConstants.EINVAL, e);
        }
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public FileDescriptor accept(FileDescriptor fd, SocketAddress peerAddress) throws ErrnoException, SocketException {
        BlockGuard.getThreadPolicy().onNetwork();
        FileDescriptor acceptFd = super.accept(fd, peerAddress);
        if (isInetSocket(acceptFd)) {
            tagSocket(acceptFd);
        }
        return acceptFd;
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public boolean access(String path, int mode) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return super.access(path, mode);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void chmod(String path, int mode) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.chmod(path, mode);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void chown(String path, int uid, int gid) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.chown(path, uid, gid);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void close(FileDescriptor fd) throws ErrnoException {
        try {
            if (fd.isSocket$() && isLingerSocket(fd)) {
                BlockGuard.getThreadPolicy().onNetwork();
            }
        } catch (ErrnoException e) {
        }
        super.close(fd);
    }

    private static boolean isInetSocket(FileDescriptor fd) throws ErrnoException {
        return isInetDomain(Libcore.os.getsockoptInt(fd, OsConstants.SOL_SOCKET, OsConstants.SO_DOMAIN));
    }

    private static boolean isInetDomain(int domain) {
        return domain == OsConstants.AF_INET || domain == OsConstants.AF_INET6;
    }

    private static boolean isLingerSocket(FileDescriptor fd) throws ErrnoException {
        StructLinger linger = Libcore.os.getsockoptLinger(fd, OsConstants.SOL_SOCKET, OsConstants.SO_LINGER);
        return linger.isOn() && linger.l_linger > 0;
    }

    private static boolean isUdpSocket(FileDescriptor fd) throws ErrnoException {
        return Libcore.os.getsockoptInt(fd, OsConstants.SOL_SOCKET, OsConstants.SO_PROTOCOL) == OsConstants.IPPROTO_UDP;
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void connect(FileDescriptor fd, InetAddress address, int port) throws ErrnoException, SocketException {
        boolean skipGuard = false;
        try {
            skipGuard = isUdpSocket(fd);
        } catch (ErrnoException e) {
        }
        if (!skipGuard) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        super.connect(fd, address, port);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void connect(FileDescriptor fd, SocketAddress address) throws ErrnoException, SocketException {
        boolean skipGuard = false;
        try {
            skipGuard = isUdpSocket(fd);
        } catch (ErrnoException e) {
        }
        if (!skipGuard) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        super.connect(fd, address);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void fchmod(FileDescriptor fd, int mode) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        super.fchmod(fd, mode);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void fchown(FileDescriptor fd, int uid, int gid) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        super.fchown(fd, uid, gid);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void fdatasync(FileDescriptor fd) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        super.fdatasync(fd);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public StructStat fstat(FileDescriptor fd) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.fstat(fd);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public StructStatVfs fstatvfs(FileDescriptor fd) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.fstatvfs(fd);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void fsync(FileDescriptor fd) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        super.fsync(fd);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void ftruncate(FileDescriptor fd, long length) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        super.ftruncate(fd, length);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public InetAddress[] android_getaddrinfo(String node, StructAddrinfo hints, int netId) throws GaiException {
        if (!((hints.ai_flags & OsConstants.AI_NUMERICHOST) != 0)) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        return super.android_getaddrinfo(node, hints, netId);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void lchown(String path, int uid, int gid) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.lchown(path, uid, gid);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void link(String oldPath, String newPath) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(oldPath);
        BlockGuard.getVmPolicy().onPathAccess(newPath);
        super.link(oldPath, newPath);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public long lseek(FileDescriptor fd, long offset, int whence) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.lseek(fd, offset, whence);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public StructStat lstat(String path) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return super.lstat(path);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void mkdir(String path, int mode) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.mkdir(path, mode);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void mkfifo(String path, int mode) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.mkfifo(path, mode);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public FileDescriptor open(String path, int flags, int mode) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        if ((OsConstants.O_ACCMODE & flags) != OsConstants.O_RDONLY) {
            BlockGuard.getThreadPolicy().onWriteToDisk();
        }
        return super.open(path, flags, mode);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int poll(StructPollfd[] fds, int timeoutMs) throws ErrnoException {
        if (timeoutMs != 0) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        return super.poll(fds, timeoutMs);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void posix_fallocate(FileDescriptor fd, long offset, long length) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        super.posix_fallocate(fd, offset, length);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int pread(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.pread(fd, buffer, offset);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int pread(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.pread(fd, bytes, byteOffset, byteCount, offset);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int pwrite(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return super.pwrite(fd, buffer, offset);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int pwrite(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return super.pwrite(fd, bytes, byteOffset, byteCount, offset);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int read(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.read(fd, buffer);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int read(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.read(fd, bytes, byteOffset, byteCount);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public String readlink(String path) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return super.readlink(path);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public String realpath(String path) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return super.realpath(path);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int readv(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.readv(fd, buffers, offsets, byteCounts);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int recvfrom(FileDescriptor fd, ByteBuffer buffer, int flags, InetSocketAddress srcAddress) throws ErrnoException, SocketException {
        BlockGuard.getThreadPolicy().onNetwork();
        return super.recvfrom(fd, buffer, flags, srcAddress);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int recvfrom(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, InetSocketAddress srcAddress) throws ErrnoException, SocketException {
        BlockGuard.getThreadPolicy().onNetwork();
        return super.recvfrom(fd, bytes, byteOffset, byteCount, flags, srcAddress);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void remove(String path) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.remove(path);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void rename(String oldPath, String newPath) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(oldPath);
        BlockGuard.getVmPolicy().onPathAccess(newPath);
        super.rename(oldPath, newPath);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public long sendfile(FileDescriptor outFd, FileDescriptor inFd, Int64Ref offset, long byteCount) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return super.sendfile(outFd, inFd, offset, byteCount);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int sendto(FileDescriptor fd, ByteBuffer buffer, int flags, InetAddress inetAddress, int port) throws ErrnoException, SocketException {
        BlockGuard.getThreadPolicy().onNetwork();
        return super.sendto(fd, buffer, flags, inetAddress, port);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int sendto(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, InetAddress inetAddress, int port) throws ErrnoException, SocketException {
        if (inetAddress != null) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        return super.sendto(fd, bytes, byteOffset, byteCount, flags, inetAddress, port);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public FileDescriptor socket(int domain, int type, int protocol) throws ErrnoException {
        FileDescriptor fd = super.socket(domain, type, protocol);
        if (isInetDomain(domain)) {
            tagSocket(fd);
        }
        return fd;
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void socketpair(int domain, int type, int protocol, FileDescriptor fd1, FileDescriptor fd2) throws ErrnoException {
        super.socketpair(domain, type, protocol, fd1, fd2);
        if (isInetDomain(domain)) {
            tagSocket(fd1);
            tagSocket(fd2);
        }
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public StructStat stat(String path) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return super.stat(path);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public StructStatVfs statvfs(String path) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return super.statvfs(path);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void symlink(String oldPath, String newPath) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(oldPath);
        BlockGuard.getVmPolicy().onPathAccess(newPath);
        super.symlink(oldPath, newPath);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int write(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return super.write(fd, buffer);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int write(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return super.write(fd, bytes, byteOffset, byteCount);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int writev(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException, InterruptedIOException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return super.writev(fd, buffers, offsets, byteCounts);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void execv(String filename, String[] argv) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(filename);
        super.execv(filename, argv);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void execve(String filename, String[] argv, String[] envp) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(filename);
        super.execve(filename, argv, envp);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public byte[] getxattr(String path, String name) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return super.getxattr(path, name);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void msync(long address, long byteCount, int flags) throws ErrnoException {
        if ((OsConstants.MS_SYNC & flags) != 0) {
            BlockGuard.getThreadPolicy().onWriteToDisk();
        }
        super.msync(address, byteCount, flags);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void removexattr(String path, String name) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.removexattr(path, name);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void setxattr(String path, String name, byte[] value, int flags) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        super.setxattr(path, name, value, flags);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int sendto(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, SocketAddress address) throws ErrnoException, SocketException {
        BlockGuard.getThreadPolicy().onNetwork();
        return super.sendto(fd, bytes, byteOffset, byteCount, flags, address);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void unlink(String pathname) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(pathname);
        super.unlink(pathname);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public long splice(FileDescriptor fdIn, Int64Ref offIn, FileDescriptor fdOut, Int64Ref offOut, long len, int flags) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.splice(fdIn, offIn, fdOut, offOut, len, flags);
    }
}
