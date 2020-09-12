package ru.job4j.collection;

/**
 * реализация Queue (очереди) для связанного списка.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 12.09.2020
 */
public class SimpleQueue<T> {

    private final SimpleStack<T> stackIn = new SimpleStack<>();
    private final SimpleStack<T> stackOut = new SimpleStack<>();
    /**
     * Поле для хранения размера стека входящих элементов.
     */
    private int sizeIn = 0;
    /**
     * Поле для хранения размера стека выходящих элементов.
     */
    private int sizeOut = 0;

    /**
     * Метод poll() - возвращает первое значение и удаляет его из коллекции.
     *
     * @return - первое значение.
     */
    public T poll() {
        if (sizeOut == 0) {
            for (int i = 0; i < sizeIn; i++) {
                stackOut.push(stackIn.pop());
            }
            sizeOut = sizeIn;
            sizeIn = 0;
        }
        T elem = stackOut.pop();
        sizeOut--;
        return elem;
    }

    /**
     * Метод push(T value) - помещает значение в конец.
     *
     * @param value - значение.
     */
    public void push(T value) {
        sizeIn++;
        stackIn.push(value);
    }
}
