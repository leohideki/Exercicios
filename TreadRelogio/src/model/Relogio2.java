package model;

public class Relogio2 implements Runnable{
	   public Mostrador hora;
	   public Mostrador minuto;
	   public Mostrador segundo;
	   public String mostrador;
	   
	   public Relogio2(){
	      hora = new Mostrador(24);
	      minuto = new Mostrador(60);
	      segundo = new Mostrador(60);
	      atualizaMostrador();
	   }
	   public void ticTac(){
	      /*minuto.incrementa();
	      if(minuto.getValor()==0){
	         hora.incrementa();
	      }
	      atualizaMostrador();*/
		   segundo.incrementa();
		   if(segundo.getValor() == 0) {
			   minuto.incrementa();
			   if(minuto.getValor() == 0) {
				   hora.incrementa();
			   }
		   }
		   atualizaMostrador();
	   } 
	   private void atualizaMostrador(){
	      mostrador = hora.mostra()+":"+minuto.mostra()+":"+segundo.mostra();
	   }
	   public String mostra(){
	      return mostrador;
	   }
	   @Override
		public void run (){
			for(int i = 0; i <= 86400; i++){
		         System.out.println(mostra());
		         ticTac();
		         try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    }
			
		}
}
