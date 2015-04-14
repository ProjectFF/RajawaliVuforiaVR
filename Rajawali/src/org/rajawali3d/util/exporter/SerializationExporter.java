/**
 * Copyright 2013 Dennis Ippel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.rajawali3d.util.exporter;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import org.rajawali3d.Geometry3D;
import org.rajawali3d.SerializedObject3D;
import org.rajawali3d.animation.mesh.VertexAnimationFrame;
import org.rajawali3d.animation.mesh.VertexAnimationObject3D;
import org.rajawali3d.util.RajLog;


public class SerializationExporter extends AExporter {

	@Override
	public void export() throws Exception {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(exportFile);
			ObjectOutputStream os = null;
			if (mCompressed) {
				GZIPOutputStream gz = new GZIPOutputStream(fos);
				os = new ObjectOutputStream(gz);
			} else {
				os = new ObjectOutputStream(fos);
			}

			SerializedObject3D ser = new SerializedObject3D(0,0,0,0,0);

			if (mObject instanceof VertexAnimationObject3D) {
				VertexAnimationObject3D o = (VertexAnimationObject3D) mObject;
				int numFrames = o.getNumFrames();
				float[][] vs = new float[numFrames][];
				float[][] ns = new float[numFrames][];
				String[] frameNames = new String[numFrames];

				for (int i = 0; i < numFrames; ++i) {
					VertexAnimationFrame frame = (VertexAnimationFrame) o.getFrame(i);
					Geometry3D geom = frame.getGeometry();
					float[] v = new float[geom.getVertices().limit()];
					geom.getVertices().get(v);
					float[] n = new float[geom.getNormals().limit()];
					geom.getNormals().get(n);
					vs[i] = v;
					ns[i] = n;
					frameNames[i] = frame.getName();
				}

				ser.setFrameVertices(vs);
				ser.setFrameNormals(ns);
				ser.setFrameNames(frameNames);
			}

			os.writeObject(ser);
			os.close();
			RajLog.i("Successfully serialized " + exportFile.getName() + " to " + exportFile.getCanonicalPath());
		} catch (Exception e) {
			RajLog.e("Serializing " + exportFile.getName() + " was unsuccessfull.");
			e.printStackTrace();
		}

	}
	
	@Override
	public String getExtension() {
		return new String("ser");
	}

}
