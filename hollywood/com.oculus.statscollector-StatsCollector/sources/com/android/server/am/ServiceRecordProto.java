package com.android.server.am;

import android.app.NotificationProto;
import android.content.IntentProto;
import android.util.Duration;
import com.android.os.AtomsProto;
import com.android.server.am.ConnectionRecordProto;
import com.android.server.am.IntentBindRecordProto;
import com.android.server.am.NeededUriGrantsProto;
import com.android.server.am.ProcessRecordProto;
import com.android.server.am.UriPermissionOwnerProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class ServiceRecordProto extends GeneratedMessageLite<ServiceRecordProto, Builder> implements ServiceRecordProtoOrBuilder {
    public static final int APPINFO_FIELD_NUMBER = 8;
    public static final int APP_FIELD_NUMBER = 9;
    public static final int BINDINGS_FIELD_NUMBER = 25;
    public static final int CONNECTIONS_FIELD_NUMBER = 26;
    public static final int CRASH_FIELD_NUMBER = 22;
    public static final int CREATED_FROM_FG_FIELD_NUMBER = 18;
    public static final int CREATE_REAL_TIME_FIELD_NUMBER = 14;
    private static final ServiceRecordProto DEFAULT_INSTANCE = new ServiceRecordProto();
    public static final int DELAYED_FIELD_NUMBER = 12;
    public static final int DELIVERED_STARTS_FIELD_NUMBER = 23;
    public static final int DESTORY_TIME_FIELD_NUMBER = 21;
    public static final int EXECUTE_FIELD_NUMBER = 20;
    public static final int FOREGROUND_FIELD_NUMBER = 13;
    public static final int INTENT_FIELD_NUMBER = 4;
    public static final int ISOLATED_PROC_FIELD_NUMBER = 10;
    public static final int IS_RUNNING_FIELD_NUMBER = 2;
    public static final int LAST_ACTIVITY_TIME_FIELD_NUMBER = 16;
    public static final int PACKAGE_NAME_FIELD_NUMBER = 5;
    private static volatile Parser<ServiceRecordProto> PARSER = null;
    public static final int PENDING_STARTS_FIELD_NUMBER = 24;
    public static final int PERMISSION_FIELD_NUMBER = 7;
    public static final int PID_FIELD_NUMBER = 3;
    public static final int PROCESS_NAME_FIELD_NUMBER = 6;
    public static final int RESTART_TIME_FIELD_NUMBER = 17;
    public static final int SHORT_NAME_FIELD_NUMBER = 1;
    public static final int STARTING_BG_TIMEOUT_FIELD_NUMBER = 15;
    public static final int START_FIELD_NUMBER = 19;
    public static final int WHITELIST_MANAGER_FIELD_NUMBER = 11;
    private ProcessRecordProto app_;
    private AppInfo appinfo_;
    private Internal.ProtobufList<IntentBindRecordProto> bindings_ = emptyProtobufList();
    private int bitField0_;
    private Internal.ProtobufList<ConnectionRecordProto> connections_ = emptyProtobufList();
    private Crash crash_;
    private Duration createRealTime_;
    private boolean createdFromFg_ = false;
    private boolean delayed_ = false;
    private Internal.ProtobufList<StartItem> deliveredStarts_ = emptyProtobufList();
    private Duration destoryTime_;
    private ExecuteNesting execute_;
    private Foreground foreground_;
    private IntentProto intent_;
    private boolean isRunning_ = false;
    private ProcessRecordProto isolatedProc_;
    private Duration lastActivityTime_;
    private String packageName_ = "";
    private Internal.ProtobufList<StartItem> pendingStarts_ = emptyProtobufList();
    private String permission_ = "";
    private int pid_ = 0;
    private String processName_ = "";
    private Duration restartTime_;
    private String shortName_ = "";
    private Start start_;
    private Duration startingBgTimeout_;
    private boolean whitelistManager_ = false;

    public interface AppInfoOrBuilder extends MessageLiteOrBuilder {
        String getBaseDir();

        ByteString getBaseDirBytes();

        String getDataDir();

        ByteString getDataDirBytes();

        String getResDir();

        ByteString getResDirBytes();

        boolean hasBaseDir();

        boolean hasDataDir();

        boolean hasResDir();
    }

    public interface CrashOrBuilder extends MessageLiteOrBuilder {
        int getCrashCount();

        Duration getNextRestartTime();

        int getRestartCount();

        Duration getRestartDelay();

        boolean hasCrashCount();

        boolean hasNextRestartTime();

        boolean hasRestartCount();

        boolean hasRestartDelay();
    }

    public interface ExecuteNestingOrBuilder extends MessageLiteOrBuilder {
        boolean getExecuteFg();

        int getExecuteNesting();

        Duration getExecutingStart();

        boolean hasExecuteFg();

        boolean hasExecuteNesting();

        boolean hasExecutingStart();
    }

    public interface ForegroundOrBuilder extends MessageLiteOrBuilder {
        int getId();

        NotificationProto getNotification();

        boolean hasId();

        boolean hasNotification();
    }

    public interface StartItemOrBuilder extends MessageLiteOrBuilder {
        int getDeliveryCount();

        int getDoneExecutingCount();

        Duration getDuration();

        int getId();

        IntentProto getIntent();

        NeededUriGrantsProto getNeededGrants();

        UriPermissionOwnerProto getUriPermissions();

        boolean hasDeliveryCount();

        boolean hasDoneExecutingCount();

        boolean hasDuration();

        boolean hasId();

        boolean hasIntent();

        boolean hasNeededGrants();

        boolean hasUriPermissions();
    }

    public interface StartOrBuilder extends MessageLiteOrBuilder {
        boolean getCallStart();

        boolean getDelayedStop();

        int getLastStartId();

        boolean getStartRequested();

        boolean getStopIfKilled();

        boolean hasCallStart();

        boolean hasDelayedStop();

        boolean hasLastStartId();

        boolean hasStartRequested();

        boolean hasStopIfKilled();
    }

    private ServiceRecordProto() {
    }

    public static final class AppInfo extends GeneratedMessageLite<AppInfo, Builder> implements AppInfoOrBuilder {
        public static final int BASE_DIR_FIELD_NUMBER = 1;
        public static final int DATA_DIR_FIELD_NUMBER = 3;
        private static final AppInfo DEFAULT_INSTANCE = new AppInfo();
        private static volatile Parser<AppInfo> PARSER = null;
        public static final int RES_DIR_FIELD_NUMBER = 2;
        private String baseDir_ = "";
        private int bitField0_;
        private String dataDir_ = "";
        private String resDir_ = "";

        private AppInfo() {
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public boolean hasBaseDir() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public String getBaseDir() {
            return this.baseDir_;
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public ByteString getBaseDirBytes() {
            return ByteString.copyFromUtf8(this.baseDir_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBaseDir(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.baseDir_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBaseDir() {
            this.bitField0_ &= -2;
            this.baseDir_ = getDefaultInstance().getBaseDir();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBaseDirBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.baseDir_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public boolean hasResDir() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public String getResDir() {
            return this.resDir_;
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public ByteString getResDirBytes() {
            return ByteString.copyFromUtf8(this.resDir_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setResDir(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.resDir_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearResDir() {
            this.bitField0_ &= -3;
            this.resDir_ = getDefaultInstance().getResDir();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setResDirBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.resDir_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public boolean hasDataDir() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public String getDataDir() {
            return this.dataDir_;
        }

        @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
        public ByteString getDataDirBytes() {
            return ByteString.copyFromUtf8(this.dataDir_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDataDir(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.dataDir_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDataDir() {
            this.bitField0_ &= -5;
            this.dataDir_ = getDefaultInstance().getDataDir();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDataDirBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.dataDir_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getBaseDir());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getResDir());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getDataDir());
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getBaseDir());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getResDir());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getDataDir());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static AppInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AppInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AppInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AppInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AppInfo parseFrom(InputStream input) throws IOException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AppInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AppInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (AppInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AppInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AppInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AppInfo parseFrom(CodedInputStream input) throws IOException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AppInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AppInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AppInfo prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AppInfo, Builder> implements AppInfoOrBuilder {
            private Builder() {
                super(AppInfo.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public boolean hasBaseDir() {
                return ((AppInfo) this.instance).hasBaseDir();
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public String getBaseDir() {
                return ((AppInfo) this.instance).getBaseDir();
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public ByteString getBaseDirBytes() {
                return ((AppInfo) this.instance).getBaseDirBytes();
            }

            public Builder setBaseDir(String value) {
                copyOnWrite();
                ((AppInfo) this.instance).setBaseDir(value);
                return this;
            }

            public Builder clearBaseDir() {
                copyOnWrite();
                ((AppInfo) this.instance).clearBaseDir();
                return this;
            }

            public Builder setBaseDirBytes(ByteString value) {
                copyOnWrite();
                ((AppInfo) this.instance).setBaseDirBytes(value);
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public boolean hasResDir() {
                return ((AppInfo) this.instance).hasResDir();
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public String getResDir() {
                return ((AppInfo) this.instance).getResDir();
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public ByteString getResDirBytes() {
                return ((AppInfo) this.instance).getResDirBytes();
            }

            public Builder setResDir(String value) {
                copyOnWrite();
                ((AppInfo) this.instance).setResDir(value);
                return this;
            }

            public Builder clearResDir() {
                copyOnWrite();
                ((AppInfo) this.instance).clearResDir();
                return this;
            }

            public Builder setResDirBytes(ByteString value) {
                copyOnWrite();
                ((AppInfo) this.instance).setResDirBytes(value);
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public boolean hasDataDir() {
                return ((AppInfo) this.instance).hasDataDir();
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public String getDataDir() {
                return ((AppInfo) this.instance).getDataDir();
            }

            @Override // com.android.server.am.ServiceRecordProto.AppInfoOrBuilder
            public ByteString getDataDirBytes() {
                return ((AppInfo) this.instance).getDataDirBytes();
            }

            public Builder setDataDir(String value) {
                copyOnWrite();
                ((AppInfo) this.instance).setDataDir(value);
                return this;
            }

            public Builder clearDataDir() {
                copyOnWrite();
                ((AppInfo) this.instance).clearDataDir();
                return this;
            }

            public Builder setDataDirBytes(ByteString value) {
                copyOnWrite();
                ((AppInfo) this.instance).setDataDirBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new AppInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    AppInfo other = (AppInfo) arg1;
                    this.baseDir_ = visitor.visitString(hasBaseDir(), this.baseDir_, other.hasBaseDir(), other.baseDir_);
                    this.resDir_ = visitor.visitString(hasResDir(), this.resDir_, other.hasResDir(), other.resDir_);
                    this.dataDir_ = visitor.visitString(hasDataDir(), this.dataDir_, other.hasDataDir(), other.dataDir_);
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
                            } else if (tag == 10) {
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.baseDir_ = s;
                            } else if (tag == 18) {
                                String s2 = input.readString();
                                this.bitField0_ |= 2;
                                this.resDir_ = s2;
                            } else if (tag == 26) {
                                String s3 = input.readString();
                                this.bitField0_ |= 4;
                                this.dataDir_ = s3;
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
                        synchronized (AppInfo.class) {
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

        public static AppInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AppInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Foreground extends GeneratedMessageLite<Foreground, Builder> implements ForegroundOrBuilder {
        private static final Foreground DEFAULT_INSTANCE = new Foreground();
        public static final int ID_FIELD_NUMBER = 1;
        public static final int NOTIFICATION_FIELD_NUMBER = 2;
        private static volatile Parser<Foreground> PARSER;
        private int bitField0_;
        private int id_ = 0;
        private NotificationProto notification_;

        private Foreground() {
        }

        @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
        public int getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(int value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
        public boolean hasNotification() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
        public NotificationProto getNotification() {
            NotificationProto notificationProto = this.notification_;
            return notificationProto == null ? NotificationProto.getDefaultInstance() : notificationProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNotification(NotificationProto value) {
            if (value != null) {
                this.notification_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNotification(NotificationProto.Builder builderForValue) {
            this.notification_ = (NotificationProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeNotification(NotificationProto value) {
            NotificationProto notificationProto = this.notification_;
            if (notificationProto == null || notificationProto == NotificationProto.getDefaultInstance()) {
                this.notification_ = value;
            } else {
                this.notification_ = (NotificationProto) ((NotificationProto.Builder) NotificationProto.newBuilder(this.notification_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNotification() {
            this.notification_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getNotification());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getNotification());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Foreground parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Foreground parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Foreground parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Foreground parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Foreground parseFrom(InputStream input) throws IOException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Foreground parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Foreground parseDelimitedFrom(InputStream input) throws IOException {
            return (Foreground) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Foreground parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Foreground) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Foreground parseFrom(CodedInputStream input) throws IOException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Foreground parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Foreground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Foreground prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Foreground, Builder> implements ForegroundOrBuilder {
            private Builder() {
                super(Foreground.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
            public boolean hasId() {
                return ((Foreground) this.instance).hasId();
            }

            @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
            public int getId() {
                return ((Foreground) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((Foreground) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Foreground) this.instance).clearId();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
            public boolean hasNotification() {
                return ((Foreground) this.instance).hasNotification();
            }

            @Override // com.android.server.am.ServiceRecordProto.ForegroundOrBuilder
            public NotificationProto getNotification() {
                return ((Foreground) this.instance).getNotification();
            }

            public Builder setNotification(NotificationProto value) {
                copyOnWrite();
                ((Foreground) this.instance).setNotification((Foreground) value);
                return this;
            }

            public Builder setNotification(NotificationProto.Builder builderForValue) {
                copyOnWrite();
                ((Foreground) this.instance).setNotification((Foreground) builderForValue);
                return this;
            }

            public Builder mergeNotification(NotificationProto value) {
                copyOnWrite();
                ((Foreground) this.instance).mergeNotification(value);
                return this;
            }

            public Builder clearNotification() {
                copyOnWrite();
                ((Foreground) this.instance).clearNotification();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Foreground();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Foreground other = (Foreground) arg1;
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.notification_ = (NotificationProto) visitor.visitMessage(this.notification_, other.notification_);
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
                                this.id_ = input.readInt32();
                            } else if (tag == 18) {
                                NotificationProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (NotificationProto.Builder) this.notification_.toBuilder();
                                }
                                this.notification_ = (NotificationProto) input.readMessage(NotificationProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.notification_);
                                    this.notification_ = (NotificationProto) subBuilder.buildPartial();
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
                        synchronized (Foreground.class) {
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

        public static Foreground getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Foreground> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Start extends GeneratedMessageLite<Start, Builder> implements StartOrBuilder {
        public static final int CALL_START_FIELD_NUMBER = 4;
        private static final Start DEFAULT_INSTANCE = new Start();
        public static final int DELAYED_STOP_FIELD_NUMBER = 2;
        public static final int LAST_START_ID_FIELD_NUMBER = 5;
        private static volatile Parser<Start> PARSER = null;
        public static final int START_REQUESTED_FIELD_NUMBER = 1;
        public static final int STOP_IF_KILLED_FIELD_NUMBER = 3;
        private int bitField0_;
        private boolean callStart_ = false;
        private boolean delayedStop_ = false;
        private int lastStartId_ = 0;
        private boolean startRequested_ = false;
        private boolean stopIfKilled_ = false;

        private Start() {
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean hasStartRequested() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean getStartRequested() {
            return this.startRequested_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStartRequested(boolean value) {
            this.bitField0_ |= 1;
            this.startRequested_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStartRequested() {
            this.bitField0_ &= -2;
            this.startRequested_ = false;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean hasDelayedStop() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean getDelayedStop() {
            return this.delayedStop_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDelayedStop(boolean value) {
            this.bitField0_ |= 2;
            this.delayedStop_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDelayedStop() {
            this.bitField0_ &= -3;
            this.delayedStop_ = false;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean hasStopIfKilled() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean getStopIfKilled() {
            return this.stopIfKilled_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStopIfKilled(boolean value) {
            this.bitField0_ |= 4;
            this.stopIfKilled_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStopIfKilled() {
            this.bitField0_ &= -5;
            this.stopIfKilled_ = false;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean hasCallStart() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean getCallStart() {
            return this.callStart_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCallStart(boolean value) {
            this.bitField0_ |= 8;
            this.callStart_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCallStart() {
            this.bitField0_ &= -9;
            this.callStart_ = false;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public boolean hasLastStartId() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
        public int getLastStartId() {
            return this.lastStartId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastStartId(int value) {
            this.bitField0_ |= 16;
            this.lastStartId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastStartId() {
            this.bitField0_ &= -17;
            this.lastStartId_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.startRequested_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.delayedStop_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.stopIfKilled_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(4, this.callStart_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.lastStartId_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.startRequested_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.delayedStop_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.stopIfKilled_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBoolSize(4, this.callStart_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.lastStartId_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Start parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Start parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Start parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Start parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Start parseFrom(InputStream input) throws IOException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Start parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Start parseDelimitedFrom(InputStream input) throws IOException {
            return (Start) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Start parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Start) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Start parseFrom(CodedInputStream input) throws IOException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Start parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Start) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Start prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Start, Builder> implements StartOrBuilder {
            private Builder() {
                super(Start.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean hasStartRequested() {
                return ((Start) this.instance).hasStartRequested();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean getStartRequested() {
                return ((Start) this.instance).getStartRequested();
            }

            public Builder setStartRequested(boolean value) {
                copyOnWrite();
                ((Start) this.instance).setStartRequested(value);
                return this;
            }

            public Builder clearStartRequested() {
                copyOnWrite();
                ((Start) this.instance).clearStartRequested();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean hasDelayedStop() {
                return ((Start) this.instance).hasDelayedStop();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean getDelayedStop() {
                return ((Start) this.instance).getDelayedStop();
            }

            public Builder setDelayedStop(boolean value) {
                copyOnWrite();
                ((Start) this.instance).setDelayedStop(value);
                return this;
            }

            public Builder clearDelayedStop() {
                copyOnWrite();
                ((Start) this.instance).clearDelayedStop();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean hasStopIfKilled() {
                return ((Start) this.instance).hasStopIfKilled();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean getStopIfKilled() {
                return ((Start) this.instance).getStopIfKilled();
            }

            public Builder setStopIfKilled(boolean value) {
                copyOnWrite();
                ((Start) this.instance).setStopIfKilled(value);
                return this;
            }

            public Builder clearStopIfKilled() {
                copyOnWrite();
                ((Start) this.instance).clearStopIfKilled();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean hasCallStart() {
                return ((Start) this.instance).hasCallStart();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean getCallStart() {
                return ((Start) this.instance).getCallStart();
            }

            public Builder setCallStart(boolean value) {
                copyOnWrite();
                ((Start) this.instance).setCallStart(value);
                return this;
            }

            public Builder clearCallStart() {
                copyOnWrite();
                ((Start) this.instance).clearCallStart();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public boolean hasLastStartId() {
                return ((Start) this.instance).hasLastStartId();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartOrBuilder
            public int getLastStartId() {
                return ((Start) this.instance).getLastStartId();
            }

            public Builder setLastStartId(int value) {
                copyOnWrite();
                ((Start) this.instance).setLastStartId(value);
                return this;
            }

            public Builder clearLastStartId() {
                copyOnWrite();
                ((Start) this.instance).clearLastStartId();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Start();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Start other = (Start) arg1;
                    this.startRequested_ = visitor.visitBoolean(hasStartRequested(), this.startRequested_, other.hasStartRequested(), other.startRequested_);
                    this.delayedStop_ = visitor.visitBoolean(hasDelayedStop(), this.delayedStop_, other.hasDelayedStop(), other.delayedStop_);
                    this.stopIfKilled_ = visitor.visitBoolean(hasStopIfKilled(), this.stopIfKilled_, other.hasStopIfKilled(), other.stopIfKilled_);
                    this.callStart_ = visitor.visitBoolean(hasCallStart(), this.callStart_, other.hasCallStart(), other.callStart_);
                    this.lastStartId_ = visitor.visitInt(hasLastStartId(), this.lastStartId_, other.hasLastStartId(), other.lastStartId_);
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
                                this.startRequested_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.delayedStop_ = input.readBool();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.stopIfKilled_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.callStart_ = input.readBool();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.lastStartId_ = input.readInt32();
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
                        synchronized (Start.class) {
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

        public static Start getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Start> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ExecuteNesting extends GeneratedMessageLite<ExecuteNesting, Builder> implements ExecuteNestingOrBuilder {
        private static final ExecuteNesting DEFAULT_INSTANCE = new ExecuteNesting();
        public static final int EXECUTE_FG_FIELD_NUMBER = 2;
        public static final int EXECUTE_NESTING_FIELD_NUMBER = 1;
        public static final int EXECUTING_START_FIELD_NUMBER = 3;
        private static volatile Parser<ExecuteNesting> PARSER;
        private int bitField0_;
        private boolean executeFg_ = false;
        private int executeNesting_ = 0;
        private Duration executingStart_;

        private ExecuteNesting() {
        }

        @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
        public boolean hasExecuteNesting() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
        public int getExecuteNesting() {
            return this.executeNesting_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExecuteNesting(int value) {
            this.bitField0_ |= 1;
            this.executeNesting_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearExecuteNesting() {
            this.bitField0_ &= -2;
            this.executeNesting_ = 0;
        }

        @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
        public boolean hasExecuteFg() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
        public boolean getExecuteFg() {
            return this.executeFg_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExecuteFg(boolean value) {
            this.bitField0_ |= 2;
            this.executeFg_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearExecuteFg() {
            this.bitField0_ &= -3;
            this.executeFg_ = false;
        }

        @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
        public boolean hasExecutingStart() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
        public Duration getExecutingStart() {
            Duration duration = this.executingStart_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExecutingStart(Duration value) {
            if (value != null) {
                this.executingStart_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExecutingStart(Duration.Builder builderForValue) {
            this.executingStart_ = (Duration) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeExecutingStart(Duration value) {
            Duration duration = this.executingStart_;
            if (duration == null || duration == Duration.getDefaultInstance()) {
                this.executingStart_ = value;
            } else {
                this.executingStart_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.executingStart_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearExecutingStart() {
            this.executingStart_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.executeNesting_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.executeFg_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getExecutingStart());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.executeNesting_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.executeFg_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getExecutingStart());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ExecuteNesting parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ExecuteNesting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ExecuteNesting parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ExecuteNesting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ExecuteNesting parseFrom(InputStream input) throws IOException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ExecuteNesting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ExecuteNesting parseDelimitedFrom(InputStream input) throws IOException {
            return (ExecuteNesting) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ExecuteNesting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExecuteNesting) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ExecuteNesting parseFrom(CodedInputStream input) throws IOException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ExecuteNesting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExecuteNesting) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExecuteNesting prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ExecuteNesting, Builder> implements ExecuteNestingOrBuilder {
            private Builder() {
                super(ExecuteNesting.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
            public boolean hasExecuteNesting() {
                return ((ExecuteNesting) this.instance).hasExecuteNesting();
            }

            @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
            public int getExecuteNesting() {
                return ((ExecuteNesting) this.instance).getExecuteNesting();
            }

            public Builder setExecuteNesting(int value) {
                copyOnWrite();
                ((ExecuteNesting) this.instance).setExecuteNesting(value);
                return this;
            }

            public Builder clearExecuteNesting() {
                copyOnWrite();
                ((ExecuteNesting) this.instance).clearExecuteNesting();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
            public boolean hasExecuteFg() {
                return ((ExecuteNesting) this.instance).hasExecuteFg();
            }

            @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
            public boolean getExecuteFg() {
                return ((ExecuteNesting) this.instance).getExecuteFg();
            }

            public Builder setExecuteFg(boolean value) {
                copyOnWrite();
                ((ExecuteNesting) this.instance).setExecuteFg(value);
                return this;
            }

            public Builder clearExecuteFg() {
                copyOnWrite();
                ((ExecuteNesting) this.instance).clearExecuteFg();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
            public boolean hasExecutingStart() {
                return ((ExecuteNesting) this.instance).hasExecutingStart();
            }

            @Override // com.android.server.am.ServiceRecordProto.ExecuteNestingOrBuilder
            public Duration getExecutingStart() {
                return ((ExecuteNesting) this.instance).getExecutingStart();
            }

            public Builder setExecutingStart(Duration value) {
                copyOnWrite();
                ((ExecuteNesting) this.instance).setExecutingStart((ExecuteNesting) value);
                return this;
            }

            public Builder setExecutingStart(Duration.Builder builderForValue) {
                copyOnWrite();
                ((ExecuteNesting) this.instance).setExecutingStart((ExecuteNesting) builderForValue);
                return this;
            }

            public Builder mergeExecutingStart(Duration value) {
                copyOnWrite();
                ((ExecuteNesting) this.instance).mergeExecutingStart(value);
                return this;
            }

            public Builder clearExecutingStart() {
                copyOnWrite();
                ((ExecuteNesting) this.instance).clearExecutingStart();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ExecuteNesting();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ExecuteNesting other = (ExecuteNesting) arg1;
                    this.executeNesting_ = visitor.visitInt(hasExecuteNesting(), this.executeNesting_, other.hasExecuteNesting(), other.executeNesting_);
                    this.executeFg_ = visitor.visitBoolean(hasExecuteFg(), this.executeFg_, other.hasExecuteFg(), other.executeFg_);
                    this.executingStart_ = (Duration) visitor.visitMessage(this.executingStart_, other.executingStart_);
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
                                this.executeNesting_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.executeFg_ = input.readBool();
                            } else if (tag == 26) {
                                Duration.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (Duration.Builder) this.executingStart_.toBuilder();
                                }
                                this.executingStart_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.executingStart_);
                                    this.executingStart_ = (Duration) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
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
                        synchronized (ExecuteNesting.class) {
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

        public static ExecuteNesting getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExecuteNesting> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Crash extends GeneratedMessageLite<Crash, Builder> implements CrashOrBuilder {
        public static final int CRASH_COUNT_FIELD_NUMBER = 4;
        private static final Crash DEFAULT_INSTANCE = new Crash();
        public static final int NEXT_RESTART_TIME_FIELD_NUMBER = 3;
        private static volatile Parser<Crash> PARSER = null;
        public static final int RESTART_COUNT_FIELD_NUMBER = 1;
        public static final int RESTART_DELAY_FIELD_NUMBER = 2;
        private int bitField0_;
        private int crashCount_ = 0;
        private Duration nextRestartTime_;
        private int restartCount_ = 0;
        private Duration restartDelay_;

        private Crash() {
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public boolean hasRestartCount() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public int getRestartCount() {
            return this.restartCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRestartCount(int value) {
            this.bitField0_ |= 1;
            this.restartCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRestartCount() {
            this.bitField0_ &= -2;
            this.restartCount_ = 0;
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public boolean hasRestartDelay() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public Duration getRestartDelay() {
            Duration duration = this.restartDelay_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRestartDelay(Duration value) {
            if (value != null) {
                this.restartDelay_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRestartDelay(Duration.Builder builderForValue) {
            this.restartDelay_ = (Duration) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeRestartDelay(Duration value) {
            Duration duration = this.restartDelay_;
            if (duration == null || duration == Duration.getDefaultInstance()) {
                this.restartDelay_ = value;
            } else {
                this.restartDelay_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.restartDelay_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRestartDelay() {
            this.restartDelay_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public boolean hasNextRestartTime() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public Duration getNextRestartTime() {
            Duration duration = this.nextRestartTime_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNextRestartTime(Duration value) {
            if (value != null) {
                this.nextRestartTime_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNextRestartTime(Duration.Builder builderForValue) {
            this.nextRestartTime_ = (Duration) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeNextRestartTime(Duration value) {
            Duration duration = this.nextRestartTime_;
            if (duration == null || duration == Duration.getDefaultInstance()) {
                this.nextRestartTime_ = value;
            } else {
                this.nextRestartTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.nextRestartTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNextRestartTime() {
            this.nextRestartTime_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public boolean hasCrashCount() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
        public int getCrashCount() {
            return this.crashCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCrashCount(int value) {
            this.bitField0_ |= 8;
            this.crashCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCrashCount() {
            this.bitField0_ &= -9;
            this.crashCount_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.restartCount_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getRestartDelay());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getNextRestartTime());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.crashCount_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.restartCount_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getRestartDelay());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getNextRestartTime());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.crashCount_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Crash parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Crash parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Crash parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Crash parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Crash parseFrom(InputStream input) throws IOException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Crash parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Crash parseDelimitedFrom(InputStream input) throws IOException {
            return (Crash) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Crash parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Crash) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Crash parseFrom(CodedInputStream input) throws IOException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Crash parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Crash) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Crash prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Crash, Builder> implements CrashOrBuilder {
            private Builder() {
                super(Crash.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public boolean hasRestartCount() {
                return ((Crash) this.instance).hasRestartCount();
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public int getRestartCount() {
                return ((Crash) this.instance).getRestartCount();
            }

            public Builder setRestartCount(int value) {
                copyOnWrite();
                ((Crash) this.instance).setRestartCount(value);
                return this;
            }

            public Builder clearRestartCount() {
                copyOnWrite();
                ((Crash) this.instance).clearRestartCount();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public boolean hasRestartDelay() {
                return ((Crash) this.instance).hasRestartDelay();
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public Duration getRestartDelay() {
                return ((Crash) this.instance).getRestartDelay();
            }

            public Builder setRestartDelay(Duration value) {
                copyOnWrite();
                ((Crash) this.instance).setRestartDelay((Crash) value);
                return this;
            }

            public Builder setRestartDelay(Duration.Builder builderForValue) {
                copyOnWrite();
                ((Crash) this.instance).setRestartDelay((Crash) builderForValue);
                return this;
            }

            public Builder mergeRestartDelay(Duration value) {
                copyOnWrite();
                ((Crash) this.instance).mergeRestartDelay(value);
                return this;
            }

            public Builder clearRestartDelay() {
                copyOnWrite();
                ((Crash) this.instance).clearRestartDelay();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public boolean hasNextRestartTime() {
                return ((Crash) this.instance).hasNextRestartTime();
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public Duration getNextRestartTime() {
                return ((Crash) this.instance).getNextRestartTime();
            }

            public Builder setNextRestartTime(Duration value) {
                copyOnWrite();
                ((Crash) this.instance).setNextRestartTime((Crash) value);
                return this;
            }

            public Builder setNextRestartTime(Duration.Builder builderForValue) {
                copyOnWrite();
                ((Crash) this.instance).setNextRestartTime((Crash) builderForValue);
                return this;
            }

            public Builder mergeNextRestartTime(Duration value) {
                copyOnWrite();
                ((Crash) this.instance).mergeNextRestartTime(value);
                return this;
            }

            public Builder clearNextRestartTime() {
                copyOnWrite();
                ((Crash) this.instance).clearNextRestartTime();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public boolean hasCrashCount() {
                return ((Crash) this.instance).hasCrashCount();
            }

            @Override // com.android.server.am.ServiceRecordProto.CrashOrBuilder
            public int getCrashCount() {
                return ((Crash) this.instance).getCrashCount();
            }

            public Builder setCrashCount(int value) {
                copyOnWrite();
                ((Crash) this.instance).setCrashCount(value);
                return this;
            }

            public Builder clearCrashCount() {
                copyOnWrite();
                ((Crash) this.instance).clearCrashCount();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Crash();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Crash other = (Crash) arg1;
                    this.restartCount_ = visitor.visitInt(hasRestartCount(), this.restartCount_, other.hasRestartCount(), other.restartCount_);
                    this.restartDelay_ = (Duration) visitor.visitMessage(this.restartDelay_, other.restartDelay_);
                    this.nextRestartTime_ = (Duration) visitor.visitMessage(this.nextRestartTime_, other.nextRestartTime_);
                    this.crashCount_ = visitor.visitInt(hasCrashCount(), this.crashCount_, other.hasCrashCount(), other.crashCount_);
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
                                this.restartCount_ = input.readInt32();
                            } else if (tag == 18) {
                                Duration.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (Duration.Builder) this.restartDelay_.toBuilder();
                                }
                                this.restartDelay_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.restartDelay_);
                                    this.restartDelay_ = (Duration) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                Duration.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (Duration.Builder) this.nextRestartTime_.toBuilder();
                                }
                                this.nextRestartTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.nextRestartTime_);
                                    this.nextRestartTime_ = (Duration) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.crashCount_ = input.readInt32();
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
                        synchronized (Crash.class) {
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

        public static Crash getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Crash> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class StartItem extends GeneratedMessageLite<StartItem, Builder> implements StartItemOrBuilder {
        private static final StartItem DEFAULT_INSTANCE = new StartItem();
        public static final int DELIVERY_COUNT_FIELD_NUMBER = 3;
        public static final int DONE_EXECUTING_COUNT_FIELD_NUMBER = 4;
        public static final int DURATION_FIELD_NUMBER = 2;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int INTENT_FIELD_NUMBER = 5;
        public static final int NEEDED_GRANTS_FIELD_NUMBER = 6;
        private static volatile Parser<StartItem> PARSER = null;
        public static final int URI_PERMISSIONS_FIELD_NUMBER = 7;
        private int bitField0_;
        private int deliveryCount_ = 0;
        private int doneExecutingCount_ = 0;
        private Duration duration_;
        private int id_ = 0;
        private IntentProto intent_;
        private NeededUriGrantsProto neededGrants_;
        private UriPermissionOwnerProto uriPermissions_;

        private StartItem() {
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public int getId() {
            return this.id_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setId(int value) {
            this.bitField0_ |= 1;
            this.id_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearId() {
            this.bitField0_ &= -2;
            this.id_ = 0;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public boolean hasDuration() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public Duration getDuration() {
            Duration duration = this.duration_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDuration(Duration value) {
            if (value != null) {
                this.duration_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDuration(Duration.Builder builderForValue) {
            this.duration_ = (Duration) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDuration(Duration value) {
            Duration duration = this.duration_;
            if (duration == null || duration == Duration.getDefaultInstance()) {
                this.duration_ = value;
            } else {
                this.duration_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.duration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDuration() {
            this.duration_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public boolean hasDeliveryCount() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public int getDeliveryCount() {
            return this.deliveryCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeliveryCount(int value) {
            this.bitField0_ |= 4;
            this.deliveryCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeliveryCount() {
            this.bitField0_ &= -5;
            this.deliveryCount_ = 0;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public boolean hasDoneExecutingCount() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public int getDoneExecutingCount() {
            return this.doneExecutingCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDoneExecutingCount(int value) {
            this.bitField0_ |= 8;
            this.doneExecutingCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDoneExecutingCount() {
            this.bitField0_ &= -9;
            this.doneExecutingCount_ = 0;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public boolean hasIntent() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public IntentProto getIntent() {
            IntentProto intentProto = this.intent_;
            return intentProto == null ? IntentProto.getDefaultInstance() : intentProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto value) {
            if (value != null) {
                this.intent_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto.Builder builderForValue) {
            this.intent_ = (IntentProto) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeIntent(IntentProto value) {
            IntentProto intentProto = this.intent_;
            if (intentProto == null || intentProto == IntentProto.getDefaultInstance()) {
                this.intent_ = value;
            } else {
                this.intent_ = (IntentProto) ((IntentProto.Builder) IntentProto.newBuilder(this.intent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIntent() {
            this.intent_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public boolean hasNeededGrants() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public NeededUriGrantsProto getNeededGrants() {
            NeededUriGrantsProto neededUriGrantsProto = this.neededGrants_;
            return neededUriGrantsProto == null ? NeededUriGrantsProto.getDefaultInstance() : neededUriGrantsProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNeededGrants(NeededUriGrantsProto value) {
            if (value != null) {
                this.neededGrants_ = value;
                this.bitField0_ |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNeededGrants(NeededUriGrantsProto.Builder builderForValue) {
            this.neededGrants_ = (NeededUriGrantsProto) builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeNeededGrants(NeededUriGrantsProto value) {
            NeededUriGrantsProto neededUriGrantsProto = this.neededGrants_;
            if (neededUriGrantsProto == null || neededUriGrantsProto == NeededUriGrantsProto.getDefaultInstance()) {
                this.neededGrants_ = value;
            } else {
                this.neededGrants_ = (NeededUriGrantsProto) ((NeededUriGrantsProto.Builder) NeededUriGrantsProto.newBuilder(this.neededGrants_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNeededGrants() {
            this.neededGrants_ = null;
            this.bitField0_ &= -33;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public boolean hasUriPermissions() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
        public UriPermissionOwnerProto getUriPermissions() {
            UriPermissionOwnerProto uriPermissionOwnerProto = this.uriPermissions_;
            return uriPermissionOwnerProto == null ? UriPermissionOwnerProto.getDefaultInstance() : uriPermissionOwnerProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUriPermissions(UriPermissionOwnerProto value) {
            if (value != null) {
                this.uriPermissions_ = value;
                this.bitField0_ |= 64;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUriPermissions(UriPermissionOwnerProto.Builder builderForValue) {
            this.uriPermissions_ = (UriPermissionOwnerProto) builderForValue.build();
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeUriPermissions(UriPermissionOwnerProto value) {
            UriPermissionOwnerProto uriPermissionOwnerProto = this.uriPermissions_;
            if (uriPermissionOwnerProto == null || uriPermissionOwnerProto == UriPermissionOwnerProto.getDefaultInstance()) {
                this.uriPermissions_ = value;
            } else {
                this.uriPermissions_ = (UriPermissionOwnerProto) ((UriPermissionOwnerProto.Builder) UriPermissionOwnerProto.newBuilder(this.uriPermissions_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUriPermissions() {
            this.uriPermissions_ = null;
            this.bitField0_ &= -65;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getDuration());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.deliveryCount_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.doneExecutingCount_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, getIntent());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(6, getNeededGrants());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeMessage(7, getUriPermissions());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getDuration());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.deliveryCount_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.doneExecutingCount_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, getIntent());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(6, getNeededGrants());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeMessageSize(7, getUriPermissions());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StartItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StartItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StartItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StartItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StartItem parseFrom(InputStream input) throws IOException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StartItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StartItem parseDelimitedFrom(InputStream input) throws IOException {
            return (StartItem) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StartItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StartItem) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StartItem parseFrom(CodedInputStream input) throws IOException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StartItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StartItem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StartItem prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StartItem, Builder> implements StartItemOrBuilder {
            private Builder() {
                super(StartItem.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public boolean hasId() {
                return ((StartItem) this.instance).hasId();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public int getId() {
                return ((StartItem) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((StartItem) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((StartItem) this.instance).clearId();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public boolean hasDuration() {
                return ((StartItem) this.instance).hasDuration();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public Duration getDuration() {
                return ((StartItem) this.instance).getDuration();
            }

            public Builder setDuration(Duration value) {
                copyOnWrite();
                ((StartItem) this.instance).setDuration((StartItem) value);
                return this;
            }

            public Builder setDuration(Duration.Builder builderForValue) {
                copyOnWrite();
                ((StartItem) this.instance).setDuration((StartItem) builderForValue);
                return this;
            }

            public Builder mergeDuration(Duration value) {
                copyOnWrite();
                ((StartItem) this.instance).mergeDuration(value);
                return this;
            }

            public Builder clearDuration() {
                copyOnWrite();
                ((StartItem) this.instance).clearDuration();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public boolean hasDeliveryCount() {
                return ((StartItem) this.instance).hasDeliveryCount();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public int getDeliveryCount() {
                return ((StartItem) this.instance).getDeliveryCount();
            }

            public Builder setDeliveryCount(int value) {
                copyOnWrite();
                ((StartItem) this.instance).setDeliveryCount(value);
                return this;
            }

            public Builder clearDeliveryCount() {
                copyOnWrite();
                ((StartItem) this.instance).clearDeliveryCount();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public boolean hasDoneExecutingCount() {
                return ((StartItem) this.instance).hasDoneExecutingCount();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public int getDoneExecutingCount() {
                return ((StartItem) this.instance).getDoneExecutingCount();
            }

            public Builder setDoneExecutingCount(int value) {
                copyOnWrite();
                ((StartItem) this.instance).setDoneExecutingCount(value);
                return this;
            }

            public Builder clearDoneExecutingCount() {
                copyOnWrite();
                ((StartItem) this.instance).clearDoneExecutingCount();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public boolean hasIntent() {
                return ((StartItem) this.instance).hasIntent();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public IntentProto getIntent() {
                return ((StartItem) this.instance).getIntent();
            }

            public Builder setIntent(IntentProto value) {
                copyOnWrite();
                ((StartItem) this.instance).setIntent((StartItem) value);
                return this;
            }

            public Builder setIntent(IntentProto.Builder builderForValue) {
                copyOnWrite();
                ((StartItem) this.instance).setIntent((StartItem) builderForValue);
                return this;
            }

            public Builder mergeIntent(IntentProto value) {
                copyOnWrite();
                ((StartItem) this.instance).mergeIntent(value);
                return this;
            }

            public Builder clearIntent() {
                copyOnWrite();
                ((StartItem) this.instance).clearIntent();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public boolean hasNeededGrants() {
                return ((StartItem) this.instance).hasNeededGrants();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public NeededUriGrantsProto getNeededGrants() {
                return ((StartItem) this.instance).getNeededGrants();
            }

            public Builder setNeededGrants(NeededUriGrantsProto value) {
                copyOnWrite();
                ((StartItem) this.instance).setNeededGrants((StartItem) value);
                return this;
            }

            public Builder setNeededGrants(NeededUriGrantsProto.Builder builderForValue) {
                copyOnWrite();
                ((StartItem) this.instance).setNeededGrants((StartItem) builderForValue);
                return this;
            }

            public Builder mergeNeededGrants(NeededUriGrantsProto value) {
                copyOnWrite();
                ((StartItem) this.instance).mergeNeededGrants(value);
                return this;
            }

            public Builder clearNeededGrants() {
                copyOnWrite();
                ((StartItem) this.instance).clearNeededGrants();
                return this;
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public boolean hasUriPermissions() {
                return ((StartItem) this.instance).hasUriPermissions();
            }

            @Override // com.android.server.am.ServiceRecordProto.StartItemOrBuilder
            public UriPermissionOwnerProto getUriPermissions() {
                return ((StartItem) this.instance).getUriPermissions();
            }

            public Builder setUriPermissions(UriPermissionOwnerProto value) {
                copyOnWrite();
                ((StartItem) this.instance).setUriPermissions((StartItem) value);
                return this;
            }

            public Builder setUriPermissions(UriPermissionOwnerProto.Builder builderForValue) {
                copyOnWrite();
                ((StartItem) this.instance).setUriPermissions((StartItem) builderForValue);
                return this;
            }

            public Builder mergeUriPermissions(UriPermissionOwnerProto value) {
                copyOnWrite();
                ((StartItem) this.instance).mergeUriPermissions(value);
                return this;
            }

            public Builder clearUriPermissions() {
                copyOnWrite();
                ((StartItem) this.instance).clearUriPermissions();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StartItem();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StartItem other = (StartItem) arg1;
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.duration_ = (Duration) visitor.visitMessage(this.duration_, other.duration_);
                    this.deliveryCount_ = visitor.visitInt(hasDeliveryCount(), this.deliveryCount_, other.hasDeliveryCount(), other.deliveryCount_);
                    this.doneExecutingCount_ = visitor.visitInt(hasDoneExecutingCount(), this.doneExecutingCount_, other.hasDoneExecutingCount(), other.doneExecutingCount_);
                    this.intent_ = (IntentProto) visitor.visitMessage(this.intent_, other.intent_);
                    this.neededGrants_ = (NeededUriGrantsProto) visitor.visitMessage(this.neededGrants_, other.neededGrants_);
                    this.uriPermissions_ = (UriPermissionOwnerProto) visitor.visitMessage(this.uriPermissions_, other.uriPermissions_);
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
                                this.id_ = input.readInt32();
                            } else if (tag == 18) {
                                Duration.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (Duration.Builder) this.duration_.toBuilder();
                                }
                                this.duration_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.duration_);
                                    this.duration_ = (Duration) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.deliveryCount_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.doneExecutingCount_ = input.readInt32();
                            } else if (tag == 42) {
                                IntentProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder2 = (IntentProto.Builder) this.intent_.toBuilder();
                                }
                                this.intent_ = (IntentProto) input.readMessage(IntentProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.intent_);
                                    this.intent_ = (IntentProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            } else if (tag == 50) {
                                NeededUriGrantsProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder3 = (NeededUriGrantsProto.Builder) this.neededGrants_.toBuilder();
                                }
                                this.neededGrants_ = (NeededUriGrantsProto) input.readMessage(NeededUriGrantsProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.neededGrants_);
                                    this.neededGrants_ = (NeededUriGrantsProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ = 32 | this.bitField0_;
                            } else if (tag == 58) {
                                UriPermissionOwnerProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder4 = (UriPermissionOwnerProto.Builder) this.uriPermissions_.toBuilder();
                                }
                                this.uriPermissions_ = (UriPermissionOwnerProto) input.readMessage(UriPermissionOwnerProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.uriPermissions_);
                                    this.uriPermissions_ = (UriPermissionOwnerProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 64;
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
                        synchronized (StartItem.class) {
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

        public static StartItem getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StartItem> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasShortName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public String getShortName() {
        return this.shortName_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ByteString getShortNameBytes() {
        return ByteString.copyFromUtf8(this.shortName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShortName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.shortName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShortName() {
        this.bitField0_ &= -2;
        this.shortName_ = getDefaultInstance().getShortName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShortNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.shortName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasIsRunning() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean getIsRunning() {
        return this.isRunning_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsRunning(boolean value) {
        this.bitField0_ |= 2;
        this.isRunning_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsRunning() {
        this.bitField0_ &= -3;
        this.isRunning_ = false;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasPid() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public int getPid() {
        return this.pid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPid(int value) {
        this.bitField0_ |= 4;
        this.pid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPid() {
        this.bitField0_ &= -5;
        this.pid_ = 0;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasIntent() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public IntentProto getIntent() {
        IntentProto intentProto = this.intent_;
        return intentProto == null ? IntentProto.getDefaultInstance() : intentProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntent(IntentProto value) {
        if (value != null) {
            this.intent_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIntent(IntentProto.Builder builderForValue) {
        this.intent_ = (IntentProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIntent(IntentProto value) {
        IntentProto intentProto = this.intent_;
        if (intentProto == null || intentProto == IntentProto.getDefaultInstance()) {
            this.intent_ = value;
        } else {
            this.intent_ = (IntentProto) ((IntentProto.Builder) IntentProto.newBuilder(this.intent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIntent() {
        this.intent_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ByteString getPackageNameBytes() {
        return ByteString.copyFromUtf8(this.packageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.packageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageName() {
        this.bitField0_ &= -17;
        this.packageName_ = getDefaultInstance().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.packageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasProcessName() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public String getProcessName() {
        return this.processName_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ByteString getProcessNameBytes() {
        return ByteString.copyFromUtf8(this.processName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessName(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.processName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessName() {
        this.bitField0_ &= -33;
        this.processName_ = getDefaultInstance().getProcessName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.processName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasPermission() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public String getPermission() {
        return this.permission_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ByteString getPermissionBytes() {
        return ByteString.copyFromUtf8(this.permission_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPermission(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.permission_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPermission() {
        this.bitField0_ &= -65;
        this.permission_ = getDefaultInstance().getPermission();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPermissionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.permission_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasAppinfo() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public AppInfo getAppinfo() {
        AppInfo appInfo = this.appinfo_;
        return appInfo == null ? AppInfo.getDefaultInstance() : appInfo;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppinfo(AppInfo value) {
        if (value != null) {
            this.appinfo_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppinfo(AppInfo.Builder builderForValue) {
        this.appinfo_ = (AppInfo) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAppinfo(AppInfo value) {
        AppInfo appInfo = this.appinfo_;
        if (appInfo == null || appInfo == AppInfo.getDefaultInstance()) {
            this.appinfo_ = value;
        } else {
            this.appinfo_ = (AppInfo) ((AppInfo.Builder) AppInfo.newBuilder(this.appinfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppinfo() {
        this.appinfo_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasApp() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ProcessRecordProto getApp() {
        ProcessRecordProto processRecordProto = this.app_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApp(ProcessRecordProto value) {
        if (value != null) {
            this.app_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApp(ProcessRecordProto.Builder builderForValue) {
        this.app_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeApp(ProcessRecordProto value) {
        ProcessRecordProto processRecordProto = this.app_;
        if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
            this.app_ = value;
        } else {
            this.app_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.app_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearApp() {
        this.app_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasIsolatedProc() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ProcessRecordProto getIsolatedProc() {
        ProcessRecordProto processRecordProto = this.isolatedProc_;
        return processRecordProto == null ? ProcessRecordProto.getDefaultInstance() : processRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsolatedProc(ProcessRecordProto value) {
        if (value != null) {
            this.isolatedProc_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsolatedProc(ProcessRecordProto.Builder builderForValue) {
        this.isolatedProc_ = (ProcessRecordProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIsolatedProc(ProcessRecordProto value) {
        ProcessRecordProto processRecordProto = this.isolatedProc_;
        if (processRecordProto == null || processRecordProto == ProcessRecordProto.getDefaultInstance()) {
            this.isolatedProc_ = value;
        } else {
            this.isolatedProc_ = (ProcessRecordProto) ((ProcessRecordProto.Builder) ProcessRecordProto.newBuilder(this.isolatedProc_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsolatedProc() {
        this.isolatedProc_ = null;
        this.bitField0_ &= -513;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasWhitelistManager() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean getWhitelistManager() {
        return this.whitelistManager_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhitelistManager(boolean value) {
        this.bitField0_ |= 1024;
        this.whitelistManager_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhitelistManager() {
        this.bitField0_ &= -1025;
        this.whitelistManager_ = false;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasDelayed() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean getDelayed() {
        return this.delayed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDelayed(boolean value) {
        this.bitField0_ |= 2048;
        this.delayed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDelayed() {
        this.bitField0_ &= -2049;
        this.delayed_ = false;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasForeground() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Foreground getForeground() {
        Foreground foreground = this.foreground_;
        return foreground == null ? Foreground.getDefaultInstance() : foreground;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForeground(Foreground value) {
        if (value != null) {
            this.foreground_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForeground(Foreground.Builder builderForValue) {
        this.foreground_ = (Foreground) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeForeground(Foreground value) {
        Foreground foreground = this.foreground_;
        if (foreground == null || foreground == Foreground.getDefaultInstance()) {
            this.foreground_ = value;
        } else {
            this.foreground_ = (Foreground) ((Foreground.Builder) Foreground.newBuilder(this.foreground_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForeground() {
        this.foreground_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasCreateRealTime() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Duration getCreateRealTime() {
        Duration duration = this.createRealTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCreateRealTime(Duration value) {
        if (value != null) {
            this.createRealTime_ = value;
            this.bitField0_ |= 8192;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCreateRealTime(Duration.Builder builderForValue) {
        this.createRealTime_ = (Duration) builderForValue.build();
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCreateRealTime(Duration value) {
        Duration duration = this.createRealTime_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.createRealTime_ = value;
        } else {
            this.createRealTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.createRealTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCreateRealTime() {
        this.createRealTime_ = null;
        this.bitField0_ &= -8193;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasStartingBgTimeout() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Duration getStartingBgTimeout() {
        Duration duration = this.startingBgTimeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartingBgTimeout(Duration value) {
        if (value != null) {
            this.startingBgTimeout_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartingBgTimeout(Duration.Builder builderForValue) {
        this.startingBgTimeout_ = (Duration) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStartingBgTimeout(Duration value) {
        Duration duration = this.startingBgTimeout_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.startingBgTimeout_ = value;
        } else {
            this.startingBgTimeout_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.startingBgTimeout_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartingBgTimeout() {
        this.startingBgTimeout_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasLastActivityTime() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Duration getLastActivityTime() {
        Duration duration = this.lastActivityTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastActivityTime(Duration value) {
        if (value != null) {
            this.lastActivityTime_ = value;
            this.bitField0_ |= 32768;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastActivityTime(Duration.Builder builderForValue) {
        this.lastActivityTime_ = (Duration) builderForValue.build();
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLastActivityTime(Duration value) {
        Duration duration = this.lastActivityTime_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.lastActivityTime_ = value;
        } else {
            this.lastActivityTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.lastActivityTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastActivityTime() {
        this.lastActivityTime_ = null;
        this.bitField0_ &= -32769;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasRestartTime() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Duration getRestartTime() {
        Duration duration = this.restartTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRestartTime(Duration value) {
        if (value != null) {
            this.restartTime_ = value;
            this.bitField0_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRestartTime(Duration.Builder builderForValue) {
        this.restartTime_ = (Duration) builderForValue.build();
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRestartTime(Duration value) {
        Duration duration = this.restartTime_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.restartTime_ = value;
        } else {
            this.restartTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.restartTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRestartTime() {
        this.restartTime_ = null;
        this.bitField0_ &= -65537;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasCreatedFromFg() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean getCreatedFromFg() {
        return this.createdFromFg_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCreatedFromFg(boolean value) {
        this.bitField0_ |= 131072;
        this.createdFromFg_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCreatedFromFg() {
        this.bitField0_ &= -131073;
        this.createdFromFg_ = false;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasStart() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Start getStart() {
        Start start = this.start_;
        return start == null ? Start.getDefaultInstance() : start;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStart(Start value) {
        if (value != null) {
            this.start_ = value;
            this.bitField0_ |= 262144;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStart(Start.Builder builderForValue) {
        this.start_ = (Start) builderForValue.build();
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStart(Start value) {
        Start start = this.start_;
        if (start == null || start == Start.getDefaultInstance()) {
            this.start_ = value;
        } else {
            this.start_ = (Start) ((Start.Builder) Start.newBuilder(this.start_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStart() {
        this.start_ = null;
        this.bitField0_ &= -262145;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasExecute() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ExecuteNesting getExecute() {
        ExecuteNesting executeNesting = this.execute_;
        return executeNesting == null ? ExecuteNesting.getDefaultInstance() : executeNesting;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExecute(ExecuteNesting value) {
        if (value != null) {
            this.execute_ = value;
            this.bitField0_ |= 524288;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExecute(ExecuteNesting.Builder builderForValue) {
        this.execute_ = (ExecuteNesting) builderForValue.build();
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeExecute(ExecuteNesting value) {
        ExecuteNesting executeNesting = this.execute_;
        if (executeNesting == null || executeNesting == ExecuteNesting.getDefaultInstance()) {
            this.execute_ = value;
        } else {
            this.execute_ = (ExecuteNesting) ((ExecuteNesting.Builder) ExecuteNesting.newBuilder(this.execute_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearExecute() {
        this.execute_ = null;
        this.bitField0_ &= -524289;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasDestoryTime() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Duration getDestoryTime() {
        Duration duration = this.destoryTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDestoryTime(Duration value) {
        if (value != null) {
            this.destoryTime_ = value;
            this.bitField0_ |= 1048576;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDestoryTime(Duration.Builder builderForValue) {
        this.destoryTime_ = (Duration) builderForValue.build();
        this.bitField0_ |= 1048576;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDestoryTime(Duration value) {
        Duration duration = this.destoryTime_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.destoryTime_ = value;
        } else {
            this.destoryTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.destoryTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1048576;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDestoryTime() {
        this.destoryTime_ = null;
        this.bitField0_ &= -1048577;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public boolean hasCrash() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public Crash getCrash() {
        Crash crash = this.crash_;
        return crash == null ? Crash.getDefaultInstance() : crash;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrash(Crash value) {
        if (value != null) {
            this.crash_ = value;
            this.bitField0_ |= 2097152;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrash(Crash.Builder builderForValue) {
        this.crash_ = (Crash) builderForValue.build();
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCrash(Crash value) {
        Crash crash = this.crash_;
        if (crash == null || crash == Crash.getDefaultInstance()) {
            this.crash_ = value;
        } else {
            this.crash_ = (Crash) ((Crash.Builder) Crash.newBuilder(this.crash_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCrash() {
        this.crash_ = null;
        this.bitField0_ &= -2097153;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public List<StartItem> getDeliveredStartsList() {
        return this.deliveredStarts_;
    }

    public List<? extends StartItemOrBuilder> getDeliveredStartsOrBuilderList() {
        return this.deliveredStarts_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public int getDeliveredStartsCount() {
        return this.deliveredStarts_.size();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public StartItem getDeliveredStarts(int index) {
        return this.deliveredStarts_.get(index);
    }

    public StartItemOrBuilder getDeliveredStartsOrBuilder(int index) {
        return this.deliveredStarts_.get(index);
    }

    private void ensureDeliveredStartsIsMutable() {
        if (!this.deliveredStarts_.isModifiable()) {
            this.deliveredStarts_ = GeneratedMessageLite.mutableCopy(this.deliveredStarts_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeliveredStarts(int index, StartItem value) {
        if (value != null) {
            ensureDeliveredStartsIsMutable();
            this.deliveredStarts_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeliveredStarts(int index, StartItem.Builder builderForValue) {
        ensureDeliveredStartsIsMutable();
        this.deliveredStarts_.set(index, (StartItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeliveredStarts(StartItem value) {
        if (value != null) {
            ensureDeliveredStartsIsMutable();
            this.deliveredStarts_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeliveredStarts(int index, StartItem value) {
        if (value != null) {
            ensureDeliveredStartsIsMutable();
            this.deliveredStarts_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeliveredStarts(StartItem.Builder builderForValue) {
        ensureDeliveredStartsIsMutable();
        this.deliveredStarts_.add((StartItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeliveredStarts(int index, StartItem.Builder builderForValue) {
        ensureDeliveredStartsIsMutable();
        this.deliveredStarts_.add(index, (StartItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDeliveredStarts(Iterable<? extends StartItem> values) {
        ensureDeliveredStartsIsMutable();
        AbstractMessageLite.addAll(values, this.deliveredStarts_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeliveredStarts() {
        this.deliveredStarts_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDeliveredStarts(int index) {
        ensureDeliveredStartsIsMutable();
        this.deliveredStarts_.remove(index);
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public List<StartItem> getPendingStartsList() {
        return this.pendingStarts_;
    }

    public List<? extends StartItemOrBuilder> getPendingStartsOrBuilderList() {
        return this.pendingStarts_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public int getPendingStartsCount() {
        return this.pendingStarts_.size();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public StartItem getPendingStarts(int index) {
        return this.pendingStarts_.get(index);
    }

    public StartItemOrBuilder getPendingStartsOrBuilder(int index) {
        return this.pendingStarts_.get(index);
    }

    private void ensurePendingStartsIsMutable() {
        if (!this.pendingStarts_.isModifiable()) {
            this.pendingStarts_ = GeneratedMessageLite.mutableCopy(this.pendingStarts_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingStarts(int index, StartItem value) {
        if (value != null) {
            ensurePendingStartsIsMutable();
            this.pendingStarts_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingStarts(int index, StartItem.Builder builderForValue) {
        ensurePendingStartsIsMutable();
        this.pendingStarts_.set(index, (StartItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingStarts(StartItem value) {
        if (value != null) {
            ensurePendingStartsIsMutable();
            this.pendingStarts_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingStarts(int index, StartItem value) {
        if (value != null) {
            ensurePendingStartsIsMutable();
            this.pendingStarts_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingStarts(StartItem.Builder builderForValue) {
        ensurePendingStartsIsMutable();
        this.pendingStarts_.add((StartItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPendingStarts(int index, StartItem.Builder builderForValue) {
        ensurePendingStartsIsMutable();
        this.pendingStarts_.add(index, (StartItem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPendingStarts(Iterable<? extends StartItem> values) {
        ensurePendingStartsIsMutable();
        AbstractMessageLite.addAll(values, this.pendingStarts_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingStarts() {
        this.pendingStarts_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePendingStarts(int index) {
        ensurePendingStartsIsMutable();
        this.pendingStarts_.remove(index);
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public List<IntentBindRecordProto> getBindingsList() {
        return this.bindings_;
    }

    public List<? extends IntentBindRecordProtoOrBuilder> getBindingsOrBuilderList() {
        return this.bindings_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public int getBindingsCount() {
        return this.bindings_.size();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public IntentBindRecordProto getBindings(int index) {
        return this.bindings_.get(index);
    }

    public IntentBindRecordProtoOrBuilder getBindingsOrBuilder(int index) {
        return this.bindings_.get(index);
    }

    private void ensureBindingsIsMutable() {
        if (!this.bindings_.isModifiable()) {
            this.bindings_ = GeneratedMessageLite.mutableCopy(this.bindings_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBindings(int index, IntentBindRecordProto value) {
        if (value != null) {
            ensureBindingsIsMutable();
            this.bindings_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBindings(int index, IntentBindRecordProto.Builder builderForValue) {
        ensureBindingsIsMutable();
        this.bindings_.set(index, (IntentBindRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBindings(IntentBindRecordProto value) {
        if (value != null) {
            ensureBindingsIsMutable();
            this.bindings_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBindings(int index, IntentBindRecordProto value) {
        if (value != null) {
            ensureBindingsIsMutable();
            this.bindings_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBindings(IntentBindRecordProto.Builder builderForValue) {
        ensureBindingsIsMutable();
        this.bindings_.add((IntentBindRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBindings(int index, IntentBindRecordProto.Builder builderForValue) {
        ensureBindingsIsMutable();
        this.bindings_.add(index, (IntentBindRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBindings(Iterable<? extends IntentBindRecordProto> values) {
        ensureBindingsIsMutable();
        AbstractMessageLite.addAll(values, this.bindings_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBindings() {
        this.bindings_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBindings(int index) {
        ensureBindingsIsMutable();
        this.bindings_.remove(index);
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public List<ConnectionRecordProto> getConnectionsList() {
        return this.connections_;
    }

    public List<? extends ConnectionRecordProtoOrBuilder> getConnectionsOrBuilderList() {
        return this.connections_;
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public int getConnectionsCount() {
        return this.connections_.size();
    }

    @Override // com.android.server.am.ServiceRecordProtoOrBuilder
    public ConnectionRecordProto getConnections(int index) {
        return this.connections_.get(index);
    }

    public ConnectionRecordProtoOrBuilder getConnectionsOrBuilder(int index) {
        return this.connections_.get(index);
    }

    private void ensureConnectionsIsMutable() {
        if (!this.connections_.isModifiable()) {
            this.connections_ = GeneratedMessageLite.mutableCopy(this.connections_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnections(int index, ConnectionRecordProto value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnections(int index, ConnectionRecordProto.Builder builderForValue) {
        ensureConnectionsIsMutable();
        this.connections_.set(index, (ConnectionRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(ConnectionRecordProto value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(int index, ConnectionRecordProto value) {
        if (value != null) {
            ensureConnectionsIsMutable();
            this.connections_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(ConnectionRecordProto.Builder builderForValue) {
        ensureConnectionsIsMutable();
        this.connections_.add((ConnectionRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConnections(int index, ConnectionRecordProto.Builder builderForValue) {
        ensureConnectionsIsMutable();
        this.connections_.add(index, (ConnectionRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllConnections(Iterable<? extends ConnectionRecordProto> values) {
        ensureConnectionsIsMutable();
        AbstractMessageLite.addAll(values, this.connections_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnections() {
        this.connections_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeConnections(int index) {
        ensureConnectionsIsMutable();
        this.connections_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getShortName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isRunning_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.pid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getIntent());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getPackageName());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(6, getProcessName());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getPermission());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getAppinfo());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getApp());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getIsolatedProc());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeBool(11, this.whitelistManager_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(12, this.delayed_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(13, getForeground());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeMessage(14, getCreateRealTime());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(15, getStartingBgTimeout());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeMessage(16, getLastActivityTime());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeMessage(17, getRestartTime());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeBool(18, this.createdFromFg_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeMessage(19, getStart());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeMessage(20, getExecute());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeMessage(21, getDestoryTime());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeMessage(22, getCrash());
        }
        for (int i = 0; i < this.deliveredStarts_.size(); i++) {
            output.writeMessage(23, this.deliveredStarts_.get(i));
        }
        for (int i2 = 0; i2 < this.pendingStarts_.size(); i2++) {
            output.writeMessage(24, this.pendingStarts_.get(i2));
        }
        for (int i3 = 0; i3 < this.bindings_.size(); i3++) {
            output.writeMessage(25, this.bindings_.get(i3));
        }
        for (int i4 = 0; i4 < this.connections_.size(); i4++) {
            output.writeMessage(26, this.connections_.get(i4));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getShortName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isRunning_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.pid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getIntent());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getPackageName());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(6, getProcessName());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getPermission());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(8, getAppinfo());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getApp());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getIsolatedProc());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeBoolSize(11, this.whitelistManager_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeBoolSize(12, this.delayed_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeMessageSize(13, getForeground());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeMessageSize(14, getCreateRealTime());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(15, getStartingBgTimeout());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeMessageSize(16, getLastActivityTime());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeMessageSize(17, getRestartTime());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeBoolSize(18, this.createdFromFg_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeMessageSize(19, getStart());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeMessageSize(20, getExecute());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeMessageSize(21, getDestoryTime());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeMessageSize(22, getCrash());
        }
        for (int i = 0; i < this.deliveredStarts_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(23, this.deliveredStarts_.get(i));
        }
        for (int i2 = 0; i2 < this.pendingStarts_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(24, this.pendingStarts_.get(i2));
        }
        for (int i3 = 0; i3 < this.bindings_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(25, this.bindings_.get(i3));
        }
        for (int i4 = 0; i4 < this.connections_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(26, this.connections_.get(i4));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ServiceRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ServiceRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ServiceRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ServiceRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ServiceRecordProto parseFrom(InputStream input) throws IOException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ServiceRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ServiceRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ServiceRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ServiceRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ServiceRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ServiceRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ServiceRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ServiceRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ServiceRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ServiceRecordProto, Builder> implements ServiceRecordProtoOrBuilder {
        private Builder() {
            super(ServiceRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasShortName() {
            return ((ServiceRecordProto) this.instance).hasShortName();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public String getShortName() {
            return ((ServiceRecordProto) this.instance).getShortName();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ByteString getShortNameBytes() {
            return ((ServiceRecordProto) this.instance).getShortNameBytes();
        }

        public Builder setShortName(String value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setShortName(value);
            return this;
        }

        public Builder clearShortName() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearShortName();
            return this;
        }

        public Builder setShortNameBytes(ByteString value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setShortNameBytes(value);
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasIsRunning() {
            return ((ServiceRecordProto) this.instance).hasIsRunning();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean getIsRunning() {
            return ((ServiceRecordProto) this.instance).getIsRunning();
        }

        public Builder setIsRunning(boolean value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setIsRunning(value);
            return this;
        }

        public Builder clearIsRunning() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearIsRunning();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasPid() {
            return ((ServiceRecordProto) this.instance).hasPid();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public int getPid() {
            return ((ServiceRecordProto) this.instance).getPid();
        }

        public Builder setPid(int value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setPid(value);
            return this;
        }

        public Builder clearPid() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearPid();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasIntent() {
            return ((ServiceRecordProto) this.instance).hasIntent();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public IntentProto getIntent() {
            return ((ServiceRecordProto) this.instance).getIntent();
        }

        public Builder setIntent(IntentProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setIntent((ServiceRecordProto) value);
            return this;
        }

        public Builder setIntent(IntentProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setIntent((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeIntent(IntentProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeIntent(value);
            return this;
        }

        public Builder clearIntent() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearIntent();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasPackageName() {
            return ((ServiceRecordProto) this.instance).hasPackageName();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public String getPackageName() {
            return ((ServiceRecordProto) this.instance).getPackageName();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((ServiceRecordProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setPackageNameBytes(value);
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasProcessName() {
            return ((ServiceRecordProto) this.instance).hasProcessName();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public String getProcessName() {
            return ((ServiceRecordProto) this.instance).getProcessName();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ByteString getProcessNameBytes() {
            return ((ServiceRecordProto) this.instance).getProcessNameBytes();
        }

        public Builder setProcessName(String value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setProcessName(value);
            return this;
        }

        public Builder clearProcessName() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearProcessName();
            return this;
        }

        public Builder setProcessNameBytes(ByteString value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setProcessNameBytes(value);
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasPermission() {
            return ((ServiceRecordProto) this.instance).hasPermission();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public String getPermission() {
            return ((ServiceRecordProto) this.instance).getPermission();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ByteString getPermissionBytes() {
            return ((ServiceRecordProto) this.instance).getPermissionBytes();
        }

        public Builder setPermission(String value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setPermission(value);
            return this;
        }

        public Builder clearPermission() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearPermission();
            return this;
        }

        public Builder setPermissionBytes(ByteString value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setPermissionBytes(value);
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasAppinfo() {
            return ((ServiceRecordProto) this.instance).hasAppinfo();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public AppInfo getAppinfo() {
            return ((ServiceRecordProto) this.instance).getAppinfo();
        }

        public Builder setAppinfo(AppInfo value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setAppinfo((ServiceRecordProto) value);
            return this;
        }

        public Builder setAppinfo(AppInfo.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setAppinfo((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeAppinfo(AppInfo value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeAppinfo(value);
            return this;
        }

        public Builder clearAppinfo() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearAppinfo();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasApp() {
            return ((ServiceRecordProto) this.instance).hasApp();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ProcessRecordProto getApp() {
            return ((ServiceRecordProto) this.instance).getApp();
        }

        public Builder setApp(ProcessRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setApp((ServiceRecordProto) value);
            return this;
        }

        public Builder setApp(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setApp((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeApp(ProcessRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeApp(value);
            return this;
        }

        public Builder clearApp() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearApp();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasIsolatedProc() {
            return ((ServiceRecordProto) this.instance).hasIsolatedProc();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ProcessRecordProto getIsolatedProc() {
            return ((ServiceRecordProto) this.instance).getIsolatedProc();
        }

        public Builder setIsolatedProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setIsolatedProc((ServiceRecordProto) value);
            return this;
        }

        public Builder setIsolatedProc(ProcessRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setIsolatedProc((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeIsolatedProc(ProcessRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeIsolatedProc(value);
            return this;
        }

        public Builder clearIsolatedProc() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearIsolatedProc();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasWhitelistManager() {
            return ((ServiceRecordProto) this.instance).hasWhitelistManager();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean getWhitelistManager() {
            return ((ServiceRecordProto) this.instance).getWhitelistManager();
        }

        public Builder setWhitelistManager(boolean value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setWhitelistManager(value);
            return this;
        }

        public Builder clearWhitelistManager() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearWhitelistManager();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasDelayed() {
            return ((ServiceRecordProto) this.instance).hasDelayed();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean getDelayed() {
            return ((ServiceRecordProto) this.instance).getDelayed();
        }

        public Builder setDelayed(boolean value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setDelayed(value);
            return this;
        }

        public Builder clearDelayed() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearDelayed();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasForeground() {
            return ((ServiceRecordProto) this.instance).hasForeground();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Foreground getForeground() {
            return ((ServiceRecordProto) this.instance).getForeground();
        }

        public Builder setForeground(Foreground value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setForeground((ServiceRecordProto) value);
            return this;
        }

        public Builder setForeground(Foreground.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setForeground((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeForeground(Foreground value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeForeground(value);
            return this;
        }

        public Builder clearForeground() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearForeground();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasCreateRealTime() {
            return ((ServiceRecordProto) this.instance).hasCreateRealTime();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Duration getCreateRealTime() {
            return ((ServiceRecordProto) this.instance).getCreateRealTime();
        }

        public Builder setCreateRealTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setCreateRealTime((ServiceRecordProto) value);
            return this;
        }

        public Builder setCreateRealTime(Duration.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setCreateRealTime((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeCreateRealTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeCreateRealTime(value);
            return this;
        }

        public Builder clearCreateRealTime() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearCreateRealTime();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasStartingBgTimeout() {
            return ((ServiceRecordProto) this.instance).hasStartingBgTimeout();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Duration getStartingBgTimeout() {
            return ((ServiceRecordProto) this.instance).getStartingBgTimeout();
        }

        public Builder setStartingBgTimeout(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setStartingBgTimeout((ServiceRecordProto) value);
            return this;
        }

        public Builder setStartingBgTimeout(Duration.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setStartingBgTimeout((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeStartingBgTimeout(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeStartingBgTimeout(value);
            return this;
        }

        public Builder clearStartingBgTimeout() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearStartingBgTimeout();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasLastActivityTime() {
            return ((ServiceRecordProto) this.instance).hasLastActivityTime();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Duration getLastActivityTime() {
            return ((ServiceRecordProto) this.instance).getLastActivityTime();
        }

        public Builder setLastActivityTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setLastActivityTime((ServiceRecordProto) value);
            return this;
        }

        public Builder setLastActivityTime(Duration.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setLastActivityTime((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeLastActivityTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeLastActivityTime(value);
            return this;
        }

        public Builder clearLastActivityTime() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearLastActivityTime();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasRestartTime() {
            return ((ServiceRecordProto) this.instance).hasRestartTime();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Duration getRestartTime() {
            return ((ServiceRecordProto) this.instance).getRestartTime();
        }

        public Builder setRestartTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setRestartTime((ServiceRecordProto) value);
            return this;
        }

        public Builder setRestartTime(Duration.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setRestartTime((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeRestartTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeRestartTime(value);
            return this;
        }

        public Builder clearRestartTime() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearRestartTime();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasCreatedFromFg() {
            return ((ServiceRecordProto) this.instance).hasCreatedFromFg();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean getCreatedFromFg() {
            return ((ServiceRecordProto) this.instance).getCreatedFromFg();
        }

        public Builder setCreatedFromFg(boolean value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setCreatedFromFg(value);
            return this;
        }

        public Builder clearCreatedFromFg() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearCreatedFromFg();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasStart() {
            return ((ServiceRecordProto) this.instance).hasStart();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Start getStart() {
            return ((ServiceRecordProto) this.instance).getStart();
        }

        public Builder setStart(Start value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setStart((ServiceRecordProto) value);
            return this;
        }

        public Builder setStart(Start.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setStart((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeStart(Start value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeStart(value);
            return this;
        }

        public Builder clearStart() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearStart();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasExecute() {
            return ((ServiceRecordProto) this.instance).hasExecute();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ExecuteNesting getExecute() {
            return ((ServiceRecordProto) this.instance).getExecute();
        }

        public Builder setExecute(ExecuteNesting value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setExecute((ServiceRecordProto) value);
            return this;
        }

        public Builder setExecute(ExecuteNesting.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setExecute((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeExecute(ExecuteNesting value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeExecute(value);
            return this;
        }

        public Builder clearExecute() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearExecute();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasDestoryTime() {
            return ((ServiceRecordProto) this.instance).hasDestoryTime();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Duration getDestoryTime() {
            return ((ServiceRecordProto) this.instance).getDestoryTime();
        }

        public Builder setDestoryTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setDestoryTime((ServiceRecordProto) value);
            return this;
        }

        public Builder setDestoryTime(Duration.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setDestoryTime((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeDestoryTime(Duration value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeDestoryTime(value);
            return this;
        }

        public Builder clearDestoryTime() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearDestoryTime();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public boolean hasCrash() {
            return ((ServiceRecordProto) this.instance).hasCrash();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public Crash getCrash() {
            return ((ServiceRecordProto) this.instance).getCrash();
        }

        public Builder setCrash(Crash value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setCrash((ServiceRecordProto) value);
            return this;
        }

        public Builder setCrash(Crash.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setCrash((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder mergeCrash(Crash value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).mergeCrash(value);
            return this;
        }

        public Builder clearCrash() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearCrash();
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public List<StartItem> getDeliveredStartsList() {
            return Collections.unmodifiableList(((ServiceRecordProto) this.instance).getDeliveredStartsList());
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public int getDeliveredStartsCount() {
            return ((ServiceRecordProto) this.instance).getDeliveredStartsCount();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public StartItem getDeliveredStarts(int index) {
            return ((ServiceRecordProto) this.instance).getDeliveredStarts(index);
        }

        public Builder setDeliveredStarts(int index, StartItem value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setDeliveredStarts((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder setDeliveredStarts(int index, StartItem.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setDeliveredStarts((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDeliveredStarts(StartItem value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addDeliveredStarts((ServiceRecordProto) value);
            return this;
        }

        public Builder addDeliveredStarts(int index, StartItem value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addDeliveredStarts((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder addDeliveredStarts(StartItem.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addDeliveredStarts((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder addDeliveredStarts(int index, StartItem.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addDeliveredStarts((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDeliveredStarts(Iterable<? extends StartItem> values) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addAllDeliveredStarts(values);
            return this;
        }

        public Builder clearDeliveredStarts() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearDeliveredStarts();
            return this;
        }

        public Builder removeDeliveredStarts(int index) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).removeDeliveredStarts(index);
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public List<StartItem> getPendingStartsList() {
            return Collections.unmodifiableList(((ServiceRecordProto) this.instance).getPendingStartsList());
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public int getPendingStartsCount() {
            return ((ServiceRecordProto) this.instance).getPendingStartsCount();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public StartItem getPendingStarts(int index) {
            return ((ServiceRecordProto) this.instance).getPendingStarts(index);
        }

        public Builder setPendingStarts(int index, StartItem value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setPendingStarts((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder setPendingStarts(int index, StartItem.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setPendingStarts((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPendingStarts(StartItem value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addPendingStarts((ServiceRecordProto) value);
            return this;
        }

        public Builder addPendingStarts(int index, StartItem value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addPendingStarts((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder addPendingStarts(StartItem.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addPendingStarts((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder addPendingStarts(int index, StartItem.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addPendingStarts((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPendingStarts(Iterable<? extends StartItem> values) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addAllPendingStarts(values);
            return this;
        }

        public Builder clearPendingStarts() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearPendingStarts();
            return this;
        }

        public Builder removePendingStarts(int index) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).removePendingStarts(index);
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public List<IntentBindRecordProto> getBindingsList() {
            return Collections.unmodifiableList(((ServiceRecordProto) this.instance).getBindingsList());
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public int getBindingsCount() {
            return ((ServiceRecordProto) this.instance).getBindingsCount();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public IntentBindRecordProto getBindings(int index) {
            return ((ServiceRecordProto) this.instance).getBindings(index);
        }

        public Builder setBindings(int index, IntentBindRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setBindings((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder setBindings(int index, IntentBindRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setBindings((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBindings(IntentBindRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addBindings((ServiceRecordProto) value);
            return this;
        }

        public Builder addBindings(int index, IntentBindRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addBindings((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder addBindings(IntentBindRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addBindings((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder addBindings(int index, IntentBindRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addBindings((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBindings(Iterable<? extends IntentBindRecordProto> values) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addAllBindings(values);
            return this;
        }

        public Builder clearBindings() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearBindings();
            return this;
        }

        public Builder removeBindings(int index) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).removeBindings(index);
            return this;
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public List<ConnectionRecordProto> getConnectionsList() {
            return Collections.unmodifiableList(((ServiceRecordProto) this.instance).getConnectionsList());
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public int getConnectionsCount() {
            return ((ServiceRecordProto) this.instance).getConnectionsCount();
        }

        @Override // com.android.server.am.ServiceRecordProtoOrBuilder
        public ConnectionRecordProto getConnections(int index) {
            return ((ServiceRecordProto) this.instance).getConnections(index);
        }

        public Builder setConnections(int index, ConnectionRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setConnections((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder setConnections(int index, ConnectionRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).setConnections((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addConnections(ConnectionRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addConnections((ServiceRecordProto) value);
            return this;
        }

        public Builder addConnections(int index, ConnectionRecordProto value) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addConnections((ServiceRecordProto) index, (int) value);
            return this;
        }

        public Builder addConnections(ConnectionRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addConnections((ServiceRecordProto) builderForValue);
            return this;
        }

        public Builder addConnections(int index, ConnectionRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addConnections((ServiceRecordProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllConnections(Iterable<? extends ConnectionRecordProto> values) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).addAllConnections(values);
            return this;
        }

        public Builder clearConnections() {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).clearConnections();
            return this;
        }

        public Builder removeConnections(int index) {
            copyOnWrite();
            ((ServiceRecordProto) this.instance).removeConnections(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ServiceRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.deliveredStarts_.makeImmutable();
                this.pendingStarts_.makeImmutable();
                this.bindings_.makeImmutable();
                this.connections_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ServiceRecordProto other = (ServiceRecordProto) arg1;
                this.shortName_ = visitor.visitString(hasShortName(), this.shortName_, other.hasShortName(), other.shortName_);
                this.isRunning_ = visitor.visitBoolean(hasIsRunning(), this.isRunning_, other.hasIsRunning(), other.isRunning_);
                this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                this.intent_ = (IntentProto) visitor.visitMessage(this.intent_, other.intent_);
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                this.processName_ = visitor.visitString(hasProcessName(), this.processName_, other.hasProcessName(), other.processName_);
                this.permission_ = visitor.visitString(hasPermission(), this.permission_, other.hasPermission(), other.permission_);
                this.appinfo_ = (AppInfo) visitor.visitMessage(this.appinfo_, other.appinfo_);
                this.app_ = (ProcessRecordProto) visitor.visitMessage(this.app_, other.app_);
                this.isolatedProc_ = (ProcessRecordProto) visitor.visitMessage(this.isolatedProc_, other.isolatedProc_);
                this.whitelistManager_ = visitor.visitBoolean(hasWhitelistManager(), this.whitelistManager_, other.hasWhitelistManager(), other.whitelistManager_);
                this.delayed_ = visitor.visitBoolean(hasDelayed(), this.delayed_, other.hasDelayed(), other.delayed_);
                this.foreground_ = (Foreground) visitor.visitMessage(this.foreground_, other.foreground_);
                this.createRealTime_ = (Duration) visitor.visitMessage(this.createRealTime_, other.createRealTime_);
                this.startingBgTimeout_ = (Duration) visitor.visitMessage(this.startingBgTimeout_, other.startingBgTimeout_);
                this.lastActivityTime_ = (Duration) visitor.visitMessage(this.lastActivityTime_, other.lastActivityTime_);
                this.restartTime_ = (Duration) visitor.visitMessage(this.restartTime_, other.restartTime_);
                this.createdFromFg_ = visitor.visitBoolean(hasCreatedFromFg(), this.createdFromFg_, other.hasCreatedFromFg(), other.createdFromFg_);
                this.start_ = (Start) visitor.visitMessage(this.start_, other.start_);
                this.execute_ = (ExecuteNesting) visitor.visitMessage(this.execute_, other.execute_);
                this.destoryTime_ = (Duration) visitor.visitMessage(this.destoryTime_, other.destoryTime_);
                this.crash_ = (Crash) visitor.visitMessage(this.crash_, other.crash_);
                this.deliveredStarts_ = visitor.visitList(this.deliveredStarts_, other.deliveredStarts_);
                this.pendingStarts_ = visitor.visitList(this.pendingStarts_, other.pendingStarts_);
                this.bindings_ = visitor.visitList(this.bindings_, other.bindings_);
                this.connections_ = visitor.visitList(this.connections_, other.connections_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.shortName_ = s;
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.isRunning_ = input.readBool();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.pid_ = input.readInt32();
                                break;
                            case 34:
                                IntentProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder = (IntentProto.Builder) this.intent_.toBuilder();
                                }
                                this.intent_ = (IntentProto) input.readMessage(IntentProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.intent_);
                                    this.intent_ = (IntentProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 42:
                                String s2 = input.readString();
                                this.bitField0_ |= 16;
                                this.packageName_ = s2;
                                break;
                            case 50:
                                String s3 = input.readString();
                                this.bitField0_ |= 32;
                                this.processName_ = s3;
                                break;
                            case 58:
                                String s4 = input.readString();
                                this.bitField0_ |= 64;
                                this.permission_ = s4;
                                break;
                            case 66:
                                AppInfo.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder2 = (AppInfo.Builder) this.appinfo_.toBuilder();
                                }
                                this.appinfo_ = (AppInfo) input.readMessage(AppInfo.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.appinfo_);
                                    this.appinfo_ = (AppInfo) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 74:
                                ProcessRecordProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder3 = (ProcessRecordProto.Builder) this.app_.toBuilder();
                                }
                                this.app_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.app_);
                                    this.app_ = (ProcessRecordProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                ProcessRecordProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder4 = (ProcessRecordProto.Builder) this.isolatedProc_.toBuilder();
                                }
                                this.isolatedProc_ = (ProcessRecordProto) input.readMessage(ProcessRecordProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.isolatedProc_);
                                    this.isolatedProc_ = (ProcessRecordProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.whitelistManager_ = input.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.delayed_ = input.readBool();
                                break;
                            case 106:
                                Foreground.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder5 = (Foreground.Builder) this.foreground_.toBuilder();
                                }
                                this.foreground_ = (Foreground) input.readMessage(Foreground.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.foreground_);
                                    this.foreground_ = (Foreground) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case 114:
                                Duration.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 8192) == 8192) {
                                    subBuilder6 = (Duration.Builder) this.createRealTime_.toBuilder();
                                }
                                this.createRealTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.createRealTime_);
                                    this.createRealTime_ = (Duration) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 8192;
                                break;
                            case 122:
                                Duration.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder7 = (Duration.Builder) this.startingBgTimeout_.toBuilder();
                                }
                                this.startingBgTimeout_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.startingBgTimeout_);
                                    this.startingBgTimeout_ = (Duration) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            case 130:
                                Duration.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 32768) == 32768) {
                                    subBuilder8 = (Duration.Builder) this.lastActivityTime_.toBuilder();
                                }
                                this.lastActivityTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.lastActivityTime_);
                                    this.lastActivityTime_ = (Duration) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 32768;
                                break;
                            case 138:
                                Duration.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 65536) == 65536) {
                                    subBuilder9 = (Duration.Builder) this.restartTime_.toBuilder();
                                }
                                this.restartTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.restartTime_);
                                    this.restartTime_ = (Duration) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 65536;
                                break;
                            case 144:
                                this.bitField0_ |= 131072;
                                this.createdFromFg_ = input.readBool();
                                break;
                            case 154:
                                Start.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 262144) == 262144) {
                                    subBuilder10 = (Start.Builder) this.start_.toBuilder();
                                }
                                this.start_ = (Start) input.readMessage(Start.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.start_);
                                    this.start_ = (Start) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 262144;
                                break;
                            case 162:
                                ExecuteNesting.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 524288) == 524288) {
                                    subBuilder11 = (ExecuteNesting.Builder) this.execute_.toBuilder();
                                }
                                this.execute_ = (ExecuteNesting) input.readMessage(ExecuteNesting.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.execute_);
                                    this.execute_ = (ExecuteNesting) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 524288;
                                break;
                            case 170:
                                Duration.Builder subBuilder12 = null;
                                if ((this.bitField0_ & 1048576) == 1048576) {
                                    subBuilder12 = (Duration.Builder) this.destoryTime_.toBuilder();
                                }
                                this.destoryTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder12 != null) {
                                    subBuilder12.mergeFrom((GeneratedMessageLite) this.destoryTime_);
                                    this.destoryTime_ = (Duration) subBuilder12.buildPartial();
                                }
                                this.bitField0_ |= 1048576;
                                break;
                            case 178:
                                Crash.Builder subBuilder13 = null;
                                if ((this.bitField0_ & 2097152) == 2097152) {
                                    subBuilder13 = (Crash.Builder) this.crash_.toBuilder();
                                }
                                this.crash_ = (Crash) input.readMessage(Crash.parser(), extensionRegistry);
                                if (subBuilder13 != null) {
                                    subBuilder13.mergeFrom((GeneratedMessageLite) this.crash_);
                                    this.crash_ = (Crash) subBuilder13.buildPartial();
                                }
                                this.bitField0_ |= 2097152;
                                break;
                            case AtomsProto.Atom.TOMB_STONE_OCCURRED_FIELD_NUMBER:
                                if (!this.deliveredStarts_.isModifiable()) {
                                    this.deliveredStarts_ = GeneratedMessageLite.mutableCopy(this.deliveredStarts_);
                                }
                                this.deliveredStarts_.add((StartItem) input.readMessage(StartItem.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER:
                                if (!this.pendingStarts_.isModifiable()) {
                                    this.pendingStarts_ = GeneratedMessageLite.mutableCopy(this.pendingStarts_);
                                }
                                this.pendingStarts_.add((StartItem) input.readMessage(StartItem.parser(), extensionRegistry));
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                if (!this.bindings_.isModifiable()) {
                                    this.bindings_ = GeneratedMessageLite.mutableCopy(this.bindings_);
                                }
                                this.bindings_.add((IntentBindRecordProto) input.readMessage(IntentBindRecordProto.parser(), extensionRegistry));
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER:
                                if (!this.connections_.isModifiable()) {
                                    this.connections_ = GeneratedMessageLite.mutableCopy(this.connections_);
                                }
                                this.connections_.add((ConnectionRecordProto) input.readMessage(ConnectionRecordProto.parser(), extensionRegistry));
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (ServiceRecordProto.class) {
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

    public static ServiceRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ServiceRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
