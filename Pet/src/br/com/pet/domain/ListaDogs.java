package br.com.pet.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListaDogs implements Serializable {
	private static final long SerialVersion = 1L;
	private List<Dog> dogs;
	@XmlElement(name="dog")
	public List<Dog> getDogs(){
		return dogs;
	}
	public void setDogs(List<Dog> dogs) {
		this.dogs= dogs;
	}
	@Override
	public String toString(){
		return "ListaDogs[Dogs=" + dogs + "]";
	}
	
	

}
