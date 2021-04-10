package X;

import com.google.common.collect.BoundType;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;

/* renamed from: X.Bl  reason: case insensitive filesystem */
public interface AbstractC0118Bl<E> extends UY<E>, AbstractC1185uk<E> {
    AbstractC0118Bl A1g();

    NavigableSet A1l();

    AbstractC1179ua A29();

    AbstractC0118Bl A3B(Object obj, BoundType boundType);

    AbstractC1179ua A3f();

    AbstractC1179ua A4V();

    AbstractC1179ua A4W();

    AbstractC0118Bl A5A(Object obj, BoundType boundType, Object obj2, BoundType boundType2);

    AbstractC0118Bl A5F(Object obj, BoundType boundType);

    @Override // X.UY
    Comparator comparator();

    @Override // X.UM
    Set entrySet();
}
