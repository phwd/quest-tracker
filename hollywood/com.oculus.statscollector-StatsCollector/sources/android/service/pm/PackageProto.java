package android.service.pm;

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

public final class PackageProto extends GeneratedMessageLite<PackageProto, Builder> implements PackageProtoOrBuilder {
    private static final PackageProto DEFAULT_INSTANCE = new PackageProto();
    public static final int INSTALLER_NAME_FIELD_NUMBER = 7;
    public static final int INSTALL_TIME_MS_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<PackageProto> PARSER = null;
    public static final int SPLITS_FIELD_NUMBER = 8;
    public static final int UID_FIELD_NUMBER = 2;
    public static final int UPDATE_TIME_MS_FIELD_NUMBER = 6;
    public static final int USERS_FIELD_NUMBER = 9;
    public static final int VERSION_CODE_FIELD_NUMBER = 3;
    public static final int VERSION_STRING_FIELD_NUMBER = 4;
    private int bitField0_;
    private long installTimeMs_ = 0;
    private String installerName_ = "";
    private String name_ = "";
    private Internal.ProtobufList<SplitProto> splits_ = emptyProtobufList();
    private int uid_ = 0;
    private long updateTimeMs_ = 0;
    private Internal.ProtobufList<UserInfoProto> users_ = emptyProtobufList();
    private int versionCode_ = 0;
    private String versionString_ = "";

    public interface SplitProtoOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        int getRevisionCode();

        boolean hasName();

