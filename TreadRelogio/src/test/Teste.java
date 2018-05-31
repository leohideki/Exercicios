package test;

import model.Relogio;
import model.Relogio2;


public class Teste {

	public static void main(String[] args) throws InterruptedException{
		 /*Relogio relogio = new Relogio();
	      //i <= 1440 para minutos e 86400 para segundos
	      for(int i = 0; i <= 1440; i++){
	         System.out.println(relogio.mostra());
	         relogio.ticTac();
	      }*/
	      
	     /* Relogio2 relogio2 = new Relogio2();
	      //i <= 1440 para minutos e 86400 para segundos
	      for(int i = 0; i <= 86400; i++){
	         System.out.println(relogio2.mostra());
	         relogio2.ticTac();
	      }*/
		Thread relogio1 = new Thread(new Relogio());
		Thread relogio2 = new Thread(new Relogio2());
		relogio1.start();
		relogio2.start();

	}
	
	


}
