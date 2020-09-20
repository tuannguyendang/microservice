package com.dangtuan.resource.service.repository;

import com.dangtuan.resource.service.entity.Matcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatcherRepository extends JpaRepository<Matcher, Long> {

}