        boolean hasRevisionCode();
    }

    public interface UserInfoProtoOrBuilder extends MessageLiteOrBuilder {
        int getDistractionFlags();

        UserInfoProto.EnabledState getEnabledState();

        int getId();

        UserInfoProto.InstallType getInstallType();

        boolean getIsHidden();

        boolean getIsLaunched();

        boolean getIsStopped();

        boolean getIsSuspended();

        String getLastDisabledAppCaller();

        ByteString getLastDisabledAppCallerBytes();

        String getSuspendingPackage();

        ByteString getSuspendingPackageBytes();

        boolean hasDistractionFlags();

        boolean hasEnabledState();

        boolean hasId();

        boolean hasInstallType();

        boolean hasIsHidden();

        boolean hasIsLaunched();

        boolean hasIsStopped();

        boolean hasIsSuspended();

        boolean hasLastDisabledAppCaller();

        boolean hasSuspendingPackage();
    }

    private PackageProto() {
    }

    public static final class SplitProto extends GeneratedMessageLite<SplitProto, Builder> implements SplitProtoOrBuilder {
        private static final SplitProto DEFAULT_INSTANCE = new SplitProto();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<SplitProto> PARSER = null;
        public static final int REVISION_CODE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private int revisionCode_ = 0;

        private SplitProto() {
        }

        @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.name_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            this.bitField0_ &= -2;
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.name_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
        public boolean hasRevisionCode() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
        public int getRevisionCode() {
            return this.revisionCode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRevisionCode(int value) {
            this.bitField0_ |= 2;
            this.revisionCode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRevisionCode() {
            this.bitField0_ &= -3;
            this.revisionCode_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.revisionCode_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.revisionCode_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static SplitProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SplitProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SplitProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SplitProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SplitProto parseFrom(InputStream input) throws IOException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SplitProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SplitProto parseDelimitedFrom(InputStream input) throws IOException {
            return (SplitProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SplitProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SplitProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SplitProto parseFrom(CodedInputStream input) throws IOException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SplitProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SplitProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SplitProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SplitProto, Builder> implements SplitProtoOrBuilder {
            private Builder() {
                super(SplitProto.DEFAULT_INSTANCE);
            }

            @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
            public boolean hasName() {
                return ((SplitProto) this.instance).hasName();
            }

            @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
            public String getName() {
                return ((SplitProto) this.instance).getName();
            }

            @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
            public ByteString getNameBytes() {
                return ((SplitProto) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((SplitProto) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((SplitProto) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((SplitProto) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
            public boolean hasRevisionCode() {
                return ((SplitProto) this.instance).hasRevisionCode();
            }

            @Override // android.service.pm.PackageProto.SplitProtoOrBuilder
            public int getRevisionCode() {
                return ((SplitProto) this.instance).getRevisionCode();
            }

            public Builder setRevisionCode(int value) {
                copyOnWrite();
                ((SplitProto) this.instance).setRevisionCode(value);
                return this;
            }

            public Builder clearRevisionCode() {
                copyOnWrite();
                ((SplitProto) this.instance).clearRevisionCode();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SplitProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SplitProto other = (SplitProto) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.revisionCode_ = visitor.visitInt(hasRevisionCode(), this.revisionCode_, other.hasRevisionCode(), other.revisionCode_);
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
                                this.name_ = s;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.revisionCode_ = input.readInt32();
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
                        synchronized (SplitProto.class) {
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

        public static SplitProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SplitProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class UserInfoProto extends GeneratedMessageLite<UserInfoProto, Builder> implements UserInfoProtoOrBuilder {
        private static final UserInfoProto DEFAULT_INSTANCE = new UserInfoProto();
        public static final int DISTRACTION_FLAGS_FIELD_NUMBER = 10;
        public static final int ENABLED_STATE_FIELD_NUMBER = 7;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int INSTALL_TYPE_FIELD_NUMBER = 2;
        public static final int IS_HIDDEN_FIELD_NUMBER = 3;
        public static final int IS_LAUNCHED_FIELD_NUMBER = 6;
        public static final int IS_STOPPED_FIELD_NUMBER = 5;
        public static final int IS_SUSPENDED_FIELD_NUMBER = 4;
        public static final int LAST_DISABLED_APP_CALLER_FIELD_NUMBER = 8;
        private static volatile Parser<UserInfoProto> PARSER = null;
        public static final int SUSPENDING_PACKAGE_FIELD_NUMBER = 9;
        private int bitField0_;
        private int distractionFlags_ = 0;
        private int enabledState_ = 0;
        private int id_ = 0;
        private int installType_ = 0;
        private boolean isHidden_ = false;
        private boolean isLaunched_ = false;
        private boolean isStopped_ = false;
        private boolean isSuspended_ = false;
        private String lastDisabledAppCaller_ = "";
        private String suspendingPackage_ = "";

        private UserInfoProto() {
        }

        public enum InstallType implements Internal.EnumLite {
            NOT_INSTALLED_FOR_USER(0),
            FULL_APP_INSTALL(1),
            INSTANT_APP_INSTALL(2);
            
            public static final int FULL_APP_INSTALL_VALUE = 1;
            public static final int INSTANT_APP_INSTALL_VALUE = 2;
            public static final int NOT_INSTALLED_FOR_USER_VALUE = 0;
            private static final Internal.EnumLiteMap<InstallType> internalValueMap = new Internal.EnumLiteMap<InstallType>() {
                /* class android.service.pm.PackageProto.UserInfoProto.InstallType.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public InstallType findValueByNumber(int number) {
                    return InstallType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static InstallType valueOf(int value2) {
                return forNumber(value2);
            }

            public static InstallType forNumber(int value2) {
                if (value2 == 0) {
                    return NOT_INSTALLED_FOR_USER;
                }
                if (value2 == 1) {
                    return FULL_APP_INSTALL;
                }
                if (value2 != 2) {
                    return null;
                }
                return INSTANT_APP_INSTALL;
            }

            public static Internal.EnumLiteMap<InstallType> internalGetValueMap() {
                return internalValueMap;
            }

            private InstallType(int value2) {
                this.value = value2;
            }
        }

        public enum EnabledState implements Internal.EnumLite {
            COMPONENT_ENABLED_STATE_DEFAULT(0),
            COMPONENT_ENABLED_STATE_ENABLED(1),
            COMPONENT_ENABLED_STATE_DISABLED(2),
            COMPONENT_ENABLED_STATE_DISABLED_USER(3),
            COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED(4);
            
            public static final int COMPONENT_ENABLED_STATE_DEFAULT_VALUE = 0;
            public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED_VALUE = 4;
            public static final int COMPONENT_ENABLED_STATE_DISABLED_USER_VALUE = 3;
            public static final int COMPONENT_ENABLED_STATE_DISABLED_VALUE = 2;
            public static final int COMPONENT_ENABLED_STATE_ENABLED_VALUE = 1;
            private static final Internal.EnumLiteMap<EnabledState> internalValueMap = new Internal.EnumLiteMap<EnabledState>() {
                /* class android.service.pm.PackageProto.UserInfoProto.EnabledState.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public EnabledState findValueByNumber(int number) {
                    return EnabledState.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static EnabledState valueOf(int value2) {
                return forNumber(value2);
            }

            public static EnabledState forNumber(int value2) {
                if (value2 == 0) {
                    return COMPONENT_ENABLED_STATE_DEFAULT;
                }
                if (value2 == 1) {
                    return COMPONENT_ENABLED_STATE_ENABLED;
                }
                if (value2 == 2) {
                    return COMPONENT_ENABLED_STATE_DISABLED;
                }
                if (value2 == 3) {
                    return COMPONENT_ENABLED_STATE_DISABLED_USER;
                }
                if (value2 != 4) {
                    return null;
                }
                return COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED;
            }

            public static Internal.EnumLiteMap<EnabledState> internalGetValueMap() {
                return internalValueMap;
            }

            private EnabledState(int value2) {
                this.value = value2;
            }
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
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

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasInstallType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public InstallType getInstallType() {
            InstallType result = InstallType.forNumber(this.installType_);
            return result == null ? InstallType.NOT_INSTALLED_FOR_USER : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInstallType(InstallType value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.installType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInstallType() {
            this.bitField0_ &= -3;
            this.installType_ = 0;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasIsHidden() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean getIsHidden() {
            return this.isHidden_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsHidden(boolean value) {
            this.bitField0_ |= 4;
            this.isHidden_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsHidden() {
            this.bitField0_ &= -5;
            this.isHidden_ = false;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasIsSuspended() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean getIsSuspended() {
            return this.isSuspended_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsSuspended(boolean value) {
            this.bitField0_ |= 8;
            this.isSuspended_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsSuspended() {
            this.bitField0_ &= -9;
            this.isSuspended_ = false;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasIsStopped() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean getIsStopped() {
            return this.isStopped_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsStopped(boolean value) {
            this.bitField0_ |= 16;
            this.isStopped_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsStopped() {
            this.bitField0_ &= -17;
            this.isStopped_ = false;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasIsLaunched() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean getIsLaunched() {
            return this.isLaunched_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsLaunched(boolean value) {
            this.bitField0_ |= 32;
            this.isLaunched_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsLaunched() {
            this.bitField0_ &= -33;
            this.isLaunched_ = false;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasEnabledState() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public EnabledState getEnabledState() {
            EnabledState result = EnabledState.forNumber(this.enabledState_);
            return result == null ? EnabledState.COMPONENT_ENABLED_STATE_DEFAULT : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEnabledState(EnabledState value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.enabledState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEnabledState() {
            this.bitField0_ &= -65;
            this.enabledState_ = 0;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasLastDisabledAppCaller() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public String getLastDisabledAppCaller() {
            return this.lastDisabledAppCaller_;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public ByteString getLastDisabledAppCallerBytes() {
            return ByteString.copyFromUtf8(this.lastDisabledAppCaller_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastDisabledAppCaller(String value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.lastDisabledAppCaller_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastDisabledAppCaller() {
            this.bitField0_ &= -129;
            this.lastDisabledAppCaller_ = getDefaultInstance().getLastDisabledAppCaller();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastDisabledAppCallerBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.lastDisabledAppCaller_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasSuspendingPackage() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public String getSuspendingPackage() {
            return this.suspendingPackage_;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public ByteString getSuspendingPackageBytes() {
            return ByteString.copyFromUtf8(this.suspendingPackage_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSuspendingPackage(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.suspendingPackage_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSuspendingPackage() {
            this.bitField0_ &= -257;
            this.suspendingPackage_ = getDefaultInstance().getSuspendingPackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSuspendingPackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.suspendingPackage_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public boolean hasDistractionFlags() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
        public int getDistractionFlags() {
            return this.distractionFlags_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDistractionFlags(int value) {
            this.bitField0_ |= 512;
            this.distractionFlags_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDistractionFlags() {
            this.bitField0_ &= -513;
            this.distractionFlags_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.installType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.isHidden_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(4, this.isSuspended_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBool(5, this.isStopped_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBool(6, this.isLaunched_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeEnum(7, this.enabledState_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeString(8, getLastDisabledAppCaller());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getSuspendingPackage());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt32(10, this.distractionFlags_);
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
                size2 += CodedOutputStream.computeEnumSize(2, this.installType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isHidden_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBoolSize(4, this.isSuspended_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBoolSize(5, this.isStopped_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBoolSize(6, this.isLaunched_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeEnumSize(7, this.enabledState_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeStringSize(8, getLastDisabledAppCaller());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getSuspendingPackage());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt32Size(10, this.distractionFlags_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static UserInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserInfoProto parseFrom(InputStream input) throws IOException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserInfoProto parseDelimitedFrom(InputStream input) throws IOException {
            return (UserInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UserInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserInfoProto parseFrom(CodedInputStream input) throws IOException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UserInfoProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserInfoProto, Builder> implements UserInfoProtoOrBuilder {
            private Builder() {
                super(UserInfoProto.DEFAULT_INSTANCE);
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasId() {
                return ((UserInfoProto) this.instance).hasId();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public int getId() {
                return ((UserInfoProto) this.instance).getId();
            }

            public Builder setId(int value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearId();
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasInstallType() {
                return ((UserInfoProto) this.instance).hasInstallType();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public InstallType getInstallType() {
                return ((UserInfoProto) this.instance).getInstallType();
            }

            public Builder setInstallType(InstallType value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setInstallType(value);
                return this;
            }

            public Builder clearInstallType() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearInstallType();
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasIsHidden() {
                return ((UserInfoProto) this.instance).hasIsHidden();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean getIsHidden() {
                return ((UserInfoProto) this.instance).getIsHidden();
            }

            public Builder setIsHidden(boolean value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setIsHidden(value);
                return this;
            }

            public Builder clearIsHidden() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearIsHidden();
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasIsSuspended() {
                return ((UserInfoProto) this.instance).hasIsSuspended();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean getIsSuspended() {
                return ((UserInfoProto) this.instance).getIsSuspended();
            }

            public Builder setIsSuspended(boolean value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setIsSuspended(value);
                return this;
            }

            public Builder clearIsSuspended() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearIsSuspended();
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasIsStopped() {
                return ((UserInfoProto) this.instance).hasIsStopped();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean getIsStopped() {
                return ((UserInfoProto) this.instance).getIsStopped();
            }

            public Builder setIsStopped(boolean value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setIsStopped(value);
                return this;
            }

            public Builder clearIsStopped() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearIsStopped();
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasIsLaunched() {
                return ((UserInfoProto) this.instance).hasIsLaunched();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean getIsLaunched() {
                return ((UserInfoProto) this.instance).getIsLaunched();
            }

            public Builder setIsLaunched(boolean value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setIsLaunched(value);
                return this;
            }

            public Builder clearIsLaunched() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearIsLaunched();
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasEnabledState() {
                return ((UserInfoProto) this.instance).hasEnabledState();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public EnabledState getEnabledState() {
                return ((UserInfoProto) this.instance).getEnabledState();
            }

            public Builder setEnabledState(EnabledState value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setEnabledState(value);
                return this;
            }

            public Builder clearEnabledState() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearEnabledState();
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasLastDisabledAppCaller() {
                return ((UserInfoProto) this.instance).hasLastDisabledAppCaller();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public String getLastDisabledAppCaller() {
                return ((UserInfoProto) this.instance).getLastDisabledAppCaller();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public ByteString getLastDisabledAppCallerBytes() {
                return ((UserInfoProto) this.instance).getLastDisabledAppCallerBytes();
            }

            public Builder setLastDisabledAppCaller(String value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setLastDisabledAppCaller(value);
                return this;
            }

            public Builder clearLastDisabledAppCaller() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearLastDisabledAppCaller();
                return this;
            }

            public Builder setLastDisabledAppCallerBytes(ByteString value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setLastDisabledAppCallerBytes(value);
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasSuspendingPackage() {
                return ((UserInfoProto) this.instance).hasSuspendingPackage();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public String getSuspendingPackage() {
                return ((UserInfoProto) this.instance).getSuspendingPackage();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public ByteString getSuspendingPackageBytes() {
                return ((UserInfoProto) this.instance).getSuspendingPackageBytes();
            }

            public Builder setSuspendingPackage(String value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setSuspendingPackage(value);
                return this;
            }

            public Builder clearSuspendingPackage() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearSuspendingPackage();
                return this;
            }

            public Builder setSuspendingPackageBytes(ByteString value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setSuspendingPackageBytes(value);
                return this;
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public boolean hasDistractionFlags() {
                return ((UserInfoProto) this.instance).hasDistractionFlags();
            }

            @Override // android.service.pm.PackageProto.UserInfoProtoOrBuilder
            public int getDistractionFlags() {
                return ((UserInfoProto) this.instance).getDistractionFlags();
            }

            public Builder setDistractionFlags(int value) {
                copyOnWrite();
                ((UserInfoProto) this.instance).setDistractionFlags(value);
                return this;
            }

            public Builder clearDistractionFlags() {
                copyOnWrite();
                ((UserInfoProto) this.instance).clearDistractionFlags();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserInfoProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    UserInfoProto other = (UserInfoProto) arg1;
                    this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                    this.installType_ = visitor.visitInt(hasInstallType(), this.installType_, other.hasInstallType(), other.installType_);
                    this.isHidden_ = visitor.visitBoolean(hasIsHidden(), this.isHidden_, other.hasIsHidden(), other.isHidden_);
                    this.isSuspended_ = visitor.visitBoolean(hasIsSuspended(), this.isSuspended_, other.hasIsSuspended(), other.isSuspended_);
                    this.isStopped_ = visitor.visitBoolean(hasIsStopped(), this.isStopped_, other.hasIsStopped(), other.isStopped_);
                    this.isLaunched_ = visitor.visitBoolean(hasIsLaunched(), this.isLaunched_, other.hasIsLaunched(), other.isLaunched_);
                    this.enabledState_ = visitor.visitInt(hasEnabledState(), this.enabledState_, other.hasEnabledState(), other.enabledState_);
                    this.lastDisabledAppCaller_ = visitor.visitString(hasLastDisabledAppCaller(), this.lastDisabledAppCaller_, other.hasLastDisabledAppCaller(), other.lastDisabledAppCaller_);
                    this.suspendingPackage_ = visitor.visitString(hasSuspendingPackage(), this.suspendingPackage_, other.hasSuspendingPackage(), other.suspendingPackage_);
                    this.distractionFlags_ = visitor.visitInt(hasDistractionFlags(), this.distractionFlags_, other.hasDistractionFlags(), other.distractionFlags_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.id_ = input.readInt32();
                                    break;
                                case 16:
                                    int rawValue = input.readEnum();
                                    if (InstallType.forNumber(rawValue) != null) {
                                        this.bitField0_ = 2 | this.bitField0_;
                                        this.installType_ = rawValue;
                                        break;
                                    } else {
                                        super.mergeVarintField(2, rawValue);
                                        break;
                                    }
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.isHidden_ = input.readBool();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.isSuspended_ = input.readBool();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.isStopped_ = input.readBool();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.isLaunched_ = input.readBool();
                                    break;
                                case 56:
                                    int rawValue2 = input.readEnum();
                                    if (EnabledState.forNumber(rawValue2) != null) {
                                        this.bitField0_ |= 64;
                                        this.enabledState_ = rawValue2;
                                        break;
                                    } else {
                                        super.mergeVarintField(7, rawValue2);
                                        break;
                                    }
                                case 66:
                                    String s = input.readString();
                                    this.bitField0_ |= 128;
                                    this.lastDisabledAppCaller_ = s;
                                    break;
                                case 74:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.suspendingPackage_ = s2;
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.distractionFlags_ = input.readInt32();
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
                        synchronized (UserInfoProto.class) {
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

        public static UserInfoProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UserInfoProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -2;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 2;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -3;
        this.uid_ = 0;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public boolean hasVersionCode() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public int getVersionCode() {
        return this.versionCode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionCode(int value) {
        this.bitField0_ |= 4;
        this.versionCode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersionCode() {
        this.bitField0_ &= -5;
        this.versionCode_ = 0;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public boolean hasVersionString() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public String getVersionString() {
        return this.versionString_;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public ByteString getVersionStringBytes() {
        return ByteString.copyFromUtf8(this.versionString_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionString(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.versionString_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersionString() {
        this.bitField0_ &= -9;
        this.versionString_ = getDefaultInstance().getVersionString();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionStringBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.versionString_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public boolean hasInstallTimeMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public long getInstallTimeMs() {
        return this.installTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInstallTimeMs(long value) {
        this.bitField0_ |= 16;
        this.installTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInstallTimeMs() {
        this.bitField0_ &= -17;
        this.installTimeMs_ = 0;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public boolean hasUpdateTimeMs() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public long getUpdateTimeMs() {
        return this.updateTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUpdateTimeMs(long value) {
        this.bitField0_ |= 32;
        this.updateTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUpdateTimeMs() {
        this.bitField0_ &= -33;
        this.updateTimeMs_ = 0;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public boolean hasInstallerName() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public String getInstallerName() {
        return this.installerName_;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public ByteString getInstallerNameBytes() {
        return ByteString.copyFromUtf8(this.installerName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInstallerName(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.installerName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInstallerName() {
        this.bitField0_ &= -65;
        this.installerName_ = getDefaultInstance().getInstallerName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInstallerNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.installerName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public List<SplitProto> getSplitsList() {
        return this.splits_;
    }

    public List<? extends SplitProtoOrBuilder> getSplitsOrBuilderList() {
        return this.splits_;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public int getSplitsCount() {
        return this.splits_.size();
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public SplitProto getSplits(int index) {
        return this.splits_.get(index);
    }

    public SplitProtoOrBuilder getSplitsOrBuilder(int index) {
        return this.splits_.get(index);
    }

    private void ensureSplitsIsMutable() {
        if (!this.splits_.isModifiable()) {
            this.splits_ = GeneratedMessageLite.mutableCopy(this.splits_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSplits(int index, SplitProto value) {
        if (value != null) {
            ensureSplitsIsMutable();
            this.splits_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSplits(int index, SplitProto.Builder builderForValue) {
        ensureSplitsIsMutable();
        this.splits_.set(index, (SplitProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplits(SplitProto value) {
        if (value != null) {
            ensureSplitsIsMutable();
            this.splits_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplits(int index, SplitProto value) {
        if (value != null) {
            ensureSplitsIsMutable();
            this.splits_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplits(SplitProto.Builder builderForValue) {
        ensureSplitsIsMutable();
        this.splits_.add((SplitProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplits(int index, SplitProto.Builder builderForValue) {
        ensureSplitsIsMutable();
        this.splits_.add(index, (SplitProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSplits(Iterable<? extends SplitProto> values) {
        ensureSplitsIsMutable();
        AbstractMessageLite.addAll(values, this.splits_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSplits() {
        this.splits_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSplits(int index) {
        ensureSplitsIsMutable();
        this.splits_.remove(index);
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public List<UserInfoProto> getUsersList() {
        return this.users_;
    }

    public List<? extends UserInfoProtoOrBuilder> getUsersOrBuilderList() {
        return this.users_;
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public int getUsersCount() {
        return this.users_.size();
    }

    @Override // android.service.pm.PackageProtoOrBuilder
    public UserInfoProto getUsers(int index) {
        return this.users_.get(index);
    }

    public UserInfoProtoOrBuilder getUsersOrBuilder(int index) {
        return this.users_.get(index);
    }

    private void ensureUsersIsMutable() {
        if (!this.users_.isModifiable()) {
            this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsers(int index, UserInfoProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsers(int index, UserInfoProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.set(index, (UserInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(UserInfoProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(int index, UserInfoProto value) {
        if (value != null) {
            ensureUsersIsMutable();
            this.users_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(UserInfoProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.add((UserInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUsers(int index, UserInfoProto.Builder builderForValue) {
        ensureUsersIsMutable();
        this.users_.add(index, (UserInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUsers(Iterable<? extends UserInfoProto> values) {
        ensureUsersIsMutable();
        AbstractMessageLite.addAll(values, this.users_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsers() {
        this.users_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUsers(int index) {
        ensureUsersIsMutable();
        this.users_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.versionCode_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getVersionString());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.installTimeMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.updateTimeMs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getInstallerName());
        }
        for (int i = 0; i < this.splits_.size(); i++) {
            output.writeMessage(8, this.splits_.get(i));
        }
        for (int i2 = 0; i2 < this.users_.size(); i2++) {
            output.writeMessage(9, this.users_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.versionCode_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getVersionString());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.installTimeMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.updateTimeMs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getInstallerName());
        }
        for (int i = 0; i < this.splits_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(8, this.splits_.get(i));
        }
        for (int i2 = 0; i2 < this.users_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(9, this.users_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageProto parseFrom(InputStream input) throws IOException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageProto, Builder> implements PackageProtoOrBuilder {
        private Builder() {
            super(PackageProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public boolean hasName() {
            return ((PackageProto) this.instance).hasName();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public String getName() {
            return ((PackageProto) this.instance).getName();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public ByteString getNameBytes() {
            return ((PackageProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((PackageProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((PackageProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public boolean hasUid() {
            return ((PackageProto) this.instance).hasUid();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public int getUid() {
            return ((PackageProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((PackageProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((PackageProto) this.instance).clearUid();
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public boolean hasVersionCode() {
            return ((PackageProto) this.instance).hasVersionCode();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public int getVersionCode() {
            return ((PackageProto) this.instance).getVersionCode();
        }

        public Builder setVersionCode(int value) {
            copyOnWrite();
            ((PackageProto) this.instance).setVersionCode(value);
            return this;
        }

        public Builder clearVersionCode() {
            copyOnWrite();
            ((PackageProto) this.instance).clearVersionCode();
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public boolean hasVersionString() {
            return ((PackageProto) this.instance).hasVersionString();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public String getVersionString() {
            return ((PackageProto) this.instance).getVersionString();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public ByteString getVersionStringBytes() {
            return ((PackageProto) this.instance).getVersionStringBytes();
        }

        public Builder setVersionString(String value) {
            copyOnWrite();
            ((PackageProto) this.instance).setVersionString(value);
            return this;
        }

        public Builder clearVersionString() {
            copyOnWrite();
            ((PackageProto) this.instance).clearVersionString();
            return this;
        }

        public Builder setVersionStringBytes(ByteString value) {
            copyOnWrite();
            ((PackageProto) this.instance).setVersionStringBytes(value);
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public boolean hasInstallTimeMs() {
            return ((PackageProto) this.instance).hasInstallTimeMs();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public long getInstallTimeMs() {
            return ((PackageProto) this.instance).getInstallTimeMs();
        }

        public Builder setInstallTimeMs(long value) {
            copyOnWrite();
            ((PackageProto) this.instance).setInstallTimeMs(value);
            return this;
        }

        public Builder clearInstallTimeMs() {
            copyOnWrite();
            ((PackageProto) this.instance).clearInstallTimeMs();
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public boolean hasUpdateTimeMs() {
            return ((PackageProto) this.instance).hasUpdateTimeMs();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public long getUpdateTimeMs() {
            return ((PackageProto) this.instance).getUpdateTimeMs();
        }

        public Builder setUpdateTimeMs(long value) {
            copyOnWrite();
            ((PackageProto) this.instance).setUpdateTimeMs(value);
            return this;
        }

        public Builder clearUpdateTimeMs() {
            copyOnWrite();
            ((PackageProto) this.instance).clearUpdateTimeMs();
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public boolean hasInstallerName() {
            return ((PackageProto) this.instance).hasInstallerName();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public String getInstallerName() {
            return ((PackageProto) this.instance).getInstallerName();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public ByteString getInstallerNameBytes() {
            return ((PackageProto) this.instance).getInstallerNameBytes();
        }

        public Builder setInstallerName(String value) {
            copyOnWrite();
            ((PackageProto) this.instance).setInstallerName(value);
            return this;
        }

        public Builder clearInstallerName() {
            copyOnWrite();
            ((PackageProto) this.instance).clearInstallerName();
            return this;
        }

        public Builder setInstallerNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageProto) this.instance).setInstallerNameBytes(value);
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public List<SplitProto> getSplitsList() {
            return Collections.unmodifiableList(((PackageProto) this.instance).getSplitsList());
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public int getSplitsCount() {
            return ((PackageProto) this.instance).getSplitsCount();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public SplitProto getSplits(int index) {
            return ((PackageProto) this.instance).getSplits(index);
        }

        public Builder setSplits(int index, SplitProto value) {
            copyOnWrite();
            ((PackageProto) this.instance).setSplits((PackageProto) index, (int) value);
            return this;
        }

        public Builder setSplits(int index, SplitProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageProto) this.instance).setSplits((PackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSplits(SplitProto value) {
            copyOnWrite();
            ((PackageProto) this.instance).addSplits((PackageProto) value);
            return this;
        }

        public Builder addSplits(int index, SplitProto value) {
            copyOnWrite();
            ((PackageProto) this.instance).addSplits((PackageProto) index, (int) value);
            return this;
        }

        public Builder addSplits(SplitProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageProto) this.instance).addSplits((PackageProto) builderForValue);
            return this;
        }

        public Builder addSplits(int index, SplitProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageProto) this.instance).addSplits((PackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSplits(Iterable<? extends SplitProto> values) {
            copyOnWrite();
            ((PackageProto) this.instance).addAllSplits(values);
            return this;
        }

        public Builder clearSplits() {
            copyOnWrite();
            ((PackageProto) this.instance).clearSplits();
            return this;
        }

        public Builder removeSplits(int index) {
            copyOnWrite();
            ((PackageProto) this.instance).removeSplits(index);
            return this;
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public List<UserInfoProto> getUsersList() {
            return Collections.unmodifiableList(((PackageProto) this.instance).getUsersList());
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public int getUsersCount() {
            return ((PackageProto) this.instance).getUsersCount();
        }

        @Override // android.service.pm.PackageProtoOrBuilder
        public UserInfoProto getUsers(int index) {
            return ((PackageProto) this.instance).getUsers(index);
        }

        public Builder setUsers(int index, UserInfoProto value) {
            copyOnWrite();
            ((PackageProto) this.instance).setUsers((PackageProto) index, (int) value);
            return this;
        }

        public Builder setUsers(int index, UserInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageProto) this.instance).setUsers((PackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUsers(UserInfoProto value) {
            copyOnWrite();
            ((PackageProto) this.instance).addUsers((PackageProto) value);
            return this;
        }

        public Builder addUsers(int index, UserInfoProto value) {
            copyOnWrite();
            ((PackageProto) this.instance).addUsers((PackageProto) index, (int) value);
            return this;
        }

        public Builder addUsers(UserInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageProto) this.instance).addUsers((PackageProto) builderForValue);
            return this;
        }

        public Builder addUsers(int index, UserInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageProto) this.instance).addUsers((PackageProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUsers(Iterable<? extends UserInfoProto> values) {
            copyOnWrite();
            ((PackageProto) this.instance).addAllUsers(values);
            return this;
        }

        public Builder clearUsers() {
            copyOnWrite();
            ((PackageProto) this.instance).clearUsers();
            return this;
        }

        public Builder removeUsers(int index) {
            copyOnWrite();
            ((PackageProto) this.instance).removeUsers(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.splits_.makeImmutable();
                this.users_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageProto other = (PackageProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.versionCode_ = visitor.visitInt(hasVersionCode(), this.versionCode_, other.hasVersionCode(), other.versionCode_);
                this.versionString_ = visitor.visitString(hasVersionString(), this.versionString_, other.hasVersionString(), other.versionString_);
                this.installTimeMs_ = visitor.visitLong(hasInstallTimeMs(), this.installTimeMs_, other.hasInstallTimeMs(), other.installTimeMs_);
                this.updateTimeMs_ = visitor.visitLong(hasUpdateTimeMs(), this.updateTimeMs_, other.hasUpdateTimeMs(), other.updateTimeMs_);
                this.installerName_ = visitor.visitString(hasInstallerName(), this.installerName_, other.hasInstallerName(), other.installerName_);
                this.splits_ = visitor.visitList(this.splits_, other.splits_);
                this.users_ = visitor.visitList(this.users_, other.users_);
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
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.name_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.uid_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.versionCode_ = input.readInt32();
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ |= 8;
                            this.versionString_ = s2;
                        } else if (tag == 40) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.installTimeMs_ = input.readInt64();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.updateTimeMs_ = input.readInt64();
                        } else if (tag == 58) {
                            String s3 = input.readString();
                            this.bitField0_ |= 64;
                            this.installerName_ = s3;
                        } else if (tag == 66) {
                            if (!this.splits_.isModifiable()) {
                                this.splits_ = GeneratedMessageLite.mutableCopy(this.splits_);
                            }
                            this.splits_.add((SplitProto) input.readMessage(SplitProto.parser(), extensionRegistry));
                        } else if (tag == 74) {
                            if (!this.users_.isModifiable()) {
                                this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
                            }
                            this.users_.add((UserInfoProto) input.readMessage(UserInfoProto.parser(), extensionRegistry));
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
                    synchronized (PackageProto.class) {
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

    public static PackageProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
