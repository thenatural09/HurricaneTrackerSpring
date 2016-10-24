package com.company;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Troy on 10/24/16.
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    User findFirstByName(String name);
}
