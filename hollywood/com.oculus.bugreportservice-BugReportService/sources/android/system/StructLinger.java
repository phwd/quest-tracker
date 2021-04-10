package android.system;

import libcore.util.Objects;

public final class StructLinger {
    public final int l_linger;
    public final int l_onoff;

    public StructLinger(int i, int i2) {
        this.l_onoff = i;
        this.l_linger = i2;
    }

    public boolean isOn() {
        return this.l_onoff != 0;
    }

    public String toString() {
        return Objects.toString(this);
    }
}
