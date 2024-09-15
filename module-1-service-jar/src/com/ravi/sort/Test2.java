package com.ravi.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class Test2 {

	public static void dumpProcessInfo(ProcessHandle p)
		 {
		 ProcessHandle.Info info=p.info();
		 System.out.println("Process Id:"+p.pid());
		 System.out.println("User: "+info.user().orElse(""));
		 System.out.println("Command: "+info.command().orElse(""));
		 System.out.println("Start Time: "+info.startInstant().orElse(Instant.now()).toString());
		 System.out.println("Total CPU Time Acquired: "+
				 	info.totalCpuDuration().orElse(Duration.ofMillis(0)).toMinutes()
				
				 );
		 System.out.println();
		 }

	public static void main(String[] args) throws Exception
		 {
		 Stream<ProcessHandle> allp=ProcessHandle.allProcesses().sorted(
				 (p1,p2) -> p2.info().totalCpuDuration().orElse(Duration.ofMillis(0)).
				 	compareTo(p1.info().totalCpuDuration().orElse(Duration.ofMillis(0)))
				 	);
		 allp.limit(100).forEach(ph->dumpProcessInfo(ph));
		 }
}


interface I1{
	void m1();
}

interface I2 {
	void m1();
}


class C1 implements I1,I2 {


	@Override
	public void m1() {
		// TODO Auto-generated method stub
		
	}
	
}
