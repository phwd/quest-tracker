package android.stats.launcher;

import android.stats.launcher.LauncherTarget;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface LauncherTargetOrBuilder extends MessageLiteOrBuilder {
    LauncherTarget.Container getContainer();

    LauncherTarget.Control getControl();

    int getGridX();

    int getGridY();

    LauncherTarget.Item getItem();

    String getLaunchComponent();

    ByteString getLaunchComponentBytes();

    int getPageId();

    LauncherTarget.Type getType();

    boolean hasContainer();

    boolean hasControl();

    boolean hasGridX();

    boolean hasGridY();

    boolean hasItem();

    boolean hasLaunchComponent();

    boolean hasPageId();

    boolean hasType();
}
