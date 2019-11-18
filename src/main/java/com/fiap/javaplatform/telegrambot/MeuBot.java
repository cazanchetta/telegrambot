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

public class MeuBot {
	
	final private static String TOKEN = "965581052:AAEAs6ojlHA4-6ld713lQAkcQ3u3Cnt4EBI";
	private TelegramBot bot;
	
	public MeuBot() {
		this.bot = criacaoDoBot();
	}
	
	private TelegramBot criacaoDoBot() {
		return TelegramBotAdapter.build(TOKEN);
	}
	
	public List<Update> mensagensPendentes(final int controle) {
		GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates().limit(100).offset(controle)); 
		return updatesResponse.updates();
	}
	
	public String estouEscrevendo(final Update update) {
		BaseResponse baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		return "Resposta de Chat Action Enviada ? " + baseResponse.isOk();
	}
	
	public String mensagemEnviada(final Update update) {
		SendResponse sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Não entendi..."));
		return "Mensagem Enviada ? " + sendResponse.isOk();
	}

}
