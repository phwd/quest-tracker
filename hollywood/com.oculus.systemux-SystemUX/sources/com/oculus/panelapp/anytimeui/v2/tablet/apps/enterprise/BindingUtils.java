package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem;
import java.util.List;

public class BindingUtils {
    @BindingAdapter({AssistantDialogContract.MultiselectionDialog.Section.ITEMS})
    public static void updateItems(RecyclerView recyclerView, List<ManagedLauncherItem> list) {
        if (recyclerView.getAdapter() instanceof ManagedLauncherContentAdapter) {
            ((ManagedLauncherContentAdapter) recyclerView.getAdapter()).setItems(list);
        }
    }
}
