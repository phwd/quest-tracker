package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ProcessRecordProto extends GeneratedMessageLite<ProcessRecordProto, Builder> implements ProcessRecordProtoOrBuilder {
    public static final int APP_ID_FIELD_NUMBER = 5;
    private static final ProcessRecordProto DEFAULT_INSTANCE = new ProcessRecordProto();
    public static final int ISOLATED_APP_ID_FIELD_NUMBER = 6;
    public static final int LRU_INDEX_FIELD_NUMBER = 8;
    private static volatile Parser<ProcessRecordProto> PARSER = null;
    public static final int PERSISTENT_FIELD_NUMBER = 7;
    public static final int PID_FIELD_NUMBER = 1;
    public static final int PROCESS_NAME_FIELD_NUMBER = 2;
    public static final int UID_FIELD_NUMBER = 3;
    public static final int USER_ID_FIELD_NUMBER = 4;
    private int appId_ = 0;
    private int bitField0_;
    private int isolatedAppId_ = 0;
    private int lruIndex_ = 0;
    private boolean persistent_ = false;
    private int pid_ = 0;
    private String processName_ = "";
    private int uid_ = 0;
    private int userId_ = 0;

    private ProcessRecordProto() {
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasPid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public int getPid() {
        return this.pid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPid(int value) {
        this.bitField0_ |= 1;
        this.pid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPid() {
        this.bitField0_ &= -2;
        this.pid_ = 0;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasProcessName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public String getProcessName() {
        return this.processName_;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public ByteString getProcessNameBytes() {
        return ByteString.copyFromUtf8(this.processName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.processName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessName() {
        this.bitField0_ &= -3;
        this.processName_ = getDefaultInstance().getProcessName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.processName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 4;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -5;
        this.uid_ = 0;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 8;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -9;
        this.userId_ = 0;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasAppId() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public int getAppId() {
        return this.appId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppId(int value) {
        this.bitField0_ |= 16;
        this.appId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppId() {
        this.bitField0_ &= -17;
        this.appId_ = 0;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasIsolatedAppId() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public int getIsolatedAppId() {
        return this.isolatedAppId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsolatedAppId(int value) {
        this.bitField0_ |= 32;
        this.isolatedAppId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsolatedAppId() {
        this.bitField0_ &= -33;
        this.isolatedAppId_ = 0;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasPersistent() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean getPersistent() {
        return this.persistent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPersistent(boolean value) {
        this.bitField0_ |= 64;
        this.persistent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPersistent() {
        this.bitField0_ &= -65;
        this.persistent_ = false;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public boolean hasLruIndex() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.am.ProcessRecordProtoOrBuilder
    public int getLruIndex() {
        return this.lruIndex_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLruIndex(int value) {
        this.bitField0_ |= 128;
        this.lruIndex_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLruIndex() {
        this.bitField0_ &= -129;
        this.lruIndex_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.pid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getProcessName());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.uid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.userId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.appId_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.isolatedAppId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.persistent_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.lruIndex_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.pid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getProcessName());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.uid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.userId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.appId_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.isolatedAppId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.persistent_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.lruIndex_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessRecordProto parseFrom(InputStream input) throws IOException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessRecordProto, Builder> implements ProcessRecordProtoOrBuilder {
        private Builder() {
            super(ProcessRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasPid() {
            return ((ProcessRecordProto) this.instance).hasPid();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public int getPid() {
            return ((ProcessRecordProto) this.instance).getPid();
        }

        public Builder setPid(int value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setPid(value);
            return this;
        }

        public Builder clearPid() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearPid();
            return this;
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasProcessName() {
            return ((ProcessRecordProto) this.instance).hasProcessName();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public String getProcessName() {
            return ((ProcessRecordProto) this.instance).getProcessName();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public ByteString getProcessNameBytes() {
            return ((ProcessRecordProto) this.instance).getProcessNameBytes();
        }

        public Builder setProcessName(String value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setProcessName(value);
            return this;
        }

        public Builder clearProcessName() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearProcessName();
            return this;
        }

        public Builder setProcessNameBytes(ByteString value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setProcessNameBytes(value);
            return this;
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasUid() {
            return ((ProcessRecordProto) this.instance).hasUid();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public int getUid() {
            return ((ProcessRecordProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearUid();
            return this;
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasUserId() {
            return ((ProcessRecordProto) this.instance).hasUserId();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public int getUserId() {
            return ((ProcessRecordProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearUserId();
            return this;
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasAppId() {
            return ((ProcessRecordProto) this.instance).hasAppId();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public int getAppId() {
            return ((ProcessRecordProto) this.instance).getAppId();
        }

        public Builder setAppId(int value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setAppId(value);
            return this;
        }

        public Builder clearAppId() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearAppId();
            return this;
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasIsolatedAppId() {
            return ((ProcessRecordProto) this.instance).hasIsolatedAppId();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public int getIsolatedAppId() {
            return ((ProcessRecordProto) this.instance).getIsolatedAppId();
        }

        public Builder setIsolatedAppId(int value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setIsolatedAppId(value);
            return this;
        }

        public Builder clearIsolatedAppId() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearIsolatedAppId();
            return this;
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasPersistent() {
            return ((ProcessRecordProto) this.instance).hasPersistent();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean getPersistent() {
            return ((ProcessRecordProto) this.instance).getPersistent();
        }

        public Builder setPersistent(boolean value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setPersistent(value);
            return this;
        }

        public Builder clearPersistent() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearPersistent();
            return this;
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public boolean hasLruIndex() {
            return ((ProcessRecordProto) this.instance).hasLruIndex();
        }

        @Override // com.android.server.am.ProcessRecordProtoOrBuilder
        public int getLruIndex() {
            return ((ProcessRecordProto) this.instance).getLruIndex();
        }

        public Builder setLruIndex(int value) {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).setLruIndex(value);
            return this;
        }

        public Builder clearLruIndex() {
            copyOnWrite();
            ((ProcessRecordProto) this.instance).clearLruIndex();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessRecordProto other = (ProcessRecordProto) arg1;
                this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                this.processName_ = visitor.visitString(hasProcessName(), this.processName_, other.hasProcessName(), other.processName_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.appId_ = visitor.visitInt(hasAppId(), this.appId_, other.hasAppId(), other.appId_);
                this.isolatedAppId_ = visitor.visitInt(hasIsolatedAppId(), this.isolatedAppId_, other.hasIsolatedAppId(), other.isolatedAppId_);
                this.persistent_ = visitor.visitBoolean(hasPersistent(), this.persistent_, other.hasPersistent(), other.persistent_);
                this.lruIndex_ = visitor.visitInt(hasLruIndex(), this.lruIndex_, other.hasLruIndex(), other.lruIndex_);
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
                            this.pid_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.processName_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.uid_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.userId_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.appId_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.isolatedAppId_ = input.readInt32();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.persistent_ = input.readBool();
                        } else if (tag == 64) {
                            this.bitField0_ |= 128;
                            this.lruIndex_ = input.readInt32();
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
                    synchronized (ProcessRecordProto.class) {
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

    public static ProcessRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
