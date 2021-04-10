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

public interface Os {
    FileDescriptor accept(FileDescriptor fileDescriptor, SocketAddress socketAddress);

    boolean access(String str, int i);

    void android_fdsan_exchange_owner_tag(FileDescriptor fileDescriptor, long j, long j2);

    InetAddress[] android_getaddrinfo(String str, StructAddrinfo structAddrinfo, int i);

    void bind(FileDescriptor fileDescriptor, InetAddress inetAddress, int i);

    void close(FileDescriptor fileDescriptor);

    void connect(FileDescriptor fileDescriptor, InetAddress inetAddress, int i);

    FileDescriptor dup2(FileDescriptor fileDescriptor, int i);

    int fcntlInt(FileDescriptor fileDescriptor, int i, int i2);

    int fcntlVoid(FileDescriptor fileDescriptor, int i);

    StructStat fstat(FileDescriptor fileDescriptor);

    String gai_strerror(int i);

    String getenv(String str);

    StructIfaddrs[] getifaddrs();

    String getnameinfo(InetAddress inetAddress, int i);

    StructPasswd getpwuid(int i);

    SocketAddress getsockname(FileDescriptor fileDescriptor);

    int getsockoptInt(FileDescriptor fileDescriptor, int i, int i2);

    StructLinger getsockoptLinger(FileDescriptor fileDescriptor, int i, int i2);

    StructTimeval getsockoptTimeval(FileDescriptor fileDescriptor, int i, int i2);

    int getuid();

    int if_nametoindex(String str);

    int ioctlInt(FileDescriptor fileDescriptor, int i, Int32Ref int32Ref);

    void listen(FileDescriptor fileDescriptor, int i);

    long mmap(long j, long j2, int i, int i2, FileDescriptor fileDescriptor, long j3);

    void munmap(long j, long j2);

    FileDescriptor open(String str, int i, int i2);

    int poll(StructPollfd[] structPollfdArr, int i);

    int read(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2);

    void remove(String str);

    void setsockoptByte(FileDescriptor fileDescriptor, int i, int i2, int i3);

    void setsockoptGroupReq(FileDescriptor fileDescriptor, int i, int i2, StructGroupReq structGroupReq);

    void setsockoptInt(FileDescriptor fileDescriptor, int i, int i2, int i3);

    void setsockoptIpMreqn(FileDescriptor fileDescriptor, int i, int i2, int i3);

    void setsockoptLinger(FileDescriptor fileDescriptor, int i, int i2, StructLinger structLinger);

    void setsockoptTimeval(FileDescriptor fileDescriptor, int i, int i2, StructTimeval structTimeval);

    void shutdown(FileDescriptor fileDescriptor, int i);

    FileDescriptor socket(int i, int i2, int i3);

    void socketpair(int i, int i2, int i3, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2);

    StructStat stat(String str);

    String strerror(int i);

    long sysconf(int i);

    StructUtsname uname();

    int write(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2);
}
