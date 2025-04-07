
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class Main

{
	private static final String HISTORY_FILE = "bmi_history.properties";
	   
	public static void main(String[] args) {
	      
	JFrame frame = new JFrame();
	frame.setSize(900, 600);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //for close
	frame.setUndecorated(true); // default remove 
	frame.getContentPane().setBackground(Color.GRAY); 
	frame.setLayout(null);

	        
            JPanel titleBar = new JPanel();
	        titleBar.setBackground(Color.WHITE);
	        titleBar.setBounds(0, 0, 900, 30);
	        titleBar.setLayout(null);

	     
	        JButton closeBtn = new JButton();
	        closeBtn.setText("X");
	        closeBtn.setBounds(850, 0, 50, 30);
	        closeBtn.setBackground(Color.RED);
	        closeBtn.setForeground(Color.WHITE);
	        closeBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);//for exit
	            }
	        });

	       
	        JButton maximizeBtn = new JButton();
	        maximizeBtn.setText("⬜");
	        maximizeBtn.setBounds(800, 0, 50, 30);
	        maximizeBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
	                    frame.setExtendedState(JFrame.NORMAL); 
	                } else {
	                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	                }
	            }
	        });


	        JButton minimizeBtn = new JButton();
	        minimizeBtn.setText("-");
	        minimizeBtn.setBounds(750, 0, 50, 30);
	        minimizeBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	             frame.setExtendedState(JFrame.ICONIFIED);
	            }
	        });

	        
	        JLabel titleBarL = new JLabel();
	        titleBarL.setText("BMI Calculator");
	        titleBarL.setBounds(400, 5, 200, 20);
	        titleBarL.setForeground(Color.BLACK);
	        titleBarL.setFont(new Font("Arial", Font.BOLD, 14));

	       
	        titleBar.add(titleBarL);
	        titleBar.add(minimizeBtn);
	        titleBar.add(maximizeBtn);
	        titleBar.add(closeBtn);
	        frame.add(titleBar);

	     
	        JLabel l1 = new JLabel();
	        l1.setText("Select unit :");
	        l1.setForeground(Color.WHITE);
	        l1.setFont(new Font("Arial", Font.PLAIN, 15));
	        l1.setBounds(300, 70, 100, 30);
	        frame.add(l1);

	        JRadioButton rb1 = new JRadioButton();
	        rb1.setText("Metric");
	        rb1.setBounds(405, 70, 100, 30);
	        frame.add(rb1);

	        JRadioButton rb2 = new JRadioButton();
	        rb2.setText("Imperial");
	        rb2.setBounds(510, 70, 100, 30);
	        frame.add(rb2);

	        ButtonGroup bg = new ButtonGroup();
	        bg.add(rb1);
	        bg.add(rb2);

	        JLabel l2 = new JLabel();
	        l2.setText("Height : ");
	        l2.setForeground(Color.WHITE);
	        l2.setFont(new Font("Arial", Font.PLAIN, 15));
	        l2.setBounds(300, 120, 70, 20);
	        frame.add(l2);

	        JTextField tf1 = new JTextField();
	        tf1.setBounds(380, 120, 70, 20);
	        frame.add(tf1);

	        JLabel l3 = new JLabel();
	        l3.setText("Weight : ");
	        l3.setForeground(Color.WHITE);
	        l3.setFont(new Font("Arial", Font.PLAIN, 15));
	        l3.setBounds(300, 165, 70, 20);
	        frame.add(l3);

	        JTextField tf2 = new JTextField();
	        tf2.setBounds(380, 165, 70, 20);
	        frame.add(tf2);

	        JButton CalculateB = new JButton();
	        CalculateB.setText("Calculate");
	        CalculateB.setBounds(50, 230, 200, 50);
	        frame.add(CalculateB);

	        JButton ResetB = new JButton();
	        ResetB.setText("Reset");
	        ResetB.setBounds(285, 230, 170, 50);
	        frame.add(ResetB);

	        JButton ViewHistoryB = new JButton();
	        ViewHistoryB.setText("View History");
	        ViewHistoryB.setBounds(480, 230, 180, 50);
	        frame.add(ViewHistoryB);

	        JButton darkMode = new JButton();
	        darkMode.setText("Dark Mode");
	        darkMode.setBounds(680, 230, 200, 50);
	        frame.add(darkMode);

	        JLabel l31 = new JLabel();
	        l31.setBounds(100, 350, 300, 30);
	        frame.add(l31);

	        JLabel l32 = new JLabel();
	        l32.setBounds(100, 380, 600, 50);
	        frame.add(l32);

	       Color darkBackgroundColor = Color.DARK_GRAY;
	       Color darkTextColor = Color.WHITE;
	       Color lightBackgroundColor = Color.GRAY;

	        
	        JLabel hUnitLabel = new JLabel();
	        hUnitLabel.setText("(m)");
	        hUnitLabel.setForeground(Color.WHITE);
	        hUnitLabel.setBounds(460, 120, 50, 20);
	        hUnitLabel.setVisible(false);  // Initially hidden
	        frame.add(hUnitLabel);

	        JLabel wUnitLabel = new JLabel();
	        wUnitLabel.setText("(kg)");
	        wUnitLabel.setForeground(Color.WHITE);
	        wUnitLabel.setBounds(460, 165, 50, 20);
	        wUnitLabel.setVisible(false);  
	        frame.add(wUnitLabel);

	      
   rb1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                hUnitLabel.setText("(m)");
	                wUnitLabel.setText("(kg)");
	                hUnitLabel.setVisible(true);  
	                wUnitLabel.setVisible(true);  
	            }
	        });

    rb2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                hUnitLabel.setText("(in)");
	                wUnitLabel.setText("(lbs)");
	                hUnitLabel.setVisible(true);  
	                wUnitLabel.setVisible(true); 
	            }
	        });

	     
	 CalculateB.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (!rb1.isSelected() && !rb2.isSelected()) {
	                    JOptionPane.showMessageDialog(frame, "Please select a unit (Metric or Imperial)!");
	                    return;
	                }

	                String heightText = tf1.getText();
	                String weightText = tf2.getText();

	                if (heightText.isEmpty() || weightText.isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Height and weight cannot be empty!");
	                    return;
	                }

	                if (!isValidNumber(heightText) || !isValidNumber(weightText)) {
	                    JOptionPane.showMessageDialog(frame, "Please enter valid positive numbers for height and weight.");
	                    return;
	                }

	                double height = Double.valueOf(heightText);
	                double weight = Double.valueOf(weightText);

	                if (height <= 0 || weight <= 0) {
	                    JOptionPane.showMessageDialog(frame, "Height and weight must be positive numbers.");
	                    return;
	                }

	                double bmi;
	                if (rb1.isSelected()) {
	                    bmi = weight / (height * height); // Metric
	                } else {
	                    bmi = (weight / (height * height)) * 703; // Imperial
	                }

	                String category;
	                String advice;
	                Color color;
	             
	               

	                if (bmi < 18.5) {
	                    category = "Underweight";
	                    
	                    color = Color.white;
	                    advice = "Try to include more proteins and healthy fats in your diet.";
	                } else if (bmi < 24.9) {
	                    category = "Normal weight";
	                    color = Color.white;
	                    advice = "Great job! Maintain a balanced diet and regular exercise.";
	                } else {
	                    category = "Overweight";
	                    color = Color.white;
	                    
	                    advice = "Consider a balanced diet and regular physical activity for a healthier weight.";
	                }

	                l31.setText(String.format("BMI: %.2f (%s)", bmi, category));
	                l31.setForeground(color);
	                l32.setText("Advice for you: " + advice);
	                l32.setForeground(color);
	                l31.setFont(new Font("serif", Font.BOLD, 14));//italic
	                l32.setFont(new Font("serif", Font.BOLD, 14));
	                
	                saveToHistory(height, weight, bmi, category);
	            
	            }
	        });

	       
	  ResetB.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                tf1.setText("");
	                tf2.setText("");
	                l31.setText("");
	                l32.setText("");
	                bg.clearSelection(); 
	                hUnitLabel.setVisible(false);  
	                wUnitLabel.setVisible(false);  
	            }
	        });

	     
	 
	        
	 ViewHistoryB.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	               
	               JDialog historyDialog = new JDialog(frame, "BMI History", true);
	            
	               historyDialog.setTitle("BMI History");
	               historyDialog.setSize(400, 300);
	                 
	         int x = frame.getX() + (frame.getWidth() / 2); 
	         int y = frame.getY() + (frame.getHeight() *1/3); 
	                    historyDialog.setLocation(x, y);
	                   
	                    historyDialog.setLayout(new BorderLayout());
	                    
	                    JTextArea historyText = new JTextArea();
	                    historyText.setEditable(false);
	                    historyText.setText(loadHistory());
	                  


	                    JButton okButton = new JButton();
	                    okButton.setText("OK");
	                    okButton.addActionListener(new ActionListener() {
	                        public void actionPerformed(ActionEvent e) {
	                            historyDialog.dispose(); // Close the dialog when OK is clicked
	                        }
	                    });
	                  
	                    JButton HButton = new JButton();
	                    HButton.setText("Reset History");
	                    HButton.addActionListener(new ActionListener() {

				
					    public void actionPerformed(ActionEvent e) {
						
						 clearHistory();
		                    historyText.setText("History Cleared!");	
					}});
	                    JPanel panel = new JPanel();
	                    panel.add(okButton);
	                    panel.add(HButton);
	                    JScrollPane scrollPane = new JScrollPane(historyText);
	                 
	                    historyDialog.add(panel, BorderLayout.SOUTH);
	                    historyDialog.add(scrollPane, BorderLayout.CENTER);
	                    historyDialog.setVisible(true);
	                  
	                }
	                
	            
	        });


	      
	        darkMode.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (frame.getContentPane().getBackground() == lightBackgroundColor) {
	                   
	                    frame.getContentPane().setBackground(darkBackgroundColor);
	                    titleBar.setBackground(Color.black);
	                    titleBarL.setForeground(Color.white);
	                    l1.setForeground(darkTextColor);
	                    l2.setForeground(darkTextColor);
	                    l3.setForeground(darkTextColor);
	                    l31.setForeground(Color.white);
	                    l32.setForeground(Color.white);
	                    closeBtn.setBackground(Color.RED);
	                    closeBtn.setForeground(darkTextColor);
	                    minimizeBtn.setBackground(Color.GRAY);
	                    minimizeBtn.setForeground(darkTextColor);
	                    maximizeBtn.setBackground(Color.GRAY);
	                    maximizeBtn.setForeground(darkTextColor);
	                    darkMode.setText("Gray Mode");
	                } else {
	                    // Switch back to light mode
	                    frame.getContentPane().setBackground(lightBackgroundColor);
	                    titleBar.setBackground(Color.white);
	                    titleBarL.setForeground(Color.black);
	                    l1.setForeground(darkTextColor);
	                    l2.setForeground(darkTextColor);
	                    l3.setForeground(darkTextColor);
	                    l31.setForeground(Color.white);
	                    l32.setForeground(Color.white);
	                    closeBtn.setBackground(Color.RED);
	                    closeBtn.setForeground(darkTextColor);
	                    minimizeBtn.setBackground(Color.white);
	                    minimizeBtn.setForeground(Color.black);
	                    maximizeBtn.setBackground(Color.white);
	                    maximizeBtn.setForeground(Color.black);
	                   darkMode.setText("Dark Mode");
	                }
	            }
	        });
	    
	        
	        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
	            public void componentResized(java.awt.event.ComponentEvent evt) {
	                // Get the width and height of the frame
	                int width = frame.getWidth();
	                int height = frame.getHeight();

	                // Adjust the title bar
	                titleBar.setBounds(0, 0, width, 30);
	                titleBarL.setBounds((width - 150) / 2, 5, 200, 20); 
	                closeBtn.setBounds(width - 50, 0, 50, 30);
	                maximizeBtn.setBounds(width - 100, 0, 50, 30);
	                minimizeBtn.setBounds(width - 150, 0, 50, 30);
	               
	              
	                
	            }
	        });

	      
	        frame.setVisible(true);
	    }
     
	 //  save the BMI data to history file
	private static void saveToHistory(double height, double weight, double bmi, String category) {
	    String entry = String.format("Height: %.2f, Weight: %.2f, BMI: %.2f (%s)%n", height, weight, bmi, category);
	    try (FileOutputStream fos = new FileOutputStream("bmi_history.txt", true)) {
	        fos.write(entry.getBytes());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	    
		 // Method to load the history from the properties file
		private static String loadHistory() {
		    try {
		        byte[] bytes = Files.readAllBytes(Paths.get("bmi_history.txt"));
		        return new String(bytes);
		    } catch (IOException e) {
		        return "No history available.";
		    }
		}

	    

	    // Method to clear the history
	    private static void clearHistory() {
	        try {
	        	Files.deleteIfExists(Paths.get("bmi_history.txt"));
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


	    

	    // if input is a valid number
	    public static boolean isValidNumber(String text) {
	        try {
	            Double.parseDouble(text);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	}


