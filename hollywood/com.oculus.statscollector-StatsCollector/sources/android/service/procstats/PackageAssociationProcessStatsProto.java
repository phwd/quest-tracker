package android.service.procstats;

import android.service.procstats.PackageAssociationSourceProcessStatsProto;
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

public final class PackageAssociationProcessStatsProto extends GeneratedMessageLite<PackageAssociationProcessStatsProto, Builder> implements PackageAssociationProcessStatsProtoOrBuilder {
    public static final int COMPONENT_NAME_FIELD_NUMBER = 1;
    private static final PackageAssociationProcessStatsProto DEFAULT_INSTANCE = new PackageAssociationProcessStatsProto();
    private static volatile Parser<PackageAssociationProcessStatsProto> PARSER = null;
    public static final int SOURCES_FIELD_NUMBER = 2;
    private int bitField0_;
    private String componentName_ = "";
    private Internal.ProtobufList<PackageAssociationSourceProcessStatsProto> sources_ = emptyProtobufList();

    private PackageAssociationProcessStatsProto() {
    }

    @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
    public boolean hasComponentName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
    public String getComponentName() {
        return this.componentName_;
    }

    @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
    public ByteString getComponentNameBytes() {
        return ByteString.copyFromUtf8(this.componentName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponentName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.componentName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearComponentName() {
        this.bitField0_ &= -2;
        this.componentName_ = getDefaultInstance().getComponentName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponentNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.componentName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
    public List<PackageAssociationSourceProcessStatsProto> getSourcesList() {
        return this.sources_;
    }

    public List<? extends PackageAssociationSourceProcessStatsProtoOrBuilder> getSourcesOrBuilderList() {
        return this.sources_;
    }

    @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
    public int getSourcesCount() {
        return this.sources_.size();
    }

    @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
    public PackageAssociationSourceProcessStatsProto getSources(int index) {
        return this.sources_.get(index);
    }

    public PackageAssociationSourceProcessStatsProtoOrBuilder getSourcesOrBuilder(int index) {
        return this.sources_.get(index);
    }

    private void ensureSourcesIsMutable() {
        if (!this.sources_.isModifiable()) {
            this.sources_ = GeneratedMessageLite.mutableCopy(this.sources_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSources(int index, PackageAssociationSourceProcessStatsProto value) {
        if (value != null) {
            ensureSourcesIsMutable();
            this.sources_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSources(int index, PackageAssociationSourceProcessStatsProto.Builder builderForValue) {
        ensureSourcesIsMutable();
        this.sources_.set(index, (PackageAssociationSourceProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSources(PackageAssociationSourceProcessStatsProto value) {
        if (value != null) {
            ensureSourcesIsMutable();
            this.sources_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSources(int index, PackageAssociationSourceProcessStatsProto value) {
        if (value != null) {
            ensureSourcesIsMutable();
            this.sources_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSources(PackageAssociationSourceProcessStatsProto.Builder builderForValue) {
        ensureSourcesIsMutable();
        this.sources_.add((PackageAssociationSourceProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSources(int index, PackageAssociationSourceProcessStatsProto.Builder builderForValue) {
        ensureSourcesIsMutable();
        this.sources_.add(index, (PackageAssociationSourceProcessStatsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSources(Iterable<? extends PackageAssociationSourceProcessStatsProto> values) {
        ensureSourcesIsMutable();
        AbstractMessageLite.addAll(values, this.sources_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSources() {
        this.sources_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSources(int index) {
        ensureSourcesIsMutable();
        this.sources_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getComponentName());
        }
        for (int i = 0; i < this.sources_.size(); i++) {
            output.writeMessage(2, this.sources_.get(i));
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
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeStringSize(1, getComponentName());
        }
        for (int i = 0; i < this.sources_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.sources_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageAssociationProcessStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageAssociationProcessStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageAssociationProcessStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageAssociationProcessStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageAssociationProcessStatsProto parseFrom(InputStream input) throws IOException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageAssociationProcessStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageAssociationProcessStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageAssociationProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageAssociationProcessStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageAssociationProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageAssociationProcessStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageAssociationProcessStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageAssociationProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageAssociationProcessStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageAssociationProcessStatsProto, Builder> implements PackageAssociationProcessStatsProtoOrBuilder {
        private Builder() {
            super(PackageAssociationProcessStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
        public boolean hasComponentName() {
            return ((PackageAssociationProcessStatsProto) this.instance).hasComponentName();
        }

        @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
        public String getComponentName() {
            return ((PackageAssociationProcessStatsProto) this.instance).getComponentName();
        }

        @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
        public ByteString getComponentNameBytes() {
            return ((PackageAssociationProcessStatsProto) this.instance).getComponentNameBytes();
        }

        public Builder setComponentName(String value) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).setComponentName(value);
            return this;
        }

        public Builder clearComponentName() {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).clearComponentName();
            return this;
        }

        public Builder setComponentNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).setComponentNameBytes(value);
            return this;
        }

        @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
        public List<PackageAssociationSourceProcessStatsProto> getSourcesList() {
            return Collections.unmodifiableList(((PackageAssociationProcessStatsProto) this.instance).getSourcesList());
        }

        @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
        public int getSourcesCount() {
            return ((PackageAssociationProcessStatsProto) this.instance).getSourcesCount();
        }

        @Override // android.service.procstats.PackageAssociationProcessStatsProtoOrBuilder
        public PackageAssociationSourceProcessStatsProto getSources(int index) {
            return ((PackageAssociationProcessStatsProto) this.instance).getSources(index);
        }

        public Builder setSources(int index, PackageAssociationSourceProcessStatsProto value) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).setSources((PackageAssociationProcessStatsProto) index, (int) value);
            return this;
        }

        public Builder setSources(int index, PackageAssociationSourceProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).setSources((PackageAssociationProcessStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSources(PackageAssociationSourceProcessStatsProto value) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).addSources((PackageAssociationProcessStatsProto) value);
            return this;
        }

        public Builder addSources(int index, PackageAssociationSourceProcessStatsProto value) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).addSources((PackageAssociationProcessStatsProto) index, (int) value);
            return this;
        }

        public Builder addSources(PackageAssociationSourceProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).addSources((PackageAssociationProcessStatsProto) builderForValue);
            return this;
        }

        public Builder addSources(int index, PackageAssociationSourceProcessStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).addSources((PackageAssociationProcessStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSources(Iterable<? extends PackageAssociationSourceProcessStatsProto> values) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).addAllSources(values);
            return this;
        }

        public Builder clearSources() {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).clearSources();
            return this;
        }

        public Builder removeSources(int index) {
            copyOnWrite();
            ((PackageAssociationProcessStatsProto) this.instance).removeSources(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageAssociationProcessStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.sources_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageAssociationProcessStatsProto other = (PackageAssociationProcessStatsProto) arg1;
                this.componentName_ = visitor.visitString(hasComponentName(), this.componentName_, other.hasComponentName(), other.componentName_);
                this.sources_ = visitor.visitList(this.sources_, other.sources_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
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
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.componentName_ = s;
                        } else if (tag == 18) {
                            if (!this.sources_.isModifiable()) {
                                this.sources_ = GeneratedMessageLite.mutableCopy(this.sources_);
                            }
                            this.sources_.add((PackageAssociationSourceProcessStatsProto) input.readMessage(PackageAssociationSourceProcessStatsProto.parser(), extensionRegistry));
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
                    synchronized (PackageAssociationProcessStatsProto.class) {
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

    public static PackageAssociationProcessStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageAssociationProcessStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
