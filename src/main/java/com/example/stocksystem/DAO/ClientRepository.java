package com.example.stocksystem.DAO;

import com.example.stocksystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT c" +
            " FROM Client c " +
            "WHERE c.email = :email")
    List<Client> findByEmail(@Param("email") String email);

    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE c.name like  :name")
    List<Client> findByName(@Param("name") String name);

    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE c.phone_number = :phone_number")
    List<Client> findByPhone_number(@Param("phone_number") String phone_number);
}
