package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.AppManager;
import view.CanvasPanel;
import view.MainPanel;
import model.Shape;

public class CanvasPanelController{
	
	private MainPanel mainPanel;
	private MenuPanelController menuController;
	private CanvasPanel canvasPanel;
	private Shape shapeObj;
	private Color color;
	private boolean empty;
	private int x,y,x1, y1, x2, y2, width, height, red, green, blue, tempx;
	private Shape shape;
	private String clicked;

	
	public CanvasPanelController()
	{
		mainPanel = AppManager.createAppManager().getMainPanel();
		menuController = AppManager.createAppManager().getMenuPanelController();
		canvasPanel = AppManager.createAppManager().getCanvasPanel();
		
		canvasPanel.pressedMousedListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();
				System.out.println(x1+ " : "+y1);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				x2 = e.getX();
				y2 = e.getY();
				System.out.println(x2+" : "+y2);
				clickedMenu();
				
			}
			
		});
		
	}

	public void clickedMenu()
	{
		clicked= menuController.getClicked();
		System.out.println(clicked);
	
		if(clicked.equals("select"))
		{
			// move
			for(int i=0; i<mainPanel.shapeVec.size(); i++)
			{
				Shape temp = mainPanel.shapeVec.get(i);
				
				if(temp.getX()<=x1 && temp.getWidth() + temp.getX()>= x1 )
				{
					if(temp.getY()<y1 && temp.getHeight() + temp.getY() >= y1)
					{
						x = x1>x2 ? x1-x2 : x2-x1;
						y = y1>y2 ? y1-y2 : y2-y1;
						temp.setX(temp.getX()+x);
						temp.setY(temp.getY()+y);
						break;
					}
				}
			}
		}
		else if(clicked.equals("rectangle") || clicked.equals("circle"))
		{
			width = x1>x2 ? x1 - x2: x2 - x1;
			height = y1>y2? y1-y2 : y2-y1;
			x = x1>x2 ? x2: x1;
			y = y1>y2? y2 : y1; 
			shape = new Shape(clicked, x, y, width, height);
			mainPanel.shapeVec.addElement(shape);
			
		}
		else if(clicked.equals("line"))
		{
			width = x1>x2 ? x1 - x2: x2 - x1;
			height = y1>y2? y1-y2 : y2-y1;
			x = x1;
			y = y1>y2? y2 : y1; 
			shape = new Shape(clicked, x, y, width, height);
			mainPanel.shapeVec.addElement(shape);
		}
		else if(clicked.equals("erase"))
		{
			
		}
		else if(clicked.equals("clear"))
		{
			mainPanel.shapeVec.removeAllElements();
			
		}
		canvasPanel.repaint();
		menuController.setClicked("select");
		
		
		
	}

}
