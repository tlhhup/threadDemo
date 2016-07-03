package com.threadDemo.thread;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedWriter;

/**
 * ʹ�ùܵ���
 * 
 * @author ping
 *
 */
public class WriterThread extends Thread {

	private PipedWriter writer;
	private BufferedWriter bufferedWriter;

	private final String[] books={"Struts2Ȩ��ָ��","RoR���ݿ���ָ��","����J2E��Ajax����","������J2E��ҵӦ��ָ��"};
	
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
