package SingleDimensionArray;

public class SingleDimensionArrayString {
		int arr[];
		
		SingleDimensionArrayString(int size){
			arr= new int[size];
			for (int i = 0; i < arr.length; i++) {
				 arr[i]= Integer.MIN_VALUE;
			}
		}
	 public void insertion(int index,int value) {
		try {
			if (arr[index]==Integer.MIN_VALUE) {
				arr[index]=value;
				System.out.println("the value "+value+"is inserted");
			}else {
				System.out.println("the cell is already fulled");
			}
		} catch (Exception e) {
			System.out.println("invalid index");
		}
	}
	 public void traverse() {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	 public void searching(int value) {
	for (int i = 0; i < arr.length; i++) {
		if (arr[i]==value) {
			System.out.println("the value is present in index"+":"+i);
		} else {
	     System.out.println("the value is not present");
		}
	}
	}
	 
	 public void deletion(int index) {
		try {
			if (arr[index]!=Integer.MIN_VALUE) {
				arr[index]=Integer.MIN_VALUE;
				
				System.out.println("the avalue is deleted");
			} else {
	      System.out.println("no element is present");
			}
		} catch (Exception e) {
			System.out.println("invalid index");
		}
	}
	 
	 public static void main(String[] args) {
		SingleDimensionArray sda = new SingleDimensionArray(5);
		sda.insertion(0, 10);
		sda.insertion(1, 20);
		sda.insertion(2, 30);
		sda.insertion(3, 40);
		sda.insertion(4, 50);
		sda.traverse();
		sda.searching(40);
		sda.deletion(3);
		sda.traverse();
	}}
