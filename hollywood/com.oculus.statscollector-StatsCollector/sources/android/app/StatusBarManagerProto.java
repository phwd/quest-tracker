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

public final class StatusBarManagerProto extends GeneratedMessageLite<StatusBarManagerProto, Builder> implements StatusBarManagerProtoOrBuilder {
    private static final StatusBarManagerProto DEFAULT_INSTANCE = new StatusBarManagerProto();
    private static volatile Parser<StatusBarManagerProto> PARSER;

    private StatusBarManagerProto() {
    }

    public enum WindowState implements Internal.EnumLite {
        WINDOW_STATE_SHOWING(0),
        WINDOW_STATE_HIDING(1),
        WINDOW_STATE_HIDDEN(2);
        
        public static final int WINDOW_STATE_HIDDEN_VALUE = 2;
        public static final int WINDOW_STATE_HIDING_VALUE = 1;
        public static final int WINDOW_STATE_SHOWING_VALUE = 0;
        private static final Internal.EnumLiteMap<WindowState> internalValueMap = new Internal.EnumLiteMap<WindowState>() {
            /* class android.app.StatusBarManagerProto.WindowState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public WindowState findValueByNumber(int number) {
                return WindowState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static WindowState valueOf(int value2) {
            return forNumber(value2);
        }

        public static WindowState forNumber(int value2) {
            if (value2 == 0) {
                return WINDOW_STATE_SHOWING;
            }
            if (value2 == 1) {
                return WINDOW_STATE_HIDING;
            }
            if (value2 != 2) {
                return null;
            }
            return WINDOW_STATE_HIDDEN;
        }

        public static Internal.EnumLiteMap<WindowState> internalGetValueMap() {
            return internalValueMap;
        }

        private WindowState(int value2) {
            this.value = value2;
        }
    }

    public enum TransientWindowState implements Internal.EnumLite {
        TRANSIENT_BAR_NONE(0),
        TRANSIENT_BAR_SHOW_REQUESTED(1),
        TRANSIENT_BAR_SHOWING(2),
        TRANSIENT_BAR_HIDING(3);
        
        public static final int TRANSIENT_BAR_HIDING_VALUE = 3;
        public static final int TRANSIENT_BAR_NONE_VALUE = 0;
        public static final int TRANSIENT_BAR_SHOWING_VALUE = 2;
        public static final int TRANSIENT_BAR_SHOW_REQUESTED_VALUE = 1;
        private static final Internal.EnumLiteMap<TransientWindowState> internalValueMap = new Internal.EnumLiteMap<TransientWindowState>() {
            /* class android.app.StatusBarManagerProto.TransientWindowState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TransientWindowState findValueByNumber(int number) {
                return TransientWindowState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TransientWindowState valueOf(int value2) {
            return forNumber(value2);
        }

        public static TransientWindowState forNumber(int value2) {
            if (value2 == 0) {
                return TRANSIENT_BAR_NONE;
            }
            if (value2 == 1) {
                return TRANSIENT_BAR_SHOW_REQUESTED;
            }
            if (value2 == 2) {
                return TRANSIENT_BAR_SHOWING;
            }
            if (value2 != 3) {
                return null;
            }
            return TRANSIENT_BAR_HIDING;
        }

        public static Internal.EnumLiteMap<TransientWindowState> internalGetValueMap() {
            return internalValueMap;
        }

        private TransientWindowState(int value2) {
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

    public static StatusBarManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StatusBarManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StatusBarManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StatusBarManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StatusBarManagerProto parseFrom(InputStream input) throws IOException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StatusBarManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StatusBarManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (StatusBarManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StatusBarManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatusBarManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StatusBarManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StatusBarManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatusBarManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StatusBarManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StatusBarManagerProto, Builder> implements StatusBarManagerProtoOrBuilder {
        private Builder() {
            super(StatusBarManagerProto.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new StatusBarManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                StatusBarManagerProto statusBarManagerProto = (StatusBarManagerProto) arg1;
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
                    synchronized (StatusBarManagerProto.class) {
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

    public static StatusBarManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StatusBarManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
