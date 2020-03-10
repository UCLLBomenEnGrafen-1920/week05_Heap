package ui;

import domain.BinaryMinHeap;
import domain.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.CheckedInputStream;

public class BinaryMinHeapDriver {

	public static void main(String[] args) {
		BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
		heap.addValue(3);
		heap.addValue(2);
		heap.addValue(1);
		heap.addValue(0);
		heap.addValue(-1);
		heap.addValue(-2);
		heap.addValue(-4);
		heap.addValue(2);
		heap.addValue(-7);
		heap.print();
		System.out.println();
		System.out.println("Kleinste waarde = " + heap.getMin());
		System.out.println();
		
//		for (int i = 1 ; i <= 5; i++){
//			System.out.println(heap.removeSmallest());
//			heap.print();
//		}
		
		System.out.println(heap.getPath(3));
		System.out.println(heap.getPath(2));
		System.out.println(heap.getPath(5));
		heap.geefDeelboom(-4).print();


		List<String> lijstGeldig = Arrays.asList(new String[]{"A", "D", "F", "H", "I", "J", "G", "K", "J"});
		List<String> lijstOnGeldig = Arrays.asList(new String[]{"A", "D", "F", "H", "I", "J", "G", "A", "J"});
		System.out.println(Checker.isValidArrayOfValues(lijstGeldig));
		System.out.println(Checker.isValidArrayOfValues(lijstOnGeldig));


	}

}
