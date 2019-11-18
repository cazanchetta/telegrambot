package com.fiap.javaplatform.telegrambot;

import java.time.LocalDateTime;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

public class Main {
	
	final private static Long CINCO_MINUTOS = 1L;

	public static void main(String[] args)  {
		
		MeuBot meuBot = new MeuBot();
		
		//controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
		int m=0;
		
		//loop que é executado por cinco minutos
		LocalDateTime agora = LocalDateTime.now();
		
		while (LocalDateTime.now().isBefore(agora.plusMinutes(CINCO_MINUTOS)) ){
			
			//lista de mensagens
			List<Update> updates = meuBot.mensagensPendentes(m);
			
			//análise de cada ação da mensagem
			for (Update update : updates) {
				
				//atualização do off-set
				m = update.updateId()+1;
				System.out.println("Recebendo mensagem: " + update.message().text());
				
				//verificação de ação de chat foi enviada com sucesso
				System.out.println(meuBot.estouEscrevendo(update));
				
				//verificação de mensagem enviada com sucesso
				System.out.println(meuBot.mensagemEnviada(update));
			}
			
		}
		
	}

}
