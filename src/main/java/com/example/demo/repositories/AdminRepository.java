package com.example.demo.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer>{

}
