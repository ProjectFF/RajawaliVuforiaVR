/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package org.rajawali3d;

public final class R {
    public static final class attr {
        /** <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>multisample</code></td><td>1</td><td></td></tr>
<tr><td><code>coverage</code></td><td>2</td><td></td></tr>
</table>
         */
        public static int antiAliasingType=0x7f010008;
        /** <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int bitsAlpha=0x7f010005;
        /** <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int bitsBlue=0x7f010004;
        /** <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int bitsDepth=0x7f010006;
        /** <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int bitsGreen=0x7f010003;
        /** <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int bitsRed=0x7f010002;
        /** <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int frameRate=0x7f010000;
        /** <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int isTransparent=0x7f010001;
        /** <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int multiSampleCount=0x7f010007;
        /** <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>RENDER_WHEN_DIRTY</code></td><td>0</td><td></td></tr>
<tr><td><code>RENDER_CONTINUOUS</code></td><td>1</td><td></td></tr>
</table>
         */
        public static int renderMode=0x7f010009;
    }
    public static final class drawable {
        public static int ic_launcher=0x7f020000;
    }
    public static final class id {
        public static int RENDER_CONTINUOUS=0x7f060002;
        public static int RENDER_WHEN_DIRTY=0x7f060003;
        public static int coverage=0x7f060000;
        public static int multisample=0x7f060001;
    }
    public static final class raw {
        public static int blend_add_fragment_shader=0x7f030000;
        public static int blend_screen_fragment_shader=0x7f030001;
        public static int blur_fragment_shader=0x7f030002;
        public static int color_threshold_shader=0x7f030003;
        public static int copy_fragment_shader=0x7f030004;
        public static int fog_fragment_shader=0x7f030005;
        public static int grey_scale_fragment_shader=0x7f030006;
        public static int minimal_vertex_shader=0x7f030007;
        public static int sepia_fragment_shader=0x7f030008;
        public static int vignette_fragment_shader=0x7f030009;
    }
    public static final class string {
        public static int app_name=0x7f040000;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.
    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.
        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static int AppBaseTheme=0x7f050000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static int AppTheme=0x7f050001;
    }
    public static final class styleable {
        /** Attributes that can be used with a RajawaliSurfaceView.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_antiAliasingType org.rajawali3d:antiAliasingType}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_bitsAlpha org.rajawali3d:bitsAlpha}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_bitsBlue org.rajawali3d:bitsBlue}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_bitsDepth org.rajawali3d:bitsDepth}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_bitsGreen org.rajawali3d:bitsGreen}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_bitsRed org.rajawali3d:bitsRed}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_frameRate org.rajawali3d:frameRate}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_isTransparent org.rajawali3d:isTransparent}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_multiSampleCount org.rajawali3d:multiSampleCount}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliSurfaceView_renderMode org.rajawali3d:renderMode}</code></td><td></td></tr>
           </table>
           @see #RajawaliSurfaceView_antiAliasingType
           @see #RajawaliSurfaceView_bitsAlpha
           @see #RajawaliSurfaceView_bitsBlue
           @see #RajawaliSurfaceView_bitsDepth
           @see #RajawaliSurfaceView_bitsGreen
           @see #RajawaliSurfaceView_bitsRed
           @see #RajawaliSurfaceView_frameRate
           @see #RajawaliSurfaceView_isTransparent
           @see #RajawaliSurfaceView_multiSampleCount
           @see #RajawaliSurfaceView_renderMode
         */
        public static final int[] RajawaliSurfaceView = {
            0x7f010000, 0x7f010001, 0x7f010002, 0x7f010003,
            0x7f010004, 0x7f010005, 0x7f010006, 0x7f010007,
            0x7f010008, 0x7f010009
        };
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#antiAliasingType}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>multisample</code></td><td>1</td><td></td></tr>
<tr><td><code>coverage</code></td><td>2</td><td></td></tr>
</table>
          @attr name org.rajawali3d:antiAliasingType
        */
        public static int RajawaliSurfaceView_antiAliasingType = 8;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsAlpha}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsAlpha
        */
        public static int RajawaliSurfaceView_bitsAlpha = 5;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsBlue}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsBlue
        */
        public static int RajawaliSurfaceView_bitsBlue = 4;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsDepth}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsDepth
        */
        public static int RajawaliSurfaceView_bitsDepth = 6;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsGreen}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsGreen
        */
        public static int RajawaliSurfaceView_bitsGreen = 3;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsRed}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsRed
        */
        public static int RajawaliSurfaceView_bitsRed = 2;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#frameRate}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:frameRate
        */
        public static int RajawaliSurfaceView_frameRate = 0;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#isTransparent}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:isTransparent
        */
        public static int RajawaliSurfaceView_isTransparent = 1;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#multiSampleCount}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:multiSampleCount
        */
        public static int RajawaliSurfaceView_multiSampleCount = 7;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#renderMode}
          attribute's value can be found in the {@link #RajawaliSurfaceView} array.


          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>RENDER_WHEN_DIRTY</code></td><td>0</td><td></td></tr>
