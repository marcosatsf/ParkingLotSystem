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
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;

import system.ParkingLot;
import system.VehicleType;

//ícone 20x20
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
    
    private static String floor0NormalButtonName	= "floor0Normal.png";
    private static String floor0HoveredButtonName	= "floor0Hovered.png";
    private static String floor0ClickedButtonName	= "floor0Clicked.png";
    
    private static String floor1NormalButtonName	= "floor1Normal.png";
    private static String floor1HoveredButtonName	= "floor1Hovered.png";
    private static String floor1ClickedButtonName	= "floor1Clicked.png";
    
    private static String carIconLogName 			= "carIconLog.png";
    private static String bikeIconLogName 			= "motorcycleIconLog.png";
    private static String miniTruckIconLogName 		= "miniTruckIconLog.png";
    
    private static String carIconName 				= "car.png";
    private static String motorcycleIconName 		= "motorcycle.png";
    private static String miniTruckIconName 		= "miniTruck.png";
    
    private static String slotUpIconName			= "slotUp.png";
    private static String slotDownIconName			= "slotDown.png";
    
    private static String plateName					= "placa.png";
    
    private URL carIconLogURL;
    private URL bikeIconLogURL;
    private URL miniTruckIconLogURL;
    
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
    
    private URL floor0NormalButton;
    private URL floor0HoveredButton;
    private URL floor0ClickedButton;
    
    private URL floor1NormalButton;
    private URL floor1HoveredButton;
    private URL floor1ClickedButton;
    
    private URL carIconURL;
    private URL bikeIconURL;
    private URL miniTruckIconURL;
    
    private URL slotUpURL;
    private URL slotDownURL;
    
    private URL carPlateURL;
   
    
    private final Color orange			= new Color(248,210,0);
    private final Color darkGray		= new Color(88,88,88);
    private final Color carPlateColor 	= new Color(49,49,49);
    
    
	public static Font defaultFont	= new Font("Trebuchet MS", Font.BOLD, 22);
	public static Font carPlateFont	= new Font("Unispace", Font.BOLD, 40);
	
	
	private JFormattedTextField carPlateNewEntry;
	private JFormattedTextField timeNewEntry;
	private JFormattedTextField dateNewEntry;
	
	private JFormattedTextField carPlateNewExit;
	private JFormattedTextField timeNewExit;
	private JFormattedTextField dateNewExit;	
	
	private MaskFormatter fmtTime;   
	private MaskFormatter fmtDate;
	private MaskFormatter fmtCarPlate;
	
	
	private ArrayList<JPanel> logList = new ArrayList<JPanel>();//testes!
	//private ArrayList<JLabel> carPlateLog = new ArrayList();
	//private ArrayList<ImageIcon> carPlateIconLog = new ArrayList();
	private JPanel logNewEntryPanel;
    private SpringLayout logNewEntryPanelLayout;
    private JPanel logsEntryPanel;
    
    private Dictionary<String, URL> logImages;
    
    private List<JLabel> slots;
  
    private boolean floorTSelected = false;
    private boolean floor1Selected = false;
    
    private ParkingLot parkingLot = ParkingLot.getInstance();
	
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
        
        String[] vehicleTypesName = { "Carro", "Moto", "Caminhonete" };
        logImages = new Hashtable<String, URL>();
        logImages.put(vehicleTypesName[0], carIconLogURL);
        logImages.put(vehicleTypesName[1], bikeIconLogURL);
        logImages.put(vehicleTypesName[2], miniTruckIconLogURL);
        
        
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
                
                new ConfigurationFrame(ParkingFrame.this);
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
			fmtDate = new MaskFormatter("##/##/####");
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
		
		JLabel vehicleTypeEntryLabel = new JLabel("Veículo:");
		vehicleTypeEntryLabel.setFont(defaultFont);
		vehicleTypeEntryLabel.setForeground(Color.BLACK);
		
		JComboBox<String> vehicleTypeEntryComboBox = new JComboBox<String>(vehicleTypesName);
		vehicleTypeEntryComboBox.setEditable(false);
		vehicleTypeEntryComboBox.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		
		
		JLabel carPlateEntryPanel = new JLabel(new ImageIcon(carPlateURL));	
		
		carPlateNewEntry = new JFormattedTextField(fmtCarPlate);
		carPlateNewEntry.setForeground(carPlateColor);
		carPlateNewEntry.setFont(carPlateFont);
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
		newEntryPanel.add(vehicleTypeEntryComboBox);
		newEntryPanel.add(vehicleTypeEntryLabel);
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
		
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, vehicleTypeEntryLabel, 15, SpringLayout.SOUTH, newEntryLabelPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, vehicleTypeEntryLabel, 20, SpringLayout.WEST,newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, vehicleTypeEntryComboBox, 0, SpringLayout.VERTICAL_CENTER, vehicleTypeEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, vehicleTypeEntryComboBox, 5, SpringLayout.EAST,vehicleTypeEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, vehicleTypeEntryComboBox, -40, SpringLayout.EAST,newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, dateNewEntryLabel, 0, SpringLayout.WEST,vehicleTypeEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, dateNewEntryLabel, 10, SpringLayout.SOUTH, vehicleTypeEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, dateNewEntry, 5, SpringLayout.EAST,dateNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, dateNewEntry, 0, SpringLayout.EAST,vehicleTypeEntryComboBox);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, dateNewEntry, 0, SpringLayout.NORTH,dateNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.SOUTH, dateNewEntry, 0, SpringLayout.SOUTH,dateNewEntryLabel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, timeNewEntryLabel, 0, SpringLayout.WEST,dateNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, timeNewEntryLabel, 10, SpringLayout.SOUTH, dateNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, timeNewEntry, 0, SpringLayout.WEST,dateNewEntry);
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, timeNewEntry, 0, SpringLayout.EAST,dateNewEntry);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, timeNewEntry, 0, SpringLayout.NORTH,timeNewEntryLabel);
		newEntryPanelLayout.putConstraint(SpringLayout.SOUTH, timeNewEntry, 0, SpringLayout.SOUTH,timeNewEntryLabel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, carPlateEntryPanel, 2, SpringLayout.HORIZONTAL_CENTER, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.NORTH, carPlateEntryPanel, 0, SpringLayout.VERTICAL_CENTER,newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, carPlateNewEntry, 0, SpringLayout.EAST, carPlateEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, carPlateNewEntry, 0, SpringLayout.WEST, carPlateEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carPlateNewEntry, 8, SpringLayout.VERTICAL_CENTER,carPlateEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.EAST, confirmEntryButton, -20, SpringLayout.HORIZONTAL_CENTER, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.SOUTH, confirmEntryButton, -20, SpringLayout.SOUTH, newEntryPanel);
		
		newEntryPanelLayout.putConstraint(SpringLayout.WEST, clearEntryButton, 20, SpringLayout.HORIZONTAL_CENTER, newEntryPanel);
		newEntryPanelLayout.putConstraint(SpringLayout.SOUTH, clearEntryButton, -20, SpringLayout.SOUTH, newEntryPanel);
		
		
		logNewEntryPanelLayout = new SpringLayout();
		logNewEntryPanel = new JPanel(logNewEntryPanelLayout);
		logNewEntryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		logNewEntryPanel.setBackground(Color.darkGray);
		
		JPanel logNewEntryLabelPanel = new JPanel();	
		logNewEntryLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		logNewEntryLabelPanel.setBackground(orange);
		
		JLabel logNewEntryLabel = new JLabel("Últimas Entradas");
		logNewEntryLabel.setForeground(Color.BLACK);
		logNewEntryLabel.setFont(defaultFont);
		
		SpringLayout logsEntryPanelLayout = new SpringLayout();
		logsEntryPanel = new JPanel(logsEntryPanelLayout);
		logsEntryPanel.setOpaque(false);
		
		logNewEntryLabelPanel.add(logNewEntryLabel);
		
		logNewEntryPanel.add(logNewEntryLabelPanel);
		logNewEntryPanel.add(logsEntryPanel);
		
		
		logNewEntryPanelLayout.putConstraint(SpringLayout.WEST, logNewEntryLabelPanel, -2, SpringLayout.WEST, logNewEntryPanel);
		logNewEntryPanelLayout.putConstraint(SpringLayout.EAST, logNewEntryLabelPanel, 2, SpringLayout.EAST, logNewEntryPanel);
		logNewEntryPanelLayout.putConstraint(SpringLayout.NORTH, logNewEntryLabelPanel, -2, SpringLayout.NORTH,logNewEntryPanel);
		
		logNewEntryPanelLayout.putConstraint(SpringLayout.WEST, logsEntryPanel, 0, SpringLayout.WEST, logNewEntryPanel);
		logNewEntryPanelLayout.putConstraint(SpringLayout.EAST, logsEntryPanel, 0, SpringLayout.EAST, logNewEntryPanel);
		logNewEntryPanelLayout.putConstraint(SpringLayout.NORTH, logsEntryPanel, 0, SpringLayout.SOUTH,logNewEntryLabelPanel);
		logNewEntryPanelLayout.putConstraint(SpringLayout.SOUTH, logsEntryPanel, 0, SpringLayout.SOUTH,logNewEntryPanel);
		
		
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
		
		JLabel newExitLabel = new JLabel("Registrar Saída");
		newExitLabel.setForeground(Color.BLACK);
		newExitLabel.setFont(defaultFont);
		
		newExitLabelPanel.add(newExitLabel);
		
		try {
			fmtTime = new MaskFormatter("##:##");
			fmtDate = new MaskFormatter("##/##/####");
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
		
		JLabel vehicleTypeExitLabel = new JLabel("Veículo:");
		vehicleTypeExitLabel.setFont(defaultFont);
		vehicleTypeExitLabel.setForeground(Color.BLACK);
		
		JComboBox<String> vehicleTypeExitComboBox = new JComboBox<String>(vehicleTypesName);
		vehicleTypeExitComboBox.setEditable(false);
		vehicleTypeExitComboBox.setFont(defaultFont.deriveFont(Font.PLAIN, 18));
		
		JLabel carPlateExitPanel = new JLabel(new ImageIcon(carPlateURL));	
		
		carPlateNewExit = new JFormattedTextField(fmtCarPlate);
		carPlateNewExit.setForeground(carPlateColor);
		carPlateNewExit.setFont(carPlateFont);
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
		newExitPanel.add(vehicleTypeExitComboBox);
		newExitPanel.add(vehicleTypeExitLabel);
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
		
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, vehicleTypeExitLabel, 15, SpringLayout.SOUTH, newExitLabelPanel);
		newExitPanelLayout.putConstraint(SpringLayout.WEST, vehicleTypeExitLabel, 20, SpringLayout.WEST,newExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, vehicleTypeExitComboBox, 0, SpringLayout.VERTICAL_CENTER, vehicleTypeExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.WEST, vehicleTypeExitComboBox, 5, SpringLayout.EAST,vehicleTypeExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.EAST, vehicleTypeExitComboBox, -40, SpringLayout.EAST,newExitPanel);
		
		newExitPanelLayout.putConstraint(SpringLayout.WEST, dateNewExitLabel, 0, SpringLayout.WEST,vehicleTypeExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, dateNewExitLabel, 10, SpringLayout.SOUTH, vehicleTypeExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.WEST, dateNewExit, 5, SpringLayout.EAST,dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, dateNewExit, 0, SpringLayout.VERTICAL_CENTER, dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.EAST, dateNewExit, 0, SpringLayout.EAST,vehicleTypeExitComboBox);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, dateNewExit, 0, SpringLayout.NORTH,dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.SOUTH, dateNewExit, 0, SpringLayout.SOUTH,dateNewExitLabel);
		
		newExitPanelLayout.putConstraint(SpringLayout.WEST, timeNewExitLabel, 0, SpringLayout.WEST,dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, timeNewExitLabel, 10, SpringLayout.SOUTH, dateNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.WEST, timeNewExit, 0, SpringLayout.WEST,dateNewExit);
		newExitPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, timeNewExit, 0, SpringLayout.VERTICAL_CENTER, timeNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.EAST, timeNewExit, 0, SpringLayout.EAST,dateNewExit);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, timeNewExit, 0, SpringLayout.NORTH,timeNewExitLabel);
		newExitPanelLayout.putConstraint(SpringLayout.SOUTH, timeNewExit, 0, SpringLayout.SOUTH,timeNewExitLabel);
		
		newExitPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, carPlateExitPanel, 2, SpringLayout.HORIZONTAL_CENTER, newExitPanel);
		newExitPanelLayout.putConstraint(SpringLayout.NORTH, carPlateExitPanel, 0, SpringLayout.VERTICAL_CENTER,newExitPanel);
		
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
		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints centerPanelModifier = new GridBagConstraints();
		centerPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		centerPanel.setBackground(Color.GRAY);
		
		SpringLayout mapPanelLayout = new SpringLayout();
		JPanel mapPanel = new JPanel(mapPanelLayout);
		mapPanel.setBackground(darkGray);
		mapPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		//mapPanel.setOpaque(false);
		
		
		slots = new ArrayList<JLabel>();
		
		
		for(int i = 0; i < 5;i++) {
			SpringLayout slotContainerLayout = new SpringLayout();
			JPanel slotContainer = new JPanel(slotContainerLayout);
			slotContainer.setPreferredSize(new Dimension(373, 97));
			slotContainer.setOpaque(true);
			
			for(int j = 0; j < 10;j++) {
				JLabel slotUp = new JLabel(new ImageIcon(slotUpURL));
				JLabel vehicleUP = new JLabel(new ImageIcon(miniTruckIconURL));
				
				
				slotContainer.add(vehicleUP);
				slotContainer.add(slotUp);
				
				slotContainerLayout.putConstraint(SpringLayout.SOUTH, slotUp, 0, SpringLayout.VERTICAL_CENTER, slotContainer);
				slotContainerLayout.putConstraint(SpringLayout.WEST, slotUp, j * 37, SpringLayout.WEST, slotContainer);
				
				slotContainerLayout.putConstraint(SpringLayout.VERTICAL_CENTER, vehicleUP, 0, SpringLayout.VERTICAL_CENTER, slotUp);
				slotContainerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, vehicleUP, 0, SpringLayout.HORIZONTAL_CENTER, slotUp);
				
				slots.add(vehicleUP);
			}
			
			for(int k = 0;k < 10;k++) {
				JLabel slotDown = new JLabel(new ImageIcon(slotDownURL));
				JLabel vehicleDown = new JLabel(new ImageIcon(carIconURL));
				
				slotContainer.add(vehicleDown);
				slotContainer.add(slotDown);
				
				slotContainerLayout.putConstraint(SpringLayout.NORTH, slotDown, -2, SpringLayout.VERTICAL_CENTER, slotContainer);
				slotContainerLayout.putConstraint(SpringLayout.WEST, slotDown, k * 37, SpringLayout.WEST, slotContainer);
				
				slotContainerLayout.putConstraint(SpringLayout.VERTICAL_CENTER, vehicleDown, 1, SpringLayout.VERTICAL_CENTER, slotDown);
				slotContainerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, vehicleDown, 0, SpringLayout.HORIZONTAL_CENTER, slotDown);
				
				slots.add(vehicleDown);
			}
			
			
			mapPanel.add(slotContainer);
			mapPanelLayout.putConstraint(SpringLayout.NORTH, slotContainer, i * 115 + 20, SpringLayout.NORTH, mapPanel);
			mapPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, slotContainer, 0, SpringLayout.HORIZONTAL_CENTER, mapPanel);
		
		}
		
		for(JLabel car : slots) {
			car.setVisible(false);
		}
		
		SpringLayout floorSelectionPanelLayout = new SpringLayout();
		JPanel floorSelectionPanel = new JPanel(floorSelectionPanelLayout);	
		floorSelectionPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		floorSelectionPanel.setBackground(darkGray);
		
		JLabel floorIndicatorLabel = new JLabel("Piso -");
		floorIndicatorLabel.setForeground(Color.BLACK);
		floorIndicatorLabel.setFont(defaultFont.deriveFont(Font.BOLD,40));
		
		
		JButton floorTButton = new JButton(new ImageIcon(floor0NormalButton));
		floorTButton.setBorder(BorderFactory.createEmptyBorder());
		floorTButton.setContentAreaFilled(false);
		floorTButton.setFocusable(false);
		
		JButton floor1Button = new JButton(new ImageIcon(floor1NormalButton));
		floor1Button.setBorder(BorderFactory.createEmptyBorder());
		floor1Button.setContentAreaFilled(false);
		floor1Button.setFocusable(false);
		
		floorTButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	if(!floorTSelected) floorTButton.setIcon(new ImageIcon(floor0HoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(!floorTSelected)	floorTButton.setIcon(new ImageIcon(floor0NormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	floorTButton.setIcon(new ImageIcon(floor0ClickedButton));
            	floor1Button.setIcon(new ImageIcon(floor1NormalButton));
            	floorIndicatorLabel.setText("Piso T");
            	floorTSelected = true;
            	floor1Selected = false;
            	
            	setParkingSlots(0);
            }
        });
		
		floor1Button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
            	if(!floor1Selected) floor1Button.setIcon(new ImageIcon(floor1HoveredButton));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(!floor1Selected) floor1Button.setIcon(new ImageIcon(floor1NormalButton));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	floor1Button.setIcon(new ImageIcon(floor1ClickedButton));
            	floorTButton.setIcon(new ImageIcon(floor0NormalButton));
            	floorIndicatorLabel.setText("Piso 1");
            	floorTSelected = false;
            	floor1Selected = true;
            	
            	setParkingSlots(1);
            }
        });
		
		floorSelectionPanel.add(floorTButton);
		floorSelectionPanel.add(floorIndicatorLabel);
		floorSelectionPanel.add(floor1Button);
		
		floorSelectionPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, floorIndicatorLabel, 0, SpringLayout.VERTICAL_CENTER, floorSelectionPanel);
		floorSelectionPanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, floorIndicatorLabel, 0, SpringLayout.HORIZONTAL_CENTER, floorSelectionPanel);
		
		floorSelectionPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, floorTButton, 0, SpringLayout.VERTICAL_CENTER, floorIndicatorLabel);
		floorSelectionPanelLayout.putConstraint(SpringLayout.WEST, floorTButton, 50, SpringLayout.WEST, floorSelectionPanel);
		
		floorSelectionPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, floor1Button, 0, SpringLayout.VERTICAL_CENTER, floorIndicatorLabel);
		floorSelectionPanelLayout.putConstraint(SpringLayout.EAST, floor1Button, -50, SpringLayout.EAST, floorSelectionPanel);
		
		centerPanelModifier.insets = new Insets(10,10,10,10);
		
		centerPanelModifier.gridx = 0;
		centerPanelModifier.gridy = 0;
		centerPanelModifier.fill = GridBagConstraints.BOTH;
		centerPanelModifier.weighty = 1;
		centerPanelModifier.weightx = 1;
		centerPanel.add(mapPanel, centerPanelModifier);
		
		centerPanelModifier.insets = new Insets(0,10,10,10);
		
		centerPanelModifier.gridx = 0;
		centerPanelModifier.gridy = 1;
		centerPanelModifier.fill = GridBagConstraints.BOTH;
		centerPanelModifier.weighty = 0.15;
		centerPanelModifier.weightx = 1;
		centerPanel.add(floorSelectionPanel, centerPanelModifier);	
		
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
        
        clearEntryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearEntry();
			}
        	
        });
        
        clearExitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearExit();
			}
        	
        });
        
        confirmEntryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				
				if(dateNewEntry.getText().equals("  /  /    ")) {
					//Data Inválida
					return;
				}
				
				if(timeNewEntry.getText().equals("  :  ")) {
					//Hora Inválida
					return;
				}
				
				if(carPlateNewEntry.getText().equals("   -    ")) {
					//Placa Inválida
					return;
				}
				
				VehicleType type;
				switch((String)vehicleTypeEntryComboBox.getSelectedItem()) {
				
				case "Carro":
					type = VehicleType.CAR;
					break;
				case "Caminhonete":
					type = VehicleType.MINITRUCK;
					break;
				case "Moto":
					type = VehicleType.MOTORCYCLE;
					break;
				default:
					type = VehicleType.CAR;
					break;
				
				}
			
				
				if(!parkingLot.addVehicle(type, dateNewEntry.getText(), timeNewEntry.getText(), carPlateNewEntry.getText())) {
					//Não foi possível adicionar o veículo
					return;
				}
				
				int distance = 0, distanceInc = logsEntryPanel.getHeight()/3;
				
				logList.add(setNewVehicleLog((String)vehicleTypeEntryComboBox.getSelectedItem(), carPlateNewEntry.getText(), dateNewEntry.getText(), timeNewEntry.getText()));
				
				
				if(logList.size()<=3)
				{
					for(JPanel log : logList)
					{
						logsEntryPanel.add(log);
						
						logsEntryPanelLayout.putConstraint(SpringLayout.EAST, log, 0, SpringLayout.EAST, logsEntryPanel);
						logsEntryPanelLayout.putConstraint(SpringLayout.WEST, log, 0, SpringLayout.WEST, logsEntryPanel);
						logsEntryPanelLayout.putConstraint(SpringLayout.NORTH, log, distance, SpringLayout.NORTH, logsEntryPanel);
						distance+=distanceInc;
						logsEntryPanelLayout.putConstraint(SpringLayout.SOUTH, log, distance, SpringLayout.NORTH, logsEntryPanel);					
					}
				}
				else
				{
					logsEntryPanel.removeAll();
					for(int i=logList.size()-3 ; i<logList.size() ; i++)
					{
						logsEntryPanel.add(logList.get(i));
						
						logsEntryPanelLayout.putConstraint(SpringLayout.EAST, logList.get(i), 0, SpringLayout.EAST, logsEntryPanel);
						logsEntryPanelLayout.putConstraint(SpringLayout.WEST, logList.get(i), 0, SpringLayout.WEST, logsEntryPanel);
						logsEntryPanelLayout.putConstraint(SpringLayout.NORTH, logList.get(i), distance, SpringLayout.NORTH, logsEntryPanel);
						distance+=distanceInc;
						logsEntryPanelLayout.putConstraint(SpringLayout.SOUTH, logList.get(i), distance, SpringLayout.NORTH, logsEntryPanel);					
					}
				}				
				
				revalidate();
				repaint();
				
				clearEntry();
				
				if(floorTSelected)
					setParkingSlots(0);
				if(floor1Selected)
					setParkingSlots(1);
			}
        	
        });
        
