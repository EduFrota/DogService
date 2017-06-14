package br.com.pet.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DogService {
	private DogDAO db = new DogDAO();
	
	public List<Dog> getDogs(){
		try{
			List<Dog> dogs = db.getDogs();
			return dogs;
		}catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Dog>();
			// TODO: handle exception
		}
	}
	
	public Dog getDog(Long id){
		try{
			return db.getDogById(id);
		}catch (SQLException e) {
			return null;
			// TODO: handle exception
		}
	}
	public boolean delete(Long id){
		try{
			return db.delete(id);
		}catch (SQLException e) {
			return false;
			// TODO: handle exception
		}
	}
  public boolean save(Dog d){
	  try{
		  db.save(d);
		  return true;
	  }catch (SQLException e) {
		// TODO: handle exception
		  return false;
	}
	  
  }
  public List<Dog> findByName(String name){
	  try{
		  return db.findByName(name);
		  
	  }catch (SQLException e) {
		  return null;
		// TODO: handle exception
	}
  }
  public List<Dog> findByRaca(String raca){
	  try{
		  return db.findByRaca(raca);
		  
	  }catch (SQLException e) {
		  return null;
		// TODO: handle exception
	}
  }	  
}

