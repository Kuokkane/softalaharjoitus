package hh.softala.softalaharjoitus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
	
	List<Kysymys> findByKysymys(String kysymys);

}
