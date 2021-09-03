package com.skilldistillery.filmquery.entities;

public enum Rating {
	G("G"), PG("PG"), PG13("PG13"), R("R"), NC17("NC17"),;

	final private String rating;
	
	Rating(String r) {
		rating = r;
	}
	@Override
	public String toString() {
		return rating;
	}
	
}
