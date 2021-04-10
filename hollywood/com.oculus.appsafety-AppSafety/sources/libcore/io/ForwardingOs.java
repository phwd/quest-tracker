package libcore.io;

import android.system.ErrnoException;
import android.system.GaiException;
import android.system.Int32Ref;
import android.system.Int64Ref;
import android.system.StructAddrinfo;
import android.system.StructCapUserData;
import android.system.StructCapUserHeader;
import android.system.StructFlock;
import android.system.StructGroupReq;
import android.system.StructIfaddrs;
import android.system.StructLinger;
import android.system.StructPasswd;
import android.system.StructPollfd;
import android.system.StructRlimit;
import android.system.StructStat;
import android.system.StructStatVfs;
import android.system.StructTimeval;
import android.system.StructUcred;
import android.system.StructUtsname;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Objects;

public class ForwardingOs implements Os {
    private final Os os;

    protected ForwardingOs(Os os2) {
        this.os = (Os) Objects.requireNonNull(os2);
    }

    /* access modifiers changed from: protected */
    public final Os delegate() {
        return this.os;
    }

    @Override // libcore.io.Os
    public FileDescriptor accept(FileDescriptor fd, SocketAddress peerAddress) throws ErrnoException, SocketException {
        return this.os.accept(fd, peerAddress);
    }

    @Override // libcore.io.Os
    public boolean access(String path, int mode) throws ErrnoException {
        return this.os.access(path, mode);
    }

    @Override // libcore.io.Os
    public InetAddress[] android_getaddrinfo(String node, StructAddrinfo hints, int netId) throws GaiException {
        return this.os.android_getaddrinfo(node, hints, netId);
    }

    @Override // libcore.io.Os
    public void bind(FileDescriptor fd, InetAddress address, int port) throws ErrnoException, SocketException {
        this.os.bind(fd, address, port);
    }

    @Override // libcore.io.Os
    public void bind(FileDescriptor fd, SocketAddress address) throws ErrnoException, SocketException {
        this.os.bind(fd, address);
    }

    @Override // libcore.io.Os
    public StructCapUserData[] capget(StructCapUserHeader hdr) throws ErrnoException {
        return this.os.capget(hdr);
    }

    @Override // libcore.io.Os
    public void capset(StructCapUserHeader hdr, StructCapUserData[] data) throws ErrnoException {
        this.os.capset(hdr, data);
    }

    @Override // libcore.io.Os
    public void chmod(String path, int mode) throws ErrnoException {
        this.os.chmod(path, mode);
    }

    @Override // libcore.io.Os
    public void chown(String path, int uid, int gid) throws ErrnoException {
        this.os.chown(path, uid, gid);
    }

    @Override // libcore.io.Os
    public void close(FileDescriptor fd) throws ErrnoException {
        this.os.close(fd);
    }

    @Override // libcore.io.Os
    public void android_fdsan_exchange_owner_tag(FileDescriptor fd, long previousOwnerId, long newOwnerId) {
        this.os.android_fdsan_exchange_owner_tag(fd, previousOwnerId, newOwnerId);
    }

    @Override // libcore.io.Os
    public long android_fdsan_get_owner_tag(FileDescriptor fd) {
        return this.os.android_fdsan_get_owner_tag(fd);
    }

    @Override // libcore.io.Os
    public String android_fdsan_get_tag_type(long tag) {
        return this.os.android_fdsan_get_tag_type(tag);
    }

    @Override // libcore.io.Os
    public long android_fdsan_get_tag_value(long tag) {
        return this.os.android_fdsan_get_tag_value(tag);
    }

    @Override // libcore.io.Os
    public void connect(FileDescriptor fd, InetAddress address, int port) throws ErrnoException, SocketException {
        this.os.connect(fd, address, port);
    }

    @Override // libcore.io.Os
    public void connect(FileDescriptor fd, SocketAddress address) throws ErrnoException, SocketException {
        this.os.connect(fd, address);
    }

