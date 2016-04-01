package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import enums.ModeratorStatusEnum;
import models.User;
import utils.Fibonacci;

/**
 * @author samuel.silva
 */
public class Main implements Consumer<User> {
	
	private static List<User> users;
	private static BufferedReader in;
	
	public void accept(User u){
		
	}
	
	/**
	 * Init Users
	 */
	public static void initUser() {
		users = new ArrayList<>();
		in = new BufferedReader(new InputStreamReader(System.in));
		int loop;
		
		System.out.println("How many users you want to inform?");
		
		try {
			loop = Integer.parseInt(in.readLine());
			
			while(loop > 0) {
				System.out.println("Enter the user name: ");
				String name = in.readLine();
				
				System.out.println("Enter the user age: ");
				int age = Integer.parseInt(in.readLine());
				
				users.add(new User(name, age));
				loop--;
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sort the user list using comparator
	 */
	public static void sortComparator() {
		users.sort(Comparator.comparing(u -> u.getName()));
		users.forEach(u -> System.out.println(u.getName()));
	}
	
	/**
	 * Sort the user list with lambda expression
	 */
	public static void sortLambda() {
		users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
		users.forEach(u -> System.out.println(u.getName()));
	}
	
	/**
	 * Show the user name and the user age
	 */
	public static void showUser() {
		Consumer<User> showName = u -> System.out.print(u.getName() + " :");
		Consumer<User> showAge = u -> System.out.println(u.getAge());
		
		users.forEach(showName.andThen(showAge));
	}
	
	/**
	 * Remove users with age above the input
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void removeUserByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to keep: ");
		int age = Integer.parseInt(in.readLine());
		
		users.removeIf(u -> u.getAge() > age);
		
		users.forEach(u -> {
			System.out.println(u.getName());
		});
	}
	
	/**
	 * Show users with age below the input 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void showUserByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to show: ");
		int age = Integer.parseInt(in.readLine());
		
		users.stream().filter(u -> u.getAge() < age).forEach(System.out::println);
	}
	
	/**
	 * Save users that have age below the input in a new list
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void saveUserByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to save: ");
		int age = Integer.parseInt(in.readLine());
		
		List<User> usersToSave = users.stream().filter(u -> u.getAge() < age).collect(Collectors.toList());
		
		usersToSave.forEach(System.out::println);
	}
	
	/**
	 * Show the user status, if is user or moderator
	 */
	public static void showStatus() {
		users.forEach(u -> System.out.println(
				ModeratorStatusEnum.recoverByStatus(u.isModerator()).getDescription()));
		
	}
	
	/**
	 * Sort the list using method reference and comparator
	 */
	public static void sortWithMethodReferenceAndComparator() {
		users.sort(Comparator.comparing(User::getName));
		users.forEach(System.out::println);
	}
	
	/**
	 * Sort the user list by the age and name
	 */
	public static void sortAgeThenName() {
		users.sort(Comparator.comparingInt(User::getAge)
				.thenComparing(User::getName));
		
		showUser();
	}
	
	/**
	 * Make a age average of the users
	 */
	public static void averageAge() {
		double average = users.stream().mapToInt(User::getAge).average().orElse(0.0);
		
		System.out.println("The average age is " + average + " years");
	}
	
	/**
	 * Sort the list by the users name and show the users under the informed limit
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void sortByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to show: ");
		int age = Integer.parseInt(in.readLine());
		
		users.stream().filter(u -> u.getAge() < age)
			.sorted(Comparator.comparing(User::getName))
			.peek(System.out::println)
			.collect(Collectors.toList());
	}
	
	/**
	 * Show the first user that have age bigger than your input
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void findFirstUserByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to show: ");
		int age = Integer.parseInt(in.readLine());
		
		User other = new User("Null", 0);
		
		User userEntity = users.stream()
				.filter(u -> u.getAge() < age)
				.findFirst()
				.orElse(other);
		
		System.out.println(userEntity.getName());
	}
	
	/**
	 * Method to sum all ages of the users
	 */
	public static void totalAge() {
		System.out.println("The total users age is " + 
				users.stream().mapToInt(User::getAge).sum() + " years old");
	}
	
	/**
	 * Method to multiply all ages of the users
	 */
	public static void agesMultiplication() {
		System.out.println("The multiplication of ages results in " +
				users.stream().mapToLong(User::getAge).reduce(1, (a,b) -> a * b)
				+ " years old");
	}
	
	/**
	 * Verify if has moderators or not
	 */
	public static void moderatorState() {
		@SuppressWarnings("unused")
		boolean hasModerator = users.stream().anyMatch(User::isModerator);
		boolean allModerators = users.stream().allMatch(User::isModerator);
		boolean noneModerators = users.stream().noneMatch(User::isModerator);
		
		long count = users.stream().filter(User::isModerator).count();
		
		if(allModerators) {
			System.out.println("All users are moderators!");
		} else if(noneModerators) {
			System.out.println("No user is moderator!");
		} else {
			if(count > 1) {
				System.out.println("There are " + count + " moderators!");
			} else {
				System.out.println("There is " + count + " moderator!");
			}
		}
	}
	
	/**
	 * Show elements of the fibonacci sequence according to the given input
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void showFibonacciSequence() throws NumberFormatException, IOException {
		System.out.println("Enter the amount of the fibonacci sequence values you want to see: ");
		int quantity = Integer.parseInt(in.readLine());
		
		IntStream.generate(new Fibonacci())
		.limit(quantity)
		.forEach(System.out::println);
	}
	
	/**
	 * Show the first element of the fibonacci sequence bigger then your input 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void showFirstBiggerThen( ) throws NumberFormatException, IOException {
		System.out.println("Enter a value: ");
		int value = Integer.parseInt(in.readLine());
		
		int biggerThen = IntStream
				.generate(new Fibonacci())
				.filter(f -> f > value)
				.findFirst()
				.getAsInt();
				
		System.out.println("The first value of the fibonacci sequence bigger Then " +  value + " is: " + biggerThen);
	}
	
	/**
	 * Menu
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args) throws IOException {
		initUser();
		int option = 0;
		
		do {
			System.out.println("What method do you want to execute? ");
			System.out.println("0 - Exit");
			System.out.println("1 - sortComparator");
			System.out.println("2 - showUser");
			System.out.println("3 - sortLambda");
			System.out.println("4 - showStatus");
			System.out.println("5 - sortWithMethodReferenceAndComparator");
			System.out.println("6 - sortAgeThenName");
			System.out.println("7 - removeUserByAge");
			System.out.println("8 - showUserByAge");
			System.out.println("9 - saveUserByAge");
			System.out.println("10 - averageAge");
			System.out.println("11 - sortByAge");
			System.out.println("12 - findFirstUserByAge");
			System.out.println("13 - totalAge");
			System.out.println("14 - agesMultiplication");
			System.out.println("15 - moderatorState");
			System.out.println("16 - showFibonacciSequence");
			System.out.println("17 - showFirstBiggerThen");
			
			option = Integer.parseInt(in.readLine());
			
			switch (option) {
			case 1:
				sortComparator();
				break;
				
			case 2:
				showUser();
				break;
				
			case 3:
				sortLambda();
				break;
				
			case 4:
				showStatus();
				break;
				
			case 5:
				sortWithMethodReferenceAndComparator();
				break;
				
			case 6:
				sortAgeThenName();
				break;
				
			case 7:
				removeUserByAge();
				break;
				
			case 8:
				showUserByAge();
				break;
				
			case 9:
				saveUserByAge();
				break;
				
			case 10:
				averageAge();
				break;
				
			case 11:
				sortByAge();
				break;
				
			case 12:
				findFirstUserByAge();
				break;
				
			case 13:
				totalAge();
				break;
				
			case 14:
				agesMultiplication();
				break;
				
			case 15:
				moderatorState();
				break;
				
			case 16:
				showFibonacciSequence();
				break;
				
			case 17:
				showFirstBiggerThen();
				break;
			default:
				break;
			}
			
			System.out.println("");
			System.out.println("");
			
		} while(option != 0);
		
		System.out.println("That all folks!");
	}
}