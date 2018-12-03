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

@SuppressWarnings("serial")
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
	private JFormattedTextField perHourDivisorModifier;
	
	private MaskFormatter fmtPrice;
	
	private ParkingLot parking = ParkingLot.getInstance();
	
	
	public ConfigurationFrame(Component source) {
		super("Configuração");
		super.setLayout(new GridBagLayout());		
		GridBagConstraints modifier = new GridBagConstraints();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		super.setSize(dim.width/2,dim.height/2);
		
		
		this.setLocation((dim.width - this.getSize().width)/2,(dim.height - this.getSize().height)/2);
		getContentPane().setBackground(orange);
		
		super.setResizable(false);
		super.setUndecorated(true);
		//super.setAlwaysOnTop(true);
		
		if(source != null)
			source.setEnabled(false);

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
		
		JLabel baseValueconfigurationLabel = new JLabel("Modificar Valores");
		baseValueconfigurationLabel.setForeground(Color.BLACK);
		baseValueconfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 25));
		
		MaskFormatter fmtDivisor = null;
		try{
			fmtPrice = new MaskFormatter("R$ ##,##");
			fmtDivisor = new MaskFormatter("##");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		fmtPrice.setValidCharacters("0123456789");
		fmtDivisor.setValidCharacters("0123456789");
		
		
		
		JLabel carConfigurationLabel = new JLabel("Carro: ");
		carConfigurationLabel.setForeground(Color.BLACK);
		carConfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		carModifier = new JFormattedTextField(fmtPrice);
		carModifier.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		carModifier.setHorizontalAlignment(JLabel.CENTER);
		carModifier.setBorder(BorderFactory.createEmptyBorder());
		
		
		JLabel motorcycleConfigurationLabel = new JLabel("Moto: ");
		motorcycleConfigurationLabel.setForeground(Color.BLACK);
		motorcycleConfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		motorcycleModifier = new JFormattedTextField(fmtPrice);
		motorcycleModifier.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		motorcycleModifier.setHorizontalAlignment(JLabel.CENTER);
		motorcycleModifier.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel miniTruckConfigurationLabel = new JLabel("Caminhonete: ");
		miniTruckConfigurationLabel.setForeground(Color.BLACK);
		miniTruckConfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		miniTruckModifier = new JFormattedTextField(fmtPrice);
		miniTruckModifier.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		miniTruckModifier.setHorizontalAlignment(JLabel.CENTER);
		miniTruckModifier.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel perHourDivisorConfigurationLabel = new JLabel("Divisor Por Hora:");
		perHourDivisorConfigurationLabel.setForeground(Color.BLACK);
		perHourDivisorConfigurationLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		perHourDivisorModifier = new JFormattedTextField(fmtDivisor);
		perHourDivisorModifier.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		perHourDivisorModifier.setHorizontalAlignment(JLabel.CENTER);
		perHourDivisorModifier.setBorder(BorderFactory.createEmptyBorder());
		
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
            	confirmButton.setIcon(new ImageIcon(confirmNormalButton));
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
		modifier.gridwidth = 2;
	
		add(configurationLabelPanel, modifier);
		modifier.gridwidth = 1;
		
		configurationPanel.add(baseValueconfigurationLabel);
		configurationPanel.add(carConfigurationLabel);
		configurationPanel.add(carModifier);
		configurationPanel.add(motorcycleConfigurationLabel);
		configurationPanel.add(motorcycleModifier);
		configurationPanel.add(miniTruckConfigurationLabel);
		configurationPanel.add(miniTruckModifier);
		configurationPanel.add(perHourDivisorConfigurationLabel);
		configurationPanel.add(perHourDivisorModifier);
		configurationPanel.add(confirmButton);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, baseValueconfigurationLabel, 15, SpringLayout.NORTH, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, baseValueconfigurationLabel, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.SOUTH, carConfigurationLabel, -10, SpringLayout.NORTH, motorcycleConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, carConfigurationLabel, 0, SpringLayout.WEST, perHourDivisorConfigurationLabel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carModifier, 0, SpringLayout.VERTICAL_CENTER, carConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, carModifier, 0, SpringLayout.NORTH, carConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.SOUTH, carModifier, 0, SpringLayout.SOUTH, carConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, carModifier, 20, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, carModifier, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.SOUTH, motorcycleConfigurationLabel, -5, SpringLayout.VERTICAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, motorcycleConfigurationLabel, 0, SpringLayout.WEST, perHourDivisorConfigurationLabel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, motorcycleModifier, 0, SpringLayout.VERTICAL_CENTER, motorcycleConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, motorcycleModifier, 0, SpringLayout.NORTH, motorcycleConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.SOUTH, motorcycleModifier, 0, SpringLayout.SOUTH, motorcycleConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, motorcycleModifier, 20, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, motorcycleModifier, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, miniTruckConfigurationLabel, 5, SpringLayout.VERTICAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, miniTruckConfigurationLabel, 0, SpringLayout.WEST, perHourDivisorConfigurationLabel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, miniTruckModifier, 0, SpringLayout.VERTICAL_CENTER, miniTruckConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, miniTruckModifier, 0, SpringLayout.NORTH, miniTruckConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.SOUTH, miniTruckModifier, 0, SpringLayout.SOUTH, miniTruckConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, miniTruckModifier, 20, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, miniTruckModifier, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, perHourDivisorConfigurationLabel, 10, SpringLayout.SOUTH, miniTruckConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, perHourDivisorConfigurationLabel, 10, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, perHourDivisorModifier, 0, SpringLayout.VERTICAL_CENTER, perHourDivisorConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, perHourDivisorModifier, 0, SpringLayout.NORTH, perHourDivisorConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.SOUTH, perHourDivisorModifier, 0, SpringLayout.SOUTH, perHourDivisorConfigurationLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, perHourDivisorModifier, 20, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, perHourDivisorModifier, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.SOUTH, confirmButton, -15, SpringLayout.SOUTH, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirmButton, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weightx = 1;
		modifier.weighty = 1;
		modifier.gridx = 1;
		modifier.gridy = 1;
		
		add(configurationPanel, modifier);
		
		
		SpringLayout defaultPanelLayout = new SpringLayout();
		JPanel defaultPanel = new JPanel(defaultPanelLayout);
		defaultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		defaultPanel.setBackground(Color.darkGray);
		
		
		JLabel baseValueDefaultLabel = new JLabel("Preço por Hora");
		baseValueDefaultLabel.setForeground(Color.BLACK);
		baseValueDefaultLabel.setFont(defaultFont.deriveFont(Font.BOLD, 25));
		
		JLabel motorcycleDefaultLabel = new JLabel("Moto:");
		motorcycleDefaultLabel.setForeground(Color.BLACK);
		motorcycleDefaultLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		JLabel motorcycleDefaultNumberLabel = new JLabel("R$" + String.format("%.2f", parking.getMultMotorcycle()));
		motorcycleDefaultNumberLabel.setForeground(Color.BLACK);
		motorcycleDefaultNumberLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));

		JLabel carDefaultLabel = new JLabel("Carro:");
		carDefaultLabel.setForeground(Color.BLACK);
		carDefaultLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		JLabel carDefaultNumberLabel = new JLabel("R$" + String.format("%.2f", parking.getMultCar()));
		carDefaultNumberLabel.setForeground(Color.BLACK);
		carDefaultNumberLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		JLabel miniTruckDefaultLabel = new JLabel("Caminhonete:");
		miniTruckDefaultLabel.setForeground(Color.BLACK);
		miniTruckDefaultLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		JLabel miniTruckDefaultNumberLabel = new JLabel("R$" + String.format("%.2f",parking.getMultMiniTruck()));
		miniTruckDefaultNumberLabel.setForeground(Color.BLACK);
		miniTruckDefaultNumberLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		
		JLabel perHourDivisorDefaultLabel = new JLabel("Divisor por hora:");
		perHourDivisorDefaultLabel.setForeground(Color.BLACK);
		perHourDivisorDefaultLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		JLabel perHourDivisorDefaultNumberLabel = new JLabel(String.format("%.0f", parking.getPerHourDivisor()));
		perHourDivisorDefaultNumberLabel.setForeground(Color.BLACK);
		perHourDivisorDefaultNumberLabel.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		
		
		
		defaultPanel.add(baseValueDefaultLabel);
		defaultPanel.add(motorcycleDefaultLabel);
		defaultPanel.add(motorcycleDefaultNumberLabel);
		defaultPanel.add(carDefaultLabel);
		defaultPanel.add(carDefaultNumberLabel);
		defaultPanel.add(miniTruckDefaultLabel);
		defaultPanel.add(miniTruckDefaultNumberLabel);
		defaultPanel.add(perHourDivisorDefaultLabel);
		defaultPanel.add(perHourDivisorDefaultNumberLabel);
		
		
		defaultPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, baseValueDefaultLabel, 0, SpringLayout.HORIZONTAL_CENTER, defaultPanel);
		defaultPanelLayout.putConstraint(SpringLayout.NORTH, baseValueDefaultLabel, 15, SpringLayout.NORTH, defaultPanel);
		
		defaultPanelLayout.putConstraint(SpringLayout.WEST, carDefaultLabel, 0, SpringLayout.WEST, motorcycleDefaultLabel);
		defaultPanelLayout.putConstraint(SpringLayout.SOUTH, carDefaultLabel, -10, SpringLayout.NORTH, motorcycleDefaultLabel);
		
		defaultPanelLayout.putConstraint(SpringLayout.WEST, carDefaultNumberLabel, 35, SpringLayout.HORIZONTAL_CENTER, defaultPanel);
		defaultPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carDefaultNumberLabel, 0, SpringLayout.VERTICAL_CENTER, carDefaultLabel);
		
		defaultPanelLayout.putConstraint(SpringLayout.WEST, motorcycleDefaultLabel, 0, SpringLayout.WEST, miniTruckDefaultLabel);
		defaultPanelLayout.putConstraint(SpringLayout.SOUTH, motorcycleDefaultLabel, -5, SpringLayout.VERTICAL_CENTER, defaultPanel);
		
		defaultPanelLayout.putConstraint(SpringLayout.WEST, motorcycleDefaultNumberLabel, 35, SpringLayout.HORIZONTAL_CENTER, defaultPanel);
		defaultPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, motorcycleDefaultNumberLabel, 0, SpringLayout.VERTICAL_CENTER, motorcycleDefaultLabel);
		
		defaultPanelLayout.putConstraint(SpringLayout.WEST, miniTruckDefaultLabel, 0, SpringLayout.WEST, perHourDivisorDefaultLabel);
		defaultPanelLayout.putConstraint(SpringLayout.NORTH, miniTruckDefaultLabel, 5, SpringLayout.VERTICAL_CENTER, defaultPanel);
		
		defaultPanelLayout.putConstraint(SpringLayout.WEST, miniTruckDefaultNumberLabel, 35, SpringLayout.HORIZONTAL_CENTER, defaultPanel);
		defaultPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, miniTruckDefaultNumberLabel, 0, SpringLayout.VERTICAL_CENTER, miniTruckDefaultLabel);
		
		defaultPanelLayout.putConstraint(SpringLayout.EAST, perHourDivisorDefaultLabel, 10, SpringLayout.HORIZONTAL_CENTER, defaultPanel);
		defaultPanelLayout.putConstraint(SpringLayout.NORTH, perHourDivisorDefaultLabel, 10, SpringLayout.SOUTH, miniTruckDefaultLabel);
		
		defaultPanelLayout.putConstraint(SpringLayout.WEST, perHourDivisorDefaultNumberLabel, 35, SpringLayout.HORIZONTAL_CENTER, defaultPanel);
		defaultPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, perHourDivisorDefaultNumberLabel, 0, SpringLayout.VERTICAL_CENTER, perHourDivisorDefaultLabel);

		
		
		
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weightx = 1;
		modifier.weighty = 1;
		modifier.gridx = 0;
		modifier.gridy = 1;
		
		add(defaultPanel, modifier);
		
		
		setVisible(true);
		//---------------------------------------------------------------------------------Listeners
		confirmButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent a) {
				double carMult, motorcycleMult, miniTruckMult, perHourDivisor;
				String temp;
				
				try{
	
					temp = carModifier.getText().substring(2).replace(",", ".");
					if(carModifier.getText().equals("R$   ,  ")) {
						carMult = parking.getMultCar();
					}
					else carMult = Float.parseFloat(temp);
					
                    temp = motorcycleModifier.getText().substring(2).replace(",",".");
                    if(motorcycleModifier.getText().equals("R$   ,  ")) {
                    	motorcycleMult = parking.getMultMotorcycle();
                    }
                    else motorcycleMult = Float.parseFloat(temp);
                    
                    temp = miniTruckModifier.getText().substring(2).replace(",",".");
                    if(miniTruckModifier.getText().equals("R$   ,  ")) {
                    	miniTruckMult = parking.getMultMiniTruck();
                    }
                    else miniTruckMult = Float.parseFloat(temp);
                    
                    if(perHourDivisorModifier.getText().equals("  ")) {
                    	perHourDivisor = parking.getPerHourDivisor();
                    }
                    else perHourDivisor = Float.parseFloat(perHourDivisorModifier.getText());
				
					parking.setMultipliers(carMult, motorcycleMult, miniTruckMult, perHourDivisor);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(ConfigurationFrame.this,"Informe valores válidos!","Informativo",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				carDefaultNumberLabel.setText("R$" + String.format("%.2f",parking.getMultCar()));
				motorcycleDefaultNumberLabel.setText("R$" + String.format("%.2f",parking.getMultMotorcycle()));
				miniTruckDefaultNumberLabel.setText("R$" + String.format("%.2f",parking.getMultMiniTruck()));
				perHourDivisorDefaultNumberLabel.setText(String.format("%.0f", parking.getPerHourDivisor()));
				
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