    @Override // libcore.io.Os
    public FileDescriptor dup(FileDescriptor oldFd) throws ErrnoException {
        return this.os.dup(oldFd);
    }

    @Override // libcore.io.Os
    public FileDescriptor dup2(FileDescriptor oldFd, int newFd) throws ErrnoException {
        return this.os.dup2(oldFd, newFd);
    }

    @Override // libcore.io.Os
    public String[] environ() {
        return this.os.environ();
    }

    @Override // libcore.io.Os
    public void execv(String filename, String[] argv) throws ErrnoException {
        this.os.execv(filename, argv);
    }

    @Override // libcore.io.Os
    public void execve(String filename, String[] argv, String[] envp) throws ErrnoException {
        this.os.execve(filename, argv, envp);
    }

    @Override // libcore.io.Os
    public void fchmod(FileDescriptor fd, int mode) throws ErrnoException {
        this.os.fchmod(fd, mode);
    }

    @Override // libcore.io.Os
    public void fchown(FileDescriptor fd, int uid, int gid) throws ErrnoException {
        this.os.fchown(fd, uid, gid);
    }

    @Override // libcore.io.Os
    public int fcntlFlock(FileDescriptor fd, int cmd, StructFlock arg) throws ErrnoException, InterruptedIOException {
        return this.os.fcntlFlock(fd, cmd, arg);
    }

    @Override // libcore.io.Os
    public int fcntlInt(FileDescriptor fd, int cmd, int arg) throws ErrnoException {
        return this.os.fcntlInt(fd, cmd, arg);
    }

    @Override // libcore.io.Os
    public int fcntlVoid(FileDescriptor fd, int cmd) throws ErrnoException {
        return this.os.fcntlVoid(fd, cmd);
    }

    @Override // libcore.io.Os
    public void fdatasync(FileDescriptor fd) throws ErrnoException {
        this.os.fdatasync(fd);
    }

    @Override // libcore.io.Os
    public StructStat fstat(FileDescriptor fd) throws ErrnoException {
        return this.os.fstat(fd);
    }

    @Override // libcore.io.Os
    public StructStatVfs fstatvfs(FileDescriptor fd) throws ErrnoException {
        return this.os.fstatvfs(fd);
    }

    @Override // libcore.io.Os
    public void fsync(FileDescriptor fd) throws ErrnoException {
        this.os.fsync(fd);
    }

    @Override // libcore.io.Os
    public void ftruncate(FileDescriptor fd, long length) throws ErrnoException {
        this.os.ftruncate(fd, length);
    }

    @Override // libcore.io.Os
    public String gai_strerror(int error) {
        return this.os.gai_strerror(error);
    }

    @Override // libcore.io.Os
    public int getegid() {
        return this.os.getegid();
    }

    @Override // libcore.io.Os
    public int geteuid() {
        return this.os.geteuid();
    }

    @Override // libcore.io.Os
    public int getgid() {
        return this.os.getgid();
    }

    @Override // libcore.io.Os
    public String getenv(String name) {
        return this.os.getenv(name);
    }

    @Override // libcore.io.Os
    public String getnameinfo(InetAddress address, int flags) throws GaiException {
        return this.os.getnameinfo(address, flags);
    }

    @Override // libcore.io.Os
    public SocketAddress getpeername(FileDescriptor fd) throws ErrnoException {
        return this.os.getpeername(fd);
    }

    @Override // libcore.io.Os
    public int getpgid(int pid) throws ErrnoException {
        return this.os.getpgid(pid);
    }

    @Override // libcore.io.Os
    public int getpid() {
        return this.os.getpid();
    }

    @Override // libcore.io.Os
    public int getppid() {
        return this.os.getppid();
    }

    @Override // libcore.io.Os
    public StructPasswd getpwnam(String name) throws ErrnoException {
        return this.os.getpwnam(name);
    }

    @Override // libcore.io.Os
    public StructPasswd getpwuid(int uid) throws ErrnoException {
        return this.os.getpwuid(uid);
    }

    @Override // libcore.io.Os
    public StructRlimit getrlimit(int resource) throws ErrnoException {
        return this.os.getrlimit(resource);
    }

