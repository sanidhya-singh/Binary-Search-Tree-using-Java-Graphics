/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;


//Imported packages
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

/*****************************************************************************
 *                     JAVA Program display Binary Tree                      *
 *                      Author : Sanidhya Pratap Singh                       *
 *                             SNU ID : AAA0403                              *                                                                         
 *****************************************************************************/


class Node
    //Node class to create nodes of the Binary Tree
    //Contains a left object, right object and its own data value
    {
            Node left;
            Node right;
            int data;
            
            //Default constructor for the Node class
            Node() {
                    left = null;
                    right = null;
                    data = 0;
            }
            
            //Custom constructor for the Node class
            Node(int a) {
                    left = null;
                    right = null;
                    data = a;
            }
    }   
    

public class JavaApplication2 extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    
    String iOrder;                                                              //String to contain inorder traversal
    String pOrder;                                                              //String to contain postorder traversal
    String prOrder;                                                             //String to contain preorder traversal
    String lNodes;                                                              //String to contain leaf nodes
    String nlNodes;                                                             //String to contain nonleaf nodes
    
    Node root = null;                                                           //Global root node for the tree
    Node imageNode = null;                                                      //Global image root node for the image
    
    int numberOfNodes = 0;
    int[][] nodeCoords = new int[500][3];                                       //array to store the coodinates of the nodes of the tree
    static int counter = 0;
    
    boolean treePainted = false;                                                //boolean variable to indicate that the tree has been painted
    boolean imagePainted = false;                                               //boolean variable to indicate that the image has been painted
    
    //buttons for the various tree functions, exit, reset etc.
    private JButton tree = new JButton("Tree");                                 
    private JButton inOrder = new JButton("In Order");
    private JButton postOrder = new JButton("Post Order");
    private JButton preOrder = new JButton("Pre Order");
    private JButton leafNodes = new JButton("Leaf Nodes");
    private JButton nonLeafNodes = new JButton("Non Leaf Nodes");
    private JButton image = new JButton("Image");
    private JButton search = new JButton("Search");
    private JButton reset = new JButton("Reset");
    private JButton exit = new JButton("Exit");
    
    //text fields for the various tree functions
    private JTextField f1 = new JTextField(15);
    private JTextField f2 = new JTextField(15);
    private JTextField f3 = new JTextField(15);
    private JTextField f4 = new JTextField(15);
    private JTextField f5 = new JTextField(15);
    private JTextField f6 = new JTextField(15);
    private JTextField f7 = new JTextField(15);
    private JTextField f8 = new JTextField(15);
    
    //Panel contains the buttons
    private JPanel buttonPanel = new JPanel();
    
    //Panel contains the text fields
    private JPanel fieldPanel = new JPanel();
    Font font = new Font("Verdana", Font.BOLD, 12);
    
    //Panel to display the tree
    private Tree view = new Tree(); 
    
    //Panel to display the tree image
    private Tree imageView = new Tree();
    
    public JavaApplication2() {
        //Constructor to create the GUI of the class
        setTitle("Binary Tree");
        setBackground(Color.white);
        
        //Adding the tree view panel to the frame, setting border and size
        add(view);
        view.setBorder(new TitledBorder("Tree View"));
        view.setPreferredSize(new Dimension(400,400));
        view.setVisible(true);
        view.setBackground(Color.white);
        
        //Adding the tree image panel to the frame, setting border and size
        add(imageView);
        imageView.setBorder(new TitledBorder("Tree Image View"));
        imageView.setPreferredSize(new Dimension(400,400));
        imageView.setVisible(true);
        imageView.setBackground(Color.white);
        
        //Adding panels containing the buttons and textfields
        add(buttonPanel);
        add(fieldPanel);
        
        //Setting layouts for the button panel and field panel
        buttonPanel.setLayout(new GridLayout(9,1));
        fieldPanel.setLayout(new GridLayout(9,1));
        
        //GridLayout for the whole frame
        setLayout(new GridLayout(2,2));
        
        //Setting the color scheme for the buttons and adding the text fields and buttons
        tree.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(tree);
        fieldPanel.add(f1);
        inOrder.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(inOrder);
        fieldPanel.add(f2);
        postOrder.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(postOrder);
        fieldPanel.add(f3);
        preOrder.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(preOrder);
        fieldPanel.add(f4);
        leafNodes.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(leafNodes);
        fieldPanel.add(f5);
        nonLeafNodes.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(nonLeafNodes);
        fieldPanel.add(f6);
        image.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(image);
        fieldPanel.add(f7);
        search.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(search);
        fieldPanel.add(f8);
        buttonPanel.add(reset);
        reset.setBackground(Color.black);
        reset.setForeground(Color.white);
        fieldPanel.add(exit);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        
        //Adding ActionListeners to all the buttons
        tree.addActionListener(this);
        inOrder.addActionListener(this);
        postOrder.addActionListener(this);
        preOrder.addActionListener(this);
        image.addActionListener(this);
        search.addActionListener(this);
        leafNodes.addActionListener(this);
        nonLeafNodes.addActionListener(this);
        reset.addActionListener(this);
        exit.addActionListener(this);
        
        //All buttons other than Tree, Reset and Exit are disabled by default
        inOrder.setEnabled(false);
        postOrder.setEnabled(false);
        preOrder.setEnabled(false);
        leafNodes.setEnabled(false);
        nonLeafNodes.setEnabled(false);
        image.setEnabled(false);
        search.setEnabled(false);
        f2.setEditable(false);
        f3.setEditable(false);
        f4.setEditable(false);
        f5.setEditable(false);
        f6.setEditable(false);
        f7.setEditable(false);
        
        //Finishing up
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public static void main(String[] args) {
        new JavaApplication2();        
    }
    
    private static Node insert(Node node, int data)   {
        //Function to insert given data into the tree
        //according to the Binary Tree property
        if (node == null)
            node = new Node(data);
        else
        {
            if (data <= node.data)
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }
        return node;
    }     
    
    public  void leafNodes(Node node) {
        //Function to compute the leaf nodes in a Binary Tree
        if(node != null && node.left == null && node.right == null) {
            lNodes += node.data + " ";
        }
        if(node.right != null) {
            leafNodes(node.right);
        }
        if(node.left != null) {
            leafNodes(node.left);
        }
    }
    
    public static boolean isLeaf(Node node) {
        //Function to determine if passed node is a leaf
        if(node != null && node.left == null && node.right == null && node != null) {
            return true;
        }
        return false;
    }
    public void nonLeafNodes(Node node) {
        //Function to compute the non-leaf nodes in a binary tree
        if(!isLeaf(node)) {
            nlNodes += node.data + " ";
            if(node.left != null)
                nonLeafNodes(node.left);
            if(node.right != null)
                nonLeafNodes(node.right);
        }
    }
    public void InOrder(Node root)  {
        //Function to perform the inorder traversal of a binary tree
        if(root != null)
        {   InOrder(root.left);
            iOrder += root.data + " ";
            InOrder(root.right);
        }
    }
    
    public void PreOrder(Node root)  {
        //Function to perform the preorder traversal of a binary tree
        if(root != null)
        {           
            prOrder += root.data + " ";
            PreOrder(root.left);
            PreOrder(root.right);
        }
    }
    
    public void PostOrder(Node root)  {
        //Function to perform the postoder traversal of a binary tree
        if(root != null)
        {
            PostOrder(root.left);
            PostOrder(root.right);
            pOrder += root.data + " ";
        }        
    }
    private static void Image(Node node) {
        //Function to create an image of passed tree (pass the root)
        if (node != null) { 
        Image(node.left); 
        Image(node.right);
        Node temp = node.left; 
        node.left = node.right; 
        node.right = temp; 
        }
    }
    
    private boolean searchTree(Node node, int data) {
        //Recursive function to perform search of passed data on a Binary Tree
        //If data is found, returns true else false
        Graphics g = getGraphics();
        try {
                   Thread.sleep(500);
               } catch (InterruptedException ex) {
                   Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
               }
        if (node == null) { 
            return false; 
        }
        
        //if data is found, its node is found and is changed to green color
        if (data == node.data) { 
           int[] temp = searchInCoords(node.data);
           if(temp[0] != 0 && temp[1] != 0) {
                g.setColor(Color.green);               
                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
                g.setColor(Color.black);
                g.drawString(node.data + "", temp[0] + 20, temp[1] + 50);   
           }
           return true; 
        } 
        
        //if data is not found, the current node is changed to yellow color and the search is called recursively
        //on the left subtree since data is smaller than current node data
        else if (data<node.data) { 
            int[] temp = searchInCoords(node.data);
            if(temp[0] != 0 && temp[1] != 0) {
                g.setColor(Color.yellow);                
                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
                g.setColor(Color.black);
                g.drawString(node.data + "", temp[0] + 20, temp[1] + 50);   
            }
            return searchTree(node.left, data); 
        } 
        
        //if data is not found, the current node is changed to yellow color and the search is called recursively
        //on the right subtree since data is greater than current node data
        else { 
            int[] temp = searchInCoords(node.data);
            if(temp[0] != 0 && temp[1] != 0) {
                g.setColor(Color.yellow);
                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
                g.setColor(Color.black);
                g.drawString(node.data + "", temp[0] + 20, temp[1] + 50);   
           }
            return searchTree(node.right, data); 
        } 
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
       //ActionListener 
       if(e.getSource() == tree) {
            String str = f1.getText();
            try {
                //Input is split using "," and is parsed
                String[] treeSplit = str.split(",");
                numberOfNodes = treeSplit.length;
                
                //Root node created
                Node temp = new Node(Integer.parseInt(treeSplit[0]));
                f2.setText("");
                f3.setText("");
                f4.setText("");
                f5.setText("");
                f6.setText("");
                f7.setText("");
                f8.setText("");
                
                //Iterate through the split input and add nodes to the tree
                for(int i=1; i<treeSplit.length;i++) {
                    temp = insert(temp,Integer.parseInt(treeSplit[i]));            
                }
                
                //Set root to temp
                root = temp;
                imageNode = null;
                
                //Paint the tree on the panel
                view.paintTree();
                
                //Indicate that the tree has been painted
                treePainted = true;
                imagePainted = false;
                
                //Enable binary tree function buttons
                inOrder.setEnabled(true);
                postOrder.setEnabled(true);
                preOrder.setEnabled(true);
                leafNodes.setEnabled(true);
                nonLeafNodes.setEnabled(true);
                image.setEnabled(true);
                search.setEnabled(true);
            } catch (Exception exception) { 
                JOptionPane.showMessageDialog(null, "Input not in correct format\nCorrect format eg. 1,7,3,9");
            f1.setText("");}
       }
       else if(e.getSource() == inOrder) {
           //Perform inorder traversal and display it
           iOrder = "";
           InOrder(root);
           f2.setText(iOrder);
       }
       else if(e.getSource() == postOrder) {
           //Perform postorder traversal and display it
           pOrder = "";
           PostOrder(root);
           f3.setText(pOrder);
       }
       else if(e.getSource() == preOrder) {
           //Perform preorder traversal and display it
           prOrder = "";
           PreOrder(root);
           f4.setText(prOrder);
       }
       else if(e.getSource() == leafNodes) {
           //calculate leaf nodes and display them
           lNodes = "";
           leafNodes(root);
           f5.setText(lNodes);
       }
       else if(e.getSource() == nonLeafNodes) {
           //calculate nonleafnodes and display them
           nlNodes = "";
           nonLeafNodes(root);
           f6.setText(nlNodes);
       }
       else if(e.getSource() == image) {
            String str = f1.getText();
            String[] treeSplit = str.split(",");
            
            //Creating image Binary Tree
            Node temp = new Node(Integer.parseInt(treeSplit[0]));
            for(int i=1; i<treeSplit.length;i++) {
                temp= insert(temp,Integer.parseInt(treeSplit[i]));            
            }
            f7.setText("Tree Image displayed");
            
            //Compute the Image
            Image(temp);
            imageNode = temp;
            
            //Display the image Tree
            imageView.paintImage();
            
            //Indicate image has been displayed
            imagePainted = true;
       }
       else if(e.getSource() == search) {
           if(f8.getText() != null) {
               try {
                //Parse search value
                int x = Integer.parseInt(f8.getText()); 
                view.paintTree();
                
                //Search parsed value in the tree
                if(searchTree(root,x)) {
                    //if value is found, inform user
                    f8.setFont(font);
                    f8.setForeground(Color.green);
                    f8.setText(x + " is in the Tree");
                }
                else {
                    //if value is not found, inform user
                    f8.setFont(font);
                    f8.setForeground(Color.red);
                    f8.setText(x + " is not in the Tree");
                }
               } catch(Exception ex) { JOptionPane.showMessageDialog(null, "Incorrect input format"); }
           }
           
       }
       else if(e.getSource() == reset) {
           //Reset the program
           dispose();
           new JavaApplication2();
       }
       else if(e.getSource() == exit) {
           //Exit from the program
           dispose();
       }
     }

    private int[] searchInCoords(int x) {
        //Search for the coordinates of the passed value
        //used to find the node coordinates for passed data
        for(int i=0; i<nodeCoords.length; i++) {
            if(x == nodeCoords[i][2]) {
                int[] temp = {nodeCoords[i][0], nodeCoords[i][1]};
                return temp;
            }
        }
        return new int[2];
    }
    
class Tree extends JPanel {   
    private int circleRadius = 15;                                                    //node radius
    private int verticalSeperation = 30;                                                      //Vertical Gap between two nodes in a Binary Tree
       
    protected void paintTree() {
      Graphics g = getGraphics();
      if(root != null) {                
            displayTree(g, root, getWidth()/2, 35, getWidth()/4);        
      }
    }
    
    protected void paintImage() {
        Graphics g = getGraphics();
        if(imageNode != null) {              
            displayTree(g, imageNode, getWidth()/2, 35, getWidth()/4);        
      }
    }
        
    
    private void displayTree(Graphics g, Node node, int x, int y, int horizatalSeperation) {
      //Function to display a subtree with root as x,y        
      g.setColor(Color.CYAN);
      g.fillOval(x - circleRadius, y - circleRadius, 2 * circleRadius, 2 * circleRadius);
      
      //Store the coordinates of the node, to be used for searching
      nodeCoords[counter][0] = x - circleRadius;
      nodeCoords[counter][1] = y - circleRadius;
      nodeCoords[counter][2] = node.data;
      counter++;
      
      //Write the data value on the node
      g.setColor(Color.black);
      g.drawString(node.data + "", x - 6, y + 4);            
      
      if (node.left != null) {
        //using drawLine to draw line to the left node
          if(!treePainted) {
          try {              
              Thread.sleep(500);
          } catch (InterruptedException ex) {
              Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
          
        //draw line
        drawLineBetween2Circles(g, x - horizatalSeperation, y + verticalSeperation, x, y);     
        
        //recursively draw the left subtree
        //decrease the horizontal and vertical gaps
        displayTree(g, node.left, x - horizatalSeperation, y + verticalSeperation, horizatalSeperation/2);        
      }          
      if (node.right != null) {
          //using drawLine to draw line to the right node
          if(!treePainted) {
          try {
          
              Thread.sleep(500);
          } catch (InterruptedException ex) {
              Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          
        //draw line
        drawLineBetween2Circles(g, x + horizatalSeperation, y + verticalSeperation, x, y);         
        //recursively draw the right subtree
        //decrease the horizontal and vertical gaps
        displayTree(g, node.right, x + horizatalSeperation, y + verticalSeperation, horizatalSeperation/2);          
      }
    }        
    
    private void drawLineBetween2Circles(Graphics g, int x1, int y1, int x2, int y2) {
        //Function to draw a line between two circles centered at x1,y1 and x2,y2
        
        //Computing adjusted coordinates
        double d = Math.sqrt(verticalSeperation * verticalSeperation + (x2 - x1) * (x2 - x1));
        int xAdjusted = (int)(x1 - circleRadius * (x1 - x2) / d);
        int yAdjusted = (int)(y1 - circleRadius * (y1 - y2) / d);
        int xAdjusted1 = (int)(x2 + circleRadius * (x1 - x2) / d);
        int yAdjusted1 = (int)(y2 + circleRadius * (y1 - y2) / d);
        
        //Draw line between adjusted coordinates
        g.drawLine(xAdjusted, yAdjusted, xAdjusted1, yAdjusted1);
    }    
  }
}
