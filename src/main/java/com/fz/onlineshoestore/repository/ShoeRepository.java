package com.fz.onlineshoestore.repository;


import com.fz.onlineshoestore.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {
}
