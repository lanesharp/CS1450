/*
 * Lane Sharp
 * CS 1450-001
 * Assignment 3
 * 
 * 
 * Design a program that uses an abstract class with private data fields and public methods.
 * Uses interfaces and subclasses to define Birds and bird types
 * Interfaces will be bird abilities(swim, fly, run) and these are based on speed/altitude
 * Read from a file that will have birds and their speeds/altitudes listed as well as an integer used for building an array
 * Builds an array to store birds in and returns an Array List. 
 * Prints out the birds sorted by their abilities and as well as an interesting fact about them
 */




import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LsharpAssignment3
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Bird birds[];
		int n;
		int swim_speed, run_speed, fly_altitude;
		String name, type;
		Scanner scan = new Scanner(new File("birds.txt"));
		n = scan.nextInt(); // read the first line
		birds = new Bird[n];
		int i = 0;
		while (scan.hasNext())
		{
			type = scan.next();
			name = scan.next();
			run_speed = scan.nextInt();
			swim_speed = scan.nextInt();
			fly_altitude = scan.nextInt();
			
			switch (type.toLowerCase())
			{
			case "p":
				birds[i] = new Penguin(name, swim_speed, run_speed);
				break;
			case "o":
				birds[i] = new Ostrich(name, swim_speed, run_speed);
				break;
			case "d":
				birds[i] = new Duck(name, swim_speed, run_speed, fly_altitude);
				break;
			case "s":
				birds[i] = new SootyTern(name, run_speed, fly_altitude);
				break;
			case "l":
				birds[i] = new Loon(name, swim_speed, fly_altitude);
				break;
			case "h":
				birds[i] = new HummingBird(name, fly_altitude);
				break;
			}
			i++;
		}

		scan.close();
		
		ArrayList<Bird> birdsCannotFly = findCannotFly(birds);
		ArrayList<Bird> birdsCannotSwim = findCannotSwim(birds);
		ArrayList<Bird> birdsCannotRun = findCannotRun(birds);
		
		System.out.println(" BIRDS THAT CANNOT FLY!\n ------------------------------");
		
		for (i = 0; i < birdsCannotFly.size(); i++)
			System.out.println(" " + birdsCannotFly.get(i).getName() + " is a " + birdsCannotFly.get(i).getType()
					+ " and cannot fly "
					+ "\n " + birdsCannotFly.get(i).interestingFact() + "\n");
		System.out.println(" BIRDS THAT CANNOT RUN!\n ------------------------------");
		
		for (i = 0; i < birdsCannotRun.size(); i++)
			System.out.println(" " + birdsCannotRun.get(i).getName() + " is a " + birdsCannotRun.get(i).getType()
					+ " and cannot run "
					+ "\n " + birdsCannotRun.get(i).interestingFact() + "\n");
		System.out.println(" BIRDS THAT CANNOT SWIM!\n ------------------------------");

		for (i = 0; i < birdsCannotSwim.size(); i++)
			System.out.println(" " + birdsCannotSwim.get(i).getName() + " is a " + birdsCannotSwim.get(i).getType()
					+ " and cannot run "
					+ "\n " + birdsCannotSwim.get(i).interestingFact() + "\n");
		ArrayList<Bird> birdsRunSwimFly = findRunSwimFly(birds);
		System.out.println(" BIRDS THAT CAN DO IT ALL - RUN, SWIM AND FLY!\n ------------------------------");

		for (i = 0; i < birdsRunSwimFly.size(); i++)
		{
			Duck duck = (Duck) birdsRunSwimFly.get(i);
			System.out.println(" " + duck.getName() + " is a " + duck.getType() + " and cando it all "
					+ "\n Running at " + duck.run() + " mph" + "\n Swimming at " + duck.swim() + " mph"
					+ "\n Flying at " + duck.fly() + " feet in elevation"
					+ "\n " + duck.interestingFact());
		}
		
	}

	public static ArrayList<Bird> findCannotFly(Bird[] birds)
	{
		
		ArrayList<Bird> birdCannotFly = new ArrayList<>();
		for (int i = 0; i < birds.length; i++)
		{
			if (birds[i] instanceof Penguin || birds[i] instanceof Ostrich)
				birdCannotFly.add(birds[i]);
		}
		return birdCannotFly;
	}
	
	public static ArrayList<Bird> findCannotRun(Bird[] birds)
	{
		ArrayList<Bird> birdCannotRun = new ArrayList<>();
		for (int i = 0; i < birds.length; i++)
		{
			if (birds[i] instanceof Loon || birds[i] instanceof HummingBird)
				birdCannotRun.add(birds[i]);
		}
		return birdCannotRun;
	}
	
	public static ArrayList<Bird> findCannotSwim(Bird[] birds)
	{
		ArrayList<Bird> birdCannotSwim = new ArrayList<>();
		for (int i = 0; i < birds.length; i++)
		{
			if (birds[i] instanceof SootyTern || birds[i] instanceof HummingBird)
				birdCannotSwim.add(birds[i]);
		}
		return birdCannotSwim;
	}
	
	public static ArrayList<Bird> findRunSwimFly(Bird[] birds)
	{
		ArrayList<Bird> birdRunSwimFly = new ArrayList<>();
		for (int i = 0; i < birds.length; i++)
		{
			if (birds[i] instanceof Duck)
				birdRunSwimFly.add(birds[i]);
		}
		return birdRunSwimFly;
	}
	
}

