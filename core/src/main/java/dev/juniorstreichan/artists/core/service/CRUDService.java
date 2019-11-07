package dev.juniorstreichan.artists.core.service;

import dev.juniorstreichan.artists.core.model.BusinessEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService<T extends BusinessEntity> {

    Page<T> page(Pageable pageable);

    T insert(T t);

    T update(T t);

    T byId(Long id);

    void delete(Long id);
}

