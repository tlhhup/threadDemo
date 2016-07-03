package com.threadDemo.thread;

import java.io.BufferedReader;
import java.io.PipedReader;

/**
 * 使用管道流
 * 
 * @author ping
 *
 */
public class ReaderThread extends Thread {

	private PipedReader reader;
	private BufferedReader bufferedReader;

	public ReaderThread(PipedReader reader) {
		this.reader=reader;
		this.bufferedReader=new BufferedReader(reader);
	}
	
	@Override
	public void run() {
		String buf=null;
		try {
			while((buf=bufferedReader.readLine())!=null){
				System.out.println(buf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null){
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
