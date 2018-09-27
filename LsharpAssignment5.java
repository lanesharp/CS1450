/*
 * Lane Sharp
 * CS 1450-001
 * Assignment 5
 * Due: September 26, 2018
 * 
 * This program uses generic classes to create stacks. 
 * Files are read and the information from each file is pushed to a stack and displayed
 * as it appears in the file.  The program then merges these stacks together and prints out
 * the stacks as sorted with the lowest value on top.
 * 
 */










import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LsharpAssignment5 
{
	
	public static void main(String[] args) throws FileNotFoundException 
	{

		GenericStack<Integer> intStack1 = new GenericStack<Integer>();

		GenericStack<Integer> intStack2 = new GenericStack<Integer>();

		int num;

		Scanner scan1 = new Scanner(new File("integers1.txt"));

		System.out.println(" Values read from integers1.txt and pushed onto stack1");

		System.out.println(" ------------------------------------------------------");

		while (scan1.hasNext())

		{
			num = scan1.nextInt();

			System.out.println(num);

			intStack1.push(num);
		}

		Scanner scan2 = new Scanner(new File("integers2.txt"));

		System.out.println(" Values read from integers2.txt and pushed onto stack2");

		System.out.println(" ------------------------------------------------------");

		while (scan2.hasNext())
		{
			num = scan2.nextInt();

			System.out.println(num);

			intStack2.push(num);
		}

		GenericStack<Integer> intMergeStack = new GenericStack<Integer>();

		mergeStacks(intStack1, intStack2, intMergeStack);

		System.out.println(" Merged Stack with lowest value on top : ");

		System.out.println(" ----------------------------------------");

		printStack(intMergeStack);

		scan1.close();

		scan2.close();

		GenericStack<String> strStack1 = new GenericStack<String>();

		GenericStack<String> strStack2 = new GenericStack<String>();

		scan1 = new Scanner(new File("states1.txt"));

		scan2 = new Scanner(new File("states2.txt"));

		String state;

		System.out.println("\n\n Values read from states1.txt and pushed onto stack1");

		System.out.println(" ------------------------------------------------------");

		while (scan1.hasNextLine())
		{
			state = scan1.nextLine();

			System.out.println(state);

			strStack1.push(state);
		}

		System.out.println("\n Values read from states2.txt and pushed onto stack2");

		System.out.println(" ------------------------------------------------------");

		while (scan2.hasNext())
		{
			state = scan2.nextLine();

			System.out.println(state);

			strStack2.push(state);
		}

		GenericStack<String> strMergeStack = new GenericStack<String>();

		mergeStacks(strStack1, strStack2, strMergeStack);

		System.out.println("\n Merged Stack with lowest value on top : ");

		System.out.println(" ----------------------------------------");

		printStack(strMergeStack);

		scan1.close();

		scan2.close();
	}
	
	// method to merge the stacks
	public static <E extends Comparable<E>> void mergeStacks(GenericStack<E> stack1, GenericStack<E> stack2,
			GenericStack<E> mergedStack)
	{
		GenericStack<E> tempStack = new GenericStack<E>();

		while (!stack1.isEmpty() && !stack2.isEmpty())
		{

			if (stack1.peek().compareTo(stack2.peek()) < 0)

				tempStack.push(stack1.pop());

			else

				tempStack.push(stack2.pop());
		}

		while (!stack1.isEmpty())

			tempStack.push(stack1.pop());

		while (!stack2.isEmpty())

			tempStack.push(stack2.pop());

		reverseStack(tempStack, mergedStack);
	}

	// method to reverse a stack
	public static <E> void reverseStack(GenericStack<E> stack, GenericStack<E> reverseStack)

	{
		while (!stack.isEmpty())

			reverseStack.push(stack.pop());
	}

	// method to print a stack

	public static <E> void printStack(GenericStack<E> stack)
	{
		while (!stack.isEmpty())

			System.out.println(stack.pop());
	}
}

class GenericStack<E> 
{

	private ArrayList<E> list;

	public GenericStack() 
	{
		list = new ArrayList<E>();
	}

	public int getSize() 
	{
		return list.size();
	}

	public boolean isEmpty() 
	{
		return getSize() == 0;

	}

	public E peek() 
	{
		return list.get(list.size() - 1);
	}

	public void push(E data) 
	{
		list.add(data);
	}

	public E pop() 
	{
		return list.remove(list.size() - 1);
	}
}
