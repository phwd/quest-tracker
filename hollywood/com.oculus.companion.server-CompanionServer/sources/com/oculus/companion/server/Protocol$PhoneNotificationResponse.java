package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$PhoneNotificationResponse extends GeneratedMessageLite<Protocol$PhoneNotificationResponse, Builder> implements Protocol$PhoneNotificationResponseOrBuilder {
    private static final Protocol$PhoneNotificationResponse DEFAULT_INSTANCE = new Protocol$PhoneNotificationResponse();
    private static volatile Parser<Protocol$PhoneNotificationResponse> PARSER;
    private boolean allowAllApps_ = false;
    private String appFilters_ = "";
    private int bitField0_;
    private boolean enabled_ = false;
    private byte memoizedIsInitialized = -1;

    private Protocol$PhoneNotificationResponse() {
    }

    public boolean hasEnabled() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabled(boolean z) {
        this.bitField0_ |= 1;
        this.enabled_ = z;
    }

    public boolean hasAllowAllApps() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowAllApps(boolean z) {
        this.bitField0_ |= 2;
        this.allowAllApps_ = z;
    }

    public boolean hasAppFilters() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getAppFilters() {
        return this.appFilters_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppFilters(String str) {
        if (str != null) {
            this.bitField0_ |= 4;
            this.appFilters_ = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.enabled_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(2, this.allowAllApps_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, getAppFilters());
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
            i2 = 0 + CodedOutputStream.computeBoolSize(1, this.enabled_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeBoolSize(2, this.allowAllApps_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, getAppFilters());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$PhoneNotificationResponse, Builder> implements Protocol$PhoneNotificationResponseOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$PhoneNotificationResponse.DEFAULT_INSTANCE);
        }

        public Builder setEnabled(boolean z) {
            copyOnWrite();
            ((Protocol$PhoneNotificationResponse) this.instance).setEnabled(z);
            return this;
        }

        public Builder setAllowAllApps(boolean z) {
            copyOnWrite();
            ((Protocol$PhoneNotificationResponse) this.instance).setAllowAllApps(z);
            return this;
        }

        public Builder setAppFilters(String str) {
            copyOnWrite();
            ((Protocol$PhoneNotificationResponse) this.instance).setAppFilters(str);
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
                return new Protocol$PhoneNotificationResponse();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasEnabled()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasAllowAllApps()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasAppFilters()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 1;
                    }
                    return DEFAULT_INSTANCE;
                }
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$PhoneNotificationResponse protocol$PhoneNotificationResponse = (Protocol$PhoneNotificationResponse) obj2;
                this.enabled_ = visitor.visitBoolean(hasEnabled(), this.enabled_, protocol$PhoneNotificationResponse.hasEnabled(), protocol$PhoneNotificationResponse.enabled_);
                this.allowAllApps_ = visitor.visitBoolean(hasAllowAllApps(), this.allowAllApps_, protocol$PhoneNotificationResponse.hasAllowAllApps(), protocol$PhoneNotificationResponse.allowAllApps_);
                this.appFilters_ = visitor.visitString(hasAppFilters(), this.appFilters_, protocol$PhoneNotificationResponse.hasAppFilters(), protocol$PhoneNotificationResponse.appFilters_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$PhoneNotificationResponse.bitField0_;
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
                                this.bitField0_ |= 1;
                                this.enabled_ = codedInputStream.readBool();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.allowAllApps_ = codedInputStream.readBool();
                            } else if (readTag == 26) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.appFilters_ = readString;
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
                    synchronized (Protocol$PhoneNotificationResponse.class) {
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
