package com.android.server.am;

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

public final class UserStateProto extends GeneratedMessageLite<UserStateProto, Builder> implements UserStateProtoOrBuilder {
    private static final UserStateProto DEFAULT_INSTANCE = new UserStateProto();
    private static volatile Parser<UserStateProto> PARSER = null;
    public static final int STATE_FIELD_NUMBER = 1;
    public static final int SWITCHING_FIELD_NUMBER = 2;
    private int bitField0_;
    private int state_ = 0;
    private boolean switching_ = false;

    private UserStateProto() {
    }

    public enum State implements Internal.EnumLite {
        STATE_BOOTING(0),
        STATE_RUNNING_LOCKED(1),
        STATE_RUNNING_UNLOCKING(2),
        STATE_RUNNING_UNLOCKED(3),
        STATE_STOPPING(4),
        STATE_SHUTDOWN(5);
        
        public static final int STATE_BOOTING_VALUE = 0;
        public static final int STATE_RUNNING_LOCKED_VALUE = 1;
        public static final int STATE_RUNNING_UNLOCKED_VALUE = 3;
        public static final int STATE_RUNNING_UNLOCKING_VALUE = 2;
        public static final int STATE_SHUTDOWN_VALUE = 5;
        public static final int STATE_STOPPING_VALUE = 4;
        private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() {
            /* class com.android.server.am.UserStateProto.State.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public State findValueByNumber(int number) {
                return State.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static State valueOf(int value2) {
            return forNumber(value2);
        }

        public static State forNumber(int value2) {
            if (value2 == 0) {
                return STATE_BOOTING;
            }
            if (value2 == 1) {
                return STATE_RUNNING_LOCKED;
            }
            if (value2 == 2) {
                return STATE_RUNNING_UNLOCKING;
            }
            if (value2 == 3) {
                return STATE_RUNNING_UNLOCKED;
            }
            if (value2 == 4) {
                return STATE_STOPPING;
            }
            if (value2 != 5) {
                return null;
            }
            return STATE_SHUTDOWN;
        }

        public static Internal.EnumLiteMap<State> internalGetValueMap() {
            return internalValueMap;
        }

        private State(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.am.UserStateProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.UserStateProtoOrBuilder
    public State getState() {
        State result = State.forNumber(this.state_);
        return result == null ? State.STATE_BOOTING : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(State value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.state_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -2;
        this.state_ = 0;
    }

    @Override // com.android.server.am.UserStateProtoOrBuilder
    public boolean hasSwitching() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.UserStateProtoOrBuilder
    public boolean getSwitching() {
        return this.switching_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSwitching(boolean value) {
        this.bitField0_ |= 2;
        this.switching_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSwitching() {
        this.bitField0_ &= -3;
        this.switching_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.state_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.switching_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.state_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.switching_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UserStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UserStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UserStateProto parseFrom(InputStream input) throws IOException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserStateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UserStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UserStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UserStateProto parseFrom(CodedInputStream input) throws IOException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UserStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UserStateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UserStateProto, Builder> implements UserStateProtoOrBuilder {
        private Builder() {
            super(UserStateProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.UserStateProtoOrBuilder
        public boolean hasState() {
            return ((UserStateProto) this.instance).hasState();
        }

        @Override // com.android.server.am.UserStateProtoOrBuilder
        public State getState() {
            return ((UserStateProto) this.instance).getState();
        }

        public Builder setState(State value) {
            copyOnWrite();
            ((UserStateProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((UserStateProto) this.instance).clearState();
            return this;
        }

        @Override // com.android.server.am.UserStateProtoOrBuilder
        public boolean hasSwitching() {
            return ((UserStateProto) this.instance).hasSwitching();
        }

        @Override // com.android.server.am.UserStateProtoOrBuilder
        public boolean getSwitching() {
            return ((UserStateProto) this.instance).getSwitching();
        }

        public Builder setSwitching(boolean value) {
            copyOnWrite();
            ((UserStateProto) this.instance).setSwitching(value);
            return this;
        }

        public Builder clearSwitching() {
            copyOnWrite();
            ((UserStateProto) this.instance).clearSwitching();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UserStateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UserStateProto other = (UserStateProto) arg1;
                this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                this.switching_ = visitor.visitBoolean(hasSwitching(), this.switching_, other.hasSwitching(), other.switching_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
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
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            if (State.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.state_ = rawValue;
                            }
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.switching_ = input.readBool();
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
                    synchronized (UserStateProto.class) {
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

    public static UserStateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UserStateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
