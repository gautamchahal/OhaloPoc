package com.ohalo.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import com.ohalo.assignment.model.User;

public interface UserRepository extends CrudRepository<User,String> {

}
