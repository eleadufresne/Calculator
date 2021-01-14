import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Button extends JButton{
	//fields
	private String text;
	//constructor
	public Button(String str) {
		super(str);
		this.text= str;
	}
	//formatting
	public void paintComponent(Graphics g) {
		//Background of the button
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp = new GradientPaint( 0, 0, new Color(1,0,7), 0, 15, new Color(41,43,77));
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		  
		//Text on button
		if(this.text=="C")
			g.setColor(Color.red);
		else
			g.setColor(Color.black);

	 	g.setFont(new Font("Arial", Font.BOLD, 25));
	 	g.drawString(this.text, getWidth()/2-5, getHeight()/2+8);
	}
}
