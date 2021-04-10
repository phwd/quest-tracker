package android.arch.lifecycle;

public class MutableLiveData<T> extends LiveData<T> {
    @Override // android.arch.lifecycle.LiveData
    public void setValue(T t) {
        super.setValue(t);
    }
}
