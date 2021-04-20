package com.springboot.test.controller;

import com.springboot.test.model.Data;

public class ThreadRun extends Thread {
	public void run() {
		while(true) {
			try {
			    Data.que.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
