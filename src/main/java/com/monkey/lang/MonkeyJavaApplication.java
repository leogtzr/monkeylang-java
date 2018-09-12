package com.monkey.lang;

import com.monkey.lang.repl.ReadEvalPrintLoop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonkeyJavaApplication {

	public static void main(final String[] args) {
		ReadEvalPrintLoop.start(System.in, System.out);
	}
}
