/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS.
Saad Akhtar and Yikan Ge

Collaboration statement:
Saad wrote much of the code for adding a friend, removing a friend, and finding the shortest path in "Facespace_run" and also wrote the Addfriend,
Removefriend, and Shortest methods in "Facespace". Saad also edited much of the code in "Facespace_run" and "Facespace" and was the one who obtained
the "AdjMatrixGraph" code from the Graph Powerpoint presented in class. Yikan wrote the code for adding a user and updating a user and wrote the insert
and findExact methods in "Facespace". He was also the one who created the Facespace_run and Facespace classes and much of the code written is based off
of the organization of his initial draft of the Facespace_run and Facespace classes. Both of us added our own comments as we saw fit. We both acknowledge
that the work was divided fairly between ourselves.
*/

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
	
public class Facespace_run {
	
	//addedUsers counts how many users have been added and is used to assign a unique graphNumber
	//userlist stores the usernames and is also displayed to the person running the program after adding a user for the sake of convenience
	public static int addedUsers = 0;
	public static ArrayList<String> userlist = new ArrayList<String>();

	//The main method is the means by which the program and the program operator interact
	public static void main( String [ ] args ) throws FileNotFoundException {
		
		//Initializing variables. The empty Graph consisting of 50 vertices is created here.
		Facespace t = new Facespace( );
		AdjMatrixGraph G = new AdjMatrixGraph(50);
		Scanner scanner= new Scanner(System.in);
			
		//Instructions for program operator
		System.out.println("Welcome to Facespace! Use the commands in quotations below to operate the program:");
		System.out.println("To add an user, please enter 'add'.");
		System.out.println("To search for an user, please enter 'search'.");
		System.out.println("To update an user's description, please enter 'update'.");
		System.out.println("To add a friend, please enter 'friend+'.");
		System.out.println("To remove a friend, please enter 'friend-'.");
		System.out.println("To find the degrees of seperation between two users, enter 'path'.");
		System.out.println("Enter anything else to exit program.");
		System.out.println("Please type a command:");

		//Loop that will only exit if an incorrect command is entered
		while(scanner.hasNext()) {
			String selection = scanner.nextLine().trim();
				
			//Search for user
			if(selection.equals("search")) {
				System.out.println("Please enter the name of the user you wish to find:");
				Profile item = t.findExact(scanner.nextLine().trim()); 

				if(item == null) {
					System.out.println("The user you were looking for was not found!");
					System.out.println("Please type a command:");
				}else {
					System.out.println("-------------------------\n" + "Name: " + item.fullName + "\nDescription:");
					System.out.println(item.Description + "\nFriends:");
					System.out.println(item.friendlist);
					System.out.println("-------------------------");
					System.out.println("Please type a command:");
				}
		
			//Adds new user 	
			}else if(selection.equals("add")) {
				System.out.println("Please enter the name of the user you wish to add:");
				String line = scanner.nextLine().trim(); 
				System.out.println("Please enter a short description:");
				String description = scanner.nextLine().trim();
				
				Profile info = new Profile(line, description, addedUsers);
				boolean repeat = t.insert(info);
				
				if(repeat == true){ 
					addedUsers += 1;
					userlist.add(info.fullName);
					
					System.out.print("User added! ");
					System.out.print("Here are the current list of users: ");
					System.out.println(userlist);
					System.out.println("Please type a command:");
				}else {
					System.out.println("User already exists!");
					System.out.println("Please type a command:");
				}
					
			//Updates Description	
			}else if(selection.equals("update")) {
				System.out.println("Please enter the name of the user you wish to update:");
				String name = scanner.nextLine().trim(); 
				System.out.println("Please enter a short description:");
				String description = scanner.nextLine().trim();
				
				t.findExact(name).Description= description;
				System.out.println("The user's description has been updated!");
				System.out.println("Please type a command:");
				
			//Adds friend
			}else if(selection.equals("friend+")) {
					
				//Prompts for names of friend and user
				System.out.println("Please enter the name of the user you wish to add a friend to:");
				String username = scanner.nextLine().trim(); 
				System.out.println("Please enter the name of the friend you wish to add:");
				String friendname = scanner.nextLine().trim();
				
				Profile userU = t.findExact(username);
				Profile userF = t.findExact(friendname);
					
				//adds friend
				boolean [] returned = t.Addfriend(username, friendname);
					
				//checks if addition was successful
				if(returned[0] == false) {
					System.out.println("The user does not exist!");
				}
				
				if(returned[1] == false) {
					System.out.println("The friend the user wishes to add does not exist!");
				}
				
				if(returned[2] == false) {
					System.out.println("The user can't befriend himself/herself!");
				}
				
				if(returned[3] == false) {
					System.out.println("These people are already friends!");
				}
				
				if(returned[0] == true && returned [1] == true && returned[2] == true && returned[3] == true) {
					System.out.println("Successfully added friend!");
					G.addEdge(userU.graphNumber, userF.graphNumber);
				}
				
				System.out.println("Please type a command:");
				
			//removes friend	
			}else if(selection.equals("friend-")) {
					
				//Prompts for names of friend and user
				System.out.println("Please enter the name of the user you wish to remove a friend from:");
				String username = scanner.nextLine().trim(); 
				System.out.println("Please enter the name of the friend you wish to remove:");
				String friendname = scanner.nextLine().trim();
				
				Profile userU = t.findExact(username);
				Profile userF = t.findExact(friendname);
	
				boolean [] returned = t.Removefriend(username, friendname);
					
				//checks if removal was successful
				if(returned[0] == false) {
					System.out.println("The user does not exist!");
				}
				
				if(returned[1] == false) {
					System.out.println("The friend the user wishes to remove does not exist!");
				}
				
				if(returned[2] == false) {
					System.out.println("The user's list of friends is already empty!");
				}
				
				if(returned[3] == false) {
					System.out.println("The friend's list of friend's is already empty!");
				}
				
				if(returned[4] == false) {
					System.out.println("The two of them are already not friends with one another!");
				}
				
				if(returned[5] == false) {
					System.out.println("The user can't unfriend himself/herself!");
				}
				
				if(returned[0] == true && returned [1] == true && returned[2] == true && returned [3] == true && returned[4] == true && returned[5] == true) {
					System.out.println("Successfully removed friend!");
					G.removeEdge(userU.graphNumber, userF.graphNumber);
				}
				
				System.out.println("Please type a command:");
			
			//finds shortest path
			}else if(selection.equals("path")) {

				System.out.println("Please enter the name of the first user:");
				String userA = scanner.nextLine().trim(); 
				System.out.println("Please enter the name of the second user:");
				String userB = scanner.nextLine().trim();

				int count = t.Shortest(userA, userB, G);

				if(count == -50) {
					System.out.println("The first user does not exist!");
					System.out.println("Please type a command:");
				}else if(count == -75) {
					System.out.println("The second user does not exist!");
					System.out.println("Please type a command:");
				}else if(count == -100) {
					System.out.println("The two users are not connected!");
					System.out.println("Please type a command:");
				}else {
					System.out.println("There are " + count + " degrees of seperation between the two users!");
					System.out.println("Please type a command:");
				}
			
			//exits if an incorrect keyword is used
			}else {
				System.out.println("That's an invalid command! The program will now close.");
				System.exit(0);
			}
		}	
		scanner.close();
	}	
}
