package com.test.fichiers;

//Packages � importer afin d'utiliser les objets
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/*import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
*/



public class TestFichiers {

	public void TestParcoursRepertoires() {
	    File f = new File("test.txt");
	    System.out.println("Chemin absolu du fichier : " + f.getAbsolutePath());
	    System.out.println("Nom du fichier : " + f.getName());
	    System.out.println("Est-ce qu'il existe ? " + f.exists());
	    System.out.println("Est-ce un r�pertoire ? " + f.isDirectory());
	    System.out.println("Est-ce un fichier ? " + f.isFile());
	    System.out.println("Affichage des lecteurs � la racine du PC : ");

	   
	    for(File file : f.listRoots())
	    {
	      System.out.println(file.getAbsolutePath());

	      try {
	        int i = 1;  
	        //On parcourt la liste des fichiers et r�pertoires
	        for(File nom : file.listFiles()){
	          //S'il s'agit d'un dossier, on ajoute un "/"
	          System.out.print("\t\t" + ((nom.isDirectory()) ? nom.getName()+"/" : nom.getName()));
	          if((i%4) == 0){
	            System.out.print("\n");
	          }
	          i++;
	        }

	        System.out.println("\n");
	      } catch (NullPointerException e) {
	        //L'instruction peut g�n�rer une NullPointerException
	        //s'il n'y a pas de sous-fichier !
	      }

	    }       	
	 }

