package android.service.print;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class InstalledPrintServiceProto extends GeneratedMessageLite<InstalledPrintServiceProto, Builder> implements InstalledPrintServiceProtoOrBuilder {
    public static final int ADD_PRINTERS_ACTIVITY_FIELD_NUMBER = 3;
    public static final int ADVANCED_OPTIONS_ACTIVITY_FIELD_NUMBER = 4;
    public static final int COMPONENT_NAME_FIELD_NUMBER = 1;
    private static final InstalledPrintServiceProto DEFAULT_INSTANCE = new InstalledPrintServiceProto();
    private static volatile Parser<InstalledPrintServiceProto> PARSER = null;
    public static final int SETTINGS_ACTIVITY_FIELD_NUMBER = 2;
    private String addPrintersActivity_ = "";
    private String advancedOptionsActivity_ = "";
    private int bitField0_;
    private ComponentNameProto componentName_;
    private String settingsActivity_ = "";

    private InstalledPrintServiceProto() {
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public boolean hasComponentName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public ComponentNameProto getComponentName() {
        ComponentNameProto componentNameProto = this.componentName_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponentName(ComponentNameProto value) {
        if (value != null) {
            this.componentName_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponentName(ComponentNameProto.Builder builderForValue) {
        this.componentName_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeComponentName(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.componentName_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.componentName_ = value;
        } else {
            this.componentName_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.componentName_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearComponentName() {
        this.componentName_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public boolean hasSettingsActivity() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public String getSettingsActivity() {
        return this.settingsActivity_;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public ByteString getSettingsActivityBytes() {
        return ByteString.copyFromUtf8(this.settingsActivity_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingsActivity(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.settingsActivity_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingsActivity() {
        this.bitField0_ &= -3;
        this.settingsActivity_ = getDefaultInstance().getSettingsActivity();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingsActivityBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.settingsActivity_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public boolean hasAddPrintersActivity() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public String getAddPrintersActivity() {
        return this.addPrintersActivity_;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public ByteString getAddPrintersActivityBytes() {
        return ByteString.copyFromUtf8(this.addPrintersActivity_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAddPrintersActivity(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.addPrintersActivity_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAddPrintersActivity() {
        this.bitField0_ &= -5;
        this.addPrintersActivity_ = getDefaultInstance().getAddPrintersActivity();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAddPrintersActivityBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.addPrintersActivity_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public boolean hasAdvancedOptionsActivity() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public String getAdvancedOptionsActivity() {
        return this.advancedOptionsActivity_;
    }

    @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
    public ByteString getAdvancedOptionsActivityBytes() {
        return ByteString.copyFromUtf8(this.advancedOptionsActivity_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdvancedOptionsActivity(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.advancedOptionsActivity_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdvancedOptionsActivity() {
        this.bitField0_ &= -9;
        this.advancedOptionsActivity_ = getDefaultInstance().getAdvancedOptionsActivity();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdvancedOptionsActivityBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.advancedOptionsActivity_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getComponentName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getSettingsActivity());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getAddPrintersActivity());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getAdvancedOptionsActivity());
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getComponentName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getSettingsActivity());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getAddPrintersActivity());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getAdvancedOptionsActivity());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static InstalledPrintServiceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static InstalledPrintServiceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static InstalledPrintServiceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static InstalledPrintServiceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static InstalledPrintServiceProto parseFrom(InputStream input) throws IOException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static InstalledPrintServiceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static InstalledPrintServiceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (InstalledPrintServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static InstalledPrintServiceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (InstalledPrintServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static InstalledPrintServiceProto parseFrom(CodedInputStream input) throws IOException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static InstalledPrintServiceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (InstalledPrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(InstalledPrintServiceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<InstalledPrintServiceProto, Builder> implements InstalledPrintServiceProtoOrBuilder {
        private Builder() {
            super(InstalledPrintServiceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public boolean hasComponentName() {
            return ((InstalledPrintServiceProto) this.instance).hasComponentName();
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public ComponentNameProto getComponentName() {
            return ((InstalledPrintServiceProto) this.instance).getComponentName();
        }

        public Builder setComponentName(ComponentNameProto value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setComponentName((InstalledPrintServiceProto) value);
            return this;
        }

        public Builder setComponentName(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setComponentName((InstalledPrintServiceProto) builderForValue);
            return this;
        }

        public Builder mergeComponentName(ComponentNameProto value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).mergeComponentName(value);
            return this;
        }

        public Builder clearComponentName() {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).clearComponentName();
            return this;
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public boolean hasSettingsActivity() {
            return ((InstalledPrintServiceProto) this.instance).hasSettingsActivity();
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public String getSettingsActivity() {
            return ((InstalledPrintServiceProto) this.instance).getSettingsActivity();
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public ByteString getSettingsActivityBytes() {
            return ((InstalledPrintServiceProto) this.instance).getSettingsActivityBytes();
        }

        public Builder setSettingsActivity(String value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setSettingsActivity(value);
            return this;
        }

        public Builder clearSettingsActivity() {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).clearSettingsActivity();
            return this;
        }

        public Builder setSettingsActivityBytes(ByteString value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setSettingsActivityBytes(value);
            return this;
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public boolean hasAddPrintersActivity() {
            return ((InstalledPrintServiceProto) this.instance).hasAddPrintersActivity();
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public String getAddPrintersActivity() {
            return ((InstalledPrintServiceProto) this.instance).getAddPrintersActivity();
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public ByteString getAddPrintersActivityBytes() {
            return ((InstalledPrintServiceProto) this.instance).getAddPrintersActivityBytes();
        }

        public Builder setAddPrintersActivity(String value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setAddPrintersActivity(value);
            return this;
        }

        public Builder clearAddPrintersActivity() {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).clearAddPrintersActivity();
            return this;
        }

        public Builder setAddPrintersActivityBytes(ByteString value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setAddPrintersActivityBytes(value);
            return this;
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public boolean hasAdvancedOptionsActivity() {
            return ((InstalledPrintServiceProto) this.instance).hasAdvancedOptionsActivity();
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public String getAdvancedOptionsActivity() {
            return ((InstalledPrintServiceProto) this.instance).getAdvancedOptionsActivity();
        }

        @Override // android.service.print.InstalledPrintServiceProtoOrBuilder
        public ByteString getAdvancedOptionsActivityBytes() {
            return ((InstalledPrintServiceProto) this.instance).getAdvancedOptionsActivityBytes();
        }

        public Builder setAdvancedOptionsActivity(String value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setAdvancedOptionsActivity(value);
            return this;
        }

        public Builder clearAdvancedOptionsActivity() {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).clearAdvancedOptionsActivity();
            return this;
        }

        public Builder setAdvancedOptionsActivityBytes(ByteString value) {
            copyOnWrite();
            ((InstalledPrintServiceProto) this.instance).setAdvancedOptionsActivityBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new InstalledPrintServiceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                InstalledPrintServiceProto other = (InstalledPrintServiceProto) arg1;
                this.componentName_ = (ComponentNameProto) visitor.visitMessage(this.componentName_, other.componentName_);
                this.settingsActivity_ = visitor.visitString(hasSettingsActivity(), this.settingsActivity_, other.hasSettingsActivity(), other.settingsActivity_);
                this.addPrintersActivity_ = visitor.visitString(hasAddPrintersActivity(), this.addPrintersActivity_, other.hasAddPrintersActivity(), other.addPrintersActivity_);
                this.advancedOptionsActivity_ = visitor.visitString(hasAdvancedOptionsActivity(), this.advancedOptionsActivity_, other.hasAdvancedOptionsActivity(), other.advancedOptionsActivity_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            ComponentNameProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ComponentNameProto.Builder) this.componentName_.toBuilder();
                            }
                            this.componentName_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.componentName_);
                                this.componentName_ = (ComponentNameProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.settingsActivity_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.addPrintersActivity_ = s2;
                        } else if (tag == 34) {
                            String s3 = input.readString();
                            this.bitField0_ |= 8;
                            this.advancedOptionsActivity_ = s3;
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (InstalledPrintServiceProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static InstalledPrintServiceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<InstalledPrintServiceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
