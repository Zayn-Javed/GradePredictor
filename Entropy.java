package Project;

public class Entropy {
	private LinkedList<Double> uniqueTP= new LinkedList<Double>();
	private LinkedList<Integer> occuranceTP= new LinkedList<Integer>();
	private LinkedList<Double> uniqueCP= new LinkedList<Double>();
	private LinkedList<LinkedList<Integer>> occuraneCP= new LinkedList<LinkedList<Integer>>();
	private LinkedList<Double> entropyCP= new LinkedList<Double>();
	private double entropyTP;
	
	public Entropy(LinkedList<Record> list) {
		LinkedList<Double> column= TPcol(list);	
		this.uniqueTP =fnidUniqueness(column);///function would return a refined list of type double
		this.occuranceTP= findOccurance(this.uniqueTP,list);
		this.entropyTP= calEntropy(this.occuranceTP);
	}
	
	public LinkedList<Double> getUniqueTP() {
		return uniqueTP;
	}
	public void setUniqueTP(LinkedList<Double> uniqueTP) {
		this.uniqueTP = uniqueTP;
	}
	public LinkedList<Integer> getOccuranceTP() {
		return occuranceTP;
	}
	public void setOcuuranceTP(LinkedList<Integer> ocuuuranceTP) {
		this.occuranceTP = ocuuuranceTP;
	}
	public double getEntropyTP() {
		return entropyTP;
	}
	public void setEntropyTP(double entropyTP) {
		this.entropyTP = entropyTP;
	}
	
	public double log2(double n) {
	    return Math.log(n) / Math.log(2);
	}
	
	public LinkedList<Double> getUniqueCP() {
		return uniqueCP;
	}

	public void setUniqueCP(LinkedList<Double> uniqueCP) {
		this.uniqueCP = uniqueCP;
	}

	public LinkedList<LinkedList<Integer>> getOccuraneCP() {
		return occuraneCP;
	}

	public void setOccuraneCP(LinkedList<LinkedList<Integer>> occuraneCP) {
		this.occuraneCP = occuraneCP;
	}

	public LinkedList<Double> getEntropyCP() {
		return entropyCP;
	}

	public void setEntropyCP(LinkedList<Double> entropyCP) {
		this.entropyCP = entropyCP;
	}

	public void setOccuranceTP(LinkedList<Integer> occuranceTP) {
		this.occuranceTP = occuranceTP;
	}

	///for constructor only i.e., for target class
	public LinkedList<Double> TPcol(LinkedList<Record> list){
		LinkedList<Double> column= new LinkedList<Double>();
		for(int i=0; i<list.size(); i++) {
			column.InsertAtEnd(list.get(i).getT_point());
		}
		return column;
	}
	
	
	public LinkedList<Double> fnidUniqueness(LinkedList<Double> column) {
		//find unique set
		LinkedList<Double> unique= new LinkedList<Double>();
		for(int i=0; i<column.size(); i++) {
			if(column.get(i).compareTo(-1.0)==0) {
				continue;
			}
			boolean flag= false;
			for(int j=0; j<unique.size(); j++) {	
				if(column.get(i).compareTo(unique.get(j))==0) {	
					flag= true;
					break;
				}
			}
			if(!flag) {
				unique.InsertAtEnd(column.get(i));
			}
		}
		return unique;
	}
	
	//for constructor only i.e., for target point accurance
	public LinkedList<Integer> findOccurance(LinkedList<Double> unique, LinkedList<Record> list){
		///find occurance of every unique TP
		LinkedList<Integer> occurance= new LinkedList<Integer>();
		for(int i=0; i<unique.size(); i++) {
			int count=0;
			for(int j=0; j<list.size(); j++) {
				if(unique.get(i).compareTo(list.get(j).getT_point())==0) {
					count++;
				}
			}
			occurance.InsertAtEnd(count);
		}
		return occurance;
	}
	
	public double calEntropy(LinkedList<Integer> occurance) {
		int tot= 0;
		for(int i=0; i<occurance.size(); i++) {
			tot= tot+ occurance.get(i);
		}
		
		double ent=0;
		
		for(int i=0; i<occurance.size(); i++) {
			double o= occurance.get(i);
			double t= tot;
			double n= o/t;
			double log=0;
			if(n!=0.0) {
				log= log2(n);
			}
			
			
			ent= ent-((o/t) * (log));
			
		}
		
		return ent;
	}
	
	
	///pass the list of record and the column number of excel sheet on which information gain is to be calculated
	public Double calInfoGain(LinkedList<Record> list, int col) {
		LinkedList<Double> column= new LinkedList<Double>();
		for(int i=0; i<list.size(); i++) {
			column.InsertAtEnd(list.get(i).getPoints().get(col));
		}
		this.uniqueCP= fnidUniqueness(column);
		
		
		
		
		
		for(int i=0; i<this.uniqueCP.size(); i++) {
			LinkedList<Integer> occurance= new LinkedList<Integer>();
			for(int j=0; j<this.uniqueTP.size(); j++) {
				int count=0;
				for(int k=0; k<list.size(); k++) {
					if((list.get(k).getPoints().get(col).compareTo(this.uniqueCP.get(i)))==0 && list.get(k).getT_point()==this.uniqueTP.get(j)) {
						
						count++;
					}
				}
				occurance.InsertAtEnd(count);
			}
			this.occuraneCP.InsertAtEnd(occurance);
		}	
		
		//calculate occurance of every unique course point
		LinkedList<Integer> totOccofOneAtt= new LinkedList<Integer>();
		for(int i=0; i<this.occuraneCP.size(); i++) {
			
			int count=0;
			for(int j=0; j<this.occuraneCP.get(i).size(); j++) {
				count= count+this.occuraneCP.get(i).get(j);
			}
			totOccofOneAtt.InsertAtEnd(count);
		}
		//calculate total grand occurance of all grade points
		int grandOcc=0;
		for(int i=0; i<totOccofOneAtt.size(); i++) {
			grandOcc= grandOcc+totOccofOneAtt.get(i);
		}
		//calculate entropy now
		double totEnt=0;
		
		for(int i=0; i<this.uniqueCP.size(); i++) {
			double ent= calEntropy(this.occuraneCP.get(i));
			this.entropyCP.InsertAtEnd(ent);
			double o= totOccofOneAtt.get(i);
			double t= grandOcc;
			totEnt= totEnt+ ((o/t)*(ent));
		}
		//now calculate information gain
		double infoGain= this.entropyTP- totEnt;
		
		
		
		
		return infoGain;
		
	}
	

}
