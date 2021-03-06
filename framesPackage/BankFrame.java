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
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
	
	private MaskFormatter fmtDate;
	
	private ParkingLot parking = ParkingLot.getInstance();
	
	private JLabel profitLabel;
	private JLabel quantityLabel;
	
	private ArrayList<Integer> toCalculate = null;
	
	JPanel graphingPanel;
	private GraphingClass graphic;
	
	
	public BankFrame(Component source) {//
		super("Caixa");
		super.setLayout(new GridBagLayout());		
		GridBagConstraints modifier = new GridBagConstraints();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		super.setSize(dim.width/2,dim.height/2);
		
		
		this.setLocation((dim.width - this.getSize().width)/2,(dim.height - this.getSize().height)/2);
		getContentPane().setBackground(orange);
		
		super.setResizable(false);
		super.setUndecorated(true);
		
		if(source != null)
			source.setEnabled(false);
		
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

		graphic = new GraphingClass(toCalculate);
		graphingPanel = graphic;
		graphingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		
		JLabel intervalLabel2 = new JLabel("Gr�fico (Carros pagos/dia):");
		intervalLabel2.setForeground(Color.BLACK);
		intervalLabel2.setFont(defaultFont.deriveFont(Font.BOLD, 25));
		
		JLabel graphicInfo = new JLabel("*OBS: Cada subdivis�o inferior do gr�fico, representa um dia!");
		graphicInfo.setForeground(Color.BLACK);
		graphicInfo.setFont(defaultFont.deriveFont(Font.ITALIC+Font.BOLD, 12));
		
		JLabel profitLabelName = new JLabel("Lucro neste invervalo: ");
		profitLabelName.setForeground(Color.BLACK);
		profitLabelName.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		profitLabel = new JLabel("R$ --,--");
		profitLabel.setForeground(Color.BLACK);
		profitLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		JLabel quantityLabelName = new JLabel("Quantidade de ve�culos total: ");
		quantityLabelName.setForeground(Color.BLACK);
		quantityLabelName.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
		quantityLabel = new JLabel("-");
		quantityLabel.setForeground(Color.BLACK);
		quantityLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14));
		
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
		configurationPanelLayout.putConstraint(SpringLayout.EAST, initialLabel, -30, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, initialDate, 0, SpringLayout.VERTICAL_CENTER, initialLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, initialDate, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, initialDate, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, finalLabel, 10, SpringLayout.SOUTH, initialLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, finalLabel, 0, SpringLayout.WEST, initialLabel);
		
		configurationPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, finalDate, 0, SpringLayout.VERTICAL_CENTER, finalLabel);
		configurationPanelLayout.putConstraint(SpringLayout.WEST, finalDate, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
		configurationPanelLayout.putConstraint(SpringLayout.EAST, finalDate, -30, SpringLayout.EAST, configurationPanel);
		
		configurationPanelLayout.putConstraint(SpringLayout.NORTH, confirmButton, 30, SpringLayout.SOUTH, finalLabel);
		configurationPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirmButton, 0, SpringLayout.HORIZONTAL_CENTER, configurationPanel);
	
		
		
		graphPanel.add(intervalLabel2);

		graphPanel.add(graphingPanel);
		graphPanel.add(graphicInfo);
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
		graphPanelLayout.putConstraint(SpringLayout.SOUTH, graphingPanel, -100, SpringLayout.SOUTH, graphPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, graphicInfo, 5, SpringLayout.SOUTH, graphingPanel);
		graphPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, graphicInfo, 0, SpringLayout.HORIZONTAL_CENTER, graphingPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, profitLabelName, 10, SpringLayout.SOUTH, graphicInfo);
		graphPanelLayout.putConstraint(SpringLayout.WEST, profitLabelName, 0, SpringLayout.WEST, graphingPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, profitLabel, 0, SpringLayout.VERTICAL_CENTER, profitLabelName);
		graphPanelLayout.putConstraint(SpringLayout.WEST, profitLabel, 5, SpringLayout.EAST, profitLabelName);
		
		graphPanelLayout.putConstraint(SpringLayout.NORTH, quantityLabelName, 10, SpringLayout.SOUTH, profitLabelName);
		graphPanelLayout.putConstraint(SpringLayout.WEST, quantityLabelName, 0, SpringLayout.WEST, graphingPanel);
		
		graphPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, quantityLabel, 0, SpringLayout.VERTICAL_CENTER, quantityLabelName);
		graphPanelLayout.putConstraint(SpringLayout.WEST, quantityLabel, 5, SpringLayout.EAST, quantityLabelName);

		
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
			public void actionPerformed(ActionEvent a){
				
				LocalDateTime initialDateTime;
				LocalDateTime finalDateTime;
				
				try {
					initialDateTime = parking.toLocalDateTime(initialDate.getText());
				}catch(DateTimeException | NumberFormatException e) {
					JOptionPane.showMessageDialog(BankFrame.this, "Data Inicial inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					finalDateTime = parking.toLocalDateTime(finalDate.getText());
				}catch(DateTimeException | NumberFormatException e) {
					JOptionPane.showMessageDialog(BankFrame.this, "Data Final inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!initialDateTime.isBefore(finalDateTime)) {
					JOptionPane.showMessageDialog(BankFrame.this, "Data Inicial maior do que a Data Final!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				toCalculate = parking.getQuantityArray(initialDateTime,finalDateTime);
				//graphingPanel = new GraphingClass(toCalculate);
				graphic.changeInput(toCalculate);
	
				profitLabel.setText("R$ " + parking.getPriceByInterval(initialDateTime, finalDateTime));
				quantityLabel.setText(Integer.toString(parking.getQuantity(parking.toLocalDateTime(initialDate.getText()),parking.toLocalDateTime(finalDate.getText()))));
				repaint();
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
