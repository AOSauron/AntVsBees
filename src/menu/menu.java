package menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;


//Inspiré de :

public class menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu window = new menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				core.AntsVsSomeBees.play();
			}
		});
		
		JLabel lblPlugYourHeadphones = new JLabel("Plug your headphones ;)");
		
		JLabel lblLevel = new JLabel("LEVEL :");
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				core.AntsVsSomeBees.level=0;
			}
		});
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				core.AntsVsSomeBees.level=1;
			}
		});
		
		JRadioButton rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				core.AntsVsSomeBees.level=2;
			}
		});
		
		JRadioButton rdbtnPro = new JRadioButton("Pro");
		rdbtnPro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				core.AntsVsSomeBees.level=3;
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(lblLevel)
					.addContainerGap(317, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(rdbtnPro)
							.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
							.addComponent(lblPlugYourHeadphones)
							.addGap(68))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnHard)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnEasy)
										.addComponent(rdbtnMedium))
									.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
									.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblLevel)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
							.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(73))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(rdbtnEasy)
							.addGap(28)
							.addComponent(rdbtnMedium)
							.addGap(30)
							.addComponent(rdbtnHard)
							.addGap(28)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPlugYourHeadphones)
						.addComponent(rdbtnPro))
					.addGap(19))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
