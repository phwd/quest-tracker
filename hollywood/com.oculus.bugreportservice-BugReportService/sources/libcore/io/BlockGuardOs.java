package libcore.io;

import android.system.ErrnoException;
import android.system.OsConstants;
import android.system.StructAddrinfo;
import android.system.StructLinger;
import android.system.StructPollfd;
import android.system.StructStat;
import dalvik.system.BlockGuard;
import dalvik.system.SocketTagger;
import java.io.FileDescriptor;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;

public class BlockGuardOs extends ForwardingOs {
    public BlockGuardOs(Os os) {
        super(os);
    }

    private FileDescriptor tagSocket(FileDescriptor fileDescriptor) {
        try {
            SocketTagger.get().tag(fileDescriptor);
            return fileDescriptor;
        } catch (SocketException e) {
            throw new ErrnoException("socket", OsConstants.EINVAL, e);
        }
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public FileDescriptor accept(FileDescriptor fileDescriptor, SocketAddress socketAddress) {
        BlockGuard.getThreadPolicy().onNetwork();
        FileDescriptor accept = super.accept(fileDescriptor, socketAddress);
        if (isInetSocket(accept)) {
            tagSocket(accept);
        }
        return accept;
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public boolean access(String str, int i) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(str);
        return super.access(str, i);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void close(FileDescriptor fileDescriptor) {
        try {
            if (fileDescriptor.isSocket$() && isLingerSocket(fileDescriptor)) {
                BlockGuard.getThreadPolicy().onNetwork();
            }
        } catch (ErrnoException unused) {
        }
        super.close(fileDescriptor);
    }

    private static boolean isInetSocket(FileDescriptor fileDescriptor) {
        return isInetDomain(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_DOMAIN));
    }

    private static boolean isInetDomain(int i) {
        return i == OsConstants.AF_INET || i == OsConstants.AF_INET6;
    }

    private static boolean isLingerSocket(FileDescriptor fileDescriptor) {
        StructLinger structLinger = Libcore.os.getsockoptLinger(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_LINGER);
        return structLinger.isOn() && structLinger.l_linger > 0;
    }

    private static boolean isUdpSocket(FileDescriptor fileDescriptor) {
        return Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_PROTOCOL) == OsConstants.IPPROTO_UDP;
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void connect(FileDescriptor fileDescriptor, InetAddress inetAddress, int i) {
        boolean z;
        try {
            z = isUdpSocket(fileDescriptor);
        } catch (ErrnoException unused) {
            z = false;
        }
        if (!z) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        super.connect(fileDescriptor, inetAddress, i);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public StructStat fstat(FileDescriptor fileDescriptor) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.fstat(fileDescriptor);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public InetAddress[] android_getaddrinfo(String str, StructAddrinfo structAddrinfo, int i) {
        if (!((structAddrinfo.ai_flags & OsConstants.AI_NUMERICHOST) != 0)) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        return super.android_getaddrinfo(str, structAddrinfo, i);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public FileDescriptor open(String str, int i, int i2) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(str);
        if ((OsConstants.O_ACCMODE & i) != OsConstants.O_RDONLY) {
            BlockGuard.getThreadPolicy().onWriteToDisk();
        }
        return super.open(str, i, i2);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int poll(StructPollfd[] structPollfdArr, int i) {
        if (i != 0) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        return super.poll(structPollfdArr, i);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int read(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return super.read(fileDescriptor, bArr, i, i2);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void remove(String str) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(str);
        super.remove(str);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public FileDescriptor socket(int i, int i2, int i3) {
        FileDescriptor socket = super.socket(i, i2, i3);
        if (isInetDomain(i)) {
            tagSocket(socket);
        }
        return socket;
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public void socketpair(int i, int i2, int i3, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2) {
        super.socketpair(i, i2, i3, fileDescriptor, fileDescriptor2);
        if (isInetDomain(i)) {
            tagSocket(fileDescriptor);
            tagSocket(fileDescriptor2);
        }
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public StructStat stat(String str) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(str);
        return super.stat(str);
    }

    @Override // libcore.io.Os, libcore.io.ForwardingOs
    public int write(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return super.write(fileDescriptor, bArr, i, i2);
    }
}
