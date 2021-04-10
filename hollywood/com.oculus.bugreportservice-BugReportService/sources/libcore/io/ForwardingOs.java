package libcore.io;

import android.system.Int32Ref;
import android.system.StructAddrinfo;
import android.system.StructGroupReq;
import android.system.StructIfaddrs;
import android.system.StructLinger;
import android.system.StructPasswd;
import android.system.StructPollfd;
import android.system.StructStat;
import android.system.StructTimeval;
import android.system.StructUtsname;
import java.io.FileDescriptor;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Objects;

public class ForwardingOs implements Os {
    private final Os os;

    protected ForwardingOs(Os os2) {
        this.os = (Os) Objects.requireNonNull(os2);
    }

    @Override // libcore.io.Os
    public FileDescriptor accept(FileDescriptor fileDescriptor, SocketAddress socketAddress) {
        return this.os.accept(fileDescriptor, socketAddress);
    }

    @Override // libcore.io.Os
    public boolean access(String str, int i) {
        return this.os.access(str, i);
    }

    @Override // libcore.io.Os
    public InetAddress[] android_getaddrinfo(String str, StructAddrinfo structAddrinfo, int i) {
        return this.os.android_getaddrinfo(str, structAddrinfo, i);
    }

    @Override // libcore.io.Os
    public void bind(FileDescriptor fileDescriptor, InetAddress inetAddress, int i) {
        this.os.bind(fileDescriptor, inetAddress, i);
    }

    @Override // libcore.io.Os
    public void close(FileDescriptor fileDescriptor) {
        this.os.close(fileDescriptor);
    }

    @Override // libcore.io.Os
    public void android_fdsan_exchange_owner_tag(FileDescriptor fileDescriptor, long j, long j2) {
        this.os.android_fdsan_exchange_owner_tag(fileDescriptor, j, j2);
    }

    @Override // libcore.io.Os
    public void connect(FileDescriptor fileDescriptor, InetAddress inetAddress, int i) {
        this.os.connect(fileDescriptor, inetAddress, i);
    }

    @Override // libcore.io.Os
    public FileDescriptor dup2(FileDescriptor fileDescriptor, int i) {
        return this.os.dup2(fileDescriptor, i);
    }

    @Override // libcore.io.Os
    public int fcntlInt(FileDescriptor fileDescriptor, int i, int i2) {
        return this.os.fcntlInt(fileDescriptor, i, i2);
    }

    @Override // libcore.io.Os
    public int fcntlVoid(FileDescriptor fileDescriptor, int i) {
        return this.os.fcntlVoid(fileDescriptor, i);
    }

    @Override // libcore.io.Os
    public StructStat fstat(FileDescriptor fileDescriptor) {
        return this.os.fstat(fileDescriptor);
    }

    @Override // libcore.io.Os
    public String gai_strerror(int i) {
        return this.os.gai_strerror(i);
    }

    @Override // libcore.io.Os
    public String getenv(String str) {
        return this.os.getenv(str);
    }

    @Override // libcore.io.Os
    public String getnameinfo(InetAddress inetAddress, int i) {
        return this.os.getnameinfo(inetAddress, i);
    }

    @Override // libcore.io.Os
    public StructPasswd getpwuid(int i) {
        return this.os.getpwuid(i);
    }

    @Override // libcore.io.Os
    public SocketAddress getsockname(FileDescriptor fileDescriptor) {
        return this.os.getsockname(fileDescriptor);
    }

    @Override // libcore.io.Os
    public int getsockoptInt(FileDescriptor fileDescriptor, int i, int i2) {
        return this.os.getsockoptInt(fileDescriptor, i, i2);
    }

    @Override // libcore.io.Os
    public StructLinger getsockoptLinger(FileDescriptor fileDescriptor, int i, int i2) {
        return this.os.getsockoptLinger(fileDescriptor, i, i2);
    }

    @Override // libcore.io.Os
    public StructTimeval getsockoptTimeval(FileDescriptor fileDescriptor, int i, int i2) {
        return this.os.getsockoptTimeval(fileDescriptor, i, i2);
    }

    @Override // libcore.io.Os
    public int getuid() {
        return this.os.getuid();
    }

