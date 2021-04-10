package android.stats.launcher;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface LauncherExtensionOrBuilder extends MessageLiteOrBuilder {
    LauncherTarget getDstTarget(int i);

    int getDstTargetCount();

    List<LauncherTarget> getDstTargetList();

    LauncherTarget getSrcTarget(int i);

    int getSrcTargetCount();

    List<LauncherTarget> getSrcTargetList();
}
