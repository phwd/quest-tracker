package android.os;

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

public final class PowerManagerInternalProto extends GeneratedMessageLite<PowerManagerInternalProto, Builder> implements PowerManagerInternalProtoOrBuilder {
    private static final PowerManagerInternalProto DEFAULT_INSTANCE = new PowerManagerInternalProto();
    private static volatile Parser<PowerManagerInternalProto> PARSER;

    private PowerManagerInternalProto() {
    }

    public enum Wakefulness implements Internal.EnumLite {
        WAKEFULNESS_ASLEEP(0),
        WAKEFULNESS_AWAKE(1),
        WAKEFULNESS_DREAMING(2),
        WAKEFULNESS_DOZING(3);
        
        public static final int WAKEFULNESS_ASLEEP_VALUE = 0;
        public static final int WAKEFULNESS_AWAKE_VALUE = 1;
        public static final int WAKEFULNESS_DOZING_VALUE = 3;
        public static final int WAKEFULNESS_DREAMING_VALUE = 2;
        private static final Internal.EnumLiteMap<Wakefulness> internalValueMap = new Internal.EnumLiteMap<Wakefulness>() {
            /* class android.os.PowerManagerInternalProto.Wakefulness.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Wakefulness findValueByNumber(int number) {
                return Wakefulness.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Wakefulness valueOf(int value2) {
            return forNumber(value2);
        }

        public static Wakefulness forNumber(int value2) {
            if (value2 == 0) {
                return WAKEFULNESS_ASLEEP;
            }
            if (value2 == 1) {
                return WAKEFULNESS_AWAKE;
            }
            if (value2 == 2) {
                return WAKEFULNESS_DREAMING;
            }
            if (value2 != 3) {
                return null;
            }
            return WAKEFULNESS_DOZING;
        }

        public static Internal.EnumLiteMap<Wakefulness> internalGetValueMap() {
            return internalValueMap;
        }

        private Wakefulness(int value2) {
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

    public static PowerManagerInternalProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerManagerInternalProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerManagerInternalProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerManagerInternalProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerManagerInternalProto parseFrom(InputStream input) throws IOException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerInternalProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerManagerInternalProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PowerManagerInternalProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerInternalProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerInternalProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerManagerInternalProto parseFrom(CodedInputStream input) throws IOException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerInternalProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerInternalProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PowerManagerInternalProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PowerManagerInternalProto, Builder> implements PowerManagerInternalProtoOrBuilder {
        private Builder() {
            super(PowerManagerInternalProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PowerManagerInternalProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PowerManagerInternalProto powerManagerInternalProto = (PowerManagerInternalProto) arg1;
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
                    synchronized (PowerManagerInternalProto.class) {
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

    public static PowerManagerInternalProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PowerManagerInternalProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
