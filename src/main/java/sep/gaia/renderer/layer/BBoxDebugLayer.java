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
package sep.gaia.renderer.layer;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

import sep.gaia.state.AbstractStateManager.StateType;
import sep.gaia.state.GLState;
import sep.gaia.state.StateManager;
import sep.gaia.util.FloatBoundingBox;
import sep.gaia.util.FloatVector3D;

public class BBoxDebugLayer extends AbstractLayer {

	private static final float TEXSIZE = 100f;
	
	private boolean texturesLoaded;
	
	private float layerDist = 0.0f;
	
	private Texture texUpperLeft;
	private Texture texLowerRight;
	
	private void loadTextures() {
		try {
			texUpperLeft = TextureIO.newTexture(new File("/bboxdbg_ul.png"), false);
			texLowerRight = TextureIO.newTexture(new File("/bboxdbg_lr.png"), false);
			
			texturesLoaded = true;
			
		} catch (GLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(GL2 gl, float height) {
		
		if(!texturesLoaded) {
			loadTextures();
		}
		
		GLState glState = (GLState) StateManager.getInstance().getState(StateType.GLState);
		
		FloatBoundingBox bbox = glState.getBoundingBox();
		
		
		FloatVector3D current = bbox.getUpperLeft();
		TextureCoords texCoords = texUpperLeft.getImageTexCoords();
		texUpperLeft.enable(gl);
		texUpperLeft.bind(gl);
		
		gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(texCoords.right(), texCoords.top());
			gl.glVertex3f(current.getX() + TEXSIZE, current.getY(), layerDist);
			gl.glTexCoord2f(texCoords.left(), texCoords.top());
			gl.glVertex3f(current.getX(), current.getY(), layerDist);
			gl.glTexCoord2f(texCoords.left(), texCoords.bottom());
			gl.glVertex3f(current.getX(), current.getY() - TEXSIZE, layerDist);
			gl.glTexCoord2f(texCoords.right(), texCoords.bottom());
			gl.glVertex2f(current.getX() + TEXSIZE, current.getY() - TEXSIZE);
		gl.glEnd();
		
		current = bbox.getLowerRight();
		texCoords = texLowerRight.getImageTexCoords();
		texLowerRight.enable(gl);
		texLowerRight.bind(gl);
		
		gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(texCoords.right(), texCoords.top());
			gl.glVertex3f(current.getX(), current.getY() + TEXSIZE, layerDist);
			gl.glTexCoord2f(texCoords.left(), texCoords.top());
			gl.glVertex3f(current.getX() - TEXSIZE, current.getY() + TEXSIZE, layerDist);
			gl.glTexCoord2f(texCoords.left(), texCoords.bottom());
			gl.glVertex3f(current.getX() - TEXSIZE, current.getY(), layerDist);
			gl.glTexCoord2f(texCoords.right(), texCoords.bottom());
			gl.glVertex2f(current.getX(), current.getY());
		gl.glEnd();
		
		DrawableLayer nextLayer = getNextLayer();
		if(nextLayer != null) {
			nextLayer.draw(gl, height + HEIGHT_GAP);
		}
	}

}
