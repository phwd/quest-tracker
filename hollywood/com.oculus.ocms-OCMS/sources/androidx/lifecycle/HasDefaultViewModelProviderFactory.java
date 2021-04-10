package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

public interface HasDefaultViewModelProviderFactory {
    @NonNull
    ViewModelProvider.Factory getDefaultViewModelProviderFactory();
}
