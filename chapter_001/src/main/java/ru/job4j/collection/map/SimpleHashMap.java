package ru.job4j.collection.map;

import java.util.*;

/**
 * Ассоциативный массив на базе хэш-таблицы.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 05.10.2020
 */
public class SimpleHashMap<K, V> implements Iterable<V> {

    /**
     * Начальная вместимость хеш таблицы по-умолчанию (1 << 4 = 16).
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * Порог заполнения (при его превышении происходит увеличение вместимости хранилища.
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Хеш-таблица, для хранение пар "ключ-значение" (в виде массива).
     */
    private Entry<K, V>[] table;

    /**
     * Количество добавленных элементов.
     */
    private int size;

    /**
     * Счётчик структурных изменений (для реализации fail-fast поведения итератора).
     */
    private int modCount;

    /**
     * Конструирууктор формирует пустую карту с начальной вместимостью по-умолчанию.
     */
    public SimpleHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Конструктор формирует пустую карту с заданной начальной вместимостью.
     *
     * @param initialCapacity начальная вместимость.
     * @throws IllegalArgumentException при попытке задать некорректную вместимость.
     */
    public SimpleHashMap(int initialCapacity) {
        if (initialCapacity < 0 && initialCapacity % 2 != 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.resize(initialCapacity);
    }

    /**
     * Возвращает количество элементов.
     *
     * @return кол-во элементов.
     */
    public int size() {
        return this.size;
    }

    /**
     * Добавляет в хранилище объект на основе заданной пары ключ-значение.
     * При возникновении коллизии добавление не происходит.
     *
     * @param key   ключ.
     * @param value значение.
     * @return true, если успешно.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int index = this.indexFor(this.hash(key), this.table.length);
        if (this.table[index] == null) {
            this.addEntry(key, value, index);
            this.size++;
            this.modCount++;
            result = true;
            this.checkCapacity();
        }
        return result;
    }

    /**
     * Проверяет необходимость расширения хранилища (колво элементов превышает установленные ограничения).
     * В случае необходимости — вызывает метод замены хранилища на новое с удвоенным размером.
     */
    private void checkCapacity() {
        if (this.size >= DEFAULT_LOAD_FACTOR * this.table.length) {
            this.resize(this.table.length << 1);
        }
    }

    /**
     * Хеш-функция на основе хеш-кода ключа.
     * Сдвигаем старшие разряды числа(начального хеш-кода ключа) вправо на 16 позиций (>>> 16)
     * и выполняем операцию XOR (^ побитовое логическое или).
     * Этим страхуемся от неудачной функции hashcode().
     *
     * @param key ключ.
     * @return hash значение хеш-функции.
     */
    private int hash(K key) {
        int hash = 0;
        if (key != null) {
            hash = key.hashCode();
            hash ^= hash >>> 16;
        }
        return hash;
    }

    /**
     * Вычисляет индекс ячейки массива (bucket), в кот. будет храниться новый элемент.
     *
     * @param hash   хеш-фукнция нового элемента.
     * @param length колво ячеек (bucket).
     * @return индекс в массиве.
     */
    private int indexFor(int hash, int length) {
        return (length - 1) & hash;
    }

    /**
     * Помещает новый данные  в хранилище, представляя их в виде объекта {@link Entry}
     *
     * @param key   ключ
     * @param value значение
     */
    private void addEntry(K key, V value, int bucketIndex) {
        this.table[bucketIndex] = new Entry<>(key, value);
    }

    /**
     * Создает новое хранилище заданной вместимости.
     * Перемещает в него элементы из текущего, если они не null.
     *
     * @param size заданная вместимость.
     * @return карта новой вместимости.
     */
    private void resize(int size) {
        Entry<K, V>[] newData = (Entry<K, V>[]) new Entry[size];
        if (table != null) {
            for (Entry<K, V> entry : this.table) {
                if (entry != null) {
                    int index = this.indexFor(this.hash(entry.key), size);
                    newData[index] = entry;
                }
            }
        }
        this.table = newData;
    }

    /**
     * Возвращает значение по заданному ключу.
     * При отсутствии — null.
     *
     * @param key ключ.
     * @return значение.
     */
    public V get(K key) {
        int index = this.indexFor(hash(key), table.length);
        Entry<K, V> selected = table[index];
        return selected != null && (key == null ? selected.key == null : Objects.equals(key, selected.key))
                ? selected.value
                : null;
    }

    /**
     * Убирает из хранилища данные по заданному ключу.
     *
     * @param key ключ.
     * @return true, если успешно.
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = indexFor(hash(key), table.length);
        Entry<K, V> selected = table[index];
        if (selected != null && Objects.equals(key, selected.key)) {
            table[index] = null;
            size--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = SimpleHashMap.this.modCount;
            private int cursor = -1;

            {
                this.shift();
            }

            @Override
            public boolean hasNext() {
                if (SimpleHashMap.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return this.cursor != SimpleHashMap.this.table.length;
            }

            @Override
            public V next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return SimpleHashMap.this.table[this.shift()].value;
            }

            private int shift() {
                int previous = this.cursor;
                do {
                    this.cursor++;
                } while (this.hasNext() && SimpleHashMap.this.table[cursor] == null);
                return previous;
            }
        };
    }

    /**
     * Класс, испольщующийся для внутреннего представления данных, переданных в коллекцию на хранение.
     */
    private static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }
    }
}
