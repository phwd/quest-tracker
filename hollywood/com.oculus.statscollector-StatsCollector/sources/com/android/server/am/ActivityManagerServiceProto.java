package com.android.server.am;

import com.android.server.am.ActivityManagerServiceDumpActivitiesProto;
import com.android.server.am.ActivityManagerServiceDumpBroadcastsProto;
import com.android.server.am.ActivityManagerServiceDumpProcessesProto;
import com.android.server.am.ActivityManagerServiceDumpServicesProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ActivityManagerServiceProto extends GeneratedMessageLite<ActivityManagerServiceProto, Builder> implements ActivityManagerServiceProtoOrBuilder {
    public static final int ACTIVITIES_FIELD_NUMBER = 1;
    public static final int BROADCASTS_FIELD_NUMBER = 2;
    private static final ActivityManagerServiceProto DEFAULT_INSTANCE = new ActivityManagerServiceProto();
    private static volatile Parser<ActivityManagerServiceProto> PARSER = null;
    public static final int PROCESSES_FIELD_NUMBER = 4;
    public static final int SERVICES_FIELD_NUMBER = 3;
    private ActivityManagerServiceDumpActivitiesProto activities_;
    private int bitField0_;
    private ActivityManagerServiceDumpBroadcastsProto broadcasts_;
    private ActivityManagerServiceDumpProcessesProto processes_;
    private ActivityManagerServiceDumpServicesProto services_;

    private ActivityManagerServiceProto() {
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public boolean hasActivities() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public ActivityManagerServiceDumpActivitiesProto getActivities() {
        ActivityManagerServiceDumpActivitiesProto activityManagerServiceDumpActivitiesProto = this.activities_;
        return activityManagerServiceDumpActivitiesProto == null ? ActivityManagerServiceDumpActivitiesProto.getDefaultInstance() : activityManagerServiceDumpActivitiesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivities(ActivityManagerServiceDumpActivitiesProto value) {
        if (value != null) {
            this.activities_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivities(ActivityManagerServiceDumpActivitiesProto.Builder builderForValue) {
        this.activities_ = (ActivityManagerServiceDumpActivitiesProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeActivities(ActivityManagerServiceDumpActivitiesProto value) {
        ActivityManagerServiceDumpActivitiesProto activityManagerServiceDumpActivitiesProto = this.activities_;
        if (activityManagerServiceDumpActivitiesProto == null || activityManagerServiceDumpActivitiesProto == ActivityManagerServiceDumpActivitiesProto.getDefaultInstance()) {
            this.activities_ = value;
        } else {
            this.activities_ = (ActivityManagerServiceDumpActivitiesProto) ((ActivityManagerServiceDumpActivitiesProto.Builder) ActivityManagerServiceDumpActivitiesProto.newBuilder(this.activities_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivities() {
        this.activities_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public boolean hasBroadcasts() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public ActivityManagerServiceDumpBroadcastsProto getBroadcasts() {
        ActivityManagerServiceDumpBroadcastsProto activityManagerServiceDumpBroadcastsProto = this.broadcasts_;
        return activityManagerServiceDumpBroadcastsProto == null ? ActivityManagerServiceDumpBroadcastsProto.getDefaultInstance() : activityManagerServiceDumpBroadcastsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
        if (value != null) {
            this.broadcasts_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcasts(ActivityManagerServiceDumpBroadcastsProto.Builder builderForValue) {
        this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
        ActivityManagerServiceDumpBroadcastsProto activityManagerServiceDumpBroadcastsProto = this.broadcasts_;
        if (activityManagerServiceDumpBroadcastsProto == null || activityManagerServiceDumpBroadcastsProto == ActivityManagerServiceDumpBroadcastsProto.getDefaultInstance()) {
            this.broadcasts_ = value;
        } else {
            this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) ((ActivityManagerServiceDumpBroadcastsProto.Builder) ActivityManagerServiceDumpBroadcastsProto.newBuilder(this.broadcasts_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBroadcasts() {
        this.broadcasts_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public boolean hasServices() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public ActivityManagerServiceDumpServicesProto getServices() {
        ActivityManagerServiceDumpServicesProto activityManagerServiceDumpServicesProto = this.services_;
        return activityManagerServiceDumpServicesProto == null ? ActivityManagerServiceDumpServicesProto.getDefaultInstance() : activityManagerServiceDumpServicesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServices(ActivityManagerServiceDumpServicesProto value) {
        if (value != null) {
            this.services_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setServices(ActivityManagerServiceDumpServicesProto.Builder builderForValue) {
        this.services_ = (ActivityManagerServiceDumpServicesProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeServices(ActivityManagerServiceDumpServicesProto value) {
        ActivityManagerServiceDumpServicesProto activityManagerServiceDumpServicesProto = this.services_;
        if (activityManagerServiceDumpServicesProto == null || activityManagerServiceDumpServicesProto == ActivityManagerServiceDumpServicesProto.getDefaultInstance()) {
            this.services_ = value;
        } else {
            this.services_ = (ActivityManagerServiceDumpServicesProto) ((ActivityManagerServiceDumpServicesProto.Builder) ActivityManagerServiceDumpServicesProto.newBuilder(this.services_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearServices() {
        this.services_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public boolean hasProcesses() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
    public ActivityManagerServiceDumpProcessesProto getProcesses() {
        ActivityManagerServiceDumpProcessesProto activityManagerServiceDumpProcessesProto = this.processes_;
        return activityManagerServiceDumpProcessesProto == null ? ActivityManagerServiceDumpProcessesProto.getDefaultInstance() : activityManagerServiceDumpProcessesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcesses(ActivityManagerServiceDumpProcessesProto value) {
        if (value != null) {
            this.processes_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcesses(ActivityManagerServiceDumpProcessesProto.Builder builderForValue) {
        this.processes_ = (ActivityManagerServiceDumpProcessesProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProcesses(ActivityManagerServiceDumpProcessesProto value) {
        ActivityManagerServiceDumpProcessesProto activityManagerServiceDumpProcessesProto = this.processes_;
        if (activityManagerServiceDumpProcessesProto == null || activityManagerServiceDumpProcessesProto == ActivityManagerServiceDumpProcessesProto.getDefaultInstance()) {
            this.processes_ = value;
        } else {
            this.processes_ = (ActivityManagerServiceDumpProcessesProto) ((ActivityManagerServiceDumpProcessesProto.Builder) ActivityManagerServiceDumpProcessesProto.newBuilder(this.processes_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcesses() {
        this.processes_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getActivities());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getBroadcasts());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getServices());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getProcesses());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getActivities());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getBroadcasts());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getServices());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getProcesses());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityManagerServiceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceProto parseFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityManagerServiceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityManagerServiceProto, Builder> implements ActivityManagerServiceProtoOrBuilder {
        private Builder() {
            super(ActivityManagerServiceProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public boolean hasActivities() {
            return ((ActivityManagerServiceProto) this.instance).hasActivities();
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public ActivityManagerServiceDumpActivitiesProto getActivities() {
            return ((ActivityManagerServiceProto) this.instance).getActivities();
        }

        public Builder setActivities(ActivityManagerServiceDumpActivitiesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setActivities((ActivityManagerServiceProto) value);
            return this;
        }

        public Builder setActivities(ActivityManagerServiceDumpActivitiesProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setActivities((ActivityManagerServiceProto) builderForValue);
            return this;
        }

        public Builder mergeActivities(ActivityManagerServiceDumpActivitiesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).mergeActivities(value);
            return this;
        }

        public Builder clearActivities() {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).clearActivities();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public boolean hasBroadcasts() {
            return ((ActivityManagerServiceProto) this.instance).hasBroadcasts();
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public ActivityManagerServiceDumpBroadcastsProto getBroadcasts() {
            return ((ActivityManagerServiceProto) this.instance).getBroadcasts();
        }

        public Builder setBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setBroadcasts((ActivityManagerServiceProto) value);
            return this;
        }

        public Builder setBroadcasts(ActivityManagerServiceDumpBroadcastsProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setBroadcasts((ActivityManagerServiceProto) builderForValue);
            return this;
        }

        public Builder mergeBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).mergeBroadcasts(value);
            return this;
        }

        public Builder clearBroadcasts() {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).clearBroadcasts();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public boolean hasServices() {
            return ((ActivityManagerServiceProto) this.instance).hasServices();
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public ActivityManagerServiceDumpServicesProto getServices() {
            return ((ActivityManagerServiceProto) this.instance).getServices();
        }

        public Builder setServices(ActivityManagerServiceDumpServicesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setServices((ActivityManagerServiceProto) value);
            return this;
        }

        public Builder setServices(ActivityManagerServiceDumpServicesProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setServices((ActivityManagerServiceProto) builderForValue);
            return this;
        }

        public Builder mergeServices(ActivityManagerServiceDumpServicesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).mergeServices(value);
            return this;
        }

        public Builder clearServices() {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).clearServices();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public boolean hasProcesses() {
            return ((ActivityManagerServiceProto) this.instance).hasProcesses();
        }

        @Override // com.android.server.am.ActivityManagerServiceProtoOrBuilder
        public ActivityManagerServiceDumpProcessesProto getProcesses() {
            return ((ActivityManagerServiceProto) this.instance).getProcesses();
        }

        public Builder setProcesses(ActivityManagerServiceDumpProcessesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setProcesses((ActivityManagerServiceProto) value);
            return this;
        }

        public Builder setProcesses(ActivityManagerServiceDumpProcessesProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).setProcesses((ActivityManagerServiceProto) builderForValue);
            return this;
        }

        public Builder mergeProcesses(ActivityManagerServiceDumpProcessesProto value) {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).mergeProcesses(value);
            return this;
        }

        public Builder clearProcesses() {
            copyOnWrite();
            ((ActivityManagerServiceProto) this.instance).clearProcesses();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityManagerServiceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityManagerServiceProto other = (ActivityManagerServiceProto) arg1;
                this.activities_ = (ActivityManagerServiceDumpActivitiesProto) visitor.visitMessage(this.activities_, other.activities_);
                this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) visitor.visitMessage(this.broadcasts_, other.broadcasts_);
                this.services_ = (ActivityManagerServiceDumpServicesProto) visitor.visitMessage(this.services_, other.services_);
                this.processes_ = (ActivityManagerServiceDumpProcessesProto) visitor.visitMessage(this.processes_, other.processes_);
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
                            ActivityManagerServiceDumpActivitiesProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ActivityManagerServiceDumpActivitiesProto.Builder) this.activities_.toBuilder();
                            }
                            this.activities_ = (ActivityManagerServiceDumpActivitiesProto) input.readMessage(ActivityManagerServiceDumpActivitiesProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.activities_);
                                this.activities_ = (ActivityManagerServiceDumpActivitiesProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            ActivityManagerServiceDumpBroadcastsProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (ActivityManagerServiceDumpBroadcastsProto.Builder) this.broadcasts_.toBuilder();
                            }
                            this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) input.readMessage(ActivityManagerServiceDumpBroadcastsProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.broadcasts_);
                                this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            ActivityManagerServiceDumpServicesProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (ActivityManagerServiceDumpServicesProto.Builder) this.services_.toBuilder();
                            }
                            this.services_ = (ActivityManagerServiceDumpServicesProto) input.readMessage(ActivityManagerServiceDumpServicesProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.services_);
                                this.services_ = (ActivityManagerServiceDumpServicesProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            ActivityManagerServiceDumpProcessesProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder4 = (ActivityManagerServiceDumpProcessesProto.Builder) this.processes_.toBuilder();
                            }
                            this.processes_ = (ActivityManagerServiceDumpProcessesProto) input.readMessage(ActivityManagerServiceDumpProcessesProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.processes_);
                                this.processes_ = (ActivityManagerServiceDumpProcessesProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 8;
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
                    synchronized (ActivityManagerServiceProto.class) {
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

    public static ActivityManagerServiceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityManagerServiceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
