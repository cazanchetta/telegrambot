package com.fiap.javaplatform.telegrambot;

import java.time.LocalDateTime;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

/**
 * Classe principal onde será executado o projeto de Bot no Telegram para o usuário Majahbot
 * @author 335232, 335339, 335798
 * @author Carlos Eduardo Zanchetta, Deivid Christian Michetti Csapó, Victor Hugo Rodrigues de Oliveira
 */

public class Main {
	
	final private static Long CINCO_MINUTOS = 5L;

	public static void main(String[] args)  {
		
		MajahBot majahBot = new MajahBot();
		
		//controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
		int m=0;
		
		//loop que é executado por cinco minutos
		LocalDateTime agora = LocalDateTime.now();
		
		while (LocalDateTime.now().isBefore(agora.plusMinutes(CINCO_MINUTOS)) ){
			
			//lista de mensagens
			List<Update> updates = majahBot.mensagensPendentes(m);
			
			//análise de cada ação da mensagem
			for (Update update : updates) {
				
				//atualização do off-set
				m = update.updateId()+1;
				System.out.println("Recebendo mensagem: " + update.message().text());
				
				//verificação de ação de chat foi enviada com sucesso
				System.out.println(majahBot.estouEscrevendo(update));
				
				//verificação de mensagem enviada com sucesso
				System.out.println(majahBot.mensagemEnviada(update));
			}
			
		}
		
	}

}