<tr><td><code>RENDER_CONTINUOUS</code></td><td>1</td><td></td></tr>
</table>
          @attr name org.rajawali3d:renderMode
        */
        public static int RajawaliSurfaceView_renderMode = 9;
        /** Attributes that can be used with a RajawaliTextureView.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #RajawaliTextureView_antiAliasingType org.rajawali3d:antiAliasingType}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_bitsAlpha org.rajawali3d:bitsAlpha}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_bitsBlue org.rajawali3d:bitsBlue}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_bitsDepth org.rajawali3d:bitsDepth}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_bitsGreen org.rajawali3d:bitsGreen}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_bitsRed org.rajawali3d:bitsRed}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_frameRate org.rajawali3d:frameRate}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_multiSampleCount org.rajawali3d:multiSampleCount}</code></td><td></td></tr>
           <tr><td><code>{@link #RajawaliTextureView_renderMode org.rajawali3d:renderMode}</code></td><td></td></tr>
           </table>
           @see #RajawaliTextureView_antiAliasingType
           @see #RajawaliTextureView_bitsAlpha
           @see #RajawaliTextureView_bitsBlue
           @see #RajawaliTextureView_bitsDepth
           @see #RajawaliTextureView_bitsGreen
           @see #RajawaliTextureView_bitsRed
           @see #RajawaliTextureView_frameRate
           @see #RajawaliTextureView_multiSampleCount
           @see #RajawaliTextureView_renderMode
         */
        public static final int[] RajawaliTextureView = {
            0x7f010000, 0x7f010002, 0x7f010003, 0x7f010004,
            0x7f010005, 0x7f010006, 0x7f010007, 0x7f010008,
            0x7f010009
        };
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#antiAliasingType}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>multisample</code></td><td>1</td><td></td></tr>
<tr><td><code>coverage</code></td><td>2</td><td></td></tr>
</table>
          @attr name org.rajawali3d:antiAliasingType
        */
        public static int RajawaliTextureView_antiAliasingType = 7;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsAlpha}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsAlpha
        */
        public static int RajawaliTextureView_bitsAlpha = 4;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsBlue}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsBlue
        */
        public static int RajawaliTextureView_bitsBlue = 3;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsDepth}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsDepth
        */
        public static int RajawaliTextureView_bitsDepth = 5;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsGreen}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsGreen
        */
        public static int RajawaliTextureView_bitsGreen = 2;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#bitsRed}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:bitsRed
        */
        public static int RajawaliTextureView_bitsRed = 1;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#frameRate}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:frameRate
        */
        public static int RajawaliTextureView_frameRate = 0;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#multiSampleCount}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name org.rajawali3d:multiSampleCount
        */
        public static int RajawaliTextureView_multiSampleCount = 6;
        /**
          <p>This symbol is the offset where the {@link org.rajawali3d.R.attr#renderMode}
          attribute's value can be found in the {@link #RajawaliTextureView} array.


          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>RENDER_WHEN_DIRTY</code></td><td>0</td><td></td></tr>
<tr><td><code>RENDER_CONTINUOUS</code></td><td>1</td><td></td></tr>
</table>
          @attr name org.rajawali3d:renderMode
        */
        public static int RajawaliTextureView_renderMode = 8;
    };
}
