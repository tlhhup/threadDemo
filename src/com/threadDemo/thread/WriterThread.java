package com.threadDemo.thread;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedWriter;

/**
 * 使用管道流
 * 
 * @author ping
 *
 */
public class WriterThread extends Thread {

	private PipedWriter writer;
	private BufferedWriter bufferedWriter;

	private final String[] books={"Struts2权威指南","RoR敏捷开发指南","基于J2E的Ajax宝典","轻量级J2E企业应用指南"};
	
	public WriterThread(PipedWriter writer) {
		this.writer = writer;
		this.bufferedWriter=new BufferedWriter(writer);
	}
	
	@Override
	public void run() {
		try {
			for(int i=0;i<1000;i++){
				bufferedWriter.write(books[i%4]);
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(writer!=null){
					writer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
