package Project;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		Processing process= new Processing();
		process.readFirst();
		process.write();
		process.readFinal();
		process.splitList();
		LinkedList<Integer> cor= new LinkedList<Integer>();
		cor= process.selectFeature(cor);
		System.out.print("Enter the Window Size: ");
		int w_size= sc.nextInt();
		System.out.print("Enter the number of trees: ");
		int n_tree= sc.nextInt();
		for(int i=1; i<=n_tree; i++) {
			
			LinkedList<Integer> feature= new LinkedList<Integer>();
			
			for(int j=0; j<w_size; j++) {
				if(j==0) {
					if(cor.size()<w_size) {
						cor= process.selectFeature(cor);
					}
				}
				System.out.print(cor.get(j)+ "   ");
				feature.InsertAtEnd(cor.get(j));
				
			}
			process.createDecisionTree(feature); 
			System.out.println();
			cor.DeleteFromStart();
			cor.DeleteFromStart();
		}
		
	//	cor.InsertAtEnd(7);
	//	cor.InsertAtEnd(3);
	//	cor.InsertAtEnd(10);
	///	cor.InsertAtEnd(12);
		//process.createDecisionTree(cor);
	}

}
