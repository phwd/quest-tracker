package com.android.server;

import com.android.server.StatLoggerProto;
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

public final class ForceAppStandbyTrackerProto extends GeneratedMessageLite<ForceAppStandbyTrackerProto, Builder> implements ForceAppStandbyTrackerProtoOrBuilder {
    public static final int ACTIVE_UIDS_FIELD_NUMBER = 2;
    private static final ForceAppStandbyTrackerProto DEFAULT_INSTANCE = new ForceAppStandbyTrackerProto();
    public static final int EXEMPTED_PACKAGES_FIELD_NUMBER = 10;
    public static final int FORCE_ALL_APPS_STANDBY_FIELD_NUMBER = 1;
    public static final int FORCE_ALL_APPS_STANDBY_FOR_SMALL_BATTERY_FIELD_NUMBER = 7;
    public static final int FOREGROUND_UIDS_FIELD_NUMBER = 11;
    public static final int IS_PLUGGED_IN_FIELD_NUMBER = 8;
    public static final int IS_SMALL_BATTERY_DEVICE_FIELD_NUMBER = 6;
    private static volatile Parser<ForceAppStandbyTrackerProto> PARSER = null;
    public static final int POWER_SAVE_USER_WHITELIST_APP_IDS_FIELD_NUMBER = 12;
    public static final int POWER_SAVE_WHITELIST_APP_IDS_FIELD_NUMBER = 3;
    public static final int RUN_ANY_IN_BACKGROUND_RESTRICTED_PACKAGES_FIELD_NUMBER = 5;
    public static final int STATS_FIELD_NUMBER = 9;
    public static final int TEMP_POWER_SAVE_WHITELIST_APP_IDS_FIELD_NUMBER = 4;
    private Internal.IntList activeUids_ = emptyIntList();
    private int bitField0_;
    private Internal.ProtobufList<ExemptedPackage> exemptedPackages_ = emptyProtobufList();
    private boolean forceAllAppsStandbyForSmallBattery_ = false;
    private boolean forceAllAppsStandby_ = false;
    private Internal.IntList foregroundUids_ = emptyIntList();
    private boolean isPluggedIn_ = false;
    private boolean isSmallBatteryDevice_ = false;
    private Internal.IntList powerSaveUserWhitelistAppIds_ = emptyIntList();
    private Internal.IntList powerSaveWhitelistAppIds_ = emptyIntList();
    private Internal.ProtobufList<RunAnyInBackgroundRestrictedPackages> runAnyInBackgroundRestrictedPackages_ = emptyProtobufList();
    private StatLoggerProto stats_;
    private Internal.IntList tempPowerSaveWhitelistAppIds_ = emptyIntList();

    public interface ExemptedPackageOrBuilder extends MessageLiteOrBuilder {
        String getPackageName();

        ByteString getPackageNameBytes();

        int getUserId();

        boolean hasPackageName();

        boolean hasUserId();
    }

    public interface RunAnyInBackgroundRestrictedPackagesOrBuilder extends MessageLiteOrBuilder {
        String getPackageName();

        ByteString getPackageNameBytes();

        int getUid();

        boolean hasPackageName();

        boolean hasUid();
    }

    private ForceAppStandbyTrackerProto() {
    }

    public static final class RunAnyInBackgroundRestrictedPackages extends GeneratedMessageLite<RunAnyInBackgroundRestrictedPackages, Builder> implements RunAnyInBackgroundRestrictedPackagesOrBuilder {
        private static final RunAnyInBackgroundRestrictedPackages DEFAULT_INSTANCE = new RunAnyInBackgroundRestrictedPackages();
        public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
        private static volatile Parser<RunAnyInBackgroundRestrictedPackages> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private String packageName_ = "";
        private int uid_ = 0;

