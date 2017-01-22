package com.test.fenetres;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.MaskFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.test.fenetres.FenetreCombo.ItemAction;
import com.test.fenetres.FenetreCombo.ItemState;

public class FenetreAnimee2 extends JFrame{

	// Construction du menu
	private JMenuBar menuBar = new JMenuBar();
/*	private JMenu menuFichier = new JMenu("Fichier");
	private JMenu menuSousFichier = new JMenu("Sous ficher");
	private JMenu menuEdition = new JMenu("Edition");
	
	private JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem menuItemFermer = new JMenuItem("Fermer");
	private JMenuItem menuItemLancer = new JMenuItem("Lancer");
	private JMenuItem menuItemArreter = new JMenuItem("Arrêter");
	
	private JCheckBoxMenuItem menuCBChoix1 = new JCheckBoxMenuItem("Choix 1");
	private JCheckBoxMenuItem menuCBChoix2 = new JCheckBoxMenuItem("Choix 2");
	
	private JRadioButtonMenuItem menuRB1 = new JRadioButtonMenuItem("Radio 1");
	private JRadioButtonMenuItem menuRB2 = new JRadioButtonMenuItem("Radio 2");
*/
	private JMenu menuAnimation = new JMenu("Animation"),
	    menuForme = new JMenu("Forme"),
	    menuTypeForme = new JMenu("Type de forme"),
	    menuaPropos = new JMenu("À propos");

	//La déclaration pour le menu contextuel 
	private JPopupMenu menuPopUpPanneau = new JPopupMenu();
	private JMenu menuPopUpCouleurFond = new JMenu("Couleur de fond");
	private JMenu menuPopUpCouleurForme = new JMenu("Couleur de la forme");
	private JMenuItem  	menuPopUpFormeRouge = new JMenuItem("Rouge"),
						menuPopUpFormeBleu = new JMenuItem("Bleu"),
						menuPopUpFormeVert = new JMenuItem("Vert"),
						menuPopUpFondRouge = new JMenuItem("Rouge"),
						menuPopUpFondBleu = new JMenuItem("Bleu"),
						menuPopUpFondVert = new JMenuItem("Vert");

	
	private JMenuItem menuItemLancer = new JMenuItem("Lancer l'animation"),
	    menuItemArreter = new JMenuItem("Arrêter l'animation"),
	    menuItemQuitter = new JMenuItem("Quitter"),
	    menuItemaPropos = new JMenuItem("?");

	private JCheckBoxMenuItem menuItemCBMorph = new JCheckBoxMenuItem("Morphing");
	private JRadioButtonMenuItem menuItemRBCarre = new JRadioButtonMenuItem("Carré"),
			menuItemRBRond = new JRadioButtonMenuItem("Rond"),
			menuItemRBTriangle = new JRadioButtonMenuItem("Triangle"),
			menuItemRBEtoile = new JRadioButtonMenuItem("Etoile");

	private ButtonGroup menuBG = new ButtonGroup();



	// Contenu de la fenetre
	private PanneauAnime pan = new PanneauAnime();
	private JButton boutonGo = new JButton("Go");
	private JButton boutonStop = new JButton("Stop");
	private JPanel container = new JPanel();
	private JCheckBox checkMorph = new JCheckBox("morph");
	//  private JLabel label = new JLabel("Le JLabel");
	private JComboBox combo = new JComboBox();
	private JRadioButton radio1 = new JRadioButton("Radio 1");
	private JRadioButton radio2 = new JRadioButton("Radio 2");
	private JRadioButton radio3 = new JRadioButton("Radio 3");
	private ButtonGroup radioGroup = new ButtonGroup();
	//  private JTextField jtf = new JTextField("Valeur par défaut");
	private JLabel label1 = new JLabel("Format Integer : ");
	private JLabel label2 = new JLabel("Format Tel : ");
	private JFormattedTextField jtf1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField jtf2;
	//  private JFormattedTextField jtf2 = new JFormattedTextField(NumberFormat.getPercentInstance());
	
