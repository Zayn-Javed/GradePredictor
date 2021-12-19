package Project;

public class Record {

	/*private int sr;
	public int getSr() {
		return sr;
	}

	public void setSr(int sr) {
		this.sr = sr;
	}*/

	
	
	
	private LinkedList<String> course;
	private LinkedList<Double> points;
	private double cgpa;
	private int warning;
	private String target;
	private double t_point;
	
	public Record(LinkedList<String> course, LinkedList<Double> points, double cgpa, int warning, String target, double t_point) {
		super();
		this.course = course;
		this.points = points;
		this.cgpa = cgpa;
		this.warning = warning;
		this.target = target;
		this.t_point = t_point;
	}
	
	public Record() {
		course= new LinkedList<String>();
		points= new LinkedList<Double>();
	}

	public LinkedList<String> getCourse() {
		return course;
	}

	public void setCourse(LinkedList<String> course) {
		this.course = course;
	}

	public LinkedList<Double> getPoints() {
		return points;
	}

	public void setPoints(LinkedList<Double> points) {
		this.points = points;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public int getWarning() {
		return warning;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public double getT_point() {
		return t_point;
	}

	public void setT_point(double t_point) {
		this.t_point = t_point;
	}
	
	public void addCourse(String course) {
		this.course.InsertAtEnd(course);
	}
	
	public void addPoint(double point) {
		this.points.InsertAtEnd(point);
	}
	
}
