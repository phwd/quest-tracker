package com.android.server.wm;

import android.graphics.RectProto;
import android.graphics.RectProtoOrBuilder;
import com.android.os.AtomsProto;
import com.android.server.wm.AppWindowThumbnailProto;
import com.android.server.wm.IdentifierProto;
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

public final class AppWindowTokenProto extends GeneratedMessageLite<AppWindowTokenProto, Builder> implements AppWindowTokenProtoOrBuilder {
    public static final int ALL_DRAWN_FIELD_NUMBER = 16;
    public static final int APP_STOPPED_FIELD_NUMBER = 8;
    public static final int CLIENT_HIDDEN_FIELD_NUMBER = 10;
    private static final AppWindowTokenProto DEFAULT_INSTANCE = new AppWindowTokenProto();
    public static final int DEFER_HIDING_CLIENT_FIELD_NUMBER = 11;
    public static final int FILLS_PARENT_FIELD_NUMBER = 7;
    public static final int FROZEN_BOUNDS_FIELD_NUMBER = 23;
    public static final int HIDDEN_REQUESTED_FIELD_NUMBER = 9;
    public static final int HIDDEN_SET_FROM_TRANSFERRED_STARTING_WINDOW_FIELD_NUMBER = 22;
    public static final int IS_REALLY_ANIMATING_FIELD_NUMBER = 5;
    public static final int IS_WAITING_FOR_TRANSITION_START_FIELD_NUMBER = 4;
    public static final int LAST_ALL_DRAWN_FIELD_NUMBER = 17;
    public static final int LAST_SURFACE_SHOWING_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int NUM_DRAWN_WINDOWS_FIELD_NUMBER = 15;
    public static final int NUM_INTERESTING_WINDOWS_FIELD_NUMBER = 14;
    private static volatile Parser<AppWindowTokenProto> PARSER = null;
    public static final int REMOVED_FIELD_NUMBER = 18;
    public static final int REPORTED_DRAWN_FIELD_NUMBER = 12;
    public static final int REPORTED_VISIBLE_FIELD_NUMBER = 13;
    public static final int STARTING_DISPLAYED_FIELD_NUMBER = 20;
    public static final int STARTING_MOVED_FIELD_NUMBER = 21;
    public static final int STARTING_WINDOW_FIELD_NUMBER = 19;
    public static final int THUMBNAIL_FIELD_NUMBER = 6;
    public static final int WINDOW_TOKEN_FIELD_NUMBER = 2;
    private boolean allDrawn_ = false;
    private boolean appStopped_ = false;
    private int bitField0_;
    private boolean clientHidden_ = false;
    private boolean deferHidingClient_ = false;
    private boolean fillsParent_ = false;
    private Internal.ProtobufList<RectProto> frozenBounds_ = emptyProtobufList();
    private boolean hiddenRequested_ = false;
    private boolean hiddenSetFromTransferredStartingWindow_ = false;
    private boolean isReallyAnimating_ = false;
    private boolean isWaitingForTransitionStart_ = false;
    private boolean lastAllDrawn_ = false;
    private boolean lastSurfaceShowing_ = false;
    private String name_ = "";
    private int numDrawnWindows_ = 0;
    private int numInterestingWindows_ = 0;
    private boolean removed_ = false;
    private boolean reportedDrawn_ = false;
    private boolean reportedVisible_ = false;
    private boolean startingDisplayed_ = false;
    private boolean startingMoved_ = false;
    private IdentifierProto startingWindow_;
    private AppWindowThumbnailProto thumbnail_;
    private WindowTokenProto windowToken_;

