package TwoDimensionArray; 

 public class Details {

	private String Name;
	private int age;
	private String EmailId;
	private String seatno;

	public Details(String name, int age, String emailId, String seatno) {
		super();
		Name = name;
		this.age = age;
		EmailId = emailId;
		this.seatno = seatno;
	}

	public String getName() {
		return Name;
	}

	public int getAge() {
		return age;
	}

	public String getEmailId() {
		return EmailId;
	}

	public String getSeatno() {
		return seatno;
	}

	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}

	@Override
	public String toString() {
		return "Details [Name=" + Name + ", age=" + age + ", EmailId=" + EmailId + ", seatno=" + seatno + "]";
	}
	

}


