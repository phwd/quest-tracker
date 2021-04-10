package androidx.databinding;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;

public class BaseObservable implements Observable {
    private transient PropertyChangeRegistry mCallbacks;

    @Override // androidx.databinding.Observable
    public void addOnPropertyChangedCallback(@NonNull Observable.OnPropertyChangedCallback onPropertyChangedCallback) {
        synchronized (this) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new PropertyChangeRegistry();
            }
        }
        this.mCallbacks.add(onPropertyChangedCallback);
    }

    @Override // androidx.databinding.Observable
    public void removeOnPropertyChangedCallback(@NonNull Observable.OnPropertyChangedCallback onPropertyChangedCallback) {
        synchronized (this) {
            if (this.mCallbacks != null) {
                this.mCallbacks.remove(onPropertyChangedCallback);
            }
        }
    }

    public void notifyChange() {
        synchronized (this) {
            if (this.mCallbacks != null) {
                this.mCallbacks.notifyCallbacks(this, 0, null);
            }
        }
    }

    public void notifyPropertyChanged(int i) {
        synchronized (this) {
            if (this.mCallbacks != null) {
                this.mCallbacks.notifyCallbacks(this, i, null);
            }
        }
    }
}
