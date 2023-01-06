package ru.vt.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Utils {

    public static class EnumeratedItem<T> {
        public T item;
        public int index;

        private EnumeratedItem(T item, int index) {
            this.item = item;
            this.index = index;
        }
    }

    // Thanks to https://stackoverflow.com/a/34804751
    private static class ListEnumerator<T> implements Iterable<EnumeratedItem<T>> {

        private Iterable<T> target;
        private int start;

        public ListEnumerator(Iterable<T> target, int start) {
            this.target = target;
            this.start = start;
        }

        @Override
        public Iterator<EnumeratedItem<T>> iterator() {
            final Iterator<T> targetIterator = target.iterator();
            return new Iterator<EnumeratedItem<T>>() {

                int index = start;

                @Override
                public boolean hasNext() {
                    return targetIterator.hasNext();
                }

                @Override
                public EnumeratedItem<T> next() {
                    EnumeratedItem<T> nextIndexedItem = new EnumeratedItem<T>(targetIterator.next(), index);
                    index++;
                    return nextIndexedItem;
                }

            };
        }

    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable, int start) {
        return new ListEnumerator<T>(iterable, start);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable) {
        return enumerate(iterable, 0);
    }

    public static <T> ArrayList<T> toArrayList(final Iterator<T> iterator) {
        return StreamSupport
            .stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String join(String delimeter, List<?> list) {
        return String.join(delimeter, list.stream().map(Object::toString).toList());
    }

    public static <T> void synchronizeOnEmpty(Collection<T> collection, Runnable runnable) {
        if (collection.isEmpty()) {
            synchronized (collection) {
                if (collection.isEmpty()) {
                    runnable.run();
                }
            }
        }
    }

    public static <K, V> void synchronizeOnEmpty(Map<K, V> map, Runnable runnable) {
        if (map.isEmpty()) {
            synchronized (map) {
                if (map.isEmpty()) {
                    runnable.run();
                }
            }
        }
    }

    public static <T, K> T ifNotNull(K obj, Function<K, T> func) {
        if (obj == null) {
            return null;
        } else {
            return func.apply(obj);
        }
    }

}
