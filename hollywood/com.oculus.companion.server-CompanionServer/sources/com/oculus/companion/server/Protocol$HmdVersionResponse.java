package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$HmdVersionResponse extends GeneratedMessageLite<Protocol$HmdVersionResponse, Builder> implements Protocol$HmdVersionResponseOrBuilder {
    private static final Protocol$HmdVersionResponse DEFAULT_INSTANCE = new Protocol$HmdVersionResponse();
    private static volatile Parser<Protocol$HmdVersionResponse> PARSER;
    private int bitField0_;
    private String board_ = "";
    private String bootloader_ = "";
    private String brand_ = "";
    private String device_ = "";
    private String display_ = "";
    private String fingerprint_ = "";
    private String hardware_ = "";
    private String host_ = "";
    private String id_ = "";
    private String manufacturer_ = "";
    private byte memoizedIsInitialized = -1;
    private String model_ = "";
    private String product_ = "";
    private String serial_ = "";
    private String tags_ = "";
    private long time_ = 0;
    private String type_ = "";
    private String user_ = "";
    private String versionBaseOs_ = "";
    private String versionCodename_ = "";
    private String versionIncremental_ = "";
    private String versionRelease_ = "";
    private String versionSecurityPatch_ = "";

    private Protocol$HmdVersionResponse() {
    }

    public boolean hasBoard() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getBoard() {
        return this.board_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBoard(String str) {
        if (str != null) {
            this.bitField0_ |= 1;
            this.board_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasBootloader() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getBootloader() {
        return this.bootloader_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBootloader(String str) {
        if (str != null) {
            this.bitField0_ |= 2;
            this.bootloader_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasBrand() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getBrand() {
        return this.brand_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBrand(String str) {
        if (str != null) {
            this.bitField0_ |= 4;
            this.brand_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasDevice() {
        return (this.bitField0_ & 8) == 8;
    }

    public String getDevice() {
        return this.device_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDevice(String str) {
        if (str != null) {
            this.bitField0_ |= 8;
            this.device_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasDisplay() {
        return (this.bitField0_ & 16) == 16;
    }

    public String getDisplay() {
        return this.display_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplay(String str) {
        if (str != null) {
            this.bitField0_ |= 16;
            this.display_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasFingerprint() {
        return (this.bitField0_ & 32) == 32;
    }

    public String getFingerprint() {
        return this.fingerprint_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFingerprint(String str) {
        if (str != null) {
            this.bitField0_ |= 32;
            this.fingerprint_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasHardware() {
        return (this.bitField0_ & 64) == 64;
    }

    public String getHardware() {
        return this.hardware_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHardware(String str) {
        if (str != null) {
            this.bitField0_ |= 64;
            this.hardware_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasHost() {
        return (this.bitField0_ & 128) == 128;
    }

    public String getHost() {
        return this.host_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHost(String str) {
        if (str != null) {
            this.bitField0_ |= 128;
            this.host_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasId() {
        return (this.bitField0_ & 256) == 256;
    }

    public String getId() {
        return this.id_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(String str) {
        if (str != null) {
            this.bitField0_ |= 256;
            this.id_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasManufacturer() {
        return (this.bitField0_ & 512) == 512;
    }

    public String getManufacturer() {
        return this.manufacturer_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturer(String str) {
        if (str != null) {
            this.bitField0_ |= 512;
            this.manufacturer_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasModel() {
        return (this.bitField0_ & 1024) == 1024;
    }

    public String getModel() {
        return this.model_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModel(String str) {
        if (str != null) {
            this.bitField0_ |= 1024;
            this.model_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasProduct() {
        return (this.bitField0_ & 2048) == 2048;
    }

    public String getProduct() {
        return this.product_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProduct(String str) {
        if (str != null) {
            this.bitField0_ |= 2048;
            this.product_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasSerial() {
        return (this.bitField0_ & 4096) == 4096;
    }

    public String getSerial() {
        return this.serial_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSerial(String str) {
        if (str != null) {
            this.bitField0_ |= 4096;
            this.serial_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasTags() {
        return (this.bitField0_ & 8192) == 8192;
    }

    public String getTags() {
        return this.tags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTags(String str) {
        if (str != null) {
            this.bitField0_ |= 8192;
            this.tags_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasTime() {
        return (this.bitField0_ & 16384) == 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTime(long j) {
        this.bitField0_ |= 16384;
        this.time_ = j;
    }

    public boolean hasType() {
        return (this.bitField0_ & 32768) == 32768;
    }

    public String getType() {
        return this.type_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(String str) {
        if (str != null) {
            this.bitField0_ |= 32768;
            this.type_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasUser() {
        return (this.bitField0_ & 65536) == 65536;
    }

    public String getUser() {
        return this.user_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUser(String str) {
        if (str != null) {
            this.bitField0_ |= 65536;
            this.user_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasVersionBaseOs() {
        return (this.bitField0_ & 131072) == 131072;
    }

    public String getVersionBaseOs() {
        return this.versionBaseOs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionBaseOs(String str) {
        if (str != null) {
            this.bitField0_ |= 131072;
            this.versionBaseOs_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasVersionCodename() {
        return (this.bitField0_ & 262144) == 262144;
    }

    public String getVersionCodename() {
        return this.versionCodename_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionCodename(String str) {
        if (str != null) {
            this.bitField0_ |= 262144;
            this.versionCodename_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasVersionIncremental() {
        return (this.bitField0_ & 524288) == 524288;
    }

    public String getVersionIncremental() {
        return this.versionIncremental_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionIncremental(String str) {
        if (str != null) {
            this.bitField0_ |= 524288;
            this.versionIncremental_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasVersionRelease() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    public String getVersionRelease() {
        return this.versionRelease_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionRelease(String str) {
        if (str != null) {
            this.bitField0_ |= 1048576;
            this.versionRelease_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasVersionSecurityPatch() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    public String getVersionSecurityPatch() {
        return this.versionSecurityPatch_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionSecurityPatch(String str) {
        if (str != null) {
            this.bitField0_ |= 2097152;
            this.versionSecurityPatch_ = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getBoard());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getBootloader());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, getBrand());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, getDevice());
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeString(5, getDisplay());
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeString(6, getFingerprint());
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeString(7, getHardware());
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeString(8, getHost());
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeString(9, getId());
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeString(10, getManufacturer());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeString(11, getModel());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeString(12, getProduct());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            codedOutputStream.writeString(13, getSerial());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            codedOutputStream.writeString(14, getTags());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            codedOutputStream.writeUInt64(15, this.time_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            codedOutputStream.writeString(16, getType());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            codedOutputStream.writeString(17, getUser());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            codedOutputStream.writeString(31, getVersionBaseOs());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            codedOutputStream.writeString(32, getVersionCodename());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            codedOutputStream.writeString(33, getVersionIncremental());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            codedOutputStream.writeString(34, getVersionRelease());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            codedOutputStream.writeString(35, getVersionSecurityPatch());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getBoard());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getBootloader());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, getBrand());
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, getDevice());
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeStringSize(5, getDisplay());
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeStringSize(6, getFingerprint());
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeStringSize(7, getHardware());
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeStringSize(8, getHost());
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeStringSize(9, getId());
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeStringSize(10, getManufacturer());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeStringSize(11, getModel());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeStringSize(12, getProduct());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            i2 += CodedOutputStream.computeStringSize(13, getSerial());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            i2 += CodedOutputStream.computeStringSize(14, getTags());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            i2 += CodedOutputStream.computeUInt64Size(15, this.time_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            i2 += CodedOutputStream.computeStringSize(16, getType());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            i2 += CodedOutputStream.computeStringSize(17, getUser());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            i2 += CodedOutputStream.computeStringSize(31, getVersionBaseOs());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            i2 += CodedOutputStream.computeStringSize(32, getVersionCodename());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            i2 += CodedOutputStream.computeStringSize(33, getVersionIncremental());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            i2 += CodedOutputStream.computeStringSize(34, getVersionRelease());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            i2 += CodedOutputStream.computeStringSize(35, getVersionSecurityPatch());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$HmdVersionResponse, Builder> implements Protocol$HmdVersionResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$HmdVersionResponse.DEFAULT_INSTANCE);
        }

        public Builder setBoard(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setBoard(str);
            return this;
        }

        public Builder setBootloader(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setBootloader(str);
            return this;
        }

        public Builder setBrand(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setBrand(str);
            return this;
        }

        public Builder setDevice(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setDevice(str);
            return this;
        }

        public Builder setDisplay(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setDisplay(str);
            return this;
        }

        public Builder setFingerprint(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setFingerprint(str);
            return this;
        }

        public Builder setHardware(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setHardware(str);
            return this;
        }

        public Builder setHost(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setHost(str);
            return this;
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setId(str);
            return this;
        }

        public Builder setManufacturer(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setManufacturer(str);
            return this;
        }

        public Builder setModel(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setModel(str);
            return this;
        }

        public Builder setProduct(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setProduct(str);
            return this;
        }

        public Builder setSerial(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setSerial(str);
            return this;
        }

        public Builder setTags(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setTags(str);
            return this;
        }

        public Builder setTime(long j) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setTime(j);
            return this;
        }

        public Builder setType(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setType(str);
            return this;
        }

        public Builder setUser(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setUser(str);
            return this;
        }

        public Builder setVersionBaseOs(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setVersionBaseOs(str);
            return this;
        }

        public Builder setVersionCodename(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setVersionCodename(str);
            return this;
        }

        public Builder setVersionIncremental(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setVersionIncremental(str);
            return this;
        }

        public Builder setVersionRelease(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setVersionRelease(str);
            return this;
        }

        public Builder setVersionSecurityPatch(String str) {
            copyOnWrite();
            ((Protocol$HmdVersionResponse) this.instance).setVersionSecurityPatch(str);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$HmdVersionResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasDisplay()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                }
                if (booleanValue) {
                    this.memoizedIsInitialized = 1;
                }
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$HmdVersionResponse protocol$HmdVersionResponse = (Protocol$HmdVersionResponse) obj2;
                this.board_ = visitor.visitString(hasBoard(), this.board_, protocol$HmdVersionResponse.hasBoard(), protocol$HmdVersionResponse.board_);
                this.bootloader_ = visitor.visitString(hasBootloader(), this.bootloader_, protocol$HmdVersionResponse.hasBootloader(), protocol$HmdVersionResponse.bootloader_);
                this.brand_ = visitor.visitString(hasBrand(), this.brand_, protocol$HmdVersionResponse.hasBrand(), protocol$HmdVersionResponse.brand_);
                this.device_ = visitor.visitString(hasDevice(), this.device_, protocol$HmdVersionResponse.hasDevice(), protocol$HmdVersionResponse.device_);
                this.display_ = visitor.visitString(hasDisplay(), this.display_, protocol$HmdVersionResponse.hasDisplay(), protocol$HmdVersionResponse.display_);
                this.fingerprint_ = visitor.visitString(hasFingerprint(), this.fingerprint_, protocol$HmdVersionResponse.hasFingerprint(), protocol$HmdVersionResponse.fingerprint_);
                this.hardware_ = visitor.visitString(hasHardware(), this.hardware_, protocol$HmdVersionResponse.hasHardware(), protocol$HmdVersionResponse.hardware_);
                this.host_ = visitor.visitString(hasHost(), this.host_, protocol$HmdVersionResponse.hasHost(), protocol$HmdVersionResponse.host_);
                this.id_ = visitor.visitString(hasId(), this.id_, protocol$HmdVersionResponse.hasId(), protocol$HmdVersionResponse.id_);
                this.manufacturer_ = visitor.visitString(hasManufacturer(), this.manufacturer_, protocol$HmdVersionResponse.hasManufacturer(), protocol$HmdVersionResponse.manufacturer_);
                this.model_ = visitor.visitString(hasModel(), this.model_, protocol$HmdVersionResponse.hasModel(), protocol$HmdVersionResponse.model_);
                this.product_ = visitor.visitString(hasProduct(), this.product_, protocol$HmdVersionResponse.hasProduct(), protocol$HmdVersionResponse.product_);
                this.serial_ = visitor.visitString(hasSerial(), this.serial_, protocol$HmdVersionResponse.hasSerial(), protocol$HmdVersionResponse.serial_);
                this.tags_ = visitor.visitString(hasTags(), this.tags_, protocol$HmdVersionResponse.hasTags(), protocol$HmdVersionResponse.tags_);
                this.time_ = visitor.visitLong(hasTime(), this.time_, protocol$HmdVersionResponse.hasTime(), protocol$HmdVersionResponse.time_);
                this.type_ = visitor.visitString(hasType(), this.type_, protocol$HmdVersionResponse.hasType(), protocol$HmdVersionResponse.type_);
                this.user_ = visitor.visitString(hasUser(), this.user_, protocol$HmdVersionResponse.hasUser(), protocol$HmdVersionResponse.user_);
                this.versionBaseOs_ = visitor.visitString(hasVersionBaseOs(), this.versionBaseOs_, protocol$HmdVersionResponse.hasVersionBaseOs(), protocol$HmdVersionResponse.versionBaseOs_);
                this.versionCodename_ = visitor.visitString(hasVersionCodename(), this.versionCodename_, protocol$HmdVersionResponse.hasVersionCodename(), protocol$HmdVersionResponse.versionCodename_);
                this.versionIncremental_ = visitor.visitString(hasVersionIncremental(), this.versionIncremental_, protocol$HmdVersionResponse.hasVersionIncremental(), protocol$HmdVersionResponse.versionIncremental_);
                this.versionRelease_ = visitor.visitString(hasVersionRelease(), this.versionRelease_, protocol$HmdVersionResponse.hasVersionRelease(), protocol$HmdVersionResponse.versionRelease_);
                this.versionSecurityPatch_ = visitor.visitString(hasVersionSecurityPatch(), this.versionSecurityPatch_, protocol$HmdVersionResponse.hasVersionSecurityPatch(), protocol$HmdVersionResponse.versionSecurityPatch_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$HmdVersionResponse.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                break;
                            case 10:
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 1;
                                this.board_ = readString;
                                continue;
                            case 18:
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.bootloader_ = readString2;
                                continue;
                            case 26:
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.brand_ = readString3;
                                continue;
                            case 34:
                                String readString4 = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.device_ = readString4;
                                continue;
                            case 42:
                                String readString5 = codedInputStream.readString();
                                this.bitField0_ |= 16;
                                this.display_ = readString5;
                                continue;
                            case 50:
                                String readString6 = codedInputStream.readString();
                                this.bitField0_ |= 32;
                                this.fingerprint_ = readString6;
                                continue;
                            case 58:
                                String readString7 = codedInputStream.readString();
                                this.bitField0_ |= 64;
                                this.hardware_ = readString7;
                                continue;
                            case 66:
                                String readString8 = codedInputStream.readString();
                                this.bitField0_ |= 128;
                                this.host_ = readString8;
                                continue;
                            case 74:
                                String readString9 = codedInputStream.readString();
                                this.bitField0_ |= 256;
                                this.id_ = readString9;
                                continue;
                            case 82:
                                String readString10 = codedInputStream.readString();
                                this.bitField0_ |= 512;
                                this.manufacturer_ = readString10;
                                continue;
                            case 90:
                                String readString11 = codedInputStream.readString();
                                this.bitField0_ |= 1024;
                                this.model_ = readString11;
                                continue;
                            case 98:
                                String readString12 = codedInputStream.readString();
                                this.bitField0_ |= 2048;
                                this.product_ = readString12;
                                continue;
                            case 106:
                                String readString13 = codedInputStream.readString();
                                this.bitField0_ |= 4096;
                                this.serial_ = readString13;
                                continue;
                            case 114:
                                String readString14 = codedInputStream.readString();
                                this.bitField0_ |= 8192;
                                this.tags_ = readString14;
                                continue;
                            case 120:
                                this.bitField0_ |= 16384;
                                this.time_ = codedInputStream.readUInt64();
                                continue;
                            case 130:
                                String readString15 = codedInputStream.readString();
                                this.bitField0_ |= 32768;
                                this.type_ = readString15;
                                continue;
                            case 138:
                                String readString16 = codedInputStream.readString();
                                this.bitField0_ |= 65536;
                                this.user_ = readString16;
                                continue;
                            case 250:
                                String readString17 = codedInputStream.readString();
                                this.bitField0_ |= 131072;
                                this.versionBaseOs_ = readString17;
                                continue;
                            case 258:
                                String readString18 = codedInputStream.readString();
                                this.bitField0_ |= 262144;
                                this.versionCodename_ = readString18;
                                continue;
                            case 266:
                                String readString19 = codedInputStream.readString();
                                this.bitField0_ |= 524288;
                                this.versionIncremental_ = readString19;
                                continue;
                            case 274:
                                String readString20 = codedInputStream.readString();
                                this.bitField0_ |= 1048576;
                                this.versionRelease_ = readString20;
                                continue;
                            case 282:
                                String readString21 = codedInputStream.readString();
                                this.bitField0_ |= 2097152;
                                this.versionSecurityPatch_ = readString21;
                                continue;
                            default:
                                if (parseUnknownField(readTag, codedInputStream)) {
                                    continue;
                                }
                                break;
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (PARSER == null) {
                    synchronized (Protocol$HmdVersionResponse.class) {
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
}
