//CPCS391 - Spring2021 - Lab Homework 
//Group#3 
//Razan Aljuhani - GBR
//Shahad Alharthi
//Shorog Alshikh
//Shahad Almalki
//Amjad Alshahrani
//Wejdan Alzhrani - GAR
package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Font;

/**
 * CPCS391_Project.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class CPCS391_Project implements GLEventListener {

    Texture tex;
    TextRenderer text;

    public static void main(String[] args) {
        Frame frame = new Frame("CPCS391-PROJECT-Group#3");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new CPCS391_Project());
        frame.add(canvas);
        frame.setSize(900, 900);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        gl.glEnable(GL.GL_TEXTURE_2D); //activate texture mapping for 2D 
        try {
            //load textures here
            tex = TextureIO.newTexture(new File("Covide-19.JPG"), true);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        text = new TextRenderer(new Font("Courgette", Font.ITALIC, 50));
        // Setup the drawing area and shading mode
        gl.glClearColor(135 / 255f, 235 / 255f, 152 / 255f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        //******************************* The texture and text *******************************
        // Move the "drawing cursor" around
        gl.glTranslatef(3.0f, -3.0f, -10.0f);

        // The Texture 
        tex.bind();
        tex.enable();
        // Draw a quad and texture  
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(-7.9f, 2.8f, 0.0f);  // Top Left
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(1.9f, 2.8f, 0.0f);   // Top Right
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(1.9f, -0.8f, 0.0f);  // Bottom Right
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(-7.9f, -0.8f, 0.0f); // Bottom Left
        gl.glEnd();  // Done Drawing The Quad
        tex.disable();

        // The text 
        text.beginRendering(drawable.getWidth(), drawable.getHeight());
        // Optionally set the color
        text.setColor(1.0f, 1.0f, 1.0f, 1.0f); //RGBA
        text.draw("Protect yourself from coronavirus :)", 80, 700);
        // ... more draw commands, color changes, etc.
        text.endRendering();

        //The Home . 
        gl.glColor3f(58 / 255f, 239 / 255f, 33 / 255f);
        gl.glRectd(-7.5f, 2.8f, -5.7f, 4f);

        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(32 / 255f, 171 / 255f, 12 / 255f);
        gl.glVertex2f(-7.5f, 4f);
        gl.glVertex2f(-6.6f, 5f);
        gl.glVertex2f(-5.7f, 4f);
        gl.glEnd();

   
        
        
        

        gl.glColor3f(1f, 1f, 1f);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
