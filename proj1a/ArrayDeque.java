public class ArrayDeque<T> {
    /* Use a circular Array. */

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static double RFACTOR = 0.25;

    public ArrayDeque() {
        items = (T []) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

//    public ArrayDeque (ArrayDeque other) {
//        items = (T[]) new Object[other.items.length];
//        nextFirst = other.nextFirst;
//        nextLast = other.nextLast;
//        size = other.size;
//        System.arraycopy(other.items,0,items,0,other.items.length);
//    }

    private void resize(int capacity) {
        T[] newItems = (T []) new Object[capacity];
        System.arraycopy(items, nextFirst + 1, newItems,
                capacity / 2 + 1, items.length - 1 - nextFirst);
        System.arraycopy(items, 0, newItems, capacity / 2 + items.length - nextFirst, nextLast);
        nextFirst = capacity / 2;
        nextLast = capacity / 2 + 1 + size;
        items = newItems;
    }

    public void addFirst(T item) {
        if (nextLast == nextFirst) {
            resize(items.length * (int) (1 / RFACTOR));
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = (items.length + (nextFirst - 1)) % items.length;
    }

    public void addLast(T item) {
        if (nextLast == nextFirst) {
            resize(items.length * (int) (1 / RFACTOR));
        }
        items[nextLast] = item;
        size += 1;
        nextLast = (nextLast + 1) % items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst + 1; i <= nextFirst + size; i++) {
            T value = items[i % items.length];
            System.out.print(value);
            if (i == nextFirst + size) {
                System.out.print("\n");
            } else {
                System.out.print(" ");
            }
        }
    }

    private void downSize(int capacity) {
        T[] newItems = (T []) new Object[capacity];
        if (nextFirst + size > items.length - 1) {
            // now array is a circular
            System.arraycopy(items, nextFirst + 1, newItems, 1, items.length - 1 - nextFirst);
            System.arraycopy(items, 0, newItems, items.length - nextFirst, nextLast);
            nextFirst = 0;
            nextLast = (1 + size) % capacity;
            items = newItems;
        } else {
            // now array is not a circular
            System.arraycopy(items, nextFirst + 1, newItems, 1, size);
            nextFirst = 0;
            nextLast = (1 + size) % capacity;
            items = newItems;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firstValue = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        nextFirst = (nextFirst + 1) % items.length;
        size -= 1;
        if ((double) size / items.length < RFACTOR && items.length > 16) {
            downSize((int) (items.length * RFACTOR));
        }
        return firstValue;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T lastValue = items[(nextLast + items.length - 1) % items.length];
        if ((double) size / items.length < RFACTOR && items.length > 16) {
            downSize(size * (int) (1 / RFACTOR));
        }
        items[(nextLast + items.length - 1) % items.length] = null;
        nextLast = (items.length + nextLast - 1) % items.length;
        size -= 1;
        return lastValue;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }
}
