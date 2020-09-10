package ru.job4j.collection;

public class SimpleStack<T> {
    /**
     * создание контейнера Stack на базе связанного списка.
     *
     * @author Bruki Mammad.
     * @version $1.0$
     * @since 10.09.2020
     */
    private final ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * метод pop - возвращает значение и удаляет его из коллекции.
     *
     * @return - значение.
     */
    public T pop() {
        return linked.deleteLast();
    }

    /**
     * метод push - помещает значение в коллекцию.
     *
     * @param value - добавляемый элемент.
     */
    public void push(T value) {
        linked.add(value);
    }
}
