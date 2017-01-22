package com.test.threads;

public class TestMainThreads {

	public TestMainThreads() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

	    CompteEnBanque cb = new CompteEnBanque();

	    Thread t = new Thread(new RunImpl(cb, "Cysboy"));
	    Thread t2 = new Thread(new RunImpl(cb, "Zéro"));
	    t.start();
	    t2.start();
	}
		
		
/*		TestThread t = new TestThread("A");
	    TestThread t2 = new TestThread("  B", t);
	    
	    try {
	      Thread.sleep(1000);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    System.out.println("statut du thread " + t.getName() + " = " + t.getState());
	    System.out.println("statut du thread " + t2.getName() + " = " +t2.getState());                
	    
	}
*/
}
