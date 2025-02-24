package TwoDimensionArray;

public class TwoDimenArray {
   int [][] arr;
   public TwoDimenArray(int rsize, int csize) {
	arr= new int[rsize][csize];
	for (int row = 0; row < arr.length; row++) {//time complexity O(N2) nsquare
		for (int col = 0; col < arr.length; col++) {
			arr[row][col]=Integer.MIN_VALUE;
			
		}
	}
}
   
   public void insertion(int row, int col, int value)  {
	try {
		if (arr[row][col]==Integer.MIN_VALUE) {
			arr[row][col]=value;
			System.out.println("the value is "+value+"inserted");
		}else {
			System.out.println("the cell is alreeday filled");
		}
	} catch (Exception e) {
	  System.out.println("invalid row and col");
	}
}
   public void traverse() {
	for (int row = 0; row < arr.length; row++) {
		for (int col = 0; col < arr.length; col++) {
			System.out.println(arr[row][col]);
		}
		
	}
}
   public void searchbyrow(int row,int value) {
	for (int i = 0; i < arr.length; i++) {
		if (arr[i][row]==value) {
			System.out.println("the is value is present: "+value);
		}else {
			System.out.println("the value is not present ");
			
		}
	}
		
	}
   public void searchBycol(int col, int value) {
	   for (int i = 0; i < arr.length; i++) {
			if (arr[i][col]==value) {
				System.out.println("the is value is present: "+value);
			}else {
				System.out.println("the value is not present ");
				
			}
		}
}
   public void delete(int row,int col ,int value) {
	if (arr[row][col]!=Integer.MIN_VALUE) {
		arr[row][col]=value;
		System.out.println("the value has been deleted");
	}else {
	System.out.println("value doesnot present");
	}
}
   public static void main(String[] args) {
	TwoDimenArray array = new TwoDimenArray(3, 3);
       array.insertion(1, 1, 10);
       array.insertion(2, 2, 20);
       array.searchbyrow(1, 10);
//       array.searchBycol(1, 20);
//       array.traverse();
       
}
}

