package com.oculus.panelapp.anytimeui.v2.tablet.apps;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.LibraryContentAdapter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryItem;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryPlatform;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class BindingUtils {
    @BindingAdapter(requireAll = false, value = {"platformOptions", "selectedPlatform", "selectedPlatformAttrChanged"})
    public static void updateLibraryPlatformDropdown(OCSelect oCSelect, List<LibraryPlatform> list, LibraryPlatform libraryPlatform, InverseBindingListener inverseBindingListener, List<LibraryPlatform> list2, LibraryPlatform libraryPlatform2, InverseBindingListener inverseBindingListener2) {
        if (!Objects.equals(list, list2) || libraryPlatform != libraryPlatform2) {
            oCSelect.setTitleMap(LibraryPlatform.getLabelMap());
            oCSelect.setIconMap(LibraryPlatform.getIconMap());
            oCSelect.setOnItemClick(new Function(inverseBindingListener2) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$BindingUtils$Sq_l0JqPlw8WE58bt_O7lr7vFFA */
                private final /* synthetic */ InverseBindingListener f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BindingUtils.lambda$updateLibraryPlatformDropdown$108(OCSelect.this, this.f$1, obj);
                }
            });
            oCSelect.setItems(list2);
            oCSelect.setSelectedItem(libraryPlatform2);
        }
    }

    static /* synthetic */ Object lambda$updateLibraryPlatformDropdown$108(OCSelect oCSelect, InverseBindingListener inverseBindingListener, Object obj) {
        oCSelect.setSelectedItem(obj);
        inverseBindingListener.onChange();
        return null;
    }

    @InverseBindingAdapter(attribute = "selectedPlatform", event = "selectedPlatformAttrChanged")
    public static LibraryPlatform captureSelectedPlatform(OCSelect oCSelect) {
        return (LibraryPlatform) oCSelect.getSelectedItem();
    }

    @BindingAdapter(requireAll = false, value = {"sorterOptions", "selectedSorter", "selectedSorterAttrChanged"})
    public static void updateLibrarySorterDropdown(OCSelect oCSelect, List<LibrarySorter> list, LibrarySorter librarySorter, InverseBindingListener inverseBindingListener, List<LibrarySorter> list2, LibrarySorter librarySorter2, InverseBindingListener inverseBindingListener2) {
        if (!Objects.equals(list, list2) || librarySorter != librarySorter2) {
            oCSelect.setTitleMap(LibrarySorter.getLabelMap());
            oCSelect.setOnItemClick(new Function(inverseBindingListener2) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$BindingUtils$voTrqvz5JTCo8S151_x7SijVgAE */
                private final /* synthetic */ InverseBindingListener f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BindingUtils.lambda$updateLibrarySorterDropdown$109(OCSelect.this, this.f$1, obj);
                }
            });
            oCSelect.setItems(list2);
            oCSelect.setSelectedItem(librarySorter2);
        }
    }

    static /* synthetic */ Object lambda$updateLibrarySorterDropdown$109(OCSelect oCSelect, InverseBindingListener inverseBindingListener, Object obj) {
        oCSelect.setSelectedItem(obj);
        inverseBindingListener.onChange();
        return null;
    }

    @InverseBindingAdapter(attribute = "selectedSorter", event = "selectedSorterAttrChanged")
    public static LibrarySorter captureSelectedSorter(OCSelect oCSelect) {
        return (LibrarySorter) oCSelect.getSelectedItem();
    }

    @BindingAdapter(requireAll = false, value = {"filterOptions", "filterCounts", "selectedFilter", "selectedFilterAttrChanged"})
    public static void updateLibraryFilterDropdown(OCSelect oCSelect, List<LibraryFilter> list, Map<LibraryFilter, Integer> map, LibraryFilter libraryFilter, InverseBindingListener inverseBindingListener, List<LibraryFilter> list2, Map<LibraryFilter, Integer> map2, LibraryFilter libraryFilter2, InverseBindingListener inverseBindingListener2) {
        if (!Objects.equals(list, list2) || !Objects.equals(map, map2) || libraryFilter != libraryFilter2) {
            HashMap hashMap = new HashMap();
            LibraryFilter[] values = LibraryFilter.values();
            for (LibraryFilter libraryFilter3 : values) {
                hashMap.put(libraryFilter3, getFilterText(oCSelect.getContext(), libraryFilter3, map2));
            }
            oCSelect.setTitleMap(hashMap);
            oCSelect.setAlertMap(LibraryFilter.getAlertMap());
            oCSelect.setOnItemClick(new Function(inverseBindingListener2) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$BindingUtils$wcJZzEB94Cnh7Nq3tcWufhtFAqk */
                private final /* synthetic */ InverseBindingListener f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BindingUtils.lambda$updateLibraryFilterDropdown$110(OCSelect.this, this.f$1, obj);
                }
            });
            oCSelect.setItems(list2);
            oCSelect.setSelectedItem(libraryFilter2);
        }
    }

    static /* synthetic */ Object lambda$updateLibraryFilterDropdown$110(OCSelect oCSelect, InverseBindingListener inverseBindingListener, Object obj) {
        oCSelect.setSelectedItem(obj);
        inverseBindingListener.onChange();
        return null;
    }

    @InverseBindingAdapter(attribute = "selectedFilter", event = "selectedFilterAttrChanged")
    public static LibraryFilter captureSelectedFilter(OCSelect oCSelect) {
        return (LibraryFilter) oCSelect.getSelectedItem();
    }

    @BindingAdapter({AssistantDialogContract.MultiselectionDialog.Section.ITEMS})
    public static void updateItems(RecyclerView recyclerView, List<LibraryItem> list) {
        if (recyclerView.getAdapter() instanceof LibraryContentAdapter) {
            ((LibraryContentAdapter) recyclerView.getAdapter()).setItems(list);
        }
    }

    @BindingAdapter({"android:paddingHorizontal"})
    public static void setLayoutPaddingHorizontal(OCRecyclerView oCRecyclerView, float f) {
        int i = (int) f;
        oCRecyclerView.setPadding(i, oCRecyclerView.getPaddingTop(), i, oCRecyclerView.getPaddingBottom());
    }

    @BindingAdapter({"layout_constraintHorizontal_chainStyle"})
    public static void setHorizontalChainStyle(View view, int i) {
        if (view.getParent() instanceof ConstraintLayout) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            layoutParams.horizontalChainStyle = i;
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter({"layout_constraintHorizontal_bias"})
    public static void setHorizontalBias(View view, float f) {
        if (view.getParent() instanceof ConstraintLayout) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            layoutParams.horizontalBias = f;
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter({"android:layout_marginStart"})
    public static void setMarginStart(View view, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMarginStart(i);
        view.setLayoutParams(marginLayoutParams);
    }

    private static String getFilterText(Context context, LibraryFilter libraryFilter, Map<LibraryFilter, Integer> map) {
        return context.getResources().getString(R.string.anytime_tablet_library_filter_text_with_count, libraryFilter.getLocalizedString(context), map.getOrDefault(libraryFilter, 0));
    }
}
