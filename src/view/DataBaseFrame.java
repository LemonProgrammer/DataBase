package view;

import java.awt.Color;

import javax.swing.JFrame;

import controller.AppController;


public class DataBaseFrame extends JFrame
	{
		
		private DataBasePanel basePanel;
		private AppController baseController;
		
		public DataBaseFrame(AppController baseController)
			{
				this.baseController = baseController;
				basePanel = new DataBasePanel(baseController);
				setupFrame();
			}

		private void setupFrame()
			{
				this.setContentPane(basePanel);
				this.setSize(400, 400);
				this.setVisible(true);
				
			}
	}
