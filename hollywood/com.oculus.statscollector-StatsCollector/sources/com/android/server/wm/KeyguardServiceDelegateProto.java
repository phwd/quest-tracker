package com.android.server.wm;

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

public final class KeyguardServiceDelegateProto extends GeneratedMessageLite<KeyguardServiceDelegateProto, Builder> implements KeyguardServiceDelegateProtoOrBuilder {
    private static final KeyguardServiceDelegateProto DEFAULT_INSTANCE = new KeyguardServiceDelegateProto();
    public static final int INTERACTIVE_STATE_FIELD_NUMBER = 5;
    public static final int OCCLUDED_FIELD_NUMBER = 2;
    private static volatile Parser<KeyguardServiceDelegateProto> PARSER = null;
    public static final int SCREEN_STATE_FIELD_NUMBER = 4;
    public static final int SECURE_FIELD_NUMBER = 3;
    public static final int SHOWING_FIELD_NUMBER = 1;
    private int bitField0_;
    private int interactiveState_ = 0;
    private boolean occluded_ = false;
    private int screenState_ = 0;
    private boolean secure_ = false;
    private boolean showing_ = false;

    private KeyguardServiceDelegateProto() {
    }

    public enum ScreenState implements Internal.EnumLite {
        SCREEN_STATE_OFF(0),
        SCREEN_STATE_TURNING_ON(1),
        SCREEN_STATE_ON(2),
        SCREEN_STATE_TURNING_OFF(3);
        
