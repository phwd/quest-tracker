package android.content.pm;

import android.content.pm.PackageItemInfoProto;
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

public final class ApplicationInfoProto extends GeneratedMessageLite<ApplicationInfoProto, Builder> implements ApplicationInfoProtoOrBuilder {
    public static final int CLASS_LOADER_NAME_FIELD_NUMBER = 14;
    public static final int DATA_DIR_FIELD_NUMBER = 13;
    private static final ApplicationInfoProto DEFAULT_INSTANCE = new ApplicationInfoProto();
    public static final int DETAIL_FIELD_NUMBER = 17;
    public static final int FLAGS_FIELD_NUMBER = 5;
    public static final int PACKAGE_FIELD_NUMBER = 1;
    private static volatile Parser<ApplicationInfoProto> PARSER = null;
    public static final int PERMISSION_FIELD_NUMBER = 2;
    public static final int PRIVATE_FLAGS_FIELD_NUMBER = 6;
    public static final int PROCESS_NAME_FIELD_NUMBER = 3;
    public static final int PUBLIC_SOURCE_DIR_FIELD_NUMBER = 9;
    public static final int RESOURCE_DIRS_FIELD_NUMBER = 12;
    public static final int SOURCE_DIR_FIELD_NUMBER = 8;
    public static final int SPLIT_CLASS_LOADER_NAMES_FIELD_NUMBER = 15;
    public static final int SPLIT_PUBLIC_SOURCE_DIRS_FIELD_NUMBER = 11;
    public static final int SPLIT_SOURCE_DIRS_FIELD_NUMBER = 10;
    public static final int THEME_FIELD_NUMBER = 7;
    public static final int UID_FIELD_NUMBER = 4;
    public static final int VERSION_FIELD_NUMBER = 16;
    private int bitField0_;
    private String classLoaderName_ = "";
    private String dataDir_ = "";
    private Detail detail_;
    private int flags_ = 0;
    private PackageItemInfoProto package_;
    private String permission_ = "";
    private int privateFlags_ = 0;
    private String processName_ = "";
    private String publicSourceDir_ = "";
    private Internal.ProtobufList<String> resourceDirs_ = GeneratedMessageLite.emptyProtobufList();
    private String sourceDir_ = "";
    private Internal.ProtobufList<String> splitClassLoaderNames_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> splitPublicSourceDirs_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> splitSourceDirs_ = GeneratedMessageLite.emptyProtobufList();
    private int theme_ = 0;
    private int uid_ = 0;
    private Version version_;

    public interface DetailOrBuilder extends MessageLiteOrBuilder {
        int getCategory();

        String getClassName();

        ByteString getClassNameBytes();

        int getCompatibleWidthLimitDp();

        String getContent();

        ByteString getContentBytes();

        String getCredentialProtectedDataDir();

        ByteString getCredentialProtectedDataDirBytes();

        int getDescriptionRes();

        String getDeviceProtectedDataDir();

        ByteString getDeviceProtectedDataDirBytes();

        Detail.FullBackupContentCase getFullBackupContentCase();

        boolean getIsFullBackup();

        int getLargestWidthLimitDp();

        String getManageSpaceActivityName();

        ByteString getManageSpaceActivityNameBytes();

        int getNetworkSecurityConfigRes();

        int getRequiresSmallestWidthDp();

        String getSeinfo();

        ByteString getSeinfoBytes();

        String getSeinfoUser();

        ByteString getSeinfoUserBytes();

        String getSharedLibraryFiles(int i);

        ByteString getSharedLibraryFilesBytes(int i);

        int getSharedLibraryFilesCount();

        List<String> getSharedLibraryFilesList();

        boolean getSupportsRtl();

        String getTaskAffinity();

        ByteString getTaskAffinityBytes();

        int getUiOptions();

        boolean hasCategory();

        boolean hasClassName();

        boolean hasCompatibleWidthLimitDp();

        boolean hasContent();

        boolean hasCredentialProtectedDataDir();

        boolean hasDescriptionRes();

        boolean hasDeviceProtectedDataDir();

        boolean hasIsFullBackup();

        boolean hasLargestWidthLimitDp();

        boolean hasManageSpaceActivityName();

        boolean hasNetworkSecurityConfigRes();

        boolean hasRequiresSmallestWidthDp();

        boolean hasSeinfo();

        boolean hasSeinfoUser();

        boolean hasSupportsRtl();

        boolean hasTaskAffinity();

        boolean hasUiOptions();
    }

    public interface VersionOrBuilder extends MessageLiteOrBuilder {
        boolean getEnabled();

        int getMinSdkVersion();

        int getTargetSandboxVersion();

        int getTargetSdkVersion();

        int getVersionCode();

        boolean hasEnabled();

        boolean hasMinSdkVersion();

        boolean hasTargetSandboxVersion();

        boolean hasTargetSdkVersion();

        boolean hasVersionCode();
    }

    private ApplicationInfoProto() {
    }

    public static final class Version extends GeneratedMessageLite<Version, Builder> implements VersionOrBuilder {
        private static final Version DEFAULT_INSTANCE = new Version();
        public static final int ENABLED_FIELD_NUMBER = 1;
        public static final int MIN_SDK_VERSION_FIELD_NUMBER = 2;
        private static volatile Parser<Version> PARSER = null;
        public static final int TARGET_SANDBOX_VERSION_FIELD_NUMBER = 5;
        public static final int TARGET_SDK_VERSION_FIELD_NUMBER = 3;
        public static final int VERSION_CODE_FIELD_NUMBER = 4;
        private int bitField0_;
        private boolean enabled_ = false;
        private int minSdkVersion_ = 0;
        private int targetSandboxVersion_ = 0;
        private int targetSdkVersion_ = 0;
        private int versionCode_ = 0;

