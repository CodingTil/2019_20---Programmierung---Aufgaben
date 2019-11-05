public class Selection {

    public static void selection(int[] a) {
		int index_min, temporary;
        for(int i = 0; i < a.length - 1; i++) {
			index_min = i;
			for(int j = i + 1; j < a.length; j++) {
				if(a[index_min] > a[j]) {
					index_min = j;
				}
			}
			
			temporary = a[index_min];
			a[index_min] = a[i];
			a[i] = temporary;
		}
    }

}
