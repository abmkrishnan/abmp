/**
 * 
 */
package com.abmp.sphinx4trainer.main;

import java.io.File;
import java.io.IOException;
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
		String strFilename = "data/sample.wav";
		File outputFile = new File(strFilename);
		/* For simplicity, the audio data format used for recording
		   is hardcoded here. We use PCM 44.1 kHz, 16 bit signed,
		   stereo.
		*/
		AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0F, 16, 2, 4, 44100.0F, false);
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
		System.out.println("Recording...");
		/* And now, we are waiting again for the user to press ENTER,
		   this time to signal that the recording should be stopped.
		*/
		System.out.println("Press ENTER to stop the recording.");
		try
		{
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		/* Here, the recording is actually stopped.
		 */
		recorder.stopRecording();
		System.out.println("Recording stopped.");
	}
}