    @Override // libcore.io.Os
    public SocketAddress getsockname(FileDescriptor fd) throws ErrnoException {
        return this.os.getsockname(fd);
    }

    @Override // libcore.io.Os
    public int getsockoptByte(FileDescriptor fd, int level, int option) throws ErrnoException {
        return this.os.getsockoptByte(fd, level, option);
    }

    @Override // libcore.io.Os
    public InetAddress getsockoptInAddr(FileDescriptor fd, int level, int option) throws ErrnoException {
        return this.os.getsockoptInAddr(fd, level, option);
    }

    @Override // libcore.io.Os
    public int getsockoptInt(FileDescriptor fd, int level, int option) throws ErrnoException {
        return this.os.getsockoptInt(fd, level, option);
    }

    @Override // libcore.io.Os
    public StructLinger getsockoptLinger(FileDescriptor fd, int level, int option) throws ErrnoException {
        return this.os.getsockoptLinger(fd, level, option);
    }

    @Override // libcore.io.Os
    public StructTimeval getsockoptTimeval(FileDescriptor fd, int level, int option) throws ErrnoException {
        return this.os.getsockoptTimeval(fd, level, option);
    }

    @Override // libcore.io.Os
    public StructUcred getsockoptUcred(FileDescriptor fd, int level, int option) throws ErrnoException {
        return this.os.getsockoptUcred(fd, level, option);
    }

    @Override // libcore.io.Os
    public int gettid() {
        return this.os.gettid();
    }

    @Override // libcore.io.Os
    public int getuid() {
        return this.os.getuid();
    }

    @Override // libcore.io.Os
    public byte[] getxattr(String path, String name) throws ErrnoException {
        return this.os.getxattr(path, name);
    }

    @Override // libcore.io.Os
    public StructIfaddrs[] getifaddrs() throws ErrnoException {
        return this.os.getifaddrs();
    }

    @Override // libcore.io.Os
    public String if_indextoname(int index) {
        return this.os.if_indextoname(index);
    }

    @Override // libcore.io.Os
    public int if_nametoindex(String name) {
        return this.os.if_nametoindex(name);
    }

    @Override // libcore.io.Os
    public InetAddress inet_pton(int family, String address) {
        return this.os.inet_pton(family, address);
    }

    @Override // libcore.io.Os
    public int ioctlFlags(FileDescriptor fd, String interfaceName) throws ErrnoException {
        return this.os.ioctlFlags(fd, interfaceName);
    }

    @Override // libcore.io.Os
    public InetAddress ioctlInetAddress(FileDescriptor fd, int cmd, String interfaceName) throws ErrnoException {
        return this.os.ioctlInetAddress(fd, cmd, interfaceName);
    }

    @Override // libcore.io.Os
    public int ioctlInt(FileDescriptor fd, int cmd, Int32Ref arg) throws ErrnoException {
        return this.os.ioctlInt(fd, cmd, arg);
    }

    @Override // libcore.io.Os
    public int ioctlMTU(FileDescriptor fd, String interfaceName) throws ErrnoException {
        return this.os.ioctlMTU(fd, interfaceName);
    }

    @Override // libcore.io.Os
    public boolean isatty(FileDescriptor fd) {
        return this.os.isatty(fd);
    }

    @Override // libcore.io.Os
    public void kill(int pid, int signal) throws ErrnoException {
        this.os.kill(pid, signal);
    }

    @Override // libcore.io.Os
    public void lchown(String path, int uid, int gid) throws ErrnoException {
        this.os.lchown(path, uid, gid);
    }

    @Override // libcore.io.Os
    public void link(String oldPath, String newPath) throws ErrnoException {
        this.os.link(oldPath, newPath);
    }

    @Override // libcore.io.Os
    public void listen(FileDescriptor fd, int backlog) throws ErrnoException {
        this.os.listen(fd, backlog);
    }

    @Override // libcore.io.Os
    public String[] listxattr(String path) throws ErrnoException {
        return this.os.listxattr(path);
    }

