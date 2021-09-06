package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pw = "student";
	
	public DatabaseAccessorObject()  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading database driver");
			System.err.println(e);
		}
	}
	
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			String sql = "SELECT id, title, description, release_year, "
					+ "language_id, rental_duration, rental_rate, length, "
					+ "replacement_cost, rating, special_features FROM film "
					+ "WHERE id = ?";
			PreparedStatement stmt= conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			//System.out.println("DEBUG: " + stmt);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				film = new Film();
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt(4));
				film.setLanguageId(rs.getInt(5));
				film.setRentalDuration(rs.getDouble(6));
				film.setLength(rs.getInt(7));
				film.setReplacementCost(rs.getDouble(8));
				film.setRating(rs.getString(9));
				film.setSpecialFeatures(rs.getString(10));			
			}
		
		} catch (SQLException e) {
			System.err.println("Database error");
			System.err.println(e);
		}
		return film;
		
	}
	@Override
	public List<Film> findFilmByKeyword(String keyWord) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			String sql = "SELECT id, title, description, release_year, "
					+ "language_id, rental_duration, rental_rate, length, "
					+ "replacement_cost, rating, special_features FROM film "
					+ "WHERE title LIKE ?";		
			PreparedStatement stmt= conn.prepareStatement(sql);
			stmt.setString(1, "%"+keyWord+"%"); 
			//System.out.println("DEBUG: " + stmt);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
					film = new Film();
					film.setId(rs.getInt(1));
					film.setTitle(rs.getString(2));
					film.setDescription(rs.getString("description"));
					film.setReleaseYear(rs.getInt(4));
					film.setLanguageId(rs.getInt(5));
					film.setRentalDuration(rs.getDouble(6));
					film.setLength(rs.getInt(7));
					film.setReplacementCost(rs.getDouble(8));
					film.setRating(rs.getString(9));
					film.setSpecialFeatures(rs.getString(10));
					films.add(film);
					System.out.println(film);
				}
		
		} catch (SQLException e) {
			System.err.println("Database error");
			System.err.println(e);
		}
		return films;
		
	}


	@Override
	public Actor findActorById(int actorId) {
		  Actor actor = null;
		  try {
			  Connection conn = DriverManager.getConnection(URL, user, pw);
			  String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			  PreparedStatement stmt = conn.prepareStatement(sql);
			  stmt.setInt(1,actorId);
			  ResultSet actorResult = stmt.executeQuery();
			  if (actorResult.next()) {
			    actor = new Actor(); 
			    actor.setActorId(actorResult.getInt(1));
			    actor.setFirstName(actorResult.getString(2));
			    actor.setLastName(actorResult.getString(3));
			  }		
		  } catch (SQLException e) {
				System.err.println("Database error");
				System.err.println(e);
			}
		  return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		  try {
		    Connection conn = DriverManager.getConnection(URL, user, pw);
		    String sql = "SELECT id, first_name, last_name \n"
		    		+ "FROM actor \n"
		    		+ "  JOIN film_actor ON actor.id = film_actor.actor_id \n"
		    		+ "WHERE film_id = ?;";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, filmId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      int actorId = rs.getInt(1);
		      String firstName = rs.getString(2);
		      String lastName = rs.getString(3);
		      Actor actor = new Actor();
		      actor.setActorId(actorId);
		      actor.setFirstName(firstName);
		      actor.setLastName(lastName);
		      actors.add(actor);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return actors;
		}
	}


