import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class Test1 {

	public static void main(String[] args) {
		Tree head=new Tree(1);
		Tree tree2=new Tree(2);
		Tree tree3=new Tree(3);
		Tree tree4=new Tree(4);
		Tree tree5=new Tree(5);
		Tree tree6=new Tree(6);
		Tree tree7=new Tree(7);
		Tree tree8=new Tree(8);
		
		head.setL(tree2);
		head.setR(tree3);
		
		tree2.setR(tree4);
		
		tree3.setL(tree5);
		tree3.setR(tree6);
		
		tree5.setL(tree7);
		tree5.setR(tree8);
		
		System.out.println("====递归遍历====");
		printTree1(head);
		
		System.out.println("\n=====栈非递归遍历=====");
		printTree2(head);
		
		System.out.println("\n=====层次遍历自动换行=====");
		printTree3(head);
	}
	
	//队列实现逐层遍历
	public static void printTree3(Tree head){
		Queue<Tree> queueTree=new LinkedList<>();
		queueTree.add(head);
		Tree last,nlast;
		last=nlast=head;
		while(!queueTree.isEmpty()) {
			Tree node=queueTree.poll();
			
			System.out.print(node.getNum()+"\t");
			if(node.getL()!=null) {
				queueTree.add(node.getL());
				nlast=node.getL();
			}
			if(node.getR()!=null) {
				nlast=node.getR();
				queueTree.add(node.getR());
			}
			
			if(last==node) {
				last=nlast;
				System.out.println();
			}
		}
	}
	
	
	//递归实现的前序遍历
	public static void printTree1(Tree head){
		if(head==null)
			return;
		
		System.out.println(head.getNum());
		printTree1(head.getL());
		printTree1(head.getR());
	}
	
	//栈实现前序遍历
	public static void printTree2(Tree head) {
		Stack<Tree> stackTree=new Stack<>();
		stackTree.push(head);
		while(!stackTree.isEmpty()) {
			
			Tree node=stackTree.pop();
			System.out.println(node.getNum());
			if(node.getR()!=null) {
				stackTree.push(node.getR());
			}
			if(node.getL()!=null) {
				stackTree.push(node.getL());
			}
		}
	}
}


class Tree{
	private int num;
	private Tree l;
	private Tree r;
	private Tree p;
	
	Tree(int num){
		this.num=num;
		l=r=p=null;
	}
	
	public Tree getL() {
		return l;
	}

	public void setL(Tree l) {
		this.l = l;
		l.p=this;
	}

	public Tree getR() {
		return r;
	}

	public void setR(Tree r) {
		this.r = r;
		r.p=this;
	}

	public Tree getP() {
		return p;
	}


	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num=num;
	}
	
	
}