    @Override // libcore.io.Os
    public long lseek(FileDescriptor fd, long offset, int whence) throws ErrnoException {
        return this.os.lseek(fd, offset, whence);
    }

    @Override // libcore.io.Os
    public StructStat lstat(String path) throws ErrnoException {
        return this.os.lstat(path);
    }

    @Override // libcore.io.Os
    public void mincore(long address, long byteCount, byte[] vector) throws ErrnoException {
        this.os.mincore(address, byteCount, vector);
    }

    @Override // libcore.io.Os
    public void mkdir(String path, int mode) throws ErrnoException {
        this.os.mkdir(path, mode);
    }

    @Override // libcore.io.Os
    public void mkfifo(String path, int mode) throws ErrnoException {
        this.os.mkfifo(path, mode);
    }

    @Override // libcore.io.Os
    public void mlock(long address, long byteCount) throws ErrnoException {
        this.os.mlock(address, byteCount);
    }

    @Override // libcore.io.Os
    public long mmap(long address, long byteCount, int prot, int flags, FileDescriptor fd, long offset) throws ErrnoException {
        return this.os.mmap(address, byteCount, prot, flags, fd, offset);
    }

    @Override // libcore.io.Os
    public void msync(long address, long byteCount, int flags) throws ErrnoException {
        this.os.msync(address, byteCount, flags);
    }

    @Override // libcore.io.Os
    public void munlock(long address, long byteCount) throws ErrnoException {
        this.os.munlock(address, byteCount);
    }

    @Override // libcore.io.Os
    public void munmap(long address, long byteCount) throws ErrnoException {
        this.os.munmap(address, byteCount);
    }

    @Override // libcore.io.Os
    public FileDescriptor open(String path, int flags, int mode) throws ErrnoException {
        return this.os.open(path, flags, mode);
    }

    @Override // libcore.io.Os
    public FileDescriptor[] pipe2(int flags) throws ErrnoException {
        return this.os.pipe2(flags);
    }

    @Override // libcore.io.Os
    public int poll(StructPollfd[] fds, int timeoutMs) throws ErrnoException {
        return this.os.poll(fds, timeoutMs);
    }

    @Override // libcore.io.Os
    public void posix_fallocate(FileDescriptor fd, long offset, long length) throws ErrnoException {
        this.os.posix_fallocate(fd, offset, length);
    }

    @Override // libcore.io.Os
    public int prctl(int option, long arg2, long arg3, long arg4, long arg5) throws ErrnoException {
        return this.os.prctl(option, arg2, arg3, arg4, arg5);
    }

