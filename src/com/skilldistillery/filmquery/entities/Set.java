package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Set {
	private String set;
	
 //set objects:'Trailers','Commentaries','Deleted Scenes',
 // 'Behind the Scenes'

	public Set(String set) {
		super();
		this.set = set;
	}

	public String getSet() {
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(set);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Set other = (Set) obj;
		return Objects.equals(set, other.set);
	}

	@Override
	public String toString() {
		return "set" ;
	}
	
	
}
