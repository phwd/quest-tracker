package com.facebook.fresco.middleware;

import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import com.facebook.fresco.ui.common.ControllerListener2;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class MiddlewareUtils {
    public static ControllerListener2.Extras obtainExtras(Map<String, Object> map, Map<String, Object> map2, @Nullable Map<String, Object> map3, @Nullable Rect rect, @Nullable String str, @Nullable PointF pointF, @Nullable Map<String, Object> map4, @Nullable Object obj, @Nullable Uri uri) {
        ControllerListener2.Extras extras = new ControllerListener2.Extras();
        extras.view = new HashMap();
        extras.view.putAll(map);
        if (rect != null) {
            extras.view.put("viewport_width", Integer.valueOf(rect.width()));
            extras.view.put("viewport_height", Integer.valueOf(rect.height()));
        } else {
            extras.view.put("viewport_width", -1);
            extras.view.put("viewport_height", -1);
        }
        extras.view.put("scale_type", str);
        if (pointF != null) {
            extras.view.put("focus_point_x", Float.valueOf(pointF.x));
            extras.view.put("focus_point_y", Float.valueOf(pointF.y));
        }
        extras.view.put("caller_context", obj);
        if (uri != null) {
            extras.view.put("uri_main", uri);
        }
        if (map3 != null) {
            extras.pipe = map3;
            if (map4 != null) {
                extras.pipe.putAll(map4);
            }
        } else {
            extras.pipe = map4;
            extras.view.putAll(map2);
        }
        return extras;
    }
}
