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

public final class Protocol$TextSend extends GeneratedMessageLite<Protocol$TextSend, Builder> implements Protocol$TextSendOrBuilder {
    private static final Protocol$TextSend DEFAULT_INSTANCE = new Protocol$TextSend();
    private static volatile Parser<Protocol$TextSend> PARSER;
    private int bitField0_;
    private String text_ = "";

    private Protocol$TextSend() {
    }

    public boolean hasText() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getText() {
        return this.text_;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getText());
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getText());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Protocol$TextSend parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Protocol$TextSend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Protocol$TextSend, Builder> implements Protocol$TextSendOrBuilder {
        /* synthetic */ Builder(Protocol$1 protocol$1) {
            this();
        }

        private Builder() {
            super(Protocol$TextSend.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protocol$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protocol$TextSend();
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return DEFAULT_INSTANCE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return null;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return new Builder(null);
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Protocol$TextSend protocol$TextSend = (Protocol$TextSend) obj2;
                this.text_ = visitor.visitString(hasText(), this.text_, protocol$TextSend.hasText(), protocol$TextSend.text_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= protocol$TextSend.bitField0_;
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
                                this.text_ = readString;
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
                    synchronized (Protocol$TextSend.class) {
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
