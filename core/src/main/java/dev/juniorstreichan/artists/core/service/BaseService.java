package dev.juniorstreichan.artists.core.service;

import dev.juniorstreichan.artists.core.model.BusinessEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
public abstract class BaseService<
    T extends BusinessEntity,
    Repository extends JpaRepository> implements CRUDService<T> {

    protected final Repository repository;

    protected BaseService(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Page<T> page(Pageable pageable) {
        log.info(this.getClass().getSimpleName() + ": Busca paginada ");
        log.info(pageable.toString());
        return repository.findAll(pageable);
    }

    @Override
    public T insert(T t) {
        log.info("Inserindo " + t.getClass().getSimpleName());
        log.info(t.toString());
        return (T) repository.save(t);
    }

    @Override
    public T update(T t) {
        if (t.getId() == null || !repository.existsById(t.getId())) {
            log.warn("Objeto " + t.getClass().getSimpleName() + " inválido");
            log.info(t.toString());
            throw new EmptyResultDataAccessException(
                "Recurso não encontrado para a alteração", 0
            );
        }
        log.info("Alterando " + t.getClass().getSimpleName());
        log.info(t.toString());
        return this.insert(t);
    }

    @Override
    public T byId(Long id) {
        log.info("Buscando Objeto com id " + id);
        return (T) repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if (id == null || !repository.existsById(id)) {
            log.warn("Objeto com id " + id + " inválido");

            throw new EmptyResultDataAccessException(
                "Recurso não encontrado", 0
            );
        }
        log.info("Deletando Objeto com id " + id);
        repository.deleteById(id);
    }
}
