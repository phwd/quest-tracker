package android.view;

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

public final class DisplayProto extends GeneratedMessageLite<DisplayProto, Builder> implements DisplayProtoOrBuilder {
    private static final DisplayProto DEFAULT_INSTANCE = new DisplayProto();
    private static volatile Parser<DisplayProto> PARSER;

    private DisplayProto() {
    }

    public enum ColorMode implements Internal.EnumLite {
        COLOR_MODE_INVALID(-1),
        COLOR_MODE_DEFAULT(0),
        COLOR_MODE_BT601_625(1),
        COLOR_MODE_BT601_625_UNADJUSTED(2),
        COLOR_MODE_BT601_525(3),
        COLOR_MODE_BT601_525_UNADJUSTED(4),
        COLOR_MODE_BT709(5),
        COLOR_MODE_DCI_P3(6),
        COLOR_MODE_SRGB(7),
        COLOR_MODE_ADOBE_RGB(8),
        COLOR_MODE_DISPLAY_P3(9);
        
        public static final int COLOR_MODE_ADOBE_RGB_VALUE = 8;
        public static final int COLOR_MODE_BT601_525_UNADJUSTED_VALUE = 4;
        public static final int COLOR_MODE_BT601_525_VALUE = 3;
        public static final int COLOR_MODE_BT601_625_UNADJUSTED_VALUE = 2;
        public static final int COLOR_MODE_BT601_625_VALUE = 1;
        public static final int COLOR_MODE_BT709_VALUE = 5;
        public static final int COLOR_MODE_DCI_P3_VALUE = 6;
        public static final int COLOR_MODE_DEFAULT_VALUE = 0;
        public static final int COLOR_MODE_DISPLAY_P3_VALUE = 9;
        public static final int COLOR_MODE_INVALID_VALUE = -1;
        public static final int COLOR_MODE_SRGB_VALUE = 7;
        private static final Internal.EnumLiteMap<ColorMode> internalValueMap = new Internal.EnumLiteMap<ColorMode>() {
            /* class android.view.DisplayProto.ColorMode.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ColorMode findValueByNumber(int number) {
                return ColorMode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ColorMode valueOf(int value2) {
            return forNumber(value2);
        }

        public static ColorMode forNumber(int value2) {
            switch (value2) {
                case -1:
                    return COLOR_MODE_INVALID;
                case 0:
                    return COLOR_MODE_DEFAULT;
                case 1:
                    return COLOR_MODE_BT601_625;
                case 2:
                    return COLOR_MODE_BT601_625_UNADJUSTED;
                case 3:
                    return COLOR_MODE_BT601_525;
                case 4:
                    return COLOR_MODE_BT601_525_UNADJUSTED;
                case 5:
                    return COLOR_MODE_BT709;
                case 6:
                    return COLOR_MODE_DCI_P3;
                case 7:
                    return COLOR_MODE_SRGB;
                case 8:
                    return COLOR_MODE_ADOBE_RGB;
                case 9:
                    return COLOR_MODE_DISPLAY_P3;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ColorMode> internalGetValueMap() {
            return internalValueMap;
        }

        private ColorMode(int value2) {
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

    public static DisplayProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayProto parseFrom(InputStream input) throws IOException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DisplayProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayProto parseFrom(CodedInputStream input) throws IOException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DisplayProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DisplayProto, Builder> implements DisplayProtoOrBuilder {
        private Builder() {
            super(DisplayProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DisplayProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DisplayProto displayProto = (DisplayProto) arg1;
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
                    synchronized (DisplayProto.class) {
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

    public static DisplayProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DisplayProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
