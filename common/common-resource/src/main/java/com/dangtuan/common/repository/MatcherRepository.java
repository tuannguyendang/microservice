package com.dangtuan.common.repository;

import com.dangtuan.common.entity.Matcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatcherRepository extends JpaRepository<Matcher, Long> {

}
