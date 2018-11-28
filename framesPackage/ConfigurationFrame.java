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

public class ConfigurationFrame extends JFrame{

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
	
	private JFormattedTextField carModifier;
	private JFormattedTextField miniTruckModifier;
	private JFormattedTextField motorcycleModifier;	
	
	private MaskFormatter fmtPrice;
	
	private ParkingLot parking;
	
	
	public ConfigurationFrame(Component source) {//
		super("Configuração");
		super.setLayout(new GridBagLayout());		
		GridBagConstraints modifier = new GridBagConstraints();
		//super.setDefaultCloseOperation(JFrame.D.ISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		super.setSize(dim.width/4,dim.height/2);
		
		
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
		
		SpringLayout configurationPanelLabelLayout = new SpringLayout();
		JPanel configurationLabelPanel = new JPanel(configurationPanelLabelLayout);	
		configurationLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		configurationLabelPanel.setBackground(orange);
		
		JLabel configurationLabel = new JLabel("Configurações");
		configurationLabel.setForeground(Color.BLACK);
		configurationLabel.setFont(defaultFont);
		
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
		
		JLabel baseValueconfigurationLabel = new JLabel("Valor base para:");
		baseValueconfigurationLabel.setForeground(Color.BLACK);
		baseValueconfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 25));
		
		try{
			fmtPrice = new MaskFormatter("R$ ##,##");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		fmtPrice.setValidCharacters("0123456789");
		
		JLabel carConfigurationLabel = new JLabel("Carro: ");
		carConfigurationLabel.setForeground(Color.BLACK);
		carConfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		carModifier = new JFormattedTextField(fmtPrice);
		carModifier.setFont(defaultFont.deriveFont(Font.PLAIN, 14));
		carModifier.setHorizontalAlignment(JLabel.CENTER);
		carModifier.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel motorcycleConfigurationLabel = new JLabel("Moto: ");
		motorcycleConfigurationLabel.setForeground(Color.BLACK);
		motorcycleConfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		motorcycleModifier = new JFormattedTextField(fmtPrice);
		motorcycleModifier.setFont(defaultFont.deriveFont(Font.PLAIN, 14));
		motorcycleModifier.setHorizontalAlignment(JLabel.CENTER);
		motorcycleModifier.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel miniTruckConfigurationLabel = new JLabel("Caminhonete: ");
		miniTruckConfigurationLabel.setForeground(Color.BLACK);
		miniTruckConfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		miniTruckModifier = new JFormattedTextField(fmtPrice);
		miniTruckModifier.setFont(defaultFont.deriveFont(Font.PLAIN, 14));
		miniTruckModifier.setHorizontalAlignment(JLabel.CENTER);
		miniTruckModifier.setBorder(BorderFactory.createEmptyBorder());
		
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
		
		configurationLabelPanel.add(closeButton);
		configurationLabelPanel.add(configurationLabel);
		
		configurationPanelLabelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, configurationLabel, 0, SpringLayout.VERTICAL_CENTER, configurationLabelPanel);
		configurationPanelLabelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, configurationLabel, 0, SpringLayout.HORIZONTAL_CENTER, configurationLabelPanel);
		
		configurationPanelLabelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, closeButton, 0, SpringLayout.VERTICAL_CENTER, configurationLabelPanel);
		configurationPanelLabelLayout.putConstraint(SpringLayout.EAST, closeButton, -5, SpringLayout.EAST, configurationLabelPanel);
		
		modifier.insets = new Insets(10,10,10,10);
		
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weightx = 1;
		modifier.weighty = 0.30;
		modifier.gridx = 0;
		modifier.gridy = 0;
	
		add(configurationLabelPanel, modifier);

		
		configurationPanel.add(baseValueconfigurationLabel);
		configurationPanel.add(carConfigurationLabel);
		configurationPanel.add(carModifier);
		configurationPanel.add(motorcycleConfigurationLabel);
		configurationPanel.add(motorcycleModifier);
		configurationPanel.add(miniTruckConfigurationLabel);
		configurationPanel.add(miniTruckModifier);
		configurationPanel.add(confirmButton);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, baseValueconfigurationLabel, 35, SpringLayout.NORTH, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, baseValueconfigurationLabel, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, carConfigurationLabel, 20, SpringLayout.SOUTH, baseValueconfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, carConfigurationLabel, 50, SpringLayout.WEST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carModifier, 0, SpringLayout.VERTICAL_CENTER, carConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, carModifier, 55, SpringLayout.EAST, carConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, carModifier, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, motorcycleConfigurationLabel, 10, SpringLayout.SOUTH, carConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, motorcycleConfigurationLabel, 50, SpringLayout.WEST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, motorcycleModifier, 0, SpringLayout.VERTICAL_CENTER, motorcycleConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, motorcycleModifier, 55, SpringLayout.EAST, motorcycleConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, motorcycleModifier, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, miniTruckConfigurationLabel, 10, SpringLayout.SOUTH, motorcycleConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, miniTruckConfigurationLabel, 50, SpringLayout.WEST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, miniTruckModifier, 0, SpringLayout.VERTICAL_CENTER, miniTruckConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, miniTruckModifier, 10, SpringLayout.EAST, miniTruckConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, miniTruckModifier, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, confirmButton, 30, SpringLayout.SOUTH, miniTruckConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirmButton, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weightx = 1;
		modifier.weighty = 1;
		modifier.gridx = 0;
		modifier.gridy = 1;
		
		add(configurationPanel, modifier);
		setVisible(true);
		//---------------------------------------------------------------------------------Listeners
		confirmButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent a) {
				float carMult, motorcycleMult, miniTruckMult;
				String temp;
				try{
					temp = carModifier.getText().substring(2).replace(",", ".");
                    carMult = Float.parseFloat(temp);
                    temp = motorcycleModifier.getText().substring(2).replace(",",".");
                    motorcycleMult = Float.parseFloat(temp);
                    temp = miniTruckModifier.getText().substring(2).replace(",",".");
                    miniTruckMult = Float.parseFloat(temp);
					
					parking = ParkingLot.getInstance();
					parking.setMultipliers(carMult, motorcycleMult, miniTruckMult);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(ConfigurationFrame.this,"Informe um valor válido!","Informativo",JOptionPane.WARNING_MESSAGE);
					System.err.println(e);
					
					return;
				}
				if(source != null)
					source.setEnabled(true);
				dispose();
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
