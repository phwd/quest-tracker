package android.graphics;

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

public final class PixelFormatProto extends GeneratedMessageLite<PixelFormatProto, Builder> implements PixelFormatProtoOrBuilder {
    private static final PixelFormatProto DEFAULT_INSTANCE = new PixelFormatProto();
    private static volatile Parser<PixelFormatProto> PARSER;

    private PixelFormatProto() {
    }

    public enum Format implements Internal.EnumLite {
        UNKNOWN(0),
        TRANSLUCENT(-3),
        TRANSPARENT(-2),
        OPAQUE(-1),
        RGBA_8888(1),
        RGBX_8888(2),
        RGB_888(3),
        RGB_565(4),
        RGBA_F16(22),
        RGBA_1010102(43);
        
        public static final int OPAQUE_VALUE = -1;
        public static final int RGBA_1010102_VALUE = 43;
        public static final int RGBA_8888_VALUE = 1;
        public static final int RGBA_F16_VALUE = 22;
        public static final int RGBX_8888_VALUE = 2;
        public static final int RGB_565_VALUE = 4;
        public static final int RGB_888_VALUE = 3;
        public static final int TRANSLUCENT_VALUE = -3;
        public static final int TRANSPARENT_VALUE = -2;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<Format> internalValueMap = new Internal.EnumLiteMap<Format>() {
            /* class android.graphics.PixelFormatProto.Format.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Format findValueByNumber(int number) {
                return Format.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Format valueOf(int value2) {
            return forNumber(value2);
        }

        public static Format forNumber(int value2) {
            if (value2 == 22) {
                return RGBA_F16;
            }
            if (value2 == 43) {
                return RGBA_1010102;
            }
            switch (value2) {
                case -3:
                    return TRANSLUCENT;
                case -2:
                    return TRANSPARENT;
                case -1:
                    return OPAQUE;
                case 0:
                    return UNKNOWN;
                case 1:
                    return RGBA_8888;
                case 2:
                    return RGBX_8888;
                case 3:
                    return RGB_888;
                case 4:
                    return RGB_565;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Format> internalGetValueMap() {
            return internalValueMap;
        }

        private Format(int value2) {
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

    public static PixelFormatProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PixelFormatProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PixelFormatProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PixelFormatProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PixelFormatProto parseFrom(InputStream input) throws IOException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PixelFormatProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PixelFormatProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PixelFormatProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PixelFormatProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PixelFormatProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PixelFormatProto parseFrom(CodedInputStream input) throws IOException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PixelFormatProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PixelFormatProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PixelFormatProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PixelFormatProto, Builder> implements PixelFormatProtoOrBuilder {
        private Builder() {
            super(PixelFormatProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PixelFormatProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PixelFormatProto pixelFormatProto = (PixelFormatProto) arg1;
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
                    synchronized (PixelFormatProto.class) {
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

    public static PixelFormatProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PixelFormatProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
