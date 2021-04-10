package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class Protocol$PhoneNotificationRequest extends GeneratedMessageLite<Protocol$PhoneNotificationRequest, Builder> implements Protocol$PhoneNotificationRequestOrBuilder {
    private static final Protocol$PhoneNotificationRequest DEFAULT_INSTANCE = new Protocol$PhoneNotificationRequest();
    private static volatile Parser<Protocol$PhoneNotificationRequest> PARSER;
    private boolean allowAllApps_ = false;
    private String appFilters_ = "";
    private int bitField0_;
    private boolean enable_ = false;
    private byte memoizedIsInitialized = -1;

    private Protocol$PhoneNotificationRequest() {
    }

    public boolean hasEnable() {
        return (this.bitField0_ & 1) == 1;
    }

    public boolean getEnable() {
        return this.enable_;
    }

    public boolean hasAllowAllApps() {
        return (this.bitField0_ & 2) == 2;
    }

    public boolean getAllowAllApps() {
        return this.allowAllApps_;
    }

    public boolean hasAppFilters() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getAppFilters() {
        return this.appFilters_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBool(1, this.enable_);
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
            i2 = 0 + CodedOutputStream.computeBoolSize(1, this.enable_);
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

    public static Protocol$PhoneNotificationRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$PhoneNotificationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$PhoneNotificationRequest, Builder> implements Protocol$PhoneNotificationRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$PhoneNotificationRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$PhoneNotificationRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasEnable()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasAllowAllApps()) {
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
                Protocol$PhoneNotificationRequest protocol$PhoneNotificationRequest = (Protocol$PhoneNotificationRequest) obj2;
                this.enable_ = visitor.visitBoolean(hasEnable(), this.enable_, protocol$PhoneNotificationRequest.hasEnable(), protocol$PhoneNotificationRequest.enable_);
                this.allowAllApps_ = visitor.visitBoolean(hasAllowAllApps(), this.allowAllApps_, protocol$PhoneNotificationRequest.hasAllowAllApps(), protocol$PhoneNotificationRequest.allowAllApps_);
                this.appFilters_ = visitor.visitString(hasAppFilters(), this.appFilters_, protocol$PhoneNotificationRequest.hasAppFilters(), protocol$PhoneNotificationRequest.appFilters_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$PhoneNotificationRequest.bitField0_;
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
                                this.enable_ = codedInputStream.readBool();
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
                    synchronized (Protocol$PhoneNotificationRequest.class) {
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
