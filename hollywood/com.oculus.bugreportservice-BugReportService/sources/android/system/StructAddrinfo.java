package android.system;

import libcore.util.Objects;

public final class StructAddrinfo {
    public int ai_family;
    public int ai_flags;
    public int ai_socktype;

    public String toString() {
        return Objects.toString(this);
    }
}
