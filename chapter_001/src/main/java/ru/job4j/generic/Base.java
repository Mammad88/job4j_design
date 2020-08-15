package ru.job4j.generic;

/**
 * Базовая модель объекта для хранения в контейнере.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 15.08.2020
 */
public abstract class Base {
    /**
     * поле id - уникальный идентификатор.
     */
    private final String id;

    /**
     * конструктор для инициализаций уникакльного идентификатора.
     *
     * @param id - уникакльный идентификатор.
     */
    protected Base(String id) {
        this.id = id;
    }

    /**
     * Вернуть уникальный идентификатор объекта.
     *
     * @return Уникальный идентификатор.
     */
    public String getId() {
        return id;
    }
}
