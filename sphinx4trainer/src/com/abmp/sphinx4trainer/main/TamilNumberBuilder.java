/**
 * 
 */
package com.abmp.sphinx4trainer.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palanikrishnan
 *
 */
public class TamilNumberBuilder
{
	/**
	 * 
	 */
	public TamilNumberBuilder()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		List<String> listA = new ArrayList<String>();
		List<String> listB = new ArrayList<String>();
		List<String> listC = new ArrayList<String>();
		BufferedReader bufferedReaderA = new BufferedReader(new FileReader(new File("data/lm/lm_1/tamil_numbers_a.txt")));
		String readLineA = null;
		while (null != (readLineA = bufferedReaderA.readLine()))
		{
			listA.add(readLineA);
		}
		bufferedReaderA.close();
		BufferedReader bufferedReaderB = new BufferedReader(new FileReader(new File("data/lm/lm_1/tamil_numbers_b.txt")));
		String readLineB = null;
		while (null != (readLineB = bufferedReaderB.readLine()))
		{
			listB.add(readLineB);
		}
		bufferedReaderB.close();
		BufferedReader bufferedReaderC = new BufferedReader(new FileReader(new File("data/lm/lm_1/tamil_round_numbers.txt")));
		String readLineC = null;
		while (null != (readLineC = bufferedReaderC.readLine()))
		{
			listC.add(readLineC);
		}
		bufferedReaderC.close();
		int i = -1;
		for (String a : listA)
		{
			System.out.println(listC.get(++i));
			for (String b : listB)
			{
				System.out.println(a + " " + b);
			}
		}
	}
}
