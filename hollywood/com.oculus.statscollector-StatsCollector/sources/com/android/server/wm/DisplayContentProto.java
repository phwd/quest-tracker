package com.android.server.wm;

import android.view.DisplayInfoProto;
import com.android.server.wm.AppTransitionProto;
import com.android.server.wm.DisplayFramesProto;
import com.android.server.wm.DockedStackDividerControllerProto;
import com.android.server.wm.IdentifierProto;
import com.android.server.wm.PinnedStackControllerProto;
import com.android.server.wm.ScreenRotationAnimationProto;
import com.android.server.wm.StackProto;
import com.android.server.wm.WindowContainerProto;
import com.android.server.wm.WindowTokenProto;
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

public final class DisplayContentProto extends GeneratedMessageLite<DisplayContentProto, Builder> implements DisplayContentProtoOrBuilder {
    public static final int ABOVE_APP_WINDOWS_FIELD_NUMBER = 6;
    public static final int APP_TRANSITION_FIELD_NUMBER = 16;
    public static final int BELOW_APP_WINDOWS_FIELD_NUMBER = 7;
    public static final int CHANGING_APPS_FIELD_NUMBER = 19;
    public static final int CLOSING_APPS_FIELD_NUMBER = 18;
    private static final DisplayContentProto DEFAULT_INSTANCE = new DisplayContentProto();
    public static final int DISPLAY_FRAMES_FIELD_NUMBER = 13;
    public static final int DISPLAY_INFO_FIELD_NUMBER = 10;
    public static final int DOCKED_STACK_DIVIDER_CONTROLLER_FIELD_NUMBER = 4;
    public static final int DPI_FIELD_NUMBER = 9;
    public static final int FOCUSED_APP_FIELD_NUMBER = 15;
    public static final int ID_FIELD_NUMBER = 2;
    public static final int IME_WINDOWS_FIELD_NUMBER = 8;
    public static final int OPENING_APPS_FIELD_NUMBER = 17;
    private static volatile Parser<DisplayContentProto> PARSER = null;
    public static final int PINNED_STACK_CONTROLLER_FIELD_NUMBER = 5;
    public static final int ROTATION_FIELD_NUMBER = 11;
    public static final int SCREEN_ROTATION_ANIMATION_FIELD_NUMBER = 12;
    public static final int STACKS_FIELD_NUMBER = 3;
    public static final int SURFACE_SIZE_FIELD_NUMBER = 14;
    public static final int WINDOW_CONTAINER_FIELD_NUMBER = 1;
    private Internal.ProtobufList<WindowTokenProto> aboveAppWindows_ = emptyProtobufList();
    private AppTransitionProto appTransition_;
    private Internal.ProtobufList<WindowTokenProto> belowAppWindows_ = emptyProtobufList();
    private int bitField0_;
    private Internal.ProtobufList<IdentifierProto> changingApps_ = emptyProtobufList();
    private Internal.ProtobufList<IdentifierProto> closingApps_ = emptyProtobufList();
    private DisplayFramesProto displayFrames_;
    private DisplayInfoProto displayInfo_;
    private DockedStackDividerControllerProto dockedStackDividerController_;
    private int dpi_ = 0;
    private String focusedApp_ = "";
    private int id_ = 0;
    private Internal.ProtobufList<WindowTokenProto> imeWindows_ = emptyProtobufList();
    private Internal.ProtobufList<IdentifierProto> openingApps_ = emptyProtobufList();
    private PinnedStackControllerProto pinnedStackController_;
    private int rotation_ = 0;
    private ScreenRotationAnimationProto screenRotationAnimation_;
    private Internal.ProtobufList<StackProto> stacks_ = emptyProtobufList();
    private int surfaceSize_ = 0;
    private WindowContainerProto windowContainer_;

