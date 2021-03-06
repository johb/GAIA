/*		
 *		Copyright (c) 2015. 
 *		Johannes Bauer, Fabian Buske, Matthias Fisch,
 *		Michael Mitterer, Maximilian Witzelsperger
 *
 *		Licensed under the Apache License, Version 2.0 (the "License");
 *		you may not use this file except in compliance with the License.
 *		You may obtain a copy of the License at
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *		Unless required by applicable law or agreed to in writing, software
 *		distributed under the License is distributed on an "AS IS" BASIS,
 *		WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *		See the License for the specific language governing permissions and
 *		limitations under the License.
 */
package sep.gaia.ui;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import sep.gaia.util.Logger;

/**
 * This is to show the short description of the selected
 * <code>PointOfInterest</code> in a JDialog.
 * 
 * @author Michael Mitterer, Matthias Fisch
 */
public class WikipediaWindow extends JFrame implements HyperlinkListener {

	/**
	 * WikipediaWindow constructor
	 * 
	 * @param summary
	 *            The summary of the poi
	 * @param poiName
	 *            The name of the poi
	 */
	public WikipediaWindow(String summary, String poiName) {
		super(poiName);
		this.setIconImage(IconFactory.getIcon("wikipedia.png"));
		JEditorPane summaryPane = new JEditorPane();
		summaryPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		summaryPane.setText(summary);
		summaryPane.setEditable(false);
		summaryPane.addHyperlinkListener(this);
		
		JScrollPane scrollPane = new JScrollPane(summaryPane);
		add(scrollPane);
		
		scrollPane.setPreferredSize(new Dimension(500, 400));
		setResizable(false);
		setVisible(true);
		pack();
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			if(Desktop.isDesktopSupported()) {
			    try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException | URISyntaxException e1) {
					Logger.getInstance().warning("Failed to open browser! Details: " + e1.getMessage());
				}
			}
        }
	}
}
