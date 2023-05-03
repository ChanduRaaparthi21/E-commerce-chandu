package com.chandu.ecommorce.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chandu.ecommorce.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
