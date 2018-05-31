package model;

public class Relogio implements Runnable{
	   public Mostrador hora;
	   public Mostrador minuto;
	   public String mostrador;
	   
	   public Relogio(){
	      hora = new Mostrador(24);
	      minuto = new Mostrador(60);
	      atualizaMostrador();
	   }
	   public void ticTac(){
	      minuto.incrementa();
	      if(minuto.getValor()==0){
	         hora.incrementa();
	      }
	      atualizaMostrador();
	   } 
	   private void atualizaMostrador(){
	      mostrador = hora.mostra()+":"+minuto.mostra();
	   }
	   public String mostra(){
	      return mostrador;
	   }
	   @Override
		public void run (){
			for(int i = 0; i <= 1440; i++){
		         System.out.println(mostra());
		         ticTac();
		         try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    }
			
		} 
}