abstract class Bird 
{
       private String name;
       private String type;
       
       public Bird(String name)
       {
             this.name = name;
             type ="";
       }
       
       public String getName()
       {
             return name;
       }
       
       public String getType()
       {
             return type;
       }
       
       public void setName(String name)
       {
             this.name = name;
       }
       
       public void setType(String type)
       {
             this.type = type;
       }
       
       abstract public String interestingFact();
}

// end of Bird

// Swimmer
interface Swimmer 
{
	public int swim();
}//end of Swimmer

//Runner
interface Runner 
{
	public int run();
}// end of Runner

// Flyer
interface Flyer 
{	
	public int fly();
}// end of Flyer


class Penguin extends Bird implements Runner, Swimmer 
{
	private int swim_speed, run_speed;
	public Penguin(String name, int swim_speed, int run_speed)
	{
		super(name);
		this.swim_speed = swim_speed;
		this.run_speed = run_speed;
		this.setType("Penguin");
	}

	@Override
	public int swim() 
	{
		return swim_speed;
	}
	
	@Override
	public int run() 
	{
		return run_speed;
	}

	@Override
	public String interestingFact() 
	{
		return "I can't fly but I'm the fastest swimmer and deepest diver of any bird and can stay underwater upto 20 minutes";
	}
}

class Ostrich extends Bird implements Runner, Swimmer 
{
	private int swim_speed, run_speed;
	public Ostrich(String name, int swim_speed, int run_speed) 
	{
		super(name);
		this.swim_speed = swim_speed;
		this.run_speed = run_speed;
		this.setType("Ostrich");
	}

	@Override
	public int swim() 
	{
		return swim_speed;
	}

	@Override
	public int run() 
	{
		return run_speed;
	}

	@Override
	public String interestingFact() 
	{
		return "Who needs flying when you're the biggest bird on earth ! I can grow up to 9 feet tall and weight as much as 300 pounds! Don't mess with me! ";
	}
}

class Duck extends Bird implements Swimmer, Runner, Flyer
{
	private int swim_speed, run_speed, fly_altitude;
	public Duck(String name, int swim_speed, int run_speed, int fly_altitude)
	{
		super(name);
		this.swim_speed = swim_speed;
		this.run_speed = run_speed;
		this.fly_altitude = fly_altitude;
		this.setType("Duck");
	}

	@Override
	public int fly() 
	{
		return fly_altitude;
	}

	@Override
	public int run() 
	{
		return run_speed;
	}

	@Override
	public int swim() 
	{
		return swim_speed;
	}

	@Override
	public String interestingFact()
	{
		return "A duck's highets documented flight was over Nevada where a plane struck a mallard at 21,000 feet!";
	}
}


class SootyTern extends Bird implements Runner, Flyer
{
	private int run_speed, fly_altitude;
	public SootyTern(String name, int run_speed, int fly_altitude) 
	{
		super(name);
		this.run_speed = run_speed;
		this.fly_altitude = fly_altitude;
		this.setType("Sooty Tern");
	}

	public void setRunSpeed(int run_speed)
	{
		this.run_speed = run_speed;
	}

	public void setFlyAltitude(int fly_altitude)
	{
		this.fly_altitude = fly_altitude;
	}

	@Override
	public int fly() 
	{
		return fly_altitude;
	}
	
	@Override
	public int run() 
	{
		return run_speed;
	}

	@Override
	public String interestingFact()
	{
		return "Strange as it may sound , I spent most of my life at sea and yet I can't swim! I also take 1 to 2 second naps while flying! ";
	}
}


class Loon extends Bird implements Swimmer, Flyer
{
	private int fly_altitude, swim_speed;
	public Loon(String name, int swim_speed, int fly_altitude) 
	{
		super(name);
		this.swim_speed = swim_speed;
		this.fly_altitude = fly_altitude;
		this.setType("Loon");
	}

	@Override
	public int fly()
	{
		return fly_altitude;
	}

	@Override
	public int swim() 
	{
		return swim_speed;
	}

	@Override
	public String interestingFact()
	{
		return "My legs are so far back on my body that I cannot walk on land, instead I push myself along the ground on my chest";
	}
}

class HummingBird extends Bird implements Flyer
{
	private int fly_altitude;
	public HummingBird(String name, int fly_altitude)
	{
		super(name);
		this.fly_altitude = fly_altitude;
		this.setType("Hummingbird");
	}

	@Override
	public int fly()
	{
		return fly_altitude;
	}

	@Override
	public String interestingFact() 
	{
		return "My feet are so small and my flying so adept! I've got the fastest beating heart in the animal kingdom with heart rates exceeding 1,200 beats per minute";
	}
}

