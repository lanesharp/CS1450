
/*
 * Lane Sharp
 * CS1450-001
 * Assignment 2
 * Due: September 6, 2018
 * 
 * This program uses multiple classes to create a character, such as a hero, villain, droid and monster.  
 * The program sorts these characters into a polymorphic array. 
 *  It reads a text file and included in the text file is the size of the array, as well as character names
 *  and their catch phrases. The program then puts it all together and outputs a table filled with character names
 *  what type they are (hero, villain, droid, monster) and then lists their catch phrase. 
 */




import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LsharpAssignment2 {
	public static void main(String[] args) throws FileNotFoundException {

		//create a scanner
		Scanner scan = new Scanner(new File("characters.txt"));
		Character characters[];
		int n = scan.nextInt();
		characters = new Character[n];
		String type;
		String name;
		int i = 0;

		// read until the end of file

		while (scan.hasNextLine()) {
			type = scan.next();
			name = scan.nextLine();
			// create the object of the class based on type of character
			switch (type) {
			case "h":
				characters[i] = new Hero(name, "Hero");
				break;
			case "v":
				characters[i] = new Villian(name, "Villian");
				break;
			case "m":
				characters[i] = new Monster(name, "Monster");
				break;
			case "d":
				characters[i] = new Droid(name, "Droid");
				break;
			}
			
			i++;

		}

		// display the character information
		System.out.printf("\n %20s\t %7s \t %s", "Character Name", "Type", "Statement");
		System.out.println("\n----------------------------------------------------------------");
		for (i = 0; i < characters.length; i++) {
			System.out.printf("\n %20s\t %7s \t %s", characters[i].getName(), characters[i].getType(),
					characters[i].speak());
		}
		scan.close();// close scanner
	}
}
	abstract class Character {
		private String name;
		private String type;

		public Character(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		abstract public String speak();
	}// end of character class

	// Hero class
	class Hero extends Character {
		public Hero(String name, String type) {
			super(name, type);
		}

		@Override
		public String speak() {
			return "To the rescue! KAPOW!! BAM!! POW!!";
		}
	}// end of Hero class

	// Villian class
	class Villian extends Character {
		public Villian(String name, String type) {
			super(name, type);
		}

		@Override
		public String speak() {
			return "You'll never stop me! Haaaaa!";
		}
	}// end of Villian class

	// Monster Class
	class Monster extends Character {
		public Monster(String name, String type) {
			super(name, type);
		}

		@Override
		public String speak() {
			return "RRAAAUUGGHH GRROWR!!!";
		}
	}// end of Monster Class

	// Droid class
	class Droid extends Character {
		public Droid(String name, String type) {
			super(name, type);
		}

		@Override
		public String speak() {
			return "Beep Beep Bloop Bloop Beep!";
		}
	}// end of Droid class

