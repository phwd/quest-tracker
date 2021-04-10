package android.providers.settings;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class SettingsProto extends GeneratedMessageLite<SettingsProto, Builder> implements SettingsProtoOrBuilder {
    private static final SettingsProto DEFAULT_INSTANCE = new SettingsProto();
    private static volatile Parser<SettingsProto> PARSER;

    private SettingsProto() {
    }

    public enum ScreenBrightnessMode implements Internal.EnumLite {
        SCREEN_BRIGHTNESS_MODE_MANUAL(0),
        SCREEN_BRIGHTNESS_MODE_AUTOMATIC(1);
        
        public static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC_VALUE = 1;
        public static final int SCREEN_BRIGHTNESS_MODE_MANUAL_VALUE = 0;
        private static final Internal.EnumLiteMap<ScreenBrightnessMode> internalValueMap = new Internal.EnumLiteMap<ScreenBrightnessMode>() {
            /* class android.providers.settings.SettingsProto.ScreenBrightnessMode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ScreenBrightnessMode findValueByNumber(int number) {
                return ScreenBrightnessMode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ScreenBrightnessMode valueOf(int value2) {
            return forNumber(value2);
        }

        public static ScreenBrightnessMode forNumber(int value2) {
            if (value2 == 0) {
                return SCREEN_BRIGHTNESS_MODE_MANUAL;
            }
            if (value2 != 1) {
                return null;
            }
            return SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        }

        public static Internal.EnumLiteMap<ScreenBrightnessMode> internalGetValueMap() {
            return internalValueMap;
        }

        private ScreenBrightnessMode(int value2) {
            this.value = value2;
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size2;
        return size2;
    }

    public static SettingsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingsProto parseFrom(InputStream input) throws IOException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingsProto parseFrom(CodedInputStream input) throws IOException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SettingsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SettingsProto, Builder> implements SettingsProtoOrBuilder {
        private Builder() {
            super(SettingsProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SettingsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SettingsProto settingsProto = (SettingsProto) arg1;
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
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
                    synchronized (SettingsProto.class) {
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

    public static SettingsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SettingsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