    private DisplayContentProto() {
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasWindowContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public WindowContainerProto getWindowContainer() {
        WindowContainerProto windowContainerProto = this.windowContainer_;
        return windowContainerProto == null ? WindowContainerProto.getDefaultInstance() : windowContainerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowContainer(WindowContainerProto value) {
        if (value != null) {
            this.windowContainer_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowContainer(WindowContainerProto.Builder builderForValue) {
        this.windowContainer_ = (WindowContainerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowContainer(WindowContainerProto value) {
        WindowContainerProto windowContainerProto = this.windowContainer_;
        if (windowContainerProto == null || windowContainerProto == WindowContainerProto.getDefaultInstance()) {
            this.windowContainer_ = value;
        } else {
            this.windowContainer_ = (WindowContainerProto) ((WindowContainerProto.Builder) WindowContainerProto.newBuilder(this.windowContainer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowContainer() {
        this.windowContainer_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getId() {
        return this.id_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(int value) {
        this.bitField0_ |= 2;
        this.id_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.bitField0_ &= -3;
        this.id_ = 0;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public List<StackProto> getStacksList() {
        return this.stacks_;
    }

    public List<? extends StackProtoOrBuilder> getStacksOrBuilderList() {
        return this.stacks_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getStacksCount() {
        return this.stacks_.size();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public StackProto getStacks(int index) {
        return this.stacks_.get(index);
    }

    public StackProtoOrBuilder getStacksOrBuilder(int index) {
        return this.stacks_.get(index);
    }

    private void ensureStacksIsMutable() {
        if (!this.stacks_.isModifiable()) {
            this.stacks_ = GeneratedMessageLite.mutableCopy(this.stacks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStacks(int index, StackProto value) {
        if (value != null) {
            ensureStacksIsMutable();
            this.stacks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStacks(int index, StackProto.Builder builderForValue) {
        ensureStacksIsMutable();
        this.stacks_.set(index, (StackProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(StackProto value) {
        if (value != null) {
            ensureStacksIsMutable();
            this.stacks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(int index, StackProto value) {
        if (value != null) {
            ensureStacksIsMutable();
            this.stacks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(StackProto.Builder builderForValue) {
        ensureStacksIsMutable();
        this.stacks_.add((StackProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStacks(int index, StackProto.Builder builderForValue) {
        ensureStacksIsMutable();
        this.stacks_.add(index, (StackProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStacks(Iterable<? extends StackProto> values) {
        ensureStacksIsMutable();
        AbstractMessageLite.addAll(values, this.stacks_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStacks() {
        this.stacks_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStacks(int index) {
        ensureStacksIsMutable();
        this.stacks_.remove(index);
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasDockedStackDividerController() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public DockedStackDividerControllerProto getDockedStackDividerController() {
        DockedStackDividerControllerProto dockedStackDividerControllerProto = this.dockedStackDividerController_;
        return dockedStackDividerControllerProto == null ? DockedStackDividerControllerProto.getDefaultInstance() : dockedStackDividerControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDockedStackDividerController(DockedStackDividerControllerProto value) {
        if (value != null) {
            this.dockedStackDividerController_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDockedStackDividerController(DockedStackDividerControllerProto.Builder builderForValue) {
        this.dockedStackDividerController_ = (DockedStackDividerControllerProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDockedStackDividerController(DockedStackDividerControllerProto value) {
        DockedStackDividerControllerProto dockedStackDividerControllerProto = this.dockedStackDividerController_;
        if (dockedStackDividerControllerProto == null || dockedStackDividerControllerProto == DockedStackDividerControllerProto.getDefaultInstance()) {
            this.dockedStackDividerController_ = value;
        } else {
            this.dockedStackDividerController_ = (DockedStackDividerControllerProto) ((DockedStackDividerControllerProto.Builder) DockedStackDividerControllerProto.newBuilder(this.dockedStackDividerController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDockedStackDividerController() {
        this.dockedStackDividerController_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasPinnedStackController() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public PinnedStackControllerProto getPinnedStackController() {
        PinnedStackControllerProto pinnedStackControllerProto = this.pinnedStackController_;
        return pinnedStackControllerProto == null ? PinnedStackControllerProto.getDefaultInstance() : pinnedStackControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPinnedStackController(PinnedStackControllerProto value) {
        if (value != null) {
            this.pinnedStackController_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPinnedStackController(PinnedStackControllerProto.Builder builderForValue) {
        this.pinnedStackController_ = (PinnedStackControllerProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePinnedStackController(PinnedStackControllerProto value) {
        PinnedStackControllerProto pinnedStackControllerProto = this.pinnedStackController_;
        if (pinnedStackControllerProto == null || pinnedStackControllerProto == PinnedStackControllerProto.getDefaultInstance()) {
            this.pinnedStackController_ = value;
        } else {
            this.pinnedStackController_ = (PinnedStackControllerProto) ((PinnedStackControllerProto.Builder) PinnedStackControllerProto.newBuilder(this.pinnedStackController_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPinnedStackController() {
        this.pinnedStackController_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public List<WindowTokenProto> getAboveAppWindowsList() {
        return this.aboveAppWindows_;
    }

    public List<? extends WindowTokenProtoOrBuilder> getAboveAppWindowsOrBuilderList() {
        return this.aboveAppWindows_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getAboveAppWindowsCount() {
        return this.aboveAppWindows_.size();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public WindowTokenProto getAboveAppWindows(int index) {
        return this.aboveAppWindows_.get(index);
    }

    public WindowTokenProtoOrBuilder getAboveAppWindowsOrBuilder(int index) {
        return this.aboveAppWindows_.get(index);
    }

    private void ensureAboveAppWindowsIsMutable() {
        if (!this.aboveAppWindows_.isModifiable()) {
            this.aboveAppWindows_ = GeneratedMessageLite.mutableCopy(this.aboveAppWindows_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAboveAppWindows(int index, WindowTokenProto value) {
        if (value != null) {
            ensureAboveAppWindowsIsMutable();
            this.aboveAppWindows_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAboveAppWindows(int index, WindowTokenProto.Builder builderForValue) {
        ensureAboveAppWindowsIsMutable();
        this.aboveAppWindows_.set(index, (WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAboveAppWindows(WindowTokenProto value) {
        if (value != null) {
            ensureAboveAppWindowsIsMutable();
            this.aboveAppWindows_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAboveAppWindows(int index, WindowTokenProto value) {
        if (value != null) {
            ensureAboveAppWindowsIsMutable();
            this.aboveAppWindows_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAboveAppWindows(WindowTokenProto.Builder builderForValue) {
        ensureAboveAppWindowsIsMutable();
        this.aboveAppWindows_.add((WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAboveAppWindows(int index, WindowTokenProto.Builder builderForValue) {
        ensureAboveAppWindowsIsMutable();
        this.aboveAppWindows_.add(index, (WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAboveAppWindows(Iterable<? extends WindowTokenProto> values) {
        ensureAboveAppWindowsIsMutable();
        AbstractMessageLite.addAll(values, this.aboveAppWindows_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAboveAppWindows() {
        this.aboveAppWindows_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAboveAppWindows(int index) {
        ensureAboveAppWindowsIsMutable();
        this.aboveAppWindows_.remove(index);
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public List<WindowTokenProto> getBelowAppWindowsList() {
        return this.belowAppWindows_;
    }

    public List<? extends WindowTokenProtoOrBuilder> getBelowAppWindowsOrBuilderList() {
        return this.belowAppWindows_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getBelowAppWindowsCount() {
        return this.belowAppWindows_.size();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public WindowTokenProto getBelowAppWindows(int index) {
        return this.belowAppWindows_.get(index);
    }

    public WindowTokenProtoOrBuilder getBelowAppWindowsOrBuilder(int index) {
        return this.belowAppWindows_.get(index);
    }

    private void ensureBelowAppWindowsIsMutable() {
        if (!this.belowAppWindows_.isModifiable()) {
            this.belowAppWindows_ = GeneratedMessageLite.mutableCopy(this.belowAppWindows_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBelowAppWindows(int index, WindowTokenProto value) {
        if (value != null) {
            ensureBelowAppWindowsIsMutable();
            this.belowAppWindows_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBelowAppWindows(int index, WindowTokenProto.Builder builderForValue) {
        ensureBelowAppWindowsIsMutable();
        this.belowAppWindows_.set(index, (WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBelowAppWindows(WindowTokenProto value) {
        if (value != null) {
            ensureBelowAppWindowsIsMutable();
            this.belowAppWindows_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBelowAppWindows(int index, WindowTokenProto value) {
        if (value != null) {
            ensureBelowAppWindowsIsMutable();
            this.belowAppWindows_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBelowAppWindows(WindowTokenProto.Builder builderForValue) {
        ensureBelowAppWindowsIsMutable();
        this.belowAppWindows_.add((WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBelowAppWindows(int index, WindowTokenProto.Builder builderForValue) {
        ensureBelowAppWindowsIsMutable();
        this.belowAppWindows_.add(index, (WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBelowAppWindows(Iterable<? extends WindowTokenProto> values) {
        ensureBelowAppWindowsIsMutable();
        AbstractMessageLite.addAll(values, this.belowAppWindows_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBelowAppWindows() {
        this.belowAppWindows_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBelowAppWindows(int index) {
        ensureBelowAppWindowsIsMutable();
        this.belowAppWindows_.remove(index);
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public List<WindowTokenProto> getImeWindowsList() {
        return this.imeWindows_;
    }

    public List<? extends WindowTokenProtoOrBuilder> getImeWindowsOrBuilderList() {
        return this.imeWindows_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getImeWindowsCount() {
        return this.imeWindows_.size();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public WindowTokenProto getImeWindows(int index) {
        return this.imeWindows_.get(index);
    }

    public WindowTokenProtoOrBuilder getImeWindowsOrBuilder(int index) {
        return this.imeWindows_.get(index);
    }

    private void ensureImeWindowsIsMutable() {
        if (!this.imeWindows_.isModifiable()) {
            this.imeWindows_ = GeneratedMessageLite.mutableCopy(this.imeWindows_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImeWindows(int index, WindowTokenProto value) {
        if (value != null) {
            ensureImeWindowsIsMutable();
            this.imeWindows_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImeWindows(int index, WindowTokenProto.Builder builderForValue) {
        ensureImeWindowsIsMutable();
        this.imeWindows_.set(index, (WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImeWindows(WindowTokenProto value) {
        if (value != null) {
            ensureImeWindowsIsMutable();
            this.imeWindows_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImeWindows(int index, WindowTokenProto value) {
        if (value != null) {
            ensureImeWindowsIsMutable();
            this.imeWindows_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImeWindows(WindowTokenProto.Builder builderForValue) {
        ensureImeWindowsIsMutable();
        this.imeWindows_.add((WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addImeWindows(int index, WindowTokenProto.Builder builderForValue) {
        ensureImeWindowsIsMutable();
        this.imeWindows_.add(index, (WindowTokenProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllImeWindows(Iterable<? extends WindowTokenProto> values) {
        ensureImeWindowsIsMutable();
        AbstractMessageLite.addAll(values, this.imeWindows_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearImeWindows() {
        this.imeWindows_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeImeWindows(int index) {
        ensureImeWindowsIsMutable();
        this.imeWindows_.remove(index);
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasDpi() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getDpi() {
        return this.dpi_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDpi(int value) {
        this.bitField0_ |= 16;
        this.dpi_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDpi() {
        this.bitField0_ &= -17;
        this.dpi_ = 0;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasDisplayInfo() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public DisplayInfoProto getDisplayInfo() {
        DisplayInfoProto displayInfoProto = this.displayInfo_;
        return displayInfoProto == null ? DisplayInfoProto.getDefaultInstance() : displayInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayInfo(DisplayInfoProto value) {
        if (value != null) {
            this.displayInfo_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayInfo(DisplayInfoProto.Builder builderForValue) {
        this.displayInfo_ = (DisplayInfoProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDisplayInfo(DisplayInfoProto value) {
        DisplayInfoProto displayInfoProto = this.displayInfo_;
        if (displayInfoProto == null || displayInfoProto == DisplayInfoProto.getDefaultInstance()) {
            this.displayInfo_ = value;
        } else {
            this.displayInfo_ = (DisplayInfoProto) ((DisplayInfoProto.Builder) DisplayInfoProto.newBuilder(this.displayInfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayInfo() {
        this.displayInfo_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasRotation() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getRotation() {
        return this.rotation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotation(int value) {
        this.bitField0_ |= 64;
        this.rotation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRotation() {
        this.bitField0_ &= -65;
        this.rotation_ = 0;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasScreenRotationAnimation() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public ScreenRotationAnimationProto getScreenRotationAnimation() {
        ScreenRotationAnimationProto screenRotationAnimationProto = this.screenRotationAnimation_;
        return screenRotationAnimationProto == null ? ScreenRotationAnimationProto.getDefaultInstance() : screenRotationAnimationProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenRotationAnimation(ScreenRotationAnimationProto value) {
        if (value != null) {
            this.screenRotationAnimation_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenRotationAnimation(ScreenRotationAnimationProto.Builder builderForValue) {
        this.screenRotationAnimation_ = (ScreenRotationAnimationProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeScreenRotationAnimation(ScreenRotationAnimationProto value) {
        ScreenRotationAnimationProto screenRotationAnimationProto = this.screenRotationAnimation_;
        if (screenRotationAnimationProto == null || screenRotationAnimationProto == ScreenRotationAnimationProto.getDefaultInstance()) {
            this.screenRotationAnimation_ = value;
        } else {
            this.screenRotationAnimation_ = (ScreenRotationAnimationProto) ((ScreenRotationAnimationProto.Builder) ScreenRotationAnimationProto.newBuilder(this.screenRotationAnimation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenRotationAnimation() {
        this.screenRotationAnimation_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasDisplayFrames() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public DisplayFramesProto getDisplayFrames() {
        DisplayFramesProto displayFramesProto = this.displayFrames_;
        return displayFramesProto == null ? DisplayFramesProto.getDefaultInstance() : displayFramesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayFrames(DisplayFramesProto value) {
        if (value != null) {
            this.displayFrames_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayFrames(DisplayFramesProto.Builder builderForValue) {
        this.displayFrames_ = (DisplayFramesProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDisplayFrames(DisplayFramesProto value) {
        DisplayFramesProto displayFramesProto = this.displayFrames_;
        if (displayFramesProto == null || displayFramesProto == DisplayFramesProto.getDefaultInstance()) {
            this.displayFrames_ = value;
        } else {
            this.displayFrames_ = (DisplayFramesProto) ((DisplayFramesProto.Builder) DisplayFramesProto.newBuilder(this.displayFrames_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayFrames() {
        this.displayFrames_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    @Deprecated
    public boolean hasSurfaceSize() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    @Deprecated
    public int getSurfaceSize() {
        return this.surfaceSize_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceSize(int value) {
        this.bitField0_ |= 512;
        this.surfaceSize_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurfaceSize() {
        this.bitField0_ &= -513;
        this.surfaceSize_ = 0;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasFocusedApp() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public String getFocusedApp() {
        return this.focusedApp_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public ByteString getFocusedAppBytes() {
        return ByteString.copyFromUtf8(this.focusedApp_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedApp(String value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.focusedApp_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFocusedApp() {
        this.bitField0_ &= -1025;
        this.focusedApp_ = getDefaultInstance().getFocusedApp();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedAppBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.focusedApp_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public boolean hasAppTransition() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public AppTransitionProto getAppTransition() {
        AppTransitionProto appTransitionProto = this.appTransition_;
        return appTransitionProto == null ? AppTransitionProto.getDefaultInstance() : appTransitionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppTransition(AppTransitionProto value) {
        if (value != null) {
            this.appTransition_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppTransition(AppTransitionProto.Builder builderForValue) {
        this.appTransition_ = (AppTransitionProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAppTransition(AppTransitionProto value) {
        AppTransitionProto appTransitionProto = this.appTransition_;
        if (appTransitionProto == null || appTransitionProto == AppTransitionProto.getDefaultInstance()) {
            this.appTransition_ = value;
        } else {
            this.appTransition_ = (AppTransitionProto) ((AppTransitionProto.Builder) AppTransitionProto.newBuilder(this.appTransition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppTransition() {
        this.appTransition_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public List<IdentifierProto> getOpeningAppsList() {
        return this.openingApps_;
    }

    public List<? extends IdentifierProtoOrBuilder> getOpeningAppsOrBuilderList() {
        return this.openingApps_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getOpeningAppsCount() {
        return this.openingApps_.size();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public IdentifierProto getOpeningApps(int index) {
        return this.openingApps_.get(index);
    }

    public IdentifierProtoOrBuilder getOpeningAppsOrBuilder(int index) {
        return this.openingApps_.get(index);
    }

    private void ensureOpeningAppsIsMutable() {
        if (!this.openingApps_.isModifiable()) {
            this.openingApps_ = GeneratedMessageLite.mutableCopy(this.openingApps_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOpeningApps(int index, IdentifierProto value) {
        if (value != null) {
            ensureOpeningAppsIsMutable();
            this.openingApps_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOpeningApps(int index, IdentifierProto.Builder builderForValue) {
        ensureOpeningAppsIsMutable();
        this.openingApps_.set(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOpeningApps(IdentifierProto value) {
        if (value != null) {
            ensureOpeningAppsIsMutable();
            this.openingApps_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOpeningApps(int index, IdentifierProto value) {
        if (value != null) {
            ensureOpeningAppsIsMutable();
            this.openingApps_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOpeningApps(IdentifierProto.Builder builderForValue) {
        ensureOpeningAppsIsMutable();
        this.openingApps_.add((IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOpeningApps(int index, IdentifierProto.Builder builderForValue) {
        ensureOpeningAppsIsMutable();
        this.openingApps_.add(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllOpeningApps(Iterable<? extends IdentifierProto> values) {
        ensureOpeningAppsIsMutable();
        AbstractMessageLite.addAll(values, this.openingApps_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOpeningApps() {
        this.openingApps_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeOpeningApps(int index) {
        ensureOpeningAppsIsMutable();
        this.openingApps_.remove(index);
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public List<IdentifierProto> getClosingAppsList() {
        return this.closingApps_;
    }

    public List<? extends IdentifierProtoOrBuilder> getClosingAppsOrBuilderList() {
        return this.closingApps_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getClosingAppsCount() {
        return this.closingApps_.size();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public IdentifierProto getClosingApps(int index) {
        return this.closingApps_.get(index);
    }

    public IdentifierProtoOrBuilder getClosingAppsOrBuilder(int index) {
        return this.closingApps_.get(index);
    }

    private void ensureClosingAppsIsMutable() {
        if (!this.closingApps_.isModifiable()) {
            this.closingApps_ = GeneratedMessageLite.mutableCopy(this.closingApps_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClosingApps(int index, IdentifierProto value) {
        if (value != null) {
            ensureClosingAppsIsMutable();
            this.closingApps_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClosingApps(int index, IdentifierProto.Builder builderForValue) {
        ensureClosingAppsIsMutable();
        this.closingApps_.set(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addClosingApps(IdentifierProto value) {
        if (value != null) {
            ensureClosingAppsIsMutable();
            this.closingApps_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addClosingApps(int index, IdentifierProto value) {
        if (value != null) {
            ensureClosingAppsIsMutable();
            this.closingApps_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addClosingApps(IdentifierProto.Builder builderForValue) {
        ensureClosingAppsIsMutable();
        this.closingApps_.add((IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addClosingApps(int index, IdentifierProto.Builder builderForValue) {
        ensureClosingAppsIsMutable();
        this.closingApps_.add(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllClosingApps(Iterable<? extends IdentifierProto> values) {
        ensureClosingAppsIsMutable();
        AbstractMessageLite.addAll(values, this.closingApps_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClosingApps() {
        this.closingApps_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeClosingApps(int index) {
        ensureClosingAppsIsMutable();
        this.closingApps_.remove(index);
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public List<IdentifierProto> getChangingAppsList() {
        return this.changingApps_;
    }

    public List<? extends IdentifierProtoOrBuilder> getChangingAppsOrBuilderList() {
        return this.changingApps_;
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public int getChangingAppsCount() {
        return this.changingApps_.size();
    }

    @Override // com.android.server.wm.DisplayContentProtoOrBuilder
    public IdentifierProto getChangingApps(int index) {
        return this.changingApps_.get(index);
    }

    public IdentifierProtoOrBuilder getChangingAppsOrBuilder(int index) {
        return this.changingApps_.get(index);
    }

    private void ensureChangingAppsIsMutable() {
        if (!this.changingApps_.isModifiable()) {
            this.changingApps_ = GeneratedMessageLite.mutableCopy(this.changingApps_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChangingApps(int index, IdentifierProto value) {
        if (value != null) {
            ensureChangingAppsIsMutable();
            this.changingApps_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChangingApps(int index, IdentifierProto.Builder builderForValue) {
        ensureChangingAppsIsMutable();
        this.changingApps_.set(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChangingApps(IdentifierProto value) {
        if (value != null) {
            ensureChangingAppsIsMutable();
            this.changingApps_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChangingApps(int index, IdentifierProto value) {
        if (value != null) {
            ensureChangingAppsIsMutable();
            this.changingApps_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChangingApps(IdentifierProto.Builder builderForValue) {
        ensureChangingAppsIsMutable();
        this.changingApps_.add((IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChangingApps(int index, IdentifierProto.Builder builderForValue) {
        ensureChangingAppsIsMutable();
        this.changingApps_.add(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllChangingApps(Iterable<? extends IdentifierProto> values) {
        ensureChangingAppsIsMutable();
        AbstractMessageLite.addAll(values, this.changingApps_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChangingApps() {
        this.changingApps_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeChangingApps(int index) {
        ensureChangingAppsIsMutable();
        this.changingApps_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.id_);
        }
        for (int i = 0; i < this.stacks_.size(); i++) {
            output.writeMessage(3, this.stacks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(4, getDockedStackDividerController());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getPinnedStackController());
        }
        for (int i2 = 0; i2 < this.aboveAppWindows_.size(); i2++) {
            output.writeMessage(6, this.aboveAppWindows_.get(i2));
        }
        for (int i3 = 0; i3 < this.belowAppWindows_.size(); i3++) {
            output.writeMessage(7, this.belowAppWindows_.get(i3));
        }
        for (int i4 = 0; i4 < this.imeWindows_.size(); i4++) {
            output.writeMessage(8, this.imeWindows_.get(i4));
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(9, this.dpi_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(10, getDisplayInfo());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(11, this.rotation_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(12, getScreenRotationAnimation());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(13, getDisplayFrames());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt32(14, this.surfaceSize_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeString(15, getFocusedApp());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(16, getAppTransition());
        }
        for (int i5 = 0; i5 < this.openingApps_.size(); i5++) {
            output.writeMessage(17, this.openingApps_.get(i5));
        }
        for (int i6 = 0; i6 < this.closingApps_.size(); i6++) {
            output.writeMessage(18, this.closingApps_.get(i6));
        }
        for (int i7 = 0; i7 < this.changingApps_.size(); i7++) {
            output.writeMessage(19, this.changingApps_.get(i7));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.id_);
        }
        for (int i = 0; i < this.stacks_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.stacks_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, getDockedStackDividerController());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getPinnedStackController());
        }
        for (int i2 = 0; i2 < this.aboveAppWindows_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.aboveAppWindows_.get(i2));
        }
        for (int i3 = 0; i3 < this.belowAppWindows_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.belowAppWindows_.get(i3));
        }
        for (int i4 = 0; i4 < this.imeWindows_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(8, this.imeWindows_.get(i4));
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(9, this.dpi_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(10, getDisplayInfo());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(11, this.rotation_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(12, getScreenRotationAnimation());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(13, getDisplayFrames());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeInt32Size(14, this.surfaceSize_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeStringSize(15, getFocusedApp());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(16, getAppTransition());
        }
        for (int i5 = 0; i5 < this.openingApps_.size(); i5++) {
            size2 += CodedOutputStream.computeMessageSize(17, this.openingApps_.get(i5));
        }
        for (int i6 = 0; i6 < this.closingApps_.size(); i6++) {
            size2 += CodedOutputStream.computeMessageSize(18, this.closingApps_.get(i6));
        }
        for (int i7 = 0; i7 < this.changingApps_.size(); i7++) {
            size2 += CodedOutputStream.computeMessageSize(19, this.changingApps_.get(i7));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DisplayContentProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayContentProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayContentProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayContentProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayContentProto parseFrom(InputStream input) throws IOException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayContentProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayContentProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DisplayContentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayContentProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayContentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayContentProto parseFrom(CodedInputStream input) throws IOException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayContentProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DisplayContentProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DisplayContentProto, Builder> implements DisplayContentProtoOrBuilder {
        private Builder() {
            super(DisplayContentProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasWindowContainer() {
            return ((DisplayContentProto) this.instance).hasWindowContainer();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public WindowContainerProto getWindowContainer() {
            return ((DisplayContentProto) this.instance).getWindowContainer();
        }

        public Builder setWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setWindowContainer((DisplayContentProto) value);
            return this;
        }

        public Builder setWindowContainer(WindowContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setWindowContainer((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder mergeWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).mergeWindowContainer(value);
            return this;
        }

        public Builder clearWindowContainer() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearWindowContainer();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasId() {
            return ((DisplayContentProto) this.instance).hasId();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getId() {
            return ((DisplayContentProto) this.instance).getId();
        }

        public Builder setId(int value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearId();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public List<StackProto> getStacksList() {
            return Collections.unmodifiableList(((DisplayContentProto) this.instance).getStacksList());
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getStacksCount() {
            return ((DisplayContentProto) this.instance).getStacksCount();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public StackProto getStacks(int index) {
            return ((DisplayContentProto) this.instance).getStacks(index);
        }

        public Builder setStacks(int index, StackProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setStacks((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder setStacks(int index, StackProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setStacks((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStacks(StackProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addStacks((DisplayContentProto) value);
            return this;
        }

        public Builder addStacks(int index, StackProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addStacks((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder addStacks(StackProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addStacks((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder addStacks(int index, StackProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addStacks((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStacks(Iterable<? extends StackProto> values) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAllStacks(values);
            return this;
        }

        public Builder clearStacks() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearStacks();
            return this;
        }

        public Builder removeStacks(int index) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).removeStacks(index);
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasDockedStackDividerController() {
            return ((DisplayContentProto) this.instance).hasDockedStackDividerController();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public DockedStackDividerControllerProto getDockedStackDividerController() {
            return ((DisplayContentProto) this.instance).getDockedStackDividerController();
        }

        public Builder setDockedStackDividerController(DockedStackDividerControllerProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setDockedStackDividerController((DisplayContentProto) value);
            return this;
        }

        public Builder setDockedStackDividerController(DockedStackDividerControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setDockedStackDividerController((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder mergeDockedStackDividerController(DockedStackDividerControllerProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).mergeDockedStackDividerController(value);
            return this;
        }

        public Builder clearDockedStackDividerController() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearDockedStackDividerController();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasPinnedStackController() {
            return ((DisplayContentProto) this.instance).hasPinnedStackController();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public PinnedStackControllerProto getPinnedStackController() {
            return ((DisplayContentProto) this.instance).getPinnedStackController();
        }

        public Builder setPinnedStackController(PinnedStackControllerProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setPinnedStackController((DisplayContentProto) value);
            return this;
        }

        public Builder setPinnedStackController(PinnedStackControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setPinnedStackController((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder mergePinnedStackController(PinnedStackControllerProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).mergePinnedStackController(value);
            return this;
        }

        public Builder clearPinnedStackController() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearPinnedStackController();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public List<WindowTokenProto> getAboveAppWindowsList() {
            return Collections.unmodifiableList(((DisplayContentProto) this.instance).getAboveAppWindowsList());
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getAboveAppWindowsCount() {
            return ((DisplayContentProto) this.instance).getAboveAppWindowsCount();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public WindowTokenProto getAboveAppWindows(int index) {
            return ((DisplayContentProto) this.instance).getAboveAppWindows(index);
        }

        public Builder setAboveAppWindows(int index, WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setAboveAppWindows((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder setAboveAppWindows(int index, WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setAboveAppWindows((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAboveAppWindows(WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAboveAppWindows((DisplayContentProto) value);
            return this;
        }

        public Builder addAboveAppWindows(int index, WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAboveAppWindows((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder addAboveAppWindows(WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAboveAppWindows((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder addAboveAppWindows(int index, WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAboveAppWindows((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAboveAppWindows(Iterable<? extends WindowTokenProto> values) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAllAboveAppWindows(values);
            return this;
        }

        public Builder clearAboveAppWindows() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearAboveAppWindows();
            return this;
        }

        public Builder removeAboveAppWindows(int index) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).removeAboveAppWindows(index);
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public List<WindowTokenProto> getBelowAppWindowsList() {
            return Collections.unmodifiableList(((DisplayContentProto) this.instance).getBelowAppWindowsList());
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getBelowAppWindowsCount() {
            return ((DisplayContentProto) this.instance).getBelowAppWindowsCount();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public WindowTokenProto getBelowAppWindows(int index) {
            return ((DisplayContentProto) this.instance).getBelowAppWindows(index);
        }

        public Builder setBelowAppWindows(int index, WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setBelowAppWindows((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder setBelowAppWindows(int index, WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setBelowAppWindows((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBelowAppWindows(WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addBelowAppWindows((DisplayContentProto) value);
            return this;
        }

        public Builder addBelowAppWindows(int index, WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addBelowAppWindows((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder addBelowAppWindows(WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addBelowAppWindows((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder addBelowAppWindows(int index, WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addBelowAppWindows((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBelowAppWindows(Iterable<? extends WindowTokenProto> values) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAllBelowAppWindows(values);
            return this;
        }

        public Builder clearBelowAppWindows() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearBelowAppWindows();
            return this;
        }

        public Builder removeBelowAppWindows(int index) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).removeBelowAppWindows(index);
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public List<WindowTokenProto> getImeWindowsList() {
            return Collections.unmodifiableList(((DisplayContentProto) this.instance).getImeWindowsList());
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getImeWindowsCount() {
            return ((DisplayContentProto) this.instance).getImeWindowsCount();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public WindowTokenProto getImeWindows(int index) {
            return ((DisplayContentProto) this.instance).getImeWindows(index);
        }

        public Builder setImeWindows(int index, WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setImeWindows((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder setImeWindows(int index, WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setImeWindows((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addImeWindows(WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addImeWindows((DisplayContentProto) value);
            return this;
        }

        public Builder addImeWindows(int index, WindowTokenProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addImeWindows((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder addImeWindows(WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addImeWindows((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder addImeWindows(int index, WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addImeWindows((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllImeWindows(Iterable<? extends WindowTokenProto> values) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAllImeWindows(values);
            return this;
        }

        public Builder clearImeWindows() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearImeWindows();
            return this;
        }

        public Builder removeImeWindows(int index) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).removeImeWindows(index);
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasDpi() {
            return ((DisplayContentProto) this.instance).hasDpi();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getDpi() {
            return ((DisplayContentProto) this.instance).getDpi();
        }

        public Builder setDpi(int value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setDpi(value);
            return this;
        }

        public Builder clearDpi() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearDpi();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasDisplayInfo() {
            return ((DisplayContentProto) this.instance).hasDisplayInfo();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public DisplayInfoProto getDisplayInfo() {
            return ((DisplayContentProto) this.instance).getDisplayInfo();
        }

        public Builder setDisplayInfo(DisplayInfoProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setDisplayInfo((DisplayContentProto) value);
            return this;
        }

        public Builder setDisplayInfo(DisplayInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setDisplayInfo((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder mergeDisplayInfo(DisplayInfoProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).mergeDisplayInfo(value);
            return this;
        }

        public Builder clearDisplayInfo() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearDisplayInfo();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasRotation() {
            return ((DisplayContentProto) this.instance).hasRotation();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getRotation() {
            return ((DisplayContentProto) this.instance).getRotation();
        }

        public Builder setRotation(int value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setRotation(value);
            return this;
        }

        public Builder clearRotation() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearRotation();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasScreenRotationAnimation() {
            return ((DisplayContentProto) this.instance).hasScreenRotationAnimation();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public ScreenRotationAnimationProto getScreenRotationAnimation() {
            return ((DisplayContentProto) this.instance).getScreenRotationAnimation();
        }

        public Builder setScreenRotationAnimation(ScreenRotationAnimationProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setScreenRotationAnimation((DisplayContentProto) value);
            return this;
        }

        public Builder setScreenRotationAnimation(ScreenRotationAnimationProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setScreenRotationAnimation((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder mergeScreenRotationAnimation(ScreenRotationAnimationProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).mergeScreenRotationAnimation(value);
            return this;
        }

        public Builder clearScreenRotationAnimation() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearScreenRotationAnimation();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasDisplayFrames() {
            return ((DisplayContentProto) this.instance).hasDisplayFrames();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public DisplayFramesProto getDisplayFrames() {
            return ((DisplayContentProto) this.instance).getDisplayFrames();
        }

        public Builder setDisplayFrames(DisplayFramesProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setDisplayFrames((DisplayContentProto) value);
            return this;
        }

        public Builder setDisplayFrames(DisplayFramesProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setDisplayFrames((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder mergeDisplayFrames(DisplayFramesProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).mergeDisplayFrames(value);
            return this;
        }

        public Builder clearDisplayFrames() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearDisplayFrames();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        @Deprecated
        public boolean hasSurfaceSize() {
            return ((DisplayContentProto) this.instance).hasSurfaceSize();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        @Deprecated
        public int getSurfaceSize() {
            return ((DisplayContentProto) this.instance).getSurfaceSize();
        }

        @Deprecated
        public Builder setSurfaceSize(int value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setSurfaceSize(value);
            return this;
        }

        @Deprecated
        public Builder clearSurfaceSize() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearSurfaceSize();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasFocusedApp() {
            return ((DisplayContentProto) this.instance).hasFocusedApp();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public String getFocusedApp() {
            return ((DisplayContentProto) this.instance).getFocusedApp();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public ByteString getFocusedAppBytes() {
            return ((DisplayContentProto) this.instance).getFocusedAppBytes();
        }

        public Builder setFocusedApp(String value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setFocusedApp(value);
            return this;
        }

        public Builder clearFocusedApp() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearFocusedApp();
            return this;
        }

        public Builder setFocusedAppBytes(ByteString value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setFocusedAppBytes(value);
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public boolean hasAppTransition() {
            return ((DisplayContentProto) this.instance).hasAppTransition();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public AppTransitionProto getAppTransition() {
            return ((DisplayContentProto) this.instance).getAppTransition();
        }

        public Builder setAppTransition(AppTransitionProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setAppTransition((DisplayContentProto) value);
            return this;
        }

        public Builder setAppTransition(AppTransitionProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setAppTransition((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder mergeAppTransition(AppTransitionProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).mergeAppTransition(value);
            return this;
        }

        public Builder clearAppTransition() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearAppTransition();
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public List<IdentifierProto> getOpeningAppsList() {
            return Collections.unmodifiableList(((DisplayContentProto) this.instance).getOpeningAppsList());
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getOpeningAppsCount() {
            return ((DisplayContentProto) this.instance).getOpeningAppsCount();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public IdentifierProto getOpeningApps(int index) {
            return ((DisplayContentProto) this.instance).getOpeningApps(index);
        }

        public Builder setOpeningApps(int index, IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setOpeningApps((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder setOpeningApps(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setOpeningApps((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addOpeningApps(IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addOpeningApps((DisplayContentProto) value);
            return this;
        }

        public Builder addOpeningApps(int index, IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addOpeningApps((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder addOpeningApps(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addOpeningApps((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder addOpeningApps(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addOpeningApps((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllOpeningApps(Iterable<? extends IdentifierProto> values) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAllOpeningApps(values);
            return this;
        }

        public Builder clearOpeningApps() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearOpeningApps();
            return this;
        }

        public Builder removeOpeningApps(int index) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).removeOpeningApps(index);
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public List<IdentifierProto> getClosingAppsList() {
            return Collections.unmodifiableList(((DisplayContentProto) this.instance).getClosingAppsList());
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getClosingAppsCount() {
            return ((DisplayContentProto) this.instance).getClosingAppsCount();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public IdentifierProto getClosingApps(int index) {
            return ((DisplayContentProto) this.instance).getClosingApps(index);
        }

        public Builder setClosingApps(int index, IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setClosingApps((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder setClosingApps(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setClosingApps((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addClosingApps(IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addClosingApps((DisplayContentProto) value);
            return this;
        }

        public Builder addClosingApps(int index, IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addClosingApps((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder addClosingApps(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addClosingApps((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder addClosingApps(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addClosingApps((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllClosingApps(Iterable<? extends IdentifierProto> values) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAllClosingApps(values);
            return this;
        }

        public Builder clearClosingApps() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearClosingApps();
            return this;
        }

        public Builder removeClosingApps(int index) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).removeClosingApps(index);
            return this;
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public List<IdentifierProto> getChangingAppsList() {
            return Collections.unmodifiableList(((DisplayContentProto) this.instance).getChangingAppsList());
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public int getChangingAppsCount() {
            return ((DisplayContentProto) this.instance).getChangingAppsCount();
        }

        @Override // com.android.server.wm.DisplayContentProtoOrBuilder
        public IdentifierProto getChangingApps(int index) {
            return ((DisplayContentProto) this.instance).getChangingApps(index);
        }

        public Builder setChangingApps(int index, IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setChangingApps((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder setChangingApps(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).setChangingApps((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addChangingApps(IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addChangingApps((DisplayContentProto) value);
            return this;
        }

        public Builder addChangingApps(int index, IdentifierProto value) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addChangingApps((DisplayContentProto) index, (int) value);
            return this;
        }

        public Builder addChangingApps(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addChangingApps((DisplayContentProto) builderForValue);
            return this;
        }

        public Builder addChangingApps(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addChangingApps((DisplayContentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllChangingApps(Iterable<? extends IdentifierProto> values) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).addAllChangingApps(values);
            return this;
        }

        public Builder clearChangingApps() {
            copyOnWrite();
            ((DisplayContentProto) this.instance).clearChangingApps();
            return this;
        }

        public Builder removeChangingApps(int index) {
            copyOnWrite();
            ((DisplayContentProto) this.instance).removeChangingApps(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DisplayContentProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.stacks_.makeImmutable();
                this.aboveAppWindows_.makeImmutable();
                this.belowAppWindows_.makeImmutable();
                this.imeWindows_.makeImmutable();
                this.openingApps_.makeImmutable();
                this.closingApps_.makeImmutable();
                this.changingApps_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DisplayContentProto other = (DisplayContentProto) arg1;
                this.windowContainer_ = (WindowContainerProto) visitor.visitMessage(this.windowContainer_, other.windowContainer_);
                this.id_ = visitor.visitInt(hasId(), this.id_, other.hasId(), other.id_);
                this.stacks_ = visitor.visitList(this.stacks_, other.stacks_);
                this.dockedStackDividerController_ = (DockedStackDividerControllerProto) visitor.visitMessage(this.dockedStackDividerController_, other.dockedStackDividerController_);
                this.pinnedStackController_ = (PinnedStackControllerProto) visitor.visitMessage(this.pinnedStackController_, other.pinnedStackController_);
                this.aboveAppWindows_ = visitor.visitList(this.aboveAppWindows_, other.aboveAppWindows_);
                this.belowAppWindows_ = visitor.visitList(this.belowAppWindows_, other.belowAppWindows_);
                this.imeWindows_ = visitor.visitList(this.imeWindows_, other.imeWindows_);
                this.dpi_ = visitor.visitInt(hasDpi(), this.dpi_, other.hasDpi(), other.dpi_);
                this.displayInfo_ = (DisplayInfoProto) visitor.visitMessage(this.displayInfo_, other.displayInfo_);
                this.rotation_ = visitor.visitInt(hasRotation(), this.rotation_, other.hasRotation(), other.rotation_);
                this.screenRotationAnimation_ = (ScreenRotationAnimationProto) visitor.visitMessage(this.screenRotationAnimation_, other.screenRotationAnimation_);
                this.displayFrames_ = (DisplayFramesProto) visitor.visitMessage(this.displayFrames_, other.displayFrames_);
                this.surfaceSize_ = visitor.visitInt(hasSurfaceSize(), this.surfaceSize_, other.hasSurfaceSize(), other.surfaceSize_);
                this.focusedApp_ = visitor.visitString(hasFocusedApp(), this.focusedApp_, other.hasFocusedApp(), other.focusedApp_);
                this.appTransition_ = (AppTransitionProto) visitor.visitMessage(this.appTransition_, other.appTransition_);
                this.openingApps_ = visitor.visitList(this.openingApps_, other.openingApps_);
                this.closingApps_ = visitor.visitList(this.closingApps_, other.closingApps_);
                this.changingApps_ = visitor.visitList(this.changingApps_, other.changingApps_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                WindowContainerProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (WindowContainerProto.Builder) this.windowContainer_.toBuilder();
                                }
                                this.windowContainer_ = (WindowContainerProto) input.readMessage(WindowContainerProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.windowContainer_);
                                    this.windowContainer_ = (WindowContainerProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.id_ = input.readInt32();
                                break;
                            case 26:
                                if (!this.stacks_.isModifiable()) {
                                    this.stacks_ = GeneratedMessageLite.mutableCopy(this.stacks_);
                                }
                                this.stacks_.add((StackProto) input.readMessage(StackProto.parser(), extensionRegistry));
                                break;
                            case 34:
                                DockedStackDividerControllerProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder2 = (DockedStackDividerControllerProto.Builder) this.dockedStackDividerController_.toBuilder();
                                }
                                this.dockedStackDividerController_ = (DockedStackDividerControllerProto) input.readMessage(DockedStackDividerControllerProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.dockedStackDividerController_);
                                    this.dockedStackDividerController_ = (DockedStackDividerControllerProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 42:
                                PinnedStackControllerProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder3 = (PinnedStackControllerProto.Builder) this.pinnedStackController_.toBuilder();
                                }
                                this.pinnedStackController_ = (PinnedStackControllerProto) input.readMessage(PinnedStackControllerProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.pinnedStackController_);
                                    this.pinnedStackController_ = (PinnedStackControllerProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 50:
                                if (!this.aboveAppWindows_.isModifiable()) {
                                    this.aboveAppWindows_ = GeneratedMessageLite.mutableCopy(this.aboveAppWindows_);
                                }
                                this.aboveAppWindows_.add((WindowTokenProto) input.readMessage(WindowTokenProto.parser(), extensionRegistry));
                                break;
                            case 58:
                                if (!this.belowAppWindows_.isModifiable()) {
                                    this.belowAppWindows_ = GeneratedMessageLite.mutableCopy(this.belowAppWindows_);
                                }
                                this.belowAppWindows_.add((WindowTokenProto) input.readMessage(WindowTokenProto.parser(), extensionRegistry));
                                break;
                            case 66:
                                if (!this.imeWindows_.isModifiable()) {
                                    this.imeWindows_ = GeneratedMessageLite.mutableCopy(this.imeWindows_);
                                }
                                this.imeWindows_.add((WindowTokenProto) input.readMessage(WindowTokenProto.parser(), extensionRegistry));
                                break;
                            case 72:
                                this.bitField0_ |= 16;
                                this.dpi_ = input.readInt32();
                                break;
                            case 82:
                                DisplayInfoProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder4 = (DisplayInfoProto.Builder) this.displayInfo_.toBuilder();
                                }
                                this.displayInfo_ = (DisplayInfoProto) input.readMessage(DisplayInfoProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.displayInfo_);
                                    this.displayInfo_ = (DisplayInfoProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 88:
                                this.bitField0_ |= 64;
                                this.rotation_ = input.readInt32();
                                break;
                            case 98:
                                ScreenRotationAnimationProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder5 = (ScreenRotationAnimationProto.Builder) this.screenRotationAnimation_.toBuilder();
                                }
                                this.screenRotationAnimation_ = (ScreenRotationAnimationProto) input.readMessage(ScreenRotationAnimationProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.screenRotationAnimation_);
                                    this.screenRotationAnimation_ = (ScreenRotationAnimationProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 106:
                                DisplayFramesProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder6 = (DisplayFramesProto.Builder) this.displayFrames_.toBuilder();
                                }
                                this.displayFrames_ = (DisplayFramesProto) input.readMessage(DisplayFramesProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.displayFrames_);
                                    this.displayFrames_ = (DisplayFramesProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 112:
                                this.bitField0_ |= 512;
                                this.surfaceSize_ = input.readInt32();
                                break;
                            case 122:
                                String s = input.readString();
                                this.bitField0_ |= 1024;
                                this.focusedApp_ = s;
                                break;
                            case 130:
                                AppTransitionProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder7 = (AppTransitionProto.Builder) this.appTransition_.toBuilder();
                                }
                                this.appTransition_ = (AppTransitionProto) input.readMessage(AppTransitionProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.appTransition_);
                                    this.appTransition_ = (AppTransitionProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 138:
                                if (!this.openingApps_.isModifiable()) {
                                    this.openingApps_ = GeneratedMessageLite.mutableCopy(this.openingApps_);
                                }
                                this.openingApps_.add((IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry));
                                break;
                            case 146:
                                if (!this.closingApps_.isModifiable()) {
                                    this.closingApps_ = GeneratedMessageLite.mutableCopy(this.closingApps_);
                                }
                                this.closingApps_.add((IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry));
                                break;
                            case 154:
                                if (!this.changingApps_.isModifiable()) {
                                    this.changingApps_ = GeneratedMessageLite.mutableCopy(this.changingApps_);
                                }
                                this.changingApps_.add((IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry));
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (DisplayContentProto.class) {
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

    public static DisplayContentProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DisplayContentProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
