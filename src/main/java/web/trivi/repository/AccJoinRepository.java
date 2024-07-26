package web.trivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.trivi.domain.AccompanyJoin;


public interface AccJoinRepository extends JpaRepository<AccompanyJoin, Long> {
}
