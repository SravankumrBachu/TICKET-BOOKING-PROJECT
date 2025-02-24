package TwoDimensionArray;

import java.util.Scanner;

public class BookMyShow {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		OnlineTicketBooking booking=new OnlineTicketBooking();
		boolean run=true;
		Details details = null;
		while (run) {
			System.out.println("\nMenu:");
			System.out.println("1. Book a seat");
			System.out.println("2. Show available seats");
			System.out.println("3. Display confirmed bookings");
			System.out.println("4. Search the ticket by row number");
			System.out.println("5. Search the ticket by col number");
			System.out.println("6. Cancel a booking");
			System.out.println("7. Exit");
			System.out.print("Select an option: ");

			int choice = sc.nextInt();
			sc.nextLine();
//			String name, int age, String emailId, String seatno
			switch (choice) {
			case 1:
				System.out.println("Enter the name: ");
				String str=sc.nextLine();
				System.out.println("Enter the age: ");
				int age=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Email :");
				String email=sc.nextLine();
				 details=new Details(str, age, email, null);
				System.out.println("Enter the seat by row No :");
				int row=sc.nextInt();
				System.out.println("Enter the seat by col No :");
				int col=sc.nextInt();
				booking.bookTicket(row, col, details);
//				sc.nextLine();
				break;
			case 2:
				booking.displayAvailableSeats();
				break;
			case 3:
				System.out.println("Enter the screen no :");
				int screen=sc.nextInt();
				booking.displayConfirmed(screen, details);
				sc.nextLine();
				break;
			case 4:
				System.out.println("Enter the row no :");
				int row2=sc.nextInt();
				sc.nextLine();
				System.out.println("enter the your name :");
				String name=sc.nextLine();
				booking.searchByRow(row2, name);
				break;
			case 5:
				System.out.println("Enter the row no :");
				int col2=sc.nextInt();
				sc.nextLine();
				System.out.println("enter the your name :");
				String name1=sc.nextLine();
				booking.searchByCol(col2, name1);	
				break;
			case 6:
				System.out.println("enter the row number : ");
				int row3=sc.nextInt();
				sc.nextLine();
				System.out.println("enter the col number : ");
				int col3=sc.nextInt();
				sc.nextLine();
				booking.delete(row3, col3);
				break;
			case 7:
				run=false;
				break;	
			default:
				System.out.println("invalid");
				break;
			}
		}
	}
}