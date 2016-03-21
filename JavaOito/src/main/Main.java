package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import enums.ModeratorStatusEnum;
import models.User;

public class Main implements Consumer<User> {
	
	private static List<User> users;
	private static BufferedReader in;
	
	public void accept(User u){
		
	}
	
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
				
				System.out.println("Enter the users age: ");
				int age = Integer.parseInt(in.readLine());
				
				users.add(new User(name, age));
				loop--;
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sortComparator() {
		users.sort(Comparator.comparing(u -> u.getName()));
		users.forEach(u -> System.out.println(u.getName()));
	}
	
	public static void sortLambda() {
		users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
		users.forEach(u -> System.out.println(u.getName()));
	}
	
	public static void showUser() {
		Consumer<User> showName = u -> System.out.print(u.getName() + " :");
		Consumer<User> showAge = u -> System.out.println(u.getAge());
		
		users.forEach(showName.andThen(showAge));
	}
	
	public static void removeUserByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to keep: ");
		int age = Integer.parseInt(in.readLine());
		
		users.removeIf(u -> u.getAge() > age);
		
		users.forEach(u -> {
			System.out.println(u.getName());
		});
	}
	
	public static void showUserByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to show: ");
		int age = Integer.parseInt(in.readLine());
		
		users.stream().filter(u -> u.getAge() < age).forEach(System.out::println);
	}
	
	public static void saveUserByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to save: ");
		int age = Integer.parseInt(in.readLine());
		
		List<User> usersToSave = users.stream().filter(u -> u.getAge() < age).collect(Collectors.toList());
		
		usersToSave.forEach(System.out::println);
	}
	
	public static void showStatus() {
		users.forEach(u -> System.out.println(
				ModeratorStatusEnum.recoverByStatus(u.isModerator()).getDescription()));
		
	}
	
	public static void sortWithMethodReferenceAndComparator() {
		users.sort(Comparator.comparing(User::getName));
		users.forEach(System.out::println);
	}
	
	public static void sortAgeThenName() {
		users.sort(Comparator.comparingInt(User::getAge)
				.thenComparing(User::getName));
		
		showUser();
	}
	
	public static void averageAge() {
		double average = users.stream().mapToInt(User::getAge).average().orElse(0.0);
		
		System.out.println("The average age is " + average + " years");
	}
	
	public static void sortByAge() throws NumberFormatException, IOException {
		System.out.println("Enter the maximum users age that you want to show: ");
		int age = Integer.parseInt(in.readLine());
		
		users.stream().filter(u -> u.getAge() < age)
			.sorted(Comparator.comparing(User::getName))
			.peek(System.out::println)
			.collect(Collectors.toList());
	}
	
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
	
	public static void totalAge() {
		System.out.println("The total users age is " + 
				users.stream().mapToInt(User::getAge).sum() + " years old");
	}
	
	public static void agesMultiplication() {
		System.out.println("The multiplication of ages results in " +
				users.stream().mapToLong(User::getAge).reduce(1, (a,b) -> a * b)
				+ " years old");
	}
	
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

	public static void main(String [] args) throws IOException {
		initUser();
		//sortComparator();
		//showUser();
		//sortLambda();
		//showStatus();
		//sortWithMethodReferenceAndComparator();
		//sortAgeThenName();
		//removeUserByAge();
		//showUserByAge();
		//saveUserByAge();
		//averageAge();
		//sortByAge();
		//findFirstUserByAge();
		//totalAge();
		//agesMultiplication();
		moderatorState();
	}
}