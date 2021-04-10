package android.app;

import android.app.PendingIntentProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AlarmClockInfoProto extends GeneratedMessageLite<AlarmClockInfoProto, Builder> implements AlarmClockInfoProtoOrBuilder {
    private static final AlarmClockInfoProto DEFAULT_INSTANCE = new AlarmClockInfoProto();
    private static volatile Parser<AlarmClockInfoProto> PARSER = null;
    public static final int SHOW_INTENT_FIELD_NUMBER = 2;
    public static final int TRIGGER_TIME_MS_FIELD_NUMBER = 1;
    private int bitField0_;
    private PendingIntentProto showIntent_;
    private long triggerTimeMs_ = 0;

    private AlarmClockInfoProto() {
    }

    @Override // android.app.AlarmClockInfoProtoOrBuilder
    public boolean hasTriggerTimeMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.AlarmClockInfoProtoOrBuilder
    public long getTriggerTimeMs() {
        return this.triggerTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTriggerTimeMs(long value) {
        this.bitField0_ |= 1;
        this.triggerTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTriggerTimeMs() {
        this.bitField0_ &= -2;
        this.triggerTimeMs_ = 0;
    }

    @Override // android.app.AlarmClockInfoProtoOrBuilder
    public boolean hasShowIntent() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.app.AlarmClockInfoProtoOrBuilder
    public PendingIntentProto getShowIntent() {
        PendingIntentProto pendingIntentProto = this.showIntent_;
        return pendingIntentProto == null ? PendingIntentProto.getDefaultInstance() : pendingIntentProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowIntent(PendingIntentProto value) {
        if (value != null) {
            this.showIntent_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowIntent(PendingIntentProto.Builder builderForValue) {
        this.showIntent_ = (PendingIntentProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeShowIntent(PendingIntentProto value) {
        PendingIntentProto pendingIntentProto = this.showIntent_;
        if (pendingIntentProto == null || pendingIntentProto == PendingIntentProto.getDefaultInstance()) {
            this.showIntent_ = value;
        } else {
            this.showIntent_ = (PendingIntentProto) ((PendingIntentProto.Builder) PendingIntentProto.newBuilder(this.showIntent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShowIntent() {
        this.showIntent_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.triggerTimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getShowIntent());
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.triggerTimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getShowIntent());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AlarmClockInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmClockInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmClockInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmClockInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmClockInfoProto parseFrom(InputStream input) throws IOException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmClockInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmClockInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AlarmClockInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmClockInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmClockInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmClockInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmClockInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmClockInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AlarmClockInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AlarmClockInfoProto, Builder> implements AlarmClockInfoProtoOrBuilder {
        private Builder() {
            super(AlarmClockInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.AlarmClockInfoProtoOrBuilder
        public boolean hasTriggerTimeMs() {
            return ((AlarmClockInfoProto) this.instance).hasTriggerTimeMs();
        }

        @Override // android.app.AlarmClockInfoProtoOrBuilder
        public long getTriggerTimeMs() {
            return ((AlarmClockInfoProto) this.instance).getTriggerTimeMs();
        }

        public Builder setTriggerTimeMs(long value) {
            copyOnWrite();
            ((AlarmClockInfoProto) this.instance).setTriggerTimeMs(value);
            return this;
        }

        public Builder clearTriggerTimeMs() {
            copyOnWrite();
            ((AlarmClockInfoProto) this.instance).clearTriggerTimeMs();
            return this;
        }

        @Override // android.app.AlarmClockInfoProtoOrBuilder
        public boolean hasShowIntent() {
            return ((AlarmClockInfoProto) this.instance).hasShowIntent();
        }

        @Override // android.app.AlarmClockInfoProtoOrBuilder
        public PendingIntentProto getShowIntent() {
            return ((AlarmClockInfoProto) this.instance).getShowIntent();
        }

        public Builder setShowIntent(PendingIntentProto value) {
            copyOnWrite();
            ((AlarmClockInfoProto) this.instance).setShowIntent((AlarmClockInfoProto) value);
            return this;
        }

        public Builder setShowIntent(PendingIntentProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmClockInfoProto) this.instance).setShowIntent((AlarmClockInfoProto) builderForValue);
            return this;
        }

        public Builder mergeShowIntent(PendingIntentProto value) {
            copyOnWrite();
            ((AlarmClockInfoProto) this.instance).mergeShowIntent(value);
            return this;
        }

        public Builder clearShowIntent() {
            copyOnWrite();
            ((AlarmClockInfoProto) this.instance).clearShowIntent();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AlarmClockInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AlarmClockInfoProto other = (AlarmClockInfoProto) arg1;
                this.triggerTimeMs_ = visitor.visitLong(hasTriggerTimeMs(), this.triggerTimeMs_, other.hasTriggerTimeMs(), other.triggerTimeMs_);
                this.showIntent_ = (PendingIntentProto) visitor.visitMessage(this.showIntent_, other.showIntent_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.triggerTimeMs_ = input.readInt64();
                        } else if (tag == 18) {
                            PendingIntentProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (PendingIntentProto.Builder) this.showIntent_.toBuilder();
                            }
                            this.showIntent_ = (PendingIntentProto) input.readMessage(PendingIntentProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.showIntent_);
                                this.showIntent_ = (PendingIntentProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
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
                    synchronized (AlarmClockInfoProto.class) {
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

    public static AlarmClockInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AlarmClockInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
