package com.fiap.javaplatform.telegrambot;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

/**
 * Classe que possui as funcionalidades do Bot do usuário Majahbot
 * @author 335232, 335339, 335798
 * @author Carlos Eduardo Zanchetta, Deivid Christian Michetti Csapó, Victor Hugo Rodrigues de Oliveira
 */

public class MajahBot {
	
	final private static String TOKEN = "965581052:AAEAs6ojlHA4-6ld713lQAkcQ3u3Cnt4EBI";
	private TelegramBot bot;
	
	public MajahBot() {
		this.bot = criacaoDoBot();
	}
	
	/**
	 * Criação de um Bot com o token definido nessa classe para o usuário Majahbot do Telegram 
	 * @return Objeto TelegramBot
	 */
	private TelegramBot criacaoDoBot() {
		return TelegramBotAdapter.build(TOKEN);
	}
	
	/**
	 * Busca as mensagens pendentes na fila
	 * @param controle ID para ler as mensagens pendentes na fila
	 * @return Lista de mensagens pendentes
	 */
	public List<Update> mensagensPendentes(final int controle) {
		GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates().limit(100).offset(controle)); 
		return updatesResponse.updates();
	}
	
	/**
	 * Mostra mensagem que o Bot está digitando
	 * @param update Objeto que representa a mensagem
	 * @return Mensagem
	 */
	public String estouEscrevendo(final Update update) {
		BaseResponse baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		return "Resposta de Chat Action Enviada ? " + baseResponse.isOk();
	}
	
	/**
	 * Envia mensagem para o Bot
	 * @param update Objeto que representa a mensagem
	 * @return Mensagem de retorno
	 */
	public String mensagemEnviada(final Update update) {
		String nomeUsuario = update.message().chat().firstName();
		String mensagem = ", nosso canal de atendimento está indisponível, favor tentar mais tarde.";
		SendResponse sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Olá " + nomeUsuario + mensagem));
		return "Mensagem Enviada ? " + sendResponse.isOk();
	}

}
