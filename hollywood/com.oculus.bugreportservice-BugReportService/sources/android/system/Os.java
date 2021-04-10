package android.system;

import java.io.FileDescriptor;
import libcore.io.Libcore;

public final class Os {
    public static int fcntlInt(FileDescriptor fileDescriptor, int i, int i2) {
        return Libcore.os.fcntlInt(fileDescriptor, i, i2);
    }

    public static int poll(StructPollfd[] structPollfdArr, int i) {
        return Libcore.os.poll(structPollfdArr, i);
    }
}
