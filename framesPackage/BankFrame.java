package framesPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.ParseException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;

import system.ParkingLot;

public class BankFrame extends JFrame{

	private static String confirmNormalButtonName	= "confirmNormalButton.png";
    private static String confirmHoveredButtonName	= "confirmHoveredButton.png";
    private static String confirmClickedButtonName	= "confirmClickedButton.png";
    
    private static String closeNormalButtonName		= "exitNormalButton.png";
    private static String closeHoveredButtonName	= "exitHoveredButton.png";
    private static String closeClickedButtonName	= "exitClickedButton.png";
    
    private URL confirmNormalButton;
    private URL confirmHoveredButton;
    private URL confirmClickedButton;
    
    private URL closeNormalButton;
    private URL closeHoveredButton;
    private URL closeClickedButton;
	
	private final Color orange			= new Color(248,210,0);
	public static Font defaultFont		= new Font("Trebuchet MS", Font.BOLD, 30);
	
	private JFormattedTextField initialDate;
	private JFormattedTextField finalDate;
	private JFormattedTextField motorcycleModifier;	
	
	private MaskFormatter fmtDate;
	
	private ParkingLot parking;
	
	private JLabel quantityLabel;
	private int x[] = {0,0,0,0,0};
	
	
	public BankFrame(Component source) {//
		super("Caixa");
		super.setLayout(new GridBagLayout());		
		GridBagConstraints modifier = new GridBagConstraints();
		//super.setDefaultCloseOperation(JFrame.D.ISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		super.setSize(dim.width/2,dim.height/2);
		
		
		this.setLocation((dim.width - this.getSize().width)/2,(dim.height - this.getSize().height)/2);
		getContentPane().setBackground(orange);
		
		super.setResizable(false);
		super.setUndecorated(true);
		//super.setAlwaysOnTop(true);
		
		if(source != null)
			source.setEnabled(false);
		
//		URL iconURL = getClass().getResource("iconMB.png");
//		ImageIcon iconFrame = new ImageIcon(iconURL);
//		super.setIconImage(iconFrame.getImage());
//		
		getUIResources();
		
		SpringLayout bankPanelLabelLayout = new SpringLayout();
		JPanel bankLabelPanel = new JPanel(bankPanelLabelLayout);	
		bankLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		bankLabelPanel.setBackground(orange);
		
		JLabel bankLabel = new JLabel("Caixa");
		bankLabel.setForeground(Color.BLACK);
		bankLabel.setFont(defaultFont);
		
		JButton closeButton = new JButton(new ImageIcon(closeNormalButton));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setContentAreaFilled(false);
        closeButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	closeButton.setIcon(new ImageIcon(closeHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	closeButton.setIcon(new ImageIcon(closeNormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	closeButton.setIcon(new ImageIcon(closeClickedButton));
            }
        });
        
        
		
		SpringLayout configurationPanelLayout = new SpringLayout();
		JPanel configurationPanel = new JPanel(configurationPanelLayout);
		configurationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		configurationPanel.setBackground(Color.darkGray);
		
		JLabel intervalLabel = new JLabel("Intervalo de dias:");
		intervalLabel.setForeground(Color.BLACK);
		intervalLabel.setFont(defaultFont.deriveFont(Font.BOLD, 25));
		
		try{
			fmtDate = new MaskFormatter("##/##/####");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		fmtDate.setValidCharacters("0123456789");
		
		JLabel initialLabel = new JLabel("Inicial: ");
		initialLabel.setForeground(Color.BLACK);
		initialLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		initialDate = new JFormattedTextField(fmtDate);
		initialDate.setFont(defaultFont.deriveFont(Font.PLAIN, 14));
		initialDate.setHorizontalAlignment(JLabel.CENTER);
		initialDate.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel finalLabel = new JLabel("Final: ");
		finalLabel.setForeground(Color.BLACK);
		finalLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		finalDate = new JFormattedTextField(fmtDate);
		finalDate.setFont(defaultFont.deriveFont(Font.PLAIN, 14));
		finalDate.setHorizontalAlignment(JLabel.CENTER);
		finalDate.setBorder(BorderFactory.createEmptyBorder());

		
		JButton confirmButton = new JButton(new ImageIcon(confirmNormalButton));
		confirmButton.setBorder(BorderFactory.createEmptyBorder());
		confirmButton.setContentAreaFilled(false);
		confirmButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	confirmButton.setIcon(new ImageIcon(confirmHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	confirmButton.setIcon(new ImageIcon(confirmHoveredButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	confirmButton.setIcon(new ImageIcon(confirmClickedButton));
            }
        });
		
		SpringLayout graphPanelLayout = new SpringLayout();
		JPanel graphPanel = new JPanel(graphPanelLayout);
		graphPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		graphPanel.setBackground(Color.darkGray);
		//graphPanel.setPreferredSize(new Dimension(300,300));
		
		JPanel graphingPanel = new GraphingClass(x);
		//graphingPanel = drawPanel;
		/*
		JFrame frame = new JFrame();
		frame.getContentPane().add(graphingPanel);
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		graphingPanel.add(drawPanel);
		graphingPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, drawPanel, 0, SpringLayout.VERTICAL_CENTER, graphingPanel);
		graphingPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, drawPanel, 0, SpringLayout.HORIZONTAL_CENTER, graphingPanel);
		*/
		
		JLabel intervalLabel2 = new JLabel("Gráfico/Total:");
		intervalLabel2.setForeground(Color.BLACK);
		intervalLabel2.setFont(defaultFont.deriveFont(Font.BOLD, 25));
		
		JLabel profitLabelName = new JLabel("Lucro neste invervalo: ");
		profitLabelName.setForeground(Color.BLACK);
		profitLabelName.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		JLabel profitLabel = new JLabel("R$" + 30.45);
		profitLabel.setForeground(Color.BLACK);
		profitLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		JLabel quantityLabelName = new JLabel("Quantidade de veículos: ");
		quantityLabelName.setForeground(Color.BLACK);
		quantityLabelName.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		quantityLabel = new JLabel("5");
		quantityLabel.setForeground(Color.BLACK);
		quantityLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		/*
		JFormattedTextField initialDate2 = new JFormattedTextField(fmtDate);
		initialDate2.setFont(defaultFont.deriveFont(Font.PLAIN, 14));
		initialDate2.setHorizontalAlignment(JLabel.CENTER);
		initialDate2.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel finalLabel2 = new JLabel("Final: ");
		finalLabel2.setForeground(Color.BLACK);
		finalLabel2.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		JFormattedTextField finalDate2 = new JFormattedTextField(fmtDate);
		finalDate2.setFont(defaultFont.deriveFont(Font.PLAIN, 14));
		finalDate2.setHorizontalAlignment(JLabel.CENTER);
		finalDate2.setBorder(BorderFactory.createEmptyBorder());
		*/
		
		bankLabelPanel.add(closeButton);
		bankLabelPanel.add(bankLabel);
		
		bankPanelLabelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, bankLabel, 0, SpringLayout.VERTICAL_CENTER, bankLabelPanel);
		bankPanelLabelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bankLabel, 0, SpringLayout.HORIZONTAL_CENTER, bankLabelPanel);
		
		bankPanelLabelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, closeButton, 0, SpringLayout.VERTICAL_CENTER, bankLabelPanel);
		bankPanelLabelLayout.putConstraint(SpringLayout.EAST, closeButton, -5, SpringLayout.EAST, bankLabelPanel);

		
		configurationPanel.add(intervalLabel);
		configurationPanel.add(initialLabel);
		configurationPanel.add(initialDate);
		configurationPanel.add(finalLabel);
		configurationPanel.add(finalDate);
		configurationPanel.add(confirmButton);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, intervalLabel, 10, SpringLayout.NORTH, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, intervalLabel, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, initialLabel, 20, SpringLayout.SOUTH, intervalLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, initialLabel, 50, SpringLayout.WEST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, initialDate, 0, SpringLayout.VERTICAL_CENTER, initialLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, initialDate, 55, SpringLayout.EAST, initialLabel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, initialDate, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, finalLabel, 10, SpringLayout.SOUTH, initialLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, finalLabel, 50, SpringLayout.WEST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, finalDate, 0, SpringLayout.VERTICAL_CENTER, finalLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, finalDate, 55, SpringLayout.EAST, finalLabel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, finalDate, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, confirmButton, 30, SpringLayout.SOUTH, finalLabel);
		configurationPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirmButton, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);

		//modifier.fill = GridBagConstraints.BOTH;		
		
		
		graphPanel.add(intervalLabel2);
		/*
		graphPanel.add(initialLabel2);
		graphPanel.add(initialDate2);
		graphPanel.add(finalLabel2);
		graphPanel.add(finalDate2);
		*/
		graphPanel.add(graphingPanel);
		graphPanel.add(profitLabelName);
		graphPanel.add(profitLabel);
		graphPanel.add(quantityLabelName);
		graphPanel.add(quantityLabel);
		
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, intervalLabel2, 10, SpringLayout.NORTH, graphPanel);
		graphPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, intervalLabel2, 0, SpringLayout.HORIZONTAL_CENTER, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, graphingPanel, 10, SpringLayout.SOUTH, intervalLabel2);
		graphPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, graphingPanel, 0, SpringLayout.HORIZONTAL_CENTER, intervalLabel2);
		graphPanelLayout.putConstraint(SpringLayout.EAST, graphingPanel, -20, SpringLayout.EAST, graphPanel);
		graphPanelLayout.putConstraint(SpringLayout.WEST, graphingPanel, 20, SpringLayout.WEST, graphPanel);
		graphPanelLayout.putConstraint(SpringLayout.SOUTH, graphingPanel, -150, SpringLayout.SOUTH, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, profitLabelName, 10, SpringLayout.SOUTH, graphingPanel);
		graphPanelLayout.putConstraint(SpringLayout.WEST, profitLabelName, 50, SpringLayout.WEST, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, profitLabel, 0, SpringLayout.VERTICAL_CENTER, profitLabelName);
		graphPanelLayout.putConstraint(SpringLayout.WEST, profitLabel, 5, SpringLayout.EAST, profitLabelName);
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, quantityLabelName, 10, SpringLayout.SOUTH, profitLabelName);
		graphPanelLayout.putConstraint(SpringLayout.WEST, quantityLabelName, 50, SpringLayout.WEST, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, quantityLabel, 0, SpringLayout.VERTICAL_CENTER, quantityLabelName);
		graphPanelLayout.putConstraint(SpringLayout.WEST, quantityLabel, 5, SpringLayout.EAST, quantityLabelName);
		/*
		graphPanelLayout.putConstraint(SpringLayout.NORTH, initialLabel2, 20, SpringLayout.SOUTH, intervalLabel2);
		graphPanelLayout.putConstraint(SpringLayout.WEST, initialLabel2, 50, SpringLayout.WEST, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, initialDate2, 0, SpringLayout.VERTICAL_CENTER, initialLabel2);
		graphPanelLayout.putConstraint(SpringLayout.WEST, initialDate2, 55, SpringLayout.EAST, initialLabel2);
		graphPanelLayout.putConstraint(SpringLayout.EAST, initialDate2, -30, SpringLayout.EAST, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, finalLabel2, 10, SpringLayout.SOUTH, initialLabel2);
		graphPanelLayout.putConstraint(SpringLayout.WEST, finalLabel2, 50, SpringLayout.WEST, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, finalDate2, 0, SpringLayout.VERTICAL_CENTER, finalLabel2);
		graphPanelLayout.putConstraint(SpringLayout.WEST, finalDate2, 55, SpringLayout.EAST, finalLabel2);
		graphPanelLayout.putConstraint(SpringLayout.EAST, finalDate2, -30, SpringLayout.EAST, graphPanel);
		*/

		
		modifier.insets = new Insets(10,10,10,10);
		
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weightx = 1;
		modifier.weighty = 0.30;
		modifier.gridx = 0;
		modifier.gridy = 0;
		modifier.gridwidth = 2;
	
		add(bankLabelPanel, modifier);
		
		modifier.weightx = 0.75;
		modifier.weighty = 1;
		modifier.gridx = 0;
		modifier.gridy = 1;
		modifier.gridwidth = 1;
		
		add(configurationPanel, modifier);
		
		//modifier.fill = GridBagConstraints.BOTH;
		modifier.weightx = 1;
		modifier.weighty = 1;
		modifier.gridx = 1;
		modifier.gridy = 1;
		
		add(graphPanel, modifier);
		
		setVisible(true);
		//---------------------------------------------------------------------------------Listeners
		confirmButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent a) {
				int tot=0;
				//teste
				for(int i=0; i<5; i++)
					{
						x[i]++;
						tot+=x[i];
					}
				repaint();
				quantityLabel.setText(" " + tot);
				if(source != null)
					source.setEnabled(true);
			}
		});
		
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent a) {
				if(source != null)
					source.setEnabled(true);
				dispose();
			}
		});
		
		addWindowListener(new java.awt.event.WindowAdapter(){
			@Override
			public void windowClosing(java.awt.event.WindowEvent event)
			{
				//source.setEnabled(true);
				//X on close
			}
		});
	}


	private void getUIResources() {
        confirmNormalButton		= ParkingFrame.class.getResource(confirmNormalButtonName);
        confirmHoveredButton	= ParkingFrame.class.getResource(confirmHoveredButtonName);
        confirmClickedButton	= ParkingFrame.class.getResource(confirmClickedButtonName);
        
        closeNormalButton		= ParkingFrame.class.getResource(closeNormalButtonName);
        closeHoveredButton	= ParkingFrame.class.getResource(closeHoveredButtonName);
        closeClickedButton	= ParkingFrame.class.getResource(closeClickedButtonName);
	}

}
