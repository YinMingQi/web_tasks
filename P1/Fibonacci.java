
public class Fibonacci {
	public static void main(String[] args){
		long a[]=new long[101];
		a[0]=0;
		a[1]=1;
		System.out.println(" "+a[1]);
		for(int i=2;i<101;i++){
			a[i]=a[i-1]+a[i-2];
			System.out.println(" "+a[i]);
		}
	}
		
}
