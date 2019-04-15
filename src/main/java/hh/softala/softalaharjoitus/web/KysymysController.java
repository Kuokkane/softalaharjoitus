package hh.softala.softalaharjoitus.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.softala.softalaharjoitus.domain.Kysely;
import hh.softala.softalaharjoitus.domain.KyselyRepository;
import hh.softala.softalaharjoitus.domain.Kysymys;
import hh.softala.softalaharjoitus.domain.KysymysRepository;
import hh.softala.softalaharjoitus.domain.Vastaus;
import hh.softala.softalaharjoitus.domain.VastausRepository;

@CrossOrigin
@Controller
public class KysymysController {

	@Autowired
	private KysymysRepository krepository;
	
	@Autowired
	private VastausRepository vrepository;
	
	@Autowired
	private KyselyRepository kyrepository;
	
	//REST etsi kaikki kyselyt
	@RequestMapping(value="/kyselyt", method=RequestMethod.GET)
	public @ResponseBody List<Kysely> kyselylistaRest(){
		return (List<Kysely>) kyrepository.findAll();
	}
	
	//REST etsi kysely id:llä
	 @RequestMapping(value="/kyselyt/{kyselyId}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Kysely> findKyselyRest(@PathVariable("kyselyId") Long id){
	    	return kyrepository.findById(id);
	 }
	 
	//REST lisää kysely
	@RequestMapping(value="/kysely", method=RequestMethod.POST)
		public @ResponseBody Kysely addKysely (@RequestBody Kysely kysely) {
			return kyrepository.save(kysely);
	}
	
	 //REST muokkaa kysylyä
	@RequestMapping (value="/kysely/muokkaa/{kyselyId}")
	  public String editKysely (@PathVariable("kyselyId") Long id, Model model) {
		model.addAttribute("kysely", kyrepository.findById(id));
		return "muokkaa";
	}
		
	//REST etsi kaikki kysymykset
	@RequestMapping(value="/kysymykset", method=RequestMethod.GET)
	public @ResponseBody List<Kysymys> kysymyslistaRest(){
		return (List<Kysymys>) krepository.findAll();
	
	}
	
	//REST etsi kysymys id:llä
	 @RequestMapping(value="/kysymykset/{kysymysId}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Kysymys> findKysymysRest(@PathVariable("kysymysId") Long id){
	    	return krepository.findById(id);
	    }
	 
	//REST lisää kysymys
	@RequestMapping(value="/kysymys", method=RequestMethod.POST)
		public @ResponseBody Kysymys addKysymys (@RequestBody Kysymys kysymys) {
			return krepository.save(kysymys);
	}
	
	 //REST muokkaa kysymystä
	@RequestMapping (value="/kysymys/muokkaa/{kysymysId}")
	  public String editKysymys (@PathVariable("kysymysId") Long id, Model model) {
		model.addAttribute("kysymys", krepository.findById(id));
		return "muokkaa";
	}
	
	//REST etsi kaikki vastaukset
	@RequestMapping(value="/vastaukset", method=RequestMethod.GET)
	public @ResponseBody List<Vastaus> vastauslistaRest(){
		return (List<Vastaus>) vrepository.findAll();
	
	}
	
	//REST etsi vastaus id:llä
	 @RequestMapping(value="/vastaukset/{vastausId}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Vastaus> findVastausRest(@PathVariable("vastausId") Long id){
	    	return vrepository.findById(id);
	    }
	
	//REST lisää vastaus
	@RequestMapping(value="/lisaaVastaus/{kysymysId}", method=RequestMethod.POST)
		public @ResponseBody Vastaus addVastaus (@RequestBody Vastaus vastaus, @PathVariable("kysymysId") Long id) {
		Kysymys vastauksenKysymys = vastaus.getKysymys();
	// vastauksen kysymykselle asetetaan pathvariablena tullut id
		vastauksenKysymys.setId(id); //ei toimi
		return vrepository.save(vastaus);
	}

}
