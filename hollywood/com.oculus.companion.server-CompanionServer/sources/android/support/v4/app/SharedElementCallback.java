package android.support.v4.app;

import android.view.View;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
    public abstract void onMapSharedElements(List<String> list, Map<String, View> map);

    public abstract void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3);

    public abstract void onSharedElementStart(List<String> list, List<View> list2, List<View> list3);
}
