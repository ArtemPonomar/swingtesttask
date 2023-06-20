package repository;

import entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class GenericRepository<E extends Entity> {
    protected Long autoincrement = 1L;
    protected final Map<Long, E> storage = new TreeMap<>();

    public E add(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                    "Cannot execute add operation, with null entity.");
        }
        entity.setId(autoincrement++);
        storage.put(entity.getId(), entity);
        return entity;
    }

    public E getById(Long id) {
        return storage.get(id);
    }

    public E update(E entity) {
        if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException(
                    "Cannot execute update, unexpected value = %s".formatted(entity));
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    public List<E> getAll(){
        return List.copyOf(storage.values());
    }
}
