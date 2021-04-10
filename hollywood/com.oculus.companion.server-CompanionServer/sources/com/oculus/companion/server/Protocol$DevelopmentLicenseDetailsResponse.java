package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$DevelopmentLicenseDetailsResponse extends GeneratedMessageLite<Protocol$DevelopmentLicenseDetailsResponse, Builder> implements Protocol$DevelopmentLicenseDetailsResponseOrBuilder {
    private static final Protocol$DevelopmentLicenseDetailsResponse DEFAULT_INSTANCE = new Protocol$DevelopmentLicenseDetailsResponse();
    private static volatile Parser<Protocol$DevelopmentLicenseDetailsResponse> PARSER;
    private int bitField0_;
    private long expiryTimeS_ = 0;
    private long issuedTimeS_ = 0;
    private boolean revoked_ = false;

    private Protocol$DevelopmentLicenseDetailsResponse() {
    }

    public boolean hasExpiryTimeS() {
        return (this.bitField0_ & 1) == 1;
    }

    public long getExpiryTimeS() {
        return this.expiryTimeS_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setExpiryTimeS(long j) {
        this.bitField0_ |= 1;
        this.expiryTimeS_ = j;
    }

    public boolean hasIssuedTimeS() {
        return (this.bitField0_ & 2) == 2;
    }

    public long getIssuedTimeS() {
        return this.issuedTimeS_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIssuedTimeS(long j) {
        this.bitField0_ |= 2;
        this.issuedTimeS_ = j;
    }

    public boolean hasRevoked() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean getRevoked() {
        return this.revoked_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRevoked(boolean z) {
        this.bitField0_ |= 4;
        this.revoked_ = z;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt64(1, this.expiryTimeS_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt64(2, this.issuedTimeS_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBool(3, this.revoked_);
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
            i2 = 0 + CodedOutputStream.computeInt64Size(1, this.expiryTimeS_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt64Size(2, this.issuedTimeS_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBoolSize(3, this.revoked_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$DevelopmentLicenseDetailsResponse, Builder> implements Protocol$DevelopmentLicenseDetailsResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$DevelopmentLicenseDetailsResponse.DEFAULT_INSTANCE);
        }

        public Builder setExpiryTimeS(long j) {
            copyOnWrite();
            ((Protocol$DevelopmentLicenseDetailsResponse) this.instance).setExpiryTimeS(j);
            return this;
        }

        public Builder setIssuedTimeS(long j) {
            copyOnWrite();
            ((Protocol$DevelopmentLicenseDetailsResponse) this.instance).setIssuedTimeS(j);
            return this;
        }

        public Builder setRevoked(boolean z) {
            copyOnWrite();
            ((Protocol$DevelopmentLicenseDetailsResponse) this.instance).setRevoked(z);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$DevelopmentLicenseDetailsResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$DevelopmentLicenseDetailsResponse protocol$DevelopmentLicenseDetailsResponse = (Protocol$DevelopmentLicenseDetailsResponse) obj2;
                this.expiryTimeS_ = visitor.visitLong(hasExpiryTimeS(), this.expiryTimeS_, protocol$DevelopmentLicenseDetailsResponse.hasExpiryTimeS(), protocol$DevelopmentLicenseDetailsResponse.expiryTimeS_);
                this.issuedTimeS_ = visitor.visitLong(hasIssuedTimeS(), this.issuedTimeS_, protocol$DevelopmentLicenseDetailsResponse.hasIssuedTimeS(), protocol$DevelopmentLicenseDetailsResponse.issuedTimeS_);
                this.revoked_ = visitor.visitBoolean(hasRevoked(), this.revoked_, protocol$DevelopmentLicenseDetailsResponse.hasRevoked(), protocol$DevelopmentLicenseDetailsResponse.revoked_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$DevelopmentLicenseDetailsResponse.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.expiryTimeS_ = codedInputStream.readInt64();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.issuedTimeS_ = codedInputStream.readInt64();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.revoked_ = codedInputStream.readBool();
                            } else if (!parseUnknownField(readTag, codedInputStream)) {
                            }
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
                    synchronized (Protocol$DevelopmentLicenseDetailsResponse.class) {
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
