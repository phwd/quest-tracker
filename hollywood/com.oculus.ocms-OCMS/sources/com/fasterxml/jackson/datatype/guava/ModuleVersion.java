package com.fasterxml.jackson.datatype.guava;

import com.fasterxml.jackson.core.util.VersionUtil;

class ModuleVersion extends VersionUtil {
    public static final ModuleVersion instance = new ModuleVersion();

    ModuleVersion() {
    }
}
