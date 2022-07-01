import java.util.Arrays;
import java.util.Collections;

public class test {

	public static void main(String[] args) {
		Integer[] arr = new Integer[] {3, 4, 2, 1, 1};
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		for(int n : arr)
			System.out.println(n);
	}

}
