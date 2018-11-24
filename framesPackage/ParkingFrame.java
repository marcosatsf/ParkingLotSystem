package framesPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//ícone 20x20
@SuppressWarnings("serial")
public class ParkingFrame extends JFrame{

	public static final String nomeEstacionamento = "GUTOMA";
	
	public ParkingFrame() {
		super("Estacionamento " + nomeEstacionamento);
		super.setLayout(new GridBagLayout());
		GridBagConstraints modifier = new GridBagConstraints();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		super.setMinimumSize(new Dimension(500,500));

        //setSize(0, 0);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width)/2, (dim.height - this.getSize().height)/2);
        
        //setResizable(false);

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
        
        
        JPanel menuPanel = new JPanel(new BorderLayout());
		menuPanel.setBackground(Color.GRAY);
		JLabel infoNome = new JLabel("Bem vindo ao teste!");
		
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.RED);
		
		
		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Color.BLUE);
		
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.GREEN);	
        
		//---------------------------------------------Constraints MenuPanel
		modifier.gridx = 0;
		modifier.gridy = 0;
		modifier.fill = GridBagConstraints.HORIZONTAL;
		modifier.ipady = 40;
		modifier.weightx = 1;
		modifier.gridwidth = 3;
		modifier.gridheight = 1;
		add(menuPanel, modifier);
		
		//---------------------------------------------Constraints westPanel
		modifier.gridx = 0;
		modifier.gridy = 1;
		modifier.fill = GridBagConstraints.BOTH;
		//modifier.ipady = 40;
		modifier.weighty = 1;
		modifier.gridwidth = 1;
		modifier.gridheight = 1;
		add(westPanel, modifier);
		
		//---------------------------------------------Constraints centerPanel
		modifier.gridx = 1;
		modifier.gridy = 1;
		modifier.fill = GridBagConstraints.BOTH;
		//modifier.ipady = 40;
		modifier.weighty = 1;
		modifier.gridheight = 1;
		add(centerPanel, modifier);
		
		//---------------------------------------------Constraints eastPanel
		modifier.gridx = 2;
		modifier.gridy = 1;
		modifier.fill = GridBagConstraints.BOTH;
		//modifier.ipady = 40;
		modifier.weighty = 1;
		modifier.gridwidth = 1;
		modifier.gridheight = 1;
		add(eastPanel, modifier);
		// TODO Auto-generated constructor stub
		
        setVisible(true);
	}

}
