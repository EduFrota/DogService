package Test;

import java.util.List;

import br.com.pet.domain.Dog;
import br.com.pet.domain.DogService;
import junit.framework.TestCase;

public class DogTest  extends TestCase{
   private DogService dogService = new DogService();

   public void testaListaDog(){
	   List<Dog> dogs = dogService.getDogs();
	   assertNotNull(dogs);
	   assertTrue(dogs.size()>0);
	   Dog lucy = dogService.findByName("Lucy").get(0);
	   assertEquals("Lucy", lucy.getNome());
   }
   public void testCrud() {
		Dog d = new Dog();
		d.setNome("Nome");
		d.setIdade("10");
		d.setRaca("Vira");
		d.setTemperamento("Agressivo");
		dogService.save(d);
		
		Long id = d.getId();
		assertNotNull(id);
		d = dogService.getDog(id);
		
		assertEquals("Nome", d.getNome());
		assertEquals("10", d.getIdade());
		assertEquals("Vira", d.getRaca());
		assertEquals("Agressivo", d.getTemperamento());
		
		d.setNome("Update");
		dogService.save(d);
		d = dogService.getDog(id);
		assertEquals("Update", d.getNome());
		dogService.delete(id);
		d= dogService.getDog(id);
		assertNull(d);
		
	}


	

}
