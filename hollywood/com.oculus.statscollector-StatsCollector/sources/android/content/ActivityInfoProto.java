package android.content;

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

public final class ActivityInfoProto extends GeneratedMessageLite<ActivityInfoProto, Builder> implements ActivityInfoProtoOrBuilder {
    private static final ActivityInfoProto DEFAULT_INSTANCE = new ActivityInfoProto();
    private static volatile Parser<ActivityInfoProto> PARSER;

    private ActivityInfoProto() {
    }

    public enum ScreenOrientation implements Internal.EnumLite {
        SCREEN_ORIENTATION_UNSET(-2),
        SCREEN_ORIENTATION_UNSPECIFIED(-1),
        SCREEN_ORIENTATION_LANDSCAPE(0),
        SCREEN_ORIENTATION_PORTRAIT(1),
        SCREEN_ORIENTATION_USER(2),
        SCREEN_ORIENTATION_BEHIND(3),
        SCREEN_ORIENTATION_SENSOR(4),
        SCREEN_ORIENTATION_NOSENSOR(5),
        SCREEN_ORIENTATION_SENSOR_LANDSCAPE(6),
        SCREEN_ORIENTATION_SENSOR_PORTRAIT(7),
        SCREEN_ORIENTATION_REVERSE_LANDSCAPE(8),
        SCREEN_ORIENTATION_REVERSE_PORTRAIT(9),
        SCREEN_ORIENTATION_FULL_SENSOR(10),
        SCREEN_ORIENTATION_USER_LANDSCAPE(11),
        SCREEN_ORIENTATION_USER_PORTRAIT(12),
        SCREEN_ORIENTATION_FULL_USER(13),
        SCREEN_ORIENTATION_LOCKED(14);
        
        public static final int SCREEN_ORIENTATION_BEHIND_VALUE = 3;
        public static final int SCREEN_ORIENTATION_FULL_SENSOR_VALUE = 10;
        public static final int SCREEN_ORIENTATION_FULL_USER_VALUE = 13;
        public static final int SCREEN_ORIENTATION_LANDSCAPE_VALUE = 0;
        public static final int SCREEN_ORIENTATION_LOCKED_VALUE = 14;
        public static final int SCREEN_ORIENTATION_NOSENSOR_VALUE = 5;
        public static final int SCREEN_ORIENTATION_PORTRAIT_VALUE = 1;
        public static final int SCREEN_ORIENTATION_REVERSE_LANDSCAPE_VALUE = 8;
        public static final int SCREEN_ORIENTATION_REVERSE_PORTRAIT_VALUE = 9;
        public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE_VALUE = 6;
        public static final int SCREEN_ORIENTATION_SENSOR_PORTRAIT_VALUE = 7;
        public static final int SCREEN_ORIENTATION_SENSOR_VALUE = 4;
        public static final int SCREEN_ORIENTATION_UNSET_VALUE = -2;
        public static final int SCREEN_ORIENTATION_UNSPECIFIED_VALUE = -1;
        public static final int SCREEN_ORIENTATION_USER_LANDSCAPE_VALUE = 11;
        public static final int SCREEN_ORIENTATION_USER_PORTRAIT_VALUE = 12;
        public static final int SCREEN_ORIENTATION_USER_VALUE = 2;
        private static final Internal.EnumLiteMap<ScreenOrientation> internalValueMap = new Internal.EnumLiteMap<ScreenOrientation>() {
            /* class android.content.ActivityInfoProto.ScreenOrientation.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ScreenOrientation findValueByNumber(int number) {
                return ScreenOrientation.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ScreenOrientation valueOf(int value2) {
            return forNumber(value2);
        }

        public static ScreenOrientation forNumber(int value2) {
            switch (value2) {
                case -2:
                    return SCREEN_ORIENTATION_UNSET;
                case -1:
                    return SCREEN_ORIENTATION_UNSPECIFIED;
                case 0:
                    return SCREEN_ORIENTATION_LANDSCAPE;
                case 1:
                    return SCREEN_ORIENTATION_PORTRAIT;
                case 2:
                    return SCREEN_ORIENTATION_USER;
                case 3:
                    return SCREEN_ORIENTATION_BEHIND;
                case 4:
                    return SCREEN_ORIENTATION_SENSOR;
                case 5:
                    return SCREEN_ORIENTATION_NOSENSOR;
                case 6:
                    return SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
                case 7:
                    return SCREEN_ORIENTATION_SENSOR_PORTRAIT;
                case 8:
                    return SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                case 9:
                    return SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                case 10:
                    return SCREEN_ORIENTATION_FULL_SENSOR;
                case 11:
                    return SCREEN_ORIENTATION_USER_LANDSCAPE;
                case 12:
                    return SCREEN_ORIENTATION_USER_PORTRAIT;
                case 13:
                    return SCREEN_ORIENTATION_FULL_USER;
                case 14:
                    return SCREEN_ORIENTATION_LOCKED;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ScreenOrientation> internalGetValueMap() {
            return internalValueMap;
        }

        private ScreenOrientation(int value2) {
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

    public static ActivityInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityInfoProto parseFrom(InputStream input) throws IOException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityInfoProto, Builder> implements ActivityInfoProtoOrBuilder {
        private Builder() {
            super(ActivityInfoProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityInfoProto activityInfoProto = (ActivityInfoProto) arg1;
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
                    synchronized (ActivityInfoProto.class) {
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

    public static ActivityInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