    @Override // libcore.io.Os
    public StructIfaddrs[] getifaddrs() {
        return this.os.getifaddrs();
    }

    @Override // libcore.io.Os
    public int if_nametoindex(String str) {
        return this.os.if_nametoindex(str);
    }

    @Override // libcore.io.Os
    public int ioctlInt(FileDescriptor fileDescriptor, int i, Int32Ref int32Ref) {
        return this.os.ioctlInt(fileDescriptor, i, int32Ref);
    }

    @Override // libcore.io.Os
    public void listen(FileDescriptor fileDescriptor, int i) {
        this.os.listen(fileDescriptor, i);
    }

    @Override // libcore.io.Os
    public long mmap(long j, long j2, int i, int i2, FileDescriptor fileDescriptor, long j3) {
        return this.os.mmap(j, j2, i, i2, fileDescriptor, j3);
    }

    @Override // libcore.io.Os
    public void munmap(long j, long j2) {
        this.os.munmap(j, j2);
    }

    @Override // libcore.io.Os
    public FileDescriptor open(String str, int i, int i2) {
        return this.os.open(str, i, i2);
    }

    @Override // libcore.io.Os
    public int poll(StructPollfd[] structPollfdArr, int i) {
        return this.os.poll(structPollfdArr, i);
    }

    @Override // libcore.io.Os
    public int read(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        return this.os.read(fileDescriptor, bArr, i, i2);
    }

    @Override // libcore.io.Os
    public void remove(String str) {
        this.os.remove(str);
    }

    @Override // libcore.io.Os
    public void setsockoptByte(FileDescriptor fileDescriptor, int i, int i2, int i3) {
        this.os.setsockoptByte(fileDescriptor, i, i2, i3);
    }

    @Override // libcore.io.Os
    public void setsockoptInt(FileDescriptor fileDescriptor, int i, int i2, int i3) {
        this.os.setsockoptInt(fileDescriptor, i, i2, i3);
    }

    @Override // libcore.io.Os
    public void setsockoptIpMreqn(FileDescriptor fileDescriptor, int i, int i2, int i3) {
        this.os.setsockoptIpMreqn(fileDescriptor, i, i2, i3);
    }

    @Override // libcore.io.Os
    public void setsockoptGroupReq(FileDescriptor fileDescriptor, int i, int i2, StructGroupReq structGroupReq) {
        this.os.setsockoptGroupReq(fileDescriptor, i, i2, structGroupReq);
    }

    @Override // libcore.io.Os
    public void setsockoptLinger(FileDescriptor fileDescriptor, int i, int i2, StructLinger structLinger) {
        this.os.setsockoptLinger(fileDescriptor, i, i2, structLinger);
    }

    @Override // libcore.io.Os
    public void setsockoptTimeval(FileDescriptor fileDescriptor, int i, int i2, StructTimeval structTimeval) {
        this.os.setsockoptTimeval(fileDescriptor, i, i2, structTimeval);
    }

    @Override // libcore.io.Os
    public void shutdown(FileDescriptor fileDescriptor, int i) {
        this.os.shutdown(fileDescriptor, i);
    }

    @Override // libcore.io.Os
    public FileDescriptor socket(int i, int i2, int i3) {
        return this.os.socket(i, i2, i3);
    }

    @Override // libcore.io.Os
    public void socketpair(int i, int i2, int i3, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2) {
        this.os.socketpair(i, i2, i3, fileDescriptor, fileDescriptor2);
    }

    @Override // libcore.io.Os
    public StructStat stat(String str) {
        return this.os.stat(str);
    }

    @Override // libcore.io.Os
    public String strerror(int i) {
        return this.os.strerror(i);
    }

    @Override // libcore.io.Os
    public long sysconf(int i) {
        return this.os.sysconf(i);
    }

    @Override // libcore.io.Os
    public StructUtsname uname() {
        return this.os.uname();
    }

    @Override // libcore.io.Os
    public int write(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        return this.os.write(fileDescriptor, bArr, i, i2);
    }

    public String toString() {
        return "ForwardingOs{os=" + this.os + "}";
    }
}