	// Test lecture/�critures
	public void testFileInOut(){

      // Nous d�clarons nos objets en dehors du bloc try/catch
      FileInputStream fis = null;
      FileOutputStream fos = null;

      try {
         // On instancie nos objets :
         // fis va lire le fichier
         // fos va �crire dans le nouveau !
         fis = new FileInputStream(new File("test.txt"));
         fos = new FileOutputStream(new File("test2.txt"));

         // On cr�e un tableau de byte pour indiquer le nombre de bytes lus �
         // chaque tour de boucle
         byte[] buf = new byte[8];

         // On cr�e une variable de type int pour y affecter le r�sultat de
         // la lecture
         // Vaut -1 quand c'est fini
         int n = 0;
         int i = 0;
         
         // Tant que l'affectation dans la variable est possible, on boucle
         // Lorsque la lecture du fichier est termin�e l'affectation n'est
         // plus possible !
         // On sort donc de la boucle
         while ((n = fis.read(buf)) >= 0) {
            // On �crit dans notre deuxi�me fichier avec l'objet ad�quat
            fos.write(buf);
            // On affiche ce qu'a lu notre boucle au format byte et au
            // format char
 /*           for (byte bit : buf) {
               System.out.print("\t" + bit + "(" + (char) bit + ")");
            }
*/
            for (i=0; i<buf.length;i ++){
                System.out.print("\t" + buf[i] + "(" + (char) buf[i] + ")");
             }
         
            System.out.println("");
            //Nous r�initialisons le buffer � vide
            //au cas o� les derniers byte lus ne soient pas un multiple de 8
            //Ceci permet d'avoir un buffer vierge � chaque lecture et ne pas avoir de doublon en fin de fichier
            buf = new byte[8];
 
         }
         System.out.println("Copie termin�e !");

      } catch (FileNotFoundException e) {
         // Cette exception est lev�e si l'objet FileInputStream ne trouve
         // aucun fichier
         e.printStackTrace();
      } catch (IOException e) {
         // Celle-ci se produit lors d'une erreur d'�criture ou de lecture
         e.printStackTrace();
      } finally {
         // On ferme nos flux de donn�es dans un bloc finally pour s'assurer
         // que ces instructions seront ex�cut�es dans tous les cas m�me si
         // une exception est lev�e !
         try {
            if (fis != null)
               fis.close();
         } catch (IOException e) {
            e.printStackTrace();
         }

         try {
            if (fos != null)
               fos.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
	}
	
	public void testBufferedRead() {
	
	    //Nous d�clarons nos objets en dehors du bloc try/catch
	    FileInputStream fis;
	    BufferedInputStream bis;        
	    try {
	      fis = new FileInputStream(new File("test.txt"));
	      bis = new BufferedInputStream(new FileInputStream(new File("test.txt")));
	      byte[] buf = new byte[8];

	      //On r�cup�re le temps du syst�me
	      long startTime = System.currentTimeMillis();
	      //Inutile d'effectuer des traitements dans notre boucle
	      while(fis.read(buf) != -1);
	      //On affiche le temps d'ex�cution
	      System.out.println("Temps de lecture avec FileInputStream : " + (System.currentTimeMillis() - startTime));
	                
	      //On r�initialise                
	      startTime = System.currentTimeMillis();
	      //Inutile d'effectuer des traitements dans notre boucle
	      while(bis.read(buf) != -1);
	      //On r�affiche
	      System.out.println("Temps de lecture avec BufferedInputStream : " + (System.currentTimeMillis() - startTime));
	                
	      //On ferme nos flux de donn�es
	      fis.close();
	      bis.close();
	                
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }     	
	}
	
	public void testBufferedWrite(){
	    //Nous d�clarons nos objets en dehors du bloc try/catch
	    FileInputStream fis;
	    FileOutputStream fos;
	    BufferedInputStream bis;
	    BufferedOutputStream bos; 

	    try {
	      fis = new FileInputStream(new File("test.txt"));
	      fos = new FileOutputStream(new File("test2.txt"));
	      bis = new BufferedInputStream(new FileInputStream(new File("test.txt")));
	      bos = new BufferedOutputStream(new FileOutputStream(new File("test3.txt")));

	      byte[] buf = new byte[8];

	      //On r�cup�re le temps du syst�me
	      long startTime = System.currentTimeMillis();

	      while(fis.read(buf) != -1){
	        fos.write(buf);
	      }

	      //On affiche le temps d'ex�cution
	      System.out.println("Temps de lecture + �criture avec FileInputStream et FileOutputStream : " + (System.currentTimeMillis() - startTime));

	      //On r�initialise                
	      startTime = System.currentTimeMillis();

	      while(bis.read(buf) != -1){
	        bos.write(buf);
	      }

	      //On r�affiche
	      System.out.println("Temps de lecture + �criture avec BufferedInputStream et BufferedOutputStream : " + (System.currentTimeMillis() - startTime));

	      //On ferme nos flux de donn�es
	      fis.close();
	      bis.close();
	      fos.close();
	      bos.close();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }       		
	}
	
	public void testInOutStreams(){

	    //Nous d�clarons nos objets en dehors du bloc try/catch
	    DataInputStream dis;
	    DataOutputStream dos;
	    try {
	      dos = new DataOutputStream(
	              new BufferedOutputStream(
	                new FileOutputStream(
	                  new File("sdz.txt"))));

	      //Nous allons �crire chaque type primitif
	      dos.writeBoolean(true);
	      dos.writeByte(100);
	      dos.writeChar('C');
	      dos.writeDouble(12.05);
	      dos.writeFloat(100.52f);
	      dos.writeInt(1024);
	      dos.writeLong(123456789654321L);
	      dos.writeShort(2);
	      dos.close();
	        	
	      //On r�cup�re maintenant les donn�es !
	      dis = new DataInputStream(
	              new BufferedInputStream(
	                new FileInputStream(
	                  new File("sdz.txt"))));
	            
	      System.out.println(dis.readBoolean());
	      System.out.println(dis.readByte());
	      System.out.println(dis.readChar());
	      System.out.println(dis.readDouble());
	      System.out.println(dis.readFloat());
	      System.out.println(dis.readInt());
	      System.out.println(dis.readLong());
	      System.out.println(dis.readShort());
	        	
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }     	
	}
	
	
	public void testSerialization () {
	    //Nous d�clarons nos objets en dehors du bloc try/catch
	    ObjectInputStream ois;
	    ObjectOutputStream oos;
	    try {
	      oos = new ObjectOutputStream(
	              new BufferedOutputStream(
	                new FileOutputStream(
	                  new File("game.txt"))));
	        	
	      //Nous allons �crire chaque objet Game dans le fichier
	      oos.writeObject(new Game("Assassin Creed", "Aventure", 45.69));
	      oos.writeObject(new Game("Tomb Raider", "Plateforme", 23.45));
	      oos.writeObject(new Game("Tetris", "Strat�gie", 2.50));
	      //Ne pas oublier de fermer le flux !
	      oos.close();
	        	
	      //On r�cup�re maintenant les donn�es !
	      ois = new ObjectInputStream(
	              new BufferedInputStream(
	                new FileInputStream(
	                  new File("game.txt"))));
	            
	      try {
	        System.out.println("Affichage des jeux :");
	        System.out.println("*************************\n");
	        System.out.println(((Game)ois.readObject()).toString());
	        System.out.println(((Game)ois.readObject()).toString());
	        System.out.println(((Game)ois.readObject()).toString());
	      } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	      }
		
	      ois.close();
	        	
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }     	
	}
	
	
	public static void testCharArray_StringArray(){
		CharArrayWriter caw = new CharArrayWriter();
	    CharArrayReader car;

	    try {
	      caw.write("Coucou les Z�ros");
	      //Appel � la m�thode toString de notre objet de mani�re tacite
	      System.out.println(caw);

	      //caw.close() n'a aucun effet sur le flux
	      //Seul caw.reset() peut tout effacer
	      caw.close();

	      //On passe un tableau de caract�res � l'objet qui va lire le tampon
	      car = new CharArrayReader(caw.toCharArray());

	      int i;
	      //On remet tous les caract�res lus dans un String
	      String str = "";
	      while(( i = car.read()) != -1)
	        str += (char) i;

	      System.out.println(str);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    
	    
	    StringWriter sw = new StringWriter();
	    StringReader sr;

	    try {
	      sw.write("Coucou les Z�ros");
	      //Appel � la m�thode toString de notre objet de mani�re tacite
	      System.out.println(sw);

	      //caw.close() n'a aucun effet sur le flux
	      //Seul caw.reset() peut tout effacer
	      sw.close();

	      //On passe un tableau de caract�res � l'objet qui va lire le tampon
	      sr = new StringReader(sw.toString());         
	      int i ;

	      //On remet tous les caract�res lus dans un String
	      String str = "";
	      while(( i = sr.read()) != -1)
	        str += (char) i;

	      System.out.println(str);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }	    
	}
	
public void testFileReaderWriter(){
	File file = new File("testFileWriter.txt");
    FileWriter fw;
    FileReader fr;

    try {
      //Cr�ation de l'objet
      fw = new FileWriter(file);
      String str = "Bonjour � tous, amis Z�ros !\n";
      str += "\tComment allez-vous ? \n";

      //On �crit la cha�ne
      fw.write(str);
      //On ferme le flux
      fw.close();

      //Cr�ation de l'objet de lecture
      fr = new FileReader(file);
      str = "";
      int i = 0;

      //Lecture des donn�es
      while((i = fr.read()) != -1)
        str += (char)i;

      //Affichage
      System.out.println(str);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
}

public void testNewFileIOStream(){
	FileInputStream fis;
    BufferedInputStream bis;
    FileChannel fc;

    try {
      //Cr�ation des objets
      fis = new FileInputStream(new File("test.txt"));
      bis = new BufferedInputStream(fis);

      //D�marrage du chrono
      long time = System.currentTimeMillis();

      //Lecture
      while(bis.read() != -1);

      //Temps d'ex�cution
      System.out.println("Temps d'ex�cution avec un buffer conventionnel : " + (System.currentTimeMillis() - time));

      //Cr�ation d'un nouveau flux de fichier
      fis = new FileInputStream(new File("test.txt"));

      //On r�cup�re le canal
      fc = fis.getChannel();

      //On en d�duit la taille
      int size = (int)fc.size();

      //On cr�e un buffer correspondant � la taille du fichier
      ByteBuffer bBuff = ByteBuffer.allocate(size);

      //D�marrage du chrono
      time = System.currentTimeMillis();
      //D�marrage de la lecture
      fc.read(bBuff);

      //On pr�pare � la lecture avec l'appel � flip
      bBuff.flip();
      //Affichage du temps d'ex�cution

      System.out.println("Temps d'ex�cution avec un nouveau buffer : " + (System.currentTimeMillis() - time));

      //Puisque nous avons utilis� un buffer de byte afin de r�cup�rer les donn�es
      //Nous pouvons utiliser un tableau de byte
      //La m�thode array retourne un tableau de byte
      byte[] tabByte = bBuff.array();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
}
	
	public static void main(String[] args) {
		TestFichiers tFichiers = new TestFichiers();
		
//		tFichiers.TestParcoursRepertoires();
//		tFichiers.testFileInOut();
//		tFichiers.testBufferedRead();
//		tFichiers.testBufferedWrite();
//		tFichiers.testInOutStreams();
//		tFichiers.testSerialization();
//		tFichiers.testFileReaderWriter();
		tFichiers.testNewFileIOStream();


	}

}
