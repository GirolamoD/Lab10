package it.polito.tdp.porto.model;

import java.util.List;
import java.util.LinkedList;

public class Author implements Comparable<Author>{

	private int id;
	private String lastname;
	private String firstname;
	private List<Paper> papers ;
		
	public Author(int id, String lastname, String firstname) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.papers = new LinkedList<>();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return this.id + " " +  this.lastname + " " + this.firstname +"\n";
	}
	
	public void addPaper (Paper p){
		this.papers.add(p);
	}

	@Override
	public int compareTo(Author o) {
		if(this.lastname.compareTo(o.lastname)!=0)
			return this.lastname.compareTo(o.lastname);
		return this.firstname.compareTo(o.firstname);
	}
}