//        Timer tm = new Timer(3000,new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	
//        		icon = new ImageIcon(listImg[whichImg]);
//        		imagem.setIcon(icon);
//        		repaint();
//            	revalidate();
//            	if(whichImg >= listImg.length-1) whichImg = 0;
//            	else whichImg++;
//            }
//        });
//		//tm.setInitialDelay(1000);
//		tm.start();
       
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

        floor0NormalButton		= ParkingFrame.class.getResource(floor0NormalButtonName);
        floor0HoveredButton		= ParkingFrame.class.getResource(floor0HoveredButtonName);
        floor0ClickedButton		= ParkingFrame.class.getResource(floor0ClickedButtonName);
        
        floor1NormalButton		= ParkingFrame.class.getResource(floor1NormalButtonName);
        floor1HoveredButton		= ParkingFrame.class.getResource(floor1HoveredButtonName);
        floor1ClickedButton		= ParkingFrame.class.getResource(floor1ClickedButtonName);
        
	    carPlateURL 			= ParkingFrame.class.getResource(plateName);
	    
	    carIconLogURL			= ParkingFrame.class.getResource(carIconLogName);
	    bikeIconLogURL			= ParkingFrame.class.getResource(bikeIconLogName);
	    miniTruckIconLogURL		= ParkingFrame.class.getResource(miniTruckIconLogName);
	    
	    carIconURL				= ParkingFrame.class.getResource(carIconName);
	    bikeIconURL				= ParkingFrame.class.getResource(motorcycleIconName);
	    miniTruckIconURL		= ParkingFrame.class.getResource(miniTruckIconName);
	    
	    slotUpURL				= ParkingFrame.class.getResource(slotUpIconName);
	    slotDownURL				= ParkingFrame.class.getResource(slotDownIconName);
	}
	
	private void clearEntry() {
		dateNewEntry.setText("");
		timeNewEntry.setText("");
		carPlateNewEntry.setText("");
	}
	
	private void clearExit() {
		dateNewExit.setText("");
		timeNewExit.setText("");
		carPlateNewExit.setText("");
	}
	
	
	private void setParkingSlots(int floorToShow) {
		List<VehicleType> slotsTypes = parkingLot.getSlotsVehicleType(floorToShow);
		List<Boolean> slotsDisponibilities = parkingLot.getSlotsDisponibility(floorToShow);

		
		for(int i = 0; i < slots.size();i++) {
			VehicleType type;
			boolean disponibility;
			
			try {
				type = slotsTypes.get(i);
				
				disponibility = slotsDisponibilities.get(i);
			}catch(IndexOutOfBoundsException e) {
				type = VehicleType.DEFAULT;
				disponibility = true;
			}
			
			switch(type) {
			case CAR:
				slots.get(i).setIcon(new ImageIcon(carIconURL));
				break;
			case MINITRUCK:
				slots.get(i).setIcon(new ImageIcon(miniTruckIconURL));
				break;
			case MOTORCYCLE:
				slots.get(i).setIcon(new ImageIcon(bikeIconURL));
				break;
			case DEFAULT:
				break;
			
			}
			
			slots.get(i).setVisible(!disponibility);
		}
	}
	

	private JPanel setNewVehicleLog(String iconType, String carPlateSelected, String dateEntryLog, String timeEntryLog){		
		SpringLayout newLogPanelLayout = new SpringLayout();
		JPanel newLogPanel = new JPanel(newLogPanelLayout);
		newLogPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
		newLogPanel.setBackground(Color.GRAY);
		//newLogPanel.setOpaque(false);
		//newLogPanel.setPreferredSize(new Dimension(100, 30));
		
		JLabel dateLog = new JLabel("Data de entrada: " + dateEntryLog);
		dateLog.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		dateLog.setForeground(Color.BLACK);
		
		JLabel timeLog = new JLabel("Horário de entrada: " + timeEntryLog);
		timeLog.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		timeLog.setForeground(Color.BLACK);
		
		JLabel carPlateLogName = new JLabel("Placa: ");
		carPlateLogName.setFont(defaultFont.deriveFont(Font.BOLD, 18));
		carPlateLogName.setForeground(Color.BLACK);
		JLabel carPlateLog = new JLabel(carPlateNewEntry.getText());
		carPlateLog.setFont(carPlateFont.deriveFont(Font.BOLD, 18));
		carPlateLog.setForeground(Color.BLACK);
		
		JLabel carPlateIconLog = new JLabel(new ImageIcon(logImages.get(iconType)));
		
		newLogPanel.add(dateLog);
		newLogPanel.add(timeLog);
		newLogPanel.add(carPlateLogName);
		newLogPanel.add(carPlateLog);
		newLogPanel.add(carPlateIconLog);
		
		
		newLogPanelLayout.putConstraint(SpringLayout.WEST, timeLog, 10, SpringLayout.WEST, newLogPanel);
		newLogPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, timeLog, 0, SpringLayout.VERTICAL_CENTER, newLogPanel);
		
		newLogPanelLayout.putConstraint(SpringLayout.WEST, carPlateLogName, 10, SpringLayout.WEST, newLogPanel);
		newLogPanelLayout.putConstraint(SpringLayout.NORTH, carPlateLogName, 10, SpringLayout.SOUTH, timeLog);
		
		newLogPanelLayout.putConstraint(SpringLayout.WEST, carPlateLog, 8, SpringLayout.EAST, carPlateLogName);
		newLogPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carPlateLog, 0, SpringLayout.VERTICAL_CENTER, carPlateLogName);
		
		newLogPanelLayout.putConstraint(SpringLayout.WEST, dateLog, 10, SpringLayout.WEST, newLogPanel);
		newLogPanelLayout.putConstraint(SpringLayout.SOUTH, dateLog, -10, SpringLayout.NORTH, timeLog);
		
		newLogPanelLayout.putConstraint(SpringLayout.EAST, carPlateIconLog, -10, SpringLayout.EAST, newLogPanel);
		newLogPanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, carPlateIconLog, 0, SpringLayout.VERTICAL_CENTER, newLogPanel);
		
		
		
		//Graphics2D g2 = null;
		//g2.setPaint(new GradientPaint(logsEntryPanel.getHeight(),logsEntryPanel.getHeight(),Color.WHITE,logsEntryPanel.getWidth(),logsEntryPanel.getWidth(),Color.RED));
		//g2.fill((Shape) newLogPanel);
		
		return newLogPanel;
	}

}
