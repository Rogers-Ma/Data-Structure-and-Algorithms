/**
 * �����㷨
 * 
 * @author mzw
 */

public class Test3 {
	public static void main(String[] args) {
		int n = 1000;
		int array[] = createArray(n);
		System.out.println("��ʼ����");
		printArray(array);

		System.out.println("ð������");
		printArray(bubbleSort(array.clone()));

		System.out.println("ѡ������");
		printArray(selectSort(array.clone()));

		System.out.println("��������");
		printArray(insertSort(array.clone()));

		System.out.println("�鲢����");
		printArray(mergeSort(array.clone()));
		
		System.out.println("��������");
		printArray(quickSort(array.clone()));
		
		System.out.println("������");
		printArray(heapSort(array.clone()));
		
		System.out.println("ϣ������");
		printArray(shellSort(array.clone()));
		
	}

	/**
	 * ����һ������Ϊn���������
	 * 
	 * @param n
	 * @return
	 */
	public static int[] createArray(int n) {
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = (int) (Math.random() * 10000) + 1;
		}
		return a;
	}

	public static void printArray(int[] array) {
		System.out.print("{ ");
		for (int a : array)
			System.out.print(a + " ");
		System.out.println("}");
	}

	/**
	 * ð������ O(n^2)
	 * 
	 * @param array
	 * @return
	 */
	public static int[] bubbleSort(int[] array) {

		boolean flag;
		for (int i = 0; i < array.length; i++) {
			flag = true;
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					if (flag)
						flag = false;
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
			if (flag)
				break;
			else
				flag = true;
		}
		return array;
	}

	/**
	 * ѡ������ O(n^2)
	 * 
	 * @param array
	 * @return
	 */
	public static int[] selectSort(int[] array) {

		for (int i = 0; i < array.length - 1; i++) {
			int max = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[max])
					max = j;
			}
			int temp = array[max];
			array[max] = array[i];
			array[i] = temp;
		}

		return array;
	}

	/***
	 * �������� O(n^2)
	 * 
	 * @param array
	 * @return
	 */
	public static int[] insertSort(int[] array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i; j >= 0 && array[j] > array[j + 1]; j--) {
				int temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
			}
		}
		return array;
	}

	/***
	 * �鲢����
	 * @param r
	 * @param r1
	 * @param s
	 * @param m
	 * @param t
	 */
	public static void merge(int r[], int r1[], int s, int m, int t) {
		int i = s, j = m + 1, k = s;
		while (i <= m && j <= t) {
			if (r[i] <= r[j]) 
				r1[k++] = r[i++];
			 else 
				r1[k++] = r[j++];
			
		}
		//System.out.println(i+" ��ǰI��"+m+"  H: "+j);
		while (i <= m) {
			r1[k++] = r[i++];
		}
		
		while (j <= t) {
			r1[k++] = r[j++];
		}

	}

	/***
	 * һ��鲢����
	 * @param r
	 * @param r1
	 * @param n
	 * @param h
	 */
	public static void mergePass(int r[], int r1[], int n, int h) {
		int i = 0;
		while (i <= n - (2 * h)+1) {
			merge(r, r1, i, i + h - 1, i + 2 * h - 1);
			i += 2 * h;
		}
		//System.out.println("��ǰI��"+i+"  H: "+h);
		if (i < n - h + 1)
			merge(r, r1, i, i + h - 1, n);
		
		else
			while (i <= n) {
				r1[i] = r[i++];
			}
	}

	/**
	 * �鲢����ķǵݹ�ʵ�֣��Ե����ϣ�Ч�ʱȵݹ��Ըߣ��ɶ����Բ�
	 * 
	 * @param r
	 * @param r1
	 * @param n
	 */
	public static int[] mergeSort(int r[]) {
		int h = 1;// ��ʼʱ����������
		int r1[]=new int[r.length];
		while (h < r.length) {
			mergePass(r, r1, r.length-1, h);
			h = h * 2;
			mergePass(r1, r, r.length-1, h);
			h = h * 2;
		}
		return r;
	}
	
	
	/***
	 * ��������ݹ�ʵ��
	 * @param a
	 * @param first
	 * @param end
	 */
	public static void partition(int a[],int first,int end) {
		int i=first;
		int j=end;
		while(i<j) {
			while(i<j) {
				if(a[i]>a[j]) {
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
					i++;
					break;
				}
				j--;
			}
			while(i<j) {
				if(a[i]>a[j]) {
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
					j--;
					break;
				}
				i++;
			}
		}
		if(i-1>first)
			partition(a,first,i-1);
		if(i+1<end)
			partition(a,i+1,end);
	}
	
	/***
	 * �������򷵻�����
	 * @param a
	 * @return
	 */
	public static int [] quickSort(int[] a) {
		partition(a, 0,a.length-1);
		return a;
	}
	
	
	/***
	 * ɸѡ��������
	 * @param a	������
	 * @param k ��ָ��
	 * @param m	������βָ��
	 */
	public static void sift(int a[],int k,int m) {
		int i=k,j=2*i+1;
		int temp;
		while(j<=m) {
			if(j<m&&a[j]<a[j+1])
				j++;
			if(a[i]>a[j])
				break;
			else {
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				i=j;
				j=2*i+1;
			}
		}
	}
	
	
	/***
	 * ������
	 * @param a
	 * @return
	 */
	public static int[] heapSort(int a[]) {
		int n=a.length-1;
		
		//���������
		for(int i=n/2;i>=0;i--) {
			sift(a,i,n);
		}
		//�����е�Ԫ��������������
		for(int i=0;i<n;i++) {
			int temp=a[0];
			a[0]=a[n-i];
			a[n-i]=temp;
			sift(a,0,n-i-1);
		}
		return a;
	}
	
	/**
	 * ϣ������
	 * @param array
	 * @return
	 */
	public static int[] shellSort(int array[]) {
		int n=array.length-1;
		for(int d=n/2;d>=1;d=d/2) {
			for(int i=d;i<=n;i++) {
				int temp=array[i];
				int j;
				for(j=i-d;j>=0&&array[j]>temp;j=j-d) {
					array[j+d]=array[j];
				}
				array[j+d]=temp;
			}
		}
		return array;
	}
	
}
