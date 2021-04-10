package com.google.common.base;

import java.util.logging.Logger;

public final class Platform {
    private static final Logger logger = Logger.getLogger(Platform.class.getName());
    private static final PatternCompiler patternCompiler = new JdkPatternCompiler((byte) 0);

    private Platform() {
    }

    public static boolean stringIsNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    static final class JdkPatternCompiler implements PatternCompiler {
        private JdkPatternCompiler() {
        }

        /* synthetic */ JdkPatternCompiler(byte b) {
            this();
        }
    }
}
