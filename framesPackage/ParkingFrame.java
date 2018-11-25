package framesPackage;

import java.awt.Color;
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

//�cone 20x20
@SuppressWarnings("serial")
public class ParkingFrame extends JFrame{

	public static final String nomeEstacionamento = "GUTOMA";
	
	
    private static String configNormalButtonName	= "configNormalButton.png";
    private static String configHoveredButtonName	= "configHoveredButton.png";
    private static String configClickedButtonName	= "configClickedButton.png";
    
    private static String bankNormalButtonName		= "caixaNormalButton.png";
    private static String bankHoveredButtonName		= "caixaHoveredButton.png";
    private static String bankClickedButtonName		= "caixaClickedButton.png";
    
    private static String confirmNormalButtonName	= "confirmNormalButton.png";
    private static String confirmHoveredButtonName	= "confirmHoveredButton.png";
    private static String confirmClickedButtonName	= "confirmClickedButton.png";
    
    private static String clearNormalButtonName		= "clearNormalButton.png";
    private static String clearHoveredButtonName	= "clearHoveredButton.png";
    private static String clearClickedButtonName	= "clearClickedButton.png";
    
    private static String closeNormalButtonName		= "exitNormalButton.png";
    private static String closeHoveredButtonName	= "exitHoveredButton.png";
    private static String closeClickedButtonName	= "exitClickedButton.png";
    
    private static String plateName					= "placa.png";
    
    
    private URL configNormalButton;
    private URL configHoveredButton;
    private URL configClickedButton;
    private URL bankNormalButton;
    private URL bankHoveredButton;
    private URL bankClickedButton;
    private URL confirmNormalButton;
    private URL confirmHoveredButton;
    private URL confirmClickedButton;
    private URL clearNormalButton;
    private URL clearHoveredButton;
    private URL clearClickedButton;
    private URL closeNormalButton;
    private URL closeHoveredButton;
    private URL closeClickedButton;
    private URL carPlateURL;
    
    
    
    
    private final Color orange		= new Color(248,210,0);
    private final Color darkGray	= new Color(88,88,88);
    
    
	public static Font defaultFont	= new Font("Trebuchet MS", Font.BOLD, 22);
	
	
	private JFormattedTextField carPlateNewEntry;
	private JFormattedTextField timeNewEntry;
	private JFormattedTextField dateNewEntry;
	
	private JFormattedTextField carPlateNewExit;
	private JFormattedTextField timeNewExit;
	private JFormattedTextField dateNewExit;	
	
