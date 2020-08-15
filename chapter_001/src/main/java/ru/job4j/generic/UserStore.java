package ru.job4j.generic;
/**
 * Class UserStore - хранилище пользователей.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 15.08.2020
 */
public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    /**
     * Method add() - добавление элемента в хранилише.
     *
     * @param model - элемент.
     */
    @Override
    public void add(User model) {
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
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    /**
     * method delete() - Удаление элемента из хранилища по уникальному идентификатору.
     *
     * @param id -  уникальный идентификатор.
     * @return Результат удаления.
     */
    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    /**
     * method findById()- поиск элемента в хранилище по уникальному идентификатору.
     *
     * @param id - уникальный идентификатор.
     * @return Найденный элемент.
     */
    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
