/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpaint;
/**
 *
 * @author Rania
 */

/*<applet code="PaintBrush" width =600 height=600>
</applet>
*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

//MouseListener handles the events when the mouse is not in motion. 
//While MouseMotionListene handles the events when mouse is in motion.
//We implement an action listener define what should be done when an user performs certain operation.

public class TestPaint extends Applet implements MouseMotionListener ,ActionListener,MouseListener  
{

   int width, height,opr;
   Image backbuffer;//search
   Graphics backg; //To drow Graphics in background

   Label lab1,lab2,lab3,lab4; 
   TextField textx,texty,texttool,textcolor;

   Panel control,top,action,cheack; //container contain more than one component 
  
   int x,y,x1,y1;
      public Boolean ddt = Boolean.FALSE;
      public Boolean  fill = Boolean.FALSE; //flags
      public Boolean  claer = Boolean.FALSE; //flags
   Button b[]=new Button[3]; //colores buttons
   Button b1[]= new Button[6]; //action buttons
	
    String color[]={"Green","Red","Blue"};

    String actionstr[]= {"Free Hand","Line","Ractangle","Ovel","Clear All","Eraser"};
    
   Checkbox Dot,Filled;
   
   
   @Override
   public void init() 
   {
       
       // Layouts are used to manage components in a specific order
       
	setLayout(new BorderLayout()); 
        setSize(1000, 1000);
       //Borderlayout use to position components in five different regions like top, bottom, left, right and center
	
 	control = new Panel(); //create anew panel
        
         // set the layout of the panel 
	control.setLayout(new GridLayout(3,1)); //Grid layout arranges component in rectangular grid takes two parameters that is column are row.
	
	top= new Panel(); 
	top.setLayout(new FlowLayout()); // It arranges components in a line, if no space left remaining components goes to next line.
	
	action= new Panel(); 
	action.setLayout(new GridLayout(2,3)); //arranges component in rectangular grid.

        cheack=new Panel();
        cheack.setLayout (new GridLayout(1,2));
        
        //initialize the labels 
	lab1= new Label("Selected Tool"); //component referance to obj of type label.
	lab2= new Label("Color");
        lab3= new Label("X");
        lab4= new Label("Y");
         
        textx= new TextField("     "); //component referance to obj of type TextFill.
	texty= new TextField("     ");
	texttool= new TextField("               ");
	textcolor= new TextField("   ");
        
       //Use add() method to add elements into the Set. 
      // add labels to the top panel 
	top.add(lab2);
	top.add(textcolor);
	top.add(lab1);
  	top.add(texttool);
        top.add(lab3);
	top.add(textx);
	top.add(lab4);
	top.add(texty);
       

        Dot=new Checkbox("Dotted");
        cheack.add(Dot);
     
       // this.add(Dot);
       Dot.addItemListener((new DottedListener()));
       Filled = new Checkbox("Filled");
         cheack.add(Filled);
       // this.add(Filled);
        Filled.addItemListener((new FillListener ()));
        //Step2 Button b is a component upon which an instance of event handler class TestPaint is registered to the Listener. 
	for(int i=0;i<b.length;i++)
	{  
	 b[i]= new Button(color[i]); 
	 b[i].addActionListener(this); 
	 control.add(b[i]); //add elements into the control set

	}
         
       //step2 Button b is a component upon which an instance of event handler class TestPaint is registered to the Listener.
       for(int i=0;i<b1.length;i++) 
       {  
	b1[i]= new Button(actionstr[i]);
	b1[i].addActionListener(this);
	action.add(b1[i]); //add elements into the action set
   
	}
       //Color of Buttons background
	 b[0].setBackground(Color.green);
	 b[1].setBackground(Color.red);
	 b[2].setBackground(Color.blue);

	add(control,BorderLayout.EAST);
	add(top,BorderLayout.NORTH);
	add(action,BorderLayout.SOUTH);
        add(cheack,BorderLayout.WEST);

      width = getSize().width;
      height = getSize().height;

        backg = getGraphics(); //Creates a graphics context for this component
        
    // The Graphics class provides methods for drawing graphical objects
      
      //defult of the paintbrush
      backg.setColor( Color.black);
       textcolor.setBackground(Color.black);
       
      // add mouseListener 
      addMouseMotionListener( this );
      addMouseListener(this);
	opr=1;
	texttool.setText("Free Hand");
        
        
   }
   
   @Override
   public void mouseMoved( MouseEvent e ) // invoked when the mouse cursor is moved from one point to another within the component, without pressing any mouse buttons.
  {
     textx.setText(Integer.toString(e.getX()));
     texty.setText(Integer.toString(e.getY()));
  }
  

  
   @Override
 public void mouseDragged( MouseEvent e ) // Invoked when a mouse button is pressed in the component and dragged. 
  {
    
   if(opr==1)
	{
       x1 = e.getX();
       y1 = e.getY();
      backg.drawLine(x,y, x1,y1);//How the free hand will be paint (Free Hand)
   	
      repaint();
      x = x1 ;
      y = y1;

	}
   
    else if(opr==6)
	{
	textcolor.setBackground(Color.white);
	backg.setColor( Color.white);
	x = e.getX();
        y = e.getY();
        backg.fillRect(x,y, 30,30); //Erase 
        repaint();
	}
   
   }

   @Override
   public void update( Graphics g ){
      
      g.drawImage( backbuffer, 0, 0, this );//serch
   }

   @Override
   public void paint( Graphics g ) {
      
      update( g );
   }

 //step_3 of creating an action when we click on buttons
   @Override
 public void actionPerformed(ActionEvent e)
{

 if(   null!=e.getActionCommand()){
	switch (e.getActionCommand()) {
           case "Green":
               backg.setColor( Color.green );
               textcolor.setBackground(Color.green );
               break;
           case "Red":
               backg.setColor( Color.red );
               textcolor.setBackground(Color.red);
               break;
           case "Blue":
               backg.setColor( Color.blue);
               textcolor.setBackground(Color.blue);
               break;
           default:
               break;
       }
 }


   if( null!=e.getActionCommand()){
	switch (e.getActionCommand()) {
           case "Free Hand":
               opr=1;
               texttool.setText("Free Hand");
               break;
           case "Line":
               opr=2;
               texttool.setText("Line");
               break;
           case "Ractangle":
               opr=3;
               texttool.setText("Rectangle");
               break;
           case "Ovel":
               opr=4;
               texttool.setText("Ovel");
               break;
           case "Clear All":
               opr=5;
               texttool.setText("Clear All");
               backg.clearRect(0, 0, 2000, 2000);
                repaint();
               //After Clear Press New Shap Then a new color To cont.
               break;
           case "Eraser":
               opr=6;
               texttool.setText("Eraser");
               break;
           default:
               break;
       }
}
}

   @Override
 public void mouseClicked(MouseEvent e) //Mouse key is pressed/released
 {
       
	 if(opr==6)
	{
	 backg.setColor( Color.white);
	x = e.getX();
        y = e.getY();
        backg.clearRect(x,y, 30,30);
        repaint();
	
	}
 }

   @Override
 public void mousePressed(MouseEvent e) //Mouse key is pressed
 {

   System.out.println("Mouse Pressed:");
   System.out.println("X:"+e.getX());
   System.out.println("Y:"+e.getY());


	  x=e.getX();
	  y=e.getY();
          
 
 }

   @Override
   public void mouseReleased(MouseEvent e)//Mouse key is released
 {

   System.out.println("Mouse Released:");
   System.out.println("X1:"+e.getX());
   System.out.println("Y1:"+e.getY());



     if(opr==2)
      {
          
      x1=e.getX();
      y1=e.getY();
      if (ddt)
      {   
         this.drawDashedLine(backg,x,y,x1,y1); 
      }
      else if (ddt== Boolean.FALSE){
      backg.drawLine(x,y,x1,y1);
      // repaint();
      }
      repaint();
      
      
      }
   else if(opr==3)
      { 
             x1=e.getX();
             y1=e.getY();
	if(fill == Boolean.FALSE){
             
       if((x<=x1)&&(y<=y1))
	      backg.drawRect(x,y,(x1-x),(y1-y));
       else if((x>=x1)&&(y<=y1))
	      backg.drawRect(x1,y,(x-x1),(y1-y));
       else if((x>=x1)&&(y>=y1))	
		backg.drawRect(x1,y1,(x-x1),(y-y1));
       else if((x<=x1)&&(y>=y1))	
		backg.drawRect(x,y1,(x1-x),(y-y1));
      repaint();}
      else if(fill)
              {
                
                 if((x<=x1)&&(y<=y1))
	      backg.fillRect(x,y,(x1-x),(y1-y));
       else if((x>=x1)&&(y<=y1))
	      backg.fillRect(x1,y,(x-x1),(y1-y));
       else if((x>=x1)&&(y>=y1))	
		backg.fillRect(x1,y1,(x-x1),(y-y1));
       else if((x<=x1)&&(y>=y1))	
		backg.fillRect(x,y1,(x1-x),(y-y1));
      repaint();

              }
      }	
	
    else if(opr==4)
	{
	x1=e.getX();
        y1=e.getY();
	if(fill== Boolean.FALSE){
       if((x<=x1)&&(y<=y1))
	      backg.drawOval(x,y,(x1-x),(y1-y));
       else if((x>=x1)&&(y<=y1))
	      backg.drawOval(x1,y,(x-x1),(y1-y));
       else if((x>=x1)&&(y>=y1))	
		backg.drawOval(x1,y1,(x-x1),(y-y1));
       else if((x<=x1)&&(y>=y1))	
		backg.drawOval(x,y1,(x1-x),(y-y1));

   	}

	
     else if(fill)
      { 
          System.out.println("Clicked Fill Oval");
     
         if((x<=x1)&&(y<=y1))
            backg.fillOval(x,y,(x1-x),(y1-y));
       else if((x>=x1)&&(y<=y1))
	      backg.fillOval(x1,y,(x-x1),(y1-y));
       else if((x>=x1)&&(y>=y1))	
		backg.fillOval(x1,y1,(x-x1),(y-y1));
       else if((x<=x1)&&(y>=y1))	
		backg.fillOval(x,y1,(x1-x),(y-y1));
      repaint();
             }
        } 
 }
   
   @Override
 public void mouseEntered(MouseEvent e)
 {}
   @Override
 public void mouseExited(MouseEvent e)
 {}
  public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2)
  {       // line to be dotted
            Graphics2D g2d = (Graphics2D) g.create();
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2); 
            g2d.dispose();
    }

    private  class DottedListener implements ItemListener {

        public DottedListener() {
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
             
           if(ddt){
           ddt = Boolean.FALSE;
           
           }else {ddt = Boolean.TRUE;}
        
        }
    }

    private  class FillListener implements ItemListener {

        public FillListener() {
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
           if(fill){
           fill = Boolean.FALSE;
           
           }else {fill = Boolean.TRUE;}
        }
    }
 
}