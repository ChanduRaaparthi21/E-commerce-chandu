package com.chandu.ecommorce.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chandu.ecommorce.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
