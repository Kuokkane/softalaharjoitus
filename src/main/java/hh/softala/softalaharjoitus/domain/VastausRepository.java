package hh.softala.softalaharjoitus.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VastausRepository extends CrudRepository<Vastaus, Long> {
	
	List<Vastaus> findByVastaus(String vastaus);

	String get(List<Vastaus> vastaukset);

}
