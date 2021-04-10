package android.service.appwidget;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface AppWidgetServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    WidgetProto getWidgets(int i);

    int getWidgetsCount();

    List<WidgetProto> getWidgetsList();
}