	private int compteur = 0;
	private boolean animated = true;
	private boolean backX, backY;
	private int x, y;
	private Thread t;


	//Création de notre barre d'outils
	private JToolBar toolBar = new JToolBar();

	//Les boutons de la barre d'outils
	private JButton   boutonToolBarLancer = new JButton(new ImageIcon("images/lancer.jpg")),
			boutonToolBarStop = new JButton(new ImageIcon("images/stop.jpg")),
			boutonToolBarCarre = new JButton(new ImageIcon("images/carre.jpg")),
			boutonToolBarTriangle = new JButton(new ImageIcon("images/triangle.jpg")),
			boutonToolBarRond = new JButton(new ImageIcon("images/rond.jpg")),
			boutonToolBarEtoile = new JButton(new ImageIcon("images/etoile.jpg"));
	private Color fondBouton = Color.WHITE;
	
    // Listener partagé
	private FormeListener formeListener = new FormeListener();
	
	
  /* 
   * Positionnement Nord avec BoxLayout
   * 
   */
  public FenetreAnimee2(){
		JPanel panneauHaut = new JPanel();
		JPanel panneauHautNord = new JPanel();
		JPanel panneauHautSud = new JPanel();
	    JPanel panneauSud = new JPanel();
	    Font police = new Font("Arial", Font.BOLD, 14);

		  
		this.setTitle("Animation");
	    this.setSize(500, 500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);

	    initMenu();
	    initToolBar();
	    
	    // On définit le contenu de la fenetre
	    JPanel b1 = new JPanel();
	    //On définit le layout en lui indiquant qu'il travaillera en ligne
	    // 1ere ligne : combo + check
	    combo.setPreferredSize(new Dimension(100, 20));
	    combo.addItem("Rond");
	    combo.addItem("Carre");
	    combo.addItem("Triangle");
	    combo.addItem("Etoile");
	    combo.addActionListener(new ComboActionListener());
	    checkMorph.addActionListener(new Check1ActionListener());
	    b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
	    b1.add(combo);
	    b1.add(checkMorph);

	    // 2e ligne : radio bouton
	    JPanel b2 = new JPanel();
	    radio1.setSelected(true);
	    radio1.addActionListener(new radioActionListener());
	    radio2.addActionListener(new radioActionListener());
	    radio3.addActionListener(new radioActionListener());
	    radioGroup.add(radio1);
	    radioGroup.add(radio2);
	    radioGroup.add(radio3);
	    b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
	    b2.add(radio1);
	    b2.add(radio2);
	    b2.add(radio3);

	    // 3e ligne : saisie texte integer
	    JPanel b3 = new JPanel();
	    jtf1.setFont(police);
	    jtf1.setPreferredSize(new Dimension(150, 30));
	    jtf1.setForeground(Color.BLUE);
	    //On ajoute l'écouteur à notre composant
	    jtf1.addKeyListener(new clavierListener());
	    b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
	    b3.add(label1);
	    b3.add(jtf1);

	    // 4e ligne : saisie texte nombre formaté
	    /*	     
		# : indique un chiffre ;
	    ' : indique un caractère d'échappement ;
	    U : indique une lettre (les minuscules sont automatiquement changées en majuscules) ;
	    L : indique une lettre (les majuscules sont automatiquement changées en minuscules) ;
	    A : indique un chiffre ou une lettre ;
	    ? : indique une lettre ;
	    * : indique que tous les caractères sont acceptés ;
	    H : indique que tous les caractères hexadécimaux sont acceptés (0 à 9, a à f et A à F).
	    */
	    JPanel b4 = new JPanel();
	    try {
		    MaskFormatter tel2 = new MaskFormatter("##-##-##-##-##");
		    //Vous pouvez ensuite le passer à votre zone de texte
		    jtf2 = new JFormattedTextField(tel2);
	    }catch(ParseException e){e.printStackTrace();}
	    jtf2.setFont(police);
	    jtf2.setPreferredSize(new Dimension(150, 30));
//	    jtf2.setForeground(Color.BLUE);
	    
	    b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
	    b4.add(label2);
	    b4.add(jtf2);

	    JPanel b5 = new JPanel();
	    //On positionne maintenant ces trois lignes les une au dessous des autres
	    b5.setLayout(new BoxLayout(b5, BoxLayout.PAGE_AXIS));
	    b5.add(toolBar);
	    b5.add(b1);
	    b5.add(b2);
	    b5.add(b3);
	    b5.add(b4);

	    
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    // capture souris sur le panneau animé
	    pan.addMouseListener(new PanSourisListener());

	    
	    container.add(b5, BorderLayout.NORTH);

	    boutonGo.addActionListener(new BoutonListenerGo()); 
	    boutonGo.setEnabled(false);
	    boutonStop.addActionListener(new BoutonListenerStop());

	    panneauSud.add(boutonGo);
	    panneauSud.add(boutonStop);
	    container.add(panneauSud, BorderLayout.SOUTH);


	    this.setContentPane(container);
	    this.setVisible(true);
	    go();
	  }

