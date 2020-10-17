package tsi.too.exercise1;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import tsi.too.message_dialog.InputDialog;
import tsi.too.message_dialog.InputDialog.MultiLineDimension;
import tsi.too.message_dialog.MessageDialog;

public class InputController {
	
	private static InputController instance; 
	
	private final String DATE_REGEX = "(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))";
	private final String PHONE_REGEX = "([(][0-9]{2}[)]) ([0-9]?\\s)?[0-9]{4}-[0-9]{4}";
	
	private InputController() {}
	
	public static InputController getInstance() {
		synchronized (InputController.class) {
			if(instance == null)
				instance = new InputController();
			
			return instance;
		}
	}
	
	public void execute() {
		var input = InputDialog.showMultiLineInputDialog("Text", "Enter the text", MultiLineDimension.MEDIUM);
		MessageDialog.showTextMessage(Constants.REPORT, createReport(input));	
	}
	
	private String createReport(String source) {
		if(source == null) {
			return Constants.NOTHING_TO_SHOW;			
		}
			
		var charWithSpaces = source.length();
		var input = source.replaceAll(" +", " ");
		var charWithoutSpaces = input.replaceAll("\\s+","").length();
		var wordsCount = input.split(" ").length;
		
		var dates = getAllMatches(DATE_REGEX, input);
		var phones = getAllMatches(PHONE_REGEX, input);
		
		var message = new StringBuilder(String.format("%s: %d", Constants.NUMBER_OF_LINES, countLines(input)))
				.append(String.format("\n%s: %d" ,Constants.NUMBER_OF_CHARACTERS_WITH_SPACE, charWithSpaces))
				.append(String.format("\n%s: %d", Constants.NUMBER_OF_CHARACTERS_WITHOUT_SPACE, charWithoutSpaces))
				.append(String.format("\n%s: %d", Constants.NUMBER_OF_WORDS, wordsCount))
				.append(String.format("\n%s: %d\n", Constants.NUMBER_OF_PHONE_NUMBERS, phones.size()))				
				.append(String.join("\n", phones))
				.append(String.format("%s: %d\n", Constants.NUMBER_OF_DATES, dates.size()))
				.append(String.join("\n", dates));
		
		return message.toString();	
	}
	
	private List<String> getAllMatches(String regexExp, String from) {
		return Pattern.compile(regexExp)
                .matcher(from)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
	}

	private int countLines(String str){
		return str.split("\r\n|\r|\n").length;		   
	}	
}