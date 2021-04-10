package com.android.server.wm;

import android.graphics.RectProto;
import android.view.DisplayCutoutProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowFramesProtoOrBuilder extends MessageLiteOrBuilder {
    RectProto getContainingFrame();

    RectProto getContentFrame();

    RectProto getContentInsets();

    DisplayCutoutProto getCutout();

    RectProto getDecorFrame();

    RectProto getDisplayFrame();

    RectProto getFrame();

    RectProto getOutsetFrame();

    RectProto getOutsets();

    RectProto getOverscanFrame();

    RectProto getOverscanInsets();

    RectProto getParentFrame();

    RectProto getStableInsets();

    RectProto getVisibleFrame();

    RectProto getVisibleInsets();

    boolean hasContainingFrame();

    boolean hasContentFrame();

    boolean hasContentInsets();

    boolean hasCutout();

    boolean hasDecorFrame();

    boolean hasDisplayFrame();

    boolean hasFrame();

    boolean hasOutsetFrame();

    boolean hasOutsets();

    boolean hasOverscanFrame();

    boolean hasOverscanInsets();

    boolean hasParentFrame();

    boolean hasStableInsets();

    boolean hasVisibleFrame();

    boolean hasVisibleInsets();
}
