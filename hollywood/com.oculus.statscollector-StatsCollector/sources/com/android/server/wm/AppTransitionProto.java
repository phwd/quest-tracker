package com.android.server.wm;

import android.view.TransitionTypeEnum;
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

public final class AppTransitionProto extends GeneratedMessageLite<AppTransitionProto, Builder> implements AppTransitionProtoOrBuilder {
    public static final int APP_TRANSITION_STATE_FIELD_NUMBER = 1;
    private static final AppTransitionProto DEFAULT_INSTANCE = new AppTransitionProto();
    public static final int LAST_USED_APP_TRANSITION_FIELD_NUMBER = 2;
    private static volatile Parser<AppTransitionProto> PARSER;
    private int appTransitionState_ = 0;
    private int bitField0_;
    private int lastUsedAppTransition_ = 0;

    private AppTransitionProto() {
    }

    public enum AppState implements Internal.EnumLite {
        APP_STATE_IDLE(0),
        APP_STATE_READY(1),
        APP_STATE_RUNNING(2),
        APP_STATE_TIMEOUT(3);
        
        public static final int APP_STATE_IDLE_VALUE = 0;
        public static final int APP_STATE_READY_VALUE = 1;
        public static final int APP_STATE_RUNNING_VALUE = 2;
        public static final int APP_STATE_TIMEOUT_VALUE = 3;
        private static final Internal.EnumLiteMap<AppState> internalValueMap = new Internal.EnumLiteMap<AppState>() {
            /* class com.android.server.wm.AppTransitionProto.AppState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AppState findValueByNumber(int number) {
                return AppState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static AppState valueOf(int value2) {
            return forNumber(value2);
        }

        public static AppState forNumber(int value2) {
            if (value2 == 0) {
                return APP_STATE_IDLE;
            }
            if (value2 == 1) {
                return APP_STATE_READY;
            }
            if (value2 == 2) {
                return APP_STATE_RUNNING;
            }
            if (value2 != 3) {
                return null;
            }
            return APP_STATE_TIMEOUT;
        }

        public static Internal.EnumLiteMap<AppState> internalGetValueMap() {
            return internalValueMap;
        }

        private AppState(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.wm.AppTransitionProtoOrBuilder
    public boolean hasAppTransitionState() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.AppTransitionProtoOrBuilder
    public AppState getAppTransitionState() {
        AppState result = AppState.forNumber(this.appTransitionState_);
        return result == null ? AppState.APP_STATE_IDLE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppTransitionState(AppState value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.appTransitionState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppTransitionState() {
        this.bitField0_ &= -2;
        this.appTransitionState_ = 0;
    }

    @Override // com.android.server.wm.AppTransitionProtoOrBuilder
    public boolean hasLastUsedAppTransition() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.AppTransitionProtoOrBuilder
    public TransitionTypeEnum getLastUsedAppTransition() {
        TransitionTypeEnum result = TransitionTypeEnum.forNumber(this.lastUsedAppTransition_);
        return result == null ? TransitionTypeEnum.TRANSIT_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastUsedAppTransition(TransitionTypeEnum value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.lastUsedAppTransition_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastUsedAppTransition() {
        this.bitField0_ &= -3;
        this.lastUsedAppTransition_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.appTransitionState_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.lastUsedAppTransition_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.appTransitionState_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.lastUsedAppTransition_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AppTransitionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppTransitionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppTransitionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppTransitionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppTransitionProto parseFrom(InputStream input) throws IOException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppTransitionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppTransitionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AppTransitionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AppTransitionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppTransitionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppTransitionProto parseFrom(CodedInputStream input) throws IOException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppTransitionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppTransitionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AppTransitionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AppTransitionProto, Builder> implements AppTransitionProtoOrBuilder {
        private Builder() {
            super(AppTransitionProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.AppTransitionProtoOrBuilder
        public boolean hasAppTransitionState() {
            return ((AppTransitionProto) this.instance).hasAppTransitionState();
        }

        @Override // com.android.server.wm.AppTransitionProtoOrBuilder
        public AppState getAppTransitionState() {
            return ((AppTransitionProto) this.instance).getAppTransitionState();
        }

        public Builder setAppTransitionState(AppState value) {
            copyOnWrite();
            ((AppTransitionProto) this.instance).setAppTransitionState(value);
            return this;
        }

        public Builder clearAppTransitionState() {
            copyOnWrite();
            ((AppTransitionProto) this.instance).clearAppTransitionState();
            return this;
        }

        @Override // com.android.server.wm.AppTransitionProtoOrBuilder
        public boolean hasLastUsedAppTransition() {
            return ((AppTransitionProto) this.instance).hasLastUsedAppTransition();
        }

        @Override // com.android.server.wm.AppTransitionProtoOrBuilder
        public TransitionTypeEnum getLastUsedAppTransition() {
            return ((AppTransitionProto) this.instance).getLastUsedAppTransition();
        }

        public Builder setLastUsedAppTransition(TransitionTypeEnum value) {
            copyOnWrite();
            ((AppTransitionProto) this.instance).setLastUsedAppTransition(value);
            return this;
        }

        public Builder clearLastUsedAppTransition() {
            copyOnWrite();
            ((AppTransitionProto) this.instance).clearLastUsedAppTransition();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AppTransitionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AppTransitionProto other = (AppTransitionProto) arg1;
                this.appTransitionState_ = visitor.visitInt(hasAppTransitionState(), this.appTransitionState_, other.hasAppTransitionState(), other.appTransitionState_);
                this.lastUsedAppTransition_ = visitor.visitInt(hasLastUsedAppTransition(), this.lastUsedAppTransition_, other.hasLastUsedAppTransition(), other.lastUsedAppTransition_);
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
                            if (AppState.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.appTransitionState_ = rawValue;
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            if (TransitionTypeEnum.forNumber(rawValue2) == null) {
                                super.mergeVarintField(2, rawValue2);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.lastUsedAppTransition_ = rawValue2;
                            }
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
                    synchronized (AppTransitionProto.class) {
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

    public static AppTransitionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AppTransitionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
