package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nagoyameshi.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @EntityGraph(attributePaths = { "storeinfo" })
    List<Favorite> findByUserId(Long userId);

    List<Favorite> findByUserIdAndStoreId(Long userId, Long storeId);

    void deleteById(Long id);

    void deleteByUserIdAndStoreId(Long userId, Long storeId); // Long型で統一

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Favorite f WHERE f.id = :id AND f.userId = :userId")
    boolean existsByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

}
