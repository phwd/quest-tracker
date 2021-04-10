package android.service.usb;

import android.service.usb.UsbAlsaDeviceProto;
import android.service.usb.UsbMidiDeviceProto;
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

public final class UsbAlsaManagerProto extends GeneratedMessageLite<UsbAlsaManagerProto, Builder> implements UsbAlsaManagerProtoOrBuilder {
    public static final int ALSA_DEVICES_FIELD_NUMBER = 2;
    public static final int CARDS_PARSER_FIELD_NUMBER = 1;
    private static final UsbAlsaManagerProto DEFAULT_INSTANCE = new UsbAlsaManagerProto();
    public static final int MIDI_DEVICES_FIELD_NUMBER = 3;
    private static volatile Parser<UsbAlsaManagerProto> PARSER;
    private Internal.ProtobufList<UsbAlsaDeviceProto> alsaDevices_ = emptyProtobufList();
    private int bitField0_;
    private int cardsParser_ = 0;
    private Internal.ProtobufList<UsbMidiDeviceProto> midiDevices_ = emptyProtobufList();

    private UsbAlsaManagerProto() {
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public boolean hasCardsParser() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public int getCardsParser() {
        return this.cardsParser_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCardsParser(int value) {
        this.bitField0_ |= 1;
        this.cardsParser_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCardsParser() {
        this.bitField0_ &= -2;
        this.cardsParser_ = 0;
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public List<UsbAlsaDeviceProto> getAlsaDevicesList() {
        return this.alsaDevices_;
    }

    public List<? extends UsbAlsaDeviceProtoOrBuilder> getAlsaDevicesOrBuilderList() {
        return this.alsaDevices_;
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public int getAlsaDevicesCount() {
        return this.alsaDevices_.size();
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public UsbAlsaDeviceProto getAlsaDevices(int index) {
        return this.alsaDevices_.get(index);
    }

    public UsbAlsaDeviceProtoOrBuilder getAlsaDevicesOrBuilder(int index) {
        return this.alsaDevices_.get(index);
    }

    private void ensureAlsaDevicesIsMutable() {
        if (!this.alsaDevices_.isModifiable()) {
            this.alsaDevices_ = GeneratedMessageLite.mutableCopy(this.alsaDevices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlsaDevices(int index, UsbAlsaDeviceProto value) {
        if (value != null) {
            ensureAlsaDevicesIsMutable();
            this.alsaDevices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlsaDevices(int index, UsbAlsaDeviceProto.Builder builderForValue) {
        ensureAlsaDevicesIsMutable();
        this.alsaDevices_.set(index, (UsbAlsaDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlsaDevices(UsbAlsaDeviceProto value) {
        if (value != null) {
            ensureAlsaDevicesIsMutable();
            this.alsaDevices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlsaDevices(int index, UsbAlsaDeviceProto value) {
        if (value != null) {
            ensureAlsaDevicesIsMutable();
            this.alsaDevices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlsaDevices(UsbAlsaDeviceProto.Builder builderForValue) {
        ensureAlsaDevicesIsMutable();
        this.alsaDevices_.add((UsbAlsaDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlsaDevices(int index, UsbAlsaDeviceProto.Builder builderForValue) {
        ensureAlsaDevicesIsMutable();
        this.alsaDevices_.add(index, (UsbAlsaDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAlsaDevices(Iterable<? extends UsbAlsaDeviceProto> values) {
        ensureAlsaDevicesIsMutable();
        AbstractMessageLite.addAll(values, this.alsaDevices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlsaDevices() {
        this.alsaDevices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAlsaDevices(int index) {
        ensureAlsaDevicesIsMutable();
        this.alsaDevices_.remove(index);
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public List<UsbMidiDeviceProto> getMidiDevicesList() {
        return this.midiDevices_;
    }

    public List<? extends UsbMidiDeviceProtoOrBuilder> getMidiDevicesOrBuilderList() {
        return this.midiDevices_;
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public int getMidiDevicesCount() {
        return this.midiDevices_.size();
    }

    @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
    public UsbMidiDeviceProto getMidiDevices(int index) {
        return this.midiDevices_.get(index);
    }

    public UsbMidiDeviceProtoOrBuilder getMidiDevicesOrBuilder(int index) {
        return this.midiDevices_.get(index);
    }

    private void ensureMidiDevicesIsMutable() {
        if (!this.midiDevices_.isModifiable()) {
            this.midiDevices_ = GeneratedMessageLite.mutableCopy(this.midiDevices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMidiDevices(int index, UsbMidiDeviceProto value) {
        if (value != null) {
            ensureMidiDevicesIsMutable();
            this.midiDevices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMidiDevices(int index, UsbMidiDeviceProto.Builder builderForValue) {
        ensureMidiDevicesIsMutable();
        this.midiDevices_.set(index, (UsbMidiDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMidiDevices(UsbMidiDeviceProto value) {
        if (value != null) {
            ensureMidiDevicesIsMutable();
            this.midiDevices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMidiDevices(int index, UsbMidiDeviceProto value) {
        if (value != null) {
            ensureMidiDevicesIsMutable();
            this.midiDevices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMidiDevices(UsbMidiDeviceProto.Builder builderForValue) {
        ensureMidiDevicesIsMutable();
        this.midiDevices_.add((UsbMidiDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMidiDevices(int index, UsbMidiDeviceProto.Builder builderForValue) {
        ensureMidiDevicesIsMutable();
        this.midiDevices_.add(index, (UsbMidiDeviceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllMidiDevices(Iterable<? extends UsbMidiDeviceProto> values) {
        ensureMidiDevicesIsMutable();
        AbstractMessageLite.addAll(values, this.midiDevices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMidiDevices() {
        this.midiDevices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeMidiDevices(int index) {
        ensureMidiDevicesIsMutable();
        this.midiDevices_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.cardsParser_);
        }
        for (int i = 0; i < this.alsaDevices_.size(); i++) {
            output.writeMessage(2, this.alsaDevices_.get(i));
        }
        for (int i2 = 0; i2 < this.midiDevices_.size(); i2++) {
            output.writeMessage(3, this.midiDevices_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.cardsParser_);
        }
        for (int i = 0; i < this.alsaDevices_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.alsaDevices_.get(i));
        }
        for (int i2 = 0; i2 < this.midiDevices_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.midiDevices_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbAlsaManagerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAlsaManagerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAlsaManagerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAlsaManagerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAlsaManagerProto parseFrom(InputStream input) throws IOException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAlsaManagerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAlsaManagerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbAlsaManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAlsaManagerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAlsaManagerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAlsaManagerProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAlsaManagerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAlsaManagerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbAlsaManagerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbAlsaManagerProto, Builder> implements UsbAlsaManagerProtoOrBuilder {
        private Builder() {
            super(UsbAlsaManagerProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public boolean hasCardsParser() {
            return ((UsbAlsaManagerProto) this.instance).hasCardsParser();
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public int getCardsParser() {
            return ((UsbAlsaManagerProto) this.instance).getCardsParser();
        }

        public Builder setCardsParser(int value) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).setCardsParser(value);
            return this;
        }

        public Builder clearCardsParser() {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).clearCardsParser();
            return this;
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public List<UsbAlsaDeviceProto> getAlsaDevicesList() {
            return Collections.unmodifiableList(((UsbAlsaManagerProto) this.instance).getAlsaDevicesList());
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public int getAlsaDevicesCount() {
            return ((UsbAlsaManagerProto) this.instance).getAlsaDevicesCount();
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public UsbAlsaDeviceProto getAlsaDevices(int index) {
            return ((UsbAlsaManagerProto) this.instance).getAlsaDevices(index);
        }

        public Builder setAlsaDevices(int index, UsbAlsaDeviceProto value) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).setAlsaDevices((UsbAlsaManagerProto) index, (int) value);
            return this;
        }

        public Builder setAlsaDevices(int index, UsbAlsaDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).setAlsaDevices((UsbAlsaManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAlsaDevices(UsbAlsaDeviceProto value) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addAlsaDevices((UsbAlsaManagerProto) value);
            return this;
        }

        public Builder addAlsaDevices(int index, UsbAlsaDeviceProto value) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addAlsaDevices((UsbAlsaManagerProto) index, (int) value);
            return this;
        }

        public Builder addAlsaDevices(UsbAlsaDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addAlsaDevices((UsbAlsaManagerProto) builderForValue);
            return this;
        }

        public Builder addAlsaDevices(int index, UsbAlsaDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addAlsaDevices((UsbAlsaManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAlsaDevices(Iterable<? extends UsbAlsaDeviceProto> values) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addAllAlsaDevices(values);
            return this;
        }

        public Builder clearAlsaDevices() {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).clearAlsaDevices();
            return this;
        }

        public Builder removeAlsaDevices(int index) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).removeAlsaDevices(index);
            return this;
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public List<UsbMidiDeviceProto> getMidiDevicesList() {
            return Collections.unmodifiableList(((UsbAlsaManagerProto) this.instance).getMidiDevicesList());
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public int getMidiDevicesCount() {
            return ((UsbAlsaManagerProto) this.instance).getMidiDevicesCount();
        }

        @Override // android.service.usb.UsbAlsaManagerProtoOrBuilder
        public UsbMidiDeviceProto getMidiDevices(int index) {
            return ((UsbAlsaManagerProto) this.instance).getMidiDevices(index);
        }

        public Builder setMidiDevices(int index, UsbMidiDeviceProto value) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).setMidiDevices((UsbAlsaManagerProto) index, (int) value);
            return this;
        }

        public Builder setMidiDevices(int index, UsbMidiDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).setMidiDevices((UsbAlsaManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addMidiDevices(UsbMidiDeviceProto value) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addMidiDevices((UsbAlsaManagerProto) value);
            return this;
        }

        public Builder addMidiDevices(int index, UsbMidiDeviceProto value) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addMidiDevices((UsbAlsaManagerProto) index, (int) value);
            return this;
        }

        public Builder addMidiDevices(UsbMidiDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addMidiDevices((UsbAlsaManagerProto) builderForValue);
            return this;
        }

        public Builder addMidiDevices(int index, UsbMidiDeviceProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addMidiDevices((UsbAlsaManagerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllMidiDevices(Iterable<? extends UsbMidiDeviceProto> values) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).addAllMidiDevices(values);
            return this;
        }

        public Builder clearMidiDevices() {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).clearMidiDevices();
            return this;
        }

        public Builder removeMidiDevices(int index) {
            copyOnWrite();
            ((UsbAlsaManagerProto) this.instance).removeMidiDevices(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbAlsaManagerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.alsaDevices_.makeImmutable();
                this.midiDevices_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbAlsaManagerProto other = (UsbAlsaManagerProto) arg1;
                this.cardsParser_ = visitor.visitInt(hasCardsParser(), this.cardsParser_, other.hasCardsParser(), other.cardsParser_);
                this.alsaDevices_ = visitor.visitList(this.alsaDevices_, other.alsaDevices_);
                this.midiDevices_ = visitor.visitList(this.midiDevices_, other.midiDevices_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.cardsParser_ = input.readInt32();
                        } else if (tag == 18) {
                            if (!this.alsaDevices_.isModifiable()) {
                                this.alsaDevices_ = GeneratedMessageLite.mutableCopy(this.alsaDevices_);
                            }
                            this.alsaDevices_.add((UsbAlsaDeviceProto) input.readMessage(UsbAlsaDeviceProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.midiDevices_.isModifiable()) {
                                this.midiDevices_ = GeneratedMessageLite.mutableCopy(this.midiDevices_);
                            }
                            this.midiDevices_.add((UsbMidiDeviceProto) input.readMessage(UsbMidiDeviceProto.parser(), extensionRegistry));
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
                    synchronized (UsbAlsaManagerProto.class) {
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

    public static UsbAlsaManagerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbAlsaManagerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
