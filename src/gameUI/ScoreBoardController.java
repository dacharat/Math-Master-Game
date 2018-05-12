package gameUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.Collection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import player.Scoreboard;
import users.Account;

public class ScoreBoardController extends Contoller {

	@FXML
	Label player1NameLabel;
	@FXML
	Label player2NameLabel;
	@FXML
	Label player3NameLabel;
	@FXML
	Label player4NameLabel;
	@FXML
	Label player5NameLabel;
	@FXML
	Label player1ScoreLabel;
	@FXML
	Label player2ScoreLabel;
	@FXML
	Label player3ScoreLabel;
	@FXML
	Label player4ScoreLabel;
	@FXML
	Label player5ScoreLabel;

	Scoreboard board = new Scoreboard();
	Account account = Account.getInstance();
	Label[] temp = new Label[5];
	Label[] temp2 = new Label[5];
	String[] name = new String[5];
	Integer[] score = new Integer[5];
	
	public void initialize() {
		board.calculadolaBoard();
		temp[0] = player1NameLabel;
		temp[1] = player2NameLabel;
		temp[2] = player3NameLabel;
		temp[3] = player4NameLabel;
		temp[4] = player5NameLabel;
		temp2[0] = player1ScoreLabel;	
		temp2[1] = player2ScoreLabel;	
		temp2[2] = player3ScoreLabel;	
		temp2[3] = player4ScoreLabel;	
		temp2[4] = player5ScoreLabel;
		
		int box = 0;
		HashMap<String, Integer> top5 = account.getTopplayer();
		
		for (String s : top5.keySet()) {
			name[box] = s;
			score[box] = top5.get(s);
//			player.add(s);
//			highscore.add(top5.get(s));
			box++;
		}
		
		for (int i = 0 ; i < 5; i++) {
			temp[i].setText(name[i]);
			temp2[i].setText(score[i]+"");
		}
	}
	
	public static void main(String[] args) {
		
	}
}
