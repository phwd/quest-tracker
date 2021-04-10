package android.system;

import java.net.InetAddress;
import libcore.util.Objects;

public final class StructGroupReq {
    public final InetAddress gr_group;

    public String toString() {
        return Objects.toString(this);
    }
}
