package android.app;

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

public final class AlarmManagerProto extends GeneratedMessageLite<AlarmManagerProto, Builder> implements AlarmManagerProtoOrBuilder {
    private static final AlarmManagerProto DEFAULT_INSTANCE = new AlarmManagerProto();
    private static volatile Parser<AlarmManagerProto> PARSER;

    private AlarmManagerProto() {
    }

    public enum AlarmType implements Internal.EnumLite {
        RTC_WAKEUP(0),
        RTC(1),
        ELAPSED_REALTIME_WAKEUP(2),
        ELAPSED_REALTIME(3);
        
        public static final int ELAPSED_REALTIME_VALUE = 3;
        public static final int ELAPSED_REALTIME_WAKEUP_VALUE = 2;
        public static final int RTC_VALUE = 1;
        public static final int RTC_WAKEUP_VALUE = 0;
        private static final Internal.EnumLiteMap<AlarmType> internalValueMap = new Internal.EnumLiteMap<AlarmType>() {
            /* class android.app.AlarmManagerProto.AlarmType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AlarmType findValueByNumber(int number) {
                return AlarmType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static AlarmType valueOf(int value2) {
            return forNumber(value2);
        }

        public static AlarmType forNumber(int value2) {
            if (value2 == 0) {
                return RTC_WAKEUP;
            }
            if (value2 == 1) {
                return RTC;
            }
            if (value2 == 2) {
                return ELAPSED_REALTIME_WAKEUP;
            }
            if (value2 != 3) {
                return null;
            }
            return ELAPSED_REALTIME;
        }

        public static Internal.EnumLiteMap<AlarmType> internalGetValueMap() {
            return internalValueMap;
        }

        private AlarmType(int value2) {
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

    public static AlarmManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmManagerProto parseFrom(InputStream input) throws IOException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AlarmManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AlarmManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AlarmManagerProto, Builder> implements AlarmManagerProtoOrBuilder {
        private Builder() {
            super(AlarmManagerProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AlarmManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AlarmManagerProto alarmManagerProto = (AlarmManagerProto) arg1;
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
                    synchronized (AlarmManagerProto.class) {
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

    public static AlarmManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AlarmManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
