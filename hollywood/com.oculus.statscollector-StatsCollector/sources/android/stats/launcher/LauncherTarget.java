package android.stats.launcher;

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

public final class LauncherTarget extends GeneratedMessageLite<LauncherTarget, Builder> implements LauncherTargetOrBuilder {
    public static final int CONTAINER_FIELD_NUMBER = 3;
    public static final int CONTROL_FIELD_NUMBER = 4;
    private static final LauncherTarget DEFAULT_INSTANCE = new LauncherTarget();
    public static final int GRID_X_FIELD_NUMBER = 7;
    public static final int GRID_Y_FIELD_NUMBER = 8;
    public static final int ITEM_FIELD_NUMBER = 2;
    public static final int LAUNCH_COMPONENT_FIELD_NUMBER = 5;
    public static final int PAGE_ID_FIELD_NUMBER = 6;
    private static volatile Parser<LauncherTarget> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int bitField0_;
    private int container_ = 0;
    private int control_ = 0;
    private int gridX_ = 0;
    private int gridY_ = 0;
    private int item_ = 0;
    private String launchComponent_ = "";
    private int pageId_ = 0;
    private int type_ = 0;

    private LauncherTarget() {
    }

    public enum Type implements Internal.EnumLite {
        NONE(0),
        ITEM_TYPE(1),
        CONTROL_TYPE(2),
        CONTAINER_TYPE(3);
        
