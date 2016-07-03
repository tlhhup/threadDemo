package com.threadDemo.test;

import java.io.PipedReader;
import java.io.PipedWriter;

import com.threadDemo.thread.ReaderThread;
import com.threadDemo.thread.WriterThread;

public class PipeTest {

	public static void main(String[] args) {
		PipedReader reader=null;
		PipedWriter writer=null;
		
		try {
			reader=new PipedReader();
			writer=new PipedWriter();
			//将输出流连接到输入流上
			writer.connect(reader);
			
			new WriterThread(writer).start();
			new ReaderThread(reader).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
