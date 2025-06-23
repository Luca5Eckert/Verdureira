package br.com.verdureiralucas.view;

import java.util.Scanner;

public class Terminal {

	public final Scanner input;

	public Terminal(Scanner input) {
		this.input = input;
	}

	public Scanner getInput() {
		return input;
	}

}