        private RunAnyInBackgroundRestrictedPackages() {
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageName(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageName() {
            this.bitField0_ &= -3;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getPackageName());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getPackageName());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(InputStream input) throws IOException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RunAnyInBackgroundRestrictedPackages parseDelimitedFrom(InputStream input) throws IOException {
            return (RunAnyInBackgroundRestrictedPackages) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static RunAnyInBackgroundRestrictedPackages parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RunAnyInBackgroundRestrictedPackages) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(CodedInputStream input) throws IOException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RunAnyInBackgroundRestrictedPackages parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RunAnyInBackgroundRestrictedPackages) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RunAnyInBackgroundRestrictedPackages prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RunAnyInBackgroundRestrictedPackages, Builder> implements RunAnyInBackgroundRestrictedPackagesOrBuilder {
            private Builder() {
                super(RunAnyInBackgroundRestrictedPackages.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
            public boolean hasUid() {
                return ((RunAnyInBackgroundRestrictedPackages) this.instance).hasUid();
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
            public int getUid() {
                return ((RunAnyInBackgroundRestrictedPackages) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((RunAnyInBackgroundRestrictedPackages) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((RunAnyInBackgroundRestrictedPackages) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
            public boolean hasPackageName() {
                return ((RunAnyInBackgroundRestrictedPackages) this.instance).hasPackageName();
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
            public String getPackageName() {
                return ((RunAnyInBackgroundRestrictedPackages) this.instance).getPackageName();
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.RunAnyInBackgroundRestrictedPackagesOrBuilder
            public ByteString getPackageNameBytes() {
                return ((RunAnyInBackgroundRestrictedPackages) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String value) {
                copyOnWrite();
                ((RunAnyInBackgroundRestrictedPackages) this.instance).setPackageName(value);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((RunAnyInBackgroundRestrictedPackages) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString value) {
                copyOnWrite();
                ((RunAnyInBackgroundRestrictedPackages) this.instance).setPackageNameBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new RunAnyInBackgroundRestrictedPackages();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    RunAnyInBackgroundRestrictedPackages other = (RunAnyInBackgroundRestrictedPackages) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
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
                                this.uid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.packageName_ = s;
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
                        synchronized (RunAnyInBackgroundRestrictedPackages.class) {
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

        public static RunAnyInBackgroundRestrictedPackages getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RunAnyInBackgroundRestrictedPackages> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ExemptedPackage extends GeneratedMessageLite<ExemptedPackage, Builder> implements ExemptedPackageOrBuilder {
        private static final ExemptedPackage DEFAULT_INSTANCE = new ExemptedPackage();
        public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
        private static volatile Parser<ExemptedPackage> PARSER = null;
        public static final int USER_ID_FIELD_NUMBER = 1;
        private int bitField0_;
        private String packageName_ = "";
        private int userId_ = 0;

        private ExemptedPackage() {
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
        public boolean hasUserId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
        public int getUserId() {
            return this.userId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserId(int value) {
            this.bitField0_ |= 1;
            this.userId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUserId() {
            this.bitField0_ &= -2;
            this.userId_ = 0;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageName(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageName() {
            this.bitField0_ &= -3;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.userId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getPackageName());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getPackageName());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ExemptedPackage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ExemptedPackage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ExemptedPackage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ExemptedPackage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ExemptedPackage parseFrom(InputStream input) throws IOException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ExemptedPackage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ExemptedPackage parseDelimitedFrom(InputStream input) throws IOException {
            return (ExemptedPackage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ExemptedPackage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExemptedPackage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ExemptedPackage parseFrom(CodedInputStream input) throws IOException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ExemptedPackage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ExemptedPackage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExemptedPackage prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ExemptedPackage, Builder> implements ExemptedPackageOrBuilder {
            private Builder() {
                super(ExemptedPackage.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
            public boolean hasUserId() {
                return ((ExemptedPackage) this.instance).hasUserId();
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
            public int getUserId() {
                return ((ExemptedPackage) this.instance).getUserId();
            }

            public Builder setUserId(int value) {
                copyOnWrite();
                ((ExemptedPackage) this.instance).setUserId(value);
                return this;
            }

            public Builder clearUserId() {
                copyOnWrite();
                ((ExemptedPackage) this.instance).clearUserId();
                return this;
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
            public boolean hasPackageName() {
                return ((ExemptedPackage) this.instance).hasPackageName();
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
            public String getPackageName() {
                return ((ExemptedPackage) this.instance).getPackageName();
            }

            @Override // com.android.server.ForceAppStandbyTrackerProto.ExemptedPackageOrBuilder
            public ByteString getPackageNameBytes() {
                return ((ExemptedPackage) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String value) {
                copyOnWrite();
                ((ExemptedPackage) this.instance).setPackageName(value);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((ExemptedPackage) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString value) {
                copyOnWrite();
                ((ExemptedPackage) this.instance).setPackageNameBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ExemptedPackage();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ExemptedPackage other = (ExemptedPackage) arg1;
                    this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                    this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
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
                                this.userId_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.packageName_ = s;
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
                        synchronized (ExemptedPackage.class) {
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

        public static ExemptedPackage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExemptedPackage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean hasForceAllAppsStandby() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean getForceAllAppsStandby() {
        return this.forceAllAppsStandby_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForceAllAppsStandby(boolean value) {
        this.bitField0_ |= 1;
        this.forceAllAppsStandby_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForceAllAppsStandby() {
        this.bitField0_ &= -2;
        this.forceAllAppsStandby_ = false;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public List<Integer> getActiveUidsList() {
        return this.activeUids_;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getActiveUidsCount() {
        return this.activeUids_.size();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getActiveUids(int index) {
        return this.activeUids_.getInt(index);
    }

    private void ensureActiveUidsIsMutable() {
        if (!this.activeUids_.isModifiable()) {
            this.activeUids_ = GeneratedMessageLite.mutableCopy(this.activeUids_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveUids(int index, int value) {
        ensureActiveUidsIsMutable();
        this.activeUids_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveUids(int value) {
        ensureActiveUidsIsMutable();
        this.activeUids_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveUids(Iterable<? extends Integer> values) {
        ensureActiveUidsIsMutable();
        AbstractMessageLite.addAll(values, this.activeUids_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveUids() {
        this.activeUids_ = emptyIntList();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public List<Integer> getForegroundUidsList() {
        return this.foregroundUids_;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getForegroundUidsCount() {
        return this.foregroundUids_.size();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getForegroundUids(int index) {
        return this.foregroundUids_.getInt(index);
    }

    private void ensureForegroundUidsIsMutable() {
        if (!this.foregroundUids_.isModifiable()) {
            this.foregroundUids_ = GeneratedMessageLite.mutableCopy(this.foregroundUids_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForegroundUids(int index, int value) {
        ensureForegroundUidsIsMutable();
        this.foregroundUids_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addForegroundUids(int value) {
        ensureForegroundUidsIsMutable();
        this.foregroundUids_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllForegroundUids(Iterable<? extends Integer> values) {
        ensureForegroundUidsIsMutable();
        AbstractMessageLite.addAll(values, this.foregroundUids_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForegroundUids() {
        this.foregroundUids_ = emptyIntList();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public List<Integer> getPowerSaveWhitelistAppIdsList() {
        return this.powerSaveWhitelistAppIds_;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getPowerSaveWhitelistAppIdsCount() {
        return this.powerSaveWhitelistAppIds_.size();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getPowerSaveWhitelistAppIds(int index) {
        return this.powerSaveWhitelistAppIds_.getInt(index);
    }

    private void ensurePowerSaveWhitelistAppIdsIsMutable() {
        if (!this.powerSaveWhitelistAppIds_.isModifiable()) {
            this.powerSaveWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.powerSaveWhitelistAppIds_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerSaveWhitelistAppIds(int index, int value) {
        ensurePowerSaveWhitelistAppIdsIsMutable();
        this.powerSaveWhitelistAppIds_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPowerSaveWhitelistAppIds(int value) {
        ensurePowerSaveWhitelistAppIdsIsMutable();
        this.powerSaveWhitelistAppIds_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPowerSaveWhitelistAppIds(Iterable<? extends Integer> values) {
        ensurePowerSaveWhitelistAppIdsIsMutable();
        AbstractMessageLite.addAll(values, this.powerSaveWhitelistAppIds_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerSaveWhitelistAppIds() {
        this.powerSaveWhitelistAppIds_ = emptyIntList();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public List<Integer> getPowerSaveUserWhitelistAppIdsList() {
        return this.powerSaveUserWhitelistAppIds_;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getPowerSaveUserWhitelistAppIdsCount() {
        return this.powerSaveUserWhitelistAppIds_.size();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getPowerSaveUserWhitelistAppIds(int index) {
        return this.powerSaveUserWhitelistAppIds_.getInt(index);
    }

    private void ensurePowerSaveUserWhitelistAppIdsIsMutable() {
        if (!this.powerSaveUserWhitelistAppIds_.isModifiable()) {
            this.powerSaveUserWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.powerSaveUserWhitelistAppIds_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerSaveUserWhitelistAppIds(int index, int value) {
        ensurePowerSaveUserWhitelistAppIdsIsMutable();
        this.powerSaveUserWhitelistAppIds_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPowerSaveUserWhitelistAppIds(int value) {
        ensurePowerSaveUserWhitelistAppIdsIsMutable();
        this.powerSaveUserWhitelistAppIds_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPowerSaveUserWhitelistAppIds(Iterable<? extends Integer> values) {
        ensurePowerSaveUserWhitelistAppIdsIsMutable();
        AbstractMessageLite.addAll(values, this.powerSaveUserWhitelistAppIds_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerSaveUserWhitelistAppIds() {
        this.powerSaveUserWhitelistAppIds_ = emptyIntList();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public List<Integer> getTempPowerSaveWhitelistAppIdsList() {
        return this.tempPowerSaveWhitelistAppIds_;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getTempPowerSaveWhitelistAppIdsCount() {
        return this.tempPowerSaveWhitelistAppIds_.size();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getTempPowerSaveWhitelistAppIds(int index) {
        return this.tempPowerSaveWhitelistAppIds_.getInt(index);
    }

    private void ensureTempPowerSaveWhitelistAppIdsIsMutable() {
        if (!this.tempPowerSaveWhitelistAppIds_.isModifiable()) {
            this.tempPowerSaveWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.tempPowerSaveWhitelistAppIds_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTempPowerSaveWhitelistAppIds(int index, int value) {
        ensureTempPowerSaveWhitelistAppIdsIsMutable();
        this.tempPowerSaveWhitelistAppIds_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTempPowerSaveWhitelistAppIds(int value) {
        ensureTempPowerSaveWhitelistAppIdsIsMutable();
        this.tempPowerSaveWhitelistAppIds_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTempPowerSaveWhitelistAppIds(Iterable<? extends Integer> values) {
        ensureTempPowerSaveWhitelistAppIdsIsMutable();
        AbstractMessageLite.addAll(values, this.tempPowerSaveWhitelistAppIds_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTempPowerSaveWhitelistAppIds() {
        this.tempPowerSaveWhitelistAppIds_ = emptyIntList();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public List<RunAnyInBackgroundRestrictedPackages> getRunAnyInBackgroundRestrictedPackagesList() {
        return this.runAnyInBackgroundRestrictedPackages_;
    }

    public List<? extends RunAnyInBackgroundRestrictedPackagesOrBuilder> getRunAnyInBackgroundRestrictedPackagesOrBuilderList() {
        return this.runAnyInBackgroundRestrictedPackages_;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getRunAnyInBackgroundRestrictedPackagesCount() {
        return this.runAnyInBackgroundRestrictedPackages_.size();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public RunAnyInBackgroundRestrictedPackages getRunAnyInBackgroundRestrictedPackages(int index) {
        return this.runAnyInBackgroundRestrictedPackages_.get(index);
    }

    public RunAnyInBackgroundRestrictedPackagesOrBuilder getRunAnyInBackgroundRestrictedPackagesOrBuilder(int index) {
        return this.runAnyInBackgroundRestrictedPackages_.get(index);
    }

    private void ensureRunAnyInBackgroundRestrictedPackagesIsMutable() {
        if (!this.runAnyInBackgroundRestrictedPackages_.isModifiable()) {
            this.runAnyInBackgroundRestrictedPackages_ = GeneratedMessageLite.mutableCopy(this.runAnyInBackgroundRestrictedPackages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages value) {
        if (value != null) {
            ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
            this.runAnyInBackgroundRestrictedPackages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages.Builder builderForValue) {
        ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
        this.runAnyInBackgroundRestrictedPackages_.set(index, (RunAnyInBackgroundRestrictedPackages) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunAnyInBackgroundRestrictedPackages(RunAnyInBackgroundRestrictedPackages value) {
        if (value != null) {
            ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
            this.runAnyInBackgroundRestrictedPackages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages value) {
        if (value != null) {
            ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
            this.runAnyInBackgroundRestrictedPackages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunAnyInBackgroundRestrictedPackages(RunAnyInBackgroundRestrictedPackages.Builder builderForValue) {
        ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
        this.runAnyInBackgroundRestrictedPackages_.add((RunAnyInBackgroundRestrictedPackages) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages.Builder builderForValue) {
        ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
        this.runAnyInBackgroundRestrictedPackages_.add(index, (RunAnyInBackgroundRestrictedPackages) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRunAnyInBackgroundRestrictedPackages(Iterable<? extends RunAnyInBackgroundRestrictedPackages> values) {
        ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
        AbstractMessageLite.addAll(values, this.runAnyInBackgroundRestrictedPackages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRunAnyInBackgroundRestrictedPackages() {
        this.runAnyInBackgroundRestrictedPackages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRunAnyInBackgroundRestrictedPackages(int index) {
        ensureRunAnyInBackgroundRestrictedPackagesIsMutable();
        this.runAnyInBackgroundRestrictedPackages_.remove(index);
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean hasIsSmallBatteryDevice() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean getIsSmallBatteryDevice() {
        return this.isSmallBatteryDevice_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSmallBatteryDevice(boolean value) {
        this.bitField0_ |= 2;
        this.isSmallBatteryDevice_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSmallBatteryDevice() {
        this.bitField0_ &= -3;
        this.isSmallBatteryDevice_ = false;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean hasForceAllAppsStandbyForSmallBattery() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean getForceAllAppsStandbyForSmallBattery() {
        return this.forceAllAppsStandbyForSmallBattery_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setForceAllAppsStandbyForSmallBattery(boolean value) {
        this.bitField0_ |= 4;
        this.forceAllAppsStandbyForSmallBattery_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearForceAllAppsStandbyForSmallBattery() {
        this.bitField0_ &= -5;
        this.forceAllAppsStandbyForSmallBattery_ = false;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean hasIsPluggedIn() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean getIsPluggedIn() {
        return this.isPluggedIn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPluggedIn(boolean value) {
        this.bitField0_ |= 8;
        this.isPluggedIn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPluggedIn() {
        this.bitField0_ &= -9;
        this.isPluggedIn_ = false;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public boolean hasStats() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public StatLoggerProto getStats() {
        StatLoggerProto statLoggerProto = this.stats_;
        return statLoggerProto == null ? StatLoggerProto.getDefaultInstance() : statLoggerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStats(StatLoggerProto value) {
        if (value != null) {
            this.stats_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStats(StatLoggerProto.Builder builderForValue) {
        this.stats_ = (StatLoggerProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStats(StatLoggerProto value) {
        StatLoggerProto statLoggerProto = this.stats_;
        if (statLoggerProto == null || statLoggerProto == StatLoggerProto.getDefaultInstance()) {
            this.stats_ = value;
        } else {
            this.stats_ = (StatLoggerProto) ((StatLoggerProto.Builder) StatLoggerProto.newBuilder(this.stats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStats() {
        this.stats_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public List<ExemptedPackage> getExemptedPackagesList() {
        return this.exemptedPackages_;
    }

    public List<? extends ExemptedPackageOrBuilder> getExemptedPackagesOrBuilderList() {
        return this.exemptedPackages_;
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public int getExemptedPackagesCount() {
        return this.exemptedPackages_.size();
    }

    @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
    public ExemptedPackage getExemptedPackages(int index) {
        return this.exemptedPackages_.get(index);
    }

    public ExemptedPackageOrBuilder getExemptedPackagesOrBuilder(int index) {
        return this.exemptedPackages_.get(index);
    }

    private void ensureExemptedPackagesIsMutable() {
        if (!this.exemptedPackages_.isModifiable()) {
            this.exemptedPackages_ = GeneratedMessageLite.mutableCopy(this.exemptedPackages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExemptedPackages(int index, ExemptedPackage value) {
        if (value != null) {
            ensureExemptedPackagesIsMutable();
            this.exemptedPackages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExemptedPackages(int index, ExemptedPackage.Builder builderForValue) {
        ensureExemptedPackagesIsMutable();
        this.exemptedPackages_.set(index, (ExemptedPackage) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExemptedPackages(ExemptedPackage value) {
        if (value != null) {
            ensureExemptedPackagesIsMutable();
            this.exemptedPackages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExemptedPackages(int index, ExemptedPackage value) {
        if (value != null) {
            ensureExemptedPackagesIsMutable();
            this.exemptedPackages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExemptedPackages(ExemptedPackage.Builder builderForValue) {
        ensureExemptedPackagesIsMutable();
        this.exemptedPackages_.add((ExemptedPackage) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addExemptedPackages(int index, ExemptedPackage.Builder builderForValue) {
        ensureExemptedPackagesIsMutable();
        this.exemptedPackages_.add(index, (ExemptedPackage) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllExemptedPackages(Iterable<? extends ExemptedPackage> values) {
        ensureExemptedPackagesIsMutable();
        AbstractMessageLite.addAll(values, this.exemptedPackages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearExemptedPackages() {
        this.exemptedPackages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeExemptedPackages(int index) {
        ensureExemptedPackagesIsMutable();
        this.exemptedPackages_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.forceAllAppsStandby_);
        }
        for (int i = 0; i < this.activeUids_.size(); i++) {
            output.writeInt32(2, this.activeUids_.getInt(i));
        }
        for (int i2 = 0; i2 < this.powerSaveWhitelistAppIds_.size(); i2++) {
            output.writeInt32(3, this.powerSaveWhitelistAppIds_.getInt(i2));
        }
        for (int i3 = 0; i3 < this.tempPowerSaveWhitelistAppIds_.size(); i3++) {
            output.writeInt32(4, this.tempPowerSaveWhitelistAppIds_.getInt(i3));
        }
        for (int i4 = 0; i4 < this.runAnyInBackgroundRestrictedPackages_.size(); i4++) {
            output.writeMessage(5, this.runAnyInBackgroundRestrictedPackages_.get(i4));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(6, this.isSmallBatteryDevice_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(7, this.forceAllAppsStandbyForSmallBattery_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(8, this.isPluggedIn_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(9, getStats());
        }
        for (int i5 = 0; i5 < this.exemptedPackages_.size(); i5++) {
            output.writeMessage(10, this.exemptedPackages_.get(i5));
        }
        for (int i6 = 0; i6 < this.foregroundUids_.size(); i6++) {
            output.writeInt32(11, this.foregroundUids_.getInt(i6));
        }
        for (int i7 = 0; i7 < this.powerSaveUserWhitelistAppIds_.size(); i7++) {
            output.writeInt32(12, this.powerSaveUserWhitelistAppIds_.getInt(i7));
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.forceAllAppsStandby_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.activeUids_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.activeUids_.getInt(i));
        }
        int size3 = size2 + dataSize + (getActiveUidsList().size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.powerSaveWhitelistAppIds_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeInt32SizeNoTag(this.powerSaveWhitelistAppIds_.getInt(i2));
        }
        int size4 = size3 + dataSize2 + (getPowerSaveWhitelistAppIdsList().size() * 1);
        int dataSize3 = 0;
        for (int i3 = 0; i3 < this.tempPowerSaveWhitelistAppIds_.size(); i3++) {
            dataSize3 += CodedOutputStream.computeInt32SizeNoTag(this.tempPowerSaveWhitelistAppIds_.getInt(i3));
        }
        int size5 = size4 + dataSize3 + (getTempPowerSaveWhitelistAppIdsList().size() * 1);
        for (int i4 = 0; i4 < this.runAnyInBackgroundRestrictedPackages_.size(); i4++) {
            size5 += CodedOutputStream.computeMessageSize(5, this.runAnyInBackgroundRestrictedPackages_.get(i4));
        }
        if ((this.bitField0_ & 2) == 2) {
            size5 += CodedOutputStream.computeBoolSize(6, this.isSmallBatteryDevice_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size5 += CodedOutputStream.computeBoolSize(7, this.forceAllAppsStandbyForSmallBattery_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size5 += CodedOutputStream.computeBoolSize(8, this.isPluggedIn_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size5 += CodedOutputStream.computeMessageSize(9, getStats());
        }
        for (int i5 = 0; i5 < this.exemptedPackages_.size(); i5++) {
            size5 += CodedOutputStream.computeMessageSize(10, this.exemptedPackages_.get(i5));
        }
        int dataSize4 = 0;
        for (int i6 = 0; i6 < this.foregroundUids_.size(); i6++) {
            dataSize4 += CodedOutputStream.computeInt32SizeNoTag(this.foregroundUids_.getInt(i6));
        }
        int size6 = size5 + dataSize4 + (getForegroundUidsList().size() * 1);
        int dataSize5 = 0;
        for (int i7 = 0; i7 < this.powerSaveUserWhitelistAppIds_.size(); i7++) {
            dataSize5 += CodedOutputStream.computeInt32SizeNoTag(this.powerSaveUserWhitelistAppIds_.getInt(i7));
        }
        int size7 = size6 + dataSize5 + (getPowerSaveUserWhitelistAppIdsList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size7;
        return size7;
    }

    public static ForceAppStandbyTrackerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ForceAppStandbyTrackerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ForceAppStandbyTrackerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ForceAppStandbyTrackerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ForceAppStandbyTrackerProto parseFrom(InputStream input) throws IOException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ForceAppStandbyTrackerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ForceAppStandbyTrackerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ForceAppStandbyTrackerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ForceAppStandbyTrackerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ForceAppStandbyTrackerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ForceAppStandbyTrackerProto parseFrom(CodedInputStream input) throws IOException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ForceAppStandbyTrackerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ForceAppStandbyTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ForceAppStandbyTrackerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ForceAppStandbyTrackerProto, Builder> implements ForceAppStandbyTrackerProtoOrBuilder {
        private Builder() {
            super(ForceAppStandbyTrackerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean hasForceAllAppsStandby() {
            return ((ForceAppStandbyTrackerProto) this.instance).hasForceAllAppsStandby();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean getForceAllAppsStandby() {
            return ((ForceAppStandbyTrackerProto) this.instance).getForceAllAppsStandby();
        }

        public Builder setForceAllAppsStandby(boolean value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setForceAllAppsStandby(value);
            return this;
        }

        public Builder clearForceAllAppsStandby() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearForceAllAppsStandby();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public List<Integer> getActiveUidsList() {
            return Collections.unmodifiableList(((ForceAppStandbyTrackerProto) this.instance).getActiveUidsList());
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getActiveUidsCount() {
            return ((ForceAppStandbyTrackerProto) this.instance).getActiveUidsCount();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getActiveUids(int index) {
            return ((ForceAppStandbyTrackerProto) this.instance).getActiveUids(index);
        }

        public Builder setActiveUids(int index, int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setActiveUids(index, value);
            return this;
        }

        public Builder addActiveUids(int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addActiveUids(value);
            return this;
        }

        public Builder addAllActiveUids(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addAllActiveUids(values);
            return this;
        }

        public Builder clearActiveUids() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearActiveUids();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public List<Integer> getForegroundUidsList() {
            return Collections.unmodifiableList(((ForceAppStandbyTrackerProto) this.instance).getForegroundUidsList());
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getForegroundUidsCount() {
            return ((ForceAppStandbyTrackerProto) this.instance).getForegroundUidsCount();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getForegroundUids(int index) {
            return ((ForceAppStandbyTrackerProto) this.instance).getForegroundUids(index);
        }

        public Builder setForegroundUids(int index, int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setForegroundUids(index, value);
            return this;
        }

        public Builder addForegroundUids(int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addForegroundUids(value);
            return this;
        }

        public Builder addAllForegroundUids(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addAllForegroundUids(values);
            return this;
        }

        public Builder clearForegroundUids() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearForegroundUids();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public List<Integer> getPowerSaveWhitelistAppIdsList() {
            return Collections.unmodifiableList(((ForceAppStandbyTrackerProto) this.instance).getPowerSaveWhitelistAppIdsList());
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getPowerSaveWhitelistAppIdsCount() {
            return ((ForceAppStandbyTrackerProto) this.instance).getPowerSaveWhitelistAppIdsCount();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getPowerSaveWhitelistAppIds(int index) {
            return ((ForceAppStandbyTrackerProto) this.instance).getPowerSaveWhitelistAppIds(index);
        }

        public Builder setPowerSaveWhitelistAppIds(int index, int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setPowerSaveWhitelistAppIds(index, value);
            return this;
        }

        public Builder addPowerSaveWhitelistAppIds(int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addPowerSaveWhitelistAppIds(value);
            return this;
        }

        public Builder addAllPowerSaveWhitelistAppIds(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addAllPowerSaveWhitelistAppIds(values);
            return this;
        }

        public Builder clearPowerSaveWhitelistAppIds() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearPowerSaveWhitelistAppIds();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public List<Integer> getPowerSaveUserWhitelistAppIdsList() {
            return Collections.unmodifiableList(((ForceAppStandbyTrackerProto) this.instance).getPowerSaveUserWhitelistAppIdsList());
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getPowerSaveUserWhitelistAppIdsCount() {
            return ((ForceAppStandbyTrackerProto) this.instance).getPowerSaveUserWhitelistAppIdsCount();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getPowerSaveUserWhitelistAppIds(int index) {
            return ((ForceAppStandbyTrackerProto) this.instance).getPowerSaveUserWhitelistAppIds(index);
        }

        public Builder setPowerSaveUserWhitelistAppIds(int index, int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setPowerSaveUserWhitelistAppIds(index, value);
            return this;
        }

        public Builder addPowerSaveUserWhitelistAppIds(int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addPowerSaveUserWhitelistAppIds(value);
            return this;
        }

        public Builder addAllPowerSaveUserWhitelistAppIds(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addAllPowerSaveUserWhitelistAppIds(values);
            return this;
        }

        public Builder clearPowerSaveUserWhitelistAppIds() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearPowerSaveUserWhitelistAppIds();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public List<Integer> getTempPowerSaveWhitelistAppIdsList() {
            return Collections.unmodifiableList(((ForceAppStandbyTrackerProto) this.instance).getTempPowerSaveWhitelistAppIdsList());
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getTempPowerSaveWhitelistAppIdsCount() {
            return ((ForceAppStandbyTrackerProto) this.instance).getTempPowerSaveWhitelistAppIdsCount();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getTempPowerSaveWhitelistAppIds(int index) {
            return ((ForceAppStandbyTrackerProto) this.instance).getTempPowerSaveWhitelistAppIds(index);
        }

        public Builder setTempPowerSaveWhitelistAppIds(int index, int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setTempPowerSaveWhitelistAppIds(index, value);
            return this;
        }

        public Builder addTempPowerSaveWhitelistAppIds(int value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addTempPowerSaveWhitelistAppIds(value);
            return this;
        }

        public Builder addAllTempPowerSaveWhitelistAppIds(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addAllTempPowerSaveWhitelistAppIds(values);
            return this;
        }

        public Builder clearTempPowerSaveWhitelistAppIds() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearTempPowerSaveWhitelistAppIds();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public List<RunAnyInBackgroundRestrictedPackages> getRunAnyInBackgroundRestrictedPackagesList() {
            return Collections.unmodifiableList(((ForceAppStandbyTrackerProto) this.instance).getRunAnyInBackgroundRestrictedPackagesList());
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getRunAnyInBackgroundRestrictedPackagesCount() {
            return ((ForceAppStandbyTrackerProto) this.instance).getRunAnyInBackgroundRestrictedPackagesCount();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public RunAnyInBackgroundRestrictedPackages getRunAnyInBackgroundRestrictedPackages(int index) {
            return ((ForceAppStandbyTrackerProto) this.instance).getRunAnyInBackgroundRestrictedPackages(index);
        }

        public Builder setRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setRunAnyInBackgroundRestrictedPackages((ForceAppStandbyTrackerProto) index, (int) value);
            return this;
        }

        public Builder setRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages.Builder builderForValue) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setRunAnyInBackgroundRestrictedPackages((ForceAppStandbyTrackerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRunAnyInBackgroundRestrictedPackages(RunAnyInBackgroundRestrictedPackages value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addRunAnyInBackgroundRestrictedPackages((ForceAppStandbyTrackerProto) value);
            return this;
        }

        public Builder addRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addRunAnyInBackgroundRestrictedPackages((ForceAppStandbyTrackerProto) index, (int) value);
            return this;
        }

        public Builder addRunAnyInBackgroundRestrictedPackages(RunAnyInBackgroundRestrictedPackages.Builder builderForValue) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addRunAnyInBackgroundRestrictedPackages((ForceAppStandbyTrackerProto) builderForValue);
            return this;
        }

        public Builder addRunAnyInBackgroundRestrictedPackages(int index, RunAnyInBackgroundRestrictedPackages.Builder builderForValue) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addRunAnyInBackgroundRestrictedPackages((ForceAppStandbyTrackerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRunAnyInBackgroundRestrictedPackages(Iterable<? extends RunAnyInBackgroundRestrictedPackages> values) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addAllRunAnyInBackgroundRestrictedPackages(values);
            return this;
        }

        public Builder clearRunAnyInBackgroundRestrictedPackages() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearRunAnyInBackgroundRestrictedPackages();
            return this;
        }

        public Builder removeRunAnyInBackgroundRestrictedPackages(int index) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).removeRunAnyInBackgroundRestrictedPackages(index);
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean hasIsSmallBatteryDevice() {
            return ((ForceAppStandbyTrackerProto) this.instance).hasIsSmallBatteryDevice();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean getIsSmallBatteryDevice() {
            return ((ForceAppStandbyTrackerProto) this.instance).getIsSmallBatteryDevice();
        }

        public Builder setIsSmallBatteryDevice(boolean value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setIsSmallBatteryDevice(value);
            return this;
        }

        public Builder clearIsSmallBatteryDevice() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearIsSmallBatteryDevice();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean hasForceAllAppsStandbyForSmallBattery() {
            return ((ForceAppStandbyTrackerProto) this.instance).hasForceAllAppsStandbyForSmallBattery();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean getForceAllAppsStandbyForSmallBattery() {
            return ((ForceAppStandbyTrackerProto) this.instance).getForceAllAppsStandbyForSmallBattery();
        }

        public Builder setForceAllAppsStandbyForSmallBattery(boolean value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setForceAllAppsStandbyForSmallBattery(value);
            return this;
        }

        public Builder clearForceAllAppsStandbyForSmallBattery() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearForceAllAppsStandbyForSmallBattery();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean hasIsPluggedIn() {
            return ((ForceAppStandbyTrackerProto) this.instance).hasIsPluggedIn();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean getIsPluggedIn() {
            return ((ForceAppStandbyTrackerProto) this.instance).getIsPluggedIn();
        }

        public Builder setIsPluggedIn(boolean value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setIsPluggedIn(value);
            return this;
        }

        public Builder clearIsPluggedIn() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearIsPluggedIn();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public boolean hasStats() {
            return ((ForceAppStandbyTrackerProto) this.instance).hasStats();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public StatLoggerProto getStats() {
            return ((ForceAppStandbyTrackerProto) this.instance).getStats();
        }

        public Builder setStats(StatLoggerProto value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setStats((ForceAppStandbyTrackerProto) value);
            return this;
        }

        public Builder setStats(StatLoggerProto.Builder builderForValue) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setStats((ForceAppStandbyTrackerProto) builderForValue);
            return this;
        }

        public Builder mergeStats(StatLoggerProto value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).mergeStats(value);
            return this;
        }

        public Builder clearStats() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearStats();
            return this;
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public List<ExemptedPackage> getExemptedPackagesList() {
            return Collections.unmodifiableList(((ForceAppStandbyTrackerProto) this.instance).getExemptedPackagesList());
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public int getExemptedPackagesCount() {
            return ((ForceAppStandbyTrackerProto) this.instance).getExemptedPackagesCount();
        }

        @Override // com.android.server.ForceAppStandbyTrackerProtoOrBuilder
        public ExemptedPackage getExemptedPackages(int index) {
            return ((ForceAppStandbyTrackerProto) this.instance).getExemptedPackages(index);
        }

        public Builder setExemptedPackages(int index, ExemptedPackage value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setExemptedPackages((ForceAppStandbyTrackerProto) index, (int) value);
            return this;
        }

        public Builder setExemptedPackages(int index, ExemptedPackage.Builder builderForValue) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).setExemptedPackages((ForceAppStandbyTrackerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addExemptedPackages(ExemptedPackage value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addExemptedPackages((ForceAppStandbyTrackerProto) value);
            return this;
        }

        public Builder addExemptedPackages(int index, ExemptedPackage value) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addExemptedPackages((ForceAppStandbyTrackerProto) index, (int) value);
            return this;
        }

        public Builder addExemptedPackages(ExemptedPackage.Builder builderForValue) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addExemptedPackages((ForceAppStandbyTrackerProto) builderForValue);
            return this;
        }

        public Builder addExemptedPackages(int index, ExemptedPackage.Builder builderForValue) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addExemptedPackages((ForceAppStandbyTrackerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllExemptedPackages(Iterable<? extends ExemptedPackage> values) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).addAllExemptedPackages(values);
            return this;
        }

        public Builder clearExemptedPackages() {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).clearExemptedPackages();
            return this;
        }

        public Builder removeExemptedPackages(int index) {
            copyOnWrite();
            ((ForceAppStandbyTrackerProto) this.instance).removeExemptedPackages(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ForceAppStandbyTrackerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.activeUids_.makeImmutable();
                this.foregroundUids_.makeImmutable();
                this.powerSaveWhitelistAppIds_.makeImmutable();
                this.powerSaveUserWhitelistAppIds_.makeImmutable();
                this.tempPowerSaveWhitelistAppIds_.makeImmutable();
                this.runAnyInBackgroundRestrictedPackages_.makeImmutable();
                this.exemptedPackages_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ForceAppStandbyTrackerProto other = (ForceAppStandbyTrackerProto) arg1;
                this.forceAllAppsStandby_ = visitor.visitBoolean(hasForceAllAppsStandby(), this.forceAllAppsStandby_, other.hasForceAllAppsStandby(), other.forceAllAppsStandby_);
                this.activeUids_ = visitor.visitIntList(this.activeUids_, other.activeUids_);
                this.foregroundUids_ = visitor.visitIntList(this.foregroundUids_, other.foregroundUids_);
                this.powerSaveWhitelistAppIds_ = visitor.visitIntList(this.powerSaveWhitelistAppIds_, other.powerSaveWhitelistAppIds_);
                this.powerSaveUserWhitelistAppIds_ = visitor.visitIntList(this.powerSaveUserWhitelistAppIds_, other.powerSaveUserWhitelistAppIds_);
                this.tempPowerSaveWhitelistAppIds_ = visitor.visitIntList(this.tempPowerSaveWhitelistAppIds_, other.tempPowerSaveWhitelistAppIds_);
                this.runAnyInBackgroundRestrictedPackages_ = visitor.visitList(this.runAnyInBackgroundRestrictedPackages_, other.runAnyInBackgroundRestrictedPackages_);
                this.isSmallBatteryDevice_ = visitor.visitBoolean(hasIsSmallBatteryDevice(), this.isSmallBatteryDevice_, other.hasIsSmallBatteryDevice(), other.isSmallBatteryDevice_);
                this.forceAllAppsStandbyForSmallBattery_ = visitor.visitBoolean(hasForceAllAppsStandbyForSmallBattery(), this.forceAllAppsStandbyForSmallBattery_, other.hasForceAllAppsStandbyForSmallBattery(), other.forceAllAppsStandbyForSmallBattery_);
                this.isPluggedIn_ = visitor.visitBoolean(hasIsPluggedIn(), this.isPluggedIn_, other.hasIsPluggedIn(), other.isPluggedIn_);
                this.stats_ = (StatLoggerProto) visitor.visitMessage(this.stats_, other.stats_);
                this.exemptedPackages_ = visitor.visitList(this.exemptedPackages_, other.exemptedPackages_);
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
                            case 8:
                                this.bitField0_ |= 1;
                                this.forceAllAppsStandby_ = input.readBool();
                                break;
                            case 16:
                                if (!this.activeUids_.isModifiable()) {
                                    this.activeUids_ = GeneratedMessageLite.mutableCopy(this.activeUids_);
                                }
                                this.activeUids_.addInt(input.readInt32());
                                break;
                            case 18:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.activeUids_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.activeUids_ = GeneratedMessageLite.mutableCopy(this.activeUids_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.activeUids_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                                break;
                            case 24:
                                if (!this.powerSaveWhitelistAppIds_.isModifiable()) {
                                    this.powerSaveWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.powerSaveWhitelistAppIds_);
                                }
                                this.powerSaveWhitelistAppIds_.addInt(input.readInt32());
                                break;
                            case 26:
                                int limit2 = input.pushLimit(input.readRawVarint32());
                                if (!this.powerSaveWhitelistAppIds_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.powerSaveWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.powerSaveWhitelistAppIds_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.powerSaveWhitelistAppIds_.addInt(input.readInt32());
                                }
                                input.popLimit(limit2);
                                break;
                            case 32:
                                if (!this.tempPowerSaveWhitelistAppIds_.isModifiable()) {
                                    this.tempPowerSaveWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.tempPowerSaveWhitelistAppIds_);
                                }
                                this.tempPowerSaveWhitelistAppIds_.addInt(input.readInt32());
                                break;
                            case 34:
                                int limit3 = input.pushLimit(input.readRawVarint32());
                                if (!this.tempPowerSaveWhitelistAppIds_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.tempPowerSaveWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.tempPowerSaveWhitelistAppIds_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.tempPowerSaveWhitelistAppIds_.addInt(input.readInt32());
                                }
                                input.popLimit(limit3);
                                break;
                            case 42:
                                if (!this.runAnyInBackgroundRestrictedPackages_.isModifiable()) {
                                    this.runAnyInBackgroundRestrictedPackages_ = GeneratedMessageLite.mutableCopy(this.runAnyInBackgroundRestrictedPackages_);
                                }
                                this.runAnyInBackgroundRestrictedPackages_.add((RunAnyInBackgroundRestrictedPackages) input.readMessage(RunAnyInBackgroundRestrictedPackages.parser(), extensionRegistry));
                                break;
                            case 48:
                                this.bitField0_ |= 2;
                                this.isSmallBatteryDevice_ = input.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 4;
                                this.forceAllAppsStandbyForSmallBattery_ = input.readBool();
                                break;
                            case 64:
                                this.bitField0_ |= 8;
                                this.isPluggedIn_ = input.readBool();
                                break;
                            case 74:
                                StatLoggerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder = (StatLoggerProto.Builder) this.stats_.toBuilder();
                                }
                                this.stats_ = (StatLoggerProto) input.readMessage(StatLoggerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.stats_);
                                    this.stats_ = (StatLoggerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 82:
                                if (!this.exemptedPackages_.isModifiable()) {
                                    this.exemptedPackages_ = GeneratedMessageLite.mutableCopy(this.exemptedPackages_);
                                }
                                this.exemptedPackages_.add((ExemptedPackage) input.readMessage(ExemptedPackage.parser(), extensionRegistry));
                                break;
                            case 88:
                                if (!this.foregroundUids_.isModifiable()) {
                                    this.foregroundUids_ = GeneratedMessageLite.mutableCopy(this.foregroundUids_);
                                }
                                this.foregroundUids_.addInt(input.readInt32());
                                break;
                            case 90:
                                int limit4 = input.pushLimit(input.readRawVarint32());
                                if (!this.foregroundUids_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.foregroundUids_ = GeneratedMessageLite.mutableCopy(this.foregroundUids_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.foregroundUids_.addInt(input.readInt32());
                                }
                                input.popLimit(limit4);
                                break;
                            case 96:
                                if (!this.powerSaveUserWhitelistAppIds_.isModifiable()) {
                                    this.powerSaveUserWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.powerSaveUserWhitelistAppIds_);
                                }
                                this.powerSaveUserWhitelistAppIds_.addInt(input.readInt32());
                                break;
                            case 98:
                                int limit5 = input.pushLimit(input.readRawVarint32());
                                if (!this.powerSaveUserWhitelistAppIds_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.powerSaveUserWhitelistAppIds_ = GeneratedMessageLite.mutableCopy(this.powerSaveUserWhitelistAppIds_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.powerSaveUserWhitelistAppIds_.addInt(input.readInt32());
                                }
                                input.popLimit(limit5);
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
                    synchronized (ForceAppStandbyTrackerProto.class) {
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

    public static ForceAppStandbyTrackerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ForceAppStandbyTrackerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
