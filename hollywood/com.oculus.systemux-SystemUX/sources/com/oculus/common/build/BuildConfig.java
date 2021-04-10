package com.oculus.common.build;

public class BuildConfig {
    public static final boolean DEBUG = (!Boolean.parseBoolean(null));
    public static final int EXOPACKAGE_FLAGS = 0;
    public static final boolean IS_EXOPACKAGE = Boolean.parseBoolean(null);
    public static final boolean IS_PYTORCH_QUERY_CODEGEN_ENABLED = Boolean.parseBoolean(null);
    public static final String PACKAGE_NAME_HOME = (!Boolean.parseBoolean(null) ? "com.oculus.home" : null);
    public static final String PACKAGE_NAME_HORIZON = (!Boolean.parseBoolean(null) ? "com.oculus.horizon" : null);
    public static final String PROVIDER_SUFFIX = (!Boolean.parseBoolean(null) ? "" : null);

    private BuildConfig() {
    }

    static {
        Boolean.parseBoolean(null);
    }
}
