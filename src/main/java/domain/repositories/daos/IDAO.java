package domain.repositories.daos;

import java.util.List;

public interface IDAO<T> {
    public boolean exist(int id, Class<T> clase);
    public T find(int id, Class<T> clase);
    public List<T> findAll(Class<T> clase);
    public void delete(T object);
    public void update(T object);
    public void insert(T object);
}
