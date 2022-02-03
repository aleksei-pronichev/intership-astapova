package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ImmutableList<E> implements List<E> {

    private final Object[] array;

    public ImmutableList(Object[] array) {
        this.array = array;
    }

    // находим фактический размер массива (количество заполненных ячеек)
    @Override
    public int size() {
        return array.length;
    }

    // проверяем, не пуст ли массив
    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    // метод проверяет, находится ли указанный (не эквивалентный) объект в массиве
    @Override
    public boolean contains(Object o) {
        for (Object obj : array) {
            if (obj == o) return true;
        }
        return false;
    }

    // тут все просто: возвращаем наш массив
    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public E get(int index) {
        // проверяем введенное число
        if (index > array.length - 1 || index < 0){
            System.err.println("Число " + index + " не является индексом данного списка");
            throw new IllegalArgumentException();
        }
        return (E) array[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = array.length-1; i >= 0; i--) {
            if (array[i] == o) {
                return i;
            }
        }
        return -1;
    }

    private int iteratorIndex = -1; //вывожу это за пределы внутреннего класса (честно слямзила с предыдущего
    // класса (реализовывала ArrayList))

    private class ImmutableListIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return iteratorIndex < array.length - 1;
        }

        @Override
        public E next() {
            iteratorIndex++;
            return (E) array[iteratorIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ImmutableListIterator();
    }

    private int iteratorIndex2 = -1;

    private class ListIteratorOfImmutableList<T> implements ListIterator<T> {

        @Override
        public boolean hasNext() {
            return iteratorIndex2 < array.length - 1;
        }

        @Override
        public T next() {
            iteratorIndex2++;
            return (T) array[iteratorIndex2];
        }

        @Override
        public boolean hasPrevious() {
            return iteratorIndex2 > 0;
        }

        @Override
        public T previous() {
            iteratorIndex2--;
            return (T) array[iteratorIndex2];
        }

        @Override
        public int nextIndex() {
            return iteratorIndex2++;
        }

        @Override
        public int previousIndex() {
            return iteratorIndex2--;
        }

        // Modification operations are not implement
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorOfImmutableList<E>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        iteratorIndex2 = index;
        return new ListIteratorOfImmutableList<>();
    }

    // метод возвращает новый ImmutableList вместо обычного List
    @Override
    public ImmutableList<E> subList(int fromIndex, int toIndex) {
        Object[] subArray = new Object[toIndex - fromIndex];
        for (int i = 0, j = fromIndex; i < toIndex - fromIndex; i++, j++) {
            subArray[i] = this.array[j];
        }
        return new ImmutableList<E>(subArray);
    }

    // обходим коллекцию, которая пришла к нам в качестве параметра:
    // каждый элемент проверяем, вызывая метод contains (уже реализован).
    // если метод возвращает false, возвращаем false.
    // Если все элементы коллекции содержатся в нашем массиве, возвращаем true
    @Override
    public boolean containsAll(Collection c) {
        for (Object obj : c) {
            if (!this.contains(obj)) return false;
        }
        return true;
    }

    // просто возвращаем наш массив и все
    @Override
    public Object[] toArray(Object[] a) {
        return this.array;
    }

    // мы ничего не можем добавить, поэтому при вызове метода выкидывается исключение
    @Override
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    // аналогично (здесь и далее)
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        // TODO: подобрать более подходящий класс исключения (или спросить)
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }
}

class Test {
    public static void main(String[] args) {
        String[] arr = {"hello", "bye", "Java"};
        List<String> immutableList = new ImmutableList<>(arr);

        System.out.println(immutableList.size());
        System.out.println(immutableList.get(0));
        System.out.println(immutableList.subList(0, 1).size());
        //immutableList.add("Ddd");
    }
}