    private AppWindowTokenProto() {
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -2;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasWindowToken() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public WindowTokenProto getWindowToken() {
        WindowTokenProto windowTokenProto = this.windowToken_;
        return windowTokenProto == null ? WindowTokenProto.getDefaultInstance() : windowTokenProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowToken(WindowTokenProto value) {
        if (value != null) {
            this.windowToken_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowToken(WindowTokenProto.Builder builderForValue) {
        this.windowToken_ = (WindowTokenProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowToken(WindowTokenProto value) {
        WindowTokenProto windowTokenProto = this.windowToken_;
        if (windowTokenProto == null || windowTokenProto == WindowTokenProto.getDefaultInstance()) {
            this.windowToken_ = value;
        } else {
            this.windowToken_ = (WindowTokenProto) ((WindowTokenProto.Builder) WindowTokenProto.newBuilder(this.windowToken_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowToken() {
        this.windowToken_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasLastSurfaceShowing() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getLastSurfaceShowing() {
        return this.lastSurfaceShowing_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastSurfaceShowing(boolean value) {
        this.bitField0_ |= 4;
        this.lastSurfaceShowing_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastSurfaceShowing() {
        this.bitField0_ &= -5;
        this.lastSurfaceShowing_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasIsWaitingForTransitionStart() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getIsWaitingForTransitionStart() {
        return this.isWaitingForTransitionStart_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsWaitingForTransitionStart(boolean value) {
        this.bitField0_ |= 8;
        this.isWaitingForTransitionStart_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsWaitingForTransitionStart() {
        this.bitField0_ &= -9;
        this.isWaitingForTransitionStart_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasIsReallyAnimating() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getIsReallyAnimating() {
        return this.isReallyAnimating_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsReallyAnimating(boolean value) {
        this.bitField0_ |= 16;
        this.isReallyAnimating_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsReallyAnimating() {
        this.bitField0_ &= -17;
        this.isReallyAnimating_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasThumbnail() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public AppWindowThumbnailProto getThumbnail() {
        AppWindowThumbnailProto appWindowThumbnailProto = this.thumbnail_;
        return appWindowThumbnailProto == null ? AppWindowThumbnailProto.getDefaultInstance() : appWindowThumbnailProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setThumbnail(AppWindowThumbnailProto value) {
        if (value != null) {
            this.thumbnail_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setThumbnail(AppWindowThumbnailProto.Builder builderForValue) {
        this.thumbnail_ = (AppWindowThumbnailProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeThumbnail(AppWindowThumbnailProto value) {
        AppWindowThumbnailProto appWindowThumbnailProto = this.thumbnail_;
        if (appWindowThumbnailProto == null || appWindowThumbnailProto == AppWindowThumbnailProto.getDefaultInstance()) {
            this.thumbnail_ = value;
        } else {
            this.thumbnail_ = (AppWindowThumbnailProto) ((AppWindowThumbnailProto.Builder) AppWindowThumbnailProto.newBuilder(this.thumbnail_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearThumbnail() {
        this.thumbnail_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasFillsParent() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getFillsParent() {
        return this.fillsParent_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFillsParent(boolean value) {
        this.bitField0_ |= 64;
        this.fillsParent_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFillsParent() {
        this.bitField0_ &= -65;
        this.fillsParent_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasAppStopped() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getAppStopped() {
        return this.appStopped_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppStopped(boolean value) {
        this.bitField0_ |= 128;
        this.appStopped_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppStopped() {
        this.bitField0_ &= -129;
        this.appStopped_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasHiddenRequested() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getHiddenRequested() {
        return this.hiddenRequested_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHiddenRequested(boolean value) {
        this.bitField0_ |= 256;
        this.hiddenRequested_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHiddenRequested() {
        this.bitField0_ &= -257;
        this.hiddenRequested_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasClientHidden() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getClientHidden() {
        return this.clientHidden_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClientHidden(boolean value) {
        this.bitField0_ |= 512;
        this.clientHidden_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClientHidden() {
        this.bitField0_ &= -513;
        this.clientHidden_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasDeferHidingClient() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getDeferHidingClient() {
        return this.deferHidingClient_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeferHidingClient(boolean value) {
        this.bitField0_ |= 1024;
        this.deferHidingClient_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeferHidingClient() {
        this.bitField0_ &= -1025;
        this.deferHidingClient_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasReportedDrawn() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getReportedDrawn() {
        return this.reportedDrawn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportedDrawn(boolean value) {
        this.bitField0_ |= 2048;
        this.reportedDrawn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportedDrawn() {
        this.bitField0_ &= -2049;
        this.reportedDrawn_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasReportedVisible() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getReportedVisible() {
        return this.reportedVisible_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportedVisible(boolean value) {
        this.bitField0_ |= 4096;
        this.reportedVisible_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportedVisible() {
        this.bitField0_ &= -4097;
        this.reportedVisible_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasNumInterestingWindows() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public int getNumInterestingWindows() {
        return this.numInterestingWindows_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumInterestingWindows(int value) {
        this.bitField0_ |= 8192;
        this.numInterestingWindows_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumInterestingWindows() {
        this.bitField0_ &= -8193;
        this.numInterestingWindows_ = 0;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasNumDrawnWindows() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public int getNumDrawnWindows() {
        return this.numDrawnWindows_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumDrawnWindows(int value) {
        this.bitField0_ |= 16384;
        this.numDrawnWindows_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumDrawnWindows() {
        this.bitField0_ &= -16385;
        this.numDrawnWindows_ = 0;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasAllDrawn() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getAllDrawn() {
        return this.allDrawn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllDrawn(boolean value) {
        this.bitField0_ |= 32768;
        this.allDrawn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllDrawn() {
        this.bitField0_ &= -32769;
        this.allDrawn_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasLastAllDrawn() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getLastAllDrawn() {
        return this.lastAllDrawn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastAllDrawn(boolean value) {
        this.bitField0_ |= 65536;
        this.lastAllDrawn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastAllDrawn() {
        this.bitField0_ &= -65537;
        this.lastAllDrawn_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasRemoved() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getRemoved() {
        return this.removed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRemoved(boolean value) {
        this.bitField0_ |= 131072;
        this.removed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRemoved() {
        this.bitField0_ &= -131073;
        this.removed_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasStartingWindow() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public IdentifierProto getStartingWindow() {
        IdentifierProto identifierProto = this.startingWindow_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartingWindow(IdentifierProto value) {
        if (value != null) {
            this.startingWindow_ = value;
            this.bitField0_ |= 262144;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartingWindow(IdentifierProto.Builder builderForValue) {
        this.startingWindow_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStartingWindow(IdentifierProto value) {
        IdentifierProto identifierProto = this.startingWindow_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.startingWindow_ = value;
        } else {
            this.startingWindow_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.startingWindow_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartingWindow() {
        this.startingWindow_ = null;
        this.bitField0_ &= -262145;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasStartingDisplayed() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getStartingDisplayed() {
        return this.startingDisplayed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartingDisplayed(boolean value) {
        this.bitField0_ |= 524288;
        this.startingDisplayed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartingDisplayed() {
        this.bitField0_ &= -524289;
        this.startingDisplayed_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasStartingMoved() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getStartingMoved() {
        return this.startingMoved_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartingMoved(boolean value) {
        this.bitField0_ |= 1048576;
        this.startingMoved_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartingMoved() {
        this.bitField0_ &= -1048577;
        this.startingMoved_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean hasHiddenSetFromTransferredStartingWindow() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public boolean getHiddenSetFromTransferredStartingWindow() {
        return this.hiddenSetFromTransferredStartingWindow_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHiddenSetFromTransferredStartingWindow(boolean value) {
        this.bitField0_ |= 2097152;
        this.hiddenSetFromTransferredStartingWindow_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHiddenSetFromTransferredStartingWindow() {
        this.bitField0_ &= -2097153;
        this.hiddenSetFromTransferredStartingWindow_ = false;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public List<RectProto> getFrozenBoundsList() {
        return this.frozenBounds_;
    }

    public List<? extends RectProtoOrBuilder> getFrozenBoundsOrBuilderList() {
        return this.frozenBounds_;
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public int getFrozenBoundsCount() {
        return this.frozenBounds_.size();
    }

    @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
    public RectProto getFrozenBounds(int index) {
        return this.frozenBounds_.get(index);
    }

    public RectProtoOrBuilder getFrozenBoundsOrBuilder(int index) {
        return this.frozenBounds_.get(index);
    }

    private void ensureFrozenBoundsIsMutable() {
        if (!this.frozenBounds_.isModifiable()) {
            this.frozenBounds_ = GeneratedMessageLite.mutableCopy(this.frozenBounds_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrozenBounds(int index, RectProto value) {
        if (value != null) {
            ensureFrozenBoundsIsMutable();
            this.frozenBounds_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrozenBounds(int index, RectProto.Builder builderForValue) {
        ensureFrozenBoundsIsMutable();
        this.frozenBounds_.set(index, (RectProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFrozenBounds(RectProto value) {
        if (value != null) {
            ensureFrozenBoundsIsMutable();
            this.frozenBounds_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFrozenBounds(int index, RectProto value) {
        if (value != null) {
            ensureFrozenBoundsIsMutable();
            this.frozenBounds_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFrozenBounds(RectProto.Builder builderForValue) {
        ensureFrozenBoundsIsMutable();
        this.frozenBounds_.add((RectProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFrozenBounds(int index, RectProto.Builder builderForValue) {
        ensureFrozenBoundsIsMutable();
        this.frozenBounds_.add(index, (RectProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllFrozenBounds(Iterable<? extends RectProto> values) {
        ensureFrozenBoundsIsMutable();
        AbstractMessageLite.addAll(values, this.frozenBounds_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFrozenBounds() {
        this.frozenBounds_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeFrozenBounds(int index) {
        ensureFrozenBoundsIsMutable();
        this.frozenBounds_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getWindowToken());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.lastSurfaceShowing_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isWaitingForTransitionStart_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.isReallyAnimating_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getThumbnail());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.fillsParent_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(8, this.appStopped_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(9, this.hiddenRequested_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeBool(10, this.clientHidden_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeBool(11, this.deferHidingClient_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(12, this.reportedDrawn_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeBool(13, this.reportedVisible_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeInt32(14, this.numInterestingWindows_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeInt32(15, this.numDrawnWindows_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeBool(16, this.allDrawn_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeBool(17, this.lastAllDrawn_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeBool(18, this.removed_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeMessage(19, getStartingWindow());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeBool(20, this.startingDisplayed_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeBool(21, this.startingMoved_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeBool(22, this.hiddenSetFromTransferredStartingWindow_);
        }
        for (int i = 0; i < this.frozenBounds_.size(); i++) {
            output.writeMessage(23, this.frozenBounds_.get(i));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getWindowToken());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.lastSurfaceShowing_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isWaitingForTransitionStart_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.isReallyAnimating_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getThumbnail());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.fillsParent_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(8, this.appStopped_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeBoolSize(9, this.hiddenRequested_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeBoolSize(10, this.clientHidden_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeBoolSize(11, this.deferHidingClient_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeBoolSize(12, this.reportedDrawn_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeBoolSize(13, this.reportedVisible_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeInt32Size(14, this.numInterestingWindows_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeInt32Size(15, this.numDrawnWindows_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeBoolSize(16, this.allDrawn_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeBoolSize(17, this.lastAllDrawn_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeBoolSize(18, this.removed_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeMessageSize(19, getStartingWindow());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeBoolSize(20, this.startingDisplayed_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeBoolSize(21, this.startingMoved_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeBoolSize(22, this.hiddenSetFromTransferredStartingWindow_);
        }
        for (int i = 0; i < this.frozenBounds_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(23, this.frozenBounds_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AppWindowTokenProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppWindowTokenProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppWindowTokenProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppWindowTokenProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppWindowTokenProto parseFrom(InputStream input) throws IOException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWindowTokenProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppWindowTokenProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AppWindowTokenProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWindowTokenProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWindowTokenProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppWindowTokenProto parseFrom(CodedInputStream input) throws IOException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWindowTokenProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AppWindowTokenProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AppWindowTokenProto, Builder> implements AppWindowTokenProtoOrBuilder {
        private Builder() {
            super(AppWindowTokenProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasName() {
            return ((AppWindowTokenProto) this.instance).hasName();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public String getName() {
            return ((AppWindowTokenProto) this.instance).getName();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public ByteString getNameBytes() {
            return ((AppWindowTokenProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasWindowToken() {
            return ((AppWindowTokenProto) this.instance).hasWindowToken();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public WindowTokenProto getWindowToken() {
            return ((AppWindowTokenProto) this.instance).getWindowToken();
        }

        public Builder setWindowToken(WindowTokenProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setWindowToken((AppWindowTokenProto) value);
            return this;
        }

        public Builder setWindowToken(WindowTokenProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setWindowToken((AppWindowTokenProto) builderForValue);
            return this;
        }

        public Builder mergeWindowToken(WindowTokenProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).mergeWindowToken(value);
            return this;
        }

        public Builder clearWindowToken() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearWindowToken();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasLastSurfaceShowing() {
            return ((AppWindowTokenProto) this.instance).hasLastSurfaceShowing();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getLastSurfaceShowing() {
            return ((AppWindowTokenProto) this.instance).getLastSurfaceShowing();
        }

        public Builder setLastSurfaceShowing(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setLastSurfaceShowing(value);
            return this;
        }

        public Builder clearLastSurfaceShowing() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearLastSurfaceShowing();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasIsWaitingForTransitionStart() {
            return ((AppWindowTokenProto) this.instance).hasIsWaitingForTransitionStart();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getIsWaitingForTransitionStart() {
            return ((AppWindowTokenProto) this.instance).getIsWaitingForTransitionStart();
        }

        public Builder setIsWaitingForTransitionStart(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setIsWaitingForTransitionStart(value);
            return this;
        }

        public Builder clearIsWaitingForTransitionStart() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearIsWaitingForTransitionStart();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasIsReallyAnimating() {
            return ((AppWindowTokenProto) this.instance).hasIsReallyAnimating();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getIsReallyAnimating() {
            return ((AppWindowTokenProto) this.instance).getIsReallyAnimating();
        }

        public Builder setIsReallyAnimating(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setIsReallyAnimating(value);
            return this;
        }

        public Builder clearIsReallyAnimating() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearIsReallyAnimating();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasThumbnail() {
            return ((AppWindowTokenProto) this.instance).hasThumbnail();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public AppWindowThumbnailProto getThumbnail() {
            return ((AppWindowTokenProto) this.instance).getThumbnail();
        }

        public Builder setThumbnail(AppWindowThumbnailProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setThumbnail((AppWindowTokenProto) value);
            return this;
        }

        public Builder setThumbnail(AppWindowThumbnailProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setThumbnail((AppWindowTokenProto) builderForValue);
            return this;
        }

        public Builder mergeThumbnail(AppWindowThumbnailProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).mergeThumbnail(value);
            return this;
        }

        public Builder clearThumbnail() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearThumbnail();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasFillsParent() {
            return ((AppWindowTokenProto) this.instance).hasFillsParent();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getFillsParent() {
            return ((AppWindowTokenProto) this.instance).getFillsParent();
        }

        public Builder setFillsParent(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setFillsParent(value);
            return this;
        }

        public Builder clearFillsParent() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearFillsParent();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasAppStopped() {
            return ((AppWindowTokenProto) this.instance).hasAppStopped();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getAppStopped() {
            return ((AppWindowTokenProto) this.instance).getAppStopped();
        }

        public Builder setAppStopped(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setAppStopped(value);
            return this;
        }

        public Builder clearAppStopped() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearAppStopped();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasHiddenRequested() {
            return ((AppWindowTokenProto) this.instance).hasHiddenRequested();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getHiddenRequested() {
            return ((AppWindowTokenProto) this.instance).getHiddenRequested();
        }

        public Builder setHiddenRequested(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setHiddenRequested(value);
            return this;
        }

        public Builder clearHiddenRequested() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearHiddenRequested();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasClientHidden() {
            return ((AppWindowTokenProto) this.instance).hasClientHidden();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getClientHidden() {
            return ((AppWindowTokenProto) this.instance).getClientHidden();
        }

        public Builder setClientHidden(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setClientHidden(value);
            return this;
        }

        public Builder clearClientHidden() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearClientHidden();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasDeferHidingClient() {
            return ((AppWindowTokenProto) this.instance).hasDeferHidingClient();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getDeferHidingClient() {
            return ((AppWindowTokenProto) this.instance).getDeferHidingClient();
        }

        public Builder setDeferHidingClient(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setDeferHidingClient(value);
            return this;
        }

        public Builder clearDeferHidingClient() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearDeferHidingClient();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasReportedDrawn() {
            return ((AppWindowTokenProto) this.instance).hasReportedDrawn();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getReportedDrawn() {
            return ((AppWindowTokenProto) this.instance).getReportedDrawn();
        }

        public Builder setReportedDrawn(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setReportedDrawn(value);
            return this;
        }

        public Builder clearReportedDrawn() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearReportedDrawn();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasReportedVisible() {
            return ((AppWindowTokenProto) this.instance).hasReportedVisible();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getReportedVisible() {
            return ((AppWindowTokenProto) this.instance).getReportedVisible();
        }

        public Builder setReportedVisible(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setReportedVisible(value);
            return this;
        }

        public Builder clearReportedVisible() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearReportedVisible();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasNumInterestingWindows() {
            return ((AppWindowTokenProto) this.instance).hasNumInterestingWindows();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public int getNumInterestingWindows() {
            return ((AppWindowTokenProto) this.instance).getNumInterestingWindows();
        }

        public Builder setNumInterestingWindows(int value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setNumInterestingWindows(value);
            return this;
        }

        public Builder clearNumInterestingWindows() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearNumInterestingWindows();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasNumDrawnWindows() {
            return ((AppWindowTokenProto) this.instance).hasNumDrawnWindows();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public int getNumDrawnWindows() {
            return ((AppWindowTokenProto) this.instance).getNumDrawnWindows();
        }

        public Builder setNumDrawnWindows(int value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setNumDrawnWindows(value);
            return this;
        }

        public Builder clearNumDrawnWindows() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearNumDrawnWindows();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasAllDrawn() {
            return ((AppWindowTokenProto) this.instance).hasAllDrawn();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getAllDrawn() {
            return ((AppWindowTokenProto) this.instance).getAllDrawn();
        }

        public Builder setAllDrawn(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setAllDrawn(value);
            return this;
        }

        public Builder clearAllDrawn() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearAllDrawn();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasLastAllDrawn() {
            return ((AppWindowTokenProto) this.instance).hasLastAllDrawn();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getLastAllDrawn() {
            return ((AppWindowTokenProto) this.instance).getLastAllDrawn();
        }

        public Builder setLastAllDrawn(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setLastAllDrawn(value);
            return this;
        }

        public Builder clearLastAllDrawn() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearLastAllDrawn();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasRemoved() {
            return ((AppWindowTokenProto) this.instance).hasRemoved();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getRemoved() {
            return ((AppWindowTokenProto) this.instance).getRemoved();
        }

        public Builder setRemoved(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setRemoved(value);
            return this;
        }

        public Builder clearRemoved() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearRemoved();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasStartingWindow() {
            return ((AppWindowTokenProto) this.instance).hasStartingWindow();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public IdentifierProto getStartingWindow() {
            return ((AppWindowTokenProto) this.instance).getStartingWindow();
        }

        public Builder setStartingWindow(IdentifierProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setStartingWindow((AppWindowTokenProto) value);
            return this;
        }

        public Builder setStartingWindow(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setStartingWindow((AppWindowTokenProto) builderForValue);
            return this;
        }

        public Builder mergeStartingWindow(IdentifierProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).mergeStartingWindow(value);
            return this;
        }

        public Builder clearStartingWindow() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearStartingWindow();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasStartingDisplayed() {
            return ((AppWindowTokenProto) this.instance).hasStartingDisplayed();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getStartingDisplayed() {
            return ((AppWindowTokenProto) this.instance).getStartingDisplayed();
        }

        public Builder setStartingDisplayed(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setStartingDisplayed(value);
            return this;
        }

        public Builder clearStartingDisplayed() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearStartingDisplayed();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasStartingMoved() {
            return ((AppWindowTokenProto) this.instance).hasStartingMoved();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getStartingMoved() {
            return ((AppWindowTokenProto) this.instance).getStartingMoved();
        }

        public Builder setStartingMoved(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setStartingMoved(value);
            return this;
        }

        public Builder clearStartingMoved() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearStartingMoved();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean hasHiddenSetFromTransferredStartingWindow() {
            return ((AppWindowTokenProto) this.instance).hasHiddenSetFromTransferredStartingWindow();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public boolean getHiddenSetFromTransferredStartingWindow() {
            return ((AppWindowTokenProto) this.instance).getHiddenSetFromTransferredStartingWindow();
        }

        public Builder setHiddenSetFromTransferredStartingWindow(boolean value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setHiddenSetFromTransferredStartingWindow(value);
            return this;
        }

        public Builder clearHiddenSetFromTransferredStartingWindow() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearHiddenSetFromTransferredStartingWindow();
            return this;
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public List<RectProto> getFrozenBoundsList() {
            return Collections.unmodifiableList(((AppWindowTokenProto) this.instance).getFrozenBoundsList());
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public int getFrozenBoundsCount() {
            return ((AppWindowTokenProto) this.instance).getFrozenBoundsCount();
        }

        @Override // com.android.server.wm.AppWindowTokenProtoOrBuilder
        public RectProto getFrozenBounds(int index) {
            return ((AppWindowTokenProto) this.instance).getFrozenBounds(index);
        }

        public Builder setFrozenBounds(int index, RectProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setFrozenBounds((AppWindowTokenProto) index, (int) value);
            return this;
        }

        public Builder setFrozenBounds(int index, RectProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).setFrozenBounds((AppWindowTokenProto) index, (int) builderForValue);
            return this;
        }

        public Builder addFrozenBounds(RectProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).addFrozenBounds((AppWindowTokenProto) value);
            return this;
        }

        public Builder addFrozenBounds(int index, RectProto value) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).addFrozenBounds((AppWindowTokenProto) index, (int) value);
            return this;
        }

        public Builder addFrozenBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).addFrozenBounds((AppWindowTokenProto) builderForValue);
            return this;
        }

        public Builder addFrozenBounds(int index, RectProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).addFrozenBounds((AppWindowTokenProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllFrozenBounds(Iterable<? extends RectProto> values) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).addAllFrozenBounds(values);
            return this;
        }

        public Builder clearFrozenBounds() {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).clearFrozenBounds();
            return this;
        }

        public Builder removeFrozenBounds(int index) {
            copyOnWrite();
            ((AppWindowTokenProto) this.instance).removeFrozenBounds(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AppWindowTokenProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.frozenBounds_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AppWindowTokenProto other = (AppWindowTokenProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.windowToken_ = (WindowTokenProto) visitor.visitMessage(this.windowToken_, other.windowToken_);
                this.lastSurfaceShowing_ = visitor.visitBoolean(hasLastSurfaceShowing(), this.lastSurfaceShowing_, other.hasLastSurfaceShowing(), other.lastSurfaceShowing_);
                this.isWaitingForTransitionStart_ = visitor.visitBoolean(hasIsWaitingForTransitionStart(), this.isWaitingForTransitionStart_, other.hasIsWaitingForTransitionStart(), other.isWaitingForTransitionStart_);
                this.isReallyAnimating_ = visitor.visitBoolean(hasIsReallyAnimating(), this.isReallyAnimating_, other.hasIsReallyAnimating(), other.isReallyAnimating_);
                this.thumbnail_ = (AppWindowThumbnailProto) visitor.visitMessage(this.thumbnail_, other.thumbnail_);
                this.fillsParent_ = visitor.visitBoolean(hasFillsParent(), this.fillsParent_, other.hasFillsParent(), other.fillsParent_);
                this.appStopped_ = visitor.visitBoolean(hasAppStopped(), this.appStopped_, other.hasAppStopped(), other.appStopped_);
                this.hiddenRequested_ = visitor.visitBoolean(hasHiddenRequested(), this.hiddenRequested_, other.hasHiddenRequested(), other.hiddenRequested_);
                this.clientHidden_ = visitor.visitBoolean(hasClientHidden(), this.clientHidden_, other.hasClientHidden(), other.clientHidden_);
                this.deferHidingClient_ = visitor.visitBoolean(hasDeferHidingClient(), this.deferHidingClient_, other.hasDeferHidingClient(), other.deferHidingClient_);
                this.reportedDrawn_ = visitor.visitBoolean(hasReportedDrawn(), this.reportedDrawn_, other.hasReportedDrawn(), other.reportedDrawn_);
                this.reportedVisible_ = visitor.visitBoolean(hasReportedVisible(), this.reportedVisible_, other.hasReportedVisible(), other.reportedVisible_);
                this.numInterestingWindows_ = visitor.visitInt(hasNumInterestingWindows(), this.numInterestingWindows_, other.hasNumInterestingWindows(), other.numInterestingWindows_);
                this.numDrawnWindows_ = visitor.visitInt(hasNumDrawnWindows(), this.numDrawnWindows_, other.hasNumDrawnWindows(), other.numDrawnWindows_);
                this.allDrawn_ = visitor.visitBoolean(hasAllDrawn(), this.allDrawn_, other.hasAllDrawn(), other.allDrawn_);
                this.lastAllDrawn_ = visitor.visitBoolean(hasLastAllDrawn(), this.lastAllDrawn_, other.hasLastAllDrawn(), other.lastAllDrawn_);
                this.removed_ = visitor.visitBoolean(hasRemoved(), this.removed_, other.hasRemoved(), other.removed_);
                this.startingWindow_ = (IdentifierProto) visitor.visitMessage(this.startingWindow_, other.startingWindow_);
                this.startingDisplayed_ = visitor.visitBoolean(hasStartingDisplayed(), this.startingDisplayed_, other.hasStartingDisplayed(), other.startingDisplayed_);
                this.startingMoved_ = visitor.visitBoolean(hasStartingMoved(), this.startingMoved_, other.hasStartingMoved(), other.startingMoved_);
                this.hiddenSetFromTransferredStartingWindow_ = visitor.visitBoolean(hasHiddenSetFromTransferredStartingWindow(), this.hiddenSetFromTransferredStartingWindow_, other.hasHiddenSetFromTransferredStartingWindow(), other.hiddenSetFromTransferredStartingWindow_);
                this.frozenBounds_ = visitor.visitList(this.frozenBounds_, other.frozenBounds_);
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
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.name_ = s;
                                break;
                            case 18:
                                WindowTokenProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (WindowTokenProto.Builder) this.windowToken_.toBuilder();
                                }
                                this.windowToken_ = (WindowTokenProto) input.readMessage(WindowTokenProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.windowToken_);
                                    this.windowToken_ = (WindowTokenProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.lastSurfaceShowing_ = input.readBool();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.isWaitingForTransitionStart_ = input.readBool();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.isReallyAnimating_ = input.readBool();
                                break;
                            case 50:
                                AppWindowThumbnailProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder2 = (AppWindowThumbnailProto.Builder) this.thumbnail_.toBuilder();
                                }
                                this.thumbnail_ = (AppWindowThumbnailProto) input.readMessage(AppWindowThumbnailProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.thumbnail_);
                                    this.thumbnail_ = (AppWindowThumbnailProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.fillsParent_ = input.readBool();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.appStopped_ = input.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.hiddenRequested_ = input.readBool();
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.clientHidden_ = input.readBool();
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.deferHidingClient_ = input.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.reportedDrawn_ = input.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 4096;
                                this.reportedVisible_ = input.readBool();
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.numInterestingWindows_ = input.readInt32();
                                break;
                            case 120:
                                this.bitField0_ |= 16384;
                                this.numDrawnWindows_ = input.readInt32();
                                break;
                            case 128:
                                this.bitField0_ |= 32768;
                                this.allDrawn_ = input.readBool();
                                break;
                            case 136:
                                this.bitField0_ |= 65536;
                                this.lastAllDrawn_ = input.readBool();
                                break;
                            case 144:
                                this.bitField0_ |= 131072;
                                this.removed_ = input.readBool();
                                break;
                            case 154:
                                IdentifierProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 262144) == 262144) {
                                    subBuilder3 = (IdentifierProto.Builder) this.startingWindow_.toBuilder();
                                }
                                this.startingWindow_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.startingWindow_);
                                    this.startingWindow_ = (IdentifierProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 262144;
                                break;
                            case 160:
                                this.bitField0_ |= 524288;
                                this.startingDisplayed_ = input.readBool();
                                break;
                            case 168:
                                this.bitField0_ |= 1048576;
                                this.startingMoved_ = input.readBool();
                                break;
                            case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 2097152;
                                this.hiddenSetFromTransferredStartingWindow_ = input.readBool();
                                break;
                            case AtomsProto.Atom.TOMB_STONE_OCCURRED_FIELD_NUMBER:
                                if (!this.frozenBounds_.isModifiable()) {
                                    this.frozenBounds_ = GeneratedMessageLite.mutableCopy(this.frozenBounds_);
                                }
                                this.frozenBounds_.add((RectProto) input.readMessage(RectProto.parser(), extensionRegistry));
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
                    synchronized (AppWindowTokenProto.class) {
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

    public static AppWindowTokenProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AppWindowTokenProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
