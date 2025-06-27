package br.com.verdureiralucas;

import java.util.Scanner;

import br.com.verdureiralucas.view.MenuItemGeral;
import br.com.verdureiralucas.view.MenuManager;
import br.com.verdureiralucas.view.MenuProvider;

public class VerdureiraAplication {

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			MenuManager menuManager = new MenuManager(new MenuProvider(new MenuItemGeral(input)));
			menuManager.chamarMenu();
		}

	}
}
