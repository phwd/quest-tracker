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

public final class Protocol$RenderKeyboardStart extends GeneratedMessageLite<Protocol$RenderKeyboardStart, Builder> implements Protocol$RenderKeyboardStartOrBuilder {
    private static final Protocol$RenderKeyboardStart DEFAULT_INSTANCE = new Protocol$RenderKeyboardStart();
    private static volatile Parser<Protocol$RenderKeyboardStart> PARSER;
    private int bitField0_;
    private String hardwareId_ = "";
    private String layout_ = "";

    private Protocol$RenderKeyboardStart() {
    }

    public boolean hasHardwareId() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getHardwareId() {
        return this.hardwareId_;
    }

    public boolean hasLayout() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getLayout() {
        return this.layout_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getHardwareId());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getLayout());
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getHardwareId());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getLayout());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$RenderKeyboardStart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$RenderKeyboardStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$RenderKeyboardStart, Builder> implements Protocol$RenderKeyboardStartOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$RenderKeyboardStart.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$RenderKeyboardStart();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$RenderKeyboardStart protocol$RenderKeyboardStart = (Protocol$RenderKeyboardStart) obj2;
                this.hardwareId_ = visitor.visitString(hasHardwareId(), this.hardwareId_, protocol$RenderKeyboardStart.hasHardwareId(), protocol$RenderKeyboardStart.hardwareId_);
                this.layout_ = visitor.visitString(hasLayout(), this.layout_, protocol$RenderKeyboardStart.hasLayout(), protocol$RenderKeyboardStart.layout_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$RenderKeyboardStart.bitField0_;
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
                            if (readTag == 10) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.hardwareId_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.layout_ = readString2;
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
                    synchronized (Protocol$RenderKeyboardStart.class) {
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
