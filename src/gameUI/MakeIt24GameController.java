package gameUI;

import org.matheclipse.core.eval.ExprEvaluator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import makeIt24Game.MakeIt24;

public class MakeIt24GameController {

	@FXML
	Label resultLabel;
	@FXML
	Label correctLabel;
	@FXML
	Button openBracketButton;
	@FXML
	Button number1Button;
	@FXML
	Button number2Button;
	@FXML
	Button number3Button;
	@FXML
	Button number4Button;
	@FXML
	Button closingBracketButton;
	@FXML
	Button addOperationButton;
	@FXML
	Button minusOperationButton;
	@FXML
	Button multiplyOperationButton;
	@FXML
	Button divideOperationButton;
	@FXML
	Button deleteButton;
	@FXML
	Button clearButton;

	private MakeIt24 make24;
	private int bracket;
	private ExprEvaluator e;

	public void initialize() {
		number1Button.setOnAction(this::onNumberButtonClicked);
		number2Button.setOnAction(this::onNumberButtonClicked);
		number3Button.setOnAction(this::onNumberButtonClicked);
		number4Button.setOnAction(this::onNumberButtonClicked);

		addOperationButton.setOnAction(this::onOperationButtonClick);
		minusOperationButton.setOnAction(this::onOperationButtonClick);
		multiplyOperationButton.setOnAction(this::onOperationButtonClick);
		divideOperationButton.setOnAction(this::onOperationButtonClick);

		openBracketButton.setOnAction(this::onOperationButtonClick);
		closingBracketButton.setOnAction(this::onOperationButtonClick);

		deleteButton.setOnAction(this::onDeleteButtonClicked);
		clearButton.setOnAction(this::onClearButtonClicked);

		make24 = new MakeIt24();
		e = new ExprEvaluator();
		getAllNumber();
	}

	public void getAllNumber() {
		bracket = 1;
		resultLabel.setText("(");
		make24.getQuestion();
		number1Button.setText(make24.getNumber1() + "");
		number2Button.setText(make24.getNumber2() + "");
		number3Button.setText(make24.getNumber3() + "");
		number4Button.setText(make24.getNumber4() + "");

		number1Button.setVisible(true);
		number2Button.setVisible(true);
		number3Button.setVisible(true);
		number4Button.setVisible(true);
	}

	public void onOperationButtonClick(ActionEvent event) {
		Button b = (Button) event.getSource();
		String oldInput = resultLabel.getText();
		String newInput = b.getText();
		String output = oldInput + newInput;
		if (newInput.equals("("))
			bracket++;
		else if (newInput.equals(")")) {
			if (bracket > 0) {
				String calculate = oldInput.substring(oldInput.lastIndexOf("(") + 1);
				output = oldInput.substring(0, oldInput.lastIndexOf("(")) + e.evaluate(calculate);
				bracket--;
			} else
				newInput = "";
		}
		resultLabel.setText(output);
	}

	public void onNumberButtonClicked(ActionEvent event) {
		Button b = (Button) event.getSource();
		b.setVisible(false);
		String oldInput = resultLabel.getText();
		String newInput = b.getText();
		String output = oldInput + newInput;
		if (!number1Button.isVisible() && !number2Button.isVisible() && !number3Button.isVisible()
				&& !number4Button.isVisible()) {
			System.out.println("555555");
			if (bracket > 0) {
				for(int i = 0; i < bracket; i++) {
					output += ")";
				}
			}
			resultLabel.setText(output = e.evaluate(output) + "");
			if(output.equals("24"))
				correctLabel.setText("Corect!!");
			else
				correctLabel.setText("Wrong!!");
		} else {
			resultLabel.setText(output);
		}
	}

	public void onClearButtonClicked(ActionEvent event) {
		correctLabel.setText(make24.getSolution());
	}

	public void onDeleteButtonClicked(ActionEvent event) {
		getAllNumber();
	}
}
// ÷
