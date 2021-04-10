package X;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/* renamed from: X.0vO  reason: invalid class name and case insensitive filesystem */
public class C07810vO implements InvocationHandler {
    public String A00;
    public boolean A01;
    public final List<String> A02;

    public C07810vO(List<String> list) {
        this.A02 = list;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Object obj2;
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (objArr == null) {
            objArr = C08160w5.A0F;
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
