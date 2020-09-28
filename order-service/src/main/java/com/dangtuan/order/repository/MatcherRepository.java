package com.dangtuan.order.repository;

import com.dangtuan.order.entity.Matcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatcherRepository extends JpaRepository<Matcher, Long> {

}