  private void initToolBar(){
	    this.boutonToolBarStop.addActionListener(new ArreterAnimationListener());
	    this.boutonToolBarStop.setBackground(fondBouton);
	    this.boutonToolBarLancer.addActionListener(new LancerAnimationListener());
	    this.boutonToolBarLancer.setBackground(fondBouton);
	    this.boutonToolBarLancer.setEnabled(false);

	    this.toolBar.add(boutonToolBarLancer);
	    this.toolBar.add(boutonToolBarStop);
	    this.toolBar.addSeparator();

	    //Ajout des Listeners
	    this.boutonToolBarRond.addActionListener(formeListener);
	    this.boutonToolBarRond.setBackground(fondBouton);
	    this.toolBar.add(boutonToolBarRond);

	    this.boutonToolBarCarre.addActionListener(formeListener);
	    this.boutonToolBarCarre.setBackground(fondBouton);
	    this.toolBar.add(boutonToolBarCarre);

	    this.boutonToolBarTriangle.setBackground(fondBouton);
	    this.boutonToolBarTriangle.addActionListener(formeListener);
	    this.toolBar.add(boutonToolBarTriangle);

	    this.boutonToolBarEtoile.setBackground(fondBouton);
	    this.boutonToolBarEtoile.addActionListener(formeListener);
	    this.toolBar.add(boutonToolBarEtoile);

//	    this.add(toolBar, BorderLayout.NORTH);    
  }

  private void initMenu(){
	    //Menu animation
	    menuAnimation.add(menuItemLancer);
	    menuItemLancer.setEnabled(false);
	    menuAnimation.add(menuItemArreter);
	    menuAnimation.addSeparator();
	    //Pour quitter l'application
	    menuItemQuitter.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	        System.exit(0);
	      }
	    });
	    menuAnimation.add(menuItemQuitter);

	    //Menu forme    
	    menuBG.add(menuItemRBCarre);
	    menuBG.add(menuItemRBTriangle);
	    menuBG.add(menuItemRBRond);
	    menuBG.add(menuItemRBEtoile);

	    menuTypeForme.add(menuItemRBRond);
	    menuTypeForme.add(menuItemRBCarre);    
	    menuTypeForme.add(menuItemRBTriangle);
	    menuTypeForme.add(menuItemRBEtoile);

	    //On crée un nouvel écouteur, inutile de créer 4 instances différentes
	    menuItemRBCarre.addActionListener(formeListener);
	    menuItemRBRond.addActionListener(formeListener);
	    menuItemRBTriangle.addActionListener(formeListener);
	    menuItemRBEtoile.addActionListener(formeListener);

	    menuItemRBRond.setSelected(true);

	    menuForme.add(menuTypeForme);
	    menuForme.add(menuItemCBMorph);

