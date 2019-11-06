package dev.juniorstreichan.artists.core.service;

import dev.juniorstreichan.artists.core.model.BusinessEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<T extends BusinessEntity> {

//    Page<T> page(Pageable pageable);

    List<T> list(Pageable pageable);

    T insert(T t);

    T update(T t);

    void delete(Long id);
}

