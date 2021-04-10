package com.oculus.panelapp.anytimeui.v2.tablet.common;

import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import java.util.List;

public interface AppViewModel {
    void executeInitialActionsAfterLoad();

    void flagInitialAppsLoadingState(boolean z);

    void flagInitialUnknownSourcesLoadingState(boolean z);

    void setApp(App app);

    void setApps(List<App> list);

    void setUnknownSources(List<UnknownSource> list);
}