//	    menuAnimation.addActionListener(new AnimationListener()); 
	    menuItemLancer.addActionListener(new LancerAnimationListener()); 
	    menuItemArreter.addActionListener(new ArreterAnimationListener()); 
	    
	    menuTypeForme.addActionListener(new FormeListener()); 
	    menuItemCBMorph.addActionListener(new MorphListener()); 

	    //Menu À propos
	    menuaPropos.add(menuItemaPropos);

	    //Ajout de mnémoniques !
	    menuAnimation.setMnemonic('A');
	    menuForme.setMnemonic('F');
	    menuaPropos.setMnemonic('P');
	    //Cette instruction ajoute l'accélérateur 'c' à notre objet
	    menuItemLancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
	    menuItemArreter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
	    
	    //Ajout des menus dans la barre de menus
	    menuBar.add(menuAnimation);
	    menuBar.add(menuForme);
	    menuBar.add(menuaPropos);

	    // Pop up : on ajoute les listeners
	    menuPopUpFondVert.addActionListener(new CouleurPopUpFondListener()); 
	    menuPopUpFondRouge.addActionListener(new CouleurPopUpFondListener()); 
	    menuPopUpFondBleu.addActionListener(new CouleurPopUpFondListener()); 
	    menuPopUpFormeVert.addActionListener(new CouleurPopUpFormeListener()); 
	    menuPopUpFormeRouge.addActionListener(new CouleurPopUpFormeListener()); 
	    menuPopUpFormeBleu.addActionListener(new CouleurPopUpFormeListener()); 
	    
	    //Ajout de la barre de menus sur la fenêtre
	    this.setJMenuBar(menuBar);
  }


  
  private void go(){
    //Les coordonnées de départ de notre rond
    x = pan.getPosX();
    y = pan.getPosY();
    //Dans cet exemple, j'utilise une boucle while
    //Vous verrez qu'elle fonctionne très bien
    while(this.animated){
      if(x < 1)backX = false;
      if(x > pan.getWidth()-50)backX = true;          
      if(y < 1)backY = false;
      if(y > pan.getHeight()-50)backY = true;
      if(!backX)pan.setPosX(++x);
      else pan.setPosX(--x);
      if(!backY) pan.setPosY(++y);
      else pan.setPosY(--y);
      pan.repaint();

      try {
        Thread.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }     
  }

  
  private void TraiterArreter()
  {
      animated = false;     
      boutonGo.setEnabled(true);
      boutonStop.setEnabled(false);
      menuItemLancer.setEnabled(true);
      menuItemArreter.setEnabled(false);
	  boutonToolBarLancer.setEnabled(true);
	  boutonToolBarStop.setEnabled(false);
  }
  
  private void TraiterLancer()
  {
      animated = true;
      t = new Thread(new PlayAnimation());
      t.start();
      boutonGo.setEnabled(false);
      boutonStop.setEnabled(true);
      menuItemLancer.setEnabled(false);
      menuItemArreter.setEnabled(true);
	  boutonToolBarLancer.setEnabled(false);
	  boutonToolBarStop.setEnabled(true);
  }
  
  //
  // Capture événements des composants de la fenetre
  //
  class radioActionListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("source : " + radio1.getText() + " - état : " + radio1.isSelected());
	      System.out.println("source : " + radio2.getText() + " - état : " + radio2.isSelected());
	      System.out.println("source : " + radio3.getText() + " - état : " + radio3.isSelected());
	    }
	  }
  
  class Check1ActionListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
	      pan.morph = checkMorph.isSelected();
	      menuItemCBMorph.setSelected(checkMorph.isSelected());
	    }
  }
  
  class ComboActionListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("ActionListener : action sur " + combo.getSelectedItem());
	      pan.setForme(combo.getSelectedItem().toString());
	      String s = combo.getSelectedItem().toString();
	      if (s.equalsIgnoreCase(TestFenetres.CARRE) || s.equalsIgnoreCase(TestFenetres.CARRé))
	    	  menuItemRBCarre.setSelected(true);
	      else if (s.equalsIgnoreCase(TestFenetres.ETOILE))
	    	  menuItemRBEtoile.setSelected(true);
	      else if (s.equalsIgnoreCase(TestFenetres.TRIANGLE))
	    	  menuItemRBTriangle.setSelected(true);
	      else if (s.equalsIgnoreCase(TestFenetres.ROND))
	    	  menuItemRBRond.setSelected(true);
	    }               
  }

  class BoutonListenerGo implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
