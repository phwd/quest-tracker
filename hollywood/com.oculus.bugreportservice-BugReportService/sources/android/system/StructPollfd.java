package android.system;

import java.io.FileDescriptor;
import libcore.util.Objects;

public final class StructPollfd {
    public short events;
    public FileDescriptor fd;

    public String toString() {
        return Objects.toString(this);
    }
}