	private MaskFormatter fmtTime;   
	private MaskFormatter fmtDate;
	private MaskFormatter fmtCarPlate;
	
	
	public ParkingFrame() {
		
		super("Estacionamento " + nomeEstacionamento);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		super.setMinimumSize(new Dimension(1080,720));
		
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);	

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width)/2, (dim.height - this.getSize().height)/2);

        super.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(JOptionPane.showConfirmDialog(ParkingFrame.this, 
                        "Deseja sair?", "Sair?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
            }
        });
        
        super.setLayout(new GridBagLayout());
		GridBagConstraints modifier = new GridBagConstraints();
        
        getUIResources();
        
        
        //---------------------------------------------------------------------------------------------------------------------------------------MenuPanel
        SpringLayout menuPanelLayout = new SpringLayout();
        JPanel menuPanel = new JPanel(menuPanelLayout);
        menuPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        menuPanel.setBackground(orange);


        JButton configButton = new JButton(new ImageIcon(configNormalButton));
        configButton.setBorder(BorderFactory.createEmptyBorder());
        configButton.setContentAreaFilled(false);
        configButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                 configButton.setIcon(new ImageIcon(configHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                configButton.setIcon(new ImageIcon(configNormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                configButton.setIcon(new ImageIcon(configClickedButton));
            }
        });
        
        JButton bankButton = new JButton(new ImageIcon(bankNormalButton));
        bankButton.setBorder(BorderFactory.createEmptyBorder());
        bankButton.setContentAreaFilled(false);
        bankButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                bankButton.setIcon(new ImageIcon(bankHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bankButton.setIcon(new ImageIcon(bankNormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                bankButton.setIcon(new ImageIcon(bankClickedButton));
            }
        });

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

        
        menuPanel.add(configButton);
        menuPanel.add(bankButton);
        menuPanel.add(closeButton);


        menuPanelLayout.putConstraint(SpringLayout.WEST, configButton, 5, SpringLayout.WEST, menuPanel);
        menuPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, configButton, 0, SpringLayout.VERTICAL_CENTER, menuPanel);

        menuPanelLayout.putConstraint(SpringLayout.WEST, bankButton, 5, SpringLayout.EAST, configButton);
        menuPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, bankButton, 0, SpringLayout.VERTICAL_CENTER, configButton);
        
        menuPanelLayout.putConstraint(SpringLayout.EAST, closeButton, -5, SpringLayout.EAST, menuPanel);
        menuPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, closeButton, 0, SpringLayout.VERTICAL_CENTER, menuPanel);
 
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------West Panel
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints westPanelModifier = new GridBagConstraints();
		westPanel.setBackground(Color.GRAY);
		
		SpringLayout newEntryPanelLayout = new SpringLayout();
		JPanel newEntryPanel = new JPanel(newEntryPanelLayout);
		newEntryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		newEntryPanel.setBackground(darkGray);
		
		JPanel newEntryLabelPanel = new JPanel();	
		newEntryLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		newEntryLabelPanel.setBackground(orange);
		
		JLabel newEntryLabel = new JLabel("Registrar Entrada");
		newEntryLabel.setForeground(Color.BLACK);
		newEntryLabel.setFont(defaultFont);
		
		newEntryLabelPanel.add(newEntryLabel);
		
		try {
			fmtTime = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        try {
			fmtDate = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        try {
			fmtCarPlate = new MaskFormatter("UUU-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
     
        fmtTime.setValidCharacters("0123456789");
        fmtDate.setValidCharacters("0123456789");
        fmtCarPlate.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	
		JLabel dateNewEntryLabel = new JLabel("Data:");
		dateNewEntryLabel.setFont(defaultFont);
		dateNewEntryLabel.setForeground(Color.BLACK);
		
		dateNewEntry = new JFormattedTextField(fmtDate);
		dateNewEntry.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		dateNewEntry.setHorizontalAlignment(JLabel.CENTER);
		dateNewEntry.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel timeNewEntryLabel = new JLabel("Hora:");
		timeNewEntryLabel.setFont(defaultFont);
		timeNewEntryLabel.setForeground(Color.BLACK);
		
		timeNewEntry = new JFormattedTextField(fmtTime);
		timeNewEntry.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		timeNewEntry.setHorizontalAlignment(JLabel.CENTER);
		timeNewEntry.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel carPlateEntryPanel = new JLabel(new ImageIcon(carPlateURL));	
		
		carPlateNewEntry = new JFormattedTextField(fmtCarPlate);
		carPlateNewEntry.setFont(new Font("Unispace", Font.BOLD, 40));
		carPlateNewEntry.setBorder(BorderFactory.createEmptyBorder());
		carPlateNewEntry.setOpaque(false);
		carPlateNewEntry.setHorizontalAlignment(JLabel.CENTER);
		
		JButton confirmEntryButton = new JButton(new ImageIcon(confirmNormalButton));
		confirmEntryButton.setBorder(BorderFactory.createEmptyBorder());
		confirmEntryButton.setContentAreaFilled(false);
		confirmEntryButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	confirmEntryButton.setIcon(new ImageIcon(confirmHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	confirmEntryButton.setIcon(new ImageIcon(confirmNormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	confirmEntryButton.setIcon(new ImageIcon(confirmClickedButton));
            }
        });


		JButton clearEntryButton = new JButton(new ImageIcon(clearNormalButton));
		clearEntryButton.setBorder(BorderFactory.createEmptyBorder());
		clearEntryButton.setContentAreaFilled(false);
		clearEntryButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	clearEntryButton.setIcon(new ImageIcon(clearHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	clearEntryButton.setIcon(new ImageIcon(clearNormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	clearEntryButton.setIcon(new ImageIcon(clearClickedButton));
            }
        });
		
			
		newEntryPanel.add(newEntryLabelPanel);
		newEntryPanel.add(dateNewEntryLabel);
		newEntryPanel.add(dateNewEntry);
		newEntryPanel.add(timeNewEntryLabel);
		newEntryPanel.add(timeNewEntry);
		newEntryPanel.add(carPlateNewEntry);
		newEntryPanel.add(carPlateEntryPanel);
		newEntryPanel.add(confirmEntryButton);
		newEntryPanel.add(clearEntryButton);

		
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, newEntryLabelPanel, -2, SpringLayout.WEST, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, newEntryLabelPanel, 2, SpringLayout.EAST, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, newEntryLabelPanel, -2, SpringLayout.NORTH,newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, dateNewEntryLabel, 15, SpringLayout.WEST,newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, dateNewEntryLabel, 30, SpringLayout.SOUTH, newEntryLabelPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, dateNewEntry, 5, SpringLayout.EAST,dateNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, dateNewEntry, 0, SpringLayout.VERTICAL_CENTER, dateNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, dateNewEntry, -50, SpringLayout.EAST,newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, timeNewEntryLabel, 15, SpringLayout.WEST,newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, timeNewEntryLabel, 20, SpringLayout.SOUTH, dateNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, timeNewEntry, 0, SpringLayout.WEST,dateNewEntry);
		newEntryPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, timeNewEntry, 0, SpringLayout.VERTICAL_CENTER, timeNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, timeNewEntry, -50, SpringLayout.EAST,newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, carPlateEntryPanel, 2, SpringLayout.HORIZONTAL_CENTER, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, carPlateEntryPanel, -10, SpringLayout.VERTICAL_CENTER,newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, carPlateNewEntry, 0, SpringLayout.EAST, carPlateEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, carPlateNewEntry, 0, SpringLayout.WEST, carPlateEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carPlateNewEntry, 8, SpringLayout.VERTICAL_CENTER,carPlateEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, confirmEntryButton, -20, SpringLayout.HORIZONTAL_CENTER, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.SOUTH, confirmEntryButton, -20, SpringLayout.SOUTH, newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, clearEntryButton, 20, SpringLayout.HORIZONTAL_CENTER, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.SOUTH, clearEntryButton, -20, SpringLayout.SOUTH, newEntryPanel);
		
		
		
		
		SpringLayout logNewEntryPanelLayout = new SpringLayout();
		JPanel logNewEntryPanel = new JPanel(logNewEntryPanelLayout);
		logNewEntryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		logNewEntryPanel.setBackground(Color.darkGray);
		
		JPanel logNewEntryLabelPanel = new JPanel();	
		logNewEntryLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		logNewEntryLabelPanel.setBackground(orange);
		
		JLabel logNewEntryLabel = new JLabel("�ltimas atualiza��es/entradas");
		logNewEntryLabel.setForeground(Color.BLACK);
		logNewEntryLabel.setFont(defaultFont);
		
		logNewEntryLabelPanel.add(logNewEntryLabel);
		
		logNewEntryPanel.add(logNewEntryLabelPanel);
		
		
		logNewEntryPanelLayout.putConstraint(SpringLayout.WEST, logNewEntryLabelPanel, -2, SpringLayout.WEST, logNewEntryPanel);
		logNewEntryPanelLayout.putConstraint(SpringLayout.EAST, logNewEntryLabelPanel, 2, SpringLayout.EAST, logNewEntryPanel);
		logNewEntryPanelLayout.putConstraint(SpringLayout.NORTH, logNewEntryLabelPanel, -2, SpringLayout.NORTH,logNewEntryPanel);
		
		
		
		westPanelModifier.insets = new Insets(10,10,10,10);
		
		westPanelModifier.fill = GridBagConstraints.BOTH;
		westPanelModifier.weightx = 1;
		westPanelModifier.weighty = 1;
		westPanelModifier.gridx = 0;
		westPanelModifier.gridy = 0;
		westPanel.add(newEntryPanel, westPanelModifier);
		
		westPanelModifier.fill = GridBagConstraints.BOTH;
		westPanelModifier.weightx = 1;
		westPanelModifier.weighty = 1;
		westPanelModifier.gridx = 0;
		westPanelModifier.gridy = 1;
		westPanel.add(logNewEntryPanel, westPanelModifier);
		
		//---------------------------------------------------------------------------------------------------------------------------------------East Panel		
		JPanel eastPanel = new JPanel(new GridBagLayout());
		GridBagConstraints eastPanelModifier = new GridBagConstraints();
		eastPanel.setBackground(Color.GRAY);
		
		SpringLayout newExitPanelLayout = new SpringLayout();
		JPanel newExitPanel = new JPanel(newExitPanelLayout);
		newExitPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		newExitPanel.setBackground(darkGray);
		
		JPanel newExitLabelPanel = new JPanel();	
		newExitLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		newExitLabelPanel.setBackground(orange);
		
		JLabel newExitLabel = new JLabel("Registrar Sa�da");
		newExitLabel.setForeground(Color.BLACK);
		newExitLabel.setFont(defaultFont);
		
		newExitLabelPanel.add(newExitLabel);
		
		try {
			fmtTime = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        try {
			fmtDate = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        try {
			fmtCarPlate = new MaskFormatter("UUU-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
     
        fmtTime.setValidCharacters("0123456789");
        fmtDate.setValidCharacters("0123456789");
        fmtCarPlate.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	
		JLabel dateNewExitLabel = new JLabel("Data:");
		dateNewExitLabel.setFont(defaultFont);
		dateNewExitLabel.setForeground(Color.BLACK);
		
		dateNewExit = new JFormattedTextField(fmtDate);
		dateNewExit.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		dateNewExit.setHorizontalAlignment(JLabel.CENTER);
		dateNewExit.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel timeNewExitLabel = new JLabel("Hora:");
		timeNewExitLabel.setFont(defaultFont);
		timeNewExitLabel.setForeground(Color.BLACK);
		
		timeNewExit = new JFormattedTextField(fmtTime);
		timeNewExit.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		timeNewExit.setHorizontalAlignment(JLabel.CENTER);
		timeNewExit.setBorder(BorderFactory.createEmptyBorder());
		
		JLabel carPlateExitPanel = new JLabel(new ImageIcon(carPlateURL));	
		
		carPlateNewExit = new JFormattedTextField(fmtCarPlate);
		carPlateNewExit.setFont(new Font("Unispace", Font.BOLD, 40));
		carPlateNewExit.setBorder(BorderFactory.createEmptyBorder());
		carPlateNewExit.setOpaque(false);
		carPlateNewExit.setHorizontalAlignment(JLabel.CENTER);
		
		JButton confirmExitButton = new JButton(new ImageIcon(confirmNormalButton));
		confirmExitButton.setBorder(BorderFactory.createEmptyBorder());
		confirmExitButton.setContentAreaFilled(false);
		confirmExitButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	confirmExitButton.setIcon(new ImageIcon(confirmHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	confirmExitButton.setIcon(new ImageIcon(confirmNormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	confirmExitButton.setIcon(new ImageIcon(confirmClickedButton));
            }
        });


		JButton clearExitButton = new JButton(new ImageIcon(clearNormalButton));
		clearExitButton.setBorder(BorderFactory.createEmptyBorder());
		clearExitButton.setContentAreaFilled(false);
		clearExitButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	clearExitButton.setIcon(new ImageIcon(clearHoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	clearExitButton.setIcon(new ImageIcon(clearNormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	clearExitButton.setIcon(new ImageIcon(clearClickedButton));
            }
        });
		
			
		newExitPanel.add(newExitLabelPanel);
		newExitPanel.add(dateNewExitLabel);
		newExitPanel.add(dateNewExit);
		newExitPanel.add(timeNewExitLabel);
		newExitPanel.add(timeNewExit);
		newExitPanel.add(carPlateNewExit);
		newExitPanel.add(carPlateExitPanel);
		newExitPanel.add(confirmExitButton);
		newExitPanel.add(clearExitButton);

		
		newExitPanelLayout.putConstraint(SpringLayout.WEST, newExitLabelPanel, -2, SpringLayout.WEST, newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.EAST, newExitLabelPanel, 2, SpringLayout.EAST, newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, newExitLabelPanel, -2, SpringLayout.NORTH,newExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.WEST, dateNewExitLabel, 15, SpringLayout.WEST,newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, dateNewExitLabel, 30, SpringLayout.SOUTH, newExitLabelPanel);
		newExitPanelLayout.putConstraint(SpringLayout.WEST, dateNewExit, 5, SpringLayout.EAST,dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, dateNewExit, 0, SpringLayout.VERTICAL_CENTER, dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.EAST, dateNewExit, -50, SpringLayout.EAST,newExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.WEST, timeNewExitLabel, 15, SpringLayout.WEST,newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, timeNewExitLabel, 20, SpringLayout.SOUTH, dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.WEST, timeNewExit, 0, SpringLayout.WEST,dateNewExit);
		newExitPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, timeNewExit, 0, SpringLayout.VERTICAL_CENTER, timeNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.EAST, timeNewExit, -50, SpringLayout.EAST,newExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, carPlateExitPanel, 2, SpringLayout.HORIZONTAL_CENTER, newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, carPlateExitPanel, -10, SpringLayout.VERTICAL_CENTER,newExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.EAST, carPlateNewExit, 0, SpringLayout.EAST, carPlateExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.WEST, carPlateNewExit, 0, SpringLayout.WEST, carPlateExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carPlateNewExit, 8, SpringLayout.VERTICAL_CENTER,carPlateExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.EAST, confirmExitButton, -20, SpringLayout.HORIZONTAL_CENTER, newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.SOUTH, confirmExitButton, -20, SpringLayout.SOUTH, newExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.WEST, clearExitButton, 20, SpringLayout.HORIZONTAL_CENTER, newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.SOUTH, clearExitButton, -20, SpringLayout.SOUTH, newExitPanel);
		
		newExitLabelPanel.add(newExitLabel);	
		
		
		
		SpringLayout receiptPanelLayout = new SpringLayout();
		JPanel receiptPanel = new JPanel(receiptPanelLayout);
		receiptPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		receiptPanel.setBackground(Color.darkGray);
		
		JPanel receiptLabelPanel = new JPanel();	
		receiptLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		receiptLabelPanel.setBackground(orange);
		
		JLabel receiptLabel = new JLabel("Recibo");
		receiptLabel.setForeground(Color.BLACK);
		receiptLabel.setFont(defaultFont);
		
		receiptLabelPanel.add(receiptLabel);
		
		receiptPanel.add(receiptLabelPanel);
		
		
		receiptPanelLayout.putConstraint(SpringLayout.WEST, receiptLabelPanel, -2, SpringLayout.WEST, receiptPanel);
		receiptPanelLayout.putConstraint(SpringLayout.EAST, receiptLabelPanel, 2, SpringLayout.EAST, receiptPanel);
		receiptPanelLayout.putConstraint(SpringLayout.NORTH, receiptLabelPanel, -2, SpringLayout.NORTH,receiptPanel);
		
		
		
		eastPanelModifier.insets = new Insets(10,10,10,10);
		
		eastPanelModifier.fill = GridBagConstraints.BOTH;
		eastPanelModifier.weightx = 1;
		eastPanelModifier.weighty = 1;
		eastPanelModifier.gridx = 0;
		eastPanelModifier.gridy = 0;
		eastPanel.add(newExitPanel, eastPanelModifier);
		
		eastPanelModifier.fill = GridBagConstraints.BOTH;
		eastPanelModifier.weightx = 1;
		eastPanelModifier.weighty = 1;
		eastPanelModifier.gridx = 0;
		eastPanelModifier.gridy = 1;
		eastPanel.add(receiptPanel, eastPanelModifier);
		
		//---------------------------------------------------------------------------------------------------------------------------------------Center Panel
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		centerPanel.setBackground(Color.GRAY);	
        
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------Constraints MenuPanel
		modifier.gridx = 0;
		modifier.gridy = 0;
		modifier.fill = GridBagConstraints.HORIZONTAL;
		modifier.ipady = 40;
		modifier.weightx = 1;
		modifier.gridwidth = 3;
		modifier.gridheight = 1;
		add(menuPanel, modifier);
			
		//---------------------------------------------------------------------------------------------------------------------------------------Constraints westPanel
		modifier.gridx = 0;
		modifier.gridy = 1;
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weighty = 1;
		modifier.weightx = 0.45;
		modifier.gridwidth = 1;
		modifier.gridheight = 1;
		modifier.ipady = 0;	
		add(westPanel, modifier);
		
		//---------------------------------------------------------------------------------------------------------------------------------------Constraints centerPanel
		modifier.gridx = 1;
		modifier.gridy = 1;
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weighty = 1;
		modifier.weightx = 1;
		modifier.gridheight = 1;
		add(centerPanel, modifier);
		
		//---------------------------------------------------------------------------------------------------------------------------------------Constraints eastPanel
		modifier.gridx = 2;
		modifier.gridy = 1;
		modifier.fill = GridBagConstraints.BOTH;
		modifier.weighty = 1;
		modifier.weightx = 0.45;
		modifier.gridwidth = 1;
		modifier.gridheight = 1;
		add(eastPanel, modifier);
		
        setVisible(true);
        
        
        
      //---------------------------------------------------------------------------------------------------------------------------------------Listeners
        
        closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				closeButton.setIcon(new ImageIcon(closeClickedButton));
				
				if(JOptionPane.showConfirmDialog(ParkingFrame.this, 
                        "Deseja sair?", "Sair?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
				{
                    System.exit(0);
                }
			}
        	
        });
       
	}
	
	private void getUIResources() {
		configNormalButton  	= ParkingFrame.class.getResource(configNormalButtonName);
        configHoveredButton 	= ParkingFrame.class.getResource(configHoveredButtonName);
        configClickedButton 	= ParkingFrame.class.getResource(configClickedButtonName);
        
        bankNormalButton    	= ParkingFrame.class.getResource(bankNormalButtonName);
        bankHoveredButton   	= ParkingFrame.class.getResource(bankHoveredButtonName);
        bankClickedButton   	= ParkingFrame.class.getResource(bankClickedButtonName);
        
        confirmNormalButton		= ParkingFrame.class.getResource(confirmNormalButtonName);
        confirmHoveredButton	= ParkingFrame.class.getResource(confirmHoveredButtonName);
        confirmClickedButton	= ParkingFrame.class.getResource(confirmClickedButtonName);
        
        clearNormalButton		= ParkingFrame.class.getResource(clearNormalButtonName);
        clearHoveredButton		= ParkingFrame.class.getResource(clearHoveredButtonName);
        clearClickedButton		= ParkingFrame.class.getResource(clearClickedButtonName);
        
        closeNormalButton		= ParkingFrame.class.getResource(closeNormalButtonName);
        closeHoveredButton		= ParkingFrame.class.getResource(closeHoveredButtonName);
        closeClickedButton		= ParkingFrame.class.getResource(closeClickedButtonName);
        
	    carPlateURL 			= ParkingFrame.class.getResource(plateName);
	}

}