    @Override // libcore.io.Os
    public int pread(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException, InterruptedIOException {
        return this.os.pread(fd, buffer, offset);
    }

    @Override // libcore.io.Os
    public int pread(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException, InterruptedIOException {
        return this.os.pread(fd, bytes, byteOffset, byteCount, offset);
    }

    @Override // libcore.io.Os
    public int pwrite(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException, InterruptedIOException {
        return this.os.pwrite(fd, buffer, offset);
    }

    @Override // libcore.io.Os
    public int pwrite(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException, InterruptedIOException {
        return this.os.pwrite(fd, bytes, byteOffset, byteCount, offset);
    }

    @Override // libcore.io.Os
    public int read(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException, InterruptedIOException {
        return this.os.read(fd, buffer);
    }

    @Override // libcore.io.Os
    public int read(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException, InterruptedIOException {
        return this.os.read(fd, bytes, byteOffset, byteCount);
    }

    @Override // libcore.io.Os
    public String readlink(String path) throws ErrnoException {
        return this.os.readlink(path);
    }

    @Override // libcore.io.Os
    public String realpath(String path) throws ErrnoException {
        return this.os.realpath(path);
    }

    @Override // libcore.io.Os
    public int readv(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException, InterruptedIOException {
        return this.os.readv(fd, buffers, offsets, byteCounts);
    }

    @Override // libcore.io.Os
    public int recvfrom(FileDescriptor fd, ByteBuffer buffer, int flags, InetSocketAddress srcAddress) throws ErrnoException, SocketException {
        return this.os.recvfrom(fd, buffer, flags, srcAddress);
    }

    @Override // libcore.io.Os
    public int recvfrom(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, InetSocketAddress srcAddress) throws ErrnoException, SocketException {
        return this.os.recvfrom(fd, bytes, byteOffset, byteCount, flags, srcAddress);
    }

    @Override // libcore.io.Os
    public void remove(String path) throws ErrnoException {
        this.os.remove(path);
    }

    @Override // libcore.io.Os
    public void removexattr(String path, String name) throws ErrnoException {
        this.os.removexattr(path, name);
    }

    @Override // libcore.io.Os
    public void rename(String oldPath, String newPath) throws ErrnoException {
        this.os.rename(oldPath, newPath);
    }

    @Override // libcore.io.Os
    public long sendfile(FileDescriptor outFd, FileDescriptor inFd, Int64Ref offset, long byteCount) throws ErrnoException {
        return this.os.sendfile(outFd, inFd, offset, byteCount);
    }

    @Override // libcore.io.Os
    public int sendto(FileDescriptor fd, ByteBuffer buffer, int flags, InetAddress inetAddress, int port) throws ErrnoException, SocketException {
        return this.os.sendto(fd, buffer, flags, inetAddress, port);
    }

    @Override // libcore.io.Os
    public int sendto(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, InetAddress inetAddress, int port) throws ErrnoException, SocketException {
        return this.os.sendto(fd, bytes, byteOffset, byteCount, flags, inetAddress, port);
    }

    @Override // libcore.io.Os
    public int sendto(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, SocketAddress address) throws ErrnoException, SocketException {
        return this.os.sendto(fd, bytes, byteOffset, byteCount, flags, address);
    }

    @Override // libcore.io.Os
    public void setegid(int egid) throws ErrnoException {
        this.os.setegid(egid);
    }

    @Override // libcore.io.Os
    public void setenv(String name, String value, boolean overwrite) throws ErrnoException {
        this.os.setenv(name, value, overwrite);
    }

    @Override // libcore.io.Os
    public void seteuid(int euid) throws ErrnoException {
        this.os.seteuid(euid);
    }

    @Override // libcore.io.Os
    public void setgid(int gid) throws ErrnoException {
        this.os.setgid(gid);
    }

    @Override // libcore.io.Os
    public void setpgid(int pid, int pgid) throws ErrnoException {
        this.os.setpgid(pid, pgid);
    }

    @Override // libcore.io.Os
    public void setregid(int rgid, int egid) throws ErrnoException {
        this.os.setregid(rgid, egid);
    }

    @Override // libcore.io.Os
    public void setreuid(int ruid, int euid) throws ErrnoException {
        this.os.setreuid(ruid, euid);
    }

    @Override // libcore.io.Os
    public int setsid() throws ErrnoException {
        return this.os.setsid();
    }

    @Override // libcore.io.Os
    public void setsockoptByte(FileDescriptor fd, int level, int option, int value) throws ErrnoException {
        this.os.setsockoptByte(fd, level, option, value);
    }

    @Override // libcore.io.Os
    public void setsockoptIfreq(FileDescriptor fd, int level, int option, String value) throws ErrnoException {
        this.os.setsockoptIfreq(fd, level, option, value);
    }

    @Override // libcore.io.Os
    public void setsockoptInt(FileDescriptor fd, int level, int option, int value) throws ErrnoException {
        this.os.setsockoptInt(fd, level, option, value);
    }

    @Override // libcore.io.Os
    public void setsockoptIpMreqn(FileDescriptor fd, int level, int option, int value) throws ErrnoException {
        this.os.setsockoptIpMreqn(fd, level, option, value);
    }

    @Override // libcore.io.Os
    public void setsockoptGroupReq(FileDescriptor fd, int level, int option, StructGroupReq value) throws ErrnoException {
        this.os.setsockoptGroupReq(fd, level, option, value);
    }

    @Override // libcore.io.Os
    public void setsockoptLinger(FileDescriptor fd, int level, int option, StructLinger value) throws ErrnoException {
        this.os.setsockoptLinger(fd, level, option, value);
    }

    @Override // libcore.io.Os
    public void setsockoptTimeval(FileDescriptor fd, int level, int option, StructTimeval value) throws ErrnoException {
        this.os.setsockoptTimeval(fd, level, option, value);
    }

    @Override // libcore.io.Os
    public void setuid(int uid) throws ErrnoException {
        this.os.setuid(uid);
    }

    @Override // libcore.io.Os
    public void setxattr(String path, String name, byte[] value, int flags) throws ErrnoException {
        this.os.setxattr(path, name, value, flags);
    }

    @Override // libcore.io.Os
    public void shutdown(FileDescriptor fd, int how) throws ErrnoException {
        this.os.shutdown(fd, how);
    }

    @Override // libcore.io.Os
    public FileDescriptor socket(int domain, int type, int protocol) throws ErrnoException {
        return this.os.socket(domain, type, protocol);
    }

    @Override // libcore.io.Os
    public void socketpair(int domain, int type, int protocol, FileDescriptor fd1, FileDescriptor fd2) throws ErrnoException {
        this.os.socketpair(domain, type, protocol, fd1, fd2);
    }

    @Override // libcore.io.Os
    public long splice(FileDescriptor fdIn, Int64Ref offIn, FileDescriptor fdOut, Int64Ref offOut, long len, int flags) throws ErrnoException {
        return this.os.splice(fdIn, offIn, fdOut, offOut, len, flags);
    }

    @Override // libcore.io.Os
    public StructStat stat(String path) throws ErrnoException {
        return this.os.stat(path);
    }

    @Override // libcore.io.Os
    public StructStatVfs statvfs(String path) throws ErrnoException {
        return this.os.statvfs(path);
    }

    @Override // libcore.io.Os
    public String strerror(int errno) {
        return this.os.strerror(errno);
    }

    @Override // libcore.io.Os
    public String strsignal(int signal) {
        return this.os.strsignal(signal);
    }

    @Override // libcore.io.Os
    public void symlink(String oldPath, String newPath) throws ErrnoException {
        this.os.symlink(oldPath, newPath);
    }

    @Override // libcore.io.Os
    public long sysconf(int name) {
        return this.os.sysconf(name);
    }

    @Override // libcore.io.Os
    public void tcdrain(FileDescriptor fd) throws ErrnoException {
        this.os.tcdrain(fd);
    }

    @Override // libcore.io.Os
    public void tcsendbreak(FileDescriptor fd, int duration) throws ErrnoException {
        this.os.tcsendbreak(fd, duration);
    }

    @Override // libcore.io.Os
    public int umask(int mask) {
        return this.os.umask(mask);
    }

    @Override // libcore.io.Os
    public StructUtsname uname() {
        return this.os.uname();
    }

    @Override // libcore.io.Os
    public void unlink(String pathname) throws ErrnoException {
        this.os.unlink(pathname);
    }

    @Override // libcore.io.Os
    public void unsetenv(String name) throws ErrnoException {
        this.os.unsetenv(name);
    }

    @Override // libcore.io.Os
    public int waitpid(int pid, Int32Ref status, int options) throws ErrnoException {
        return this.os.waitpid(pid, status, options);
    }

    @Override // libcore.io.Os
    public int write(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException, InterruptedIOException {
        return this.os.write(fd, buffer);
    }

    @Override // libcore.io.Os
    public int write(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException, InterruptedIOException {
        return this.os.write(fd, bytes, byteOffset, byteCount);
    }

    @Override // libcore.io.Os
    public int writev(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException, InterruptedIOException {
        return this.os.writev(fd, buffers, offsets, byteCounts);
    }

    public String toString() {
        return "ForwardingOs{os=" + ((Object) this.os) + "}";
    }
}
