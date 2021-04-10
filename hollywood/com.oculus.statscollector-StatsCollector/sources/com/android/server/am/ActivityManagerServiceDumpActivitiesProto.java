package com.android.server.am;

import com.android.server.am.ActivityStackSupervisorProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ActivityManagerServiceDumpActivitiesProto extends GeneratedMessageLite<ActivityManagerServiceDumpActivitiesProto, Builder> implements ActivityManagerServiceDumpActivitiesProtoOrBuilder {
    public static final int ACTIVITY_STACK_SUPERVISOR_FIELD_NUMBER = 1;
    private static final ActivityManagerServiceDumpActivitiesProto DEFAULT_INSTANCE = new ActivityManagerServiceDumpActivitiesProto();
    private static volatile Parser<ActivityManagerServiceDumpActivitiesProto> PARSER;
    private ActivityStackSupervisorProto activityStackSupervisor_;
    private int bitField0_;

    private ActivityManagerServiceDumpActivitiesProto() {
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpActivitiesProtoOrBuilder
    public boolean hasActivityStackSupervisor() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpActivitiesProtoOrBuilder
    public ActivityStackSupervisorProto getActivityStackSupervisor() {
        ActivityStackSupervisorProto activityStackSupervisorProto = this.activityStackSupervisor_;
        return activityStackSupervisorProto == null ? ActivityStackSupervisorProto.getDefaultInstance() : activityStackSupervisorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivityStackSupervisor(ActivityStackSupervisorProto value) {
        if (value != null) {
            this.activityStackSupervisor_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivityStackSupervisor(ActivityStackSupervisorProto.Builder builderForValue) {
        this.activityStackSupervisor_ = (ActivityStackSupervisorProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeActivityStackSupervisor(ActivityStackSupervisorProto value) {
        ActivityStackSupervisorProto activityStackSupervisorProto = this.activityStackSupervisor_;
        if (activityStackSupervisorProto == null || activityStackSupervisorProto == ActivityStackSupervisorProto.getDefaultInstance()) {
            this.activityStackSupervisor_ = value;
        } else {
            this.activityStackSupervisor_ = (ActivityStackSupervisorProto) ((ActivityStackSupervisorProto.Builder) ActivityStackSupervisorProto.newBuilder(this.activityStackSupervisor_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivityStackSupervisor() {
        this.activityStackSupervisor_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getActivityStackSupervisor());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getActivityStackSupervisor());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpActivitiesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpActivitiesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpActivitiesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpActivitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityManagerServiceDumpActivitiesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityManagerServiceDumpActivitiesProto, Builder> implements ActivityManagerServiceDumpActivitiesProtoOrBuilder {
        private Builder() {
            super(ActivityManagerServiceDumpActivitiesProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpActivitiesProtoOrBuilder
        public boolean hasActivityStackSupervisor() {
            return ((ActivityManagerServiceDumpActivitiesProto) this.instance).hasActivityStackSupervisor();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpActivitiesProtoOrBuilder
        public ActivityStackSupervisorProto getActivityStackSupervisor() {
            return ((ActivityManagerServiceDumpActivitiesProto) this.instance).getActivityStackSupervisor();
        }

        public Builder setActivityStackSupervisor(ActivityStackSupervisorProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpActivitiesProto) this.instance).setActivityStackSupervisor((ActivityManagerServiceDumpActivitiesProto) value);
            return this;
        }

        public Builder setActivityStackSupervisor(ActivityStackSupervisorProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpActivitiesProto) this.instance).setActivityStackSupervisor((ActivityManagerServiceDumpActivitiesProto) builderForValue);
            return this;
        }

        public Builder mergeActivityStackSupervisor(ActivityStackSupervisorProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpActivitiesProto) this.instance).mergeActivityStackSupervisor(value);
            return this;
        }

        public Builder clearActivityStackSupervisor() {
            copyOnWrite();
            ((ActivityManagerServiceDumpActivitiesProto) this.instance).clearActivityStackSupervisor();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityManagerServiceDumpActivitiesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityManagerServiceDumpActivitiesProto other = (ActivityManagerServiceDumpActivitiesProto) arg1;
                this.activityStackSupervisor_ = (ActivityStackSupervisorProto) visitor.visitMessage(this.activityStackSupervisor_, other.activityStackSupervisor_);
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
                        } else if (tag == 10) {
                            ActivityStackSupervisorProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ActivityStackSupervisorProto.Builder) this.activityStackSupervisor_.toBuilder();
                            }
                            this.activityStackSupervisor_ = (ActivityStackSupervisorProto) input.readMessage(ActivityStackSupervisorProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.activityStackSupervisor_);
                                this.activityStackSupervisor_ = (ActivityStackSupervisorProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (ActivityManagerServiceDumpActivitiesProto.class) {
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

    public static ActivityManagerServiceDumpActivitiesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityManagerServiceDumpActivitiesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
