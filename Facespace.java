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

import java.util.ArrayList;
import java.util.LinkedList;

public class Facespace {
	
	Profile root;
		
	public Facespace() {
			
	}
		
	//method to insert a new user
	public boolean insert (Profile data){
		
		//initializing variables	
		int x = 0;
		boolean repeat = false;
			
		//first entry
		if(root == null) {
			root = data;
			data.ID = x;
		
		//entries besides the first	
		}else { 
			Profile temp = root;
			while (temp != null) {
					
				//checking for repeats
				if(temp.fullName.compareToIgnoreCase(data.fullName) == 0) {
					return repeat;
				}
					
				//entering data into a linked list
				//entering data into the new double array
				if(temp.fullName.compareToIgnoreCase(data.fullName) < 0) {
					if(temp.right == null) {
						temp.right = data; 
						x = x+1;
						data.ID = x;
						repeat = true;
						return repeat;	
					}else {
						temp = temp.right;
						x = x+1;
						data.ID = x;
					}
				}
					
				if(temp.fullName.compareToIgnoreCase(data.fullName) > 0) {
					if(temp.left == null) {
						temp.left = data;
						x = x+1;
						data.ID = x;
						repeat = true;
						return repeat;
							
					}else {
						temp = temp.left;
						x = x+1;
						data.ID = x;	
					}
				}
			}
		}
		repeat = true;
		return repeat;	
	}
		
	//Search function for matching friend
	public Profile findExact(String key) {
		
		//Handles for no users added
		if(root == null) {
			return null; 
		}else { 
			Profile temp = root;
			while (temp != null) {		
				//Find right result, return the profile
				if(temp.fullName.compareToIgnoreCase(key) == 0) {
					return temp;
				}

				if(temp.fullName.compareToIgnoreCase(key) < 0) {
					//At end of list, no matches	
					if(temp.right == null) {
						return null;
					//Moves to the right in search of a more fitting result	
					}else {
						temp = temp.right;
					}
				}
		
				if(temp.fullName.compareToIgnoreCase(key) > 0) {	
					//At start of list, no matches	
					if(temp.left == null) {
						return null;	
					//Moves to the left in search of a more fitting result	
					}else {
						temp = temp.left;
					}
				}
			}
		}
		return null;
	}

	//Method to add new friends to user
	//Reciprocal, friend also adds user in turn
	public boolean [] Addfriend (String username, String friendname) {
		boolean [] toreturns = new boolean [] {true, true, true, true};
		Profile newfriend = findExact(friendname);
		Profile user = findExact(username);
		
		//checks if a user is trying to be made a friend of himself
		if(username.equals(friendname)){
			toreturns[2] = false;
		}
			
		//checks if user and requested friend exist
		if(user == null) {
			toreturns[0] = false;
		}
		
		if(newfriend == null) {
			toreturns[1] = false;
		}
		
		if(user != null && newfriend != null) {
			if(user.friendlist.contains(friendname)) {
				toreturns[3] = false;
			}
		}

		if(toreturns[0] == false || toreturns[1] == false || toreturns[2] == false || toreturns[3] == false) {
			return toreturns;	
		//changes friend status	
		}else {
			user.friendlist.add(friendname);
			newfriend.friendlist.add(username);
		}
		return toreturns;
	}

	//Method to Remove new friends from user
	//Reciprocal, friend also remove user in turn
	public boolean[] Removefriend (String username, String friendname) {
		
		boolean [] toreturn = new boolean [] {true, true, true, true, true, true};
		Profile newfriend = findExact(friendname);
		Profile user = findExact(username);
			
		//checks if user and requested friend exist
		if(user == null) {
			toreturn[0] = false;
		}
		
		if(newfriend == null) {
			toreturn[1] = false;
		}
		
		if(user.friendlist.isEmpty()) {
			toreturn[2] = false;
		}
		
		if(newfriend.friendlist.isEmpty()) {
			toreturn[3] = false;
		}
		
		if(!user.friendlist.contains(friendname)) {
			toreturn[4] = false;
		}
		
		if(username.equals(friendname)) {
			toreturn[5] = false;
		}

		if(toreturn[0] == false || toreturn[1] == false || toreturn[2] == false || toreturn[3] == false || toreturn[4] == false || toreturn[5] == false) {
			return toreturn;
		//changes friend status	
		}else {
			user.friendlist.remove(friendname);
			newfriend.friendlist.remove(username);
		}
		return toreturn;
	}
	
	//Finds shortest path
	public int Shortest (String namea, String nameb, AdjMatrixGraph G) {
		
		int encounter = 0;
		int count = 0;
		
		if(namea.equals(nameb)) {
			encounter = 0;
			return encounter;
		}
		
		ArrayList<Integer> visited = new ArrayList<Integer>();
		
		Profile userA = findExact(namea);
		Profile userB = findExact(nameb);
		LinkedList<Integer> q = new LinkedList<Integer>();
		
		if(userA == null) {
			encounter = -50;
			return encounter;
		}
		
		if(userB == null) {
			encounter = -75;
			return encounter;
		}
		
		if(userA.friendlist.isEmpty()) {
			encounter = -100;
			return encounter;
		}
		
		if(userB.friendlist.isEmpty()) {
			encounter = -100;
			return encounter;
		}
		
		q.add(userA.graphNumber);
		
		while (!q.isEmpty()) {
			int element = q.poll();
			
			if(element == -1) {
				if(q.peek() == null) {
					break;
				}else if(q.peek() == -1) {
					continue;
				}else {
					int z = 0;
					
					if(q.peek() == null) {
						break;
					}else {
						z = q.poll();
					}
					
					if(q.peek() == null) {
						break;
					}else if(z == userB.graphNumber) {
						q.addFirst(z);
						encounter += 1;
						count = 0;
						continue;
					}else if(z != userB.graphNumber && count == 1) {
						q.addFirst(z);
						encounter += 1;
						count = 0;
						continue;
					}else if(q.peek() == -1) {
						q.addFirst(z);
						count = 0;
						continue;
					}else {
						q.addFirst(z);
						encounter += 1;
						count = 0;
						continue;
					}
				}
			}else {
				if(!visited.contains(element)) {
					visited.add(element);
				}else {
					continue;
				}
			}
			
			if(element == userB.graphNumber) {
				encounter += 1;
				return encounter;
			}
			
			for (int k : G.adj(element)) {
				if(visited.contains(k)) {
					count += 1;
					continue;
				}else {
					q.add(k);
				}
			}
			q.add(-1);
		}
		
		encounter = -100;
		return encounter;
	}
}

//Profile object that has a user's name, description, and graphNumber (special identifier for adjacency list and comes in use for Shortest). Each
//profile also has a list of friends and an ID.
class Profile {
	String fullName;
	String Description; 
	int graphNumber;
	int ID;
	ArrayList<String> friendlist = new ArrayList<String>();
	Profile left;
	Profile right;
		
	public Profile(String f, String D, int N) {
		Description = D;
		fullName = f;
		graphNumber = N;
	}
}
