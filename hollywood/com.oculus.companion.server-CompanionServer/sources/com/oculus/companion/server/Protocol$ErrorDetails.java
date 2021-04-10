package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.oculus.companion.server.Protocol$BlePairingKey;
import java.io.IOException;

public final class Protocol$ErrorDetails extends GeneratedMessageLite<Protocol$ErrorDetails, Builder> implements Protocol$ErrorDetailsOrBuilder {
    private static final Protocol$ErrorDetails DEFAULT_INSTANCE = new Protocol$ErrorDetails();
    private static volatile Parser<Protocol$ErrorDetails> PARSER;
    private int bitField0_;
    private Protocol$BlePairingKey blePairingKey_;
    private int code_ = 0;
    private String debugDetails_ = "";
    private String localizedUserFacingDescription_ = "";
    private byte memoizedIsInitialized = -1;

    private Protocol$ErrorDetails() {
    }

    public boolean hasCode() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCode(Protocol$ErrorCode protocol$ErrorCode) {
        if (protocol$ErrorCode != null) {
            this.bitField0_ |= 1;
            this.code_ = protocol$ErrorCode.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasDebugDetails() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getDebugDetails() {
        return this.debugDetails_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebugDetails(String str) {
        if (str != null) {
            this.bitField0_ |= 2;
            this.debugDetails_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasLocalizedUserFacingDescription() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getLocalizedUserFacingDescription() {
        return this.localizedUserFacingDescription_;
    }

    public boolean hasBlePairingKey() {
        return (this.bitField0_ & 8) == 8;
    }

    public Protocol$BlePairingKey getBlePairingKey() {
        Protocol$BlePairingKey protocol$BlePairingKey = this.blePairingKey_;
        return protocol$BlePairingKey == null ? Protocol$BlePairingKey.getDefaultInstance() : protocol$BlePairingKey;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBlePairingKey(Protocol$BlePairingKey protocol$BlePairingKey) {
        if (protocol$BlePairingKey != null) {
            this.blePairingKey_ = protocol$BlePairingKey;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeEnum(1, this.code_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getDebugDetails());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, getLocalizedUserFacingDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeMessage(4, getBlePairingKey());
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
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.code_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getDebugDetails());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, getLocalizedUserFacingDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeMessageSize(4, getBlePairingKey());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$ErrorDetails, Builder> implements Protocol$ErrorDetailsOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$ErrorDetails.DEFAULT_INSTANCE);
        }

        public Builder setCode(Protocol$ErrorCode protocol$ErrorCode) {
            copyOnWrite();
            ((Protocol$ErrorDetails) this.instance).setCode(protocol$ErrorCode);
            return this;
        }

        public Builder setDebugDetails(String str) {
            copyOnWrite();
            ((Protocol$ErrorDetails) this.instance).setDebugDetails(str);
            return this;
        }

        public Builder setBlePairingKey(Protocol$BlePairingKey protocol$BlePairingKey) {
            copyOnWrite();
            ((Protocol$ErrorDetails) this.instance).setBlePairingKey(protocol$BlePairingKey);
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
                return new Protocol$ErrorDetails();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasCode()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasBlePairingKey() || getBlePairingKey().isInitialized()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 1;
                    }
                    return DEFAULT_INSTANCE;
                } else {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                }
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$ErrorDetails protocol$ErrorDetails = (Protocol$ErrorDetails) obj2;
                this.code_ = visitor.visitInt(hasCode(), this.code_, protocol$ErrorDetails.hasCode(), protocol$ErrorDetails.code_);
                this.debugDetails_ = visitor.visitString(hasDebugDetails(), this.debugDetails_, protocol$ErrorDetails.hasDebugDetails(), protocol$ErrorDetails.debugDetails_);
                this.localizedUserFacingDescription_ = visitor.visitString(hasLocalizedUserFacingDescription(), this.localizedUserFacingDescription_, protocol$ErrorDetails.hasLocalizedUserFacingDescription(), protocol$ErrorDetails.localizedUserFacingDescription_);
                this.blePairingKey_ = (Protocol$BlePairingKey) visitor.visitMessage(this.blePairingKey_, protocol$ErrorDetails.blePairingKey_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$ErrorDetails.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                int readEnum = codedInputStream.readEnum();
                                if (Protocol$ErrorCode.forNumber(readEnum) == null) {
                                    super.mergeVarintField(1, readEnum);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.code_ = readEnum;
                                }
                            } else if (readTag == 18) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.debugDetails_ = readString;
                            } else if (readTag == 26) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.localizedUserFacingDescription_ = readString2;
                            } else if (readTag == 34) {
                                Protocol$BlePairingKey.Builder builder = (this.bitField0_ & 8) == 8 ? (Protocol$BlePairingKey.Builder) this.blePairingKey_.toBuilder() : null;
                                this.blePairingKey_ = (Protocol$BlePairingKey) codedInputStream.readMessage(Protocol$BlePairingKey.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((GeneratedMessageLite) this.blePairingKey_);
                                    this.blePairingKey_ = (Protocol$BlePairingKey) builder.buildPartial();
                                }
                                this.bitField0_ |= 8;
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
                    synchronized (Protocol$ErrorDetails.class) {
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
