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

public final class SurfaceProto extends GeneratedMessageLite<SurfaceProto, Builder> implements SurfaceProtoOrBuilder {
    private static final SurfaceProto DEFAULT_INSTANCE = new SurfaceProto();
    private static volatile Parser<SurfaceProto> PARSER;

    private SurfaceProto() {
    }

    public enum Rotation implements Internal.EnumLite {
        ROTATION_0(0),
        ROTATION_90(1),
        ROTATION_180(2),
        ROTATION_270(3);
        
        public static final int ROTATION_0_VALUE = 0;
        public static final int ROTATION_180_VALUE = 2;
        public static final int ROTATION_270_VALUE = 3;
        public static final int ROTATION_90_VALUE = 1;
        private static final Internal.EnumLiteMap<Rotation> internalValueMap = new Internal.EnumLiteMap<Rotation>() {
            /* class android.view.SurfaceProto.Rotation.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Rotation findValueByNumber(int number) {
                return Rotation.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Rotation valueOf(int value2) {
            return forNumber(value2);
        }

        public static Rotation forNumber(int value2) {
            if (value2 == 0) {
                return ROTATION_0;
            }
            if (value2 == 1) {
                return ROTATION_90;
            }
            if (value2 == 2) {
                return ROTATION_180;
            }
            if (value2 != 3) {
                return null;
            }
            return ROTATION_270;
        }

        public static Internal.EnumLiteMap<Rotation> internalGetValueMap() {
            return internalValueMap;
        }

        private Rotation(int value2) {
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

    public static SurfaceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SurfaceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SurfaceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SurfaceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SurfaceProto parseFrom(InputStream input) throws IOException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SurfaceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SurfaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SurfaceProto parseFrom(CodedInputStream input) throws IOException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SurfaceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SurfaceProto, Builder> implements SurfaceProtoOrBuilder {
        private Builder() {
            super(SurfaceProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SurfaceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SurfaceProto surfaceProto = (SurfaceProto) arg1;
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
                    synchronized (SurfaceProto.class) {
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

    public static SurfaceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SurfaceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
