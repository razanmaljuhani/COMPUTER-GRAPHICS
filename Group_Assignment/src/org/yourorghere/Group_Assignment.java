//CPCS391 - Spring2021 - Lab Homework 
//Group#9 
//Alanoud Almutairi - GAR
//Razan Aljuhani - GBR
//Lama Almuhmadi - GAR
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
 * Assignment.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Assignment implements GLEventListener {

    Texture tex;
    TextRenderer text;

    public static void main(String[] args) {
        Frame frame = new Frame("Group#9 - Razan(1806065) - Lama(1805751) - Alanoud(1806928) - Wejdan(1708094) . ");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Assignment());
        frame.add(canvas);
        frame.setSize(700, 700);
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
            tex = TextureIO.newTexture(new File("girl.PNG"), true);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        text = new TextRenderer(new Font("Courgette", Font.ITALIC, 50));

        // Setup the drawing area and shading mode
        gl.glClearColor(0.25098f, 0.0f, 0.50196f, 0.0f);
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

        //******************************* The texture and text by Alanoud Almutairi(1806928) *******************************
        // texture little girl :)
        gl.glColor3f(0.25098f, 0.0f, 0.50196f);
        gl.glTranslatef(3.0f, -3.0f, -10.0f);
        tex.bind();
        tex.enable();
        // Draw A Quad and texture  
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0, 0);

        gl.glVertex3f(-1.0f, 1.0f, 0.0f);  // Top Left
        gl.glTexCoord2f(1, 0);

        gl.glVertex3f(1.0f, 1.0f, 0.0f);   // Top Right
        gl.glTexCoord2f(1, 1);

        gl.glVertex3f(1.0f, -1.0f, 0.0f);  // Bottom Right
        gl.glTexCoord2f(0, 1);

        gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
        gl.glEnd();  // Done Drawing The Quad

        tex.disable();
        // The text 
        text.beginRendering(drawable.getWidth(), drawable.getHeight());
        // Optionally set the color
        text.setColor(1.0f, 1.0f, 1.0f, 1.0f); //RGBA
        text.draw("Ramadan Kareem", 100, 50);
        // ... more draw commands, color changes, etc.
        text.endRendering();

        //************************ The Crescent by LAMA Almuhmadi(1805751) *******************************
        // Draw the front circle with the same color as the background
        gl.glTranslatef(-2.5f, 4f, -10.0f);
        gl.glColor3f(1f, 1f, 120 / 255f);
        float numPoints1 = 100;
        float Radius1 = 6f;
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints1; i++) {
            double Angle = i * (2 * Math.PI / numPoints1);
            double x = Math.cos(Angle) * Radius1;
            double y = Math.sin(Angle) * Radius1;
            gl.glVertex2d(x, y);
        }
        gl.glEnd();
        //Draw the back circle wich will be the Crescent
        gl.glTranslatef(1f, -0.3f, 10.0f);
        gl.glColor3d(0.25098f, 0.0f, 0.50196f);
        float numPoints = 100;
        float Radius = 2.5f;
        gl.glBegin(GL.GL_POLYGON);
        for (int l = 0; l < numPoints; l++) {
            double Angle = l * (2 * Math.PI / numPoints);
            double x = Math.cos(Angle) * Radius;
            double y = Math.sin(Angle) * Radius;
            gl.glVertex2d(x, y);
        }
        gl.glEnd();  //Done drawing the Crescent .

        //************************ The Mosque by Razan Aljuhani(1806065) *******************************
        gl.glTranslatef(1.5f, -3.5f, 1.0f);
        //The base of the mosque  . 
        gl.glColor3f(64 / 255f, 0f, 64 / 255f);
        gl.glRectd(-2.2f, 2.1f, -1f, 3.4f);
        gl.glRectd(-2.6f, 2.1f, -2.5f, 3.4f);
        gl.glRectd(-0.7f, 2.1f, -0.5f, 3.4f);
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glRectd(-3f, 2.1f, -0.1f, 2f);

        //============================================
        //Frist pole
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glRectd(-3f, 2.1f, -2.6f, 4f);
        gl.glColor3f(64 / 255f, 0f, 0f);
        gl.glRectd(-2.9f, 2.1f, -2.7f, 3.9f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0f, 128 / 255f, 64 / 255f);
        gl.glVertex2f(-3f, 4f);
        gl.glVertex2f(-2.8f, 5f);
        gl.glVertex2f(-2.6f, 4f);
        gl.glEnd();
        //============================================
        //Second pole
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glRectd(-2.5f, 2.1f, -2.2f, 3.5f);
        gl.glColor3f(64 / 255f, 0f, 0f);
        gl.glRectd(-2.4f, 2.1f, -2.29f, 3.4f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0f, 128 / 255f, 64 / 255f);
        gl.glVertex2f(-2.5f, 3.5f);
        gl.glVertex2f(-2.35f, 4.5f);
        gl.glVertex2f(-2.2f, 3.5f);
        gl.glEnd();
        //============================================
        //Third pole
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glRectd(-1f, 2.1f, -0.7f, 3.5f);
        gl.glColor3f(64 / 255f, 0f, 0f);
        gl.glRectd(-0.9f, 2.1f, -0.79f, 3.4f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0f, 128 / 255f, 64 / 255f);
        gl.glVertex2f(-1f, 3.5f);
        gl.glVertex2f(-0.85f, 4.5f);
        gl.glVertex2f(-0.7f, 3.5f);
        gl.glEnd();
        //============================================
        //Fourth pole
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glRectd(-0.5f, 2.1f, -0.1f, 4f);
        gl.glColor3f(64 / 255f, 0f, 0f);
        gl.glRectd(-0.4f, 2.1f, -0.2f, 3.9f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0f, 128 / 255f, 64 / 255f);
        gl.glVertex2f(-0.5f, 4f);
        gl.glVertex2f(-0.3f, 5f);
        gl.glVertex2f(-0.1f, 4f);
        gl.glEnd();
        //============================================
        //The door of the mosque . 
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glVertex2f(-1.8f, 2.1f);
        gl.glVertex2f(-1.8f, 2.7f);
        gl.glVertex2f(-1.4f, 2.7f);
        gl.glVertex2f(-1.4f, 2.1f);
        gl.glEnd();
        gl.glPointSize(10);
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glVertex2f(-1.6f, 2.4f);
        gl.glEnd();
        //============================================
        //The dome of the mosque 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0f, 128 / 255f, 64 / 255f);
        gl.glVertex2f(-2.2f, 3.4f);
        gl.glVertex2f(-2.1f, 3.8f);
        gl.glVertex2f(-1.6f, 4.2f);
        gl.glVertex2f(-1.1f, 3.8f);
        gl.glVertex2f(-1f, 3.4f);
        gl.glEnd();
        gl.glLineWidth(1.2f);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(227 / 255f, 148 / 255f, 28 / 255f);
        gl.glVertex2f(-1.6f, 4.2f);
        gl.glVertex2f(-1.6f, 4.5f);
        gl.glEnd(); //Done drawing the Mousque ^-^ .

        //************************ The Star by Wejdan Alzhrani(1708094)*******************************

        gl.glTranslatef(-13f, 4.5f, -10f);

        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(1f, 1f, 120 / 255f);
        gl.glVertex2f(1, 0);
        gl.glVertex2f(4, 4);
        gl.glVertex2f(-1.5f, 3);
        gl.glVertex2f(4, 0);
        gl.glVertex2f(2, 5);
        gl.glEnd();

        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0.25098f, 0.0f, 0.50196f);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(2.f, 2);
        gl.glVertex2f(4.7f, 0);
        gl.glEnd();

        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0.25098f, 0.0f, 0.50196f);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(0.9f, 3);
        gl.glVertex2f(-2.4f, 5);
        gl.glEnd(); //End drawing the Star

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
