package sun.nio.ch;

import sun.misc.Cleaner;

public interface DirectBuffer {
    long address();

    Cleaner cleaner();
}
