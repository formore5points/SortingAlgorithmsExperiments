public class SortingClass {
//------------------HEAP SORT----------------------------
	public void maxHeapify(int[] A, int n, int i) {
		int Left = (2 * i) + 1;
		int Right = (2 * i) + 2;
		int Largest = i;
		int Temp;
		if (Left < n && A[Left] > A[Largest])
			Largest = Left;
		if (Right < n && A[Right] > A[Largest])
			Largest = Right;
		if (Largest != i) {
			Temp = A[i];
			A[i] = A[Largest];
			A[Largest] = Temp;
			maxHeapify(A, n, Largest);
		}
	}

	public void buildMaxHeap(int[] A, int n) {
		n = A.length;
		for (int i = (n / 2) - 1; i >= 0; i--)
			maxHeapify(A, n, i);
	}

	public void HEAPSORT(int[] A) {
		int n = A.length;
		buildMaxHeap(A, n);
		for (int i = n - 1; i >= 0; i--) {
			int Temp = A[i];
			A[i] = A[0];
			A[0] = Temp;
			maxHeapify(A, i, 0);
		}
	}
//------------------- QUICK SORT (FIRST)------------------------
	public void quickSortFirst(int[] A, int First, int Last) {
		int Pivot;
		if (First < Last) {
			Pivot = partitionFirst(A, First, Last);
			quickSortFirst(A, First, Pivot - 1);
			quickSortFirst(A, Pivot + 1, Last);
		}
	}

	public int partitionFirst(int[] A, int First, int Last) {
		int Pivot = A[First];
		int Left = First + 1;
		int Right = First + 1;

		while (Right <= Last) {
			if (A[Right] < Pivot) {
				int Temp = A[Left];
				A[Left] = A[Right];
				A[Right] = Temp;
				Left++;
			}
			Right++;
		}
		int Temp = A[First];
		A[First] = A[Left - 1];
		A[Left - 1] = Temp;
		return Left - 1;
	}
	//------------------- QUICK SORT (RANDOM)------------------------
	public void quickSortRandom(int[] A, int First, int Last) {
		if (First < Last) {
			int Pivot = partitionRandom(A, First, Last);
			quickSortRandom(A, First, Pivot - 1);
			quickSortRandom(A, Pivot + 1, Last);
		}
	}

	public int partitionRandom(int[] A, int First, int Last) {
		int randomEndIndex = numberBetweenRandom(First, Last);
		swap(A, Last, randomEndIndex);
		return partitionRandom2(A, First, Last);
	}

	public int partitionRandom2(int[] A, int First, int Last) {
		int pivotValue = A[Last];
		int pivotIndex = First;

		for (int j = First; j < Last; j++) {
			if (A[j] <= pivotValue) {
				swap(A, pivotIndex, j);
				pivotIndex = pivotIndex + 1;
			}
		}
		swap(A, pivotIndex, Last);
		return pivotIndex;
	}

	public int numberBetweenRandom(int startNumber, int endNumber) {
		return (int) Math.floor(Math.random() * (endNumber - startNumber + 1) + startNumber);
	}

	public void swap(int[] array, int firstIndex, int secondIndex) {
		int temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}
	//------------------- QUICK SORT (MOFMLE)------------------------
	public void quickSortMOFMLE(int[] A, int First, int Last) {
		int Pivot;
		if (First < Last) {
			Pivot = partitionMOFMLE(A, First, Last);
			quickSortMOFMLE(A, First, Pivot - 1);
			quickSortMOFMLE(A, Pivot + 1, Last);
		}
	}

	public int partitionMOFMLE(int[] A, int First, int Last) {

		int[] FML = new int[3];
		FML[0] = A[First];
		int Mid = (First + Last) / 2;
		FML[1] = A[Mid];
		FML[2] = A[Last];
		HEAPSORT(FML);
		if (First == FML[1]) {
			swap(A, First, Last);
		} else if (Mid == FML[1]) {
			swap(A, Mid, Last);
		} else {
			swap(A, Last, Last);
		}

		int pivotValue = A[Last];
		int pivotIndex = First;

		for (int j = First; j < Last; j++) {
			if (A[j] <= pivotValue) {
				swap(A, pivotIndex, j);
				pivotIndex = pivotIndex + 1;
			}
		}
		swap(A, pivotIndex, Last);
		return pivotIndex;

	}

	public void QUICKSORT(int[] A, String pivotType) {
		if (pivotType.equals("FirstElement")) {
			quickSortFirst(A, 0, A.length - 1);
		} else if (pivotType.equals("RandomElement")) {
			quickSortRandom(A, 0, A.length - 1);
		} else if (pivotType.equals("MidOfFirstMidLastElement")) {
			quickSortMOFMLE(A, 0, A.length - 1);
		}

	}
//--------------------DUAL PIVOT QUICK SORT----------------
	public void DUALPIVOTQUICKSORT(int[] a, int left, int right, int div) {
		int len = right - left;
		if (len < 27) {
			for (int i = left + 1; i <= right; i++) {
				for (int j = i; j > left && a[j] < a[j - 1]; j--) {
					swap(a, j, j - 1);
				}
			}
			return;
		}
		int third = len / div;

		int m1 = left + third;
		int m2 = right - third;
		if (m1 <= left) {
			m1 = left + 1;
		}
		if (m2 >= right) {
			m2 = right - 1;
		}
		if (a[m1] < a[m2]) {
			swap(a, m1, left);
			swap(a, m2, right);
		} else {
			swap(a, m1, right);
			swap(a, m2, left);
		}

		int pivot1 = a[left];
		int pivot2 = a[right];

		int less = left + 1;
		int great = right - 1;

		for (int k = less; k <= great; k++) {
			if (a[k] < pivot1) {
				swap(a, k, less++);
			} else if (a[k] > pivot2) {
				while (k < great && a[great] > pivot2) {
					great--;
				}
				swap(a, k, great--);
				if (a[k] < pivot1) {
					swap(a, k, less++);
				}
			}
		}

		int dist = great - less;
		if (dist < 13) {
			div++;
		}
		swap(a, less - 1, left);
		swap(a, great + 1, right);

		DUALPIVOTQUICKSORT(a, left, less - 2, div);
		DUALPIVOTQUICKSORT(a, great + 2, right, div);

		if (dist > len - 13 && pivot1 != pivot2) {
			for (int k = less; k <= great; k++) {
				if (a[k] == pivot1) {
					swap(a, k, less++);
				} else if (a[k] == pivot2) {
					swap(a, k, great--);
					if (a[k] == pivot1) {
						swap(a, k, less++);
					}
				}
			}
		}
		if (pivot1 < pivot2) {
			DUALPIVOTQUICKSORT(a, less, great, div);
		}
	}
//--------------------INTRO SORT---------------------------
	public void INTROSORT(int[] arrayToSort){		
		int depth = ((int) Math.log(arrayToSort.length))*2;
		introSort(arrayToSort, depth, 0, arrayToSort.length-1);
}
	
	public void introSort(int[] arrayToSort, int depth, int start, int end){
		int length = arrayToSort.length;
		if(length <= 1){
			return;
		}else if(depth == 0){
			HEAPSORT(arrayToSort);
		}else{
			if(start >= end)
				return;
			int index =  partitionFirst(arrayToSort, start, end);
			introSort(arrayToSort, depth-1, start, index-1);
			introSort(arrayToSort, depth-1, index, end);
		}
}
	
}
