package com.android.os.statsd;

import com.android.os.AtomsProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class ShellDataProto {

    public interface ShellDataOrBuilder extends MessageLiteOrBuilder {
        AtomsProto.Atom getAtom(int i);

        int getAtomCount();

        List<AtomsProto.Atom> getAtomList();
    }

    private ShellDataProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class ShellData extends GeneratedMessageLite<ShellData, Builder> implements ShellDataOrBuilder {
        public static final int ATOM_FIELD_NUMBER = 1;
        private static final ShellData DEFAULT_INSTANCE = new ShellData();
        private static volatile Parser<ShellData> PARSER;
        private Internal.ProtobufList<AtomsProto.Atom> atom_ = emptyProtobufList();

        private ShellData() {
        }

        @Override // com.android.os.statsd.ShellDataProto.ShellDataOrBuilder
        public List<AtomsProto.Atom> getAtomList() {
            return this.atom_;
        }

        public List<? extends AtomsProto.AtomOrBuilder> getAtomOrBuilderList() {
            return this.atom_;
        }

        @Override // com.android.os.statsd.ShellDataProto.ShellDataOrBuilder
        public int getAtomCount() {
            return this.atom_.size();
        }

        @Override // com.android.os.statsd.ShellDataProto.ShellDataOrBuilder
        public AtomsProto.Atom getAtom(int index) {
            return this.atom_.get(index);
        }

        public AtomsProto.AtomOrBuilder getAtomOrBuilder(int index) {
            return this.atom_.get(index);
        }

        private void ensureAtomIsMutable() {
            if (!this.atom_.isModifiable()) {
                this.atom_ = GeneratedMessageLite.mutableCopy(this.atom_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAtom(int index, AtomsProto.Atom value) {
            if (value != null) {
                ensureAtomIsMutable();
                this.atom_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAtom(int index, AtomsProto.Atom.Builder builderForValue) {
            ensureAtomIsMutable();
            this.atom_.set(index, (AtomsProto.Atom) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtom(AtomsProto.Atom value) {
            if (value != null) {
                ensureAtomIsMutable();
                this.atom_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtom(int index, AtomsProto.Atom value) {
            if (value != null) {
                ensureAtomIsMutable();
                this.atom_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtom(AtomsProto.Atom.Builder builderForValue) {
            ensureAtomIsMutable();
            this.atom_.add((AtomsProto.Atom) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAtom(int index, AtomsProto.Atom.Builder builderForValue) {
            ensureAtomIsMutable();
            this.atom_.add(index, (AtomsProto.Atom) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllAtom(Iterable<? extends AtomsProto.Atom> values) {
            ensureAtomIsMutable();
            AbstractMessageLite.addAll(values, this.atom_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAtom() {
            this.atom_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeAtom(int index) {
            ensureAtomIsMutable();
            this.atom_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.atom_.size(); i++) {
                output.writeMessage(1, this.atom_.get(i));
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
            for (int i = 0; i < this.atom_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.atom_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ShellData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ShellData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ShellData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ShellData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ShellData parseFrom(InputStream input) throws IOException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ShellData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ShellData parseDelimitedFrom(InputStream input) throws IOException {
            return (ShellData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ShellData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ShellData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ShellData parseFrom(CodedInputStream input) throws IOException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ShellData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ShellData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ShellData prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ShellData, Builder> implements ShellDataOrBuilder {
            private Builder() {
                super(ShellData.DEFAULT_INSTANCE);
            }

            @Override // com.android.os.statsd.ShellDataProto.ShellDataOrBuilder
            public List<AtomsProto.Atom> getAtomList() {
                return Collections.unmodifiableList(((ShellData) this.instance).getAtomList());
            }

            @Override // com.android.os.statsd.ShellDataProto.ShellDataOrBuilder
            public int getAtomCount() {
                return ((ShellData) this.instance).getAtomCount();
            }

            @Override // com.android.os.statsd.ShellDataProto.ShellDataOrBuilder
            public AtomsProto.Atom getAtom(int index) {
                return ((ShellData) this.instance).getAtom(index);
            }

            public Builder setAtom(int index, AtomsProto.Atom value) {
                copyOnWrite();
                ((ShellData) this.instance).setAtom((ShellData) index, (int) value);
                return this;
            }

            public Builder setAtom(int index, AtomsProto.Atom.Builder builderForValue) {
                copyOnWrite();
                ((ShellData) this.instance).setAtom((ShellData) index, (int) builderForValue);
                return this;
            }

            public Builder addAtom(AtomsProto.Atom value) {
                copyOnWrite();
                ((ShellData) this.instance).addAtom((ShellData) value);
                return this;
            }

            public Builder addAtom(int index, AtomsProto.Atom value) {
                copyOnWrite();
                ((ShellData) this.instance).addAtom((ShellData) index, (int) value);
                return this;
            }

            public Builder addAtom(AtomsProto.Atom.Builder builderForValue) {
                copyOnWrite();
                ((ShellData) this.instance).addAtom((ShellData) builderForValue);
                return this;
            }

            public Builder addAtom(int index, AtomsProto.Atom.Builder builderForValue) {
                copyOnWrite();
                ((ShellData) this.instance).addAtom((ShellData) index, (int) builderForValue);
                return this;
            }

            public Builder addAllAtom(Iterable<? extends AtomsProto.Atom> values) {
                copyOnWrite();
                ((ShellData) this.instance).addAllAtom(values);
                return this;
            }

            public Builder clearAtom() {
                copyOnWrite();
                ((ShellData) this.instance).clearAtom();
                return this;
            }

            public Builder removeAtom(int index) {
                copyOnWrite();
                ((ShellData) this.instance).removeAtom(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ShellData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.atom_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    this.atom_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.atom_, ((ShellData) arg1).atom_);
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
                                if (!this.atom_.isModifiable()) {
                                    this.atom_ = GeneratedMessageLite.mutableCopy(this.atom_);
                                }
                                this.atom_.add((AtomsProto.Atom) input.readMessage(AtomsProto.Atom.parser(), extensionRegistry));
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
                        synchronized (ShellData.class) {
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

        public static ShellData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ShellData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
