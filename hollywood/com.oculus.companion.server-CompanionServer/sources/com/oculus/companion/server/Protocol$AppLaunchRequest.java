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

public final class Protocol$AppLaunchRequest extends GeneratedMessageLite<Protocol$AppLaunchRequest, Builder> implements Protocol$AppLaunchRequestOrBuilder {
    private static final Protocol$AppLaunchRequest DEFAULT_INSTANCE = new Protocol$AppLaunchRequest();
    private static volatile Parser<Protocol$AppLaunchRequest> PARSER;
    private String appId_ = "";
    private int bitField0_;
    private String launchParametersJson_ = "";
    private byte memoizedIsInitialized = -1;
    private String packageName_ = "";

    private Protocol$AppLaunchRequest() {
    }

    public boolean hasAppId() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getAppId() {
        return this.appId_;
    }

    public boolean hasPackageName() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getPackageName() {
        return this.packageName_;
    }

    public boolean hasLaunchParametersJson() {
        return (this.bitField0_ & 4) == 4;
    }

    public String getLaunchParametersJson() {
        return this.launchParametersJson_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getAppId());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getPackageName());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, getLaunchParametersJson());
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getAppId());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getPackageName());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, getLaunchParametersJson());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$AppLaunchRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$AppLaunchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$AppLaunchRequest, Builder> implements Protocol$AppLaunchRequestOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$AppLaunchRequest.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$AppLaunchRequest();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (b == 0) {
                    return null;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (!hasAppId()) {
                    if (booleanValue) {
                        this.memoizedIsInitialized = 0;
                    }
                    return null;
                } else if (!hasPackageName()) {
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
                Protocol$AppLaunchRequest protocol$AppLaunchRequest = (Protocol$AppLaunchRequest) obj2;
                this.appId_ = visitor.visitString(hasAppId(), this.appId_, protocol$AppLaunchRequest.hasAppId(), protocol$AppLaunchRequest.appId_);
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, protocol$AppLaunchRequest.hasPackageName(), protocol$AppLaunchRequest.packageName_);
                this.launchParametersJson_ = visitor.visitString(hasLaunchParametersJson(), this.launchParametersJson_, protocol$AppLaunchRequest.hasLaunchParametersJson(), protocol$AppLaunchRequest.launchParametersJson_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$AppLaunchRequest.bitField0_;
                }
                return this;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 1;
                                this.appId_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.packageName_ = readString2;
                            } else if (readTag == 26) {
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.launchParametersJson_ = readString3;
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
                    synchronized (Protocol$AppLaunchRequest.class) {
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
