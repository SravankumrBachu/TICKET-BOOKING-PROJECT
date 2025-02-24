package TwoDimensionArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OnlineTicketBooking {

	  private Details[][] details = new Details[5][5];

		public void bookTicket(int row, int col, Details d1) {
			try {
				if (details[row][col] == null) {
					String s = (char) (64 + row) + "" + col + "";
					d1.setSeatno(s);
					details[row][col] = d1;
					System.out.println("Your ticket is confirmed for the seat Number: " + s);

				} else {
					System.out.println("The seat is already booked");
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid seat number");
			}
		}

		public void displayConfirmed(int screenNumber, Details d1) {
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");
			String form = currentTime.format(formatter);
			System.out.println(form);

			Movies movies = new Movies();
			switch (screenNumber) {
			case 1:
				System.out.println("Name: " + d1.getName());
				System.out.println("EmailId: " + d1.getEmailId());
				movies.screen1();
				System.out.println("Theatre \n" + movies.thr);
				System.out.println("Seat \n PRIME | " + d1.getSeatno());
				break;

			case 2:
				System.out.println("Name: " + d1.getName());
				System.out.println("EmailId: " + d1.getEmailId());
				movies.screen2();
				System.out.println("Theatre \n" + movies.thr);
				System.out.println("Seat \n PRIME | " + d1.getSeatno());
				break;

			case 3:
				System.out.println("Name: " + d1.getName());
				System.out.println("EmailId: " + d1.getEmailId());
				movies.screen3();
				System.out.println("Theatre \n" + movies.thr);
				System.out.println("Seat \n PRIME | " + d1.getSeatno());
				break;

			case 4:
				System.out.println("Name: " + d1.getName());
				System.out.println("EmailId: " + d1.getEmailId());
				movies.screen4();
				System.out.println("Theatre \n" + movies.thr);
				System.out.println("Seat \n PRIME | " + d1.getSeatno());
				break;

			default:
				System.out.println("No such screen number");
			}
		}

		public void displayAvailableSeats() {
			
			for (int i = 0; i < details.length; i++) {
				for (int j = 0; j < details.length; j++) {
					if (details[i][j]==null) {
						System.out.println("here the seats is seats are available "+" "+i+" "+j);
					}
				}
				
			}	
				
				
				
			

		}
		
		public void searchByRow(int row,String name) {
			for (int i = 0; i < details.length; i++) {
				if(details[row][i]!=null && details[row][i].getName().equalsIgnoreCase(name)) {
					System.out.println("the ticket is present");
					return;
				}
			}
			System.out.println("no ticket has booked");
		}
		
		public void searchByCol(int col,String name) {
			for (int i = 0; i < details.length; i++) {
				if(details[i][col]!=null && details[i][col].getName().equalsIgnoreCase(name)) {
					System.out.println("the ticket is present");
					return;
				}
			}
			System.out.println("no ticket has booked");
		}
		
		public void delete(int row,int col) {
			if(details[row][col]!=null) {
				details[row][col]=null;
				System.out.println("Ticket has been cancled");
			}
		}

}