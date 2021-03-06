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
package sep.gaia.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import sep.gaia.state.GLState;
import sep.gaia.util.FloatVector3D;

/** 
 * This is to implement the <code>MouseListener</code> Interface and the
 * <code>MouseMotionListener</code> Interface to check if the user wants to
 * rotate the virtual earth by clicking and dragging the mouse in the desired
 * direction.
 * 
 * @author Johannes Bauer (Spezifikation: Michael Mitterer)
 */
public class MouseDraggedListener3d implements MouseMotionListener {
	/** 
	 * The <code>GLState</code> reference.
	 */
	private GLState state;
	
	/**
	 * The tracked x-coordinate of the mouse 
	 */
	private float mouseX = 0;
	
	/**
	 * The tracked y-coordinate of the mouse
	 */
	private float mouseY = 0;
	
	/**
	 * The scroll amount should be divided by this constant in order
	 * for the movement to be 'softer'
	 */
	private static final int ADJUSTMENT_FACTOR = 5;
	
	/**
	 * MouseDraggedListener constructor
	 * 
	 * @param state The current <code>GLState</code>
	 */
    public MouseDraggedListener3d(GLState state) {
    	this.state = state;
    }
    
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (state.is2DMode()) {
			return;
		}
		
		// current mouse coordinates
		float x = (float) e.getX();
		float y = (float) e.getY();
		
		float scrollX = (this.mouseX - x) / ADJUSTMENT_FACTOR;
		float scrollY = (this.mouseY - y) / ADJUSTMENT_FACTOR;
		
		// rotate around the amount by which the mouse has moved
		state.rotate(new FloatVector3D(scrollY, scrollX, 0));
		
		System.out.println(state.getRotation());
		
		// keep track of the mouse position
		this.mouseX = x;
		this.mouseY = y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		// keep track of the mouse
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

}
