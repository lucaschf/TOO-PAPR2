package tsi.too;

import java.util.Arrays;
import java.util.List;

import tsi.too.excercise2.controller.MenuController;
import tsi.too.exercise1.InputController;
import tsi.too.message_dialog.InputDialog;

public class Application {

	private static final String EXCERCISE_ONE = "Excercicio 1";
	private static final String EXCERCISE_TWO = "Excercicio 2";
	private static final String EXIT = "Sair";
	private static final String PAPR_2 = "PAPR2";
	
	public static void main(String[] args) {
		final String chooseAnOption = "Escolha uma das opções";
		final List<String> OPTIONS = Arrays.asList(
				EXCERCISE_ONE,
				EXCERCISE_TWO
		);
		
		var app = new Application();
		InputDialog.showMenuDialog(PAPR_2, chooseAnOption, OPTIONS, EXIT, app::execute);
	}
	
	private void execute(String action) {
		if(action == null)
			System.exit(0);
		
		switch (action) {			
			case EXCERCISE_ONE:
				InputController.getInstance().execute();
				break;
			case EXCERCISE_TWO:
				MenuController.getInstance().Menu();
				break;
			default:
				System.exit(0);
				break;
			}
	}
}
