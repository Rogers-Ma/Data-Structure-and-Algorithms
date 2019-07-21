import java.util.LinkedList;
import java.util.Queue;

class Tree {
	private int num;
	private Tree l;
	private Tree r;
	private boolean state;

	Tree() {
		num = 0;
		l = r = null;
		state = false;
	}

	Tree(int num) {
		this.num = num;
		l = r = null;
		state = true;
	}

	public Tree getL() {
		return l;
	}

	public void setL(Tree l) {
		this.l = l;
	}

	public Tree getR() {
		return r;
	}

	public void setR(Tree r) {
		this.r = r;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean s) {
		state = s;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		if (state)
			return String.valueOf(num) + "!";
		else
			return "#!";
	}

}

public class Test2 {

	public static void main(String[] args) {
		Tree head = new Tree(1);
		Tree tree2 = new Tree(2);
		Tree tree3 = new Tree(3);
		Tree tree4 = new Tree(4);
		Tree tree5 = new Tree(5);
		Tree tree6 = new Tree(6);
		Tree tree7 = new Tree(7);
		Tree tree8 = new Tree(8);

		head.setL(tree2);
		head.setR(tree3);

		tree2.setR(tree4);

		tree3.setL(tree5);
		tree3.setR(tree6);

		tree5.setL(tree7);
		tree5.setR(tree8);

		System.out.println("\n=====初始的树=====");
		printTree(head);

		System.out.println("\n=====前序序列化、反序列化====");
		printTree(Deserialize(serialize(head)));

		System.out.println("\n=====按层遍历序列化、反序列化====");
		String str=serialize1(head);
		printTree(deserialize1(str));
	}

	// 打印树
	public static void printTree(Tree head) {
		Queue<Tree> queue = new LinkedList<>();
		Tree last = head, nlast = head;
		queue.add(head);
		while (!queue.isEmpty()) {
			Tree node = queue.poll();
			System.out.print(node.getNum() + "\t");
			if (node.getL() != null) {
				queue.add(node.getL());
				nlast = node.getL();
			}
			if (node.getR() != null) {
				queue.add(node.getR());
				nlast = node.getR();
			}
			if (node == last) {
				last = nlast;
				System.out.println();
			}
		}
	}

	// 逐层遍历序列化
	public static String serialize1(Tree head) {
		Queue<Tree> queueTree = new LinkedList<>();
		queueTree.add(head);
		String str = "";
		while (!queueTree.isEmpty()) {
			Tree node = queueTree.poll();
			str += node.toString();
			if (node.getL() != null) {
				queueTree.add(node.getL());
			} else if (node.getState()) {
				queueTree.add(new Tree());
			}
			if (node.getR() != null) {
				queueTree.add(node.getR());
			} else if (node.getState()) {
				queueTree.add(new Tree());
			}
		}
		return str;
	}

	// 逐层反序列化
	public static Tree deserialize1(String preStr) {
		System.out.println(preStr);
		String[] str = preStr.split("!");
		Queue<String> queue = new LinkedList<>();
		for (String s : str)
			queue.add(s);
		Tree head=null;
		Queue<Tree> queueTree=new LinkedList<>();
		String s=queue.poll();
		head=new Tree(Integer.valueOf(s));
		queueTree.add(head);

		while(!queue.isEmpty()) {
			Tree node=queueTree.poll();
			String l=queue.poll();
			String r=queue.poll();
			if(!"#".equals(l)) {
				node.setL(new Tree(Integer.valueOf(l)));
				queueTree.add(node.getL());
			}
			if(!"#".equals(r)) {
				node.setR(new Tree(Integer.valueOf(r)));
				queueTree.add(node.getR());
			}
		}
		return head;
	}

	// 递归实现的前序遍历序列化
	public static String serialize(Tree head) {
		if (head == null)
			return "#!";
		String str = head.toString();
		str += serialize(head.getL());
		str += serialize(head.getR());
		return str;
	}

	// 前序反序列化
	public static Tree Deserialize(String preStr) {
		System.out.println(preStr);
		String[] str = preStr.split("!");
		Queue<String> queue = new LinkedList<>();
		for (String s : str)
			queue.add(s);
		return createTree(queue);
	}
	
	//前序反序列化递归
	public static Tree createTree(Queue<String> queue) {
		Tree head = null;
		String str = queue.poll();
		if (str.equals("#"))
			return null;
		head = new Tree(Integer.valueOf(str));
		head.setL(createTree(queue));
		head.setR(createTree(queue));
		return head;
	}
}
