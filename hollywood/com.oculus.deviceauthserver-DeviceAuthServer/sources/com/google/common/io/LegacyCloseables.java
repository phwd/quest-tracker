package com.google.common.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class LegacyCloseables {
    private static final Logger logger = Logger.getLogger(LegacyCloseables.class.getName());

    private LegacyCloseables() {
    }

    @Deprecated
    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "IOException thrown while closing Closeable.", (Throwable) e);
            }
        }
    }
}
