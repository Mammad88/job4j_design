package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class MemStore<T> - каркас универсального хранилище.
 *
 * @param <T> Тип хранимой модели наследовавшийся от базовой.
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 15.08.2020
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> store = new ArrayList<>();

    /**
     * Method add() - добавление элемента в хранилише.
     *
     * @param model - элемент.
     */
    @Override
    public void add(T model) {
        store.add(model);
    }

    /**
     * Method replace() - замена элемента в хранилище.
     *
     * @param id    - уникальный элемент.
     * @param model - элемент в хранилище.
     * @return резултат замены.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        ListIterator<T> tIterator = store.listIterator();
        while (tIterator.hasNext()) {
            if (tIterator.next().getId().equals(id)) {
                tIterator.set(model);
                result = true;
                break;
            }

        }
        return result;
    }

    /**
     * method delete() - Удаление элемента из хранилища по уникальному идентификатору.
     *
     * @param id -  уникальный идентификатор.
     * @return Результат удаления.
     */
    @Override
    public boolean delete(String id) {
        return store.removeIf(t -> t.getId().equals(id));
    }

    /**
     * method findById()- поиск элемента в хранилище по уникальному идентификатору.
     *
     * @param id - уникальный идентификатор.
     * @return Найденный элемент.
     */
    @Override
    public T findById(String id) {
        return store.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
