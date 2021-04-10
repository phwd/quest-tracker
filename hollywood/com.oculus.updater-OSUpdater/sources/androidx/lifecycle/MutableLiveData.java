package androidx.lifecycle;

public class MutableLiveData<T> extends LiveData<T> {
    @Override // androidx.lifecycle.LiveData
    public void setValue(T t) {
        super.setValue(t);
    }
}
