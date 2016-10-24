package com.company;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Troy on 10/21/16.
 */
public interface HurricaneRepository extends CrudRepository<Hurricane,Integer> {
    List<Hurricane> findByCategory(int category);
    List<Hurricane> findByLocation(String location);
    List<Hurricane> findByNameContainingOrLocationContaining(String name,String location);

    Hurricane findFirstByLocation(String location);
    int countByCategory(int category);
}
