package ru.job4j.generic;

/**
 * Class RoleStore - хранилище ролей.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 15.08.2020
 */
public class RoleStore implements Store<Role> {

    private final Store<Role> roleStore = new MemStore<>();

    /**
     * Добавление элемента в хранилише.
     *
     * @param model Элемент.
     */
    @Override
    public void add(Role model) {
        roleStore.add(model);
    }

    /**
     * Замена элемента в хранилище.
     *
     * @param id    -  Уникальный идентификатор.
     * @param model - Элемент в хранилище.
     * @return Результат замены.
     */
    @Override
    public boolean replace(String id, Role model) {
        return roleStore.replace(id, model);
    }

    /**
     * Удаление элемента из хранилища по уникальному идентификатору.
     *
     * @param id - Уникальный идентификатор.
     * @return Результат удаления.
     */
    @Override
    public boolean delete(String id) {
        return roleStore.delete(id);
    }

    /**
     * Поиск элемента в хранилище по уникальному идентификатору.
     *
     * @param id - Уникальный идентификатор.
     * @return Найденный элемент.
     */
    @Override
    public Role findById(String id) {
        return roleStore.findById(id);
    }
}
