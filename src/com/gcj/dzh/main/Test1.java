package com.gcj.dzh.main;

public class Test1 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("a");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Test1()).start();
		new Thread(new Test2()).start();
	}

}
