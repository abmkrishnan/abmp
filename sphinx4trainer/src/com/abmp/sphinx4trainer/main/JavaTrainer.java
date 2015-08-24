/**
 * 
 */
package com.abmp.sphinx4trainer.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * @author Palanikrishnan
 *
 */
public class JavaTrainer
{
	/**
	 * 
	 */
	public JavaTrainer()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		JavaTrainer javaTrainer = new JavaTrainer();
		javaTrainer.train();
	}

	public void train()
	{
		try
		{
			String setNo = "set_1";
			int seq = 0;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("data/sentences_list.txt")));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("data/sets/" + setNo + "/fileids.txt")));
			BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(new File("data/sets/" + setNo + "/transcription.txt")));
			String sentece = null;
			while (null != (sentece = bufferedReader.readLine()))
			{
				String ser = "";
				if(seq < 10)
				{
					ser = "0000" + (++seq);
				}
				else if(seq < 100)
				{
					ser = "000" + (++seq);
				}
				else if(seq < 1000)
				{
					ser = "00" + (++seq);
				}
				else if(seq < 10000)
				{
					ser = "0" + (++seq);
				}
				else
				{
					ser = "" + (++seq);
				}
				System.out.println(ser + ": " + sentece);
				System.out.println("Press ENTER to start the recording.");
				Scanner s = new Scanner(System.in);
				s.nextLine();
				recordWav(setNo, ser);
				bufferedWriter.append(ser).append("\n");
				bufferedWriter2.append("<S> " + sentece + " </S> (" + ser + ")").append("\n");
			}
			bufferedReader.close();
			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedWriter2.flush();
			bufferedWriter2.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void recordWav(String dir, String ser)
	{
		String strFilename = "data/sets/" + dir + "/" + ser + ".wav";
		File outputFile = new File(strFilename);
		/* For simplicity, the audio data format used for recording
		   is hardcoded here. We use PCM 44.1 kHz, 16 bit signed,
		   stereo.
		*/
		int channels = 1; // default 2
		float sampleRate = 16000.0F; // default 44100.0F
		AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, channels, channels * 2, sampleRate, false);
		/* Now, we are trying to get a TargetDataLine. The
		   TargetDataLine is used later to read audio data from it.
		   If requesting the line was successful, we are opening
		   it (important!).
		*/
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
		TargetDataLine targetDataLine = null;
		try
		{
			targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
			targetDataLine.open(audioFormat);
		}
		catch (LineUnavailableException e)
		{
			System.err.println("unable to get a recording line");
			e.printStackTrace();
			System.exit(1);
		}
		/* Again for simplicity, we've hardcoded the audio file
		   type, too.
		*/
		AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;
		/* Now, we are creating an SimpleAudioRecorder object. It
		   contains the logic of starting and stopping the
		   recording, reading audio data from the TargetDataLine
		   and writing the data to a file.
		*/
		SimpleAudioRecorder recorder = new SimpleAudioRecorder(targetDataLine, targetType, outputFile);
		recorder.start();
		/* And now, we are waiting again for the user to press ENTER,
		   this time to signal that the recording should be stopped.
		*/
		System.out.println("Press ENTER to stop the recording.");
		try
		{
			Scanner s = new Scanner(System.in);
			s.nextLine();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		/* Here, the recording is actually stopped.
		 */
		recorder.stopRecording();
		System.out.println("Recording stopped.");
	}
}
