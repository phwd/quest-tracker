package com.oculus.os;

import java.util.Optional;

public final class AppQuirks {
    public AppQuirks() {
        throw new RuntimeException("Stub!");
    }

    public static <T> Optional<T> getQuirkForProcess(int pid, Quirk<T> quirk) {
        throw new RuntimeException("Stub!");
    }
}