/*      animated = true;
      t = new Thread(new PlayAnimation());
      t.start();
      boutonGo.setEnabled(false);
      boutonStop.setEnabled(true);
      menuItemLancer.setEnabled(false);
      menuItemArreter.setEnabled(true);
*/
    	TraiterLancer();
    	// récupération des text fields
    	System.out.println("TEXT : jtf1 " + jtf1.getText());
    	System.out.println("TEXT : jtf2 " + jtf2.getText());
    }
  }

  class BoutonListenerStop implements ActionListener{
     public void actionPerformed(ActionEvent e) {
/*
      animated = false;     
      boutonGo.setEnabled(true);
      boutonStop.setEnabled(false);
      menuItemLancer.setEnabled(true);
      menuItemArreter.setEnabled(false);
 */    
      TraiterArreter();
      // récupération des text fields
      System.out.println("TEXT : jtf1 " + jtf1.getText());
      System.out.println("TEXT : jtf2 " + jtf2.getText());
    }
  }
 
  class PlayAnimation implements Runnable{
	    public void run() {
	      go();                   
	    }               
  }       


  //
  // Capture menu
  //
  class MorphListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      //Si la case est cochée, activation du mode morphing
	    if(menuItemCBMorph.isSelected())
	    {
	    	checkMorph.setSelected(true);
	    	pan.setMorph(true);
	    }
	    //Sinon rien !
	    else 
		{
			checkMorph.setSelected(false);
		    pan.setMorph(false);
		}
	  }    
	}  

	class LancerAnimationListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {     

	    	TraiterLancer();
/*
	   	  animated = true;
	      t = new Thread(new PlayAnimation());
	      t.start();
	      boutonGo.setEnabled(false);
	      boutonStop.setEnabled(true);
	      menuItemLancer.setEnabled(false);
	      menuItemArreter.setEnabled(true);
*/
	    }
	}

	class ArreterAnimationListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {     

	    	TraiterArreter();
/*
	    animated = false;     
        boutonGo.setEnabled(true);
        boutonStop.setEnabled(false);
        menuItemLancer.setEnabled(true);
        menuItemArreter.setEnabled(false);
*/
	    }
	}

  	class FormeListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {     
	      //On commente cette ligne pour l'instant
	      //****************************************
	      //pan.setForme(combo.getSelectedItem().toString());
	        //Si l'action vient d'un bouton radio du menu 
	    	String sClassName = e.getSource().getClass().getName();
	    	String s;
	    	
	    	// On gère le menu Formes
	    	if(e.getSource().getClass().getName().equals("javax.swing.JRadioButtonMenuItem"))
	    	{
		    	JRadioButtonMenuItem testJR = ((JRadioButtonMenuItem)e.getSource());
		    	s = testJR.getText();
		    	pan.setForme(s);
		    	// pan.setForme(((JRadioButtonMenuItem)e.getSource()).getText());
		    	// Maj de la combo
				if (s.equalsIgnoreCase(TestFenetres.CARRE) || s.equalsIgnoreCase(TestFenetres.CARRé))
					combo.setSelectedItem(TestFenetres.CARRE);
				else if (s.equalsIgnoreCase(TestFenetres.ETOILE))
					combo.setSelectedItem(TestFenetres.ETOILE);
				else if (s.equalsIgnoreCase(TestFenetres.TRIANGLE))
					combo.setSelectedItem(TestFenetres.TRIANGLE);
				else if (s.equalsIgnoreCase(TestFenetres.ROND))
					combo.setSelectedItem(TestFenetres.ROND);
	    	// On gère les boutons de la toolbar : on simule un click sur l'équivalent du menu Formes
	    	} else if(sClassName.equals("javax.swing.JButton"))
	    	{
	            if(e.getSource() == boutonToolBarCarre){
	                menuItemRBCarre.doClick();
	              }
	              else if(e.getSource() == boutonToolBarTriangle){
	            	  menuItemRBTriangle.doClick();
	              }
	              else if(e.getSource() == boutonToolBarEtoile){
	            	  menuItemRBEtoile.doClick();
	              }
	              else{
	            	  menuItemRBRond.doClick();
	              }
	    		
	    	}
	    	

	    }    
	  }

    //Écoute le changement de couleur du fond
    class CouleurPopUpFondListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {

        if(e.getSource() == menuPopUpFondVert)
          pan.setCouleurFond(Color.green);
        else if (e.getSource() == menuPopUpFondBleu)
          pan.setCouleurFond(Color.blue);
        else if(e.getSource() == menuPopUpFondRouge)
          pan.setCouleurFond(Color.red);
        else
          pan.setCouleurFond(Color.white);
      }
    }

    //Écoute le changement de couleur du fond
    class CouleurPopUpFormeListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuPopUpFormeVert)
          pan.setCouleurForme(Color.green);
        else if (e.getSource() == menuPopUpFormeBleu)
          pan.setCouleurForme(Color.blue);
        else if(e.getSource() == menuPopUpFormeRouge)
          pan.setCouleurForme(Color.red);
        else
          pan.setCouleurForme(Color.white);
      }
    }	

  	
  	
  	//
  	// Capture souris
  	//
	class PanSourisListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int x = arg0.getX();
			int y = arg0.getY();
			int xos = arg0.getXOnScreen();
			int yos = arg0.getYOnScreen();
		    System.out.println("mouse click : X="+x+", Y="+y+", XoS="+xos+", YoS="+yos);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent event) {
	        //Seulement s'il s'agit d'un clic droit
	        //if(event.getButton() == MouseEvent.BUTTON3)
			if(event.isPopupTrigger()){       
				menuPopUpCouleurFond.add(menuPopUpFondRouge);
				menuPopUpCouleurFond.add(menuPopUpFondBleu);
				menuPopUpCouleurFond.add(menuPopUpFondVert);
				
				menuPopUpCouleurForme.add(menuPopUpFormeRouge);
				menuPopUpCouleurForme.add(menuPopUpFormeBleu);
				menuPopUpCouleurForme.add(menuPopUpFormeVert);
				
				menuPopUpPanneau.add(menuItemLancer);
				menuPopUpPanneau.add(menuItemArreter);
				menuPopUpPanneau.add(menuPopUpCouleurForme);
				menuPopUpPanneau.add(menuPopUpCouleurFond);
				//La méthode qui va afficher le menu
				menuPopUpPanneau.show(pan, event.getX(), event.getY());
			}
		}
		
	}

	//
	// Capture clavier
	//
	class clavierListener implements KeyListener {
	
		@Override
		public void keyPressed(KeyEvent event) {
		    System.out.println("Code touche pressée : " + event.getKeyCode() + " - caractère touche pressée : " + event.getKeyChar());
		    //		    pause();		
		}
		@Override
		public void keyReleased(KeyEvent event) {
		    System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
		    if(!isNumeric(event.getKeyChar())){
		        jtf1.setText(jtf1.getText().replace(String.valueOf(event.getKeyChar()), ""));      	
//		        jtf1.setText("");      	
		    	
		    }

		    //		    pause();                  
		}
	
		@Override
		public void keyTyped(KeyEvent event) {
		    System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
		    //		    pause();
		}
	
	    //Retourne true si le paramètre est numérique, false dans le cas contraire
	    private boolean isNumeric(char carac){
		    try {
		      Integer.parseInt(String.valueOf(carac));
		    }
		    catch (NumberFormatException e) {
		      return false;            
		    }
		    return true;
	    }
	    
		private void pause(){
			  try {
			    Thread.sleep(1000);
			  } catch (InterruptedException e) {
			    e.printStackTrace();
			  }
		} 
	}

}

