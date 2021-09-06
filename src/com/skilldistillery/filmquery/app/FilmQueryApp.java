package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	boolean quit = false;
	while (!quit) {
		System.out.println("Please choose an option. Type in the number of your choice.");
		System.out.println("\t 1.) Look up film by id \n"
				+ "\t 2.) Look up film by keyword \n"
				+ "\t 3.) Quit");
		int choice = input.nextInt();
		if (choice == 1) {
			findFilm(1, input);
		}
		else if (choice == 2) {
			findFilm(2, input);
			
		}
		else if (choice == 3) {
			System.out.println("Quitting application");
			quit = true;
		}
		else  {
			System.out.println("Invalid entry. Please try again.");
		}
		input.nextLine();
	}
	  //to check list size: 
	  //	if (films.size() == 0) { ..
    
  }

private void findFilm(int choice, Scanner input) {
	if(choice == 1) {
		int filmId;
		System.out.println("Please enter the film id:");
		filmId = input.nextInt();
		Film film = db.findFilmById(filmId);
		if (film != null) {
		    System.out.println(film);
		    int langId = film.getLanguageId();
		    displayLanguage(langId);
			List<Actor> actors = new ArrayList<>();
			actors = db.findActorsByFilmId(film.getId());
			System.out.println(actors);
		    System.out.println("");
	    } else {
	    	System.out.println("No film with that ID was found");
	    }
	    
	}
	else if (choice == 2) {
		String keyword;
		System.out.println("Please enter the film keyword:");
		keyword = input.next();
		List<Film> films = db.findFilmByKeyword(keyword);
		if (films != null) {
			for(int i = 0; i < films.size(); i++) {
			    System.out.println(films);
			    int langId = films.get(i).getLanguageId();
			    displayLanguage(langId);
				List<Actor> actors = new ArrayList<>();
				actors = db.findActorsByFilmId(films.get(i).getId());
				System.out.println(actors);
			    System.out.println("");
				}
	    } else {
	    	System.out.println("No film with that keyword was found");
	    }
	}
	
}

private void displayLanguage(int langId) {

	switch(langId) {
		case 1: System.out.println("This film is in English");
			break;
		case 2: System.out.println("This film is in Italian");
			break;
		case 3: System.out.println("This film is in Japanese");
			break;
		case 4: System.out.println("This film is in Mandarin");
			break;
		case 5: System.out.println("This film is in French");
			break;
		case 6: System.out.println("This film is in German");
			break;
	}
	
}

}