        private Version() {
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public boolean hasEnabled() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public boolean getEnabled() {
            return this.enabled_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEnabled(boolean value) {
            this.bitField0_ |= 1;
            this.enabled_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEnabled() {
            this.bitField0_ &= -2;
            this.enabled_ = false;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public boolean hasMinSdkVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public int getMinSdkVersion() {
            return this.minSdkVersion_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMinSdkVersion(int value) {
            this.bitField0_ |= 2;
            this.minSdkVersion_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMinSdkVersion() {
            this.bitField0_ &= -3;
            this.minSdkVersion_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public boolean hasTargetSdkVersion() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public int getTargetSdkVersion() {
            return this.targetSdkVersion_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTargetSdkVersion(int value) {
            this.bitField0_ |= 4;
            this.targetSdkVersion_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTargetSdkVersion() {
            this.bitField0_ &= -5;
            this.targetSdkVersion_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public boolean hasVersionCode() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public int getVersionCode() {
            return this.versionCode_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVersionCode(int value) {
            this.bitField0_ |= 8;
            this.versionCode_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVersionCode() {
            this.bitField0_ &= -9;
            this.versionCode_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public boolean hasTargetSandboxVersion() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
        public int getTargetSandboxVersion() {
            return this.targetSandboxVersion_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTargetSandboxVersion(int value) {
            this.bitField0_ |= 16;
            this.targetSandboxVersion_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTargetSandboxVersion() {
            this.bitField0_ &= -17;
            this.targetSandboxVersion_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.enabled_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.minSdkVersion_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.targetSdkVersion_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.versionCode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.targetSandboxVersion_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.enabled_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.minSdkVersion_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.targetSdkVersion_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.versionCode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.targetSandboxVersion_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Version parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Version parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Version parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Version parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Version parseFrom(InputStream input) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Version parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Version parseDelimitedFrom(InputStream input) throws IOException {
            return (Version) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Version parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Version) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Version parseFrom(CodedInputStream input) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Version parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Version prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Version, Builder> implements VersionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Version.DEFAULT_INSTANCE);
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public boolean hasEnabled() {
                return ((Version) this.instance).hasEnabled();
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public boolean getEnabled() {
                return ((Version) this.instance).getEnabled();
            }

            public Builder setEnabled(boolean value) {
                copyOnWrite();
                ((Version) this.instance).setEnabled(value);
                return this;
            }

            public Builder clearEnabled() {
                copyOnWrite();
                ((Version) this.instance).clearEnabled();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public boolean hasMinSdkVersion() {
                return ((Version) this.instance).hasMinSdkVersion();
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public int getMinSdkVersion() {
                return ((Version) this.instance).getMinSdkVersion();
            }

            public Builder setMinSdkVersion(int value) {
                copyOnWrite();
                ((Version) this.instance).setMinSdkVersion(value);
                return this;
            }

            public Builder clearMinSdkVersion() {
                copyOnWrite();
                ((Version) this.instance).clearMinSdkVersion();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public boolean hasTargetSdkVersion() {
                return ((Version) this.instance).hasTargetSdkVersion();
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public int getTargetSdkVersion() {
                return ((Version) this.instance).getTargetSdkVersion();
            }

            public Builder setTargetSdkVersion(int value) {
                copyOnWrite();
                ((Version) this.instance).setTargetSdkVersion(value);
                return this;
            }

            public Builder clearTargetSdkVersion() {
                copyOnWrite();
                ((Version) this.instance).clearTargetSdkVersion();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public boolean hasVersionCode() {
                return ((Version) this.instance).hasVersionCode();
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public int getVersionCode() {
                return ((Version) this.instance).getVersionCode();
            }

            public Builder setVersionCode(int value) {
                copyOnWrite();
                ((Version) this.instance).setVersionCode(value);
                return this;
            }

            public Builder clearVersionCode() {
                copyOnWrite();
                ((Version) this.instance).clearVersionCode();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public boolean hasTargetSandboxVersion() {
                return ((Version) this.instance).hasTargetSandboxVersion();
            }

            @Override // android.content.pm.ApplicationInfoProto.VersionOrBuilder
            public int getTargetSandboxVersion() {
                return ((Version) this.instance).getTargetSandboxVersion();
            }

            public Builder setTargetSandboxVersion(int value) {
                copyOnWrite();
                ((Version) this.instance).setTargetSandboxVersion(value);
                return this;
            }

            public Builder clearTargetSandboxVersion() {
                copyOnWrite();
                ((Version) this.instance).clearTargetSandboxVersion();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Version();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Version other = (Version) arg1;
                    this.enabled_ = visitor.visitBoolean(hasEnabled(), this.enabled_, other.hasEnabled(), other.enabled_);
                    this.minSdkVersion_ = visitor.visitInt(hasMinSdkVersion(), this.minSdkVersion_, other.hasMinSdkVersion(), other.minSdkVersion_);
                    this.targetSdkVersion_ = visitor.visitInt(hasTargetSdkVersion(), this.targetSdkVersion_, other.hasTargetSdkVersion(), other.targetSdkVersion_);
                    this.versionCode_ = visitor.visitInt(hasVersionCode(), this.versionCode_, other.hasVersionCode(), other.versionCode_);
                    this.targetSandboxVersion_ = visitor.visitInt(hasTargetSandboxVersion(), this.targetSandboxVersion_, other.hasTargetSandboxVersion(), other.targetSandboxVersion_);
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
                                this.enabled_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.minSdkVersion_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.targetSdkVersion_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.versionCode_ = input.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.targetSandboxVersion_ = input.readInt32();
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
                        synchronized (Version.class) {
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

        public static Version getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Version> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Detail extends GeneratedMessageLite<Detail, Builder> implements DetailOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 18;
        public static final int CLASS_NAME_FIELD_NUMBER = 1;
        public static final int COMPATIBLE_WIDTH_LIMIT_DP_FIELD_NUMBER = 4;
        public static final int CONTENT_FIELD_NUMBER = 15;
        public static final int CREDENTIAL_PROTECTED_DATA_DIR_FIELD_NUMBER = 9;
        private static final Detail DEFAULT_INSTANCE = new Detail();
        public static final int DESCRIPTION_RES_FIELD_NUMBER = 12;
        public static final int DEVICE_PROTECTED_DATA_DIR_FIELD_NUMBER = 8;
        public static final int IS_FULL_BACKUP_FIELD_NUMBER = 16;
        public static final int LARGEST_WIDTH_LIMIT_DP_FIELD_NUMBER = 5;
        public static final int MANAGE_SPACE_ACTIVITY_NAME_FIELD_NUMBER = 11;
        public static final int NETWORK_SECURITY_CONFIG_RES_FIELD_NUMBER = 17;
        private static volatile Parser<Detail> PARSER = null;
        public static final int REQUIRES_SMALLEST_WIDTH_DP_FIELD_NUMBER = 3;
        public static final int SEINFO_FIELD_NUMBER = 6;
        public static final int SEINFO_USER_FIELD_NUMBER = 7;
        public static final int SHARED_LIBRARY_FILES_FIELD_NUMBER = 10;
        public static final int SUPPORTS_RTL_FIELD_NUMBER = 14;
        public static final int TASK_AFFINITY_FIELD_NUMBER = 2;
        public static final int UI_OPTIONS_FIELD_NUMBER = 13;
        private int bitField0_;
        private int category_ = 0;
        private String className_ = "";
        private int compatibleWidthLimitDp_ = 0;
        private String credentialProtectedDataDir_ = "";
        private int descriptionRes_ = 0;
        private String deviceProtectedDataDir_ = "";
        private int fullBackupContentCase_ = 0;
        private Object fullBackupContent_;
        private int largestWidthLimitDp_ = 0;
        private String manageSpaceActivityName_ = "";
        private int networkSecurityConfigRes_ = 0;
        private int requiresSmallestWidthDp_ = 0;
        private String seinfoUser_ = "";
        private String seinfo_ = "";
        private Internal.ProtobufList<String> sharedLibraryFiles_ = GeneratedMessageLite.emptyProtobufList();
        private boolean supportsRtl_ = false;
        private String taskAffinity_ = "";
        private int uiOptions_ = 0;

        private Detail() {
        }

        public enum FullBackupContentCase implements Internal.EnumLite {
            CONTENT(15),
            IS_FULL_BACKUP(16),
            FULLBACKUPCONTENT_NOT_SET(0);
            
            private final int value;

            private FullBackupContentCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static FullBackupContentCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static FullBackupContentCase forNumber(int value2) {
                if (value2 == 0) {
                    return FULLBACKUPCONTENT_NOT_SET;
                }
                if (value2 == 15) {
                    return CONTENT;
                }
                if (value2 != 16) {
                    return null;
                }
                return IS_FULL_BACKUP;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public FullBackupContentCase getFullBackupContentCase() {
            return FullBackupContentCase.forNumber(this.fullBackupContentCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFullBackupContent() {
            this.fullBackupContentCase_ = 0;
            this.fullBackupContent_ = null;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasClassName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getClassName() {
            return this.className_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getClassNameBytes() {
            return ByteString.copyFromUtf8(this.className_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClassName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.className_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearClassName() {
            this.bitField0_ &= -2;
            this.className_ = getDefaultInstance().getClassName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClassNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.className_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasTaskAffinity() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getTaskAffinity() {
            return this.taskAffinity_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getTaskAffinityBytes() {
            return ByteString.copyFromUtf8(this.taskAffinity_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTaskAffinity(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.taskAffinity_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTaskAffinity() {
            this.bitField0_ &= -3;
            this.taskAffinity_ = getDefaultInstance().getTaskAffinity();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTaskAffinityBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.taskAffinity_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasRequiresSmallestWidthDp() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getRequiresSmallestWidthDp() {
            return this.requiresSmallestWidthDp_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRequiresSmallestWidthDp(int value) {
            this.bitField0_ |= 4;
            this.requiresSmallestWidthDp_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRequiresSmallestWidthDp() {
            this.bitField0_ &= -5;
            this.requiresSmallestWidthDp_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasCompatibleWidthLimitDp() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getCompatibleWidthLimitDp() {
            return this.compatibleWidthLimitDp_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCompatibleWidthLimitDp(int value) {
            this.bitField0_ |= 8;
            this.compatibleWidthLimitDp_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCompatibleWidthLimitDp() {
            this.bitField0_ &= -9;
            this.compatibleWidthLimitDp_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasLargestWidthLimitDp() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getLargestWidthLimitDp() {
            return this.largestWidthLimitDp_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLargestWidthLimitDp(int value) {
            this.bitField0_ |= 16;
            this.largestWidthLimitDp_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLargestWidthLimitDp() {
            this.bitField0_ &= -17;
            this.largestWidthLimitDp_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasSeinfo() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getSeinfo() {
            return this.seinfo_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getSeinfoBytes() {
            return ByteString.copyFromUtf8(this.seinfo_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSeinfo(String value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.seinfo_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSeinfo() {
            this.bitField0_ &= -33;
            this.seinfo_ = getDefaultInstance().getSeinfo();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSeinfoBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.seinfo_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasSeinfoUser() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getSeinfoUser() {
            return this.seinfoUser_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getSeinfoUserBytes() {
            return ByteString.copyFromUtf8(this.seinfoUser_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSeinfoUser(String value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.seinfoUser_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSeinfoUser() {
            this.bitField0_ &= -65;
            this.seinfoUser_ = getDefaultInstance().getSeinfoUser();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSeinfoUserBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.seinfoUser_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasDeviceProtectedDataDir() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getDeviceProtectedDataDir() {
            return this.deviceProtectedDataDir_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getDeviceProtectedDataDirBytes() {
            return ByteString.copyFromUtf8(this.deviceProtectedDataDir_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeviceProtectedDataDir(String value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.deviceProtectedDataDir_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDeviceProtectedDataDir() {
            this.bitField0_ &= -129;
            this.deviceProtectedDataDir_ = getDefaultInstance().getDeviceProtectedDataDir();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDeviceProtectedDataDirBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.deviceProtectedDataDir_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasCredentialProtectedDataDir() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getCredentialProtectedDataDir() {
            return this.credentialProtectedDataDir_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getCredentialProtectedDataDirBytes() {
            return ByteString.copyFromUtf8(this.credentialProtectedDataDir_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCredentialProtectedDataDir(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.credentialProtectedDataDir_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCredentialProtectedDataDir() {
            this.bitField0_ &= -257;
            this.credentialProtectedDataDir_ = getDefaultInstance().getCredentialProtectedDataDir();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCredentialProtectedDataDirBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.credentialProtectedDataDir_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public List<String> getSharedLibraryFilesList() {
            return this.sharedLibraryFiles_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getSharedLibraryFilesCount() {
            return this.sharedLibraryFiles_.size();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getSharedLibraryFiles(int index) {
            return this.sharedLibraryFiles_.get(index);
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getSharedLibraryFilesBytes(int index) {
            return ByteString.copyFromUtf8(this.sharedLibraryFiles_.get(index));
        }

        private void ensureSharedLibraryFilesIsMutable() {
            if (!this.sharedLibraryFiles_.isModifiable()) {
                this.sharedLibraryFiles_ = GeneratedMessageLite.mutableCopy(this.sharedLibraryFiles_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSharedLibraryFiles(int index, String value) {
            if (value != null) {
                ensureSharedLibraryFilesIsMutable();
                this.sharedLibraryFiles_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSharedLibraryFiles(String value) {
            if (value != null) {
                ensureSharedLibraryFilesIsMutable();
                this.sharedLibraryFiles_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllSharedLibraryFiles(Iterable<String> values) {
            ensureSharedLibraryFilesIsMutable();
            AbstractMessageLite.addAll(values, this.sharedLibraryFiles_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSharedLibraryFiles() {
            this.sharedLibraryFiles_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addSharedLibraryFilesBytes(ByteString value) {
            if (value != null) {
                ensureSharedLibraryFilesIsMutable();
                this.sharedLibraryFiles_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasManageSpaceActivityName() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getManageSpaceActivityName() {
            return this.manageSpaceActivityName_;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getManageSpaceActivityNameBytes() {
            return ByteString.copyFromUtf8(this.manageSpaceActivityName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setManageSpaceActivityName(String value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.manageSpaceActivityName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearManageSpaceActivityName() {
            this.bitField0_ &= -513;
            this.manageSpaceActivityName_ = getDefaultInstance().getManageSpaceActivityName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setManageSpaceActivityNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.manageSpaceActivityName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasDescriptionRes() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getDescriptionRes() {
            return this.descriptionRes_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDescriptionRes(int value) {
            this.bitField0_ |= 1024;
            this.descriptionRes_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDescriptionRes() {
            this.bitField0_ &= -1025;
            this.descriptionRes_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasUiOptions() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getUiOptions() {
            return this.uiOptions_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUiOptions(int value) {
            this.bitField0_ |= 2048;
            this.uiOptions_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUiOptions() {
            this.bitField0_ &= -2049;
            this.uiOptions_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasSupportsRtl() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean getSupportsRtl() {
            return this.supportsRtl_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSupportsRtl(boolean value) {
            this.bitField0_ |= 4096;
            this.supportsRtl_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSupportsRtl() {
            this.bitField0_ &= -4097;
            this.supportsRtl_ = false;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasContent() {
            return this.fullBackupContentCase_ == 15;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public String getContent() {
            if (this.fullBackupContentCase_ == 15) {
                return (String) this.fullBackupContent_;
            }
            return "";
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public ByteString getContentBytes() {
            String ref = "";
            if (this.fullBackupContentCase_ == 15) {
                ref = (String) this.fullBackupContent_;
            }
            return ByteString.copyFromUtf8(ref);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setContent(String value) {
            if (value != null) {
                this.fullBackupContentCase_ = 15;
                this.fullBackupContent_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearContent() {
            if (this.fullBackupContentCase_ == 15) {
                this.fullBackupContentCase_ = 0;
                this.fullBackupContent_ = null;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setContentBytes(ByteString value) {
            if (value != null) {
                this.fullBackupContentCase_ = 15;
                this.fullBackupContent_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasIsFullBackup() {
            return this.fullBackupContentCase_ == 16;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean getIsFullBackup() {
            if (this.fullBackupContentCase_ == 16) {
                return ((Boolean) this.fullBackupContent_).booleanValue();
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsFullBackup(boolean value) {
            this.fullBackupContentCase_ = 16;
            this.fullBackupContent_ = Boolean.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsFullBackup() {
            if (this.fullBackupContentCase_ == 16) {
                this.fullBackupContentCase_ = 0;
                this.fullBackupContent_ = null;
            }
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasNetworkSecurityConfigRes() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getNetworkSecurityConfigRes() {
            return this.networkSecurityConfigRes_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNetworkSecurityConfigRes(int value) {
            this.bitField0_ |= 32768;
            this.networkSecurityConfigRes_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNetworkSecurityConfigRes() {
            this.bitField0_ &= -32769;
            this.networkSecurityConfigRes_ = 0;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public boolean hasCategory() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
        public int getCategory() {
            return this.category_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCategory(int value) {
            this.bitField0_ |= 65536;
            this.category_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCategory() {
            this.bitField0_ &= -65537;
            this.category_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getClassName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getTaskAffinity());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.requiresSmallestWidthDp_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.compatibleWidthLimitDp_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.largestWidthLimitDp_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeString(6, getSeinfo());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeString(7, getSeinfoUser());
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeString(8, getDeviceProtectedDataDir());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getCredentialProtectedDataDir());
            }
            for (int i = 0; i < this.sharedLibraryFiles_.size(); i++) {
                output.writeString(10, this.sharedLibraryFiles_.get(i));
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeString(11, getManageSpaceActivityName());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeInt32(12, this.descriptionRes_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(13, this.uiOptions_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeBool(14, this.supportsRtl_);
            }
            if (this.fullBackupContentCase_ == 15) {
                output.writeString(15, getContent());
            }
            if (this.fullBackupContentCase_ == 16) {
                output.writeBool(16, ((Boolean) this.fullBackupContent_).booleanValue());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeInt32(17, this.networkSecurityConfigRes_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeInt32(18, this.category_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getClassName());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getTaskAffinity());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.requiresSmallestWidthDp_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.compatibleWidthLimitDp_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.largestWidthLimitDp_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeStringSize(6, getSeinfo());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeStringSize(7, getSeinfoUser());
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeStringSize(8, getDeviceProtectedDataDir());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getCredentialProtectedDataDir());
            }
            int dataSize = 0;
            for (int i = 0; i < this.sharedLibraryFiles_.size(); i++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.sharedLibraryFiles_.get(i));
            }
            int size3 = size2 + dataSize + (getSharedLibraryFilesList().size() * 1);
            if ((this.bitField0_ & 512) == 512) {
                size3 += CodedOutputStream.computeStringSize(11, getManageSpaceActivityName());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size3 += CodedOutputStream.computeInt32Size(12, this.descriptionRes_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size3 += CodedOutputStream.computeInt32Size(13, this.uiOptions_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size3 += CodedOutputStream.computeBoolSize(14, this.supportsRtl_);
            }
            if (this.fullBackupContentCase_ == 15) {
                size3 += CodedOutputStream.computeStringSize(15, getContent());
            }
            if (this.fullBackupContentCase_ == 16) {
                size3 += CodedOutputStream.computeBoolSize(16, ((Boolean) this.fullBackupContent_).booleanValue());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size3 += CodedOutputStream.computeInt32Size(17, this.networkSecurityConfigRes_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size3 += CodedOutputStream.computeInt32Size(18, this.category_);
            }
            int size4 = size3 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static Detail parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Detail parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Detail parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Detail parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Detail parseFrom(InputStream input) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Detail parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Detail parseDelimitedFrom(InputStream input) throws IOException {
            return (Detail) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Detail parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Detail) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Detail parseFrom(CodedInputStream input) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Detail parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Detail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Detail prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Detail, Builder> implements DetailOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Detail.DEFAULT_INSTANCE);
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public FullBackupContentCase getFullBackupContentCase() {
                return ((Detail) this.instance).getFullBackupContentCase();
            }

            public Builder clearFullBackupContent() {
                copyOnWrite();
                ((Detail) this.instance).clearFullBackupContent();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasClassName() {
                return ((Detail) this.instance).hasClassName();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getClassName() {
                return ((Detail) this.instance).getClassName();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getClassNameBytes() {
                return ((Detail) this.instance).getClassNameBytes();
            }

            public Builder setClassName(String value) {
                copyOnWrite();
                ((Detail) this.instance).setClassName(value);
                return this;
            }

            public Builder clearClassName() {
                copyOnWrite();
                ((Detail) this.instance).clearClassName();
                return this;
            }

            public Builder setClassNameBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setClassNameBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasTaskAffinity() {
                return ((Detail) this.instance).hasTaskAffinity();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getTaskAffinity() {
                return ((Detail) this.instance).getTaskAffinity();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getTaskAffinityBytes() {
                return ((Detail) this.instance).getTaskAffinityBytes();
            }

            public Builder setTaskAffinity(String value) {
                copyOnWrite();
                ((Detail) this.instance).setTaskAffinity(value);
                return this;
            }

            public Builder clearTaskAffinity() {
                copyOnWrite();
                ((Detail) this.instance).clearTaskAffinity();
                return this;
            }

            public Builder setTaskAffinityBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setTaskAffinityBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasRequiresSmallestWidthDp() {
                return ((Detail) this.instance).hasRequiresSmallestWidthDp();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getRequiresSmallestWidthDp() {
                return ((Detail) this.instance).getRequiresSmallestWidthDp();
            }

            public Builder setRequiresSmallestWidthDp(int value) {
                copyOnWrite();
                ((Detail) this.instance).setRequiresSmallestWidthDp(value);
                return this;
            }

            public Builder clearRequiresSmallestWidthDp() {
                copyOnWrite();
                ((Detail) this.instance).clearRequiresSmallestWidthDp();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasCompatibleWidthLimitDp() {
                return ((Detail) this.instance).hasCompatibleWidthLimitDp();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getCompatibleWidthLimitDp() {
                return ((Detail) this.instance).getCompatibleWidthLimitDp();
            }

            public Builder setCompatibleWidthLimitDp(int value) {
                copyOnWrite();
                ((Detail) this.instance).setCompatibleWidthLimitDp(value);
                return this;
            }

            public Builder clearCompatibleWidthLimitDp() {
                copyOnWrite();
                ((Detail) this.instance).clearCompatibleWidthLimitDp();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasLargestWidthLimitDp() {
                return ((Detail) this.instance).hasLargestWidthLimitDp();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getLargestWidthLimitDp() {
                return ((Detail) this.instance).getLargestWidthLimitDp();
            }

            public Builder setLargestWidthLimitDp(int value) {
                copyOnWrite();
                ((Detail) this.instance).setLargestWidthLimitDp(value);
                return this;
            }

            public Builder clearLargestWidthLimitDp() {
                copyOnWrite();
                ((Detail) this.instance).clearLargestWidthLimitDp();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasSeinfo() {
                return ((Detail) this.instance).hasSeinfo();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getSeinfo() {
                return ((Detail) this.instance).getSeinfo();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getSeinfoBytes() {
                return ((Detail) this.instance).getSeinfoBytes();
            }

            public Builder setSeinfo(String value) {
                copyOnWrite();
                ((Detail) this.instance).setSeinfo(value);
                return this;
            }

            public Builder clearSeinfo() {
                copyOnWrite();
                ((Detail) this.instance).clearSeinfo();
                return this;
            }

            public Builder setSeinfoBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setSeinfoBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasSeinfoUser() {
                return ((Detail) this.instance).hasSeinfoUser();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getSeinfoUser() {
                return ((Detail) this.instance).getSeinfoUser();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getSeinfoUserBytes() {
                return ((Detail) this.instance).getSeinfoUserBytes();
            }

            public Builder setSeinfoUser(String value) {
                copyOnWrite();
                ((Detail) this.instance).setSeinfoUser(value);
                return this;
            }

            public Builder clearSeinfoUser() {
                copyOnWrite();
                ((Detail) this.instance).clearSeinfoUser();
                return this;
            }

            public Builder setSeinfoUserBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setSeinfoUserBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasDeviceProtectedDataDir() {
                return ((Detail) this.instance).hasDeviceProtectedDataDir();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getDeviceProtectedDataDir() {
                return ((Detail) this.instance).getDeviceProtectedDataDir();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getDeviceProtectedDataDirBytes() {
                return ((Detail) this.instance).getDeviceProtectedDataDirBytes();
            }

            public Builder setDeviceProtectedDataDir(String value) {
                copyOnWrite();
                ((Detail) this.instance).setDeviceProtectedDataDir(value);
                return this;
            }

            public Builder clearDeviceProtectedDataDir() {
                copyOnWrite();
                ((Detail) this.instance).clearDeviceProtectedDataDir();
                return this;
            }

            public Builder setDeviceProtectedDataDirBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setDeviceProtectedDataDirBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasCredentialProtectedDataDir() {
                return ((Detail) this.instance).hasCredentialProtectedDataDir();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getCredentialProtectedDataDir() {
                return ((Detail) this.instance).getCredentialProtectedDataDir();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getCredentialProtectedDataDirBytes() {
                return ((Detail) this.instance).getCredentialProtectedDataDirBytes();
            }

            public Builder setCredentialProtectedDataDir(String value) {
                copyOnWrite();
                ((Detail) this.instance).setCredentialProtectedDataDir(value);
                return this;
            }

            public Builder clearCredentialProtectedDataDir() {
                copyOnWrite();
                ((Detail) this.instance).clearCredentialProtectedDataDir();
                return this;
            }

            public Builder setCredentialProtectedDataDirBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setCredentialProtectedDataDirBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public List<String> getSharedLibraryFilesList() {
                return Collections.unmodifiableList(((Detail) this.instance).getSharedLibraryFilesList());
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getSharedLibraryFilesCount() {
                return ((Detail) this.instance).getSharedLibraryFilesCount();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getSharedLibraryFiles(int index) {
                return ((Detail) this.instance).getSharedLibraryFiles(index);
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getSharedLibraryFilesBytes(int index) {
                return ((Detail) this.instance).getSharedLibraryFilesBytes(index);
            }

            public Builder setSharedLibraryFiles(int index, String value) {
                copyOnWrite();
                ((Detail) this.instance).setSharedLibraryFiles(index, value);
                return this;
            }

            public Builder addSharedLibraryFiles(String value) {
                copyOnWrite();
                ((Detail) this.instance).addSharedLibraryFiles(value);
                return this;
            }

            public Builder addAllSharedLibraryFiles(Iterable<String> values) {
                copyOnWrite();
                ((Detail) this.instance).addAllSharedLibraryFiles(values);
                return this;
            }

            public Builder clearSharedLibraryFiles() {
                copyOnWrite();
                ((Detail) this.instance).clearSharedLibraryFiles();
                return this;
            }

            public Builder addSharedLibraryFilesBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).addSharedLibraryFilesBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasManageSpaceActivityName() {
                return ((Detail) this.instance).hasManageSpaceActivityName();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getManageSpaceActivityName() {
                return ((Detail) this.instance).getManageSpaceActivityName();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getManageSpaceActivityNameBytes() {
                return ((Detail) this.instance).getManageSpaceActivityNameBytes();
            }

            public Builder setManageSpaceActivityName(String value) {
                copyOnWrite();
                ((Detail) this.instance).setManageSpaceActivityName(value);
                return this;
            }

            public Builder clearManageSpaceActivityName() {
                copyOnWrite();
                ((Detail) this.instance).clearManageSpaceActivityName();
                return this;
            }

            public Builder setManageSpaceActivityNameBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setManageSpaceActivityNameBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasDescriptionRes() {
                return ((Detail) this.instance).hasDescriptionRes();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getDescriptionRes() {
                return ((Detail) this.instance).getDescriptionRes();
            }

            public Builder setDescriptionRes(int value) {
                copyOnWrite();
                ((Detail) this.instance).setDescriptionRes(value);
                return this;
            }

            public Builder clearDescriptionRes() {
                copyOnWrite();
                ((Detail) this.instance).clearDescriptionRes();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasUiOptions() {
                return ((Detail) this.instance).hasUiOptions();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getUiOptions() {
                return ((Detail) this.instance).getUiOptions();
            }

            public Builder setUiOptions(int value) {
                copyOnWrite();
                ((Detail) this.instance).setUiOptions(value);
                return this;
            }

            public Builder clearUiOptions() {
                copyOnWrite();
                ((Detail) this.instance).clearUiOptions();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasSupportsRtl() {
                return ((Detail) this.instance).hasSupportsRtl();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean getSupportsRtl() {
                return ((Detail) this.instance).getSupportsRtl();
            }

            public Builder setSupportsRtl(boolean value) {
                copyOnWrite();
                ((Detail) this.instance).setSupportsRtl(value);
                return this;
            }

            public Builder clearSupportsRtl() {
                copyOnWrite();
                ((Detail) this.instance).clearSupportsRtl();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasContent() {
                return ((Detail) this.instance).hasContent();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public String getContent() {
                return ((Detail) this.instance).getContent();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public ByteString getContentBytes() {
                return ((Detail) this.instance).getContentBytes();
            }

            public Builder setContent(String value) {
                copyOnWrite();
                ((Detail) this.instance).setContent(value);
                return this;
            }

            public Builder clearContent() {
                copyOnWrite();
                ((Detail) this.instance).clearContent();
                return this;
            }

            public Builder setContentBytes(ByteString value) {
                copyOnWrite();
                ((Detail) this.instance).setContentBytes(value);
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasIsFullBackup() {
                return ((Detail) this.instance).hasIsFullBackup();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean getIsFullBackup() {
                return ((Detail) this.instance).getIsFullBackup();
            }

            public Builder setIsFullBackup(boolean value) {
                copyOnWrite();
                ((Detail) this.instance).setIsFullBackup(value);
                return this;
            }

            public Builder clearIsFullBackup() {
                copyOnWrite();
                ((Detail) this.instance).clearIsFullBackup();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasNetworkSecurityConfigRes() {
                return ((Detail) this.instance).hasNetworkSecurityConfigRes();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getNetworkSecurityConfigRes() {
                return ((Detail) this.instance).getNetworkSecurityConfigRes();
            }

            public Builder setNetworkSecurityConfigRes(int value) {
                copyOnWrite();
                ((Detail) this.instance).setNetworkSecurityConfigRes(value);
                return this;
            }

            public Builder clearNetworkSecurityConfigRes() {
                copyOnWrite();
                ((Detail) this.instance).clearNetworkSecurityConfigRes();
                return this;
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public boolean hasCategory() {
                return ((Detail) this.instance).hasCategory();
            }

            @Override // android.content.pm.ApplicationInfoProto.DetailOrBuilder
            public int getCategory() {
                return ((Detail) this.instance).getCategory();
            }

            public Builder setCategory(int value) {
                copyOnWrite();
                ((Detail) this.instance).setCategory(value);
                return this;
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((Detail) this.instance).clearCategory();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean z = true;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Detail();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.sharedLibraryFiles_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Detail other = (Detail) arg1;
                    this.className_ = visitor.visitString(hasClassName(), this.className_, other.hasClassName(), other.className_);
                    this.taskAffinity_ = visitor.visitString(hasTaskAffinity(), this.taskAffinity_, other.hasTaskAffinity(), other.taskAffinity_);
                    this.requiresSmallestWidthDp_ = visitor.visitInt(hasRequiresSmallestWidthDp(), this.requiresSmallestWidthDp_, other.hasRequiresSmallestWidthDp(), other.requiresSmallestWidthDp_);
                    this.compatibleWidthLimitDp_ = visitor.visitInt(hasCompatibleWidthLimitDp(), this.compatibleWidthLimitDp_, other.hasCompatibleWidthLimitDp(), other.compatibleWidthLimitDp_);
                    this.largestWidthLimitDp_ = visitor.visitInt(hasLargestWidthLimitDp(), this.largestWidthLimitDp_, other.hasLargestWidthLimitDp(), other.largestWidthLimitDp_);
                    this.seinfo_ = visitor.visitString(hasSeinfo(), this.seinfo_, other.hasSeinfo(), other.seinfo_);
                    this.seinfoUser_ = visitor.visitString(hasSeinfoUser(), this.seinfoUser_, other.hasSeinfoUser(), other.seinfoUser_);
                    this.deviceProtectedDataDir_ = visitor.visitString(hasDeviceProtectedDataDir(), this.deviceProtectedDataDir_, other.hasDeviceProtectedDataDir(), other.deviceProtectedDataDir_);
                    this.credentialProtectedDataDir_ = visitor.visitString(hasCredentialProtectedDataDir(), this.credentialProtectedDataDir_, other.hasCredentialProtectedDataDir(), other.credentialProtectedDataDir_);
                    this.sharedLibraryFiles_ = visitor.visitList(this.sharedLibraryFiles_, other.sharedLibraryFiles_);
                    this.manageSpaceActivityName_ = visitor.visitString(hasManageSpaceActivityName(), this.manageSpaceActivityName_, other.hasManageSpaceActivityName(), other.manageSpaceActivityName_);
                    this.descriptionRes_ = visitor.visitInt(hasDescriptionRes(), this.descriptionRes_, other.hasDescriptionRes(), other.descriptionRes_);
                    this.uiOptions_ = visitor.visitInt(hasUiOptions(), this.uiOptions_, other.hasUiOptions(), other.uiOptions_);
                    this.supportsRtl_ = visitor.visitBoolean(hasSupportsRtl(), this.supportsRtl_, other.hasSupportsRtl(), other.supportsRtl_);
                    this.networkSecurityConfigRes_ = visitor.visitInt(hasNetworkSecurityConfigRes(), this.networkSecurityConfigRes_, other.hasNetworkSecurityConfigRes(), other.networkSecurityConfigRes_);
                    this.category_ = visitor.visitInt(hasCategory(), this.category_, other.hasCategory(), other.category_);
                    int i = AnonymousClass1.$SwitchMap$android$content$pm$ApplicationInfoProto$Detail$FullBackupContentCase[other.getFullBackupContentCase().ordinal()];
                    if (i == 1) {
                        if (this.fullBackupContentCase_ != 15) {
                            z = false;
                        }
                        this.fullBackupContent_ = visitor.visitOneofString(z, this.fullBackupContent_, other.fullBackupContent_);
                    } else if (i == 2) {
                        if (this.fullBackupContentCase_ != 16) {
                            z = false;
                        }
                        this.fullBackupContent_ = visitor.visitOneofBoolean(z, this.fullBackupContent_, other.fullBackupContent_);
                    } else if (i == 3) {
                        if (this.fullBackupContentCase_ == 0) {
                            z = false;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.fullBackupContentCase_;
                        if (i2 != 0) {
                            this.fullBackupContentCase_ = i2;
                        }
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
                                case 10:
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.className_ = s;
                                    break;
                                case 18:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 2;
                                    this.taskAffinity_ = s2;
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.requiresSmallestWidthDp_ = input.readInt32();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.compatibleWidthLimitDp_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.largestWidthLimitDp_ = input.readInt32();
                                    break;
                                case 50:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 32;
                                    this.seinfo_ = s3;
                                    break;
                                case 58:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 64;
                                    this.seinfoUser_ = s4;
                                    break;
                                case 66:
                                    String s5 = input.readString();
                                    this.bitField0_ |= 128;
                                    this.deviceProtectedDataDir_ = s5;
                                    break;
                                case 74:
                                    String s6 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.credentialProtectedDataDir_ = s6;
                                    break;
                                case 82:
                                    String s7 = input.readString();
                                    if (!this.sharedLibraryFiles_.isModifiable()) {
                                        this.sharedLibraryFiles_ = GeneratedMessageLite.mutableCopy(this.sharedLibraryFiles_);
                                    }
                                    this.sharedLibraryFiles_.add(s7);
                                    break;
                                case 90:
                                    String s8 = input.readString();
                                    this.bitField0_ |= 512;
                                    this.manageSpaceActivityName_ = s8;
                                    break;
                                case 96:
                                    this.bitField0_ |= 1024;
                                    this.descriptionRes_ = input.readInt32();
                                    break;
                                case 104:
                                    this.bitField0_ |= 2048;
                                    this.uiOptions_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 4096;
                                    this.supportsRtl_ = input.readBool();
                                    break;
                                case 122:
                                    String s9 = input.readString();
                                    this.fullBackupContentCase_ = 15;
                                    this.fullBackupContent_ = s9;
                                    break;
                                case 128:
                                    this.fullBackupContentCase_ = 16;
                                    this.fullBackupContent_ = Boolean.valueOf(input.readBool());
                                    break;
                                case 136:
                                    this.bitField0_ |= 32768;
                                    this.networkSecurityConfigRes_ = input.readInt32();
                                    break;
                                case 144:
                                    this.bitField0_ |= 65536;
                                    this.category_ = input.readInt32();
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
                        synchronized (Detail.class) {
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

        public static Detail getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Detail> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: android.content.pm.ApplicationInfoProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$content$pm$ApplicationInfoProto$Detail$FullBackupContentCase = new int[Detail.FullBackupContentCase.values().length];

        static {
            try {
                $SwitchMap$android$content$pm$ApplicationInfoProto$Detail$FullBackupContentCase[Detail.FullBackupContentCase.CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$content$pm$ApplicationInfoProto$Detail$FullBackupContentCase[Detail.FullBackupContentCase.IS_FULL_BACKUP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$content$pm$ApplicationInfoProto$Detail$FullBackupContentCase[Detail.FullBackupContentCase.FULLBACKUPCONTENT_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasPackage() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public PackageItemInfoProto getPackage() {
        PackageItemInfoProto packageItemInfoProto = this.package_;
        return packageItemInfoProto == null ? PackageItemInfoProto.getDefaultInstance() : packageItemInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackage(PackageItemInfoProto value) {
        if (value != null) {
            this.package_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackage(PackageItemInfoProto.Builder builderForValue) {
        this.package_ = (PackageItemInfoProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePackage(PackageItemInfoProto value) {
        PackageItemInfoProto packageItemInfoProto = this.package_;
        if (packageItemInfoProto == null || packageItemInfoProto == PackageItemInfoProto.getDefaultInstance()) {
            this.package_ = value;
        } else {
            this.package_ = (PackageItemInfoProto) ((PackageItemInfoProto.Builder) PackageItemInfoProto.newBuilder(this.package_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackage() {
        this.package_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasPermission() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getPermission() {
        return this.permission_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getPermissionBytes() {
        return ByteString.copyFromUtf8(this.permission_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPermission(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.permission_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPermission() {
        this.bitField0_ &= -3;
        this.permission_ = getDefaultInstance().getPermission();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPermissionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.permission_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasProcessName() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getProcessName() {
        return this.processName_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getProcessNameBytes() {
        return ByteString.copyFromUtf8(this.processName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessName(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.processName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessName() {
        this.bitField0_ &= -5;
        this.processName_ = getDefaultInstance().getProcessName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.processName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 8;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -9;
        this.uid_ = 0;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 16;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -17;
        this.flags_ = 0;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasPrivateFlags() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getPrivateFlags() {
        return this.privateFlags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrivateFlags(int value) {
        this.bitField0_ |= 32;
        this.privateFlags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrivateFlags() {
        this.bitField0_ &= -33;
        this.privateFlags_ = 0;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasTheme() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getTheme() {
        return this.theme_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTheme(int value) {
        this.bitField0_ |= 64;
        this.theme_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTheme() {
        this.bitField0_ &= -65;
        this.theme_ = 0;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasSourceDir() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getSourceDir() {
        return this.sourceDir_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getSourceDirBytes() {
        return ByteString.copyFromUtf8(this.sourceDir_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceDir(String value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.sourceDir_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSourceDir() {
        this.bitField0_ &= -129;
        this.sourceDir_ = getDefaultInstance().getSourceDir();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSourceDirBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.sourceDir_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasPublicSourceDir() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getPublicSourceDir() {
        return this.publicSourceDir_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getPublicSourceDirBytes() {
        return ByteString.copyFromUtf8(this.publicSourceDir_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPublicSourceDir(String value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.publicSourceDir_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPublicSourceDir() {
        this.bitField0_ &= -257;
        this.publicSourceDir_ = getDefaultInstance().getPublicSourceDir();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPublicSourceDirBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.publicSourceDir_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public List<String> getSplitSourceDirsList() {
        return this.splitSourceDirs_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getSplitSourceDirsCount() {
        return this.splitSourceDirs_.size();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getSplitSourceDirs(int index) {
        return this.splitSourceDirs_.get(index);
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getSplitSourceDirsBytes(int index) {
        return ByteString.copyFromUtf8(this.splitSourceDirs_.get(index));
    }

    private void ensureSplitSourceDirsIsMutable() {
        if (!this.splitSourceDirs_.isModifiable()) {
            this.splitSourceDirs_ = GeneratedMessageLite.mutableCopy(this.splitSourceDirs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSplitSourceDirs(int index, String value) {
        if (value != null) {
            ensureSplitSourceDirsIsMutable();
            this.splitSourceDirs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplitSourceDirs(String value) {
        if (value != null) {
            ensureSplitSourceDirsIsMutable();
            this.splitSourceDirs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSplitSourceDirs(Iterable<String> values) {
        ensureSplitSourceDirsIsMutable();
        AbstractMessageLite.addAll(values, this.splitSourceDirs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSplitSourceDirs() {
        this.splitSourceDirs_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplitSourceDirsBytes(ByteString value) {
        if (value != null) {
            ensureSplitSourceDirsIsMutable();
            this.splitSourceDirs_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public List<String> getSplitPublicSourceDirsList() {
        return this.splitPublicSourceDirs_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getSplitPublicSourceDirsCount() {
        return this.splitPublicSourceDirs_.size();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getSplitPublicSourceDirs(int index) {
        return this.splitPublicSourceDirs_.get(index);
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getSplitPublicSourceDirsBytes(int index) {
        return ByteString.copyFromUtf8(this.splitPublicSourceDirs_.get(index));
    }

    private void ensureSplitPublicSourceDirsIsMutable() {
        if (!this.splitPublicSourceDirs_.isModifiable()) {
            this.splitPublicSourceDirs_ = GeneratedMessageLite.mutableCopy(this.splitPublicSourceDirs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSplitPublicSourceDirs(int index, String value) {
        if (value != null) {
            ensureSplitPublicSourceDirsIsMutable();
            this.splitPublicSourceDirs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplitPublicSourceDirs(String value) {
        if (value != null) {
            ensureSplitPublicSourceDirsIsMutable();
            this.splitPublicSourceDirs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSplitPublicSourceDirs(Iterable<String> values) {
        ensureSplitPublicSourceDirsIsMutable();
        AbstractMessageLite.addAll(values, this.splitPublicSourceDirs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSplitPublicSourceDirs() {
        this.splitPublicSourceDirs_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplitPublicSourceDirsBytes(ByteString value) {
        if (value != null) {
            ensureSplitPublicSourceDirsIsMutable();
            this.splitPublicSourceDirs_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public List<String> getResourceDirsList() {
        return this.resourceDirs_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getResourceDirsCount() {
        return this.resourceDirs_.size();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getResourceDirs(int index) {
        return this.resourceDirs_.get(index);
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getResourceDirsBytes(int index) {
        return ByteString.copyFromUtf8(this.resourceDirs_.get(index));
    }

    private void ensureResourceDirsIsMutable() {
        if (!this.resourceDirs_.isModifiable()) {
            this.resourceDirs_ = GeneratedMessageLite.mutableCopy(this.resourceDirs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResourceDirs(int index, String value) {
        if (value != null) {
            ensureResourceDirsIsMutable();
            this.resourceDirs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResourceDirs(String value) {
        if (value != null) {
            ensureResourceDirsIsMutable();
            this.resourceDirs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllResourceDirs(Iterable<String> values) {
        ensureResourceDirsIsMutable();
        AbstractMessageLite.addAll(values, this.resourceDirs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResourceDirs() {
        this.resourceDirs_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResourceDirsBytes(ByteString value) {
        if (value != null) {
            ensureResourceDirsIsMutable();
            this.resourceDirs_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasDataDir() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getDataDir() {
        return this.dataDir_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getDataDirBytes() {
        return ByteString.copyFromUtf8(this.dataDir_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataDir(String value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.dataDir_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataDir() {
        this.bitField0_ &= -513;
        this.dataDir_ = getDefaultInstance().getDataDir();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataDirBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.dataDir_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasClassLoaderName() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getClassLoaderName() {
        return this.classLoaderName_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getClassLoaderNameBytes() {
        return ByteString.copyFromUtf8(this.classLoaderName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClassLoaderName(String value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.classLoaderName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClassLoaderName() {
        this.bitField0_ &= -1025;
        this.classLoaderName_ = getDefaultInstance().getClassLoaderName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClassLoaderNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.classLoaderName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public List<String> getSplitClassLoaderNamesList() {
        return this.splitClassLoaderNames_;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public int getSplitClassLoaderNamesCount() {
        return this.splitClassLoaderNames_.size();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public String getSplitClassLoaderNames(int index) {
        return this.splitClassLoaderNames_.get(index);
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public ByteString getSplitClassLoaderNamesBytes(int index) {
        return ByteString.copyFromUtf8(this.splitClassLoaderNames_.get(index));
    }

    private void ensureSplitClassLoaderNamesIsMutable() {
        if (!this.splitClassLoaderNames_.isModifiable()) {
            this.splitClassLoaderNames_ = GeneratedMessageLite.mutableCopy(this.splitClassLoaderNames_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSplitClassLoaderNames(int index, String value) {
        if (value != null) {
            ensureSplitClassLoaderNamesIsMutable();
            this.splitClassLoaderNames_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplitClassLoaderNames(String value) {
        if (value != null) {
            ensureSplitClassLoaderNamesIsMutable();
            this.splitClassLoaderNames_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSplitClassLoaderNames(Iterable<String> values) {
        ensureSplitClassLoaderNamesIsMutable();
        AbstractMessageLite.addAll(values, this.splitClassLoaderNames_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSplitClassLoaderNames() {
        this.splitClassLoaderNames_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSplitClassLoaderNamesBytes(ByteString value) {
        if (value != null) {
            ensureSplitClassLoaderNamesIsMutable();
            this.splitClassLoaderNames_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasVersion() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public Version getVersion() {
        Version version = this.version_;
        return version == null ? Version.getDefaultInstance() : version;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(Version value) {
        if (value != null) {
            this.version_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(Version.Builder builderForValue) {
        this.version_ = (Version) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVersion(Version value) {
        Version version = this.version_;
        if (version == null || version == Version.getDefaultInstance()) {
            this.version_ = value;
        } else {
            this.version_ = (Version) ((Version.Builder) Version.newBuilder(this.version_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersion() {
        this.version_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public boolean hasDetail() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.content.pm.ApplicationInfoProtoOrBuilder
    public Detail getDetail() {
        Detail detail = this.detail_;
        return detail == null ? Detail.getDefaultInstance() : detail;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDetail(Detail value) {
        if (value != null) {
            this.detail_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDetail(Detail.Builder builderForValue) {
        this.detail_ = (Detail) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDetail(Detail value) {
        Detail detail = this.detail_;
        if (detail == null || detail == Detail.getDefaultInstance()) {
            this.detail_ = value;
        } else {
            this.detail_ = (Detail) ((Detail.Builder) Detail.newBuilder(this.detail_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDetail() {
        this.detail_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getPermission());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getProcessName());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.uid_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.flags_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.privateFlags_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.theme_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeString(8, getSourceDir());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeString(9, getPublicSourceDir());
        }
        for (int i = 0; i < this.splitSourceDirs_.size(); i++) {
            output.writeString(10, this.splitSourceDirs_.get(i));
        }
        for (int i2 = 0; i2 < this.splitPublicSourceDirs_.size(); i2++) {
            output.writeString(11, this.splitPublicSourceDirs_.get(i2));
        }
        for (int i3 = 0; i3 < this.resourceDirs_.size(); i3++) {
            output.writeString(12, this.resourceDirs_.get(i3));
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeString(13, getDataDir());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeString(14, getClassLoaderName());
        }
        for (int i4 = 0; i4 < this.splitClassLoaderNames_.size(); i4++) {
            output.writeString(15, this.splitClassLoaderNames_.get(i4));
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(16, getVersion());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(17, getDetail());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getPermission());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getProcessName());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.uid_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.flags_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.privateFlags_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.theme_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeStringSize(8, getSourceDir());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeStringSize(9, getPublicSourceDir());
        }
        int dataSize = 0;
        for (int i = 0; i < this.splitSourceDirs_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.splitSourceDirs_.get(i));
        }
        int size3 = size2 + dataSize + (getSplitSourceDirsList().size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.splitPublicSourceDirs_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeStringSizeNoTag(this.splitPublicSourceDirs_.get(i2));
        }
        int size4 = size3 + dataSize2 + (getSplitPublicSourceDirsList().size() * 1);
        int dataSize3 = 0;
        for (int i3 = 0; i3 < this.resourceDirs_.size(); i3++) {
            dataSize3 += CodedOutputStream.computeStringSizeNoTag(this.resourceDirs_.get(i3));
        }
        int size5 = size4 + dataSize3 + (getResourceDirsList().size() * 1);
        if ((this.bitField0_ & 512) == 512) {
            size5 += CodedOutputStream.computeStringSize(13, getDataDir());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size5 += CodedOutputStream.computeStringSize(14, getClassLoaderName());
        }
        int dataSize4 = 0;
        for (int i4 = 0; i4 < this.splitClassLoaderNames_.size(); i4++) {
            dataSize4 += CodedOutputStream.computeStringSizeNoTag(this.splitClassLoaderNames_.get(i4));
        }
        int size6 = size5 + dataSize4 + (getSplitClassLoaderNamesList().size() * 1);
        if ((this.bitField0_ & 2048) == 2048) {
            size6 += CodedOutputStream.computeMessageSize(16, getVersion());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size6 += CodedOutputStream.computeMessageSize(17, getDetail());
        }
        int size7 = size6 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size7;
        return size7;
    }

    public static ApplicationInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ApplicationInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ApplicationInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ApplicationInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ApplicationInfoProto parseFrom(InputStream input) throws IOException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ApplicationInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ApplicationInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ApplicationInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ApplicationInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ApplicationInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ApplicationInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ApplicationInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ApplicationInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ApplicationInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ApplicationInfoProto, Builder> implements ApplicationInfoProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(ApplicationInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasPackage() {
            return ((ApplicationInfoProto) this.instance).hasPackage();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public PackageItemInfoProto getPackage() {
            return ((ApplicationInfoProto) this.instance).getPackage();
        }

        public Builder setPackage(PackageItemInfoProto value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setPackage((ApplicationInfoProto) value);
            return this;
        }

        public Builder setPackage(PackageItemInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setPackage((ApplicationInfoProto) builderForValue);
            return this;
        }

        public Builder mergePackage(PackageItemInfoProto value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).mergePackage(value);
            return this;
        }

        public Builder clearPackage() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearPackage();
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasPermission() {
            return ((ApplicationInfoProto) this.instance).hasPermission();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getPermission() {
            return ((ApplicationInfoProto) this.instance).getPermission();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getPermissionBytes() {
            return ((ApplicationInfoProto) this.instance).getPermissionBytes();
        }

        public Builder setPermission(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setPermission(value);
            return this;
        }

        public Builder clearPermission() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearPermission();
            return this;
        }

        public Builder setPermissionBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setPermissionBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasProcessName() {
            return ((ApplicationInfoProto) this.instance).hasProcessName();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getProcessName() {
            return ((ApplicationInfoProto) this.instance).getProcessName();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getProcessNameBytes() {
            return ((ApplicationInfoProto) this.instance).getProcessNameBytes();
        }

        public Builder setProcessName(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setProcessName(value);
            return this;
        }

        public Builder clearProcessName() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearProcessName();
            return this;
        }

        public Builder setProcessNameBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setProcessNameBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasUid() {
            return ((ApplicationInfoProto) this.instance).hasUid();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getUid() {
            return ((ApplicationInfoProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearUid();
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasFlags() {
            return ((ApplicationInfoProto) this.instance).hasFlags();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getFlags() {
            return ((ApplicationInfoProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearFlags();
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasPrivateFlags() {
            return ((ApplicationInfoProto) this.instance).hasPrivateFlags();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getPrivateFlags() {
            return ((ApplicationInfoProto) this.instance).getPrivateFlags();
        }

        public Builder setPrivateFlags(int value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setPrivateFlags(value);
            return this;
        }

        public Builder clearPrivateFlags() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearPrivateFlags();
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasTheme() {
            return ((ApplicationInfoProto) this.instance).hasTheme();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getTheme() {
            return ((ApplicationInfoProto) this.instance).getTheme();
        }

        public Builder setTheme(int value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setTheme(value);
            return this;
        }

        public Builder clearTheme() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearTheme();
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasSourceDir() {
            return ((ApplicationInfoProto) this.instance).hasSourceDir();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getSourceDir() {
            return ((ApplicationInfoProto) this.instance).getSourceDir();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getSourceDirBytes() {
            return ((ApplicationInfoProto) this.instance).getSourceDirBytes();
        }

        public Builder setSourceDir(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setSourceDir(value);
            return this;
        }

        public Builder clearSourceDir() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearSourceDir();
            return this;
        }

        public Builder setSourceDirBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setSourceDirBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasPublicSourceDir() {
            return ((ApplicationInfoProto) this.instance).hasPublicSourceDir();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getPublicSourceDir() {
            return ((ApplicationInfoProto) this.instance).getPublicSourceDir();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getPublicSourceDirBytes() {
            return ((ApplicationInfoProto) this.instance).getPublicSourceDirBytes();
        }

        public Builder setPublicSourceDir(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setPublicSourceDir(value);
            return this;
        }

        public Builder clearPublicSourceDir() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearPublicSourceDir();
            return this;
        }

        public Builder setPublicSourceDirBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setPublicSourceDirBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public List<String> getSplitSourceDirsList() {
            return Collections.unmodifiableList(((ApplicationInfoProto) this.instance).getSplitSourceDirsList());
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getSplitSourceDirsCount() {
            return ((ApplicationInfoProto) this.instance).getSplitSourceDirsCount();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getSplitSourceDirs(int index) {
            return ((ApplicationInfoProto) this.instance).getSplitSourceDirs(index);
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getSplitSourceDirsBytes(int index) {
            return ((ApplicationInfoProto) this.instance).getSplitSourceDirsBytes(index);
        }

        public Builder setSplitSourceDirs(int index, String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setSplitSourceDirs(index, value);
            return this;
        }

        public Builder addSplitSourceDirs(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addSplitSourceDirs(value);
            return this;
        }

        public Builder addAllSplitSourceDirs(Iterable<String> values) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addAllSplitSourceDirs(values);
            return this;
        }

        public Builder clearSplitSourceDirs() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearSplitSourceDirs();
            return this;
        }

        public Builder addSplitSourceDirsBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addSplitSourceDirsBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public List<String> getSplitPublicSourceDirsList() {
            return Collections.unmodifiableList(((ApplicationInfoProto) this.instance).getSplitPublicSourceDirsList());
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getSplitPublicSourceDirsCount() {
            return ((ApplicationInfoProto) this.instance).getSplitPublicSourceDirsCount();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getSplitPublicSourceDirs(int index) {
            return ((ApplicationInfoProto) this.instance).getSplitPublicSourceDirs(index);
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getSplitPublicSourceDirsBytes(int index) {
            return ((ApplicationInfoProto) this.instance).getSplitPublicSourceDirsBytes(index);
        }

        public Builder setSplitPublicSourceDirs(int index, String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setSplitPublicSourceDirs(index, value);
            return this;
        }

        public Builder addSplitPublicSourceDirs(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addSplitPublicSourceDirs(value);
            return this;
        }

        public Builder addAllSplitPublicSourceDirs(Iterable<String> values) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addAllSplitPublicSourceDirs(values);
            return this;
        }

        public Builder clearSplitPublicSourceDirs() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearSplitPublicSourceDirs();
            return this;
        }

        public Builder addSplitPublicSourceDirsBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addSplitPublicSourceDirsBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public List<String> getResourceDirsList() {
            return Collections.unmodifiableList(((ApplicationInfoProto) this.instance).getResourceDirsList());
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getResourceDirsCount() {
            return ((ApplicationInfoProto) this.instance).getResourceDirsCount();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getResourceDirs(int index) {
            return ((ApplicationInfoProto) this.instance).getResourceDirs(index);
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getResourceDirsBytes(int index) {
            return ((ApplicationInfoProto) this.instance).getResourceDirsBytes(index);
        }

        public Builder setResourceDirs(int index, String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setResourceDirs(index, value);
            return this;
        }

        public Builder addResourceDirs(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addResourceDirs(value);
            return this;
        }

        public Builder addAllResourceDirs(Iterable<String> values) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addAllResourceDirs(values);
            return this;
        }

        public Builder clearResourceDirs() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearResourceDirs();
            return this;
        }

        public Builder addResourceDirsBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addResourceDirsBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasDataDir() {
            return ((ApplicationInfoProto) this.instance).hasDataDir();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getDataDir() {
            return ((ApplicationInfoProto) this.instance).getDataDir();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getDataDirBytes() {
            return ((ApplicationInfoProto) this.instance).getDataDirBytes();
        }

        public Builder setDataDir(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setDataDir(value);
            return this;
        }

        public Builder clearDataDir() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearDataDir();
            return this;
        }

        public Builder setDataDirBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setDataDirBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasClassLoaderName() {
            return ((ApplicationInfoProto) this.instance).hasClassLoaderName();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getClassLoaderName() {
            return ((ApplicationInfoProto) this.instance).getClassLoaderName();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getClassLoaderNameBytes() {
            return ((ApplicationInfoProto) this.instance).getClassLoaderNameBytes();
        }

        public Builder setClassLoaderName(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setClassLoaderName(value);
            return this;
        }

        public Builder clearClassLoaderName() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearClassLoaderName();
            return this;
        }

        public Builder setClassLoaderNameBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setClassLoaderNameBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public List<String> getSplitClassLoaderNamesList() {
            return Collections.unmodifiableList(((ApplicationInfoProto) this.instance).getSplitClassLoaderNamesList());
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public int getSplitClassLoaderNamesCount() {
            return ((ApplicationInfoProto) this.instance).getSplitClassLoaderNamesCount();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public String getSplitClassLoaderNames(int index) {
            return ((ApplicationInfoProto) this.instance).getSplitClassLoaderNames(index);
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public ByteString getSplitClassLoaderNamesBytes(int index) {
            return ((ApplicationInfoProto) this.instance).getSplitClassLoaderNamesBytes(index);
        }

        public Builder setSplitClassLoaderNames(int index, String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setSplitClassLoaderNames(index, value);
            return this;
        }

        public Builder addSplitClassLoaderNames(String value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addSplitClassLoaderNames(value);
            return this;
        }

        public Builder addAllSplitClassLoaderNames(Iterable<String> values) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addAllSplitClassLoaderNames(values);
            return this;
        }

        public Builder clearSplitClassLoaderNames() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearSplitClassLoaderNames();
            return this;
        }

        public Builder addSplitClassLoaderNamesBytes(ByteString value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).addSplitClassLoaderNamesBytes(value);
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasVersion() {
            return ((ApplicationInfoProto) this.instance).hasVersion();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public Version getVersion() {
            return ((ApplicationInfoProto) this.instance).getVersion();
        }

        public Builder setVersion(Version value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setVersion((ApplicationInfoProto) value);
            return this;
        }

        public Builder setVersion(Version.Builder builderForValue) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setVersion((ApplicationInfoProto) builderForValue);
            return this;
        }

        public Builder mergeVersion(Version value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).mergeVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearVersion();
            return this;
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public boolean hasDetail() {
            return ((ApplicationInfoProto) this.instance).hasDetail();
        }

        @Override // android.content.pm.ApplicationInfoProtoOrBuilder
        public Detail getDetail() {
            return ((ApplicationInfoProto) this.instance).getDetail();
        }

        public Builder setDetail(Detail value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setDetail((ApplicationInfoProto) value);
            return this;
        }

        public Builder setDetail(Detail.Builder builderForValue) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).setDetail((ApplicationInfoProto) builderForValue);
            return this;
        }

        public Builder mergeDetail(Detail value) {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).mergeDetail(value);
            return this;
        }

        public Builder clearDetail() {
            copyOnWrite();
            ((ApplicationInfoProto) this.instance).clearDetail();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ApplicationInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.splitSourceDirs_.makeImmutable();
                this.splitPublicSourceDirs_.makeImmutable();
                this.resourceDirs_.makeImmutable();
                this.splitClassLoaderNames_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ApplicationInfoProto other = (ApplicationInfoProto) arg1;
                this.package_ = (PackageItemInfoProto) visitor.visitMessage(this.package_, other.package_);
                this.permission_ = visitor.visitString(hasPermission(), this.permission_, other.hasPermission(), other.permission_);
                this.processName_ = visitor.visitString(hasProcessName(), this.processName_, other.hasProcessName(), other.processName_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.privateFlags_ = visitor.visitInt(hasPrivateFlags(), this.privateFlags_, other.hasPrivateFlags(), other.privateFlags_);
                this.theme_ = visitor.visitInt(hasTheme(), this.theme_, other.hasTheme(), other.theme_);
                this.sourceDir_ = visitor.visitString(hasSourceDir(), this.sourceDir_, other.hasSourceDir(), other.sourceDir_);
                this.publicSourceDir_ = visitor.visitString(hasPublicSourceDir(), this.publicSourceDir_, other.hasPublicSourceDir(), other.publicSourceDir_);
                this.splitSourceDirs_ = visitor.visitList(this.splitSourceDirs_, other.splitSourceDirs_);
                this.splitPublicSourceDirs_ = visitor.visitList(this.splitPublicSourceDirs_, other.splitPublicSourceDirs_);
                this.resourceDirs_ = visitor.visitList(this.resourceDirs_, other.resourceDirs_);
                this.dataDir_ = visitor.visitString(hasDataDir(), this.dataDir_, other.hasDataDir(), other.dataDir_);
                this.classLoaderName_ = visitor.visitString(hasClassLoaderName(), this.classLoaderName_, other.hasClassLoaderName(), other.classLoaderName_);
                this.splitClassLoaderNames_ = visitor.visitList(this.splitClassLoaderNames_, other.splitClassLoaderNames_);
                this.version_ = (Version) visitor.visitMessage(this.version_, other.version_);
                this.detail_ = (Detail) visitor.visitMessage(this.detail_, other.detail_);
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
                                PackageItemInfoProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (PackageItemInfoProto.Builder) this.package_.toBuilder();
                                }
                                this.package_ = (PackageItemInfoProto) input.readMessage(PackageItemInfoProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.package_);
                                    this.package_ = (PackageItemInfoProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 18:
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.permission_ = s;
                                break;
                            case 26:
                                String s2 = input.readString();
                                this.bitField0_ |= 4;
                                this.processName_ = s2;
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.uid_ = input.readInt32();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.flags_ = input.readInt32();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.privateFlags_ = input.readInt32();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.theme_ = input.readInt32();
                                break;
                            case 66:
                                String s3 = input.readString();
                                this.bitField0_ |= 128;
                                this.sourceDir_ = s3;
                                break;
                            case 74:
                                String s4 = input.readString();
                                this.bitField0_ |= 256;
                                this.publicSourceDir_ = s4;
                                break;
                            case 82:
                                String s5 = input.readString();
                                if (!this.splitSourceDirs_.isModifiable()) {
                                    this.splitSourceDirs_ = GeneratedMessageLite.mutableCopy(this.splitSourceDirs_);
                                }
                                this.splitSourceDirs_.add(s5);
                                break;
                            case 90:
                                String s6 = input.readString();
                                if (!this.splitPublicSourceDirs_.isModifiable()) {
                                    this.splitPublicSourceDirs_ = GeneratedMessageLite.mutableCopy(this.splitPublicSourceDirs_);
                                }
                                this.splitPublicSourceDirs_.add(s6);
                                break;
                            case 98:
                                String s7 = input.readString();
                                if (!this.resourceDirs_.isModifiable()) {
                                    this.resourceDirs_ = GeneratedMessageLite.mutableCopy(this.resourceDirs_);
                                }
                                this.resourceDirs_.add(s7);
                                break;
                            case 106:
                                String s8 = input.readString();
                                this.bitField0_ |= 512;
                                this.dataDir_ = s8;
                                break;
                            case 114:
                                String s9 = input.readString();
                                this.bitField0_ |= 1024;
                                this.classLoaderName_ = s9;
                                break;
                            case 122:
                                String s10 = input.readString();
                                if (!this.splitClassLoaderNames_.isModifiable()) {
                                    this.splitClassLoaderNames_ = GeneratedMessageLite.mutableCopy(this.splitClassLoaderNames_);
                                }
                                this.splitClassLoaderNames_.add(s10);
                                break;
                            case 130:
                                Version.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder2 = (Version.Builder) this.version_.toBuilder();
                                }
                                this.version_ = (Version) input.readMessage(Version.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.version_);
                                    this.version_ = (Version) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 138:
                                Detail.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder3 = (Detail.Builder) this.detail_.toBuilder();
                                }
                                this.detail_ = (Detail) input.readMessage(Detail.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.detail_);
                                    this.detail_ = (Detail) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4096;
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
                    synchronized (ApplicationInfoProto.class) {
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

    public static ApplicationInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ApplicationInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