/*
 * 	class clavierListener implements KeyListener {
	
		@Override
		public void keyPressed(KeyEvent event) {
		    System.out.println("Code touche pressée : " + event.getKeyCode() + " - caractère touche pressée : " + event.getKeyChar());
		    if(!isNumeric(event.getKeyChar()))
		        jtf1.setText(jtf1.getText().replace(String.valueOf(event.getKeyChar()), ""));      	
		      }
		    //		    pause();		
		}
	
		@Override
		public void keyReleased(KeyEvent event) {
		    System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
		    //		    pause();                  
		}
	
		@Override
		public void keyTyped(KeyEvent event) {
		    System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
		    //		    pause();
		}
		
	    //Retourne true si le paramètre est numérique, false dans le cas contraire
	    private boolean isNumeric(char carac){
		    try {
		      Integer.parseInt(String.valueOf(carac));
		    }
		    catch (NumberFormatException e) {
		      return false;            
		    }
		    return true;
	    }
	
		private void pause(){
		  try {
		    Thread.sleep(1000);
		  } catch (InterruptedException e) {
		    e.printStackTrace();
		  }
		} 
	}

 * 
 */




/* 
 * Positionnement avec des BorderLayout 
 * 
 * */
/*  
public FenetreAnimee2(){
	JPanel panneauHaut = new JPanel();
	JPanel panneauHautNord = new JPanel();
	JPanel panneauHautSud = new JPanel();
  JPanel panneauSud = new JPanel();
  Font police = new Font("Arial", Font.BOLD, 14);

	  
	this.setTitle("Animation");
  this.setSize(300, 300);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);

  container.setBackground(Color.white);
  container.setLayout(new BorderLayout());
  container.add(pan, BorderLayout.CENTER);
  
  jtf.setFont(police);
  jtf.setPreferredSize(new Dimension(150, 30));
  jtf.setForeground(Color.BLUE);

  combo.setPreferredSize(new Dimension(100, 20));
  combo.addItem("Rond");
  combo.addItem("Carre");
  combo.addItem("Triangle");
  combo.addItem("Etoile");
  combo.addActionListener(new ComboActionListener());
  
  checkMorph.addActionListener(new Check1ActionListener());
  
  radio1.setSelected(true);
  radio1.addActionListener(new radioActionListener());
  radio2.addActionListener(new radioActionListener());
  radio3.addActionListener(new radioActionListener());
  
  radioGroup.add(radio1);
  radioGroup.add(radio2);
  radioGroup.add(radio3);
//  panneauHautNord.setLayout(new BorderLayout());
  panneauHautNord.add(combo, BorderLayout.WEST);
  panneauHautNord.add(checkMorph, BorderLayout.EAST);
//  panneauHautSud.setLayout(new BorderLayout());
//  panneauHautSud.add(radio1, BorderLayout.WEST);
//  panneauHautSud.add(radio2, BorderLayout.EAST);
  panneauHautSud.add(radio1);
  panneauHautSud.add(radio2);
  panneauHautSud.add(radio3);
  
  panneauHaut.setLayout(new BorderLayout());
  panneauHaut.add(panneauHautNord, BorderLayout.NORTH);
  panneauHaut.add(panneauHautSud, BorderLayout.SOUTH);
  
  container.add(panneauHaut, BorderLayout.NORTH);

  bouton.addActionListener(new BoutonListener()); 
  bouton.setEnabled(false);
  bouton2.addActionListener(new Bouton2Listener());

  panneauSud.add(bouton);
  panneauSud.add(bouton2);
  container.add(panneauSud, BorderLayout.SOUTH);


  // Ajout d'un label
//  Font police = new Font("Tahoma", Font.BOLD, 16);
//  label.setFont(police);
//  label.setForeground(Color.blue);
//  label.setHorizontalAlignment(JLabel.CENTER);
//  container.add(label, BorderLayout.NORTH);
  
  this.setContentPane(container);
  this.setVisible(true);
  go();
}
*/
