package repository;

import java.util.List;

public interface Repository<T> {
    int getEntityId(T Entity) throws  RepositoryException;
    T getEntityById(int id) throws RepositoryException;
    List<T> getAllEntities() throws RepositoryException;
    void addEntity(T entity) throws RepositoryException;
    void updateEntity(T entity) throws RepositoryException;
    void removeEntity(T entity) throws RepositoryException;
}