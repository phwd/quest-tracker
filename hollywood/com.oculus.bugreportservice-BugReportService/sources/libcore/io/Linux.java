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

public final class Linux implements Os {
    private native int readBytes(FileDescriptor fileDescriptor, Object obj, int i, int i2);

    private native int writeBytes(FileDescriptor fileDescriptor, Object obj, int i, int i2);

    @Override // libcore.io.Os
    public native FileDescriptor accept(FileDescriptor fileDescriptor, SocketAddress socketAddress);

    @Override // libcore.io.Os
    public native boolean access(String str, int i);

    @Override // libcore.io.Os
    public native void android_fdsan_exchange_owner_tag(FileDescriptor fileDescriptor, long j, long j2);

    @Override // libcore.io.Os
    public native InetAddress[] android_getaddrinfo(String str, StructAddrinfo structAddrinfo, int i);

    @Override // libcore.io.Os
    public native void bind(FileDescriptor fileDescriptor, InetAddress inetAddress, int i);

    @Override // libcore.io.Os
    public native void close(FileDescriptor fileDescriptor);

    @Override // libcore.io.Os
    public native void connect(FileDescriptor fileDescriptor, InetAddress inetAddress, int i);

    @Override // libcore.io.Os
    public native FileDescriptor dup2(FileDescriptor fileDescriptor, int i);

    @Override // libcore.io.Os
    public native int fcntlInt(FileDescriptor fileDescriptor, int i, int i2);

    @Override // libcore.io.Os
    public native int fcntlVoid(FileDescriptor fileDescriptor, int i);

    @Override // libcore.io.Os
    public native StructStat fstat(FileDescriptor fileDescriptor);

    @Override // libcore.io.Os
    public native String gai_strerror(int i);

    @Override // libcore.io.Os
    public native String getenv(String str);

    @Override // libcore.io.Os
    public native StructIfaddrs[] getifaddrs();

    @Override // libcore.io.Os
    public native String getnameinfo(InetAddress inetAddress, int i);

    @Override // libcore.io.Os
    public native StructPasswd getpwuid(int i);

    @Override // libcore.io.Os
    public native SocketAddress getsockname(FileDescriptor fileDescriptor);

    @Override // libcore.io.Os
    public native int getsockoptInt(FileDescriptor fileDescriptor, int i, int i2);

    @Override // libcore.io.Os
    public native StructLinger getsockoptLinger(FileDescriptor fileDescriptor, int i, int i2);

    @Override // libcore.io.Os
    public native StructTimeval getsockoptTimeval(FileDescriptor fileDescriptor, int i, int i2);

    @Override // libcore.io.Os
    public native int getuid();

    @Override // libcore.io.Os
    public native int if_nametoindex(String str);

    @Override // libcore.io.Os
    public native int ioctlInt(FileDescriptor fileDescriptor, int i, Int32Ref int32Ref);

    @Override // libcore.io.Os
    public native void listen(FileDescriptor fileDescriptor, int i);

    @Override // libcore.io.Os
    public native long mmap(long j, long j2, int i, int i2, FileDescriptor fileDescriptor, long j3);

    @Override // libcore.io.Os
    public native void munmap(long j, long j2);

    @Override // libcore.io.Os
    public native FileDescriptor open(String str, int i, int i2);

    @Override // libcore.io.Os
    public native int poll(StructPollfd[] structPollfdArr, int i);

    @Override // libcore.io.Os
    public native void remove(String str);

    @Override // libcore.io.Os
    public native void setsockoptByte(FileDescriptor fileDescriptor, int i, int i2, int i3);

    @Override // libcore.io.Os
    public native void setsockoptGroupReq(FileDescriptor fileDescriptor, int i, int i2, StructGroupReq structGroupReq);

    @Override // libcore.io.Os
    public native void setsockoptInt(FileDescriptor fileDescriptor, int i, int i2, int i3);

    @Override // libcore.io.Os
    public native void setsockoptIpMreqn(FileDescriptor fileDescriptor, int i, int i2, int i3);

    @Override // libcore.io.Os
    public native void setsockoptLinger(FileDescriptor fileDescriptor, int i, int i2, StructLinger structLinger);

    @Override // libcore.io.Os
    public native void setsockoptTimeval(FileDescriptor fileDescriptor, int i, int i2, StructTimeval structTimeval);

    @Override // libcore.io.Os
    public native void shutdown(FileDescriptor fileDescriptor, int i);

    @Override // libcore.io.Os
    public native FileDescriptor socket(int i, int i2, int i3);

    @Override // libcore.io.Os
    public native void socketpair(int i, int i2, int i3, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2);

    @Override // libcore.io.Os
    public native StructStat stat(String str);

    @Override // libcore.io.Os
    public native String strerror(int i);

    @Override // libcore.io.Os
    public native long sysconf(int i);

    @Override // libcore.io.Os
    public native StructUtsname uname();

    Linux() {
    }

    @Override // libcore.io.Os
    public int read(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        return readBytes(fileDescriptor, bArr, i, i2);
    }

    @Override // libcore.io.Os
    public int write(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        return writeBytes(fileDescriptor, bArr, i, i2);
    }
}
