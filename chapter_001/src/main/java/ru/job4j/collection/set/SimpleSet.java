package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * Class SimpleSet - реализация простого множества на массиве (на подобие hashSet()).
 *
 * @param <E> - тип эл-тов множества.
 * @author Bruki mammad.
 * @version $1.0$
 * @since 13.09.2020
 */
public class SimpleSet<E> implements Iterable<E> {

    private final SimpleArray<E> data = new SimpleArray<>();

    /**
     * method add() - добавляет элемент в коллекцию без дубликатов.
     *
     * @param e - добавляемый элемент.
     */
    void add(E e) {
        if (!contains(e)) {
            data.add(e);
        }
    }

    /**
     * метод contains(E e) - проверяет, есть ли элемент в колекций,
     * если есть то не добавляет,
     * иначе добавляет элемент в коллекцию.
     *
     * @param e - проверяемый элемент.
     * @return isContains.
     */
    private boolean contains(E e) {
        boolean isContains = false;
        for (E value : data) {
            if (Objects.equals(e, value)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    /**
     * итератор по коллекций на основе массива.
     *
     * @return итератор по элементам.
     */
    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }
}
