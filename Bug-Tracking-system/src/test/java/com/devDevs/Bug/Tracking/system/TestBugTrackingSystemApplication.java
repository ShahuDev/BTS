package com.devDevs.Bug.Tracking.system;

import org.springframework.boot.SpringApplication;

public class TestBugTrackingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(BugTrackingSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
