package dev.juniorstreichan.artists.core.service;

import dev.juniorstreichan.artists.core.model.BusinessEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Slf4j
public abstract class BaseService<
    T extends BusinessEntity,
    Repository extends JpaRepository> implements CRUDService<T> {

    private final Repository repository;

    protected BaseService(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<T> list(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public T insert(T t) {
        return (T) repository.save(t);
    }

    @Override
    public T update(T t) {
        if (t.getId() == null || !repository.existsById(t.getId())) {
            throw new EmptyResultDataAccessException(
                "Recurso não encontrado", 0
            );
        }
        return this.insert(t);
    }


    @Override
    public void delete(Long id) {
        if (id == null || !repository.existsById(id)) {
            throw new EmptyResultDataAccessException(
                "Recurso não encontrado", 0
            );
        }
        repository.deleteById(id);
    }
}
