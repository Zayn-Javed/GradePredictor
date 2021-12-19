package Project;

import java.io.File;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.*;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class Processing {

	private LinkedList<Record> list= new LinkedList<Record>();
	private LinkedList<Tree<String>> tree= new LinkedList<Tree<String>>();
	private LinkedList<Record> training_dataset= new LinkedList<Record>();
	private LinkedList<Record> testing_dataset= new LinkedList<Record>();
	public LinkedList<Record> getList() {
		return list;
	}
	public void setList(LinkedList<Record> list) {
		this.list = list;
	}
	public LinkedList<Tree<String>> getTree() {
		return tree;
	}
	public void setTree(LinkedList<Tree<String>> tree) {
		this.tree = tree;
	}

	public void readFirst() {
		  
		try {
			FileInputStream file=new FileInputStream("Student’s Dataset.xlsx");
			XSSFWorkbook workbook=new XSSFWorkbook(file);
			Sheet sheet= workbook.getSheetAt(0);  
			DataFormatter form= new DataFormatter();
			Iterator<Row> itr= sheet.iterator();
			int row_n=0;
			boolean skip= false;
			Record rec= new Record();
			double cgpa=-1;
			int war=-1;
			String isWithDraw="z";
			while(itr.hasNext()) {
				
				//LinkedList<String> temp= new LinkedList<String>();
				Row row;
				if(!skip) {
					skip= true;
					row=itr.next();
					continue;
				}
				row=itr.next();
				Cell cell=row.getCell(0);
				String value=form.formatCellValue(cell);
		
				
				int sr= Integer.parseInt(value);
				
				if(sr==row_n) {
					
					//rec.setSr(sr);/////////
					cell=row.getCell(2);
					value=cell.getStringCellValue();//course
					for(int i=0; i<rec.getCourse().size(); i++) {
						if(value.equals(rec.getCourse().get(i))) {
							rec.getCourse().DeleteAny(value);
							rec.getPoints().DeleteAny(rec.getPoints().get(i));
						}
					}
					if(value.equals("CS201")) {
						rec.setTarget(value);
						cell=row.getCell(5);
						value=form.formatCellValue(cell);
						isWithDraw= value;
						cell=row.getCell(6);
						value=form.formatCellValue(cell);
						double point= Double.parseDouble(value);//grade pont
						rec.setT_point(point);
					}
					else {
						////
						
						rec.addCourse(value);
						cell=row.getCell(6);
						value=form.formatCellValue(cell);
						double point= Double.parseDouble(value);//grade pont
						rec.addPoint(point);
					}
					
					cell=row.getCell(8);
					value=form.formatCellValue(cell);
					cgpa= Double.parseDouble(value);//cgpa
					
					cell=row.getCell(9);
					value=form.formatCellValue(cell);
					war= Integer.parseInt(value);//warning
						
					
						
				}
				else {
					row_n++;
					if(rec.getTarget()!=null && (!isWithDraw.equals("W"))) {
						
						rec.setCgpa(cgpa);
						rec.setWarning(war);
						
						
						
						list.InsertAtEnd(rec);
					}
					
					rec= new Record();
					cell=row.getCell(2);
					value=cell.getStringCellValue();//course
					if(value.equals("CS201")) {
						rec.setTarget(value);
						cell=row.getCell(5);
						value=form.formatCellValue(cell);
						isWithDraw= value;
						cell=row.getCell(6);
						value=form.formatCellValue(cell);
						double point= Double.parseDouble(value);//grade pont
						rec.setT_point(point);
					}
					else {
						rec.addCourse(value);
						cell=row.getCell(6);
						value=form.formatCellValue(cell);
						double point= Double.parseDouble(value);//grade pont
						rec.addPoint(point);
					}
					cell=row.getCell(8);
					value=form.formatCellValue(cell);
					cgpa= Double.parseDouble(value);//cgpa
					rec.setCgpa(cgpa);
					cell=row.getCell(9);
					value=form.formatCellValue(cell);
					war= Integer.parseInt(value);//warning
					rec.setWarning(war);
				}

				
			}
			workbook.close();
			file.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	

	
	
	public Record FindLargest() {
		Record record= new Record();
		record= this.list.get(0);
		for(int i=0; i<this.list.size(); i++) {
			if(record.getCourse().size()<this.list.get(i).getCourse().size() && this.list.get(i).getWarning()==0) {
				record= this.list.get(i);
			}
			
		}
		/*for(int i=0; i<this.list.size(); i++) {
			System.out.println(this.list.get(i).getSr() +"     "  + this.list.get(i).getCgpa()  + "   ");
		}
		for(int i=0; i<this.list.size(); i++) {
			if(record.getCourse().size()==this.list.get(i).getCourse().size()) {
				for(int j=0; j<record.getCourse().size();j++) {
					System.out.print(this.list.get(i).getCourse().get(j) +"     " );
				}
			}
			System.out.println();
		}*
		System.out.println();
		System.out.println(record.getSr() +"     "  + record.getCgpa()  + "   ");*/
		/*for(int i=0; i<this.list.size(); i++) {
			for(int j=0; j<this.list.get(i).getCourse().size(); j++) {
				if(this.list.get(i).getCourse().get(j).equals("SS111")) {
					System.out.println("    /////// " +  this.list.get(i).getSr() );
				}
			}
			
		}*/
		//for(int i=0; i<this.list.size(); i++) {
		//	System.out.println(this.list.get(i).getPoints().size() +"    /////// " +  this.list.get(i).getCourse().size()+"    /////// " +  record.getCourse().size() );
		//}
		

		return record;
	}
	
	public void write() {
		XSSFWorkbook workbook= new XSSFWorkbook();
		XSSFSheet spreadsheet= workbook.createSheet("Sheet1");
		XSSFRow row;
		XSSFRow trow;
		int rowid = 0;
	//	DataFormatter form= new DataFormatter();
		trow = spreadsheet.createRow(rowid++);
		int cellid = 0;
		int topcol=0;//////////////////
		Record record= FindLargest();
		//row = spreadsheet.createRow(rowid++);
		Cell celltop = trow.createCell(topcol++);
		celltop.setCellValue("Sr. No");
		for(int i=0; i<record.getCourse().size();i++) {
			celltop = trow.createCell(topcol++);
			 //////////////////
			 celltop.setCellValue(record.getCourse().get(i));
			 
		}
		
		
		Row TopRrow = spreadsheet.createRow(rowid++);
		cellid = 0;
		int endingCell=0;
		Cell cell = TopRrow.createCell(cellid++);
		cell.setCellValue(1);
		for(int r=0; r<record.getCourse().size();r++) {
			
			cell = TopRrow.createCell(cellid++);
			endingCell= cellid;
			 //////////////////
			cell.setCellValue(record.getPoints().get(r));
			 
		}
		this.list.DeleteAny(record);
		
		
		Row[] rowA= new Row[this.list.size()];
		for(int i=0; i<this.list.size(); i++) {
			
			row = spreadsheet.createRow(rowid++);
			rowA[i]= row;
			cellid = 0;
			cell = row.createCell(cellid++);
			cell.setCellValue(i+2);
			LinkedList<Integer> temp= new LinkedList<Integer> ();
			
			for(int j=0; j<record.getCourse().size(); j++) {
				
				
				boolean flag= false;
				for(int l=0; l<this.list.get(i).getCourse().size(); l++) {
					if(this.list.get(i).getCourse().get(l).equals(record.getCourse().get(j))) {
						cell = row.createCell(cellid++);
						cell.setCellValue(this.list.get(i).getPoints().get(l));	
						temp.InsertAtEnd(l);
						flag= true;
						break;
					}
					
				}
				if(!flag) {
					cell = row.createCell(cellid++);
					cell.setCellValue(" ");	
				}	  
			}
			if(temp.size()<this.list.get(i).getCourse().size()) {
				for(int j=0; j<this.list.get(i).getCourse().size(); j++) {
					boolean flag= false;
					for(int k=0;  k<temp.size(); k++) {
						if(j==temp.get(k)) {
							flag=  true;
							break;
						}
					}
					if(!flag) {
						record.getCourse().InsertAtEnd(this.list.get(i).getCourse().get(j));//////////////''''''''
						
						celltop= trow.createCell(topcol++);/////////
						celltop.setCellValue(this.list.get(i).getCourse().get(j));///
						cell = row.createCell(cellid++);
						cell.setCellValue(this.list.get(i).getPoints().get(j));	
					}
				}
			}
		}
		
		cell = trow.createCell(topcol);
		cell.setCellValue("CGPA");	
		for(;endingCell<=topcol; endingCell++) {
			cell= TopRrow.createCell(endingCell);
			cell.setCellValue(" ");
		}
		cell= TopRrow.createCell(topcol);
		cell.setCellValue(record.getCgpa());
		
		for(int i=0; i<this.list.size(); i++) {		
			cell = rowA[i].createCell(topcol);
			cell.setCellValue(this.list.get(i).getCgpa());
		}
		topcol++;
		cell = trow.createCell(topcol);
		cell.setCellValue("Warning");
		cell= TopRrow.createCell(topcol);
		cell.setCellValue(record.getWarning());
		for(int i=0; i<this.list.size(); i++) {		
			cell = rowA[i].createCell(topcol);
			cell.setCellValue(this.list.get(i).getWarning());
		}
		topcol++;
		cell = trow.createCell(topcol);
		cell.setCellValue(this.list.get(0).getTarget());
		cell= TopRrow.createCell(topcol);
		cell.setCellValue(record.getT_point());
		for(int i=0; i<this.list.size(); i++) {	
			cell = rowA[i].createCell(topcol);
			cell.setCellValue(this.list.get(i).getT_point());
		}
		
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("Student’s Dataset1.xlsx"));
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readFinal() {
		this.list.deleteAll();
		FileInputStream file;
		try {
			LinkedList<String> tem= new LinkedList<String>();
			file = new FileInputStream("Student’s Dataset1.xlsx");
			XSSFWorkbook workbook=new XSSFWorkbook(file);
			Sheet sheet= workbook.getSheetAt(0);  
			DataFormatter form= new DataFormatter();
			Iterator<Row> itr= sheet.iterator();
			boolean skip= false;
			while(itr.hasNext()) {
				Record rec= new Record();
				Row row;
				
				if(!skip) {
					skip= true;
					row=itr.next();					
					for(int i=0; i<21; i++) {
						Cell cell=row.getCell(i+1);
						String value=form.formatCellValue(cell);
						tem.InsertAtEnd(value);	
					}
				}
				row=itr.next();
				for(int i=0; i<21; i++) {
					Cell cell=row.getCell(i+1);
					String value=form.formatCellValue(cell);
					if(!(value.equals(" "))) {
						double point= Double.parseDouble(value);
						rec.addPoint(point);
					}
					else {
						rec.addPoint(-1);
					}	
				}
				Cell cell=row.getCell(22);
				String value=form.formatCellValue(cell);
				double cgpa=  Double.parseDouble(value);
				
				rec.setCgpa(cgpa);
			//	rec.addPoint(cgpa);
			//	tem.InsertAtEnd("CGPA");
				cell=row.getCell(23);
				value=form.formatCellValue(cell);
				int war=  Integer.parseInt(value);
				rec.setWarning(war);
			//	rec.addPoint(war);
			//	tem.InsertAtEnd("Warning");
				
				//System.out.println(cgpa + "   " + war);
				cell=row.getCell(24);
				value=form.formatCellValue(cell);
				double t_point=  Double.parseDouble(value);
				rec.setT_point(t_point);
				rec.setCourse(tem);
				this.list.InsertAtEnd(rec);
				workbook.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<this.list.size(); i++) {
			this.list.get(i).addCourse("CGPA");
			this.list.get(i).addPoint(this.list.get(i).getCgpa());
			this.list.get(i).addCourse("Warning");
			this.list.get(i).addPoint(this.list.get(i).getWarning());
		}
	}
	
	
	public TreeNode<String> createTree(LinkedList<Record> list, LinkedList<Integer> cols) {
		
		LinkedList<Entropy> objList= new LinkedList<Entropy>();
		LinkedList<Double> gain= new LinkedList<Double>();
		for(int i=0;i<cols.size(); i++) {
			
			Entropy obj= new Entropy(list);
			
			double info= obj.calInfoGain(list, cols.get(i));
			objList.InsertAtEnd(obj);
			
			gain.InsertAtEnd(info);
		}
		int large= cols.get(0);/////to find index of column having large infogain
		int ind=0;
		
		double tem= gain.get(0);
		for(int i=0; i<gain.size(); i++) {
			
			if(tem<gain.get(i)) {
				tem= gain.get(i);
				large= cols.get(i);
				ind=i;
			}
		}
		
		TreeNode<String> node= new TreeNode<String>();
		
		node.setData(list.get(0).getCourse().get(large));
		
		LinkedList<Double> uniqueChild= new LinkedList<Double>();
		uniqueChild= objList.get(ind).getUniqueCP();
		LinkedList<TreeNode<String>> uniqueChildStr= new LinkedList<TreeNode<String>>();
		for(int i=0; i<uniqueChild.size(); i++) {
			
			TreeNode<String> nodetemp= new TreeNode<String>();
			
			String str= Double.toString(uniqueChild.get(i));
			nodetemp.setData(str);
			uniqueChildStr.InsertAtEnd(nodetemp);
		}
		node.setList(uniqueChildStr);
		LinkedList<Integer> tempCol= new LinkedList<Integer>();
		
		for(int i=0; i<cols.size(); i++) {
			if(cols.get(i)==large) {
				continue;
			}
			tempCol.InsertAtEnd(cols.get(i));
		}
		
		
		//loop for creating nodes against all unique grades
		
		for(int i=0; i<uniqueChild.size(); i++) {	
			if(objList.get(ind).getEntropyCP().get(i).compareTo(0.0)!=0 && tempCol.size()!=0) {
				System.out.println(cols.size());////////
				TreeNode<String> newNode;
				///refine data
				LinkedList<Record> refined= new LinkedList<Record>();
				
				for(int j=0; j<list.size(); j++) {
					if(list.get(j).getPoints().get(large).compareTo(uniqueChild.get(i))==0) {
						refined.InsertAtEnd(list.get(j));
					}
				}
				////end refining
				if(refined!=null) {
					
					newNode= createTree(refined, tempCol);
					node.getList().get(i).addNode(newNode);
				}
				
			}
			else if(objList.get(ind).getEntropyCP().get(i).compareTo(0.0)==0) {							/**///////////////
				TreeNode<String> res= new TreeNode<String>();
				objList.get(ind).getUniqueTP().get(i);
				System.out.println(objList.get(ind).getUniqueTP().get(i)+ " allah khyr");
				node.getList().get(i).addNode(res);
			} 									/**//**////////////
		}
		return node;
	}
	
	public void splitList() {
		
		for(int i=0; i<56; i++) {
			this.testing_dataset.InsertAtEnd(this.list.get(i));
		}
		for(int i=56; i<this.list.size(); i++) {
			this.training_dataset.InsertAtEnd(this.list.get(i));
		}
		
	}
	
	public void createDecisionTree(LinkedList<Integer> cols) {
		TreeNode<String> root= createTree(this.training_dataset, cols);
		Tree<String> tree= new Tree<String>();
		tree.setRoot(root);
		this.tree.InsertAtEnd(tree);
	}
	
	public LinkedList<Integer> selectFeature(LinkedList<Integer> feature) {
		for(int i=0; i<this.list.get(0).getPoints().size(); i++) {
			feature.InsertAtEnd(i);
		}
		return feature;
	}
}
