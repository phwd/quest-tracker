package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$HmdCapabilitiesResponse extends GeneratedMessageLite<Protocol$HmdCapabilitiesResponse, Builder> implements Protocol$HmdCapabilitiesResponseOrBuilder {
    private static final Protocol$HmdCapabilitiesResponse DEFAULT_INSTANCE = new Protocol$HmdCapabilitiesResponse();
    private static volatile Parser<Protocol$HmdCapabilitiesResponse> PARSER;
    private int bitField0_;
    private boolean chromecastAvailable_ = false;
    private boolean chromecastNativeReceiverAvailable_ = false;
    private int manualOtaVersion_ = 0;
    private boolean multiUserAvailable_ = false;
    private boolean tourGuideV2Available_ = false;

    private Protocol$HmdCapabilitiesResponse() {
    }

    public boolean hasChromecastAvailable() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getChromecastAvailable() {
        return this.chromecastAvailable_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChromecastAvailable(boolean z) {
        this.bitField0_ |= 1;
        this.chromecastAvailable_ = z;
    }

    public boolean hasMultiUserAvailable() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getMultiUserAvailable() {
        return this.multiUserAvailable_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMultiUserAvailable(boolean z) {
        this.bitField0_ |= 2;
        this.multiUserAvailable_ = z;
    }

    public boolean hasManualOtaVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    public int getManualOtaVersion() {
        return this.manualOtaVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManualOtaVersion(int i) {
        this.bitField0_ |= 4;
        this.manualOtaVersion_ = i;
    }

    public boolean hasChromecastNativeReceiverAvailable() {
        return (this.bitField0_ & 8) == 8;
    }

    public boolean getChromecastNativeReceiverAvailable() {
        return this.chromecastNativeReceiverAvailable_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChromecastNativeReceiverAvailable(boolean z) {
        this.bitField0_ |= 8;
        this.chromecastNativeReceiverAvailable_ = z;
    }

    public boolean hasTourGuideV2Available() {
        return (this.bitField0_ & 16) == 16;
    }

    public boolean getTourGuideV2Available() {
        return this.tourGuideV2Available_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTourGuideV2Available(boolean z) {
        this.bitField0_ |= 16;
        this.tourGuideV2Available_ = z;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.chromecastAvailable_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(11, this.multiUserAvailable_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(12, this.manualOtaVersion_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBool(13, this.chromecastNativeReceiverAvailable_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeBool(14, this.tourGuideV2Available_);
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
            i2 = 0 + CodedOutputStream.computeBoolSize(1, this.chromecastAvailable_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBoolSize(11, this.multiUserAvailable_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeInt32Size(12, this.manualOtaVersion_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBoolSize(13, this.chromecastNativeReceiverAvailable_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeBoolSize(14, this.tourGuideV2Available_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$HmdCapabilitiesResponse, Builder> implements Protocol$HmdCapabilitiesResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$HmdCapabilitiesResponse.DEFAULT_INSTANCE);
        }

        public Builder setChromecastAvailable(boolean z) {
            copyOnWrite();
            ((Protocol$HmdCapabilitiesResponse) this.instance).setChromecastAvailable(z);
            return this;
        }

        public Builder setMultiUserAvailable(boolean z) {
            copyOnWrite();
            ((Protocol$HmdCapabilitiesResponse) this.instance).setMultiUserAvailable(z);
            return this;
        }

        public Builder setManualOtaVersion(int i) {
            copyOnWrite();
            ((Protocol$HmdCapabilitiesResponse) this.instance).setManualOtaVersion(i);
            return this;
        }

        public Builder setChromecastNativeReceiverAvailable(boolean z) {
            copyOnWrite();
            ((Protocol$HmdCapabilitiesResponse) this.instance).setChromecastNativeReceiverAvailable(z);
            return this;
        }

        public Builder setTourGuideV2Available(boolean z) {
            copyOnWrite();
            ((Protocol$HmdCapabilitiesResponse) this.instance).setTourGuideV2Available(z);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$HmdCapabilitiesResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$HmdCapabilitiesResponse protocol$HmdCapabilitiesResponse = (Protocol$HmdCapabilitiesResponse) obj2;
                this.chromecastAvailable_ = visitor.visitBoolean(hasChromecastAvailable(), this.chromecastAvailable_, protocol$HmdCapabilitiesResponse.hasChromecastAvailable(), protocol$HmdCapabilitiesResponse.chromecastAvailable_);
                this.multiUserAvailable_ = visitor.visitBoolean(hasMultiUserAvailable(), this.multiUserAvailable_, protocol$HmdCapabilitiesResponse.hasMultiUserAvailable(), protocol$HmdCapabilitiesResponse.multiUserAvailable_);
                this.manualOtaVersion_ = visitor.visitInt(hasManualOtaVersion(), this.manualOtaVersion_, protocol$HmdCapabilitiesResponse.hasManualOtaVersion(), protocol$HmdCapabilitiesResponse.manualOtaVersion_);
                this.chromecastNativeReceiverAvailable_ = visitor.visitBoolean(hasChromecastNativeReceiverAvailable(), this.chromecastNativeReceiverAvailable_, protocol$HmdCapabilitiesResponse.hasChromecastNativeReceiverAvailable(), protocol$HmdCapabilitiesResponse.chromecastNativeReceiverAvailable_);
                this.tourGuideV2Available_ = visitor.visitBoolean(hasTourGuideV2Available(), this.tourGuideV2Available_, protocol$HmdCapabilitiesResponse.hasTourGuideV2Available(), protocol$HmdCapabilitiesResponse.tourGuideV2Available_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$HmdCapabilitiesResponse.bitField0_;
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
                                this.chromecastAvailable_ = codedInputStream.readBool();
                            } else if (readTag == 88) {
                                this.bitField0_ |= 2;
                                this.multiUserAvailable_ = codedInputStream.readBool();
                            } else if (readTag == 96) {
                                this.bitField0_ |= 4;
                                this.manualOtaVersion_ = codedInputStream.readInt32();
                            } else if (readTag == 104) {
                                this.bitField0_ |= 8;
                                this.chromecastNativeReceiverAvailable_ = codedInputStream.readBool();
                            } else if (readTag == 112) {
                                this.bitField0_ |= 16;
                                this.tourGuideV2Available_ = codedInputStream.readBool();
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
                    synchronized (Protocol$HmdCapabilitiesResponse.class) {
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
