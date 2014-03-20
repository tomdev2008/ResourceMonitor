package com.gcj.dzh.main;

public class Test2 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("b");
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

	}

}
