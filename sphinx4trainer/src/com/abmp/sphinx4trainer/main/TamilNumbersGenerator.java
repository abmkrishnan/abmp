/**
 * 
 */
package com.abmp.sphinx4trainer.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palanikrishnan
 *
 */
public class TamilNumbersGenerator
{
	/**
	 * 
	 */
	public TamilNumbersGenerator()
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
		BufferedReader bufferedReaderA = new BufferedReader(new FileReader(new File("data/lm/lm_3/tamil_numbers_a.txt")));
		String readLineA = null;
		while (null != (readLineA = bufferedReaderA.readLine()))
		{
			listA.add(readLineA);
		}
		bufferedReaderA.close();
		BufferedReader bufferedReaderB = new BufferedReader(new FileReader(new File("data/lm/lm_3/tamil_numbers_b.txt")));
		String readLineB = null;
		while (null != (readLineB = bufferedReaderB.readLine()))
		{
			listB.add(readLineB);
		}
		bufferedReaderB.close();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("data/lm/lm_3/tamil_numbers.sent"), true));
		for (String a : listA)
		{
			bufferedWriter.append(a).append("\r\n");
			System.out.println(a);
			for (String b : listB)
			{
				System.out.println(a + " " + b);
				bufferedWriter.append(a + " " + b).append("\r\n");
			}
		}
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}
