package android.stats.launcher;

import android.stats.launcher.LauncherTarget;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class LauncherExtension extends GeneratedMessageLite<LauncherExtension, Builder> implements LauncherExtensionOrBuilder {
    private static final LauncherExtension DEFAULT_INSTANCE = new LauncherExtension();
    public static final int DST_TARGET_FIELD_NUMBER = 2;
    private static volatile Parser<LauncherExtension> PARSER = null;
    public static final int SRC_TARGET_FIELD_NUMBER = 1;
    private Internal.ProtobufList<LauncherTarget> dstTarget_ = emptyProtobufList();
    private Internal.ProtobufList<LauncherTarget> srcTarget_ = emptyProtobufList();

    private LauncherExtension() {
    }

    @Override // android.stats.launcher.LauncherExtensionOrBuilder
    public List<LauncherTarget> getSrcTargetList() {
        return this.srcTarget_;
    }

    public List<? extends LauncherTargetOrBuilder> getSrcTargetOrBuilderList() {
        return this.srcTarget_;
    }

    @Override // android.stats.launcher.LauncherExtensionOrBuilder
    public int getSrcTargetCount() {
        return this.srcTarget_.size();
    }

    @Override // android.stats.launcher.LauncherExtensionOrBuilder
    public LauncherTarget getSrcTarget(int index) {
        return this.srcTarget_.get(index);
    }

    public LauncherTargetOrBuilder getSrcTargetOrBuilder(int index) {
        return this.srcTarget_.get(index);
    }

    private void ensureSrcTargetIsMutable() {
        if (!this.srcTarget_.isModifiable()) {
            this.srcTarget_ = GeneratedMessageLite.mutableCopy(this.srcTarget_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSrcTarget(int index, LauncherTarget value) {
        if (value != null) {
            ensureSrcTargetIsMutable();
            this.srcTarget_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSrcTarget(int index, LauncherTarget.Builder builderForValue) {
        ensureSrcTargetIsMutable();
        this.srcTarget_.set(index, (LauncherTarget) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSrcTarget(LauncherTarget value) {
        if (value != null) {
            ensureSrcTargetIsMutable();
            this.srcTarget_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSrcTarget(int index, LauncherTarget value) {
        if (value != null) {
            ensureSrcTargetIsMutable();
            this.srcTarget_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSrcTarget(LauncherTarget.Builder builderForValue) {
        ensureSrcTargetIsMutable();
        this.srcTarget_.add((LauncherTarget) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSrcTarget(int index, LauncherTarget.Builder builderForValue) {
        ensureSrcTargetIsMutable();
        this.srcTarget_.add(index, (LauncherTarget) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSrcTarget(Iterable<? extends LauncherTarget> values) {
        ensureSrcTargetIsMutable();
        AbstractMessageLite.addAll(values, this.srcTarget_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSrcTarget() {
        this.srcTarget_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSrcTarget(int index) {
        ensureSrcTargetIsMutable();
        this.srcTarget_.remove(index);
    }

    @Override // android.stats.launcher.LauncherExtensionOrBuilder
    public List<LauncherTarget> getDstTargetList() {
        return this.dstTarget_;
    }

    public List<? extends LauncherTargetOrBuilder> getDstTargetOrBuilderList() {
        return this.dstTarget_;
    }

    @Override // android.stats.launcher.LauncherExtensionOrBuilder
    public int getDstTargetCount() {
        return this.dstTarget_.size();
    }

    @Override // android.stats.launcher.LauncherExtensionOrBuilder
    public LauncherTarget getDstTarget(int index) {
        return this.dstTarget_.get(index);
    }

    public LauncherTargetOrBuilder getDstTargetOrBuilder(int index) {
        return this.dstTarget_.get(index);
    }

    private void ensureDstTargetIsMutable() {
        if (!this.dstTarget_.isModifiable()) {
            this.dstTarget_ = GeneratedMessageLite.mutableCopy(this.dstTarget_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDstTarget(int index, LauncherTarget value) {
        if (value != null) {
            ensureDstTargetIsMutable();
            this.dstTarget_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDstTarget(int index, LauncherTarget.Builder builderForValue) {
        ensureDstTargetIsMutable();
        this.dstTarget_.set(index, (LauncherTarget) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDstTarget(LauncherTarget value) {
        if (value != null) {
            ensureDstTargetIsMutable();
            this.dstTarget_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDstTarget(int index, LauncherTarget value) {
        if (value != null) {
            ensureDstTargetIsMutable();
            this.dstTarget_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDstTarget(LauncherTarget.Builder builderForValue) {
        ensureDstTargetIsMutable();
        this.dstTarget_.add((LauncherTarget) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDstTarget(int index, LauncherTarget.Builder builderForValue) {
        ensureDstTargetIsMutable();
        this.dstTarget_.add(index, (LauncherTarget) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDstTarget(Iterable<? extends LauncherTarget> values) {
        ensureDstTargetIsMutable();
        AbstractMessageLite.addAll(values, this.dstTarget_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDstTarget() {
        this.dstTarget_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDstTarget(int index) {
        ensureDstTargetIsMutable();
        this.dstTarget_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.srcTarget_.size(); i++) {
            output.writeMessage(1, this.srcTarget_.get(i));
        }
        for (int i2 = 0; i2 < this.dstTarget_.size(); i2++) {
            output.writeMessage(2, this.dstTarget_.get(i2));
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
        for (int i = 0; i < this.srcTarget_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.srcTarget_.get(i));
        }
        for (int i2 = 0; i2 < this.dstTarget_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.dstTarget_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static LauncherExtension parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LauncherExtension parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LauncherExtension parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LauncherExtension parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LauncherExtension parseFrom(InputStream input) throws IOException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LauncherExtension parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LauncherExtension parseDelimitedFrom(InputStream input) throws IOException {
        return (LauncherExtension) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static LauncherExtension parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LauncherExtension) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LauncherExtension parseFrom(CodedInputStream input) throws IOException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LauncherExtension parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LauncherExtension) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LauncherExtension prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LauncherExtension, Builder> implements LauncherExtensionOrBuilder {
        private Builder() {
            super(LauncherExtension.DEFAULT_INSTANCE);
        }

        @Override // android.stats.launcher.LauncherExtensionOrBuilder
        public List<LauncherTarget> getSrcTargetList() {
            return Collections.unmodifiableList(((LauncherExtension) this.instance).getSrcTargetList());
        }

        @Override // android.stats.launcher.LauncherExtensionOrBuilder
        public int getSrcTargetCount() {
            return ((LauncherExtension) this.instance).getSrcTargetCount();
        }

        @Override // android.stats.launcher.LauncherExtensionOrBuilder
        public LauncherTarget getSrcTarget(int index) {
            return ((LauncherExtension) this.instance).getSrcTarget(index);
        }

        public Builder setSrcTarget(int index, LauncherTarget value) {
            copyOnWrite();
            ((LauncherExtension) this.instance).setSrcTarget((LauncherExtension) index, (int) value);
            return this;
        }

        public Builder setSrcTarget(int index, LauncherTarget.Builder builderForValue) {
            copyOnWrite();
            ((LauncherExtension) this.instance).setSrcTarget((LauncherExtension) index, (int) builderForValue);
            return this;
        }

        public Builder addSrcTarget(LauncherTarget value) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addSrcTarget((LauncherExtension) value);
            return this;
        }

        public Builder addSrcTarget(int index, LauncherTarget value) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addSrcTarget((LauncherExtension) index, (int) value);
            return this;
        }

        public Builder addSrcTarget(LauncherTarget.Builder builderForValue) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addSrcTarget((LauncherExtension) builderForValue);
            return this;
        }

        public Builder addSrcTarget(int index, LauncherTarget.Builder builderForValue) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addSrcTarget((LauncherExtension) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSrcTarget(Iterable<? extends LauncherTarget> values) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addAllSrcTarget(values);
            return this;
        }

        public Builder clearSrcTarget() {
            copyOnWrite();
            ((LauncherExtension) this.instance).clearSrcTarget();
            return this;
        }

        public Builder removeSrcTarget(int index) {
            copyOnWrite();
            ((LauncherExtension) this.instance).removeSrcTarget(index);
            return this;
        }

        @Override // android.stats.launcher.LauncherExtensionOrBuilder
        public List<LauncherTarget> getDstTargetList() {
            return Collections.unmodifiableList(((LauncherExtension) this.instance).getDstTargetList());
        }

        @Override // android.stats.launcher.LauncherExtensionOrBuilder
        public int getDstTargetCount() {
            return ((LauncherExtension) this.instance).getDstTargetCount();
        }

        @Override // android.stats.launcher.LauncherExtensionOrBuilder
        public LauncherTarget getDstTarget(int index) {
            return ((LauncherExtension) this.instance).getDstTarget(index);
        }

        public Builder setDstTarget(int index, LauncherTarget value) {
            copyOnWrite();
            ((LauncherExtension) this.instance).setDstTarget((LauncherExtension) index, (int) value);
            return this;
        }

        public Builder setDstTarget(int index, LauncherTarget.Builder builderForValue) {
            copyOnWrite();
            ((LauncherExtension) this.instance).setDstTarget((LauncherExtension) index, (int) builderForValue);
            return this;
        }

        public Builder addDstTarget(LauncherTarget value) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addDstTarget((LauncherExtension) value);
            return this;
        }

        public Builder addDstTarget(int index, LauncherTarget value) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addDstTarget((LauncherExtension) index, (int) value);
            return this;
        }

        public Builder addDstTarget(LauncherTarget.Builder builderForValue) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addDstTarget((LauncherExtension) builderForValue);
            return this;
        }

        public Builder addDstTarget(int index, LauncherTarget.Builder builderForValue) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addDstTarget((LauncherExtension) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDstTarget(Iterable<? extends LauncherTarget> values) {
            copyOnWrite();
            ((LauncherExtension) this.instance).addAllDstTarget(values);
            return this;
        }

        public Builder clearDstTarget() {
            copyOnWrite();
            ((LauncherExtension) this.instance).clearDstTarget();
            return this;
        }

        public Builder removeDstTarget(int index) {
            copyOnWrite();
            ((LauncherExtension) this.instance).removeDstTarget(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new LauncherExtension();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.srcTarget_.makeImmutable();
                this.dstTarget_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                LauncherExtension other = (LauncherExtension) arg1;
                this.srcTarget_ = visitor.visitList(this.srcTarget_, other.srcTarget_);
                this.dstTarget_ = visitor.visitList(this.dstTarget_, other.dstTarget_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            if (!this.srcTarget_.isModifiable()) {
                                this.srcTarget_ = GeneratedMessageLite.mutableCopy(this.srcTarget_);
                            }
                            this.srcTarget_.add((LauncherTarget) input.readMessage(LauncherTarget.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            if (!this.dstTarget_.isModifiable()) {
                                this.dstTarget_ = GeneratedMessageLite.mutableCopy(this.dstTarget_);
                            }
                            this.dstTarget_.add((LauncherTarget) input.readMessage(LauncherTarget.parser(), extensionRegistry));
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
                    synchronized (LauncherExtension.class) {
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

    public static LauncherExtension getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LauncherExtension> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
