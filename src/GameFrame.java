package pong;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame { 

	public GamePanel panel;

	public GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black); // color için awt library kullandık.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); // ?????????
		this.setVisible(true);
		this.setLocationRelativeTo(null); // ortalama ekranı
	}

}