        public static final int SCREEN_STATE_OFF_VALUE = 0;
        public static final int SCREEN_STATE_ON_VALUE = 2;
        public static final int SCREEN_STATE_TURNING_OFF_VALUE = 3;
        public static final int SCREEN_STATE_TURNING_ON_VALUE = 1;
        private static final Internal.EnumLiteMap<ScreenState> internalValueMap = new Internal.EnumLiteMap<ScreenState>() {
            /* class com.android.server.wm.KeyguardServiceDelegateProto.ScreenState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ScreenState findValueByNumber(int number) {
                return ScreenState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ScreenState valueOf(int value2) {
            return forNumber(value2);
        }

        public static ScreenState forNumber(int value2) {
            if (value2 == 0) {
                return SCREEN_STATE_OFF;
            }
            if (value2 == 1) {
                return SCREEN_STATE_TURNING_ON;
            }
            if (value2 == 2) {
                return SCREEN_STATE_ON;
            }
            if (value2 != 3) {
                return null;
            }
            return SCREEN_STATE_TURNING_OFF;
        }

        public static Internal.EnumLiteMap<ScreenState> internalGetValueMap() {
            return internalValueMap;
        }

        private ScreenState(int value2) {
            this.value = value2;
        }
    }

    public enum InteractiveState implements Internal.EnumLite {
        INTERACTIVE_STATE_SLEEP(0),
        INTERACTIVE_STATE_WAKING(1),
        INTERACTIVE_STATE_AWAKE(2),
        INTERACTIVE_STATE_GOING_TO_SLEEP(3);
        
        public static final int INTERACTIVE_STATE_AWAKE_VALUE = 2;
        public static final int INTERACTIVE_STATE_GOING_TO_SLEEP_VALUE = 3;
        public static final int INTERACTIVE_STATE_SLEEP_VALUE = 0;
        public static final int INTERACTIVE_STATE_WAKING_VALUE = 1;
        private static final Internal.EnumLiteMap<InteractiveState> internalValueMap = new Internal.EnumLiteMap<InteractiveState>() {
            /* class com.android.server.wm.KeyguardServiceDelegateProto.InteractiveState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public InteractiveState findValueByNumber(int number) {
                return InteractiveState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static InteractiveState valueOf(int value2) {
            return forNumber(value2);
        }

        public static InteractiveState forNumber(int value2) {
            if (value2 == 0) {
                return INTERACTIVE_STATE_SLEEP;
            }
            if (value2 == 1) {
                return INTERACTIVE_STATE_WAKING;
            }
            if (value2 == 2) {
                return INTERACTIVE_STATE_AWAKE;
            }
            if (value2 != 3) {
                return null;
            }
            return INTERACTIVE_STATE_GOING_TO_SLEEP;
        }

        public static Internal.EnumLiteMap<InteractiveState> internalGetValueMap() {
            return internalValueMap;
        }

        private InteractiveState(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean hasShowing() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean getShowing() {
        return this.showing_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowing(boolean value) {
        this.bitField0_ |= 1;
        this.showing_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShowing() {
        this.bitField0_ &= -2;
        this.showing_ = false;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean hasOccluded() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean getOccluded() {
        return this.occluded_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOccluded(boolean value) {
        this.bitField0_ |= 2;
        this.occluded_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOccluded() {
        this.bitField0_ &= -3;
        this.occluded_ = false;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean hasSecure() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean getSecure() {
        return this.secure_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSecure(boolean value) {
        this.bitField0_ |= 4;
        this.secure_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSecure() {
        this.bitField0_ &= -5;
        this.secure_ = false;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean hasScreenState() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public ScreenState getScreenState() {
        ScreenState result = ScreenState.forNumber(this.screenState_);
        return result == null ? ScreenState.SCREEN_STATE_OFF : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenState(ScreenState value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.screenState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenState() {
        this.bitField0_ &= -9;
        this.screenState_ = 0;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public boolean hasInteractiveState() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
    public InteractiveState getInteractiveState() {
        InteractiveState result = InteractiveState.forNumber(this.interactiveState_);
        return result == null ? InteractiveState.INTERACTIVE_STATE_SLEEP : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInteractiveState(InteractiveState value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.interactiveState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInteractiveState() {
        this.bitField0_ &= -17;
        this.interactiveState_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.showing_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.occluded_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.secure_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.screenState_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeEnum(5, this.interactiveState_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.showing_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.occluded_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.secure_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.screenState_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeEnumSize(5, this.interactiveState_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static KeyguardServiceDelegateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KeyguardServiceDelegateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KeyguardServiceDelegateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KeyguardServiceDelegateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KeyguardServiceDelegateProto parseFrom(InputStream input) throws IOException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardServiceDelegateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KeyguardServiceDelegateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (KeyguardServiceDelegateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardServiceDelegateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardServiceDelegateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KeyguardServiceDelegateProto parseFrom(CodedInputStream input) throws IOException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KeyguardServiceDelegateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KeyguardServiceDelegateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KeyguardServiceDelegateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<KeyguardServiceDelegateProto, Builder> implements KeyguardServiceDelegateProtoOrBuilder {
        private Builder() {
            super(KeyguardServiceDelegateProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean hasShowing() {
            return ((KeyguardServiceDelegateProto) this.instance).hasShowing();
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean getShowing() {
            return ((KeyguardServiceDelegateProto) this.instance).getShowing();
        }

        public Builder setShowing(boolean value) {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).setShowing(value);
            return this;
        }

        public Builder clearShowing() {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).clearShowing();
            return this;
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean hasOccluded() {
            return ((KeyguardServiceDelegateProto) this.instance).hasOccluded();
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean getOccluded() {
            return ((KeyguardServiceDelegateProto) this.instance).getOccluded();
        }

        public Builder setOccluded(boolean value) {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).setOccluded(value);
            return this;
        }

        public Builder clearOccluded() {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).clearOccluded();
            return this;
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean hasSecure() {
            return ((KeyguardServiceDelegateProto) this.instance).hasSecure();
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean getSecure() {
            return ((KeyguardServiceDelegateProto) this.instance).getSecure();
        }

        public Builder setSecure(boolean value) {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).setSecure(value);
            return this;
        }

        public Builder clearSecure() {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).clearSecure();
            return this;
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean hasScreenState() {
            return ((KeyguardServiceDelegateProto) this.instance).hasScreenState();
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public ScreenState getScreenState() {
            return ((KeyguardServiceDelegateProto) this.instance).getScreenState();
        }

        public Builder setScreenState(ScreenState value) {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).setScreenState(value);
            return this;
        }

        public Builder clearScreenState() {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).clearScreenState();
            return this;
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public boolean hasInteractiveState() {
            return ((KeyguardServiceDelegateProto) this.instance).hasInteractiveState();
        }

        @Override // com.android.server.wm.KeyguardServiceDelegateProtoOrBuilder
        public InteractiveState getInteractiveState() {
            return ((KeyguardServiceDelegateProto) this.instance).getInteractiveState();
        }

        public Builder setInteractiveState(InteractiveState value) {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).setInteractiveState(value);
            return this;
        }

        public Builder clearInteractiveState() {
            copyOnWrite();
            ((KeyguardServiceDelegateProto) this.instance).clearInteractiveState();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new KeyguardServiceDelegateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                KeyguardServiceDelegateProto other = (KeyguardServiceDelegateProto) arg1;
                this.showing_ = visitor.visitBoolean(hasShowing(), this.showing_, other.hasShowing(), other.showing_);
                this.occluded_ = visitor.visitBoolean(hasOccluded(), this.occluded_, other.hasOccluded(), other.occluded_);
                this.secure_ = visitor.visitBoolean(hasSecure(), this.secure_, other.hasSecure(), other.secure_);
                this.screenState_ = visitor.visitInt(hasScreenState(), this.screenState_, other.hasScreenState(), other.screenState_);
                this.interactiveState_ = visitor.visitInt(hasInteractiveState(), this.interactiveState_, other.hasInteractiveState(), other.interactiveState_);
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
                            this.bitField0_ |= 1;
                            this.showing_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.occluded_ = input.readBool();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.secure_ = input.readBool();
                        } else if (tag == 32) {
                            int rawValue = input.readEnum();
                            if (ScreenState.forNumber(rawValue) == null) {
                                super.mergeVarintField(4, rawValue);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.screenState_ = rawValue;
                            }
                        } else if (tag == 40) {
                            int rawValue2 = input.readEnum();
                            if (InteractiveState.forNumber(rawValue2) == null) {
                                super.mergeVarintField(5, rawValue2);
                            } else {
                                this.bitField0_ = 16 | this.bitField0_;
                                this.interactiveState_ = rawValue2;
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
                    synchronized (KeyguardServiceDelegateProto.class) {
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

    public static KeyguardServiceDelegateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeyguardServiceDelegateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
