/*
 *  Copyright (C) 2010-2013 JPEXS
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.gui;

import com.jpexs.decompiler.flash.Main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * Dialog showing loading of SWF file
 *
 * @author JPEXS
 */
public class LoadingDialog extends JFrame implements ImageObserver {

   private JLabel loadingLabel = new JLabel("", JLabel.CENTER);
   private LoadingPanel loadingPanel;
   String load = "Loading, please wait...";
   JProgressBar progressBar = new JProgressBar(0, 100);

   public void setDetail(String d) {
      loadingLabel.setText("<html><center>" + load + "<br>" + d + "</center></html>");
      loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
   }

   public void setPercent(int percent) {
      progressBar.setValue(percent);
      progressBar.setVisible(true);
   }

   public void hidePercent() {
      if (progressBar.isVisible()) {
         progressBar.setVisible(false);
      }
   }

   /**
    * Constructor
    */
   public LoadingDialog() {
      setResizable(false);
      setTitle(Main.shortApplicationVerName);
      setSize(250, 150);
      setLayout(new BorderLayout());

      loadingPanel = new LoadingPanel(50, 50);
      loadingPanel.setPreferredSize(new Dimension(100, 100));
      add(loadingPanel, BorderLayout.WEST);
      JPanel pan = new JPanel();
      pan.setLayout(null);
      pan.setPreferredSize(new Dimension(120, 150));
      loadingLabel.setBounds(0, 20, 125, 50);
      progressBar.setBounds(0, 70, 125, 25);
      pan.add(loadingLabel);
      pan.add(progressBar);
      add(pan, BorderLayout.CENTER);
      progressBar.setVisible(false);
      progressBar.setStringPainted(true);
      //progressBar.setVisible(false);
      View.centerScreen(this);
      View.setWindowIcon(this);
      loadingLabel.setHorizontalAlignment(SwingConstants.LEFT);
      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
   }
}