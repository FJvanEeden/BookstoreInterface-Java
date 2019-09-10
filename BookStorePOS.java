// imports java packages
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class BookStorePOS {
	
	// Prints out a menu of options. 
	static void homeMenu() {
		System.out.println("\n1: Enter book ~");
		System.out.println("2: Update book ~");
		System.out.println("3: Delete book ~");
		System.out.println("4: Search books ~");
		System.out.println("0: Exit application ~");
		System.out.println("\nEnter selection number [0 - 4]:\n");
	}

	// Main menu of cases to execute.
	static void mainMenu(int m) {
		switch (m) {	// Creates a switch statement with the expression 'm'
		
			case 0:	// Declared case to evaluate expression against.
				System.out.println("\nThank you for using the Book-Store Point of Sale.\nGoodbye.");
					break;
					
			case 1: // Declared case to evaluate expression against.
				System.out.println("\nAdd a book ~");
				System.out.println("\nPlease enter a unique ID for the record:");
				String id = userInput();	// Calls method to get user input.
				
				/* Creates a try-catch exception to test if user input is a valid integer.
				   If input value isn't an integer, the application restarts. */
					try { 
						Integer.parseInt(id);	// Parses input value (string) to integer
					} catch (Exception e) {		// Displays error message when triggered. After which it restarts the application.
						System.out.println("Please enter a valid number.");
						startCom();		// Restarts application by calling method.
						break;
					}
					
				// Verifies ID input to ensure that no duplicate's are added to the database. 
				boolean returnValue = verifyID(id);		// Calls ID verification method and returns boolean value.
				
				// Starts If-Else statement.
				if (returnValue == false) {		// If returned boolean is false, the application will continue to request user data.
					System.out.println("\nPlease enter the name of a book you would like to add to the database:");
					String title = userInput();		// Calls method to get user input.
					System.out.println("\nPlease enter the name of the Author:");
					String author = userInput();	// Calls method to get user input.
					System.out.println("\nPlease enter Quantity of books to add:");
					String qty = userInput();	// Calls method to get user input.
					insertDB(Integer.parseInt(id), title, author, Integer.parseInt(qty));	// Adds variables assigned with user data to called database 'insert' method
						System.out.println("\nThank you...:");
						startCom();		// Restarts application by calling method.
				} else {	// If returned boolean is true, the application will restart.
					System.out.println("\nSorry, an error has occured. Please try again.");
					startCom();		// Restarts application by calling method.
				} 
					break;

			case 2:	// Declared case to evaluate expression against.
				System.out.println("\nUpdate a books information ~");
				System.out.println("\nWhat would you like to update? Please select one of the following options ['Title' , 'Author' , 'Quantity']: ");
				String updateSelection = userInput();	// Calls method to get user input.
					
					// Starts If-Else statement to match user input...
					if (updateSelection.equals("TITLE")) {		// Executes switch statement case if user input matches string.
						subUpdateMenu(1);
					} else if (updateSelection.equals("AUTHOR")) {		// Executes switch statement case if user input matches string.
						subUpdateMenu(2);
					} else if (updateSelection.equals("QUANTITY")) {		// Executes switch statement case if user input matches string.
						subUpdateMenu(3);
					} else {	// If no strings match, prints error and restarts application.
						System.out.println("\nInvalid selection! Please try again...");
						startCom();		// Restarts application by calling method.
					}
					break;
					
			case 3:	// Declared case to evaluate expression against.
				System.out.println("\nRemove a book ~");
				System.out.println("\nPlease enter the name of the book to be deleted from the database: ");
				String index = userInput();	// Calls method to get user input.
				deleteDB(index);	// Calls method to delete database entry.
				startCom();		// Restarts application by calling method.
					break;
					
			case 4:	// Declared case to evaluate expression against.
				System.out.println("\nSearch for a book ~");
				System.out.println("\nHow would you like to search for a book? Please select one of the following options ['Title' , 'Author' , 'ID']: ");
				System.out.println("\nAlternativly, select 'All' to display all records.");
				String searchSelection = userInput();	// Calls method to get user input.
					if (searchSelection.equals("TITLE")) {		// Executes switch statement case if user input matches string.
						subSearchMenu(1);
					} else if (searchSelection.equals("AUTHOR")) {		// Executes switch statement case if user input matches string.
						subSearchMenu(2);
					} else if (searchSelection.equals("ID")) {		// Executes switch statement case if user input matches string.
						subSearchMenu(3);
					} else if (searchSelection.equals("ALL")) {		// Executes switch statement case if user input matches string.
						subSearchMenu(4);
					} else {	// If no strings match, prints error and restarts application.
						System.out.println("\nInvalid selection! Please try again...");
						startCom();		// Restarts application by calling method.
					}
					break;
					
			// Default action if no case is called.		
			default: System.out.println("\nInvalid selection. Please try again using a valid option.");
					 	startCom();		// Restarts application by calling method.
		}
	}
	
	// sub menu creator for database search options.
	static void subSearchMenu(int s) {
		switch (s) {	// Creates a switch statement with the expression 's'
			
		case 1: // Declared case to evaluate expression against.
			System.out.println("\nSearch for a book by title ~");
			System.out.println("Please enter the Title of the book you're looking for: ");
			String searchTitle = userInput();	// Calls method to get user input.
			searchTitleDB(searchTitle);		// Calls method to search database using a book title.
				break;
				
		case 2: // Declared case to evaluate expression against.
			System.out.println("\nSearch for a book by author ~");
			System.out.println("Please enter the Authors name of the book you're looking for: ");
			String searchAuthor = userInput();	// Calls method to get user input.
			searchAuthorDB(searchAuthor);	// Calls method to search database using a book author.
				break;
				
		case 3: // Declared case to evaluate expression against.
			System.out.println("\nSearch for a book by ID ~");
			System.out.println("Please enter the ID of the book you're looking for: ");
			
			// Creates a try-catch exception to test if user input is a valid integer
			try { 
				int subMenuSearchSelection_1 = Integer.parseInt(userInput());	// Calls method to get user input, and parses input value (string) to integer.
				searchIdDB(subMenuSearchSelection_1);	// Calls method to search database using a book ID.
			} catch (Exception e) {		// Displays error message when triggered. After which it restarts the application.
				System.out.println("Please enter a valid number.");
				startCom();		// Restarts application by calling method.
				break;
			}
					break;
					
		case 4: // Declared case to evaluate expression against.
			System.out.println("\nDisplaying all entries in database ~\n");
			searchAll(); 	// Calls method to search database and displays all records.
				break;
				
		// Default action if no case is called.		
		default: System.out.println("\nInvalid selection. Please try again using a valid option.");
		 			startCom();		// Restarts application by calling method.
		}
	}
	
	// Method to verify ID input when called.
	static boolean verifyID(String idNum) { 
		
		// Creates try-catch to display exceptions and errors.
			try(
				// allocates a database connection object.
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
										
				// allocates a statement object inside the connection.
				Statement stmt = conn.createStatement();
				) {
				
				// Assigns SQL query to a variable.
				String selectID = "select id from books";		// SQL query to select all records in 'ID' column.
				ResultSet rs = stmt.executeQuery(selectID);		// Executes SQL query and creates a result set.
				ArrayList<String> idArrList = new ArrayList<>();	// Creates a new Array List.
				
				// While the result set has a next value...
				while (rs.next()) {
					idArrList.add(rs.getString("id"));	// Adds result set value to array list.
				}
	
				// Assigns boolean value (true) to variable if Array list contains user input value.
				boolean ans = idArrList.contains(idNum);
				if (ans) {		// Displays error message if ID is contained within array.
					System.out.println("Error! Entered ID number has already been used. Please choose a different ID.");
					startCom();		// Restarts application by calling method.
				} else {
					return false;
				}
			}
				catch(SQLException ex) { 	// Displays error message (stack trace) when triggered.
					ex.printStackTrace();
			}
			return false;
		}

	// sub menu creator for database update options.
	static void subUpdateMenu(int u) {
		switch (u) {	// Creates a switch statement with the expression 'u'
			
		case 1: // Declared case to evaluate expression against.
			System.out.println("\nUpdate a book title ~");
			System.out.println("Please enter the ID number of the book to be updated: ");
			//String index = userInput();	// Calls method to get user input.
			int subMenuSelection_1 = Integer.parseInt(userInput());		// Calls method to get user input and parses input value (string) to integer
			System.out.println("Please enter the new book title: ");
			String newTitle = userInput();	// Calls method to get user input.
			updateTitleDB(subMenuSelection_1, newTitle);	// Calls method to update book title using a book ID.
				break;
				
		case 2: // Declared case to evaluate expression against.
			System.out.println("\nUpdate a book author ~");
			System.out.println("Please enter the title of the book to be updated: ");
			String titleIndex = userInput();	// Calls method to get user input.
			System.out.println("Please enter the new author of the book to be updated: ");
			String newAuthor = userInput();		// Calls method to get user input.
			updateAuthorDB(newAuthor, titleIndex);		// Calls method to update book author using a book title.
				break;
				
		case 3: // Declared case to evaluate expression against.
			System.out.println("\nUpdate a book quantity ~");
			System.out.println("Please enter the title of the book to be updated: ");
			String titleIndexQty = userInput();	// Calls method to get user input.
			System.out.println("Please enter the new book quantity: ");
			
			// Creates a try-catch exception to test if user input is a valid integer
			try { 
				int subMenuUpdateSelection_3 = Integer.parseInt(userInput());	// Calls method to get user input. // Parses input value (string) to integer
				updateQtyDB(subMenuUpdateSelection_3, titleIndexQty);		// Calls method to update book quantity using a book title.
			} catch (Exception e) {		// Displays error message when triggered. After which it restarts the application.
				System.out.println("Please enter a valid number.");
				startCom();		// Restarts application by calling method.
				break;
			}
				break;
		
		// Default action if no case is called.			
		default: System.out.println("\nInvalid selection. Please try again using a valid option.");
		 			startCom();		// Restarts application by calling method.
		}
	}
	
	// Method to update book title in database when called.
	static void updateTitleDB(int bookID, String newBookTitle) {
		
		// Creates try-catch to display exceptions and errors.
		try(
			// allocates a database connection object.
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
								
			// allocates a statement object inside the connection.
			Statement stmt = conn.createStatement();
			
			// Assigns new variable with SQL query which itself contains variables at specific index points.
			PreparedStatement updateDB = conn.prepareStatement("update books set Title = ? where id = ?");
			) {
				updateDB.setString(1, titleCase(newBookTitle));		// Sets prepared statement index variable value.
				updateDB.setInt(2, bookID);		// Sets prepared statement index variable value.
				updateDB.executeUpdate();		// Executes database update using the SQL query.
				startCom();		// Restarts application by calling method.
		}
			catch(SQLException ex) {	// Displays error message (stack trace) when triggered.
				ex.printStackTrace();
		}		
	}
	
	// Method to update book author in database when called.
	static void updateAuthorDB(String authorFull, String bookTitle) {
		
		// Creates try-catch to display exceptions and errors.
		try(
			// allocates a database connection object.
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
								
			// allocates a statement object inside the connection.
			Statement stmt = conn.createStatement();
				
			// Assigns new variable with SQL query which itself contains variables at specific index points.
			PreparedStatement updateDB = conn.prepareStatement("update books set Author = ? where title = ?");
			) {
				updateDB.setString(1, titleCase(authorFull));		// Sets prepared statement index variable value.
				updateDB.setString(2, titleCase(bookTitle));		// Sets prepared statement index variable value.
				updateDB.executeUpdate();		// Executes database update using the SQL query.
				startCom();		// Restarts application by calling method.
		}
			catch(SQLException ex) {	// Displays error message (stack trace) when triggered.
				ex.printStackTrace();
		}		
	}
	
	// Method to update book quantity in database when called.
	static void updateQtyDB(int qtyNumber, String bookTitle) {
		
		// Creates try-catch to display exceptions and errors.
		try(
			// allocates a database connection object.
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
								
			// allocates a statement object inside the connection.
			Statement stmt = conn.createStatement();
				
			// Assigns new variable with SQL query which itself contains variables at specific index points.
			PreparedStatement updateDB = conn.prepareStatement("update books set qty = ? where title = ?");
			) {
				updateDB.setInt(1, qtyNumber);		// Sets prepared statement index variable value.
				updateDB.setString(2, bookTitle);		// Sets prepared statement index variable value.
				updateDB.executeUpdate();		// Executes database update using the SQL query.
				startCom();		// Restarts application by calling method.
		}
			catch(SQLException ex) {	// Displays error message (stack trace) when triggered.
				ex.printStackTrace();
		}		
	}
	
	// Method to delete a book from the database when called.
	static void deleteDB(String index) {
		
		// Creates try-catch to display exceptions and errors.
		try(
			// allocates a database connection object.
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
								
			// allocates a statement object inside the connection.
			Statement stmt = conn.createStatement();
			
			// Assigns new variable with SQL query which itself contains variables at specific index points.
			PreparedStatement deleteFromDB = conn.prepareStatement("delete from books where upper(title) = ?");
			) {
				deleteFromDB.setString(1, index);		// Sets prepared statement index variable value.
				deleteFromDB.executeUpdate();		// Executes database update using the SQL query.
				startCom();		// Restarts the application.
		}
			catch(SQLException ex) {	// Displays error message (stack trace) when triggered.
				ex.printStackTrace();
		}		
	}
	
	// Method to insert a book into the database when called.
	static void insertDB(int id, String name, String author, int qty) {
		
		// Creates try-catch to display exceptions and errors.
		try(
			// allocates a database connection object.
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
								
			// allocates a statement object inside the connection.
			Statement stmt = conn.createStatement();
			
			// Assigns new variable with SQL query which itself contains variables at specific index points.
			PreparedStatement insertIntoDB = conn.prepareStatement("insert into books values (?, ?, ?, ?)");
			) {
				insertIntoDB.setInt(1,  id);		// Sets prepared statement index variable value.
				insertIntoDB.setString(2,  name);		// Sets prepared statement index variable value.
				insertIntoDB.setString(3,  author);		// Sets prepared statement index variable value.
				insertIntoDB.setInt(4,  qty);		// Sets prepared statement index variable value.
				insertIntoDB.executeUpdate();		// Executes database update using the SQL query.
				startCom();		// Restarts application by calling method.
		}
			catch(SQLException ex) {	// Displays error message (stack trace) when triggered.
				ex.printStackTrace();
		}		
	}

	// Method to search the database by book title when called.
	static void searchTitleDB(String bookTitle) {
		// Creates try-catch to display exceptions and errors.
			try(
				// allocates a database connection object.
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
										
				// allocates a statement object inside the connection.
				Statement stmt = conn.createStatement();
					
				// Assigns new variable with SQL query which itself contains variables at specific index points.
				PreparedStatement searchDB = conn.prepareStatement("select * from books where upper(title) like ?");
				) {
					searchDB.setString(1, "%" + bookTitle + "%");		// Sets prepared statement index variable value. Using '%', the query will return partial results as well.
					boolean results = searchDB.execute();		// Executes SQL query by assigning it to boolean value.
					int rsCount = 0;		// Sets counter to 0.
						
					// While the SQL query successfully executes (boolean returns true)
					do {
						if (results) {		// If boolean value equals true...
							ResultSet rs = searchDB.getResultSet();		// Gets value from result set and assigns it to variable.
							System.out.println("\nYour search results: ");
							
							// While the result set has a next value...
							while (rs.next()) {
								rsCount++;		// Adds 1 to counter.
								System.out.println("\nID: " + rs.getInt("id") + "\nTitle: " + rs.getString("Title") + "\nAuthor: " + rs.getString("Author")+ "\nQty: " + rs.getInt("Qty"));
							}
						}
				        results = searchDB.getMoreResults();		// Moves to this statement object's next result.
						System.out.println("Amount of records found: " + rsCount);
						
						// If boolean value equals false...
				        } while (results);
							startCom();		// Restarts application by calling method.
				    }
					catch(SQLException ex) {	// Displays error message (stack trace) when triggered.
						ex.printStackTrace();
				}		
		} 
	
	// Method to search the database by book ID when called.
	static void searchIdDB(int bookId) {
		
		// Creates try-catch to display exceptions and errors.
		try(
			// allocates a database connection object.
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
								
			// allocates a statement object inside the connection.
			Statement stmt = conn.createStatement();
			
			// Assigns new variable with SQL query which itself contains variables at specific index points.	
			PreparedStatement searchDB = conn.prepareStatement("select * from books where id = ?");
			) {
				searchDB.setInt(1, bookId);		// Sets prepared statement index variable value.
				boolean results = searchDB.execute();		// Executes SQL query by assigning it to boolean value.
				int rsCount = 0;	// Sets counter to 0.
				
				// While the SQL query successfully executes (boolean returns true)
				do {
					if (results) {		// If boolean value equals true...
						ResultSet rs = searchDB.getResultSet();		// Gets value from result set and assigns it to variable.
						System.out.println("\nYour search results: ");
						
						// While the result set has a next value...
						while (rs.next()) {
							rsCount++;		// Adds 1 to counter.
							System.out.println("\nID: " + rs.getInt("id") + "\nTitle: " + rs.getString("Title") + "\nAuthor: " + rs.getString("Author")+ "\nQty: " + rs.getInt("Qty"));
						}
					}
		            results = searchDB.getMoreResults();		// Moves to this statement object's next result.
					System.out.println("Amount of records found: " + rsCount);
		        } while (results);
		    }
			catch(SQLException ex) {		// Displays error message (stack trace) when triggered.
				ex.printStackTrace();
		}		
	}
	
	// Method to search the database by book author when called.
	static void searchAuthorDB(String bookAuthor) {
		
		// Creates try-catch to display exceptions and errors.
		try(
			// allocates a database connection object.
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
								
			// allocates a statement object inside the connection.
			Statement stmt = conn.createStatement();
			
			// Assigns new variable with SQL query which itself contains variables at specific index points.
			PreparedStatement searchDB = conn.prepareStatement("select * from books where upper(Author) like ?");
			) {
				searchDB.setString(1, "%" + bookAuthor + "%");		// Sets prepared statement index variable value.
				boolean results = searchDB.execute();		// Executes SQL query by assigning it to boolean value.
				int rsCount = 0;		// Sets counter to 0.
				
				// While the SQL query successfully executes (boolean returns true)
				do {
					if (results) {		// If boolean value equals true...
						ResultSet rs = searchDB.getResultSet();		// Gets value from result set and assigns it to variable.
						System.out.println("\nYour search results: ");
						
						// While the result set has a next value...
						while (rs.next()) {
							rsCount++;		// Adds 1 to counter.
							System.out.println("\nID: " + rs.getInt("id") + "\nTitle: " + rs.getString("Title") + "\nAuthor: " + rs.getString("Author")+ "\nQty: " + rs.getInt("Qty"));
						}
					}
		            results = searchDB.getMoreResults();		// Moves to this statement object's next result.
		            System.out.println("Amount of records found: " + rsCount);
		        } while (results);
		    }
			catch(SQLException ex) {		// Displays error message (stack trace) when triggered.
				ex.printStackTrace();
		}		
	}
	
	// Method to display all database entries when called.
	static void searchAll() {
		// Creates try-catch to display exceptions and errors.
			try(
				// allocates a database connection object.
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "root");
										
				// allocates a statement object inside the connection.
				Statement stmt = conn.createStatement();
				) {
					int rsCount = 0;		// Sets counter to 0.
					
					// Assigns SQL query to a variable.
					String allRecords = "Select * from books";		// Assigns SQL query to a variable.
					ResultSet rset = stmt.executeQuery(allRecords);		// Executes SQL query and retrieves result set.
					
					// While the result set has a next value...
					while(rset.next()) {
						rsCount++;		// Adds 1 to counter.
						System.out.println(rset.getInt("id") + ", " + rset.getString("author") + ", " + rset.getString("title")+ ", " + rset.getInt("qty"));
					}	
					System.out.println("\nNumber of entries: " + rsCount);
				   }
				catch(SQLException ex) {	// Displays error message (stack trace) when triggered.
					ex.printStackTrace();
			}		
		}	
	
	// Method to get user input when called.
	static String userInput() {	
		Scanner input = new Scanner(System.in);		// Calls scanner to get user input.
		String menuSelection = input.nextLine();	// Gets next line from user input.
		String selectionUpper = menuSelection.toUpperCase();		// Calls method to cast string to upper case.
		return selectionUpper;		// Value to return when method is called.
	}
	
	// Method to start / restart application when called.
	static void startCom() {
		homeMenu();			// Calls method to display menu.
		int menuSelection_1 = Integer.parseInt(userInput()); 	// Calls method to get user input. // Parses input value (string) to integer
		mainMenu(menuSelection_1);		// Calls main menu method including expression for switch statement.
	}
	
	// Method to convert user string input into title case when called.
	public static String titleCase(String text) {
		
		// If input string is empty, method moves to next value.
		if (text == null || text.isEmpty()) {
			return text;
		}
		
		// Creates a new modifiable string.
		StringBuilder converted = new StringBuilder();
		
		// Creates new boolean variable (true).
		boolean convertNext = true;
		
		// Starts for loop.
		for (char ch : text.toCharArray()) {	// Converts string input into a character array.
			
			// If character is a space, method moves to next character.
			if (Character.isSpaceChar(ch)) {	
				convertNext = true;
				
			} else if (convertNext) {		// Converts character to title case if it is the first character of a word.
				ch = Character.toTitleCase(ch);
				convertNext = false;
				
			} else {		// Else, the character is converted to lower case.
				ch = Character.toLowerCase(ch);
			}
			converted.append(ch);		// Adds character to string builder.
		}
		return converted.toString();	// Returns string builder as a string.
	}
	
	// BookStorePOS main method
	public static void main(String[] args) {

		System.out.println("\nWelcome to the Book-Store Point of sale!\nPlease select an option from the list below...\n");
		startCom();		// Restarts application by calling method.
		} 
}

