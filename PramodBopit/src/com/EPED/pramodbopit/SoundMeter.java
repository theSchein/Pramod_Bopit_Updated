/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.EPED.pramodbopit;
import java.io.IOException;

import android.media.MediaRecorder;
import android.util.Log;
public class SoundMeter {
	 static final private double EMA_FILTER = 0.6;
	 private static final String LOG_TAG = "AudioRecordTest";
     private MediaRecorder mRecorder = null;
     private double mEMA = 0.0;

     public void start(){
             
                 mRecorder = new MediaRecorder();
                 mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                 mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                 mRecorder.setOutputFile("/dev/null"); 
                 mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                 
                
                
                 try {
					mRecorder.prepare();
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 mRecorder.start();
                 mEMA = 0.0;
             }
     
     
     public void stop() {
             if (mRecorder != null) {
                     mRecorder.stop();       
                     mRecorder.release();
                     mRecorder = null;
             }
     }
     
     public double getAmplitude() {
             if (mRecorder != null)
                     return  (mRecorder.getMaxAmplitude()/2700.0);
             else
                     return 0;

     }

     public double getAmplitudeEMA() {
             double amp = getAmplitude();
             mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
             return mEMA;
     }
}


