package com.fasterxml.jackson.datatype.guava;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.guava.ser.GuavaBeanSerializerModifier;

public class GuavaModule extends Module {
    private final String NAME = "GuavaModule";

    @Override // com.fasterxml.jackson.databind.Module
    public String getModuleName() {
        return "GuavaModule";
    }

    @Override // com.fasterxml.jackson.databind.Module, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return ModuleVersion.instance.version();
    }

    @Override // com.fasterxml.jackson.databind.Module
    public void setupModule(Module.SetupContext setupContext) {
        setupContext.addDeserializers(new GuavaDeserializers());
        setupContext.addSerializers(new GuavaSerializers());
        setupContext.addTypeModifier(new MultimapTypeModifier());
        setupContext.addBeanSerializerModifier(new GuavaBeanSerializerModifier());
    }
}