        public static final int CONTAINER_TYPE_VALUE = 3;
        public static final int CONTROL_TYPE_VALUE = 2;
        public static final int ITEM_TYPE_VALUE = 1;
        public static final int NONE_VALUE = 0;
        private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
            /* class android.stats.launcher.LauncherTarget.Type.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Type findValueByNumber(int number) {
                return Type.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Type valueOf(int value2) {
            return forNumber(value2);
        }

        public static Type forNumber(int value2) {
            if (value2 == 0) {
                return NONE;
            }
            if (value2 == 1) {
                return ITEM_TYPE;
            }
            if (value2 == 2) {
                return CONTROL_TYPE;
            }
            if (value2 != 3) {
                return null;
            }
            return CONTAINER_TYPE;
        }

        public static Internal.EnumLiteMap<Type> internalGetValueMap() {
            return internalValueMap;
        }

        private Type(int value2) {
            this.value = value2;
        }
    }

    public enum Item implements Internal.EnumLite {
        DEFAULT_ITEM(0),
        APP_ICON(1),
        SHORTCUT(2),
        WIDGET(3),
        FOLDER_ICON(4),
        DEEPSHORTCUT(5),
        SEARCHBOX(6),
        EDITTEXT(7),
        NOTIFICATION(8),
        TASK(9);
        
        public static final int APP_ICON_VALUE = 1;
        public static final int DEEPSHORTCUT_VALUE = 5;
        public static final int DEFAULT_ITEM_VALUE = 0;
        public static final int EDITTEXT_VALUE = 7;
        public static final int FOLDER_ICON_VALUE = 4;
        public static final int NOTIFICATION_VALUE = 8;
        public static final int SEARCHBOX_VALUE = 6;
        public static final int SHORTCUT_VALUE = 2;
        public static final int TASK_VALUE = 9;
        public static final int WIDGET_VALUE = 3;
        private static final Internal.EnumLiteMap<Item> internalValueMap = new Internal.EnumLiteMap<Item>() {
            /* class android.stats.launcher.LauncherTarget.Item.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Item findValueByNumber(int number) {
                return Item.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Item valueOf(int value2) {
            return forNumber(value2);
        }

        public static Item forNumber(int value2) {
            switch (value2) {
                case 0:
                    return DEFAULT_ITEM;
                case 1:
                    return APP_ICON;
                case 2:
                    return SHORTCUT;
                case 3:
                    return WIDGET;
                case 4:
                    return FOLDER_ICON;
                case 5:
                    return DEEPSHORTCUT;
                case 6:
                    return SEARCHBOX;
                case 7:
                    return EDITTEXT;
                case 8:
                    return NOTIFICATION;
                case 9:
                    return TASK;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Item> internalGetValueMap() {
            return internalValueMap;
        }

        private Item(int value2) {
            this.value = value2;
        }
    }

    public enum Container implements Internal.EnumLite {
        DEFAULT_CONTAINER(0),
        HOTSEAT(1),
        FOLDER(2),
        PREDICTION(3),
        SEARCHRESULT(4);
        
        public static final int DEFAULT_CONTAINER_VALUE = 0;
        public static final int FOLDER_VALUE = 2;
        public static final int HOTSEAT_VALUE = 1;
        public static final int PREDICTION_VALUE = 3;
        public static final int SEARCHRESULT_VALUE = 4;
        private static final Internal.EnumLiteMap<Container> internalValueMap = new Internal.EnumLiteMap<Container>() {
            /* class android.stats.launcher.LauncherTarget.Container.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Container findValueByNumber(int number) {
                return Container.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Container valueOf(int value2) {
            return forNumber(value2);
        }

        public static Container forNumber(int value2) {
            if (value2 == 0) {
                return DEFAULT_CONTAINER;
            }
            if (value2 == 1) {
                return HOTSEAT;
            }
            if (value2 == 2) {
                return FOLDER;
            }
            if (value2 == 3) {
                return PREDICTION;
            }
            if (value2 != 4) {
                return null;
            }
            return SEARCHRESULT;
        }

        public static Internal.EnumLiteMap<Container> internalGetValueMap() {
            return internalValueMap;
        }

        private Container(int value2) {
            this.value = value2;
        }
    }

    public enum Control implements Internal.EnumLite {
        DEFAULT_CONTROL(0),
        MENU(1),
        UNINSTALL(2),
        REMOVE(3);
        
        public static final int DEFAULT_CONTROL_VALUE = 0;
        public static final int MENU_VALUE = 1;
        public static final int REMOVE_VALUE = 3;
        public static final int UNINSTALL_VALUE = 2;
        private static final Internal.EnumLiteMap<Control> internalValueMap = new Internal.EnumLiteMap<Control>() {
            /* class android.stats.launcher.LauncherTarget.Control.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Control findValueByNumber(int number) {
                return Control.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Control valueOf(int value2) {
            return forNumber(value2);
        }

        public static Control forNumber(int value2) {
            if (value2 == 0) {
                return DEFAULT_CONTROL;
            }
            if (value2 == 1) {
                return MENU;
            }
            if (value2 == 2) {
                return UNINSTALL;
            }
            if (value2 != 3) {
                return null;
            }
            return REMOVE;
        }

        public static Internal.EnumLiteMap<Control> internalGetValueMap() {
            return internalValueMap;
        }

        private Control(int value2) {
            this.value = value2;
        }
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public Type getType() {
        Type result = Type.forNumber(this.type_);
        return result == null ? Type.NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(Type value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.type_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -2;
        this.type_ = 0;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasItem() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public Item getItem() {
        Item result = Item.forNumber(this.item_);
        return result == null ? Item.DEFAULT_ITEM : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setItem(Item value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.item_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearItem() {
        this.bitField0_ &= -3;
        this.item_ = 0;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasContainer() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public Container getContainer() {
        Container result = Container.forNumber(this.container_);
        return result == null ? Container.DEFAULT_CONTAINER : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContainer(Container value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.container_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContainer() {
        this.bitField0_ &= -5;
        this.container_ = 0;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasControl() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public Control getControl() {
        Control result = Control.forNumber(this.control_);
        return result == null ? Control.DEFAULT_CONTROL : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setControl(Control value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.control_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearControl() {
        this.bitField0_ &= -9;
        this.control_ = 0;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasLaunchComponent() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public String getLaunchComponent() {
        return this.launchComponent_;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public ByteString getLaunchComponentBytes() {
        return ByteString.copyFromUtf8(this.launchComponent_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLaunchComponent(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.launchComponent_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLaunchComponent() {
        this.bitField0_ &= -17;
        this.launchComponent_ = getDefaultInstance().getLaunchComponent();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLaunchComponentBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.launchComponent_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasPageId() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public int getPageId() {
        return this.pageId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPageId(int value) {
        this.bitField0_ |= 32;
        this.pageId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPageId() {
        this.bitField0_ &= -33;
        this.pageId_ = 0;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasGridX() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public int getGridX() {
        return this.gridX_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGridX(int value) {
        this.bitField0_ |= 64;
        this.gridX_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGridX() {
        this.bitField0_ &= -65;
        this.gridX_ = 0;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public boolean hasGridY() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.stats.launcher.LauncherTargetOrBuilder
    public int getGridY() {
        return this.gridY_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGridY(int value) {
        this.bitField0_ |= 128;
        this.gridY_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGridY() {
        this.bitField0_ &= -129;
        this.gridY_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.item_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.container_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.control_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getLaunchComponent());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.pageId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.gridX_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.gridY_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.item_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.container_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.control_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getLaunchComponent());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.pageId_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.gridX_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.gridY_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static LauncherTarget parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LauncherTarget parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LauncherTarget parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LauncherTarget parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LauncherTarget parseFrom(InputStream input) throws IOException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LauncherTarget parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LauncherTarget parseDelimitedFrom(InputStream input) throws IOException {
        return (LauncherTarget) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static LauncherTarget parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LauncherTarget) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LauncherTarget parseFrom(CodedInputStream input) throws IOException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LauncherTarget parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LauncherTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LauncherTarget prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LauncherTarget, Builder> implements LauncherTargetOrBuilder {
        private Builder() {
            super(LauncherTarget.DEFAULT_INSTANCE);
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasType() {
            return ((LauncherTarget) this.instance).hasType();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public Type getType() {
            return ((LauncherTarget) this.instance).getType();
        }

        public Builder setType(Type value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearType();
            return this;
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasItem() {
            return ((LauncherTarget) this.instance).hasItem();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public Item getItem() {
            return ((LauncherTarget) this.instance).getItem();
        }

        public Builder setItem(Item value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setItem(value);
            return this;
        }

        public Builder clearItem() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearItem();
            return this;
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasContainer() {
            return ((LauncherTarget) this.instance).hasContainer();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public Container getContainer() {
            return ((LauncherTarget) this.instance).getContainer();
        }

        public Builder setContainer(Container value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setContainer(value);
            return this;
        }

        public Builder clearContainer() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearContainer();
            return this;
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasControl() {
            return ((LauncherTarget) this.instance).hasControl();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public Control getControl() {
            return ((LauncherTarget) this.instance).getControl();
        }

        public Builder setControl(Control value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setControl(value);
            return this;
        }

        public Builder clearControl() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearControl();
            return this;
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasLaunchComponent() {
            return ((LauncherTarget) this.instance).hasLaunchComponent();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public String getLaunchComponent() {
            return ((LauncherTarget) this.instance).getLaunchComponent();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public ByteString getLaunchComponentBytes() {
            return ((LauncherTarget) this.instance).getLaunchComponentBytes();
        }

        public Builder setLaunchComponent(String value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setLaunchComponent(value);
            return this;
        }

        public Builder clearLaunchComponent() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearLaunchComponent();
            return this;
        }

        public Builder setLaunchComponentBytes(ByteString value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setLaunchComponentBytes(value);
            return this;
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasPageId() {
            return ((LauncherTarget) this.instance).hasPageId();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public int getPageId() {
            return ((LauncherTarget) this.instance).getPageId();
        }

        public Builder setPageId(int value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setPageId(value);
            return this;
        }

        public Builder clearPageId() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearPageId();
            return this;
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasGridX() {
            return ((LauncherTarget) this.instance).hasGridX();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public int getGridX() {
            return ((LauncherTarget) this.instance).getGridX();
        }

        public Builder setGridX(int value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setGridX(value);
            return this;
        }

        public Builder clearGridX() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearGridX();
            return this;
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public boolean hasGridY() {
            return ((LauncherTarget) this.instance).hasGridY();
        }

        @Override // android.stats.launcher.LauncherTargetOrBuilder
        public int getGridY() {
            return ((LauncherTarget) this.instance).getGridY();
        }

        public Builder setGridY(int value) {
            copyOnWrite();
            ((LauncherTarget) this.instance).setGridY(value);
            return this;
        }

        public Builder clearGridY() {
            copyOnWrite();
            ((LauncherTarget) this.instance).clearGridY();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new LauncherTarget();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                LauncherTarget other = (LauncherTarget) arg1;
                this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                this.item_ = visitor.visitInt(hasItem(), this.item_, other.hasItem(), other.item_);
                this.container_ = visitor.visitInt(hasContainer(), this.container_, other.hasContainer(), other.container_);
                this.control_ = visitor.visitInt(hasControl(), this.control_, other.hasControl(), other.control_);
                this.launchComponent_ = visitor.visitString(hasLaunchComponent(), this.launchComponent_, other.hasLaunchComponent(), other.launchComponent_);
                this.pageId_ = visitor.visitInt(hasPageId(), this.pageId_, other.hasPageId(), other.pageId_);
                this.gridX_ = visitor.visitInt(hasGridX(), this.gridX_, other.hasGridX(), other.gridX_);
                this.gridY_ = visitor.visitInt(hasGridY(), this.gridY_, other.hasGridY(), other.gridY_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            if (Type.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.type_ = rawValue;
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            if (Item.forNumber(rawValue2) == null) {
                                super.mergeVarintField(2, rawValue2);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.item_ = rawValue2;
                            }
                        } else if (tag == 24) {
                            int rawValue3 = input.readEnum();
                            if (Container.forNumber(rawValue3) == null) {
                                super.mergeVarintField(3, rawValue3);
                            } else {
                                this.bitField0_ |= 4;
                                this.container_ = rawValue3;
                            }
                        } else if (tag == 32) {
                            int rawValue4 = input.readEnum();
                            if (Control.forNumber(rawValue4) == null) {
                                super.mergeVarintField(4, rawValue4);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.control_ = rawValue4;
                            }
                        } else if (tag == 42) {
                            String s = input.readString();
                            this.bitField0_ = 16 | this.bitField0_;
                            this.launchComponent_ = s;
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.pageId_ = input.readInt32();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.gridX_ = input.readInt32();
                        } else if (tag == 64) {
                            this.bitField0_ |= 128;
                            this.gridY_ = input.readInt32();
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
                    synchronized (LauncherTarget.class) {
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

    public static LauncherTarget getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LauncherTarget> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
