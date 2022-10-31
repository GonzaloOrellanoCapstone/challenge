package com.tenpo.challenge.repository;

import com.tenpo.challenge.model.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMessageRepository extends JpaRepository<LogMessage, Long>  {
}
