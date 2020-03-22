package com.asajenko.kudos.repository;

import com.asajenko.kudos.model.Kudos;
import org.springframework.data.repository.CrudRepository;

public interface KudosRepository extends CrudRepository<Kudos, Long> {
}
