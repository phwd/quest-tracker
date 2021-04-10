package com.facebook.common.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

public class FbGuavaModule extends GuavaModule {
    @Override // com.fasterxml.jackson.databind.Module, com.fasterxml.jackson.datatype.guava.GuavaModule, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return Version.unknownVersion();
    }
}
