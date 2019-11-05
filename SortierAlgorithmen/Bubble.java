import java.util.Arrays;

public class Bubble {

    public static void bubble(int[] a) {
		int temporary;
		for(int i = a.length - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(a[j] > a[j + 1]) {
					temporary = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temporary;
				}
			}
		}
    }

}
