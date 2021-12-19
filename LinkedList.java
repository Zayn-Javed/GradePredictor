package Project;

public class LinkedList <T> {

private Node<T> head;
	

	public LinkedList() {
		head= null;
	}
	
	public void swap(T i, T j) {
		Node<T> currNode= head;
		while(currNode!=null) {
			if(currNode.getData().equals(i)) {
				currNode.setData(j);
				break;
			}
			currNode= currNode.getNext();
		}
		currNode= head;
		while(currNode!=null) {
			if(currNode.getData().equals(j)) {
				currNode.setData(i);
				break;
			}
			currNode= currNode.getNext();
		}
	}
	
	public int size() {
		int size=0;
		Node<T> currNode= head;
		if(head!=null) {
			while(currNode!=null) {
				currNode= currNode.getNext();
				size++;
			}
		}
		return size;
	}
	
	public T get(int index) {
		int currInd= 0;
		Node<T> currNode= head;
		if(index<0) {
			System.out.println("Index is not valid");
			return null;
		}
		if(head !=null) {
			while(currNode!=null) {
				if(index==currInd) {
					return (T) currNode.getData();
				}
				currNode= currNode.getNext();
				currInd++;
			}
		}
		else {
			System.out.println("LinkedList is empty");
		}
		return null;
	}
	
	
	
	public void InsertAtStart(T value) {
		
		if(head!=null) {
			Node<T> newNode= new Node<T>(value);
			newNode.setNext(head);
			head= newNode;
		}
		else {
			Node<T> newNode= new Node(value);
			head= newNode;
		}
	}
	
	public void InsertAtEnd(T value) {
		if(head!=null) {
			Node currNode= head;
			while(currNode.getNext()!=null) {
				currNode= currNode.getNext();
			}
			Node<T> newNode= new Node<T>(value);
			currNode.setNext(newNode);
		}
		else {
			Node<T> newNode= new Node(value);
			head= newNode;
		}
	}
	
	public void InsertAfter(T value, T data) {
		if(head!=null) {
			Node currNode= head;
			while(currNode!=null && currNode.getData()!=value) {
				currNode= currNode.getNext();
			}
			if(currNode!=null) {
				Node<T> newNode= new Node<T>(data);
				newNode.setNext(currNode.getNext());
				currNode.setNext(newNode);
				
			}
			else {
				System.out.println("There is no such element in LinkList");
			}
		}
		else {
			Node<T> newNode= new Node<T>(data);
			head= newNode;
		}
	}
	
	
	public void DeleteFromStart() {
		if(head!=null) {
			Node deleteNode= head;
			head= deleteNode.getNext();
		}
		else {
			System.out.println("There is no element in LinkList");
		}
	}
	
	
	public void DeleteFromEnd() {
		if(head!=null) {
			Node prevNode= null;
			Node currNode=head;
			while(currNode.getNext()!=null) {
				prevNode= currNode;
				currNode= currNode.getNext();
			}
			prevNode.setNext(null);
		}
		else {
			System.out.println("There is no element in LinkList");
		}
	}
	
	
	public void DeleteAny(T data) {
		if(head!=null) {
			Node prevNode= null;
			Node currNode=head;
			while(currNode!=null && (!currNode.getData().equals(data))) {
				prevNode= currNode;
				currNode= currNode.getNext();
			}
			if(currNode!=null) {
				if(prevNode!=null) {
					prevNode.setNext(currNode.getNext());
				}
				else {
					head= currNode.getNext();
				}
			}
			else {
				System.out.println("There is no such element in LinkList");
			}
		}
		else {
			System.out.println("There is no element in LinkList");
		}
	}

	public void deleteAll() {
		this.head= null;
	}
	
}
