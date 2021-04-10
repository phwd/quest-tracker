package X;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/* renamed from: X.cc  reason: case insensitive filesystem */
public final class C0593cc implements InvocationHandler {
    public String A00;
    public boolean A01;
    public final List A02;

    public C0593cc(List list) {
        this.A02 = list;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        Object obj2;
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (objArr == null) {
            objArr = C0561by.A06;
        }
        if (name.equals("supports") && Boolean.TYPE == returnType) {
            return true;
        }
        if (name.equals("unsupported") && Void.TYPE == returnType) {
            this.A01 = true;
            return null;
        } else if (name.equals("protocols") && objArr.length == 0) {
            return this.A02;
        } else {
            if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1) {
                Object obj3 = objArr[0];
                if (obj3 instanceof List) {
                    List list = (List) obj3;
                    int size = list.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            obj2 = this.A02.get(0);
                            break;
                        } else if (this.A02.contains(list.get(i))) {
                            obj2 = list.get(i);
                            break;
                        } else {
                            i++;
                        }
                    }
                    String str = (String) obj2;
                    this.A00 = str;
                    return str;
                }
            }
            if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                return method.invoke(this, objArr);
            }
            this.A00 = (String) objArr[0];
            return null;
        }
    }
}
