package com.mainacad.dao;

import com.mainacad.entity.Cart;
import com.mainacad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartDAO extends JpaRepository<Cart, Integer> {

    Optional<Cart> findById(Integer id);

    List<Cart> findByUser(Integer userID);

    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE status=2 AND user_id=?")
    List<Cart> findByOpenCartAndUser(Integer usedId);

}
