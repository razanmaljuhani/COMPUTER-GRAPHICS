package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * Razan_1806065_Lab3.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Razan_1806065_Lab3 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("lAB#4 - Razan Aljuhani ");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Razan_1806065_Lab3());
        frame.add(canvas);
        frame.setSize(800, 800);
        frame.setResizable(false); //Size of my frame is fixed.
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
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f); //RGBA.
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

        // Move the "drawing cursor" around
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);

        //_______________Lab(3)__________________
        //The Herb
        gl.glLineWidth(7);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(0, 1, 0);
        gl.glVertex2f(-10, 0);
        gl.glVertex2f(10, 0);
        gl.glEnd();
        //TheHome
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(1, 0, 0);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(0, 1.2f);
        gl.glColor3f(1, 0, 0);
        gl.glVertex2f(1, 0);
        gl.glVertex2f(1, 1.2f);
        gl.glEnd();
        //The Door
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(1, 0, 0);
        gl.glVertex2f(0.30f, 0.0f);
        gl.glVertex2f(0.30f, 0.40f);
        gl.glVertex2f(0.70f, 0.40f);
        gl.glVertex2f(0.70f, 0.0f);
        gl.glEnd();
        //The Triangle
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glColor3f(0, 0, 1);
        gl.glVertex2f(-0.3f, 1.2f);
        gl.glVertex2f(0.5f, 1.8f);
        gl.glColor3f(0, 0, 1);
        gl.glVertex2f(0.5f, 1.8f);
        gl.glVertex2f(1.3f, 1.2f);
        gl.glEnd();
        //The Sun 
        gl.glPointSize(50);
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1,1, 0);
        gl.glVertex2f(2.8f, 1.9f);
        gl.glEnd();
       //Sun rays
        gl.glLineWidth(1.2f);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(1,1, 0);
        gl.glVertex2f(2.8f,1.9f);
        gl.glVertex2f(2.8f, 2.5f);
        gl.glColor3f(1,1, 0);
        gl.glVertex2f(2.8f,1.9f);
        gl.glVertex2f(2.8f, 1.3f);
        gl.glColor3f(1,1, 0);
        gl.glVertex2f(2.8f,1.9f);
        gl.glVertex2f(2.2f, 1.9f);
        gl.glColor3f(1,1, 0);
        gl.glVertex2f(2.8f,1.9f);
        gl.glVertex2f(3.4f, 1.9f);
        gl.glEnd(); 
        
        //_______________Lab(4)__________________

       //The Tree 
       //1- the polygon 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0,0.65f, 0);
        
        gl.glVertex2f(1.5f, 0.8f);
        gl.glVertex2f(1.3f, 0.6f);
        gl.glVertex2f(1.5f,0.4f);
        gl.glVertex2f(1.7f, 0.4f);  
        gl.glVertex2f(1.9f, 0.6f);       
        gl.glVertex2f(1.7f, 0.8f);
        gl.glEnd(); 
        
       //2- The rectangle 
        gl.glColor3f(76/255f,51/255f, 51/255f);
        gl.glRectd(1.5f,0,1.7f,0.4f);       
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

